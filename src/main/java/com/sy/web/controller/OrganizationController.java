package com.sy.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sy.common.Constants;
import com.sy.common.JsonUtil;
import com.sy.entity.Organization;
import com.sy.model.biz.vo.PageSet;
import com.sy.service.OrganizationService;

/**
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 14-2-14
 * <p>
 * Version: 1.0
 */
@Controller
@RequestMapping("/organization")
public class OrganizationController extends PageSet {

	@Autowired
	private OrganizationService organizationService;

	// sss
	@RequiresPermissions("organization:view")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST})
	public String index(Model model,HttpServletRequest request) {
		Organization orgn=null;
		Map<String,Object> map=new HashMap<>();
		String code=request.getParameter("code");
		String name=request.getParameter("name");
		if(StringUtils.isNotBlank(code)||StringUtils.isNotBlank(name)){
			orgn=new Organization();
			orgn.setCode(code);
			orgn.setName(name);
		}
		setPagination(request, map);
		List<Organization> organizationList = organizationService.findAllOrgByPageManage(orgn,map);
		Long count=organizationService.totalCount(orgn,map);
		model.addAttribute("organizationList", organizationList);
		model.addAttribute("totalCount",count);
		if(StringUtils.isNotBlank(code)){
			model.addAttribute("code",code);
		}
		if(StringUtils.isNotBlank(name)){
			model.addAttribute("name",name);
		}
		return "organization/orglayout";
	}

	@RequiresPermissions("organization:view")
	@RequestMapping(value = "/tree", method = RequestMethod.GET)
	public String showTree(Model model) {
		model.addAttribute("organizationList", organizationService.findAll());
		return "organization/tree";
	}

	@RequiresPermissions("organization:view")
	@RequestMapping(value = "/showTree2json", method = RequestMethod.GET)
	@ResponseBody
	public List<Organization> showTree2json(Model model) {
		List<Organization> orgList = organizationService.findAll();
		return orgList;
	}

	@RequiresPermissions("organization:create")
	@RequestMapping(value = "/{parentId}/appendChild", method = RequestMethod.GET)
	public String showAppendChildForm(@PathVariable("parentId") Long parentId,
			Model model) {
		Organization parent = organizationService.findOne(parentId);
		Organization child = new Organization();
		child.setParentId(parentId);
		child.setParentIds(parent.makeSelfAsParentIds());

		model.addAttribute("parent", parent);
		model.addAttribute("child", child);
		model.addAttribute("op", "新增");
		return "organization/add_organization";
	}

	// sss
	@RequiresPermissions("organization:create")
	@RequestMapping(value = "/{parentId}/appendChild", method = RequestMethod.POST)
	@ResponseBody
	public String create(Organization organization) {
		organizationService.createOrganization(organization);
		// return "redirect:/organization/success";
		return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_ADD_SUCCESS, Constants.MSG_REL_ORG, null,Constants.CLOSECURRENT, "organization");
	}

	@RequiresPermissions("organization:update")
	@RequestMapping(value = "/{id}/maintain", method = RequestMethod.GET)
	public String showMaintainForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("organization", organizationService.findOne(id));
		return "organization/upt_organization";
	}

	@RequiresPermissions("organization:update")
	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	@ResponseBody
	public String update(Organization organization,RedirectAttributes redirectAttributes) {
		organizationService.updateOrganization(organization);
		//redirectAttributes.addFlashAttribute("msg", "修改成功");
		//return "redirect:/organization/success";
		return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_UPDATE_SUCCESS, Constants.MSG_REL_ORG, null,Constants.CLOSECURRENT, "organization");
	}

	@RequiresPermissions("organization:delete")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(@PathVariable("id") Long id,RedirectAttributes redirectAttributes) {
		organizationService.deleteOrganization(id);
		//redirectAttributes.addFlashAttribute("msg", "删除成功");
		//return "redirect:/organization/success";
		return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_DEL_SUCCESS, null, null,Constants.FORWARD, "organization");
	}

	@RequiresPermissions("organization:update")
	@RequestMapping(value = "/{sourceId}/move", method = RequestMethod.GET)
	public String showMoveForm(@PathVariable("sourceId") Long sourceId,
			Model model) {
		Organization source = organizationService.findOne(sourceId);
		model.addAttribute("source", source);
		model.addAttribute("targetList",
				organizationService.findAllWithExclude(source));
		return "organization/move";
	}

	@RequiresPermissions("organization:update")
	@RequestMapping(value = "/{sourceId}/move", method = RequestMethod.POST)
	public String move(@PathVariable("sourceId") Long sourceId,
			@RequestParam("targetId") Long targetId) {
		Organization source = organizationService.findOne(sourceId);
		Organization target = organizationService.findOne(targetId);
		organizationService.move(source, target);
		return "redirect:/organization/success";
	}

	@RequiresPermissions("organization:view")
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String success() {
		return "organization/success";
	}


}

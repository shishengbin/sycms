package com.sy.web.controller.cms;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.sy.common.Constants;
import com.sy.common.JsonUtil;
import com.sy.model.biz.vo.CmsNewsCategoryVo;
import com.sy.model.biz.vo.RecordState;
import com.sy.model.core.domain.CmsNewsCategory;
import com.sy.service.cms.CmsNewsCategoryService;


@Controller
@RequestMapping(value="/ncategory")
public class CmsNewsCategoryController {
	
	@Resource
	private CmsNewsCategoryService cncservice;
	
	@RequiresPermissions("ncategory:view")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST})
	public String getNewsCategoriesByPage(Model model,@ModelAttribute CmsNewsCategoryVo categoryvo,HttpServletRequest request){
		PageInfo<CmsNewsCategory> ncategorylist=cncservice.findAllNewsCategoriesByPage(categoryvo);
		model.addAttribute("ncategorylist", ncategorylist);
		return "ncategory/newscategorylist";
		
	}
	
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String preAddNewsCategory(Model model){
		model.addAttribute("newscategory",new CmsNewsCategory());
		return "ncategory/add_news_category";
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	@ResponseBody
	public String saveNewsCategory(@ModelAttribute CmsNewsCategory cmsnewscategory){
		cncservice.createNewsCategory(cmsnewscategory);
		return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_ADD_SUCCESS, Constants.MSG_REL_NEWS_CATEGORY, "",Constants.CLOSECURRENT, "");
	}
	
	
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public String preUpdateNewsCategory(Model model,HttpServletRequest request){
		CmsNewsCategory cncgory=null;
		String nidstr=request.getParameter("nid");
		if(StringUtils.isNotBlank(nidstr)){
			Integer nid=Integer.parseInt(nidstr);
			cncgory=cncservice.findNewsCategoryById(nid);
		}
		model.addAttribute("newscategory",cncgory);
		return "ncategory/upd_news_category";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public String saveNewsCategoryByUpd(@ModelAttribute CmsNewsCategory cmsnewscategory){
		cncservice.updateNewsCategory(cmsnewscategory);
		return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_UPDATE_SUCCESS, Constants.MSG_REL_NEWS_CATEGORY, "",Constants.CLOSECURRENT, "");
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	public String saveNewsCategoryByUpd(HttpServletRequest request){
		CmsNewsCategory cncgory=null;
		String nidstr=request.getParameter("nid");
		if(StringUtils.isNotBlank(nidstr)){
			Integer nid=Integer.parseInt(nidstr);
			cncgory=new CmsNewsCategory();
			cncgory.setNid(nid);
			cncgory.setNewsStatus(RecordState.deleted.getByteIndex());
			cncservice.updateNewsCategory(cncgory);
		}
		return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_DEL_SUCCESS, Constants.MSG_REL_NEWS_CATEGORY, "",Constants.FORWARD, "ncategory");
	}

}

package com.sy.web.controller;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sy.common.Constants;
import com.sy.common.JsonUtil;
import com.sy.entity.User;
import com.sy.model.biz.vo.PageSet;
import com.sy.service.OrganizationService;
import com.sy.service.RoleService;
import com.sy.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends PageSet {

    @Autowired
    private UserService userService;

    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private RoleService roleService;

    @RequiresPermissions("user:view")
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST})
    public String list(Model model) {
        model.addAttribute("userList", userService.findAll());
        return "user/userlist";
    }

    @RequiresPermissions("user:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
       setCommonData(model);
       model.addAttribute("user", new User());
       model.addAttribute("op", "新增");
       return "user/createuser";
    }

    /**
     * 备注下：花了点时间
     * return 
     * post 创建完用户，关闭dialog，返回列表list，开始用的method=RequestMethod.GET,
     * post-->get,肯定不行
     * @param user
     * @param model
     * @return
     */
    //sss
    @RequiresPermissions("user:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public String createBySave(User user, Model model) {
    	userService.createUser(user);
    	return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_ADD_SUCCESS, Constants.MSG_REL_USER, "",Constants.CLOSECURRENT, "");
    }

    @RequiresPermissions("user:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        setCommonData(model);
        model.addAttribute("upduser", userService.findOne(id));
        model.addAttribute("op", "修改");
        return "user/edituser";
    }

    @RequiresPermissions("user:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(User user, RedirectAttributes redirectAttributes) {
        userService.updateUser(user);
        return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_UPDATE_SUCCESS, Constants.MSG_REL_USER, null,Constants.CLOSECURRENT, "user");
    }

   @RequiresPermissions("user:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String showDeleteForm(@PathVariable("id") Long id, Model model) {
        setCommonData(model);
        model.addAttribute("user", userService.findOne(id));
        model.addAttribute("op", "删除");
        return "user/edit";
    }

    @RequiresPermissions("user:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        userService.deleteUser(id);
        //redirectAttributes.addFlashAttribute("msg", "删除成功");
        //return "redirect:/user";
        return JsonUtil.transferJsonResponse(Constants.SUCCESS,Constants.MSG_DEL_SUCCESS, null, null,Constants.FORWARD, "user");
    }

   @RequiresPermissions("user:update")
    @RequestMapping(value = "/{id}/changePassword", method = RequestMethod.GET)
    public String showChangePasswordForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findOne(id));
        model.addAttribute("op", "修改密码");
        return "user/changePassword";
    }

    @RequiresPermissions("user:update")
    @RequestMapping(value = "/{id}/changePassword", method = RequestMethod.POST)
    public String changePassword(@PathVariable("id") Long id, String newPassword, RedirectAttributes redirectAttributes) {
        userService.changePassword(id, newPassword);
        redirectAttributes.addFlashAttribute("msg", "修改密码成功");
        return "redirect:/user";
    }
    
    
    private void setCommonData(Model model) {
        model.addAttribute("organizationList", organizationService.findAll());
        model.addAttribute("roleList", roleService.findAll());
    }

}

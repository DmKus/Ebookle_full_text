package com.ebookle.controller;

import com.ebookle.entity.User;
import com.ebookle.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 28.08.13
 * Time: 6:46
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class AdministrationController {

    @Autowired
    private AdminService adminService;


    @Secured("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String goToAdminPage (ModelMap modelMap) {

        List<User> users = adminService.getUsersHasRoleUser();
        modelMap.addAttribute("users", users);
        return "admin";
    }

    @Secured("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/removeUser/{id}", method = RequestMethod.POST)
    public String removeUser (@PathVariable("id") Integer userId) {

        adminService.removeUser(userId);
        return "redirect:/admin";
    }
}

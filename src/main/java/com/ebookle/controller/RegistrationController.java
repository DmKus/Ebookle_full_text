package com.ebookle.controller;

import com.ebookle.service.RegistrationService;
import com.ebookle.util.UtilStrings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 28.08.13
 * Time: 6:33
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @RequestMapping("/register")
    public String register (RedirectAttributes redirectAttributes,
                            @RequestParam("login") String login,
                            @RequestParam("password") String password,
                            @RequestParam("email") String email,
                            @RequestParam("name") String name,
                            @RequestParam("surname") String surname) {

        if (! checkParams(login, password, email, name, surname)) {
            redirectAttributes.addFlashAttribute("badInput", UtilStrings.BAD_INPUT);
            return "redirect:/registration";
        }
        registrationService.register(login, password, email, name, surname);
        redirectAttributes.addFlashAttribute("flashMessage", UtilStrings.SEND_DATA_SUCCESS);
        return "redirect:/";
    }

    @RequestMapping(value = "/registration_success", method = RequestMethod.GET)
    public String registrationSuccess (ModelMap modelMap, @RequestParam("key") String key) {

        if (registrationService.activateUser(key)) {
            modelMap.addAttribute("registrationSuccess", UtilStrings.REGISTRATION_SUCCESS);
            return "registration_success";
        }
        return "registration_failed";
    }

    private boolean checkParams (String login, String password, String email, String name, String surname) {
        if ("".equals(login.trim())
                || "".equals(password.trim())
                || "".equals(email.trim())
                || "".equals(name.trim())
                || "".equals(surname.trim())) {
            return false;
        }
        if (! checkLogin(login)) {
            return false;
        }
        return true;
    }

    private boolean checkLogin (String login) {
        //TODO: check login in database
        return true;
    }


    @RequestMapping(value = "/registration")
    public String goToRegistration (ModelMap modelMap) {
        return "registration";
    }


}

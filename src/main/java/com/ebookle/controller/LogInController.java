package com.ebookle.controller;

import com.ebookle.entity.Book;
import com.ebookle.entity.User;
import com.ebookle.service.BookService;
import com.ebookle.service.UserService;
import com.ebookle.util.UtilStrings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

/*
import com.ebookle.entity.Category;
import com.ebookle.entity.Chapter;
*/

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 24.08.13
 * Time: 1:55
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class LogInController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    //TODO: add rus internationalization (model.put(JRParameter.REPORT_RESOURCE_BUNDLE , resourceBundle);)

    @RequestMapping("/")
    public String goHome (ModelMap modelMap, Principal principal, RedirectAttributes redirectAttributes) {

        try {
            List<Book> books = bookService.findAllWithAuthors();
            if (books == null) {
                return showFlashMessage("Bad database", redirectAttributes);
            }
            modelMap.addAttribute("books", books);
            if (principal == null) {
                modelMap.addAttribute("person", UtilStrings.GUEST_PERSON);
                return "home";
            }

            String login = principal.getName();
            User user = userService.findByLogin(login);
            if (user == null) {
                return showFlashMessage("Bad database", redirectAttributes);
            }
            if (user.getRole().equals(UtilStrings.USER_ROLE_TEXT)){
                modelMap.addAttribute("person", UtilStrings.USER_PERSON);
                modelMap.addAttribute("user", user);
                modelMap.addAttribute("userBooks", user.getBooks());
            }
            if (user.getRole().equals(UtilStrings.ADMIN_ROLE_TEXT)){
                modelMap.addAttribute("person", UtilStrings.ADMIN_PERSON);
                modelMap.addAttribute("user", user);
                modelMap.addAttribute("userBooks", user.getBooks());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "home";
    }

    @RequestMapping("/home")
    public String goHome () {
        return "redirect:/";
    }


    @RequestMapping(value = "/welcome")
    public String welcomeUser (ModelMap modelMap, Principal principal) {
        modelMap.addAttribute("userLogin", principal.getName());
        return "redirect:/";
    }

    @RequestMapping(value = "/login_fail")
    public String loginFail (RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("flashMessage", "Login was failed!");
        return "redirect:/";
    }

    @RequestMapping(value = "/logout")
    public String logout (RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("flashMessage", "You are log out");
        return "redirect:/";
    }

    private String showFlashMessage (String flashMessage, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("flashMessage", flashMessage);
        return "home";
    }

}

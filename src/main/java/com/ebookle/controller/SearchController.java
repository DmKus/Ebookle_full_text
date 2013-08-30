package com.ebookle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 28.08.13
 * Time: 10:36
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class SearchController {

    @RequestMapping(value = "/most_popular")
    public String searchMostPopular () {

        return "redirect:/";
    }

    @RequestMapping(value = "/recent")
    public String searchRecent () {

        return "redirect:/";
    }

}

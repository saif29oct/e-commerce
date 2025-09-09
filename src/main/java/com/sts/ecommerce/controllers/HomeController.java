package com.sts.ecommerce.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author saif
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }

}

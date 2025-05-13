package com.saber.spring_boot_web_demo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {
    @RequestMapping(value = {"/", "index"})
    public String indexPage(Model model) {
        return "index.html";
    }
    @RequestMapping(value = {"hello"})
    public String helloPage(Model model) {
        String firstName = "saber";
        String lastName = "azizi";
        model.addAttribute("firstName",firstName);
        model.addAttribute("lastName",lastName);
        return "hello";
    }

}
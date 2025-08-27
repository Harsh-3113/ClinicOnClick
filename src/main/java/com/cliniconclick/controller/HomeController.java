package com.cliniconclick.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "ClinicOnClick - Healthcare Management System");
        return "index";
    }
    
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About Us - ClinicOnClick");
        return "about";
    }
    
    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("title", "Contact Us - ClinicOnClick");
        return "contact";
    }
}

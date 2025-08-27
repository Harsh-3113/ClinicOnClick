package com.cliniconclick.controller;

import com.cliniconclick.entity.User;
import com.cliniconclick.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;
    
    // DoctorService no longer needed in registration flow

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                           @RequestParam(value = "logout", required = false) String logout,
                           Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password");
        }
        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully");
        }
        model.addAttribute("title", "Login - ClinicOnClick");
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("title", "Register - ClinicOnClick");
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                              @RequestParam String email,
                              @RequestParam String password,
                              @RequestParam String firstName,
                              @RequestParam String lastName,
                              RedirectAttributes redirectAttributes) {
        try {
            // Create a new User object
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            
            userService.createUser(user);
            redirectAttributes.addFlashAttribute("success", "Registration successful! Please login.");
            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/register";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("title", "Dashboard - ClinicOnClick");
        return "dashboard/index";
    }
}

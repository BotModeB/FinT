package com.finances.finT.Controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class WelcomeController {
    @GetMapping("/welcome")
    public String showWelcomePage(Model model) {
        model.addAttribute("username", "Имя пользователя"); // Тестовое значение
        return "welcome";
    }

    @PostMapping("/welcome")
    public String handleAction( @RequestParam String action,RedirectAttributes redirectAttributes) {
        
        if ("add".equals(action)) {
            return "redirect:/expenses/add";
        } else if ("read".equals(action)) {
            return "redirect:/expenses";
        }
        return "redirect:/welcome";
    }
}
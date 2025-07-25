package com.finances.finT.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class autor {
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @GetMapping("/welcome")
    public String showWelcomePage() {
        return "welcome";
    }

    @GetMapping("/add_categories")
    public String showAddCategoriesPage() {
        return "add_categories";
    }

    // @GetMapping("/add_expenses")
    // public String showAddExpensesPage() {
    //     return "add_expenses";
    // }

    @GetMapping("/read_expenses")
    public String showReadExpensesPage() {
        return "read_expenses";
    }

        @GetMapping("/login.html")
    public String showLoginPage1() {
        return "login";
    }

    @GetMapping("/register.html")
    public String showRegisterPage1() {
        return "register";
    }

    @GetMapping("/welcome.html")
    public String showWelcomePage1() {
        return "welcome";
    }

    @GetMapping("/add_categories.html")
    public String showAddCategoriesPage1() {
        return "add_categories";
    }

    @GetMapping("/add_expenses.html")
    public String showAddExpensesPage1(Model model) {
        return "add_expenses";
    }

    @GetMapping("/read_expenses.html")
    public String showReadExpensesPage1() {
        return "read_expenses";
    }
}

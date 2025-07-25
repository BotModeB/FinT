package com.finances.finT.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.finances.finT.service.CategoryService;
import com.finances.finT.service.ExpenseService;
import com.finances.finT.models.*;
import com.finances.finT.repositories.UserRepository;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/expenses")
public class ExpenseController { 

    private final ExpenseService expenseService;  
    private final CategoryService categoryService;
    private final UserRepository userRepository;
    
    @Autowired
    public ExpenseController(
        ExpenseService expenseService,
        CategoryService categoryService,
        UserRepository userRepository
    ) {
        this.expenseService = expenseService;
        this.categoryService = categoryService;
        this.userRepository = userRepository; 
    }

@GetMapping("/add")
public String showAddForm(Model model) {
    try {
        // Логирование для отладки
        System.out.println("Loading categories...");
        List<categories> categories = categoryService.getAllCategories();
        System.out.println("Found " + categories.size() + " categories");
        
        model.addAttribute("categories", categories);
        model.addAttribute("expense", new expenseDTO());
        return "add_expenses";
    } catch (Exception e) {
        System.err.println("Error in showAddForm: " + e.getMessage());
        e.printStackTrace();
        return "test"; // Создайте страницу error.html
    }
}

    @PostMapping("/add")
    public String addExpense(
        @ModelAttribute("expense") expenseDTO expenseDto,
        Authentication authentication,
        BindingResult result) {

        if (result.hasErrors()) {
            return "add_expenses";
        }    

        String username = authentication.getName();
        users user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        categories category = categoryService.getCategoryById(expenseDto.getCategoryId())
            .orElseThrow(() -> new RuntimeException("Category not found"));

        expenses expense = new expenses();
        expense.setDescription(expenseDto.getDescription());
        expense.setAmount(expenseDto.getAmount());
        expense.setNotes(expenseDto.getNotes());
        expense.setUser(user);
        expense.setCategory(category);
    
        expenseService.saveExpense(expense);
        return "redirect:/expenses";
    }

    @PostMapping("/delete/{id}")
    public String deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return "redirect:/expenses";
    }
    @GetMapping("/edit/{id}")
    public String linkExpense() {
        return "edit_expenses";
    }

    // @GetMapping
    // public String getAllExpenses(Model model) {
    //     List<expenses> expensesList = expenseService.getAllExpenses();
    //     model.addAttribute("expenses", expensesList);
        
    //     // Рассчет общей суммы
    //     BigDecimal total = expensesList.stream()
    //         .map(expenses::getAmount)
    //         .reduce(BigDecimal.ZERO, BigDecimal::add);
    //     model.addAttribute("totalAmount", total);
        
    //     return "expenses_list";
    // }

    @GetMapping
    public String getUserExpenses(Model model, Authentication authentication) {
    String username = authentication.getName();
    users user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    
    List<expenses> userExpenses = expenseService.getExpensesByUser(user);
    model.addAttribute("expenses", userExpenses);
    
    BigDecimal total = userExpenses.stream()
        .map(expenses::getAmount)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
    model.addAttribute("totalAmount", total);
    
    return "expenses_list"; 
    
}
}
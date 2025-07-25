package com.finances.finT.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.finances.finT.models.*; 
import com.finances.finT.repositories.ExpensesRepository;

@Service
@Transactional
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpensesRepository expenseRepository;

    @Autowired
    public ExpenseServiceImpl(ExpensesRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public expenses saveExpense(expenses expense) {
        System.out.println("Saving expense: " + expense);
        expenses saved = expenseRepository.save(expense);
        System.out.println("Saved expense ID: " + saved.getId());
        return saved;
    }
    
    @Override
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    @Override
    public List<expenses> getAllExpenses() {
        return expenseRepository.findAll();
    }

    @Override
    public List<expenses> getExpensesByCategory(Long categoryId) {
        return expenseRepository.findByCategoryId(categoryId);
    }
    @Override
    public List<expenses> getExpensesByUser(users user) {
    return expenseRepository.findByUser(user);
}
}
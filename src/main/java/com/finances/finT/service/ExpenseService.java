package com.finances.finT.service;

import java.util.List;
import com.finances.finT.models.expenses;
import com.finances.finT.models.users;

public interface ExpenseService {
    expenses saveExpense(expenses expense);
    List<expenses> getAllExpenses();
    List<expenses> getExpensesByCategory(Long categoryId);
    void deleteExpense(Long id);
    List<expenses> getExpensesByUser(users user);
    expenses getExpenseById(Long id);
    expenses updateExpense(expenses expense);
}
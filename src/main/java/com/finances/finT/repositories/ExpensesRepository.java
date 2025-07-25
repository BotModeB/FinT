package com.finances.finT.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finances.finT.models.expenses;
import com.finances.finT.models.users;

public interface ExpensesRepository extends JpaRepository<expenses, Long> {
    List<expenses> findByCategoryId(Long categoryId);
    List<expenses> findByUser(users user);
}
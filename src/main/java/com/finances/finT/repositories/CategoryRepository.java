package com.finances.finT.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.finances.finT.models.categories;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<categories, Long> {
}
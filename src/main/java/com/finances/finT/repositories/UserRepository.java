package com.finances.finT.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.finances.finT.models.users;

import java.util.Optional;

public interface UserRepository extends JpaRepository<users, Long> {
    Optional<users> findByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
    Optional<users> findByUsername(String username);
}
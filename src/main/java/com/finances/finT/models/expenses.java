package com.finances.finT.models;

import lombok.Data;

import java.math.BigDecimal;

import jakarta.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "expenсes")
public class expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "text")
    private String description;

    @Column(nullable = false, name = "price")
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "categoryid", nullable = false)
    private categories category;

    @Column(nullable = true, name = "particularity")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", nullable = false)
    private users user;

    @Column(name = "date", nullable = false)
    private LocalDate date = LocalDate.now();

    // Геттеры и сеттеры остаются без изменений
}
package com.finanzas.breadcredit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "installment")
public class Installment {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "installment_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "purchase_id", nullable = false)
    private Purchase purchase;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;
    @Column(name = "amount", nullable = false, precision = 16, scale = 2)
    private BigDecimal amount;

}
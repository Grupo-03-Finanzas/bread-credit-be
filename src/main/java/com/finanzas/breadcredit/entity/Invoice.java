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
@Table(name = "invoice")
public class Invoice {

    @Id
    @SequenceGenerator(name = "invoice_idinvoice_seq", sequenceName = "invoice_idinvoice_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoice_idinvoice_seq")
    @Column(name = "invoice_id", nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "purchase_id", referencedColumnName = "purchase_id", nullable = false)
    private Purchase purchase;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;
    @Column(name = "amount", nullable = false, precision = 16, scale = 2)
    private BigDecimal amount;

}
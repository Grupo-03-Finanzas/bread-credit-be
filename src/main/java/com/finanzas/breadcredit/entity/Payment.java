package com.finanzas.breadcredit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false)
    private Long id;

    @OneToOne(mappedBy = "payment")
    private Invoice invoice;
    @OneToOne(mappedBy = "payment")
    private Installment installment;

    @Column(name = "amount", nullable = false, precision = 16, scale = 12)
    private BigDecimal amount;
    @Column(name = "\"time\"", nullable = false)
    private Instant time;

}
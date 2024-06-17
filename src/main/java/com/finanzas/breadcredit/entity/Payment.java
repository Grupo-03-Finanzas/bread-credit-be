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
    @SequenceGenerator(name = "payment_idpayment_seq", sequenceName = "payment_idpayment_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_idpayment_seq")
    @Column(name = "payment_id", nullable = false)
    private Integer id;

    @Column(name = "amount", nullable = false, precision = 16, scale = 12)
    private BigDecimal amount;
    @Column(name = "\"time\"", nullable = false)
    private Instant time;

}
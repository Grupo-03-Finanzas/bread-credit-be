package com.finanzas.breadcredit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "creditaccount_id", nullable = false)
    private Creditaccount creditaccount;
    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL)
    private Set<Installment> installments;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @Column(name = "initial_cost", nullable = false, precision = 16, scale = 2)
    private BigDecimal initialCost;
    @Column(name = "\"time\"", nullable = false)
    private Instant time;
    @Column(name = "installment_number", nullable = false)
    private Long installmentNumber;
    @Column(name = "credit_rate_type", nullable = false, length = 3)
    private String creditRateType;
    @Column(name = "credit_rate", nullable = false, precision = 16, scale = 12)
    private BigDecimal creditRate;
    @Column(name = "credit_compouding", nullable = false)
    private Long creditCompouding;
    @Column(name = "penalty_rate_type", nullable = false, length = 3)
    private String penaltyRateType;
    @Column(name = "penalty_rate", nullable = false, precision = 16, scale = 12)
    private BigDecimal penaltyRate;
    @Column(name = "penalty_compouding", nullable = false)
    private Long penaltyCompouding;
    @Column(name = "compensatory_rate_type", nullable = false, length = 3)
    private String compensatoryRateType;
    @Column(name = "compensatory_rate", nullable = false, precision = 16, scale = 12)
    private BigDecimal compensatoryRate;
    @Column(name = "compensatory_compouding", nullable = false)
    private Long compensatoryCompouding;

}
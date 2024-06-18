package com.finanzas.breadcredit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "creditaccount")
public class Creditaccount {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "creditaccount_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    @Column(name = "active", nullable = false)
    private Boolean active = false;
    @Column(name = "max_credit", nullable = false, precision = 16, scale = 12)
    private BigDecimal maxCredit;
    @Column(name = "current_credit", nullable = false, precision = 16, scale = 12)
    private BigDecimal currentCredit;
    @Column(name = "billing_day")
    private Long billingDay;
    @Column(name = "credit_type_of_rate", nullable = false, length = 3)
    private String creditTypeOfRate;
    @Column(name = "credit_rate", nullable = false, precision = 16, scale = 12)
    private BigDecimal creditRate;
    @Column(name = "credit_compounding", nullable = false)
    private Long creditCompounding;
    @Column(name = "invoice_penalty_rate_type", nullable = false, length = 3)
    private String invoicePenaltyRateType;
    @Column(name = "invoice_penalty_rate", nullable = false, precision = 16, scale = 12)
    private BigDecimal invoicePenaltyRate;
    @Column(name = "invoice_penalty_compouding", nullable = false)
    private Long invoicePenaltyCompouding;
    @Column(name = "installment_penalty_rate_type", nullable = false, length = 3)
    private String installmentPenaltyRateType;
    @Column(name = "installment_penalty_rate", nullable = false, precision = 16, scale = 12)
    private BigDecimal installmentPenaltyRate;
    @Column(name = "installment_penalty_compouding", nullable = false)
    private Long installmentPenaltyCompouding;
    @Column(name = "invoice_compensatory_rate_type", nullable = false, length = 3)
    private String invoiceCompensatoryRateType;
    @Column(name = "invoice_compensatory_rate", nullable = false, precision = 16, scale = 12)
    private BigDecimal invoiceCompensatoryRate;
    @Column(name = "invoice_compensatory_compouding", nullable = false)
    private Long invoiceCompensatoryCompouding;
    @Column(name = "installment_compensatory_rate_type", nullable = false, length = 3)
    private String installmentCompensatoryRateType;
    @Column(name = "installment_compensatory_rate", nullable = false, precision = 16, scale = 12)
    private BigDecimal installmentCompensatoryRate;
    @Column(name = "installment_compensatory_compouding", nullable = false)
    private Long installmentCompensatoryCompouding;

}
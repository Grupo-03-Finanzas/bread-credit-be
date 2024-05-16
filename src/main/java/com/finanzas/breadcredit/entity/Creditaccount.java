package com.finanzas.breadcredit.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "creditaccount")
public class Creditaccount {
    @Id
    @SequenceGenerator(name="creditaccount_idcreditaccount_seq",sequenceName="creditaccount_idcreditaccount_seq",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="creditaccount_idcreditaccount_seq")
    @Column(name = "creditaccount_id", nullable = false)
    private Integer id;

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

    @Column(name = "credit_type_of_rate", nullable = false, length = 3)
    private String creditTypeOfRate;

    @Column(name = "credit_rate", nullable = false, precision = 16, scale = 12)
    private BigDecimal creditRate;

    @Column(name = "credit_compounding", nullable = false)
    private Integer creditCompounding;

    @Column(name = "invoice_penalty_rate_type", nullable = false, length = 3)
    private String invoicePenaltyRateType;

    @Column(name = "invoice_penalty_rate", nullable = false, precision = 16, scale = 12)
    private BigDecimal invoicePenaltyRate;

    @Column(name = "invoice_penalty_compouding", nullable = false)
    private Integer invoicePenaltyCompouding;

    @Column(name = "installment_penalty_rate_type", nullable = false, length = 3)
    private String installmentPenaltyRateType;

    @Column(name = "installment_penalty_rate", nullable = false, precision = 16, scale = 12)
    private BigDecimal installmentPenaltyRate;

    @Column(name = "installment_penalty_compouding", nullable = false)
    private Integer installmentPenaltyCompouding;

    @Column(name = "invoice_compensatory_rate_type", nullable = false, length = 3)
    private String invoiceCompensatoryRateType;

    @Column(name = "invoice_compensatory_rate", nullable = false, precision = 16, scale = 12)
    private BigDecimal invoiceCompensatoryRate;

    @Column(name = "invoice_compensatory_compouding", nullable = false)
    private Integer invoiceCompensatoryCompouding;

    @Column(name = "installment_compensatory_rate_type", nullable = false, length = 3)
    private String installmentCompensatoryRateType;

    @Column(name = "installment_compensatory_rate", nullable = false, precision = 16, scale = 12)
    private BigDecimal installmentCompensatoryRate;

    @Column(name = "installment_compensatory_compouding", nullable = false)
    private Integer installmentCompensatoryCompouding;

}
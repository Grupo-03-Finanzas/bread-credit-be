package com.finanzas.breadcredit.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "purchase")
public class Purchase {
    @Id
    @SequenceGenerator(name="purchase_idpurchase_seq",sequenceName="purchase_idpurchase_seq",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="purchase_idpurchase_seq")
    @Column(name = "purchase_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "creditaccount_id", nullable = false)
    private Creditaccount creditaccount;

    @Column(name = "initial_cost", nullable = false, precision = 16, scale = 2)
    private BigDecimal initialCost;

    @Column(name = "\"time\"", nullable = false)
    private Instant time;

    @Column(name = "installment_number", nullable = false)
    private Integer installmentNumber;

    @Column(name = "credit_rate_type", nullable = false, length = 3)
    private String creditRateType;

    @Column(name = "credit_rate", nullable = false, precision = 16, scale = 12)
    private BigDecimal creditRate;

    @Column(name = "credit_compouding", nullable = false)
    private Integer creditCompouding;

    @Column(name = "penalty_rate_type", nullable = false, length = 3)
    private String penaltyRateType;

    @Column(name = "penalty_rate", nullable = false, precision = 16, scale = 12)
    private BigDecimal penaltyRate;

    @Column(name = "penalty_compouding", nullable = false)
    private Integer penaltyCompouding;

    @Column(name = "compensatory_rate_type", nullable = false, length = 3)
    private String compensatoryRateType;

    @Column(name = "compensatory_rate", nullable = false, precision = 16, scale = 12)
    private BigDecimal compensatoryRate;

    @Column(name = "compensatory_compouding", nullable = false)
    private Integer compensatoryCompouding;

}
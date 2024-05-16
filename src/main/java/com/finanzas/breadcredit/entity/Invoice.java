package com.finanzas.breadcredit.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @SequenceGenerator(name="invoice_idinvoice_seq",sequenceName="invoice_idinvoice_seq",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="invoice_idinvoice_seq")
    @Column(name = "invoice_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "amount", nullable = false, precision = 16, scale = 2)
    private BigDecimal amount;

}
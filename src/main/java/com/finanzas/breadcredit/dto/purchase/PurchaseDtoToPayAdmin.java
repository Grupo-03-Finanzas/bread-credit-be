package com.finanzas.breadcredit.dto.purchase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDtoToPayAdmin implements Serializable {
    private Long installmentId;
    private Long invoiceId;
    private String dni;
    private String fullName;
    private BigDecimal initialCost;
    private BigDecimal finalCost;
    private Long installmentNumber;
    private LocalDate dueDate;
    private Instant time;
}
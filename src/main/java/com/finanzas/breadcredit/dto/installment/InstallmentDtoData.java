package com.finanzas.breadcredit.dto.installment;

import com.finanzas.breadcredit.dto.payment.PaymentDtoData;
import com.finanzas.breadcredit.dto.purchase.PurchaseDtoData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link com.finanzas.breadcredit.entity.Installment}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstallmentDtoData implements Serializable {
    private Long id;
    private PurchaseDtoData purchase;
    private PaymentDtoData payment;
    private LocalDate dueDate;
    private BigDecimal amount;
}
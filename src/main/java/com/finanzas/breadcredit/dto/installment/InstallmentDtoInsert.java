package com.finanzas.breadcredit.dto.installment;

import com.finanzas.breadcredit.dto.payment.PaymentDtoInsert;
import com.finanzas.breadcredit.dto.purchase.PurchaseDtoInsert;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link com.finanzas.breadcredit.entity.Installment}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InstallmentDtoInsert implements Serializable {
    Integer id;
    PurchaseDtoInsert purchase;
    PaymentDtoInsert payment;
    LocalDate dueDate;
    BigDecimal amount;
}
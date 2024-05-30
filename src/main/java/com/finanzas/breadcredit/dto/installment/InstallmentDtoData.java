package com.finanzas.breadcredit.dto.installment;

import com.finanzas.breadcredit.dto.payment.PaymentDtoData;
import com.finanzas.breadcredit.dto.purchase.PurchaseDtoData;
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
public class InstallmentDtoData implements Serializable {
    Integer id;
    PurchaseDtoData purchase;
    PaymentDtoData payment;
    LocalDate dueDate;
    BigDecimal amount;
}
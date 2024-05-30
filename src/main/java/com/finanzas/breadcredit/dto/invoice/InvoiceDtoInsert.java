package com.finanzas.breadcredit.dto.invoice;

import com.finanzas.breadcredit.dto.payment.PaymentDtoInsert;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link com.finanzas.breadcredit.entity.Invoice}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InvoiceDtoInsert implements Serializable {
    Integer id;
    PaymentDtoInsert payment;
    LocalDate dueDate;
    BigDecimal amount;
}
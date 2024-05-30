package com.finanzas.breadcredit.dto.invoice;

import com.finanzas.breadcredit.dto.payment.PaymentDtoData;
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
public class InvoiceDtoData implements Serializable {
    Integer id;
    PaymentDtoData payment;
    LocalDate dueDate;
    BigDecimal amount;
}
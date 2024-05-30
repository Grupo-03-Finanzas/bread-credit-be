package com.finanzas.breadcredit.dto.payment;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for {@link com.finanzas.breadcredit.entity.Payment}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentDtoInsert implements Serializable {
    Integer id;
    BigDecimal amount;
    Instant time;
}
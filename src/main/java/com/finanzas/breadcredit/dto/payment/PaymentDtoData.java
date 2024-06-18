package com.finanzas.breadcredit.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for {@link com.finanzas.breadcredit.entity.Payment}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDtoData implements Serializable {
    private Long id;
    private BigDecimal amount;
    private Instant time;
}
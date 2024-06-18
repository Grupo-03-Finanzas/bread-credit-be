package com.finanzas.breadcredit.dto.installment;

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
    private PurchaseDto purchase;
    private PaymentDto payment;
    private LocalDate dueDate;
    private BigDecimal amount;

    /**
     * DTO for {@link com.finanzas.breadcredit.entity.Purchase}
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PurchaseDto implements Serializable {
        private Long id;
    }

    /**
     * DTO for {@link com.finanzas.breadcredit.entity.Payment}
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PaymentDto implements Serializable {
        private Long id;
    }
}
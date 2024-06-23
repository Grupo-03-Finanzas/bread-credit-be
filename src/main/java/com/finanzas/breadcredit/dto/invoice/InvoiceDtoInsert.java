package com.finanzas.breadcredit.dto.invoice;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;

/**
 * DTO for {@link com.finanzas.breadcredit.entity.Invoice}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDtoInsert implements Serializable {
    private Long id;
    private Set<PurchaseDto> purchases;
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
        private CreditaccountDto creditaccount;
        private BigDecimal initialCost;
        private BigDecimal finalCost;
        private Instant time;
        private Long installmentNumber;
        private String creditRateType;
        private BigDecimal creditRate;
        private Long creditCompouding;
        private String penaltyRateType;
        private BigDecimal penaltyRate;
        private Long penaltyCompouding;
        private String compensatoryRateType;
        private BigDecimal compensatoryRate;
        private Long compensatoryCompouding;

        /**
         * DTO for {@link com.finanzas.breadcredit.entity.Creditaccount}
         */
        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class CreditaccountDto implements Serializable {
            private Long id;
        }
    }
}
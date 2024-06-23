package com.finanzas.breadcredit.dto.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * DTO for {@link com.finanzas.breadcredit.entity.Invoice}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDtoCheckDueDate implements Serializable {
    private Long id;
    private Set<PurchaseDto> purchases;
    private PaymentDto payment;
    private LocalDate dueDate;

    /**
     * DTO for {@link com.finanzas.breadcredit.entity.Purchase}
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PurchaseDto implements Serializable {
        private Long id;
        private CreditaccountDto creditaccount;

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
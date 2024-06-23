package com.finanzas.breadcredit.dto.installment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.finanzas.breadcredit.entity.Installment}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstallmentDtoCheckDueDate implements Serializable {
    private Long id;
    private PurchaseDto purchase;
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
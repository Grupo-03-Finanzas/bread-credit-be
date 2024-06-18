package com.finanzas.breadcredit.dto.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

/**
 * DTO for {@link com.finanzas.breadcredit.entity.Invoice}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDtoData implements Serializable {
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
        private CreditaccountDto creditaccount;
        private BigDecimal initialCost;
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
            private CustomerDto customer;
            private AdminDto admin;
            private Boolean active = false;
            private BigDecimal maxCredit;
            private BigDecimal currentCredit;
            private Long billingDay;
            private String creditTypeOfRate;
            private BigDecimal creditRate;
            private Long creditCompounding;
            private String invoicePenaltyRateType;
            private BigDecimal invoicePenaltyRate;
            private Long invoicePenaltyCompouding;
            private String installmentPenaltyRateType;
            private BigDecimal installmentPenaltyRate;
            private Long installmentPenaltyCompouding;
            private String invoiceCompensatoryRateType;
            private BigDecimal invoiceCompensatoryRate;
            private Long invoiceCompensatoryCompouding;
            private String installmentCompensatoryRateType;
            private BigDecimal installmentCompensatoryRate;
            private Long installmentCompensatoryCompouding;

            /**
             * DTO for {@link com.finanzas.breadcredit.entity.Customer}
             */
            @Data
            @AllArgsConstructor
            @NoArgsConstructor
            public static class CustomerDto implements Serializable {
                private Long id;
                private UserDto user;
                private String address;

                /**
                 * DTO for {@link com.finanzas.breadcredit.entity.User}
                 */
                @Data
                @AllArgsConstructor
                @NoArgsConstructor
                public static class UserDto implements Serializable {
                    private Long id;
                    private String firstName;
                    private String lastName;
                    private String dni;
                    private String phone;
                    private String email;
                }
            }

            /**
             * DTO for {@link com.finanzas.breadcredit.entity.Admin}
             */
            @Data
            @AllArgsConstructor
            @NoArgsConstructor
            public static class AdminDto implements Serializable {
                private Long id;
                private UserDto user;
                private String businessName;
                private String businessType;

                /**
                 * DTO for {@link com.finanzas.breadcredit.entity.User}
                 */
                @Data
                @AllArgsConstructor
                @NoArgsConstructor
                public static class UserDto implements Serializable {
                    private Long id;
                    private String firstName;
                    private String lastName;
                    private String dni;
                    private String phone;
                    private String email;
                }
            }
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
        private BigDecimal amount;
        private Instant time;
    }
}
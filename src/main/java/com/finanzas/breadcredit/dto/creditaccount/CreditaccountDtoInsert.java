package com.finanzas.breadcredit.dto.creditaccount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.finanzas.breadcredit.entity.Creditaccount}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditaccountDtoInsert implements Serializable {
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
    }

    /**
     * DTO for {@link com.finanzas.breadcredit.entity.Admin}
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AdminDto implements Serializable {
        private Long id;
    }
}
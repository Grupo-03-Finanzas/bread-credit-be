package com.finanzas.breadcredit.dto.purchase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for {@link com.finanzas.breadcredit.entity.Purchase}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDtoInsert implements Serializable {
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
    }

}
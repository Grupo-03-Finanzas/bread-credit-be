package com.finanzas.breadcredit.dto.purchase;

import com.finanzas.breadcredit.dto.creditaccount.CreditaccountDtoInsert;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for {@link com.finanzas.breadcredit.entity.Purchase}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PurchaseDtoInsert implements Serializable {
    Integer id;
    CreditaccountDtoInsert creditaccount;
    BigDecimal initialCost;
    Instant time;
    Integer installmentNumber;
    String creditRateType;
    BigDecimal creditRate;
    Integer creditCompouding;
    String penaltyRateType;
    BigDecimal penaltyRate;
    Integer penaltyCompouding;
    String compensatoryRateType;
    BigDecimal compensatoryRate;
    Integer compensatoryCompouding;
}
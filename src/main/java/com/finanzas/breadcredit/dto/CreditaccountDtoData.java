package com.finanzas.breadcredit.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.finanzas.breadcredit.entity.Creditaccount}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreditaccountDtoData implements Serializable {
    Integer id;
    CustomerDtoData customer;
    AdminDtoData admin;
    Boolean active;
    BigDecimal maxCredit;
    BigDecimal currentCredit;
    String creditTypeOfRate;
    BigDecimal creditRate;
    Integer creditCompounding;
    String invoicePenaltyRateType;
    BigDecimal invoicePenaltyRate;
    Integer invoicePenaltyCompouding;
    String installmentPenaltyRateType;
    BigDecimal installmentPenaltyRate;
    Integer installmentPenaltyCompouding;
    String invoiceCompensatoryRateType;
    BigDecimal invoiceCompensatoryRate;
    Integer invoiceCompensatoryCompouding;
    String installmentCompensatoryRateType;
    BigDecimal installmentCompensatoryRate;
    Integer installmentCompensatoryCompouding;
}
package com.finanzas.breadcredit.dto.creditaccount;

import com.finanzas.breadcredit.dto.customer.CustomerDtoInsert;
import com.finanzas.breadcredit.dto.admin.AdminDtoInsert;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.finanzas.breadcredit.entity.Creditaccount}
 */
@Value
public class CreditaccountDtoInsert implements Serializable {
    Integer id;
    CustomerDtoInsert customer;
    AdminDtoInsert admin;
    Boolean active;
    BigDecimal maxCredit;
    BigDecimal currentCredit;
    Integer billingDay;
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
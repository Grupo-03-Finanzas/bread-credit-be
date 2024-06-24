package com.finanzas.breadcredit.dto.productpurchase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.finanzas.breadcredit.entity.ProductPurchase}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPurchaseDto implements Serializable {
    private PurchaseDtoPP purchase;
    private ProductDto product;
    private BigDecimal count;

    /**
     * DTO for {@link com.finanzas.breadcredit.entity.Purchase}
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PurchaseDtoPP implements Serializable {
        private Long id;
    }

    /**
     * DTO for {@link com.finanzas.breadcredit.entity.Product}
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductDto implements Serializable {
        private Long id;
    }
}
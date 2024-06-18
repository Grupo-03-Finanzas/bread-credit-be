package com.finanzas.breadcredit.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.finanzas.breadcredit.entity.Product}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDtoInsert implements Serializable {
    private Long id;
    private AdminDto admin;
    private String name;
    private BigDecimal price;

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
package com.finanzas.breadcredit.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.finanzas.breadcredit.entity.Product}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDtoData implements Serializable {
    Integer id;
    AdminDtoData admin;
    String name;
    BigDecimal price;
}
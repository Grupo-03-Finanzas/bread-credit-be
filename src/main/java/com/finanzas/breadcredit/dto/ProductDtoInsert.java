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
public class ProductDtoInsert implements Serializable {
    Integer id;
    AdminDtoInsert admin;
    String name;
    BigDecimal price;
}
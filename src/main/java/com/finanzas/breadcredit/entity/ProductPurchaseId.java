package com.finanzas.breadcredit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class ProductPurchaseId implements Serializable {

    @Column(name = "purchase_id", nullable = false)
    private Integer purchaseId;
    @Column(name = "product_id", nullable = false)
    private Integer productId;

}
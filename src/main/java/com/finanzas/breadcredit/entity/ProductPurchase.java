package com.finanzas.breadcredit.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "product_purchase")
public class ProductPurchase {
    @EmbeddedId
    private ProductPurchaseId id;

    @MapsId("purchaseId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "purchase_id", nullable = false)
    private Purchase purchase;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "count", nullable = false, precision = 16, scale = 2)
    private BigDecimal count;

}
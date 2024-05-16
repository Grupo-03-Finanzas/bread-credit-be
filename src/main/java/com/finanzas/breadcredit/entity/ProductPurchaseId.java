package com.finanzas.breadcredit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
public class ProductPurchaseId implements Serializable {
    private static final long serialVersionUID = -3939319235571618420L;
    @Column(name = "purchase_id", nullable = false)
    private Integer purchaseId;

    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductPurchaseId entity = (ProductPurchaseId) o;
        return Objects.equals(this.productId, entity.productId) &&
                Objects.equals(this.purchaseId, entity.purchaseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, purchaseId);
    }

}
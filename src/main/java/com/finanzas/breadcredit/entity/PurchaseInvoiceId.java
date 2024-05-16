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
public class PurchaseInvoiceId implements Serializable {
    private static final long serialVersionUID = -3436149364021661435L;
    @Column(name = "purchase_id", nullable = false)
    private Integer purchaseId;

    @Column(name = "invoice_id", nullable = false)
    private Integer invoiceId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PurchaseInvoiceId entity = (PurchaseInvoiceId) o;
        return Objects.equals(this.purchaseId, entity.purchaseId) &&
                Objects.equals(this.invoiceId, entity.invoiceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseId, invoiceId);
    }

}
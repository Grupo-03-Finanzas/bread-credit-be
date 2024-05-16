package com.finanzas.breadcredit.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "purchase_invoice")
public class PurchaseInvoice {
    @EmbeddedId
    private PurchaseInvoiceId id;

    @MapsId("purchaseId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "purchase_id", nullable = false)
    private Purchase purchase;

    @MapsId("invoiceId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;

}
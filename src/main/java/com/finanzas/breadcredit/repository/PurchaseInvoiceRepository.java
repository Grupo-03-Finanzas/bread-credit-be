package com.finanzas.breadcredit.repository;

import com.finanzas.breadcredit.entity.PurchaseInvoice;
import com.finanzas.breadcredit.entity.PurchaseInvoiceId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseInvoiceRepository extends JpaRepository<PurchaseInvoice, PurchaseInvoiceId> {
}
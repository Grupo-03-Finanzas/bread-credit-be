package com.finanzas.breadcredit.repository;

import com.finanzas.breadcredit.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
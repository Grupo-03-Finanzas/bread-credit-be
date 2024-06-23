package com.finanzas.breadcredit.repository;

import com.finanzas.breadcredit.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    @Query("SELECT DISTINCT i FROM Invoice i JOIN i.purchases p WHERE p.creditaccount.admin.id = :adminId AND i.payment IS NULL")
    List<Invoice> findInvoiceWithNoPaymentByAdminId(@Param("adminId") Long adminId);
    @Query("SELECT DISTINCT i FROM Invoice i JOIN i.purchases p WHERE p.creditaccount.customer.id = :customerId AND i.payment IS NULL")
    List<Invoice> findInvoiceWithNoPaymentByCustomerId(@Param("customerId") Long customerId);
}
package com.finanzas.breadcredit.repository;

import com.finanzas.breadcredit.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    @Query("SELECT p FROM Purchase p WHERE p.installmentNumber = 0 AND p.invoice IS NULL AND p.time >= :startOfMonth")
    List<Purchase> findPurchasesWithoutInstallmentsAndNoInvoice(@Param("startOfMonth") Instant startOfMonth);
}
package com.finanzas.breadcredit.repository;

import com.finanzas.breadcredit.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
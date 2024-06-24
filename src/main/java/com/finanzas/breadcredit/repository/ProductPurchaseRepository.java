package com.finanzas.breadcredit.repository;

import com.finanzas.breadcredit.entity.ProductPurchase;
import com.finanzas.breadcredit.entity.ProductPurchaseId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductPurchaseRepository extends JpaRepository<ProductPurchase, ProductPurchaseId> {
    List<ProductPurchase> findByPurchase_Id(Long id);
}
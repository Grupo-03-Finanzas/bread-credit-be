package com.finanzas.breadcredit.repository;

import com.finanzas.breadcredit.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
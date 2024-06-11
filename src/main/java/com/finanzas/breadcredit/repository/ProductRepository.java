package com.finanzas.breadcredit.repository;

import com.finanzas.breadcredit.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<List<Product>> findByAdmin_Id(Integer adminId);
}
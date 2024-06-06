package com.finanzas.breadcredit.repository;

import com.finanzas.breadcredit.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByUserDni(String userDni);
}
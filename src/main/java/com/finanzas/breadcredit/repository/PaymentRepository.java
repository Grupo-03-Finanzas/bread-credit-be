package com.finanzas.breadcredit.repository;

import com.finanzas.breadcredit.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
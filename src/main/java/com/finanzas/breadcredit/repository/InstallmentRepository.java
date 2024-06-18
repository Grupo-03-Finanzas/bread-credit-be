package com.finanzas.breadcredit.repository;

import com.finanzas.breadcredit.entity.Installment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstallmentRepository extends JpaRepository<Installment, Long> {
}
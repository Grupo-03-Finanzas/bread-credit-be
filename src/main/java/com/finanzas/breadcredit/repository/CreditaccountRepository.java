package com.finanzas.breadcredit.repository;

import com.finanzas.breadcredit.entity.Creditaccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditaccountRepository extends JpaRepository<Creditaccount, Integer> {
    Boolean existsByCustomerIdAndAdminId(Integer customerId, Integer adminId);
}
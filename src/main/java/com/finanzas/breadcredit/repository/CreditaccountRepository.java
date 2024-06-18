package com.finanzas.breadcredit.repository;

import com.finanzas.breadcredit.entity.Creditaccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CreditaccountRepository extends JpaRepository<Creditaccount, Long> {

    Boolean existsByCustomer_IdAndAdmin_Id(Long customerId, Long adminId);

    Optional<List<Creditaccount>> findByAdmin_Id(Long adminId);

    Optional<Creditaccount> findByAdmin_IdAndCustomer_Id(Long adminId, Long customerId);
    Optional<Creditaccount> findByCustomer_Id(Long customerId);
    Optional<Creditaccount> findByCustomer_User_Dni(String dni);
}
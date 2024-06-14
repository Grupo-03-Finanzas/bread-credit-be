package com.finanzas.breadcredit.repository;

import com.finanzas.breadcredit.entity.Creditaccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CreditaccountRepository extends JpaRepository<Creditaccount, Integer> {

    Boolean existsByCustomer_IdAndAdmin_Id(Integer customerId, Integer adminId);

    Optional<List<Creditaccount>> findByAdmin_Id(Integer adminId);

    Optional<Creditaccount> findByAdmin_IdAndCustomer_Id(Integer adminId, Integer customerId);
    Optional<Creditaccount> findByCustomer_Id(Integer customerId);
    Optional<Creditaccount> findByCustomer_User_Dni(String dni);
}
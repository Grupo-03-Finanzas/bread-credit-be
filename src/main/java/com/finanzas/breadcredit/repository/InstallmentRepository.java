package com.finanzas.breadcredit.repository;

import com.finanzas.breadcredit.entity.Installment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InstallmentRepository extends JpaRepository<Installment, Long> {
    @Query("SELECT i FROM Installment i WHERE i.payment IS NULL AND i.purchase.creditaccount.admin.id = :adminId")
    List<Installment> findInstallmentsWithNoPaymentByAdminId(@Param("adminId") Long adminId);
}
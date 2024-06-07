package com.finanzas.breadcredit.business;


import com.finanzas.breadcredit.entity.Installment;
import com.finanzas.breadcredit.repository.InstallmentRepository;
import com.finanzas.breadcredit.repository.PaymentRepository;
import com.finanzas.breadcredit.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InstallmentBusiness {

    @Autowired
    private InstallmentRepository installmentRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public Installment insertInstallment(Installment installment) throws Exception {
        installment.setId(null);
        if (!purchaseRepository.existsById(installment.getPurchase().getId())) {
            throw new Exception("Purchase not found");
        }
        if (!paymentRepository.existsById(installment.getPayment().getId())) {
            throw new Exception("Payment not found");
        }
        return installmentRepository.save(installment);
    }

    public Installment getInstallmentById(Integer id) throws Exception {
        return installmentRepository.findById(id).orElseThrow(() -> new Exception("Installment not found"));
    }

    public List<Installment> listInstallments() throws Exception {
        List<Installment> installmentList = installmentRepository.findAll();
        //if(installmentList.isEmpty()) {
        //    throw new Exception("No installments found");
        //}
        return installmentList;
    }

    @Transactional
    public Installment updateInstallment(Integer id, Installment installment) throws Exception {
        installment.setId(id);

        if (!installmentRepository.existsById(id)) {
            throw new Exception("Installment not found");
        }
        if (!purchaseRepository.existsById(installment.getPurchase().getId())) {
            throw new Exception("Purchase not found");
        }
        if (!paymentRepository.existsById(installment.getPayment().getId())) {
            throw new Exception("Payment not found");
        }

        return installmentRepository.save(installment);
    }

    @Transactional
    public void deleteInstallment(Integer id) throws Exception {
        Installment installment = installmentRepository.findById(id).orElseThrow(() -> new Exception("Installment not found"));
        installmentRepository.delete(installment);
    }
}
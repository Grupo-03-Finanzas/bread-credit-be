package com.finanzas.breadcredit.business;

import com.finanzas.breadcredit.entity.Installment;
import com.finanzas.breadcredit.exception.ResourceNotFoundException;
import com.finanzas.breadcredit.repository.InstallmentRepository;
import com.finanzas.breadcredit.repository.PaymentRepository;
import com.finanzas.breadcredit.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InstallmentBusiness {

    private final InstallmentRepository installmentRepository;
    private final PurchaseRepository purchaseRepository;
    private final PaymentRepository paymentRepository;

    @Autowired
    public InstallmentBusiness(InstallmentRepository installmentRepository, PurchaseRepository purchaseRepository, PaymentRepository paymentRepository) {
        this.installmentRepository = installmentRepository;
        this.purchaseRepository = purchaseRepository;
        this.paymentRepository = paymentRepository;
    }

    public Installment getInstallmentById(Long id) throws ResourceNotFoundException {
        return installmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Installment { id=" + id + "} not found"));
    }

    public List<Installment> listInstallments() throws ResourceNotFoundException {
        List<Installment> installmentList = installmentRepository.findAll();
        if (installmentList.isEmpty()) {
            throw new ResourceNotFoundException("Installment not found");
        }
        return installmentList;
    }

    public Installment insertInstallment(Installment installment) throws ResourceNotFoundException {
        installment.setId(null);
        installment.setPayment(null);
        if (!purchaseRepository.existsById(installment.getPurchase().getId())) {
            throw new ResourceNotFoundException("Purchase { id=" + installment.getPurchase().getId() + "} not found");
        }
        return installmentRepository.save(installment);
    }

    @Transactional
    public Installment updateInstallment(Long id, Installment installment) throws ResourceNotFoundException {
        installment.setId(id);

        if (!installmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Installment { id=" + id + "} not found");
        }
        if (!purchaseRepository.existsById(installment.getPurchase().getId())) {
            throw new ResourceNotFoundException("Purchase { id=" + installment.getPurchase().getId() + "} not found");
        }
        if (!paymentRepository.existsById(installment.getPayment().getId())) {
            throw new ResourceNotFoundException("Payment { id=" + installment.getPayment().getId() + "} not found");
        }

        return installmentRepository.save(installment);
    }

    @Transactional
    public void deleteInstallment(Long id) throws ResourceNotFoundException {
        Installment installment = installmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Installment { id=" + id + "} not found"));
        installmentRepository.delete(installment);
    }
}
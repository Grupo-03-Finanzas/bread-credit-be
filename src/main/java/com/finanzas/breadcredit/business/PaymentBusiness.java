package com.finanzas.breadcredit.business;

import com.finanzas.breadcredit.entity.Installment;
import com.finanzas.breadcredit.entity.Invoice;
import com.finanzas.breadcredit.entity.Payment;
import com.finanzas.breadcredit.exception.ResourceNotFoundException;
import com.finanzas.breadcredit.repository.InstallmentRepository;
import com.finanzas.breadcredit.repository.InvoiceRepository;
import com.finanzas.breadcredit.repository.PaymentRepository;
import com.finanzas.breadcredit.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class PaymentBusiness {


    private final PaymentRepository paymentRepository;
    private final InvoiceRepository invoiceRepository;
    private final InstallmentRepository installmentRepository;

    @Autowired
    public PaymentBusiness(PaymentRepository paymentRepository, InstallmentRepository installmentRepository, InvoiceRepository invoiceRepository) {
        this.paymentRepository = paymentRepository;
        this.invoiceRepository = invoiceRepository;
        this.installmentRepository = installmentRepository;
    }
    public Payment getPaymentById(Long id) throws ResourceNotFoundException {
        return paymentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Payment { id=" + id + " } not found"));
    }

    public List<Payment> listPayments() throws ResourceNotFoundException {
        List<Payment> paymentList = paymentRepository.findAll();
        if (paymentList.isEmpty()) {
            throw new ResourceNotFoundException("No payments found");
        }
        return paymentList;
    }

    public Payment insertPayment(Payment payment) {
        payment.setId(null);
        payment.setTime(Instant.now());

        Payment savedPayment = paymentRepository.save(payment);

        if (payment.getInvoice()!=null) {
            Invoice invoice = this.invoiceRepository.findById(payment.getInvoice().getId()).orElse(null);
            invoice.setPayment(savedPayment);
            invoiceRepository.save(invoice);
        }
        else {
            Installment installment = this.installmentRepository.findById(payment.getInstallment().getId()).orElse(null);
            installment.setPayment(savedPayment);
            installmentRepository.save(installment);
        }

        return savedPayment;
    }


    @Transactional
    public Payment updatePayment(Long id, Payment payment) throws ResourceNotFoundException {
        payment.setId(id);
        if (!paymentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Payment { id=" + id + " } not found");
        }
        return paymentRepository.save(payment);
    }

    @Transactional
    public void deletePayment(Long id) throws ResourceNotFoundException {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Payment { id=" + id + " } not found"));
        paymentRepository.delete(payment);
    }
}

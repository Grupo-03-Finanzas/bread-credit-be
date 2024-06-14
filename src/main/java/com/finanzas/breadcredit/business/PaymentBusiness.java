package com.finanzas.breadcredit.business;

import com.finanzas.breadcredit.entity.Payment;
import com.finanzas.breadcredit.exception.ResourceNotFoundException;
import com.finanzas.breadcredit.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class PaymentBusiness {


    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentBusiness(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
    public Payment getPaymentById(Integer id) throws ResourceNotFoundException {
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
        return paymentRepository.save(payment);
    }


    @Transactional
    public Payment updatePayment(Integer id, Payment payment) throws ResourceNotFoundException {
        payment.setId(id);
        if (!paymentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Payment { id=" + id + " } not found");
        }
        return paymentRepository.save(payment);
    }

    @Transactional
    public void deletePayment(Integer id) throws ResourceNotFoundException {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Payment { id=" + id + " } not found"));
        paymentRepository.delete(payment);
    }
}

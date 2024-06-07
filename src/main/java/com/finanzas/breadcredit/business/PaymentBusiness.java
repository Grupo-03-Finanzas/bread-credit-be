package com.finanzas.breadcredit.business;

import com.finanzas.breadcredit.entity.Payment;
import com.finanzas.breadcredit.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class PaymentBusiness {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment insertPayment(Payment payment) throws Exception {
        payment.setId(null);
        payment.setTime(Instant.now());
        return paymentRepository.save(payment);
    }

    public Payment getPaymentById(Integer id) throws Exception {
        return paymentRepository.findById(id).orElseThrow(() -> new Exception("Payment not found"));
    }

    public List<Payment> listPayments() throws Exception {
        List<Payment> paymentList = paymentRepository.findAll();

        //if(paymentList.isEmpty()) {
        //    throw new Exception("No payments found");
        //}

        return paymentList;
    }

    @Transactional
    public Payment updatePayment(Integer id, Payment payment) throws Exception {
        payment.setId(id);

        if (!paymentRepository.existsById(id)) {
            throw new Exception("Payment not found");
        }

        return paymentRepository.save(payment);
    }

    @Transactional
    public void deletePayment(Integer id) throws Exception {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new Exception("Payment not found"));
        paymentRepository.delete(payment);
    }
}

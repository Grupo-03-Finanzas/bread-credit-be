package com.finanzas.breadcredit.controller;

import com.finanzas.breadcredit.business.PaymentBusiness;
import com.finanzas.breadcredit.dto.payment.PaymentDtoData;
import com.finanzas.breadcredit.dto.payment.PaymentDtoInsert;
import com.finanzas.breadcredit.entity.Payment;
import com.finanzas.breadcredit.exception.ResourceNotFoundException;
import com.finanzas.breadcredit.utility.UtilityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentBusiness paymentBusiness;

    @Autowired
    public PaymentController(PaymentBusiness paymentBusiness) {
        this.paymentBusiness = paymentBusiness;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PaymentDtoData getPaymentById(@PathVariable Long id) throws ResourceNotFoundException {
        Payment payment = paymentBusiness.getPaymentById(id);
        return UtilityDto.convertTo(payment, PaymentDtoData.class);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<PaymentDtoData> listPayments() {
        List<Payment> paymentList = paymentBusiness.listPayments();
        return UtilityDto.convertToList(paymentList, PaymentDtoData.class);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentDtoData insertPayment(@RequestBody PaymentDtoInsert paymentDtoInsert) {
        Payment payment = UtilityDto.convertTo(paymentDtoInsert, Payment.class);
        payment = paymentBusiness.insertPayment(payment);
        return UtilityDto.convertTo(payment, PaymentDtoData.class);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PaymentDtoData updatePayment(@PathVariable Long id, @RequestBody PaymentDtoInsert paymentDtoInsert) throws ResourceNotFoundException {
        Payment payment = UtilityDto.convertTo(paymentDtoInsert, Payment.class);
        payment = paymentBusiness.updatePayment(id, payment);
        return UtilityDto.convertTo(payment, PaymentDtoData.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Void deletePayment(@PathVariable Long id) throws ResourceNotFoundException {
        paymentBusiness.deletePayment(id);
        return null;
    }
}

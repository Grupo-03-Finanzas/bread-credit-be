package com.finanzas.breadcredit.controller;


import com.finanzas.breadcredit.business.PaymentBusiness;
import com.finanzas.breadcredit.dto.payment.PaymentDtoData;
import com.finanzas.breadcredit.dto.payment.PaymentDtoInsert;
import com.finanzas.breadcredit.entity.Payment;
import com.finanzas.breadcredit.utility.UtilityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentBusiness paymentBusiness;

    @PostMapping("")
    public ResponseEntity<PaymentDtoData> insertPayment(@RequestBody PaymentDtoInsert paymentDtoInsert) {
        Payment payment = UtilityDto.convertTo(paymentDtoInsert, Payment.class);
        try {
            payment = paymentBusiness.insertPayment(payment);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        PaymentDtoData paymentDtoData = UtilityDto.convertTo(payment, PaymentDtoData.class);
        return new ResponseEntity<>(paymentDtoData, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDtoData> getPaymentById(@PathVariable Integer id) {
        Payment payment;
        try {
            payment = paymentBusiness.getPaymentById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        PaymentDtoData paymentDtoData = UtilityDto.convertTo(payment, PaymentDtoData.class);
        return new ResponseEntity<>(paymentDtoData, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<PaymentDtoData>> listPayments() {
        List<Payment> paymentList;
        try {
            paymentList = paymentBusiness.listPayments();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        List<PaymentDtoData> paymentDtoDataList = UtilityDto.convertToList(paymentList, PaymentDtoData.class);
        return new ResponseEntity<>(paymentDtoDataList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDtoData> updatePayment(@PathVariable Integer id, @RequestBody PaymentDtoInsert paymentDtoInsert) {
        Payment payment = UtilityDto.convertTo(paymentDtoInsert, Payment.class);
        try {
            payment = paymentBusiness.updatePayment(id, payment);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        PaymentDtoData paymentDtoData = UtilityDto.convertTo(payment, PaymentDtoData.class);
        return new ResponseEntity<>(paymentDtoData, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Integer id) {
        try {
            paymentBusiness.deletePayment(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
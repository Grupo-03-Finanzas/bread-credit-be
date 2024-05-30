package com.finanzas.breadcredit.controller;


import com.finanzas.breadcredit.business.InstallmentBusiness;
import com.finanzas.breadcredit.dto.installment.InstallmentDtoData;
import com.finanzas.breadcredit.dto.installment.InstallmentDtoInsert;
import com.finanzas.breadcredit.entity.Installment;
import com.finanzas.breadcredit.utility.UtilityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/installment")
public class InstallmentController {
    @Autowired
    private InstallmentBusiness installmentBusiness;

    @PostMapping("")
    public ResponseEntity<InstallmentDtoData> createInstallment(@RequestBody InstallmentDtoInsert installmentDtoInsert) {
        Installment installment = UtilityDto.convertTo(installmentDtoInsert, Installment.class);
        try {
            installment = installmentBusiness.insertInstallment(installment);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        InstallmentDtoData installmentDtoData = UtilityDto.convertTo(installment, InstallmentDtoData.class);
        return new ResponseEntity<>(installmentDtoData, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstallmentDtoData> getInstallmentById(@PathVariable Integer id) {
        Installment installment;
        try {
            installment = installmentBusiness.getInstallmentById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        InstallmentDtoData installmentDtoData = UtilityDto.convertTo(installment, InstallmentDtoData.class);
        return new ResponseEntity<>(installmentDtoData, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<InstallmentDtoData>> listInstallments() {
        List<Installment> installmentList;
        try {
            installmentList = installmentBusiness.listInstallments();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        List<InstallmentDtoData> installmentDtoDataList = UtilityDto.convertToList(installmentList, InstallmentDtoData.class);
        return new ResponseEntity<>(installmentDtoDataList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstallmentDtoData> updateInstallment(@PathVariable Integer id, @RequestBody InstallmentDtoInsert installmentDtoInsert) {
        Installment installment = UtilityDto.convertTo(installmentDtoInsert, Installment.class);
        try {
            installment = installmentBusiness.updateInstallment(id, installment);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        InstallmentDtoData installmentDtoData = UtilityDto.convertTo(installment, InstallmentDtoData.class);
        return new ResponseEntity<>(installmentDtoData, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstallment(@PathVariable Integer id) {
        try {
            installmentBusiness.deleteInstallment(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
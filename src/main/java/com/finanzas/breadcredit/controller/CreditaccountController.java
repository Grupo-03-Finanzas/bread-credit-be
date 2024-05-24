package com.finanzas.breadcredit.controller;

import com.finanzas.breadcredit.business.CreditaccountBusiness;
import com.finanzas.breadcredit.dto.creditaccount.CreditaccountDtoData;
import com.finanzas.breadcredit.dto.creditaccount.CreditaccountDtoInsert;
import com.finanzas.breadcredit.entity.Creditaccount;
import com.finanzas.breadcredit.utility.UtilityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/creditaccount")
public class CreditaccountController {

    @Autowired
    private CreditaccountBusiness creditaccountBusiness;

    @PostMapping("")
    public ResponseEntity<CreditaccountDtoData> insertCreditaccount(@RequestBody CreditaccountDtoInsert creditaccountDtoInsert) {
        Creditaccount creditaccount = UtilityDto.convertTo(creditaccountDtoInsert, Creditaccount.class);

        try {
            creditaccount = creditaccountBusiness.insertCreditaccount(creditaccount);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,e.getMessage());
        }

        CreditaccountDtoData creditaccountDtoData = UtilityDto.convertTo(creditaccount, CreditaccountDtoData.class);
        return new ResponseEntity<>(creditaccountDtoData, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditaccountDtoData> getCreditaccountById(@PathVariable Integer id) {
        Creditaccount creditaccount;

        try {
            creditaccount = creditaccountBusiness.getCreditaccountById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

        CreditaccountDtoData creditaccountDtoData = UtilityDto.convertTo(creditaccount, CreditaccountDtoData.class);
        return new ResponseEntity<>(creditaccountDtoData, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<CreditaccountDtoData>> listCreditaccounts() {
        List<Creditaccount> listCreditaccounts;

        try {
             listCreditaccounts = creditaccountBusiness.listCreditaccounts();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

        List<CreditaccountDtoData> creditaccountDtoDataList = UtilityDto.convertToList(listCreditaccounts, CreditaccountDtoData.class);
        return new ResponseEntity<>(creditaccountDtoDataList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditaccountDtoData> updateCreditaccount(@PathVariable Integer id, @RequestBody CreditaccountDtoInsert creditaccountDtoInsert) {
        Creditaccount creditaccount = UtilityDto.convertTo(creditaccountDtoInsert, Creditaccount.class);

        try {
            creditaccount = creditaccountBusiness.updateCreditaccount(id, creditaccount);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        CreditaccountDtoData creditaccountDtoData = UtilityDto.convertTo(creditaccount, CreditaccountDtoData.class);
        return new ResponseEntity<>(creditaccountDtoData, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            creditaccountBusiness.deleteCreditaccount(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

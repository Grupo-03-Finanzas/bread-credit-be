package com.finanzas.breadcredit.controller;

import com.finanzas.breadcredit.business.CreditaccountBusiness;
import com.finanzas.breadcredit.dto.creditaccount.CreditaccountDtoData;
import com.finanzas.breadcredit.dto.creditaccount.CreditaccountDtoInsert;
import com.finanzas.breadcredit.entity.Creditaccount;
import com.finanzas.breadcredit.exception.ResourceConflictException;
import com.finanzas.breadcredit.exception.ResourceNotFoundException;
import com.finanzas.breadcredit.utility.UtilityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/creditaccount")
public class CreditaccountController {

    private final CreditaccountBusiness creditaccountBusiness;

    @Autowired
    public CreditaccountController(CreditaccountBusiness creditaccountBusiness) {
        this.creditaccountBusiness = creditaccountBusiness;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CreditaccountDtoData getCreditaccountById(@PathVariable Long id) throws ResourceNotFoundException {
        Creditaccount creditaccount = creditaccountBusiness.getCreditaccountById(id);
        return UtilityDto.convertTo(creditaccount, CreditaccountDtoData.class);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<CreditaccountDtoData> listCreditaccounts() throws ResourceNotFoundException {
        List<Creditaccount> listCreditaccounts = creditaccountBusiness.listCreditaccounts();
        return UtilityDto.convertToList(listCreditaccounts, CreditaccountDtoData.class);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditaccountDtoData insertCreditaccount(@RequestBody CreditaccountDtoInsert creditaccountDtoInsert) throws ResourceNotFoundException, ResourceConflictException {
        Creditaccount creditaccount = UtilityDto.convertTo(creditaccountDtoInsert, Creditaccount.class);
        creditaccount = creditaccountBusiness.insertCreditaccount(creditaccount);
        return UtilityDto.convertTo(creditaccount, CreditaccountDtoData.class);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CreditaccountDtoData updateCreditaccount(@PathVariable Long id, @RequestBody CreditaccountDtoInsert creditaccountDtoInsert) throws ResourceNotFoundException, ResourceConflictException {
        Creditaccount creditaccount = UtilityDto.convertTo(creditaccountDtoInsert, Creditaccount.class);
        creditaccount = creditaccountBusiness.updateCreditaccount(id, creditaccount);
        return UtilityDto.convertTo(creditaccount, CreditaccountDtoData.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Void delete(@PathVariable Long id) throws ResourceNotFoundException {
        creditaccountBusiness.deleteCreditaccount(id);
        return null;
    }

    @GetMapping("/admin/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<CreditaccountDtoData> getCreditaccountsByAdminId(@PathVariable Long id) throws ResourceNotFoundException {
        List<Creditaccount> listCreditaccounts = creditaccountBusiness.getCreditAccountsByAdminId(id);
        return UtilityDto.convertToList(listCreditaccounts, CreditaccountDtoData.class);
    }

    @GetMapping("/admin/{adminId}/customer/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public CreditaccountDtoData getCreditaccountByAdminIdANDCustomerId(@PathVariable Long adminId, @PathVariable Long customerId) throws ResourceNotFoundException {
        Creditaccount creditaccount = creditaccountBusiness.getCreditaccountByAdmin_IdANDCustomer_Id(adminId, customerId);
        return UtilityDto.convertTo(creditaccount, CreditaccountDtoData.class);
    }

    @GetMapping("/customer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CreditaccountDtoData getCreditaccountByAdminId(@PathVariable Long id) throws ResourceNotFoundException {
        Creditaccount creditaccount = creditaccountBusiness.getCreditaccountByCustomer_Id(id);
        return UtilityDto.convertTo(creditaccount, CreditaccountDtoData.class);
    }

    @GetMapping("/customer/dni/{dni}")
    @ResponseStatus(HttpStatus.OK)
    public CreditaccountDtoData getCreditaccountByCustomerDni(@PathVariable String dni) throws ResourceNotFoundException {
        Creditaccount creditaccount = creditaccountBusiness.getCreditaccountByCustomerDni(dni);
        return UtilityDto.convertTo(creditaccount, CreditaccountDtoData.class);
    }
}

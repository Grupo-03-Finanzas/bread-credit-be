package com.finanzas.breadcredit.controller;

import com.finanzas.breadcredit.business.InstallmentBusiness;
import com.finanzas.breadcredit.dto.installment.InstallmentDtoData;
import com.finanzas.breadcredit.dto.installment.InstallmentDtoInsert;
import com.finanzas.breadcredit.entity.Installment;
import com.finanzas.breadcredit.exception.ResourceNotFoundException;
import com.finanzas.breadcredit.utility.UtilityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/installment")
public class InstallmentController {

    private final InstallmentBusiness installmentBusiness;

    @Autowired
    public InstallmentController(InstallmentBusiness installmentBusiness) {
        this.installmentBusiness = installmentBusiness;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InstallmentDtoData getInstallmentById(@PathVariable Integer id) throws ResourceNotFoundException {
        Installment installment = installmentBusiness.getInstallmentById(id);
        return UtilityDto.convertTo(installment, InstallmentDtoData.class);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<InstallmentDtoData> listInstallments() throws ResourceNotFoundException {
        List<Installment> installmentList = installmentBusiness.listInstallments();
        return UtilityDto.convertToList(installmentList, InstallmentDtoData.class);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public InstallmentDtoData insertInstallment(@RequestBody InstallmentDtoInsert installmentDtoInsert) throws ResourceNotFoundException {
        Installment installment = UtilityDto.convertTo(installmentDtoInsert, Installment.class);
        installment = installmentBusiness.insertInstallment(installment);
        return UtilityDto.convertTo(installment, InstallmentDtoData.class);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InstallmentDtoData updateInstallment(@PathVariable Integer id, @RequestBody InstallmentDtoInsert installmentDtoInsert) throws ResourceNotFoundException {
        Installment installment = UtilityDto.convertTo(installmentDtoInsert, Installment.class);
        installment = installmentBusiness.updateInstallment(id, installment);
        return UtilityDto.convertTo(installment, InstallmentDtoData.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Void deleteInstallment(@PathVariable Integer id) throws ResourceNotFoundException {
        installmentBusiness.deleteInstallment(id);
        return null;
    }
}

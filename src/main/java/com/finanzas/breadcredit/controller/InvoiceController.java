package com.finanzas.breadcredit.controller;

import com.finanzas.breadcredit.business.InvoiceBusiness;
import com.finanzas.breadcredit.dto.invoice.InvoiceDtoData;
import com.finanzas.breadcredit.dto.invoice.InvoiceDtoInsert;
import com.finanzas.breadcredit.entity.Invoice;
import com.finanzas.breadcredit.exception.ResourceNotFoundException;
import com.finanzas.breadcredit.utility.UtilityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    private final InvoiceBusiness invoiceBusiness;

    @Autowired
    public InvoiceController(InvoiceBusiness invoiceBusiness) {
        this.invoiceBusiness = invoiceBusiness;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InvoiceDtoData getInvoiceById(@PathVariable Integer id) throws ResourceNotFoundException {
        Invoice invoice = invoiceBusiness.getInvoiceById(id);
        return UtilityDto.convertTo(invoice, InvoiceDtoData.class);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceDtoData> listInvoices() throws ResourceNotFoundException {
        List<Invoice> invoiceList = invoiceBusiness.listInvoices();
        return UtilityDto.convertToList(invoiceList, InvoiceDtoData.class);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceDtoData insertInvoice(@RequestBody InvoiceDtoInsert invoiceDtoInsert) {
        Invoice invoice = UtilityDto.convertTo(invoiceDtoInsert, Invoice.class);
        invoice = invoiceBusiness.insertInvoice(invoice);
        return UtilityDto.convertTo(invoice, InvoiceDtoData.class);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InvoiceDtoData updateInvoice(@PathVariable Integer id, @RequestBody InvoiceDtoInsert invoiceDtoInsert) throws ResourceNotFoundException {
        Invoice invoice = UtilityDto.convertTo(invoiceDtoInsert, Invoice.class);
        invoice = invoiceBusiness.updateInvoice(id, invoice);
        return UtilityDto.convertTo(invoice, InvoiceDtoData.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Void deleteInvoice(@PathVariable Integer id) throws ResourceNotFoundException {
        invoiceBusiness.deleteInvoice(id);
        return null;
    }
}

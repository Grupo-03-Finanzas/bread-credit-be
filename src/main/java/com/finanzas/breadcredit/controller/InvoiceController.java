package com.finanzas.breadcredit.controller;

import com.finanzas.breadcredit.business.InvoiceBusiness;
import com.finanzas.breadcredit.dto.invoice.InvoiceDtoData;
import com.finanzas.breadcredit.dto.invoice.InvoiceDtoInsert;
import com.finanzas.breadcredit.entity.Invoice;
import com.finanzas.breadcredit.utility.UtilityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
    private InvoiceBusiness invoiceBusiness;

    @PostMapping("")
    public ResponseEntity<InvoiceDtoData> createInvoice(@RequestBody InvoiceDtoInsert invoiceDtoInsert) {
        Invoice invoice = UtilityDto.convertTo(invoiceDtoInsert, Invoice.class);
        try {
            invoice = invoiceBusiness.insertInvoice(invoice);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        InvoiceDtoData invoiceDtoData = UtilityDto.convertTo(invoice, InvoiceDtoData.class);
        return new ResponseEntity<>(invoiceDtoData, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDtoData> getInvoiceById(@PathVariable Integer id) {
        Invoice invoice;
        try {
            invoice = invoiceBusiness.getInvoiceById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        InvoiceDtoData invoiceDtoData = UtilityDto.convertTo(invoice, InvoiceDtoData.class);
        return new ResponseEntity<>(invoiceDtoData, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<InvoiceDtoData>> listInvoices() {
        List<Invoice> invoiceList;
        try {
            invoiceList = invoiceBusiness.listInvoices();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        List<InvoiceDtoData> invoiceDtoDataList = UtilityDto.convertToList(invoiceList, InvoiceDtoData.class);
        return new ResponseEntity<>(invoiceDtoDataList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceDtoData> updateInvoice(@PathVariable Integer id, @RequestBody InvoiceDtoInsert invoiceDtoInsert) {
        Invoice invoice = UtilityDto.convertTo(invoiceDtoInsert, Invoice.class);
        try {
            invoice = invoiceBusiness.updateInvoice(id, invoice);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        InvoiceDtoData invoiceDtoData = UtilityDto.convertTo(invoice, InvoiceDtoData.class);
        return new ResponseEntity<>(invoiceDtoData, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Integer id) {
        try {
            invoiceBusiness.deleteInvoice(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
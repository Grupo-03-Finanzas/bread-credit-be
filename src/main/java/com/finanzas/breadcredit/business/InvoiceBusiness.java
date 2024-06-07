package com.finanzas.breadcredit.business;


import com.finanzas.breadcredit.entity.Invoice;
import com.finanzas.breadcredit.repository.InvoiceRepository;
import com.finanzas.breadcredit.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InvoiceBusiness {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public Invoice insertInvoice(Invoice invoice) throws Exception {
        invoice.setId(null);
        invoice.setPayment(null);
        return invoiceRepository.save(invoice);
    }

    public Invoice getInvoiceById(Integer id) throws Exception {
        return invoiceRepository.findById(id).orElseThrow(() -> new Exception("Invoice not found"));
    }

    public List<Invoice> listInvoices() throws Exception {
        List<Invoice> invoiceList = invoiceRepository.findAll();

        //if (invoiceList.isEmpty()) {
        //    throw new Exception("No invoices found");
        //}

        return invoiceList;
    }

    @Transactional
    public Invoice updateInvoice(Integer id, Invoice invoice) throws Exception {
        invoice.setId(id);

        if (!invoiceRepository.existsById(id)) {
            throw new Exception("Invoice not found");
        }

        return invoiceRepository.save(invoice);
    }

    @Transactional
    public void deleteInvoice(Integer id) throws Exception {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new Exception("Invoice not found"));
        invoiceRepository.delete(invoice);
    }
}
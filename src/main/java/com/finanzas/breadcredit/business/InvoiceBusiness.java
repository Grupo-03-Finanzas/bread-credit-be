package com.finanzas.breadcredit.business;

import com.finanzas.breadcredit.entity.Invoice;
import com.finanzas.breadcredit.exception.ResourceNotFoundException;
import com.finanzas.breadcredit.repository.InvoiceRepository;
import com.finanzas.breadcredit.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InvoiceBusiness {

    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceBusiness(InvoiceRepository invoiceRepository, PaymentRepository paymentRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public Invoice getInvoiceById(Integer id) throws ResourceNotFoundException {
        return invoiceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invoice { id=" + id + " } not found"));
    }

    public List<Invoice> listInvoices() throws ResourceNotFoundException {
        List<Invoice> invoiceList = invoiceRepository.findAll();
        if (invoiceList.isEmpty()) {
            throw new ResourceNotFoundException("Invoices not found");
        }
        return invoiceList;
    }

    public Invoice insertInvoice(Invoice invoice) {
        invoice.setId(null);
        invoice.setPayment(null);
        return invoiceRepository.save(invoice);
    }

    @Transactional
    public Invoice updateInvoice(Integer id, Invoice invoice) throws ResourceNotFoundException {
        invoice.setId(id);
        if (!invoiceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Invoice { id=" + id + " } not found");
        }
        return invoiceRepository.save(invoice);
    }

    @Transactional
    public void deleteInvoice(Integer id) throws ResourceNotFoundException {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invoice { id=" + id + " } not found"));
        invoiceRepository.delete(invoice);
    }
}

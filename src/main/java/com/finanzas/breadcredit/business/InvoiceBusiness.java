package com.finanzas.breadcredit.business;


import com.finanzas.breadcredit.dto.invoice.InvoiceDtoInsert;
import com.finanzas.breadcredit.entity.Invoice;
import com.finanzas.breadcredit.entity.Purchase;
import com.finanzas.breadcredit.exception.ResourceNotFoundException;
import com.finanzas.breadcredit.repository.InvoiceRepository;
import com.finanzas.breadcredit.repository.PurchaseRepository;
import com.finanzas.breadcredit.utility.UtilityDto;
import com.finanzas.breadcredit.utility.UtilityFinance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

@Service
public class InvoiceBusiness {

    private final InvoiceRepository invoiceRepository;
    private final PurchaseRepository purchaseRepository;

    @Autowired
    public InvoiceBusiness(InvoiceRepository invoiceRepository, PurchaseRepository purchaseRepository) {
        this.invoiceRepository = invoiceRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public Invoice getInvoiceById(Long id) throws ResourceNotFoundException {
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
    public Invoice updateInvoice(Long id, Invoice invoice) throws ResourceNotFoundException {
        invoice.setId(id);
        if (!invoiceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Invoice { id=" + id + " } not found");
        }
        return invoiceRepository.save(invoice);
    }

    @Transactional
    public void deleteInvoice(Long id) throws ResourceNotFoundException {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invoice { id=" + id + " } not found"));
        invoiceRepository.delete(invoice);
    }

    public List<Invoice> generateInvoices() {
        ZonedDateTime startOfMonthZoned = LocalDate.now().minusMonths(1).withDayOfMonth(1).atStartOfDay(ZoneId.systemDefault());
        List<Purchase> purchases = purchaseRepository.findPurchasesWithoutInstallmentsAndNoInvoice(startOfMonthZoned.toInstant());
        List<InvoiceDtoInsert.PurchaseDto> purchaseDtoInserts = UtilityDto.convertToList(purchases, InvoiceDtoInsert.PurchaseDto.class);

        Map<Long, List<InvoiceDtoInsert.PurchaseDto>> groupedPurchases = new HashMap<>();
        List<Invoice> invoices = new ArrayList<>();

        for (InvoiceDtoInsert.PurchaseDto purchase : purchaseDtoInserts) {
            Long creditAccountId = purchase.getCreditaccount().getId();
            groupedPurchases.computeIfAbsent(creditAccountId, k -> new ArrayList<>()).add(purchase);
        }

        for (Map.Entry<Long, List<InvoiceDtoInsert.PurchaseDto>> entry : groupedPurchases.entrySet()) {
            Set<Purchase> purchaseSet = new HashSet<>(UtilityDto.convertToList(entry.getValue(), Purchase.class));

            for (Purchase purchase : purchaseSet) {
                BigDecimal presentValue = purchase.getInitialCost();
                String creditType = purchase.getCreditRateType();
                BigDecimal rate = purchase.getCreditRate();
                Long compounding = purchase.getCreditCompouding();
                Date purchaseDate = Date.from(purchase.getTime());
                Date currentDate = new Date();
                BigDecimal finalCost = UtilityFinance.calcFutureValue(presentValue, creditType, rate, purchaseDate, currentDate, compounding);
                purchase.setFinalCost(finalCost);
                purchaseRepository.save(purchase);
            }
            BigDecimal totalAmount = purchaseSet.stream().map(Purchase::getFinalCost).reduce(BigDecimal.ZERO, BigDecimal::add);
            Invoice invoice = new Invoice();
            invoice.setPurchases(purchaseSet);
            invoice.setAmount(totalAmount);
            invoice.setDueDate(LocalDate.now().plusDays(9)); // debido a que se ejecuta el día 1 (+ 9 = 10)
            invoice.setTime(Instant.now());
            invoices.add(invoice);
            Invoice invoiceToPurchase = insertInvoice(invoice);
            for (Purchase purchase : purchaseSet) {
                purchase.setInvoice(invoiceToPurchase);
                purchaseRepository.save(purchase);
            }
        }
        return invoices;
    }
    @Scheduled(cron = "0 0 0 1 * ?")
    public void generateInvoicesScheduled() {
        generateInvoices();
    }

}

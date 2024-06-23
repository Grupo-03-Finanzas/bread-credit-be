package com.finanzas.breadcredit.business;

import com.finanzas.breadcredit.dto.purchase.PurchaseDtoToPayAdmin;
import com.finanzas.breadcredit.dto.purchase.PurchaseDtoToPayCustomer;
import com.finanzas.breadcredit.entity.Creditaccount;
import com.finanzas.breadcredit.entity.Installment;
import com.finanzas.breadcredit.entity.Invoice;
import com.finanzas.breadcredit.entity.Purchase;
import com.finanzas.breadcredit.exception.ResourceNotFoundException;
import com.finanzas.breadcredit.repository.CreditaccountRepository;
import com.finanzas.breadcredit.repository.InstallmentRepository;
import com.finanzas.breadcredit.repository.InvoiceRepository;
import com.finanzas.breadcredit.repository.PurchaseRepository;
import com.finanzas.breadcredit.utility.UtilityFinance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PurchaseBusiness {


    private final PurchaseRepository purchaseRepository;
    private final CreditaccountRepository creditaccountRepository;
    private final InvoiceRepository invoiceRepository;
    private final InstallmentRepository installmentRepository;

    @Autowired
    public PurchaseBusiness(PurchaseRepository purchaseRepository, CreditaccountRepository creditaccountRepository, InvoiceRepository invoiceRepository, InstallmentRepository installmentRepository) {
        this.purchaseRepository = purchaseRepository;
        this.creditaccountRepository = creditaccountRepository;
        this.invoiceRepository = invoiceRepository;
        this.installmentRepository = installmentRepository;

    }

    public Purchase insertPurchase(Purchase purchase) {
        purchase.setId(null);
        purchase.setTime(Instant.now());
        return purchaseRepository.save(purchase);
    }

    public Purchase getPurchaseById(Long id) throws ResourceNotFoundException {
        return purchaseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Purchase { id=" + id + " } not found"));
    }

    public List<Purchase> listPurchases() throws ResourceNotFoundException {
        List<Purchase> purchaseList = purchaseRepository.findAll();
        if (purchaseList.isEmpty()) {
            throw new ResourceNotFoundException("No purchases found");
        }
        return purchaseList;
    }

    public Purchase updatePurchase(Long id, Purchase purchase) throws ResourceNotFoundException {
        purchase.setId(id);
        if (!creditaccountRepository.existsById(purchase.getCreditaccount().getId())) {
            throw new ResourceNotFoundException("Credit account { id=" + purchase.getCreditaccount().getId() + " }  not found");
        }
        if (!purchaseRepository.existsById(purchase.getId())) {
            throw new ResourceNotFoundException("Purchase { id=" + id + " } not found");
        }
        return purchaseRepository.save(purchase);
    }

    public void deletePurchase(Long id) throws ResourceNotFoundException {
        if (!purchaseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Purchase not found");
        }
        purchaseRepository.deleteById(id);
    }

    public List<PurchaseDtoToPayAdmin> listPurchasesToPayAdmin(Long id) throws ResourceNotFoundException {
        List<Invoice> invoices = invoiceRepository.findInvoiceWithNoPaymentByAdminId(id);
        List<Installment> installments = installmentRepository.findInstallmentsWithNoPaymentByAdminId(id);
        List<PurchaseDtoToPayAdmin> dtos = new ArrayList<>();

        for (Invoice invoice : invoices) {
            Purchase firstPurchase = invoice.getPurchases().iterator().next();
            PurchaseDtoToPayAdmin dto = new PurchaseDtoToPayAdmin();
            dto.setDni(firstPurchase.getCreditaccount().getCustomer().getUser().getDni());
            dto.setFullName(firstPurchase.getCreditaccount().getCustomer().getUser().getFirstName() + " " + firstPurchase.getCreditaccount().getCustomer().getUser().getLastName());
            dto.setInitialCost(invoice.getAmount());
            dto.setDueDate(invoice.getDueDate());
            dto.setTime(invoice.getTime());
            dto.setFinalCost(calculateRealFinalCost(invoice));
            dto.setInvoiceId(invoice.getId());
            dtos.add(dto);
        }

        for (Installment installment : installments) {
            PurchaseDtoToPayAdmin dto = new PurchaseDtoToPayAdmin();
            dto.setInitialCost(installment.getAmount());
            dto.setDueDate(installment.getDueDate());
            dto.setTime(installment.getPurchase().getTime());
            dto.setDni(installment.getPurchase().getCreditaccount().getCustomer().getUser().getDni());
            dto.setFullName(installment.getPurchase().getCreditaccount().getCustomer().getUser().getFirstName() + " " + installment.getPurchase().getCreditaccount().getCustomer().getUser().getLastName());
            dto.setInstallmentNumber(installment.getInstallmentNumber());

            BigDecimal finalCost = installment.getAmount();
            String creditType = installment.getPurchase().getCompensatoryRateType();
            BigDecimal rate = installment.getPurchase().getCompensatoryRate();
            Long compounding = installment.getPurchase().getCompensatoryCompouding();
            Date dueDate = Date.from(installment.getDueDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date currentDate = new Date();
            BigDecimal realFinalCost = UtilityFinance.calcFutureValue(finalCost, creditType, rate, dueDate, currentDate, compounding);
            String creditType2 = installment.getPurchase().getPenaltyRateType();
            BigDecimal rate2 = installment.getPurchase().getPenaltyRate();
            Long compounding2 = installment.getPurchase().getPenaltyCompouding();
            realFinalCost = UtilityFinance.calcFutureValue(realFinalCost, creditType2, rate2, dueDate, currentDate, compounding2);
            dto.setFinalCost(realFinalCost);
            dto.setInstallmentId(installment.getId());
            dtos.add(dto);
        }

        return dtos;
    }

    public List<PurchaseDtoToPayCustomer> listPurchasesToPayCustomer(Long id) throws ResourceNotFoundException {
        List<Invoice> invoices = invoiceRepository.findInvoiceWithNoPaymentByCustomerId(id);
        List<Installment> installments = installmentRepository.findInstallmentsWithNoPaymentByCustomerId(id);
        System.out.println("SIZE:" + installments.size());
        List<PurchaseDtoToPayCustomer> dtos = new ArrayList<>();

        for (Invoice invoice : invoices) {
            PurchaseDtoToPayCustomer dto = new PurchaseDtoToPayCustomer();
            dto.setDescription("en progreso");
            dto.setInitialCost(invoice.getAmount());
            dto.setDueDate(invoice.getDueDate());
            dto.setTime(invoice.getTime());
            dto.setFinalCost(calculateRealFinalCost(invoice));
            dto.setInvoiceId(invoice.getId());
            dtos.add(dto);
        }

        for (Installment installment : installments) {
            PurchaseDtoToPayCustomer dto = new PurchaseDtoToPayCustomer();
            dto.setDescription("en progreso");
            dto.setInitialCost(installment.getAmount());
            dto.setInstallmentNumber(installment.getInstallmentNumber());
            dto.setDueDate(installment.getDueDate());
            dto.setTime(installment.getPurchase().getTime());

            BigDecimal finalCost = installment.getAmount();
            String creditType = installment.getPurchase().getCompensatoryRateType();
            BigDecimal rate = installment.getPurchase().getCompensatoryRate();
            Long compounding = installment.getPurchase().getCompensatoryCompouding();
            Date dueDate = Date.from(installment.getDueDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date currentDate = new Date();
            BigDecimal realFinalCost = UtilityFinance.calcFutureValue(finalCost, creditType, rate, dueDate, currentDate, compounding);
            String creditType2 = installment.getPurchase().getPenaltyRateType();
            BigDecimal rate2 = installment.getPurchase().getPenaltyRate();
            Long compounding2 = installment.getPurchase().getPenaltyCompouding();
            realFinalCost = UtilityFinance.calcFutureValue(realFinalCost, creditType2, rate2, dueDate, currentDate, compounding2);
            dto.setFinalCost(realFinalCost);
            dto.setInstallmentId(installment.getId());
            dtos.add(dto);
        }

        return dtos;
    }

    private BigDecimal calculateRealFinalCost(Invoice invoice) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (Purchase purchase : invoice.getPurchases()) {
            BigDecimal finalCost = purchase.getFinalCost();
            String creditType = purchase.getCompensatoryRateType();
            BigDecimal rate = purchase.getCompensatoryRate();
            Long compounding = purchase.getCompensatoryCompouding();
            Date dueDate = Date.from(invoice.getDueDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date currentDate = new Date();
            BigDecimal realFinalCost = UtilityFinance.calcFutureValue(finalCost, creditType, rate, dueDate, currentDate, compounding);
            String creditType2 = purchase.getPenaltyRateType();
            BigDecimal rate2 = purchase.getPenaltyRate();
            Long compounding2 = purchase.getPenaltyCompouding();
            realFinalCost = UtilityFinance.calcFutureValue(realFinalCost, creditType2, rate2, dueDate, currentDate, compounding2);
            totalAmount = totalAmount.add(realFinalCost);
        }
        return totalAmount;
    }

    public List<PurchaseDtoToPayAdmin> listPurchasesToPayAdminSearch(Long id, String search) throws ResourceNotFoundException {
        List<PurchaseDtoToPayAdmin> purchaseDtoToPayAdminList = listPurchasesToPayAdmin(id);
        return purchaseDtoToPayAdminList.stream().filter(purchase -> purchase.getDni().toLowerCase().contains(search.toLowerCase()) || purchase.getFullName().toLowerCase().contains(search.toLowerCase())).collect(Collectors.toList());
    }

    @Transactional
    public List<Creditaccount> checkDueDates() {
        List<Invoice> invoices = invoiceRepository.findAll();
        List<Installment> installments = installmentRepository.findAll();

        List<Creditaccount> creditaccountList = new ArrayList<>();

        for (Invoice invoice : invoices) {
            if (invoice.getDueDate().isBefore(LocalDate.now()) && invoice.getPayment() == null) {
                Set<Purchase> purchases = invoice.getPurchases();
                if (purchases == null || purchases.isEmpty()) {
                    continue;
                }
                Long creditaccountId = purchases.stream().findFirst().orElse(null).getCreditaccount().getId();
                Creditaccount creditaccount = creditaccountRepository.findById(creditaccountId).orElse(null);
                if (creditaccount != null && creditaccount.getActive()) {
                    creditaccount.setActive(false);
                    creditaccountRepository.save(creditaccount);
                    creditaccountList.add(creditaccount);
                }
            }
        }

        for (Installment installment : installments) {
            if (installment.getDueDate().isBefore(LocalDate.now()) && installment.getPayment() == null) {
                Long creditaccountId = installment.getPurchase().getCreditaccount().getId();

                Creditaccount creditaccount = creditaccountRepository.findById(creditaccountId).orElse(null);
                if (creditaccount != null && creditaccount.getActive()) {
                    creditaccount.setActive(false);
                    creditaccountRepository.save(creditaccount);
                    creditaccountList.add(creditaccount);
                }
            }
        }
        return creditaccountList;
    }


    @Scheduled(cron = "0 0 0 * * *")
    public void checkDueDatesScheduled() {
        checkDueDates();
    }

}

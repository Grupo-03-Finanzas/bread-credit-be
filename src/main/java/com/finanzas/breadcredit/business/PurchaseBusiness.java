package com.finanzas.breadcredit.business;

import com.finanzas.breadcredit.dto.purchase.PurchaseDtoToPayAdmin;
import com.finanzas.breadcredit.entity.Installment;
import com.finanzas.breadcredit.entity.Invoice;
import com.finanzas.breadcredit.entity.Purchase;
import com.finanzas.breadcredit.exception.ResourceNotFoundException;
import com.finanzas.breadcredit.repository.CreditaccountRepository;
import com.finanzas.breadcredit.repository.InstallmentRepository;
import com.finanzas.breadcredit.repository.InvoiceRepository;
import com.finanzas.breadcredit.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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
            dto.setFullname(firstPurchase.getCreditaccount().getCustomer().getUser().getFirstName() + " " + firstPurchase.getCreditaccount().getCustomer().getUser().getLastName());
            dto.setAmount(invoice.getAmount());
            dto.setDueDate(invoice.getDueDate());
            dto.setTime(firstPurchase.getTime());
            dtos.add(dto);
        }

        for (Installment installment : installments) {
            PurchaseDtoToPayAdmin dto = new PurchaseDtoToPayAdmin();
            dto.setDni(installment.getPurchase().getCreditaccount().getCustomer().getUser().getDni());
            dto.setFullname(installment.getPurchase().getCreditaccount().getCustomer().getUser().getFirstName() + " " + installment.getPurchase().getCreditaccount().getCustomer().getUser().getLastName());
            dto.setAmount(installment.getAmount());
            dto.setInstallmentNumber(installment.getInstallmentNumber());
            dto.setDueDate(installment.getDueDate());
            dto.setTime(installment.getPurchase().getTime());
            dtos.add(dto);
        }

        return dtos;
    }

}

package com.finanzas.breadcredit.business;

import com.finanzas.breadcredit.entity.Purchase;
import com.finanzas.breadcredit.exception.ResourceNotFoundException;
import com.finanzas.breadcredit.repository.CreditaccountRepository;
import com.finanzas.breadcredit.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class PurchaseBusiness {


    private final PurchaseRepository purchaseRepository;


    private final CreditaccountRepository creditaccountRepository;

    @Autowired
    public PurchaseBusiness(PurchaseRepository purchaseRepository, CreditaccountRepository creditaccountRepository) {
        this.purchaseRepository = purchaseRepository;
        this.creditaccountRepository = creditaccountRepository;
    }

    public Purchase insertPurchase(Purchase purchase) {
        purchase.setId(null);
        purchase.setTime(Instant.now());
        return purchaseRepository.save(purchase);
    }

    public Purchase getPurchaseById(Integer id) throws ResourceNotFoundException {
        return purchaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase { id=" + id + " } not found"));
    }

    public List<Purchase> listPurchases() throws ResourceNotFoundException {
        List<Purchase> purchaseList = purchaseRepository.findAll();
        if (purchaseList.isEmpty()) {
            throw new ResourceNotFoundException("No purchases found");
        }
        return purchaseList;
    }

    public Purchase updatePurchase(Integer id, Purchase purchase) throws ResourceNotFoundException {
        purchase.setId(id);
        if (!creditaccountRepository.existsById(purchase.getCreditaccount().getId())) {
            throw new ResourceNotFoundException("Credit account { id=" + purchase.getCreditaccount().getId() + " }  not found");
        }
        if (!purchaseRepository.existsById(purchase.getId())) {
            throw new ResourceNotFoundException("Purchase { id=" + id + " } not found");
        }
        return purchaseRepository.save(purchase);
    }

    public void deletePurchase(Integer id) throws ResourceNotFoundException {
        if (!purchaseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Purchase not found");
        }
        purchaseRepository.deleteById(id);
    }
}

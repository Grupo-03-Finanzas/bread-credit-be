package com.finanzas.breadcredit.business;

import com.finanzas.breadcredit.entity.Purchase;
import com.finanzas.breadcredit.repository.CreditaccountRepository;
import com.finanzas.breadcredit.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class PurchaseBusiness {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private CreditaccountRepository creditaccountRepository;

    public Purchase insertPurchase(Purchase purchase) throws Exception{
        purchase.setId(null);
        purchase.setTime(Instant.now());
        return purchaseRepository.save(purchase);
    }

    public Purchase getPurchaseById(Integer id) throws Exception{
        return purchaseRepository.findById(id).orElseThrow(() -> new Exception("Purchase not found"));
    }

    public List<Purchase> listPurchases() throws Exception{
        List<Purchase> purchaseList = purchaseRepository.findAll();
        if(purchaseList.isEmpty()){
            throw new Exception("No purchases found");
        }
        return purchaseList;
    }

    public Purchase updatePurchase(Integer id, Purchase purchase) throws Exception{
        purchase.setId(id);
        if(!creditaccountRepository.existsById(purchase.getCreditaccount().getId())){
            throw new Exception("Credit account not found");
        }
        if(!purchaseRepository.existsById(purchase.getId())){
            System.out.println(purchase.getId());
            throw new Exception("Purchase not found");
        }
        return purchaseRepository.save(purchase);
    }

    public void deletePurchase(Integer id) throws Exception{
        if(!purchaseRepository.existsById(id)){
            throw new Exception("Purchase not found");
        }
        purchaseRepository.deleteById(id);
    }

}

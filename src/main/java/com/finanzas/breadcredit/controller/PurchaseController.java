package com.finanzas.breadcredit.controller;

import com.finanzas.breadcredit.business.PurchaseBusiness;
import com.finanzas.breadcredit.dto.purchase.PurchaseDtoData;
import com.finanzas.breadcredit.dto.purchase.PurchaseDtoInsert;
import com.finanzas.breadcredit.dto.purchase.PurchaseDtoToPayAdmin;
import com.finanzas.breadcredit.entity.Purchase;
import com.finanzas.breadcredit.exception.ResourceNotFoundException;
import com.finanzas.breadcredit.utility.UtilityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    private final PurchaseBusiness purchaseBusiness;

    @Autowired
    public PurchaseController(PurchaseBusiness purchaseBusiness) {
        this.purchaseBusiness = purchaseBusiness;

    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public PurchaseDtoData insertPurchase(@RequestBody PurchaseDtoInsert purchaseDtoInsert) {
        Purchase purchase = UtilityDto.convertTo(purchaseDtoInsert, Purchase.class);
        purchase = purchaseBusiness.insertPurchase(purchase);
        return UtilityDto.convertTo(purchase, PurchaseDtoData.class);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PurchaseDtoData getPurchaseById(@PathVariable Long id) {
        Purchase purchase = purchaseBusiness.getPurchaseById(id);
        return UtilityDto.convertTo(purchase, PurchaseDtoData.class);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<PurchaseDtoData> listPurchases() {
        List<Purchase> purchaseList = purchaseBusiness.listPurchases();
        return UtilityDto.convertToList(purchaseList, PurchaseDtoData.class);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PurchaseDtoData updatePurchase(@PathVariable Long id, @RequestBody PurchaseDtoInsert purchaseDtoInsert) {
        Purchase purchase = UtilityDto.convertTo(purchaseDtoInsert, Purchase.class);
        purchase = purchaseBusiness.updatePurchase(id, purchase);
        return UtilityDto.convertTo(purchase, PurchaseDtoData.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Void deletePurchase(@PathVariable Long id) {
        purchaseBusiness.deletePurchase(id);
        return null;
    }

    @GetMapping("/topay/admin/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<PurchaseDtoToPayAdmin> listPurchasesToPayAdmin(@PathVariable Long id) throws ResourceNotFoundException {
        return purchaseBusiness.listPurchasesToPayAdmin(id);
    }
}

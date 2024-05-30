package com.finanzas.breadcredit.controller;

import com.finanzas.breadcredit.business.PurchaseBusiness;
import com.finanzas.breadcredit.dto.purchase.PurchaseDtoData;
import com.finanzas.breadcredit.dto.purchase.PurchaseDtoInsert;
import com.finanzas.breadcredit.entity.Purchase;
import com.finanzas.breadcredit.utility.UtilityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseBusiness purchaseBusiness;

    @PostMapping("")
    public ResponseEntity<PurchaseDtoData> purchase(@RequestBody PurchaseDtoInsert purchaseDtoInsert) {
        Purchase purchase = UtilityDto.convertTo(purchaseDtoInsert, Purchase.class);
        try {
            purchase = purchaseBusiness.insertPurchase(purchase);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        PurchaseDtoData purchaseDtoData = UtilityDto.convertTo(purchase, PurchaseDtoData.class);
        return new ResponseEntity<>(purchaseDtoData, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseDtoData> getPurchaseBy(@PathVariable Integer id) {
        Purchase purchase;
        try {
            purchase = purchaseBusiness.getPurchaseById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        PurchaseDtoData purchaseDtoData = UtilityDto.convertTo(purchase, PurchaseDtoData.class);
        return new ResponseEntity<>(purchaseDtoData, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<PurchaseDtoData>> listPurchases() {
        List<Purchase> purchaseList;
        try {
            purchaseList = purchaseBusiness.listPurchases();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        List<PurchaseDtoData> purchaseDtoDataList = UtilityDto.convertToList(purchaseList, PurchaseDtoData.class);
        return new ResponseEntity<>(purchaseDtoDataList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaseDtoData> updatePurchase(@PathVariable Integer id, @RequestBody PurchaseDtoInsert purchaseDtoInsert) {
        Purchase purchase = UtilityDto.convertTo(purchaseDtoInsert, Purchase.class);
        try {
            purchase = purchaseBusiness.updatePurchase(id, purchase);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        PurchaseDtoData purchaseDtoData = UtilityDto.convertTo(purchase, PurchaseDtoData.class);
        return new ResponseEntity<>(purchaseDtoData, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchase(@PathVariable Integer id) {
        try {
            purchaseBusiness.deletePurchase(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

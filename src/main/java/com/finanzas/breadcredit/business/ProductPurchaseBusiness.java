package com.finanzas.breadcredit.business;

import com.finanzas.breadcredit.entity.ProductPurchase;
import com.finanzas.breadcredit.repository.ProductPurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductPurchaseBusiness {

    private final ProductPurchaseRepository productPurchaseRepository;

    @Autowired
    public ProductPurchaseBusiness(ProductPurchaseRepository productPurchaseRepository) {
        this.productPurchaseRepository  = productPurchaseRepository;
    }

    public ProductPurchase insert(ProductPurchase productPurchase) {
        productPurchaseRepository.save(productPurchase);
        return productPurchase;
    }
}

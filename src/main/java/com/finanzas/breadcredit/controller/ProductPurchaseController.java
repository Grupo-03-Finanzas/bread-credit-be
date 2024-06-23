package com.finanzas.breadcredit.controller;

import com.finanzas.breadcredit.business.ProductPurchaseBusiness;
import com.finanzas.breadcredit.dto.productpurchase.ProductPurchaseDto;
import com.finanzas.breadcredit.entity.ProductPurchase;
import com.finanzas.breadcredit.utility.UtilityDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product-purchase")
public class ProductPurchaseController {
    private final ProductPurchaseBusiness productPurchaseBusiness;

    public ProductPurchaseController(ProductPurchaseBusiness productPurchaseBusiness) {
        this.productPurchaseBusiness = productPurchaseBusiness;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductPurchaseDto addProductPurchase(@RequestBody ProductPurchaseDto productPurchaseDto) {
        ProductPurchase productPurchase;
        productPurchase = UtilityDto.convertTo(productPurchaseDto, ProductPurchase.class);
        productPurchase = productPurchaseBusiness.insert(productPurchase);
        return UtilityDto.convertTo(productPurchase, ProductPurchaseDto.class);
    }
}

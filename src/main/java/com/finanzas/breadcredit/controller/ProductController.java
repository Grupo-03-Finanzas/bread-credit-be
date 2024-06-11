package com.finanzas.breadcredit.controller;

import com.finanzas.breadcredit.business.ProductBusiness;
import com.finanzas.breadcredit.dto.creditaccount.CreditaccountDtoData;
import com.finanzas.breadcredit.dto.product.ProductDtoData;
import com.finanzas.breadcredit.dto.product.ProductDtoInsert;
import com.finanzas.breadcredit.entity.Creditaccount;
import com.finanzas.breadcredit.entity.Product;
import com.finanzas.breadcredit.utility.UtilityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductBusiness productBusiness;

    @PostMapping("")
    public ResponseEntity<ProductDtoData> insertProduct(@RequestBody ProductDtoInsert productDtoInsert) {
        Product product = UtilityDto.convertTo(productDtoInsert, Product.class);

        try {
            product = productBusiness.insertProduct(product);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }

        ProductDtoData productDtoData = UtilityDto.convertTo(product, ProductDtoData.class);
        return new ResponseEntity<>(productDtoData, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDtoData> getProductById(@PathVariable Integer id) {
        Product product;

        try {
            product = productBusiness.getProductById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

        ProductDtoData productDtoData = UtilityDto.convertTo(product, ProductDtoData.class);
        return new ResponseEntity<>(productDtoData, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<ProductDtoData>> listProducts() {
        List<Product> listProducts;

        try {
            listProducts = productBusiness.listProducts();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

        List<ProductDtoData> productDtoDataList = UtilityDto.convertToList(listProducts, ProductDtoData.class);
        return new ResponseEntity<>(productDtoDataList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDtoData> updateProduct(@PathVariable Integer id, @RequestBody ProductDtoInsert productDtoInsert) {
        Product product = UtilityDto.convertTo(productDtoInsert, Product.class);

        try {
            product = productBusiness.updateProduct(id, product);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        ProductDtoData productDtoData = UtilityDto.convertTo(product, ProductDtoData.class);
        return new ResponseEntity<>(productDtoData, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        try {
            productBusiness.deleteProduct(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<List<ProductDtoData>> getProductsByAdminId(@PathVariable Integer id) {
        List<Product> listProducts;
        try {
            listProducts = productBusiness.getProductByAdminId(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        List<ProductDtoData> productDtoDataList = UtilityDto.convertToList(listProducts, ProductDtoData.class);
        return new ResponseEntity<>(productDtoDataList, HttpStatus.OK);
    }
}

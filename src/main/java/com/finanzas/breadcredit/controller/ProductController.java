package com.finanzas.breadcredit.controller;

import com.finanzas.breadcredit.business.ProductBusiness;
import com.finanzas.breadcredit.dto.product.ProductDtoData;
import com.finanzas.breadcredit.dto.product.ProductDtoInsert;
import com.finanzas.breadcredit.entity.Product;
import com.finanzas.breadcredit.exception.ResourceNotFoundException;
import com.finanzas.breadcredit.utility.UtilityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductBusiness productBusiness;

    @Autowired
    public ProductController(ProductBusiness productBusiness) {
        this.productBusiness = productBusiness;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDtoData getProductById(@PathVariable Long id) throws ResourceNotFoundException {
        Product product = productBusiness.getProductById(id);
        return UtilityDto.convertTo(product, ProductDtoData.class);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDtoData> listProducts() throws ResourceNotFoundException {
        List<Product> listProducts = productBusiness.listProducts();
        return UtilityDto.convertToList(listProducts, ProductDtoData.class);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDtoData insertProduct(@RequestBody ProductDtoInsert productDtoInsert) throws ResourceNotFoundException {
        Product product = UtilityDto.convertTo(productDtoInsert, Product.class);
        product = productBusiness.insertProduct(product);
        return UtilityDto.convertTo(product, ProductDtoData.class);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDtoData updateProduct(@PathVariable Long id, @RequestBody ProductDtoInsert productDtoInsert) throws ResourceNotFoundException {
        Product product = UtilityDto.convertTo(productDtoInsert, Product.class);
        product = productBusiness.updateProduct(id, product);
        return UtilityDto.convertTo(product, ProductDtoData.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) throws ResourceNotFoundException {
        productBusiness.deleteProduct(id);
    }

    @GetMapping("/admin/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDtoData> getProductsByAdminId(@PathVariable Long id) throws ResourceNotFoundException {
        List<Product> listProducts = productBusiness.getProductByAdminId(id);
        return UtilityDto.convertToList(listProducts, ProductDtoData.class);
    }
}

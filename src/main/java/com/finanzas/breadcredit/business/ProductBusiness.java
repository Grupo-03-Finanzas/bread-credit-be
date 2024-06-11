package com.finanzas.breadcredit.business;

import com.finanzas.breadcredit.entity.Creditaccount;
import com.finanzas.breadcredit.entity.Product;
import com.finanzas.breadcredit.repository.ProductRepository;
import com.finanzas.breadcredit.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductBusiness {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AdminRepository adminRepository;

    public Product insertProduct(Product product) throws Exception {
        product.setId(null);
        if (!adminRepository.existsById(product.getAdmin().getId())) {
            throw new Exception("Admin not found");
        }
        return productRepository.save(product);
    }

    public Product getProductById(Integer id) throws Exception {
        return productRepository.findById(id).orElseThrow(() -> new Exception("Product not found"));
    }

    public List<Product> listProducts() throws Exception {
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    @Transactional
    public Product updateProduct(Integer id, Product product) throws Exception {
        product.setId(id);

        if (!productRepository.existsById(id)) {
            throw new Exception("Product not found");
        }

        return productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(Integer id) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(() -> new Exception("Product not found"));
        productRepository.delete(product);
    }

    public List<Product> getProductByAdminId(Integer id) throws Exception{
        List<Product> productList = productRepository.findByAdmin_Id(id).orElseThrow();
        return productList;
    }
}

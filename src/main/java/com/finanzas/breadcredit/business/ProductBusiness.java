package com.finanzas.breadcredit.business;

import com.finanzas.breadcredit.entity.Product;
import com.finanzas.breadcredit.repository.ProductRepository;
import com.finanzas.breadcredit.repository.AdminRepository;
import com.finanzas.breadcredit.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductBusiness {

    private final ProductRepository productRepository;
    private final AdminRepository adminRepository;

    @Autowired
    public ProductBusiness(ProductRepository productRepository, AdminRepository adminRepository) {
        this.productRepository = productRepository;
        this.adminRepository = adminRepository;
    }

    public Product getProductById(Long id) throws ResourceNotFoundException {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    public List<Product> listProducts() throws ResourceNotFoundException {
        List<Product> productList = productRepository.findAll();
        if (productList.isEmpty()) {
            throw new ResourceNotFoundException("No products found");
        }
        return productList;
    }

    public Product insertProduct(Product product) throws ResourceNotFoundException {
        product.setId(null);
        if (!adminRepository.existsById(product.getAdmin().getId())) {
            throw new ResourceNotFoundException("Admin not found");
        }
        return productRepository.save(product);
    }

    @Transactional
    public Product updateProduct(Long id, Product product) throws ResourceNotFoundException {
        product.setId(id);

        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found");
        }

        return productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(Long id) throws ResourceNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        productRepository.delete(product);
    }

    public List<Product> getProductByAdminId(Long id) throws ResourceNotFoundException {
        return productRepository.findByAdmin_Id(id)
                .orElseThrow(() -> new ResourceNotFoundException("Products not found for the given admin"));
    }
}

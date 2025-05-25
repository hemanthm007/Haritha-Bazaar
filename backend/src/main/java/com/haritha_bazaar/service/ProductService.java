package com.haritha_bazaar.service;

// import com.haritha_bazaar.*;
import com.haritha_bazaar.model.Product;
import com.haritha_bazaar.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getProductsBySeller(String sellerEmail) {
        return productRepository.findBySellerEmail(sellerEmail);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}

package com.haritha_bazaar.controller;

import com.haritha_bazaar.model.Product;
import com.haritha_bazaar.service.ProductService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
// import com.haritha_bazaar.*;


@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")  // Change this to your frontend URL in production
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Add product
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    // Get all products (for customers)
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Get seller’s products
    @GetMapping("/seller/{email}")
    public List<Product> getProductsBySeller(@PathVariable String email) {
        return productService.getProductsBySeller(email);
    }
}

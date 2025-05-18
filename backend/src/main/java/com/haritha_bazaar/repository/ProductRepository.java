package com.haritha_bazaar.repository;

import com.haritha_bazaar.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findBySellerEmail(String sellerEmail);
}

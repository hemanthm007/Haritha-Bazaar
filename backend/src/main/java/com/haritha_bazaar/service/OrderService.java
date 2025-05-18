package com.haritha_bazaar.service;

import com.haritha_bazaar.model.Order;
import com.haritha_bazaar.model.OrderItem;
import com.haritha_bazaar.model.Product;
import com.haritha_bazaar.repository.OrderRepository;
import com.haritha_bazaar.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepo;
    private final ProductRepository productRepo;

    public OrderService(OrderRepository orderRepo, ProductRepository productRepo) {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
    }

    public List<Order> getOrdersByCustomer(String email) {
        return orderRepo.findByCustomerEmail(email);
    }

    public Order placeOrder(Order order) {
        double total = 0;

        for (OrderItem item : order.getItems()) {
            Product p = productRepo.findById(item.getProduct().getId()).orElseThrow();
            item.setProduct(p);
            item.setOrder(order);
            total += p.getPrice() * item.getQuantity();
        }

        order.setTotal(total);
        return orderRepo.save(order);
    }
}

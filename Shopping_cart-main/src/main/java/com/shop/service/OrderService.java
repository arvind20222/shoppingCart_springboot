
package com.shop.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.dao.OrderDAO;
import com.shop.entity.Cart;
import com.shop.entity.Customer;
import com.shop.entity.Employee;
import com.shop.entity.Order;
import com.shop.entity.Product;
import com.shop.entity.Stock;
import com.shop.repository.CartRepository;
import com.shop.repository.CustomerRepository;
import com.shop.repository.OrderRepository;
import com.shop.repository.ProductRepository;
import com.shop.repository.StockRepository;

@Service
public class OrderService implements OrderDAO {

    // Autowired constructor for dependency injection.
    @Autowired
    CartService cartService;
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository; 

    @Autowired
    StockRepository stockRepository; 

    // Method to get orders by customer ID.
    public List<Order> getOrdersByCustomerId(Long cId) {
        return orderRepository.findAll();
    }

    // Method to create an order for a given customer ID.
    public Order createOrder(Long cId) {
        Order order = new Order();
        Cart cart = cartService.getCartByCustomerId(cId);

        order.setCart(cart);
        order.setoDate(LocalDate.now());
        Double cost = 0.0;
        
        // Calculating total cost based on products in the cart.
        List<Product> prod = cart.getProducts();
        for (Product p : prod) {
            cost = cost + (cart.getQuantity() * p.getPrice());
        }

        order.setoCost(cost);

        return orderRepository.save(order);
    }

    // Method to save an order.
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    // Method to find an order by its ID.
    public Optional<Order> findById(Long oId) {
        return orderRepository.findById(oId);
    }
}

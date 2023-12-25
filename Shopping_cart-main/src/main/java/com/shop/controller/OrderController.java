
package com.shop.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.shop.entity.Order;
import com.shop.exception.OrderNotFoundException;
import com.shop.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    // Logger for logging messages
    Logger logger = LogManager.getLogger(OrderController.class);

    private  OrderService orderService;

    // Constructor for injecting OrderService dependency
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // HTTP endpoint for retrieving orders by customer ID
    @GetMapping("/{cId}")
    public ResponseEntity<List<Order>> getOrdersByCustomerId(@PathVariable Long cId) {
        // Logging the request to get orders by customer ID
        logger.info("Retrieving orders for customer with ID {}", cId);

        if (orderService.getOrdersByCustomerId(cId) != null) {
            List<Order> orders = orderService.getOrdersByCustomerId(cId);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } else {
            throw new OrderNotFoundException("Orders not found with customer ID " + cId);
        }
    }

    // HTTP endpoint for creating a new order for a customer
    @PostMapping("/{cId}")
    public ResponseEntity<Order> createOrder(@PathVariable Long cId) {
        // Logging the creation of a new order for a customer
        logger.info("Creating a new order for customer with ID {}", cId);

        Order order = orderService.createOrder(cId);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}

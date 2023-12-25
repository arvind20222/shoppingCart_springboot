

package com.shop.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.shop.entity.Customer;
import com.shop.exception.CustomerNotFoundException;
import com.shop.service.CustomerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    // Logger for logging messages
    Logger logger = LogManager.getLogger(CustomerController.class);

    private final CustomerService customerService;

    // Constructor for injecting CustomerService dependency
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // HTTP endpoint for creating a new customer
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Validated @RequestBody Customer customer) {
        // Logging the creation of a new customer
        logger.info("Creating a new customer: {}", customer);

        return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.CREATED);
    }

    // HTTP endpoint for retrieving all customers
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        // Logging the request to get all customers
        logger.info("Retrieving all customers");
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    // HTTP endpoint for getting a customer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        // Logging the request to get a customer by ID
        logger.info("Retrieving customer with ID {}", id);

        Optional<Customer> customer = customerService.getCustomerById(id);

        customer.orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID " + id));

        return customer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // HTTP endpoint for updating a customer
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @Validated @RequestBody Customer updatedCustomer) {
        // Logging the update of a customer
        logger.info("Updating customer with ID {} with new data: {}", id, updatedCustomer);

        Customer result = customerService.updateCustomer(id, updatedCustomer);
        return result != null ?
                new ResponseEntity<>(result, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

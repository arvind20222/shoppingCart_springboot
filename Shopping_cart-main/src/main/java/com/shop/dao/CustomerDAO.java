package com.shop.dao;
import com.shop.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {
    Customer createCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Optional<Customer> getCustomerById(Long id);
    Customer updateCustomer(Long id, Customer updatedCustomer);
}
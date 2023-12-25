package com.shop.tests;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.shop.entity.Customer;
import com.shop.repository.CustomerRepository;
import com.shop.service.CustomerService;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer(1L, "John Doe", "123 Main St", "1234567890");

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer savedCustomer = customerService.createCustomer(customer);

        verify(customerRepository, times(1)).save(any(Customer.class));

    }

    @Test
    public void testGetAllCustomers() {
        Customer customer1 = new Customer(1L, "John Doe", "123 Main St", "1234567890");
        Customer customer2 = new Customer(2L, "Jane Doe", "456 Oak St", "9876543210");

        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        List<Customer> customers = customerService.getAllCustomers();

        verify(customerRepository, times(1)).findAll();

    }

    @Test
    public void testGetCustomerById() {
        Long customerId = 1L;
        Customer customer = new Customer(customerId, "John Doe", "123 Main St", "1234567890");

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        Optional<Customer> foundCustomer = customerService.getCustomerById(customerId);

        verify(customerRepository, times(1)).findById(customerId);

    }

    @Test
    public void testUpdateCustomer() {
        Long customerId = 1L;
        Customer existingCustomer = new Customer(customerId, "John Doe", "123 Main St", "1234567890");
        Customer updatedCustomer = new Customer(customerId, "Updated Name", "Updated Address", "9876543210");

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));
        when(customerRepository.save(any(Customer.class))).thenReturn(updatedCustomer);

        Customer result = customerService.updateCustomer(customerId, updatedCustomer);

        verify(customerRepository, times(1)).findById(customerId);
        verify(customerRepository, times(1)).save(any(Customer.class));

    }
}

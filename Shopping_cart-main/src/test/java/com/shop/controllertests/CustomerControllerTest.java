package com.shop.controllertests;



import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.shop.controller.CustomerController;
import com.shop.entity.Customer;
import com.shop.service.CustomerService;

class CustomerControllerTest {

 @Mock
 private CustomerService customerService;

 @InjectMocks
 private CustomerController customerController;

 @BeforeEach
 void setUp() {
     MockitoAnnotations.openMocks(this);
 }

 @Test
 void createCustomer_ValidCustomer_ReturnsCreatedResponse() {
     // Arrange
     Customer validCustomer = new Customer();
     when(customerService.createCustomer(any(Customer.class))).thenReturn(validCustomer);

     // Act
     ResponseEntity<Customer> response = customerController.createCustomer(validCustomer);

     // Assert
     verify(customerService, times(1)).createCustomer(validCustomer);
     assert response.getStatusCode() == HttpStatus.CREATED;
     assert response.getBody() == validCustomer;
 }

 @Test
 void getCustomerById_ExistingCustomer_ReturnsOkResponse() {
     // Arrange
     Long customerId = 1L;
     Customer existingCustomer = new Customer();
     when(customerService.getCustomerById(customerId)).thenReturn(Optional.of(existingCustomer));

     // Act
     ResponseEntity<Customer> response = customerController.getCustomerById(customerId);

     // Assert
     verify(customerService, times(1)).getCustomerById(customerId);
     assert response.getStatusCode() == HttpStatus.OK;
     assert response.getBody() == existingCustomer;
 }


}

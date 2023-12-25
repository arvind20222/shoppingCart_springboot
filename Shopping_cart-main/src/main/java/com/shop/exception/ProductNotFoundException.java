package com.shop.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

//This annotation sets the HTTP status code to 404 (NOT_FOUND) when an instance of this exception is thrown.
@ResponseStatus(HttpStatus.NOT_FOUND)
//This class extends the Exception class, indicating that it is a custom exception for product not found scenarios.
public class ProductNotFoundException extends Exception {

 // Constructor that takes a message as a parameter and passes it to the constructor of the superclass (Exception).
 public ProductNotFoundException(String message) {
     // Calls the constructor of the superclass (Exception) with the provided message.
     super(message);
 }
}




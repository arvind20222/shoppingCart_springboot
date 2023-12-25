package com.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CartItemNotFoundException extends Exception {
    public CartItemNotFoundException(String message) {
        super(message);
    }
}
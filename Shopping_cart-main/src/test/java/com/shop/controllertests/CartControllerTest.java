package com.shop.controllertests;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.shop.controller.CartController;
import com.shop.entity.Cart;
import com.shop.exception.CartItemNotFoundException;
import com.shop.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

public class CartControllerTest {

    private CartService cartService;
    private CartController cartController;

    @BeforeEach
    public void setUp() {
    	cartService = mock(CartService.class);
        cartController = new CartController();
        cartController.setCartService(cartService);
    }

    @Test
    public void addToCart_ShouldReturnCreatedStatus() {
        Long productId = 1L;
        Long customerId = 2L;
        int quantity = 3;

        Cart mockCart = new Cart();
        when(cartService.addToCart(productId, customerId, quantity)).thenReturn(mockCart);

        ResponseEntity<Cart> response = cartController.addToCart(productId, customerId, quantity);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockCart, response.getBody());
        verify(cartService, times(1)).addToCart(productId, customerId, quantity);
    }

    @Test
    public void getCart_ShouldReturnListOfCartItems() {
        List<Cart> mockCartItems = Arrays.asList(new Cart(), new Cart());
        when(cartService.getCart()).thenReturn(mockCartItems);

        List<Cart> result = cartController.getCart();

        assertEquals(mockCartItems.size(), result.size());
        verify(cartService, times(1)).getCart();
    }

    @Test
    public void getCartByCustomerId_NonExistingCustomerId_ShouldThrowException() {
        Long customerId = 1L;
        when(cartService.getCartByCustomerId(customerId)).thenReturn(null);

        assertThrows(CartItemNotFoundException.class, () -> cartController.getCartByCustomerId(customerId));
        verify(cartService, times(1)).getCartByCustomerId(customerId);
    }

    @Test
    public void deleteCartByCustomerId_ShouldInvokeDeleteCartByCartId() {
        Long cartId = 1L;

        cartController.deleteCartByCustomerId(cartId);

        verify(cartService, times(1)).deleteCartByCartId(cartId);
    }

}

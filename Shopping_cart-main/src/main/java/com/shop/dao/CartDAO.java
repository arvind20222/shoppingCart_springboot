package com.shop.dao;
import com.shop.entity.Cart;

import java.util.List;
import java.util.Optional;

public interface CartDAO {
    Cart addToCart(Long productId, Long customerId, int quantity);
    List<Cart> getCart();
    Cart getCartByCustomerId(Long customerId);
}
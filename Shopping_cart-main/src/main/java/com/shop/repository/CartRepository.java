// This is a Spring Data JPA repository interface for the "Cart" entity.

package com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entity.Cart;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    // This interface extends JpaRepository, providing CRUD operations for the "Cart" entity.
    
    // Custom query method to find a cart by customer ID
    Cart findByCustomer_Id(Long customerId);

}

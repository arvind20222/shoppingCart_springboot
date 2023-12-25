// This is a Spring Data JPA repository interface for the "Order" entity.

package com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // This interface extends JpaRepository, providing CRUD operations for the "Order" entity.
  
}

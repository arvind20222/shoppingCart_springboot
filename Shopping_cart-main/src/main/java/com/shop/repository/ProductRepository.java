// This is a Spring Data JPA repository interface for the "Product" entity.

package com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // This interface extends JpaRepository, providing CRUD operations for the "Product" entity.
   
}

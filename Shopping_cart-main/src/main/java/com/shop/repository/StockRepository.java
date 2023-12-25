// This is a Spring Data JPA repository interface for the "Stock" entity.
package com.shop.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.shop.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
    // This interface extends JpaRepository, providing CRUD operations for the "Stock" entity.
    // The entity type is "Stock," and the primary key type is "Long."
    // Spring Data JPA automatically provides implementation for basic CRUD operations.
}

// This is a Spring Data JPA repository interface for the "Customer" entity.

package com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // This interface extends JpaRepository, providing CRUD operations for the "Customer" entity.
}

// This is a Spring Data JPA repository interface for the "Employee" entity.
package com.shop.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.shop.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // This interface extends JpaRepository, providing CRUD operations for the "Employee" entity.  
}
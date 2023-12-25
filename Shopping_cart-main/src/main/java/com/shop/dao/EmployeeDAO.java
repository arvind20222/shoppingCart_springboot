package com.shop.dao;
import com.shop.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDAO {
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(Long id);
    Employee saveEmployee(Employee employee);
    void deleteEmployee(Long id);
    void confirm(Long oId, Long eId);
}
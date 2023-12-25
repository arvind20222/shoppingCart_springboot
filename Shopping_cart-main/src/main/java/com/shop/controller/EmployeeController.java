package com.shop.controller;
 
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.shop.entity.Employee;
import com.shop.entity.Order;
import com.shop.exception.EmployeeNotFoundException;
import com.shop.service.EmployeeService;
 
import java.util.List;
import java.util.Optional;
import java.util.Set;
//Controller class for managing Employee-related HTTP requests.
 
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    // Logger for logging messages
    Logger logger = LogManager.getLogger(EmployeeController.class);
 
    private final EmployeeService employeeService;
 
    // Constructor for injecting EmployeeService dependency
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
 
    // HTTP endpoint  for retrieving all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        // Logging the request to get all employees
        logger.info("Retrieving all employees");
        return employeeService.getAllEmployees();
    }
 
    // HTTP endpoint for getting an employee by ID
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) throws EmployeeNotFoundException {
        // Logging the request to get an employee by ID
        logger.info("Retrieving employee with ID {}", id);
 
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        employee.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID " + id));
 
        return employee.get();
    }
 
    // HTTP endpoint for getting orders of an employee by ID
    @GetMapping("/{id}/orders")
    public Set<Order> getEmployeeOrdersById(@PathVariable Long id) throws EmployeeNotFoundException {
        // Logging the request to get orders of an employee by ID
        logger.info("Retrieving orders for employee with ID {}", id);
 
        Optional<Set<Order>> employee = Optional.of(employeeService.getEmployeeOrderById(id));
        employee.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID " + id));
 
        return employee.get();
    }
 
    // HTTP endpoint for creating a new employee
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        // Logging the creation of a new employee
        logger.info("Creating a new employee: {}", employee);
 
        return employeeService.saveEmployee(employee);
    }
 
    // HTTP endpoint for updating an employee
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        // Logging the update of an employee
        logger.info("Updating employee with ID {} with new data: {}", id, employee);
        Employee existingEmployee=employeeService.getEmployeeById(id).get();
        return employeeService.updateEmployee(existingEmployee,employee);
    }
 
    // HTTP endpoint for deleting an employee
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        // Logging the deletion of an employee
        logger.info("Deleting employee with ID {}", id);
 
        employeeService.getEmployeeById(id).get();
        employeeService.deleteEmployee(id);
    }
 
    // HTTP endpoint for confirming an order for an employee
    @PostMapping("/confirmorder/{oId}/{eId}")
    public String confirmOrder(@PathVariable Long oId, @PathVariable Long eId) {
        // Logging the confirmation of an order for an employee
        logger.info("Confirming order with ID {} for employee with ID {}", oId, eId);
 
        employeeService.confirm(oId, eId);
        return "Order confirmed for employee with order ID " + oId;
    }
}
// Service class managing Employee-related operations.
// Implements EmployeeDAO interface.
 
package com.shop.service;
 
import java.util.List;
import java.util.Optional;
import java.util.Set;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.shop.dao.EmployeeDAO;
import com.shop.entity.Employee;
import com.shop.entity.Order;
import com.shop.entity.Product;
import com.shop.entity.Stock;
import com.shop.repository.EmployeeRepository;
 
@Service
public class EmployeeService implements EmployeeDAO {
 
    // Autowired constructor for dependency injection.
    private final EmployeeRepository employeeRepository;
 
    @Autowired
    private OrderService orderService;
 
    @Autowired
    StockService stockService;
 
    @Autowired
    CartService cartService;
 
    @Autowired
    public EmployeeService(
            EmployeeRepository employeeRepository,
            OrderService orderService,
            StockService stockService,
            CartService cartService) {
        this.employeeRepository = employeeRepository;
        this.orderService = orderService;
        this.stockService = stockService;
        this.cartService = cartService;
    }
 
    // Method to get all employees.
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
 
    // Method to get an employee by ID.
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }
 
    // Method to save an employee.
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
 
    // Method to delete an employee by ID.
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
 
    // Method to confirm an order by updating order status, stock quantity, and employee orders.
    public void confirm(Long oId, Long eId) {
 
        // Updating order status to true.
        Order order = orderService.findById(oId).get();
        order.setOrderStatus(true);
        orderService.save(order);
 
        // Updating quantity of stock in stock entity.
        List<Product> prod = order.getCart().getProducts();
        for (Product p : prod) {
            int res = p.getStock().getQuantity() - order.getCart().getQuantity();
            Stock stock = stockService.getStockByProductId(p.getId()).get();
            stock.setQuantity(res);
            stockService.save(stock);
        }
 
        // Updating the employee's orders and orders accepted count.
        Employee employee = employeeRepository.findById(eId).get();
        employee.setOrdersAccepted(employee.getOrdersAccepted() + 1);
        Set<Order> s = employee.getOrders();
        s.add(order);
        employee.setOrders(s);
        employeeRepository.save(employee);
    }
 
    // Method to get orders accepted by an employee by ID.
    public Set<Order> getEmployeeOrderById(Long id) {
        return getEmployeeById(id).get().getOrders();
    }
 
	public Employee updateEmployee(Employee existingEmployee, Employee employee) {
		if (employee.getName() != null) {
	        existingEmployee.setName(employee.getName());
	    }
 
	    // Check and update the salary field
	    if (employee.getSalary() > 0) {
	        existingEmployee.setSalary(employee.getSalary());
	    }
 
	    // Check and update the position field
	    if (employee.getPosition() != null && !employee.getPosition().isBlank()) {
	        existingEmployee.setPosition(employee.getPosition());
	    }
	    if (employee.getOrdersAccepted() >=0) {
	        existingEmployee.setOrdersAccepted(employee.getOrdersAccepted());
	    }
	    return employeeRepository.save(existingEmployee);
	}
}
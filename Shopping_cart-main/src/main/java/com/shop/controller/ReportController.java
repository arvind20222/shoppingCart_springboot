package com.shop.controller;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.entity.Cart;
import com.shop.entity.Customer;
import com.shop.entity.Employee;
import com.shop.entity.Order;
import com.shop.entity.Product;
import com.shop.entity.Stock;
import com.shop.repository.OrderRepository;
import com.shop.repository.StockRepository;
import com.shop.service.CartService;
import com.shop.service.CustomerService;
import com.shop.service.EmployeeService;
import com.shop.service.ProductService;

@RestController
@RequestMapping("/")
public class ReportController {
    Logger logger = LogManager.getLogger(ReportController.class);

    @Autowired
    CustomerService customerService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    StockRepository stockService;

    @Autowired
    CartService cartService;

    @Autowired
    OrderRepository orderService;

    @Autowired
    ProductService productService;

    // Customer
    @GetMapping("/sortedcustomers")
    public List<Customer> getAllSortedCustomers() {
        logger.info("Fetching all customers and sorting by name.");
        List<Customer> customers = customerService.getAllCustomers();
        List<Customer> sortedByName = customers.stream().sorted(Comparator.comparing(Customer::getName))
                .collect(Collectors.toList());

        return sortedByName;
    }

    // Stock
    @GetMapping("/sortedStock")
    public List<Stock> getAllSortedStocks() {
        logger.info("Fetching all stocks and filtering by quantity greater than 10.");
        List<Stock> stocks = stockService.findAll();
        List<Stock> filteredStocks = stocks.stream().filter(stock -> stock.getQuantity() > 10)
                .collect(Collectors.toList());
        return filteredStocks;
    }

    // Orders
    @GetMapping("/latestOrders/{count}")
    public ResponseEntity<List<Order>> getLatestOrders(@PathVariable int count) {
        logger.info("Fetching latest orders.");
        List<Order> orders = orderService.findAll().stream().sorted(Comparator.comparing(Order::getoDate).reversed())
                .limit(count).collect(Collectors.toList());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    // Cart
    @GetMapping("/existsInCart/{customerId}/{productId}")
    public boolean doesProductExistInCustomerCart(@PathVariable Long customerId, @PathVariable Long productId) {
        logger.info("Checking if product with ID {} exists in the cart of customer with ID {}", productId, customerId);

        Cart customerCarts = cartService.getCartByCustomerId(customerId);

        return customerCarts.getProducts().stream().anyMatch(x->x.getId().equals(productId));
    }


    // Employees
    @GetMapping("/sortedEmployees")
    public List<Employee> getAllSortedEmployees() {
        logger.info("Fetching all employees and sorting by name.");
        List<Employee> employee = employeeService.getAllEmployees();
        List<Employee> sortedByName = employee.stream().sorted(Comparator.comparing(Employee::getName))
                .collect(Collectors.toList());

        return sortedByName;
    }

    // Products
    @GetMapping("/group-by-category")
    public Map<String, List<Product>> groupProductsByCategory() {
        logger.info("Grouping products by category.");
        Map<String, List<Product>> groupedProducts = productService.getAllProducts().stream()
                .collect(Collectors.groupingBy(Product::getCategory));
        return groupedProducts;
    }
}

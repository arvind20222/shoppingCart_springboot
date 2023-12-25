/*
 * Controller class for managing Product-related HTTP requests.
 * Implements methods to handle product operations, including creating, retrieving, updating, and deleting products.
 */

package com.shop.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.shop.entity.Employee;
import com.shop.entity.Product;
import com.shop.exception.EmployeeNotFoundException;
import com.shop.exception.ProductNotFoundException;
import com.shop.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    // Logger for logging messages
    Logger logger = LogManager.getLogger(ProductController.class);

    private final ProductService productService;

    // Constructor for injecting ProductService dependency
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // HTTP endpoint for creating a new product
    @PostMapping
    public ResponseEntity<Product> createProduct(@Validated @RequestBody Product product) {
        // Logging the creation of a new product
        logger.info("Creating a new product: {}", product);

        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }

    // HTTP endpoint for retrieving all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        // Logging the request to get all products
        logger.info("Retrieving all products");
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    // HTTP endpoint for retrieving a product by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) throws ProductNotFoundException {
        // Logging the request to get a product by ID
        logger.info("Retrieving product with ID {}", id);

        Optional<Product> product = productService.getProductById(id);
        product.orElseThrow(() -> new ProductNotFoundException("Product not found with ID " + id));

       // return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
         //       .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        return product.get();
    }
    
    
    /*@GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) throws EmployeeNotFoundException {
        // Logging the request to get an employee by ID
        logger.info("Retrieving employee with ID {}", id);

        Optional<Employee> employee = employeeService.getEmployeeById(id);
        employee.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID " + id));

        return employee.get();
    }*/
    
    

    // HTTP endpoint for updating a product by ID
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Validated @RequestBody Product updatedProduct) {
        // Logging the update of a product
        logger.info("Updating product with ID {} with new data: {}", id, updatedProduct);

        Product result = productService.updateProduct(id, updatedProduct);
        return result != null ?
                new ResponseEntity<>(result, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // HTTP endpoint for deleting a product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        // Logging the deletion of a product
        logger.info("Deleting product with ID {}", id);

        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

// Service class managing Product-related operations.
// Implements ProductDAO interface.

package com.shop.service;

import com.shop.dao.ProductDAO;
import com.shop.entity.Employee;
import com.shop.entity.Product;
import com.shop.repository.ProductRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductDAO {

    // Autowired constructor for dependency injection.
    private  ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Method to create a new Product.
    public Product createProduct(@Valid Product product) {
        // Additional validation or business logic can be added here.
        return productRepository.save(product);
    }

    // Method to retrieve all products.
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Method to retrieve a product by its ID.
    public Optional<Product> getProductById(Long id) {
        // Returning null if the product with the given ID is not found.
    	//Optional<Product> p = productRepository.findById(id);
    	//return p.orElse(null);
    	
    	return productRepository.findById(id);
    }

    
    /*public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }*/
    
    
    
    
    
    
    
    
    // Method to update a product by its ID.
    public Product updateProduct(Long id, @Valid Product updatedProduct) {
        // Additional validation or business logic can be added here.
        return productRepository.findById(id)
                .map(existingProduct -> {
                    // Updating product details.
                    existingProduct.setName(updatedProduct.getName());
                    existingProduct.setPrice(updatedProduct.getPrice());
                    existingProduct.setCategory(updatedProduct.getCategory());
                    existingProduct.setStock(updatedProduct.getStock());
                   // existingProduct.setCart(updatedProduct.getCart());
                    return productRepository.save(existingProduct);
                })
                .orElse(null); // Handle the case where the product with the given ID is not found.
    }

    // Method to delete a product by its ID.
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

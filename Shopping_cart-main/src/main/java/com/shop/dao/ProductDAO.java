package com.shop.dao;
import com.shop.entity.Product;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public interface ProductDAO {
    Product createProduct(@Valid Product product);
    List<Product> getAllProducts();
    //Product getProductById(Long id);
    Product updateProduct(Long id, @Valid Product updatedProduct);
    void deleteProduct(Long id);
}
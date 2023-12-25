package com.shop.tests;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import com.shop.dao.ProductDAO;
import com.shop.entity.Product;
import com.shop.repository.ProductRepository;
import com.shop.service.ProductService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testCreateProduct() {
        // Arrange
        Product product = new Product(); // Create a sample product
        when(productRepository.save(any(Product.class))).thenReturn(product);

        // Act
        Product result = productService.createProduct(product);

        // Assert
        verify(productRepository, times(1)).save(any(Product.class));
     
    }

    @Test
    public void testGetAllProducts() {
        // Arrange
        List<Product> productList = new ArrayList<>(); // Create a list of sample products
        when(productRepository.findAll()).thenReturn(productList);

        // Act
        List<Product> result = productService.getAllProducts();

        // Assert
        verify(productRepository, times(1)).findAll();
    }


    @Test
    public void testUpdateProduct() {
        // Arrange
        Long productId = 1L;
        Product existingProduct = new Product();
        Product updatedProduct = new Product();
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

        // Act
        Product result = productService.updateProduct(productId, updatedProduct);

        // Assert
        verify(productRepository, times(1)).findById(anyLong());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void testDeleteProduct() {
        // Arrange
        Long productId = 1L;

        // Act
        productService.deleteProduct(productId);

        // Assert
        verify(productRepository, times(1)).deleteById(anyLong());
    }
}

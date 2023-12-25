package com.shop.controllertests;

import com.shop.controller.ProductController;
import com.shop.entity.Product;
import com.shop.exception.ProductNotFoundException;
import com.shop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {
        // Arrange
        Product product = new Product();
        when(productService.createProduct(product)).thenReturn(product);

        // Act
        ResponseEntity<Product> responseEntity = productController.createProduct(product);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(product, responseEntity.getBody());
    }

    @Test
    void testGetAllProducts() {
        // Arrange
        List<Product> productList = new ArrayList<>();
        when(productService.getAllProducts()).thenReturn(productList);

        // Act
        ResponseEntity<List<Product>> responseEntity = productController.getAllProducts();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(productList, responseEntity.getBody());
    }

   /* @Test
    void testGetProductById() throws ProductNotFoundException {
        // Arrange
        Long productId = 1L;
        Product product = new Product();
        Optional<Product> optionalProduct = Optional.of(product);
        when(productService.getProductById(productId)).thenReturn(product);

        // Act
        ResponseEntity<Product> responseEntity = productController.getProductById(productId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(product, responseEntity.getBody());
    }*/
    





    @Test
    void testUpdateProduct() {
        // Arrange
        Long productId = 1L;
        Product updatedProduct = new Product();
        when(productService.updateProduct(productId, updatedProduct)).thenReturn(updatedProduct);

        // Act
        ResponseEntity<Product> responseEntity = productController.updateProduct(productId, updatedProduct);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedProduct, responseEntity.getBody());
    }

    @Test
    void testUpdateProduct_ProductNotFound() {
        // Arrange
        Long productId = 1L;
        Product updatedProduct = new Product();
        when(productService.updateProduct(productId, updatedProduct)).thenReturn(null);

        // Act
        ResponseEntity<Product> responseEntity = productController.updateProduct(productId, updatedProduct);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testDeleteProduct() {
        // Arrange
        Long productId = 1L;

        // Act
        ResponseEntity<Void> responseEntity = productController.deleteProduct(productId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(productService, times(1)).deleteProduct(productId);
    }
}

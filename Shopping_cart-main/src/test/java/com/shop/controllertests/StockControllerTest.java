package com.shop.controllertests;

import com.shop.controller.StockController;
import com.shop.entity.Stock;
import com.shop.exception.StockNotFoundException;
import com.shop.service.StockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class StockControllerTest {

    @Mock
    private StockService stockService;

    @InjectMocks
    private StockController stockController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getStockByProductId_ExistingProduct_ReturnsStock() {
        // Arrange
        Long productId = 1L;
        Stock mockStock = new Stock();
        when(stockService.getStockByProductId(productId)).thenReturn(Optional.of(mockStock));

        // Act
        ResponseEntity<Stock> responseEntity = stockController.getStockByProductId(productId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockStock, responseEntity.getBody());
    }

    @Test
    void getStockByProductId_NonExistingProduct_ThrowsException() {
        // Arrange
        Long productId = 2L;
        when(stockService.getStockByProductId(productId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(StockNotFoundException.class, () -> stockController.getStockByProductId(productId));
    }

    @Test
    void updateStock_ExistingProduct_ReturnsUpdatedStock() {
        // Arrange
        Long productId = 1L;
        Stock updatedStock = new Stock();
        when(stockService.updateStock(productId, updatedStock)).thenReturn(updatedStock);

        // Act
        ResponseEntity<Stock> responseEntity = stockController.updateStock(productId, updatedStock);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedStock, responseEntity.getBody());
    }

    @Test
    void updateStock_NonExistingProduct_ReturnsNotFound() {
        // Arrange
        Long productId = 2L;
        Stock updatedStock = new Stock();
        when(stockService.updateStock(productId, updatedStock)).thenReturn(null);

        // Act
        ResponseEntity<Stock> responseEntity = stockController.updateStock(productId, updatedStock);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void deleteStock_ExistingProduct_ReturnsNoContent() {
        // Arrange
        Long productId = 1L;

        // Act
        ResponseEntity<Void> responseEntity = stockController.deleteStock(productId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(stockService, times(1)).deleteStock(productId);
    }


}

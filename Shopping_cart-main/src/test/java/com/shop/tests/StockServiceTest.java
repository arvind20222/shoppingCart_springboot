package com.shop.tests;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.shop.entity.Product;
import com.shop.entity.Stock;
import com.shop.repository.ProductRepository;
import com.shop.repository.StockRepository;
import com.shop.service.StockService;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
 
class StockServiceTest {
 
    @Mock

    private StockRepository stockRepository;
 
    @Mock

    private ProductRepository productRepository;
 
    @InjectMocks

    private StockService stockService;
 
    private Validator validator;
 
    @BeforeEach

    void setUp() {

        MockitoAnnotations.openMocks(this);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        validator = factory.getValidator();

    }
 
    @Test

    void testCreateStock() {

        Long productId = 1L;

        Stock stock = new Stock(10, "Pune", "WareHouse-A");

        Product product = new Product();

        product.setId(productId);
 
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        when(stockRepository.save(any(Stock.class))).thenReturn(stock);
 
        Stock createdStock = stockService.createStock(productId, stock);
 
        assertNotNull(createdStock);

        assertEquals(stock.getQuantity(), createdStock.getQuantity());

        assertEquals(stock.getLocation(), createdStock.getLocation());

        assertEquals(stock.getSupplier(), createdStock.getSupplier());
 
        verify(productRepository, times(1)).findById(productId);

        verify(stockRepository, times(1)).save(any(Stock.class));

    }
 
    @Test

    void testGetStockByProductId() {

        Long productId = 1L;

        Stock stock = new Stock(10, "Delhi", "WareHouse-B");
 
        when(stockRepository.findById(productId)).thenReturn(Optional.of(stock));
 
        Optional<Stock> retrievedStock = stockService.getStockByProductId(productId);
 
        assertTrue(retrievedStock.isPresent());

        assertEquals(stock, retrievedStock.get());
 
        verify(stockRepository, times(1)).findById(productId);

    }
 
    @Test

    void testUpdateStock() {

        Long productId = 1L;

        Stock existingStock = new Stock(10, "Delhi", "Warehouse-B");

        Stock updatedStock = new Stock(20, "Hyderabad", "Warehouse-C");
 
        when(stockRepository.findById(productId)).thenReturn(Optional.of(existingStock));

        when(stockRepository.save(any(Stock.class))).thenReturn(updatedStock);
 
        Stock result = stockService.updateStock(productId, updatedStock);
 
        assertNotNull(result);

        assertEquals(updatedStock.getQuantity(), result.getQuantity());

        assertEquals(updatedStock.getLocation(), result.getLocation());

        assertEquals(updatedStock.getSupplier(), result.getSupplier());
 
        verify(stockRepository, times(1)).findById(productId);

        verify(stockRepository, times(1)).save(any(Stock.class));

    }
 
    @Test

    void testDeleteStock() {

        Long productId = 1L;
 
        stockService.deleteStock(productId);
 
        verify(stockRepository, times(1)).deleteById(productId);

    }
 
    @Test

    void testSave() {

        Stock stock = new Stock(10, "Delhi", "Warehouse-B");
 
        when(stockRepository.save(any(Stock.class))).thenReturn(stock);
 
        Stock savedStock = stockService.save(stock);
 
        assertNotNull(savedStock);

        assertEquals(stock, savedStock);
 
        verify(stockRepository, times(1)).save(any(Stock.class));

    }

}

 
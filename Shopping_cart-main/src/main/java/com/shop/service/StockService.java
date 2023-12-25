// Service class managing Stock-related operations.
// Implements StockDAO interface.

package com.shop.service;

import com.shop.dao.StockDAO;
import com.shop.entity.Employee;
import com.shop.entity.Product;
import com.shop.entity.Stock;
import com.shop.repository.ProductRepository;
import com.shop.repository.StockRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockService implements StockDAO {

    // Autowired constructor for dependency injection.
    private final StockRepository stockRepository;
    private final ProductRepository productRepository;

    @Autowired
    public StockService(StockRepository stockRepository, ProductRepository productRepository) {
        this.stockRepository = stockRepository;
        this.productRepository = productRepository;
    }

    // Method to create a new Stock for a given Product ID.
    public Stock createStock(Long productId, @Valid Stock stock) {
        Optional<Product> productOptional = productRepository.findById(productId);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            // Linking Stock with Product (commented out, as relationship not implemented).
            // stock.setProduct(product);
            return stockRepository.save(stock);
        } else {
            // Throw exception if Product with given ID is not found.
            throw new IllegalArgumentException("Product with ID " + productId + " not found.");
        }
    }

    // Method to retrieve Stock information by Product ID.
    public Optional<Stock> getStockByProductId(Long productId) {
        return stockRepository.findById(productId);
    }

    // Method to update Stock information for a given Product ID.
    public Stock updateStock(Long productId, @Valid Stock updatedStock) {
        Stock existingStock = getStockByProductId(productId).get();

        if (existingStock != null) {
            // Updating Stock details.
            existingStock.setQuantity(updatedStock.getQuantity());
            existingStock.setLocation(updatedStock.getLocation());
            existingStock.setSupplier(updatedStock.getSupplier());
            return stockRepository.save(existingStock);
        } else {
            // Return null if Stock with given Product ID is not found.
            return null;
        }
    }

    // Method to delete Stock information by Product ID.
    public void deleteStock(Long productId) {
        stockRepository.deleteById(productId);
    }
    
    // Method to save a Stock entity.
    public Stock save(Stock stock) {
		return stockRepository.save(stock);
	}
}

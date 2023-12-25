/*Controller class for managing Stock-related HTTP requests.
/ Implements methods to handle stock operations, including retrieving, updating, and deleting stock information.
/ Additional endpoints and functionality related to StockController can be added here.
 * */
 
package com.shop.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.shop.entity.Stock;
import com.shop.exception.StockNotFoundException;
import com.shop.service.StockService;

import java.util.Optional;

@RestController
@RequestMapping("/stocks")
public class StockController {
    // Logger for logging messages
    Logger logger = LogManager.getLogger(StockController.class);

    private final StockService stockService;

    // Constructor for injecting StockService dependency
    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    // HTTP endpoint for retrieving stock by product ID
    @GetMapping("/{productId}")
    public ResponseEntity<Stock> getStockByProductId(@PathVariable Long productId) {
        // Logging the request to get stock by product ID
        logger.info("Retrieving stock for product with ID {}", productId);

        Optional<Stock> stock = stockService.getStockByProductId(productId);
        stock.orElseThrow(() -> new StockNotFoundException("Stock not found for product with ID " + productId));

        return stock.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // HTTP endpoint for updating stock by product ID
    @PutMapping("/{productId}")
    public ResponseEntity<Stock> updateStock(@PathVariable Long productId, @Validated @RequestBody Stock updatedStock) {
        // Logging the update of stock by product ID
        logger.info("Updating stock for product with ID {} with new data: {}", productId, updatedStock);

        Stock result = stockService.updateStock(productId, updatedStock);
        return result != null ?
                new ResponseEntity<>(result, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // HTTP endpoint for deleting stock by product ID
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long productId) {
        // Logging the request to delete stock by product ID
        logger.info("Deleting stock for product with ID {}", productId);

        stockService.deleteStock(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

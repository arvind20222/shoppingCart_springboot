package com.shop.dao;
import com.shop.entity.Stock;

import jakarta.validation.Valid;

import java.util.Optional;

public interface StockDAO {
    Stock createStock(Long productId, @Valid Stock stock);
    Optional<Stock> getStockByProductId(Long productId);
    Stock updateStock(Long productId, @Valid Stock updatedStock);
    void deleteStock(Long productId);
}
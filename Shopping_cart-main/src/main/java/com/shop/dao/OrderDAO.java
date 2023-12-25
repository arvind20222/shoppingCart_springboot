package com.shop.dao;
import com.shop.entity.Order;

import java.util.List;

public interface OrderDAO {
    List<Order> getOrdersByCustomerId(Long cId);
    Order createOrder(Long cId);
}
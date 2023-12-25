package com.shop.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.shop.dao.OrderDAO;
import com.shop.entity.Cart;
import com.shop.entity.Order;
import com.shop.entity.Product;
import com.shop.repository.OrderRepository;
import com.shop.service.CartService;
import com.shop.service.OrderService;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CartService cartService;

    @InjectMocks
    private OrderService orderService;

    @Test
    void testGetOrdersByCustomerId() {
        Long customerId = 1L;
        List<Order> expectedOrders = Arrays.asList(new Order(), new Order());

        when(orderRepository.findAll()).thenReturn(expectedOrders);

        List<Order> actualOrders = orderService.getOrdersByCustomerId(customerId);

        assertEquals(expectedOrders, actualOrders);
        verify(orderRepository).findAll();
    }

    @Test
    void testCreateOrder() {
        Long customerId = 1L;

        Cart cart = new Cart();
        Product product = new Product();
        product.setPrice(100.0);
        cart.setProducts(Arrays.asList(product));
        cart.setQuantity(2);

        when(cartService.getCartByCustomerId(customerId)).thenReturn(cart);
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Order createdOrder = orderService.createOrder(customerId);

        assertEquals(cart, createdOrder.getCart());
        assertEquals(LocalDate.now(), createdOrder.getoDate());
        assertEquals(200.0, createdOrder.getoCost());

        verify(cartService).getCartByCustomerId(customerId);
        verify(orderRepository).save(any(Order.class));
    }

    @Test
    void testSaveOrder() {
        Order order = new Order();
        when(orderRepository.save(order)).thenReturn(order);

        Order savedOrder = orderService.save(order);

        assertEquals(order, savedOrder);
        verify(orderRepository).save(order);
    }

    @Test
    void testFindOrderById() {
        Long orderId = 1L;
        Order expectedOrder = new Order();

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(expectedOrder));

        Optional<Order> actualOrder = orderService.findById(orderId);

        assertEquals(expectedOrder, actualOrder.orElse(null));
        verify(orderRepository).findById(orderId);
    }
}


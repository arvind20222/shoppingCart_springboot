package com.shop.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.shop.entity.Cart;
import com.shop.entity.Customer;
import com.shop.entity.Product;
import com.shop.repository.CartRepository;
import com.shop.service.CartService;
import com.shop.service.CustomerService;
import com.shop.service.ProductService;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @Mock
    private CustomerService customerService;

    @Mock
    private ProductService productService;

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;

   
    @Test
    public void testAddToCart() {
        // Mock customer and product
        Long customerId = 1L;
        Long productId = 2L;
        int quantity = 3;

        Customer mockedCustomer = new Customer();
        mockedCustomer.setId(customerId);

        Product mockedProduct = new Product();
        mockedProduct.setId(productId);

        // Mock customerService behavior
        when(customerService.getCustomerById(customerId)).thenReturn(Optional.of(mockedCustomer));

        // Mock productService behavior
        when(productService.getProductById(productId)).thenReturn(Optional.of(mockedProduct)); // Use Optional.of()

        // Mock cartRepository behavior
        when(cartRepository.findByCustomer_Id(customerId)).thenReturn(null);

        // Perform the test
        Cart result = cartService.addToCart(productId, customerId, quantity);

        verify(cartRepository, times(1)).save(any(Cart.class));
    }


    @Test
    public void testGetCart() {
        // Mock cartRepository behavior
        when(cartRepository.findAll()).thenReturn(new ArrayList<>());

        // Perform the test
        List<Cart> result = cartService.getCart();


        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetCartByCustomerId() {
        // Mock cartRepository behavior
        Long customerId = 1L;
        when(cartRepository.findByCustomer_Id(customerId)).thenReturn(new Cart());

        // Perform the test
        Cart result = cartService.getCartByCustomerId(customerId);

        assertNotNull(result);
    }

    @Test
    public void testDeleteCartByCartId() {
        // Mock cartRepository behavior
        Long cartId = 1L;
        Cart mockedCart = new Cart();
        List<Product> mockedProducts = new ArrayList<>();
        mockedProducts.add(new Product());
        mockedCart.setProducts(mockedProducts);
        when(cartRepository.findById(cartId)).thenReturn(Optional.of(mockedCart));

        // Perform the test
        cartService.deleteCartByCartId(cartId);

        // Verify that the save method is called on the cartRepository
        verify(cartRepository, times(1)).save(any(Cart.class));

        assertEquals(0, mockedCart.getProducts().size());
        assertEquals(0, mockedCart.getQuantity());
    }
}


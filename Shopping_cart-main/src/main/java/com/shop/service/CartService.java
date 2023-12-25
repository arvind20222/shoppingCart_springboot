// Service class managing Cart-related operations.
// Implements CartDAO interface.

package com.shop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.dao.CartDAO;
import com.shop.entity.Cart;
import com.shop.entity.Customer;
import com.shop.entity.Product;
import com.shop.repository.CartRepository;
import com.shop.repository.CustomerRepository;
import com.shop.repository.ProductRepository;

@Service
public class CartService implements CartDAO {

    // Autowired fields for dependency injection.
    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartRepository cartRepository;

    // Method to add a product to the cart.
    public Cart addToCart(Long productId, Long customerId, int quantity) {
        // Find the customer by ID
        Optional<Customer> customerOptional = customerService.getCustomerById(customerId);

        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();

            // Find the product by ID
            Product product = productService.getProductById(productId).get();

            // Find the cart by customer ID
            Cart cart = cartRepository.findByCustomer_Id(customerId);

            if (cart == null) {
                // If the cart doesn't exist, create a new one
                cart = new Cart();
                cart.setCustomer(customer);
                List<Product> products = new ArrayList<>();
                products.add(product);
                cart.setProducts(products);
                cart.setQuantity(quantity);

            } else {
                // If the cart exists, add the product to it
                List<Product> products = cart.getProducts();
                products.add(product);
                cart.setCustomer(customer);
                cart.setProducts(products);
                cart.setQuantity(quantity);
            }
            Cart c = cartRepository.save(cart);
            return c;
        } else {
            // Handle the case where the customer is not found
            // You may throw an exception or return a special value indicating an error
            return null; // For simplicity, returning null here; consider better error handling
        }
    }

    // Method to get all carts.
    public List<Cart> getCart() {
        return cartRepository.findAll();
    }

    // Method to get a cart by customer ID.
    public Cart getCartByCustomerId(Long customerId) {
        // Assuming CartRepository has a method like findByCustomer_Id
        // return cartRepository.findByCustomer_Id(customerId);
        return cartRepository.findByCustomer_Id(customerId);
    }

    // Method to delete a cart by cart ID.
    public void deleteCartByCartId(Long cartId) {
        // Find the cart by ID
        Cart cart = cartRepository.findById(cartId).get();
        List<Product> products = cart.getProducts();
        products.clear();
        cart.setQuantity(0);
        cart.setProducts(products);
        cartRepository.save(cart);
    }
}

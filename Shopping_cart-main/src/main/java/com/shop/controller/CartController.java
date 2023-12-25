package com.shop.controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.shop.entity.Cart;
import com.shop.exception.CartItemNotFoundException;
import com.shop.service.CartService;
import java.util.List;
@RestController
@RequestMapping("/carts")
public class CartController {
    // Logger object for logging messages
    Logger logger = LogManager.getLogger(CartController.class);

    //injecting cartService object
    @Autowired
    private CartService cartService;

    // Setter method for injecting CartService dependency
    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    // HTTP endpoint for adding a product to the cart
    @PostMapping("{pId}/{cId}/{quantity}")
    public ResponseEntity<Cart> addToCart(@PathVariable Long pId,
                                          @PathVariable Long cId, @PathVariable int quantity) {
        // Logging the cart addition
        logger.info("Adding {} quantity of product with ID {} to the cart of customer with ID {}", quantity, pId, cId);

        Cart cart = cartService.addToCart(pId, cId, quantity);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    // HTTP endpoint for retrieving all cart items
    @GetMapping
    public List<Cart> getCart() {
        // Logging the request to retrieve the cart items
        logger.info("Retrieving all cart items");
        return cartService.getCart();
    }

    // HTTP endpoint for getting the cart by customer ID
    @GetMapping("/{customerId}")
    public Cart getCartByCustomerId(@PathVariable Long customerId) throws CartItemNotFoundException {
        // Logging the request to get the cart by customer ID
        logger.info("Retrieving cart for customer with ID {}", customerId);

        if (cartService.getCartByCustomerId(customerId) != null) {
            return cartService.getCartByCustomerId(customerId);
        } else {
            throw new CartItemNotFoundException("Cart not found with customer ID " + customerId);
        }
    }

    // HTTP endpoint for deleting the cart by cart ID
    @DeleteMapping("/{cartId}")
    public void deleteCartByCustomerId(@PathVariable Long cartId) {
        // Logging the request to delete the cart by cart ID
        logger.info("Deleting cart with ID {}", cartId);
        cartService.deleteCartByCartId(cartId);
    }
}

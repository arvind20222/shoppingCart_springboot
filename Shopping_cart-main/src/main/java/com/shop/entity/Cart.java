// This is a Java class representing the "Cart" entity in a Spring application.

package com.shop.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="carts")
public class Cart {

    // Default constructor
	public Cart() {
		super();
	}
	
	// Parameterized constructor with a List of products and a reference to the customer
	public Cart(Long id, int quantity, List<Product> products, Customer customer) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.products = products;
		this.customer = customer;
	}

	// Entity identifier
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Assuming you have a primary key for the Cart entity
   
    // Quantity of items in the cart
    private int quantity;
    
    // One-to-many relationship with Product entity
    @OneToMany
    private List<Product> products;
    
    // One-to-one relationship with Customer entity
    @OneToOne
    private Customer customer;

	// Getters and setters for the fields

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}

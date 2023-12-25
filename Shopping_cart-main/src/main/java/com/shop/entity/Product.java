// This is a Java class representing the "Product" entity in a Spring application.

package com.shop.entity;

import org.springframework.validation.annotation.Validated;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
@Validated
public class Product {

	// Default constructor
	public Product() {
		super();
	}

	// Parameterized constructor with Stock entity reference
	public Product(Long id, @NotBlank(message = "Name is required") String name,
			@Positive(message = "Price must be greater than 0") double price,
			@NotBlank(message = "Category is required") String category, Stock stock) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
		this.stock = stock;
	}

	// Alternate parameterized constructor without Stock entity reference
	public Product(Long id, @NotBlank(message = "Name is required") String name,
			@Positive(message = "Price must be greater than 0") double price,
			@NotBlank(message = "Category is required") String category) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
	}

	// Alternate parameterized constructor without ID
	public Product(@NotBlank(message = "Name is required") String name,
			@Positive(message = "Price must be greater than 0") double price,
			@NotBlank(message = "Category is required") String category) {
		super();
		this.name = name;
		this.price = price;
		this.category = category;
	}

	// Entity identifier
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Validation annotation for name
	@NotBlank(message = "Name is required")
	private String name;

	// Validation annotation for positive price
	@Positive(message = "Price must be greater than 0")
	private double price;

	// Validation annotation for category
	@NotBlank(message = "Category is required")
	private String category;

	// One-to-one relationship with Stock entity, cascade operation on all operations
	@OneToOne(cascade = CascadeType.ALL)
	// @JoinColumn(name="profuct_id",referencedColumnName = "id")
	private Stock stock;

	// Getters and setters for the fields

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}
}

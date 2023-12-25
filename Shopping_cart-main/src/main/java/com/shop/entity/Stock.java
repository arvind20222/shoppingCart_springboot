// This is a Java class representing the "Stock" entity in a Spring application.

package com.shop.entity;

import org.springframework.validation.annotation.Validated;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Validated
public class Stock {
    
    // Default constructor
    public Stock() {
		super();
	}

    // Parameterized constructor with validation annotations
	public Stock(@Min(value = 0, message = "Quantity must be greater than or equal to 0") int quantity,
			@NotBlank(message = "Location is required") String location,
			@NotBlank(message = "Supplier is required") String supplier) {
		super();
		this.quantity = quantity;
		this.location = location;
		this.supplier = supplier;
	}
	
	// Entity identifier
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  

	// Validation annotation for quantity
	@Min(value = 0, message = "Quantity must be greater than or equal to 0")
    private int quantity;

    // Validation annotation for location
    @NotBlank(message = "Location is required")
    private String location;

    // Validation annotation for supplier
    @NotBlank(message = "Supplier is required")
    private String supplier;

    // Getters and setters for the fields

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

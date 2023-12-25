// This is a Java class representing the "Customer" entity in a Spring application.

package com.shop.entity;

import java.util.*;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Validated
public class Customer {
	
	// Parameterized constructor with validation annotations
    public Customer(Long id,
			@NotBlank(message = "Name cannot be blank") @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters") String name,
			@NotBlank(message = "Address cannot be blank") String address,
			@Pattern(regexp = "\\d{10}", message = "Contact must be a 10-digit number") String contact) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.contact = contact;
	}

	// Default constructor
    public Customer() {
		super();
	}

	// Entity identifier
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Validation annotations for name
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    // Validation annotations for address
    @NotBlank(message = "Address cannot be blank")
    private String address;

    // Validation annotations for contact number using regex pattern
    @Pattern(regexp = "\\d{10}", message = "Contact must be a 10-digit number")
    private String contact;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", address=" + address + ", contact=" + contact + "]";
	}
}

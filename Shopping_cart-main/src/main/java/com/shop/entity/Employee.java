// This is a Java class representing the "Employee" entity in a Spring application.
 
package com.shop.entity;
 
import java.util.HashSet;
 
import java.util.Set;
 
import org.springframework.validation.annotation.Validated;
 
import com.fasterxml.jackson.annotation.JsonIgnore;
 
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
 
@Entity
@Table(name ="employees")
@Validated
public class Employee {
 
	// Ignoring this field in JSON serialization
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
 
	// Validation annotations for name
	@NotBlank(message = "Name cannot be blank")
	@Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
	private String name;
 
	// Validation annotations for salary
	@Positive(message = "Salary cannot be null")
	private double salary;
 
	// Validation annotations for position
	@NotBlank(message = "Position cannot be blank")
	private String position;
 
	// Default constructor
	public Employee() {
		super();
	}
 
	// Parameterized constructor
	public Employee(Long id, String name, double salary, String position) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.position = position;
	}
 
	// Ignoring this field in JSON serialization
	@JsonIgnore
	@ManyToMany
	private Set<Order> orders = new HashSet<>();
 
	// Number of orders accepted by the employee
	private int ordersAccepted;
 
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
 
	public double getSalary() {
		return salary;
	}
 
	public void setSalary(double salary) {
		this.salary = salary;
	}
 
	public String getPosition() {
		return position;
	}
 
	public void setPosition(String position) {
		this.position = position;
	}
 
	public Set<Order> getOrders() {
		return orders;
	}
 
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
 
	public int getOrdersAccepted() {
		return ordersAccepted;
	}
 
	public void setOrdersAccepted(int ordersAccepted) {
		this.ordersAccepted = ordersAccepted;
	}
}
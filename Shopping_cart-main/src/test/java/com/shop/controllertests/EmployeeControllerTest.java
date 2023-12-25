package com.shop.controllertests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.shop.controller.EmployeeController;
import com.shop.entity.Employee;
import com.shop.entity.Order;
import com.shop.exception.EmployeeNotFoundException;
import com.shop.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class EmployeeControllerTest {

    private EmployeeService employeeService;
    private EmployeeController employeeController;

    @BeforeEach
    public void setUp() {
        employeeService = mock(EmployeeService.class);
        employeeController = new EmployeeController(employeeService);
    }

    @Test
    public void getAllEmployees_ShouldReturnListOfEmployees() {
        List<Employee> mockEmployees = Arrays.asList(new Employee(), new Employee());
        when(employeeService.getAllEmployees()).thenReturn(mockEmployees);

        List<Employee> result = employeeController.getAllEmployees();

        assertEquals(mockEmployees.size(), result.size());
        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    public void getEmployeeById_ExistingId_ShouldReturnEmployee() throws EmployeeNotFoundException {
        Long employeeId = 1L;
        Employee mockEmployee = new Employee();
        when(employeeService.getEmployeeById(employeeId)).thenReturn(Optional.of(mockEmployee));

        Employee result = employeeController.getEmployeeById(employeeId);

        assertNotNull(result);
        verify(employeeService, times(1)).getEmployeeById(employeeId);
    }

    @Test
    public void getEmployeeById_NonExistingId_ShouldThrowException() throws EmployeeNotFoundException {
        Long employeeId = 1L;
        when(employeeService.getEmployeeById(employeeId)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> employeeController.getEmployeeById(employeeId));
        verify(employeeService, times(1)).getEmployeeById(employeeId);
    }

    @Test
    public void getEmployeeOrdersById_ExistingId_ShouldReturnEmployeeOrders() throws EmployeeNotFoundException {
        Long employeeId = 1L;
        Set<Order> mockOrders = Set.of(new Order(), new Order());
        when(employeeService.getEmployeeOrderById(employeeId)).thenReturn(mockOrders);

        Set<Order> result = employeeController.getEmployeeOrdersById(employeeId);

        assertEquals(mockOrders.size(), result.size());
        verify(employeeService, times(1)).getEmployeeOrderById(employeeId);
    }

}

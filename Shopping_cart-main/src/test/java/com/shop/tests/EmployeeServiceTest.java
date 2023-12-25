package com.shop.tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
 
import java.util.*;
 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
 
import com.shop.entity.Cart;
import com.shop.entity.Employee;
import com.shop.entity.Order;
import com.shop.entity.Product;
import com.shop.entity.Stock;
import com.shop.repository.EmployeeRepository;
import com.shop.service.CartService;
import com.shop.service.EmployeeService;
import com.shop.service.OrderService;
import com.shop.service.StockService;
 
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
 
    @Mock
    private EmployeeRepository employeeRepository;
 
    @Mock
    private OrderService orderService;
 
    @Mock
    private StockService stockService;
 
    @Mock
    private CartService cartService;
 
    @InjectMocks
    private EmployeeService employeeService;
 
    @Test
    public void testGetAllEmployees() {
        // Arrange
        List<Employee> employees = Arrays.asList(new Employee(), new Employee());
        when(employeeRepository.findAll()).thenReturn(employees);
 
        // Act
        List<Employee> result = employeeService.getAllEmployees();
 
        // Assert
        verify(employeeRepository).findAll();
        assertEquals(2, result.size());
    }
 
    @Test
    public void testGetEmployeeById() {
        // Arrange
        Long employeeId = 1L;
        Employee employee = new Employee();
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
 
        // Act
        Optional<Employee> result = employeeService.getEmployeeById(employeeId);
 
        // Assert
        verify(employeeRepository).findById(employeeId);
        assertTrue(result.isPresent());
        assertEquals(employee, result.get());
    }
 
    @Test
    public void testSaveEmployee() {
        // Arrange
        Employee employee = new Employee();
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
 
        // Act
        Employee result = employeeService.saveEmployee(employee);
 
        // Assert
        verify(employeeRepository).save(employee);
        assertEquals(employee, result);
    }
 
    @Test
    public void testDeleteEmployee() {
        // Arrange
        Long employeeId = 1L;
 
        // Act
        employeeService.deleteEmployee(employeeId);
 
        // Assert
        verify(employeeRepository).deleteById(employeeId);
    }
 
 
    @Test
    void testConfirm() {
        // Create test data
        Long orderId = 1L;
        Long employeeId = 2L;
 
        Order order = new Order();
        order.setoId(orderId);
 
        Product product = new Product();
        product.setId(1L);
 
        Stock stock = new Stock();
        stock.setId(1L);
        stock.setQuantity(10);
 
        product.setStock(stock);
 
        List<Product> products = new ArrayList<>();
        products.add(product);
 
        Cart cart = new Cart();
        cart.setId(1L);
        cart.setQuantity(5);
        cart.setProducts(products);
 
        order.setCart(cart);
 
        // Mock dependencies
        when(orderService.findById(orderId)).thenReturn(Optional.of(order));
        when(stockService.getStockByProductId(1L)).thenReturn(Optional.of(stock));
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(new Employee()));
 
        // Call the method to test
        employeeService.confirm(orderId, employeeId);
 
        // Verify that the orderService.save() method was called with the updated order
        verify(orderService, times(1)).save(order);
 
        // Verify that the stockService.save() method was called with the updated stock
        verify(stockService, times(1)).save(stock);
 
       
 
        // Verify that the employeeRepository.save() method was called with any Employee instance
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }
 
    @Test
    public void testGetEmployeeOrderById() {
        // Arrange
        Long employeeId = 1L;
        Employee employee = new Employee();
        Order order = new Order();
        employee.setOrders(new HashSet<>(Collections.singletonList(order)));
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
 
        // Act
        Set<Order> result = employeeService.getEmployeeOrderById(employeeId);
 
        // Assert
        verify(employeeRepository).findById(employeeId);
        assertEquals(1, result.size() );
        assertTrue(result.contains(order));
    }
}
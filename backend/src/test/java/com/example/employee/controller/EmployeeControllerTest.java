package com.example.employee.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;

class EmployeeControllerTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllEmployees() {
        Employee emp1 = new Employee(1L, "John", "john.doe@example.com", "Developer");
        Employee emp2 = new Employee(2L, "Jane", "jane.smith@example.com", "Designer");
        List<Employee> employees = Arrays.asList(emp1, emp2);

        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> response = employeeController.getAllEmployees();

        assertEquals(2, response.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void getEmployeeById() {
        Employee emp = new Employee(1L, "John", "john.doe@example.com", "Developer");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(emp));

        ResponseEntity<Employee> response = employeeController.getEmployeeById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("John", response.getBody().getFirstName());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void getEmployeeById_NotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Employee> response = employeeController.getEmployeeById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void createEmployee() {
        Employee emp = new Employee(null, "John", "john.doe@example.com", "Developer");
        Employee savedEmp = new Employee(1L, "John", "john.doe@example.com", "Developer");

        when(employeeRepository.save(emp)).thenReturn(savedEmp);

        Employee response = employeeController.createEmployee(emp);

        assertEquals(1L, response.getId());
        verify(employeeRepository, times(1)).save(emp);
    }

    @Test
    void updateEmployee() {
        Employee existingEmp = new Employee(1L, "John", "john.doe@example.com", "Developer");
        Employee updatedEmpDetails = new Employee(1L, "Johnathon", "johnathon.doe@example.com", "Senior Developer");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(existingEmp));
        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmpDetails);

        ResponseEntity<Employee> response = employeeController.updateEmployee(1L, updatedEmpDetails);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Johnathon", response.getBody().getFirstName());
        assertEquals("johnathon.doe@example.com", response.getBody().getEmail());
        assertEquals("Senior Developer", response.getBody().getPosition());
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void updateEmployee_NotFound() {
        Employee updatedEmpDetails = new Employee(1L, "Johnathon", "johnathon.doe@example.com", "Senior Developer");

        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Employee> response = employeeController.updateEmployee(1L, updatedEmpDetails);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, never()).save(any(Employee.class));
    }

    @Test
    void deleteEmployee() {
        Employee emp = new Employee(1L, "John", "john.doe@example.com", "Developer");

        when(employeeRepository.existsById(1L)).thenReturn(true);
        doNothing().when(employeeRepository).deleteById(1L);

        ResponseEntity<Void> response = employeeController.deleteEmployee(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(employeeRepository, times(1)).existsById(1L);
        verify(employeeRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteEmployee_NotFound() {
        when(employeeRepository.existsById(1L)).thenReturn(false);

        ResponseEntity<Void> response = employeeController.deleteEmployee(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(employeeRepository, times(1)).existsById(1L);
        verify(employeeRepository, never()).deleteById(anyLong());
    }
}
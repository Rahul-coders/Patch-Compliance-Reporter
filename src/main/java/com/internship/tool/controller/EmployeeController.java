package com.internship.tool.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.internship.tool.dto.EmployeeDTO;
import com.internship.tool.entity.Employee;
import com.internship.tool.service.EmployeeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(
        @Valid @RequestBody Employee employee) {

    Employee savedEmployee =
            employeeService.saveEmployee(employee);

    return new ResponseEntity<>(
            savedEmployee,
            HttpStatus.CREATED
    );
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {

    List<Employee> employees =
            employeeService.getAllEmployees();

    return new ResponseEntity<>(
            employees,
            HttpStatus.OK
    );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(
        @PathVariable Long id) {

    Employee employee =
            employeeService.getEmployeeById(id);

    return new ResponseEntity<>(
            employee,
            HttpStatus.OK
    );
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id,
                               @Valid @RequestBody Employee employee) {
    return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(
        @PathVariable Long id) {

    employeeService.deleteEmployee(id);

    return new ResponseEntity<>(
            "Employee deleted successfully",
            HttpStatus.OK
    );
    }

    @GetMapping("/dto/{id}")
    public EmployeeDTO getEmployeeDTO(@PathVariable Long id) {
        return employeeService.getEmployeeDTO(id);
    }
}
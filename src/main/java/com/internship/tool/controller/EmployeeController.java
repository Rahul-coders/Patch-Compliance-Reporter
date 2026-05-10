package com.internship.tool.controller;
import com.internship.tool.entity.Employee;
import com.internship.tool.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // SAVE Employee
    @PostMapping
    public Employee saveEmployee(@Valid @RequestBody Employee employee) {

        return employeeService.saveEmployee(employee);
    }

    // GET ALL Employees
    @GetMapping
    public List<Employee> getAllEmployees() {

        return employeeService.getAllEmployees();
    }

    // GET Employee By ID
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {

        return employeeService.getEmployeeById(id);
    }

    // UPDATE Employee
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id,
                                   @Valid @RequestBody Employee employee) {

        return employeeService.updateEmployee(id, employee);
    }

    // DELETE Employee
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {

        return employeeService.deleteEmployee(id);
    }
}
package com.internship.tool.controller;
import com.internship.tool.entity.Employee;
import com.internship.tool.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import com.internship.tool.dto.EmployeeDTO;
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // SAVE Employee
    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee){
        return ResponseEntity.ok(employeeService.saveEmployee(employee));
    }

    // GET ALL Employees
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {

        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    // GET Employee By ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){

        return ResponseEntity.ok(employeeService.getEmployeeById(id));
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
    @GetMapping("/employees/page")
    public Page<Employee> getEmployeesWithPagination(
        @RequestParam int page,
        @RequestParam int size) {

    return employeeService.getEmployeesWithPagination(page, size);
    }
    @GetMapping("/employees/sort/name")
    public List<Employee> getEmployeesSortedByName() {

    return employeeService.getEmployeesSortedByName();
    }
    
    @GetMapping("/dto/{id}")
    public EmployeeDTO getEmployeeDTO(@PathVariable Long id) {

    Employee employee = employeeService.getEmployeeById(id);

    EmployeeDTO dto = new EmployeeDTO();

    dto.setId(employee.getId());
    dto.setName(employee.getName());
    dto.setEmail(employee.getEmail());

    return dto;
    }

}
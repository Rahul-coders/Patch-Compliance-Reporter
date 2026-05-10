package com.internship.tool.service;
import com.internship.tool.dto.EmployeeDTO;
import com.internship.tool.entity.Employee;
import java.util.List;
import org.springframework.data.domain.Page;
public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    Employee updateEmployee(Long id, Employee employee);

    String deleteEmployee(Long id);

    Page<Employee> getEmployeesWithPagination(int page, int size);

    List<Employee> getEmployeesSortedByName();

    List<Employee> getEmployeesSortedBySalaryDesc();

    EmployeeDTO getEmployeeDTO(Long id);
}
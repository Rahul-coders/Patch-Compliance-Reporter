package com.internship.tool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.internship.tool.dto.EmployeeDTO;
import com.internship.tool.entity.Employee;
import com.internship.tool.repository.EmployeeRepository;
import org.springframework.data.domain.Sort;
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existingEmployee = employeeRepository.findById(id).orElse(null);

        if (existingEmployee != null) {
            existingEmployee.setName(employee.getName());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setSalary(employee.getSalary());

            return employeeRepository.save(existingEmployee);
        }

        return null;
    }

    @Override
    public String deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
        return "Employee deleted successfully";
    }

    @Override
    public EmployeeDTO getEmployeeDTO(Long id) {

        Employee employee = employeeRepository.findById(id).orElse(null);

        if (employee == null) {
            return null;
        }

        EmployeeDTO dto = new EmployeeDTO();

        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setSalary(employee.getSalary());

        return dto;
    }
    @Override
    public List<Employee> getEmployeesByDepartment(String department) {

    return employeeRepository.findByDepartment(department);
    }
    @Override
    public Page<Employee> getEmployeesWithPagination(int page, int size) {
    return employeeRepository.findAll(PageRequest.of(page, size));
    }@Override
    public List<Employee> getEmployeesSortedByName() {
    return employeeRepository.findAll(Sort.by("name"));
    }

    @Override
    public List<Employee> getEmployeesSortedBySalaryDesc() {
    return employeeRepository.findAll(
            Sort.by(Sort.Direction.DESC, "salary"));
    }
}
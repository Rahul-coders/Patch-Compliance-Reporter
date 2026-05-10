package com.internship.tool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internship.tool.dto.EmployeeDTO;
import com.internship.tool.entity.Employee;
import com.internship.tool.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    
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

            return employeeRepository.save(existingEmployee);
        }

        return null;
    }

    @Override
    public String deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
        return "Employee deleted successfully";
    }

    
    public EmployeeDTO getEmployeeDTO(Long id) {

        Employee employee = employeeRepository.findById(id).orElse(null);

        if (employee == null) {
            return null;
        }

        EmployeeDTO dto = new EmployeeDTO();

        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());

        return dto;
    }
    @Override
    public List<Employee> getEmployeesSortedBySalaryDesc() {
    return employeeRepository.findAll()
            .stream()
            .sorted((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()))
            .toList();
    }
    @Override
    public List<Employee> getEmployeesSortedByName() {
    return employeeRepository.findAll()
            .stream()
            .sorted((e1, e2) -> e1.getName().compareTo(e2.getName()))
            .toList();
    }
    @Override
    public org.springframework.data.domain.Page<Employee> getEmployeesWithPagination(int page, int size) {

    org.springframework.data.domain.Pageable pageable =
            org.springframework.data.domain.PageRequest.of(page, size);

    return employeeRepository.findAll(pageable);
    }
}
package com.internship.tool.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.internship.tool.entity.Employee;
import com.internship.tool.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger logger =
    LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Autowired
    private EmployeeRepository repository;

    @Override
    public Employee saveEmployee(Employee employee) {
        logger.info("Saving employee: {}", employee.getName());
        return repository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
    logger.info("Fetching employee with id: {}", id);
        return repository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {

        return repository.findById(id).orElse(null);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {

        Employee existingEmployee =
                repository.findById(id).orElse(null);

        if (existingEmployee != null) {

            existingEmployee.setName(employee.getName());
            existingEmployee.setDepartment(employee.getDepartment());
            existingEmployee.setSalary(employee.getSalary());

            return repository.save(existingEmployee);
        }

        return null;
    }

    @Override
    public String deleteEmployee(Long id) {
        logger.info("Deleting employee with id: {}", id);
        repository.deleteById(id);

        return "Employee deleted successfully";
    }
}
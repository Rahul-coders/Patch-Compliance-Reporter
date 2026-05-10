package com.internship.tool.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.internship.tool.entity.Employee;
import com.internship.tool.repository.EmployeeRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.internship.tool.exception.ResourceNotFoundException;
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
    logger.info("Fetching all employees");
        return repository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {

    Optional<Employee> optionalEmployee = repository.findById(id);

    if(optionalEmployee.isPresent()) {
        return optionalEmployee.get();
    } else {
        throw new ResourceNotFoundException("Employee not found with id: " + id);
    }
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

    Employee employee = repository.findById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException("Employee not found with id: " + id));

    repository.delete(employee);

    return "Employee deleted successfully";
    }
}
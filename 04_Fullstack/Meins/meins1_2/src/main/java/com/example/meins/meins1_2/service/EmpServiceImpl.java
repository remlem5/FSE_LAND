package com.example.meins.meins1_2.service;

import com.example.meins.meins1_2.entity.Employee;
import com.example.meins.meins1_2.exception.EmployeeNotFoundException;
import com.example.meins.meins1_2.repos.EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpRepo empRepo;

    @Override
    public Employee saveEmployee(Employee employee) {
        return empRepo.save(employee);
    }

    @Override
    public List<Employee> fetchEmployeeList() {
        return empRepo.findAll();
    }

    @Override
    public Employee fetchEmployeeById(Long empId) throws EmployeeNotFoundException {
        Optional<Employee> employee = empRepo.findById(empId);
        if (!employee.isPresent()) {
            throw new EmployeeNotFoundException("Employee Not Found");
        }
        return employee.get();
    }

    @Override
    public void deleteEmployeeById(Long empId) {
        empRepo.deleteById(empId);
    }

    @Override
    public Employee fetchEmployeeByName(String name) {
        if (empRepo.findEmployeeByFirstNameIgnoreCase(name) != null)
            return empRepo.findEmployeeByFirstNameIgnoreCase(name);
        else
            return empRepo.findEmployeeByLastNameIgnoreCase(name);
    }

}

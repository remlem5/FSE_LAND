package com.example.meins.meins1_2.service;

import com.example.meins.meins1_2.entity.Employee;
import com.example.meins.meins1_2.exception.EmployeeNotFoundException;

import java.util.List;

public interface EmpService {
    public Employee saveEmployee(Employee employee);

    public List<Employee> fetchEmployeeList();

    public Employee fetchEmployeeById(Long empId) throws EmployeeNotFoundException;

    public void deleteEmployeeById(Long empId);

    Employee fetchEmployeeByName(String name);
}

package com.example.meins.meins1_3.service;

import com.example.meins.meins1_3.entity.Employee;
import com.example.meins.meins1_3.exception.EmployeeNotFoundException;

import java.util.List;

public interface EmpService {
    public Employee saveEmployee(Employee employee);

    public List<Employee> fetchEmployeeList();

    public Employee fetchEmployeeById(Long empId) throws EmployeeNotFoundException;

    public void deleteEmployeeById(Long empId);

    Employee fetchEmployeeByName(String name);

    String updateCompId(Long empId, Long compId) throws EmployeeNotFoundException;
}

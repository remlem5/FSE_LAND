package com.example.meins.meins1_4.service;

import com.example.meins.meins1_4.entity.Department;
import com.example.meins.meins1_4.entity.Employee;
import com.example.meins.meins1_4.exception.EmployeeNotFoundException;

import java.util.List;

public interface EmpService {
    Employee saveEmployee(Employee employee);

    //Employee saveEmployeeWithCompany(Employee employee, long compId);

    Employee saveEmployeeWithDepartment(Employee employee, long compId);

    List<Employee> fetchEmployeeList();

    //List<Employee> fetchEmployeeListByCompany(long compId);

    List<Employee> fetchEmployeeListByDepartment(long depId);

    Employee fetchEmployeeById(Long empId) throws EmployeeNotFoundException;

    void deleteEmployeeById(Long empId);

    Employee fetchEmployeeByName(String name);

    //String updateCompId(Long empId, Long compId) throws EmployeeNotFoundException;

    String updateDepId(Long empId, Long depId);
}

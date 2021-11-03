package com.example.meins.meins1.controller;

import com.example.meins.meins1.entity.Employee;
import com.example.meins.meins1.exception.EmployeeNotFoundException;
import com.example.meins.meins1.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    @PostMapping("/employees")
    public Employee saveEmployee(@Valid @RequestBody Employee employee){
        return empService.saveEmployee(employee);
    }

    @GetMapping("/employees")
    public List<Employee> fetchEmployeeList(){
        return empService.fetchEmployeeList();
    }

    @GetMapping("/employees/{id}")
    public Employee fetchEmployeeById(@PathVariable("id") Long empId) throws EmployeeNotFoundException{
        return empService.fetchEmployeeById(empId);
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployeeById(@PathVariable("id") Long empId){
        empService.deleteEmployeeById(empId);
        return "Employee deleted";
    }

    @GetMapping("/employees/name/{name}")
    public Employee fetchEmployeeByName(@PathVariable("name") String empName){
        return empService.fetchEmployeeByName(empName);
    }

}

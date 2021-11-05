package com.example.meins.meins1_4.controller;

import com.example.meins.meins1_4.entity.Employee;
import com.example.meins.meins1_4.exception.CompanyNotFoundException;
import com.example.meins.meins1_4.exception.EmployeeNotFoundException;
import com.example.meins.meins1_4.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapAutoConfiguration;
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

    /*@PostMapping("/employees/{compId}")
    public Employee saveEmployeeWithCompany(@Valid @RequestBody Employee employee,
                                            @PathVariable(name = "compId") Long compId){
        return empService.saveEmployeeWithCompany(employee, compId);
    }*/

    @PostMapping("/employees/{depId}")
    public Employee saveEmployeeWithDepartment(@Valid @RequestBody Employee employee,
                                            @PathVariable(name = "depId") Long depId){
        return empService.saveEmployeeWithDepartment(employee, depId);
    }

    @GetMapping("/employees")
    public List<Employee> fetchEmployeeList(){
        return empService.fetchEmployeeList();
    }

    /*@GetMapping("/employees/compId/{compId}")
    public List<Employee> fetchEmployeeListByCompId(@PathVariable(name = "compId") long compId){
        return empService.fetchEmployeeListByCompany(compId);
    }*/

    @GetMapping("/employees/depId/{depId}")
    public List<Employee> fetchEmployeeListByDepId(@PathVariable(name = "depId") long depId){
        return empService.fetchEmployeeListByDepartment(depId);
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

    /*@PostMapping("/employees/{empId}/{compId}")
    public String updateCompId(@PathVariable("empId") Long empId, @PathVariable("compId") Long compId) throws EmployeeNotFoundException, CompanyNotFoundException {
        return empService.updateCompId(empId, compId);
    }*/

    @PostMapping("/employees/{empId}/{depId}")
    public String updateCompId(@PathVariable("empId") Long empId, @PathVariable("depId") Long depId) throws EmployeeNotFoundException, CompanyNotFoundException {
        return empService.updateDepId(empId, depId);
    }

}

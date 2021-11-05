package com.example.meins.meins1_4.service;

import com.example.meins.meins1_4.entity.Company;
import com.example.meins.meins1_4.entity.Department;
import com.example.meins.meins1_4.entity.Employee;
import com.example.meins.meins1_4.exception.CompanyNotFoundException;
import com.example.meins.meins1_4.exception.EmployeeNotFoundException;
import com.example.meins.meins1_4.repos.CompRepo;
import com.example.meins.meins1_4.repos.DepRepo;
import com.example.meins.meins1_4.repos.EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpRepo empRepo;

    /*@Autowired
    private CompRepo compRepo;*/

    @Autowired
    private DepRepo depRepo;

    @Override
    public Employee saveEmployee(Employee employee) {
        return empRepo.save(employee);
    }

    /*@Override
    public Employee saveEmployeeWithCompany(Employee employee, long compId){
        employee.company = compRepo.findCompanyByCompId(compId);
        return empRepo.save(employee);
    }*/

    @Override
    public Employee saveEmployeeWithDepartment(Employee employee, long depId){
        employee.department = depRepo.findDepartmentByDepId(depId);
        return empRepo.save(employee);
    }

    @Override
    public List<Employee> fetchEmployeeList() {
        return empRepo.findAll();
    }

    /*@Override
    public List<Employee> fetchEmployeeListByCompany(long compId) {
        Company comp = compRepo.findCompanyByCompId(compId);
        return empRepo.findEmployeesByCompany(comp);
    }*/

    @Override
    public List<Employee> fetchEmployeeListByDepartment(long depId) {
        Department dep = depRepo.findDepartmentByDepId(depId);
        return empRepo.findEmployeesByDepartment(dep);
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

    /*@Override
    public String updateCompId(Long empId, Long compId) {
        Employee emp = empRepo.findEmployeeByEmpId(empId);
        emp.company = compRepo.findCompanyByCompId(compId);
        empRepo.save(emp);
        return "Company for Employee: "+ emp.empId + " updated!";
    }*/

    @Override
    public String updateDepId(Long empId, Long depId) {
        Employee emp = empRepo.findEmployeeByEmpId(empId);
        emp.department = depRepo.findDepartmentByDepId(depId);
        empRepo.save(emp);
        return "Department for Employee: "+ emp.empId + " updated!";
    }

}

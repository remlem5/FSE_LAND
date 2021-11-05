package com.example.meins.meins1_4.repos;

import com.example.meins.meins1_4.entity.Company;
import com.example.meins.meins1_4.entity.Department;
import com.example.meins.meins1_4.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpRepo extends JpaRepository<Employee, Long> {

    public Employee findEmployeeByEmpId(long id);

    public Employee findEmployeeByFirstNameIgnoreCase(String name);

    public Employee findEmployeeByLastNameIgnoreCase(String name);

    //List<Employee> findEmployeesByCompany(Company company);

    List<Employee> findEmployeesByDepartment(Department department);

}

package com.example.meins.meins1.repos;

import com.example.meins.meins1.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpRepo extends JpaRepository<Employee, Long> {

    public Employee findEmployeeByEmpId(long id);

    public Employee findEmployeeByFirstNameIgnoreCase(String name);

    public Employee findEmployeeByLastNameIgnoreCase(String name);
}

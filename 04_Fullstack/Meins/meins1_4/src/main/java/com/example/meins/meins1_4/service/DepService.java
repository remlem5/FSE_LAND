package com.example.meins.meins1_4.service;

import com.example.meins.meins1_4.entity.Company;
import com.example.meins.meins1_4.entity.Department;
import com.example.meins.meins1_4.entity.Employee;
import com.example.meins.meins1_4.exception.CompanyNotFoundException;
import com.example.meins.meins1_4.exception.DepartmentNotFoundException;

import java.util.List;

public interface DepService {

    Department saveDepartment(Department department);

    Department saveDepartmentWithCompany(Department department, long compId);

    List<Department> fetchDepartmentList();

    Department fetchDepartmentById(Long depId) throws DepartmentNotFoundException;

    void deleteDepartmentById(Long depId);

    Department fetchDepartmentByName(String name);

}

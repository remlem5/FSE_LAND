package com.itkolleg.Aufgabe3.service;

import com.itkolleg.Aufgabe3.entitiy.Department;
import com.itkolleg.Aufgabe3.exception.DepartmentNotFoundException;

import java.util.List;

public interface DepartmentService {
    public Department saveDepartment(Department department);

    public List<Department> fetchDepartmentList();

    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException;

    public void deleteDepartmentByID(Long departmentId);

    public Department updateDepartment(Long departmentId, Department department);

    Department fetchDepartmentByName(String depName);
}

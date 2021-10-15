package com.itkolleg.Aufgabe3.service;

import com.itkolleg.Aufgabe3.entitiy.Department;

import java.util.List;

public interface DepartmentService {
    public Department saveDepartment(Department department);

    public List<Department> fetchDepartmentList();

    public Department fetchDepartmentById(Long departmentId);

    public void deleteDepartmentByID(Long departmentId);

    public Department updateDepartment(Long departmentId, Department department);
}

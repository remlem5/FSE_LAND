package com.itkolleg.Aufgabe3.service;

import com.itkolleg.Aufgabe3.entitiy.Department;
import com.itkolleg.Aufgabe3.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }
}

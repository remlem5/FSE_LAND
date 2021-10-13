package com.itkolleg.Aufgabe3.controller;

import com.itkolleg.Aufgabe3.entitiy.Department;
import com.itkolleg.Aufgabe3.service.DepartmentService;
import com.itkolleg.Aufgabe3.service.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/departments")
    public Department saveDepartment(@RequestBody Department department){
        return departmentService.saveDepartment(department);
    }

}

package com.example.meins.meins1_4.controller;

import com.example.meins.meins1_4.entity.Department;
import com.example.meins.meins1_4.entity.Employee;
import com.example.meins.meins1_4.exception.DepartmentNotFoundException;
import com.example.meins.meins1_4.service.DepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DepController {

    @Autowired
    private DepService depService;

    @PostMapping("/departments")
    public Department saveDepartment(@Valid @RequestBody Department department){
        return depService.saveDepartment(department);
    }

    @PostMapping("/departments/{compId}")
    public Department saveDepartmentWithCompany(@Valid @RequestBody Department department,
                                            @PathVariable(name = "compId") Long compId){
        return depService.saveDepartmentWithCompany(department, compId);
    }

    @GetMapping("/departments")
    public List<Department> fetchDepartmentList(){
        return depService.fetchDepartmentList();
    }

    @GetMapping("/departments/{id}")
    public Department fetchDepartmentById(@PathVariable(name = "id") long depId) throws DepartmentNotFoundException {
        return depService.fetchDepartmentById(depId);
    }

    @DeleteMapping("/departments/{id}")
    public String deleteDepartmentById(@PathVariable(name = "id") long depId){
        depService.deleteDepartmentById(depId);
        return "Department deleted";
    }

    @GetMapping("/departments/name/{name}")
    public Department fetchDepartmentByName(@PathVariable(name = "name") String name){
        return depService.fetchDepartmentByName(name);
    }

}

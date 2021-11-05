package com.example.meins.meins1_4.service;

import com.example.meins.meins1_4.entity.Company;
import com.example.meins.meins1_4.entity.Department;
import com.example.meins.meins1_4.entity.Employee;
import com.example.meins.meins1_4.exception.DepartmentNotFoundException;
import com.example.meins.meins1_4.repos.CompRepo;
import com.example.meins.meins1_4.repos.DepRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepServiceImpl implements DepService{

    @Autowired
    private DepRepo depRepo;

    @Autowired
    private CompRepo compRepo;

    @Override
    public Department saveDepartment(Department department) {
        return depRepo.save(department);
    }

    @Override
    public Department saveDepartmentWithCompany(Department department, long compId){
        department.company = compRepo.findCompanyByCompId(compId);
        return depRepo.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return depRepo.findAll();
    }

    @Override
    public Department fetchDepartmentById(Long depId) throws DepartmentNotFoundException {
        return depRepo.findDepartmentByDepId(depId);
    }

    @Override
    public void deleteDepartmentById(Long depId) {
        depRepo.deleteById(depId);
    }

    @Override
    public Department fetchDepartmentByName(String name) {
        return depRepo.findDepartmentByDepName(name);
    }
}

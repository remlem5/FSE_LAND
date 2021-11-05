package com.example.meins.meins1_4.service;

import com.example.meins.meins1_4.entity.Company;
import com.example.meins.meins1_4.entity.Department;
import com.example.meins.meins1_4.entity.Employee;
import com.example.meins.meins1_4.exception.CompanyNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CompService {

    Company saveCompany(Company company);

    List<Company> fetchCompanyList();

    Company fetchCompanyById(Long compId) throws CompanyNotFoundException;

    void deleteCompanyById(Long compId);

    Company fetchCompanyByName(String name);
}

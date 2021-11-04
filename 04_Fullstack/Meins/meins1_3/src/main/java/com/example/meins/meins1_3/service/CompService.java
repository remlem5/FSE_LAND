package com.example.meins.meins1_3.service;

import com.example.meins.meins1_3.entity.Company;
import com.example.meins.meins1_3.exception.CompanyNotFoundException;

import java.util.List;

public interface CompService {

    Company saveCompany(Company company);

    List<Company> fetchCompanyList();

    Company fetchCompanyById(Long compId) throws CompanyNotFoundException;

    void deleteCompanyById(Long compId);

    Company fetchCompanyByName(String name);
}

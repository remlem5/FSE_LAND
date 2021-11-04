package com.example.meins.meins1_2.service;

import com.example.meins.meins1_2.entity.Company;
import com.example.meins.meins1_2.exception.CompanyNotFoundException;
import com.example.meins.meins1_2.repos.CompRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompServiceImpl implements CompService {

    @Autowired
    private CompRepo compRepo;

    @Override
    public Company saveCompany(Company company) {
        return compRepo.save(company);
    }

    @Override
    public List<Company> fetchCompanyList() {
        return compRepo.findAll();
    }

    @Override
    public Company fetchCompanyById(Long compId) throws CompanyNotFoundException {
        Optional<Company> company = compRepo.findById(compId);
        if(!company.isPresent()){
            throw new CompanyNotFoundException("Company Not Found");
        }
        return company.get();
    }

    @Override
    public void deleteCompanyById(Long compId) {
        compRepo.deleteById(compId);
    }

    @Override
    public Company fetchCompanyByName(String name) {
        return compRepo.findCompanyByCompName(name);
    }
}

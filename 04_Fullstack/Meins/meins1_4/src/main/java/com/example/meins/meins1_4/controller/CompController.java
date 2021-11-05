package com.example.meins.meins1_4.controller;

import com.example.meins.meins1_4.entity.Company;
import com.example.meins.meins1_4.entity.Employee;
import com.example.meins.meins1_4.exception.CompanyNotFoundException;
import com.example.meins.meins1_4.service.CompService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CompController {

    @Autowired
    private CompService compService;

    @PostMapping("/companies")
    public Company saveCompany(@Valid @RequestBody Company company){
        return compService.saveCompany(company);
    }

    @GetMapping("/companies")
    public List<Company> fetchCompanyList(){
        return compService.fetchCompanyList();
    }

    @GetMapping("/companies/{id}")
    public Company fetchCompanyById(@PathVariable("id") Long compId) throws CompanyNotFoundException {
        return compService.fetchCompanyById(compId);
    }

    @DeleteMapping("/companies/{id}")
    public String deleteCompanyById(@PathVariable("id") Long compId){
        compService.deleteCompanyById(compId);
        return "Company deleted";
    }

    @GetMapping("/companies/name/{name}")
    public Company fetchCompanyByName(@PathVariable("name") String compName){
        return compService.fetchCompanyByName(compName);
    }

}

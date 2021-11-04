package com.example.meins.meins1_2.controller;

import com.example.meins.meins1_2.entity.Company;
import com.example.meins.meins1_2.entity.Employee;
import com.example.meins.meins1_2.service.CompService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CompController {

    @Autowired
    private CompService compService;

    @PostMapping("/companies")
    public Company saveEmployee(@Valid @RequestBody Company company){
        return compService.saveCompany(company);
    }

    @GetMapping("/companies")
    public List<Company> fetchCompanyList(){
        return compService.fetchCompanyList();
    }

    @DeleteMapping("/companies/{id}")
    public String deleteCompnayById(@PathVariable("id") Long compId){
        compService.deleteCompanyById(compId);
        return "Company deleted";
    }

    @GetMapping("/companies/name/{name}")
    public Company fetchCompanyBaName(@PathVariable("name") String compName){
        return compService.fetchCompanyByName(compName);
    }

}

package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Exception.IllegalOperationException;
import com.thoughtworks.springbootemployee.Exception.NoSuchDataException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping(params = {"page", "pageSize"})
    public Page<Company> getCompaniesInPage(@RequestParam(value = "page") Integer page,
                                            @RequestParam(value = "pageSize") Integer pageSize) {
        return companyService.getCompaniesPage(page, pageSize);
    }

    @GetMapping
    public List<Company> getCompanies() {
        return companyService.getCompanies();
    }

    @GetMapping("/{id}")
    public Company getCompany(@PathVariable int id) {
        return companyService.getCompany(id);
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeeFromCompany(@PathVariable int id) {
        return companyService.getCompany(id).getEmployees();
    }

    @PostMapping
    public Company addCompany(@RequestBody Company company) {
        return companyService.createCompany(company);
    }

    @PutMapping("/{id}")
    public Company updateCompany(@PathVariable("id") int id, @RequestBody Company company) throws NoSuchDataException, IllegalOperationException {
        return companyService.updateCompany(id, company);
    }

    @DeleteMapping("{id}")
    public void deleteCompany(@PathVariable("id") int id) throws NoSuchDataException {
        companyService.deleteCompany(id);
    }

}

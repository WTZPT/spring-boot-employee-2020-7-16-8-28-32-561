package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.utils.PageUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private static final String ADD_SUCCESS = "Add Success";
    private static final String UPDATE_SUCCESS = "update success";
    private static final String DELETE_SUCCESS = "delete success";

    @GetMapping
    public List<Company> getCompanies(@RequestParam(value = "page", required = false) Integer page,
                                      @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (Objects.nonNull(page) && Objects.nonNull(pageSize)) {
            //// TODO: 7/29/2020  
            return new PageUtils<Company>().getPage(getCompanyList(), page, pageSize);
        }
        return getCompanyList();
    }

    @GetMapping("/{id}")
    public Company getCompany(@PathVariable int id) {
        List<Company> companies = getCompanyList();
        return companies.stream().filter(company -> company.getId() == id).findFirst().orElse(null);
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeeFromCompany(@PathVariable String id) {
        Company company = new Company(1, "xxx", 2, getEmployeeList());
        return company.getEmployees();
    }

    @PostMapping
    public String addCompany(@RequestBody Company company) {
        //// TODO: 7/29/2020  
        return ADD_SUCCESS;
    }

    @PutMapping("/{id}")
    public String updateCompany(@PathVariable("id") String id) {
        //// TODO: 7/29/2020
        return UPDATE_SUCCESS;
    }

    @DeleteMapping("{id}")
    public String deleteCompany(@PathVariable("id") String id) {
        //// TODO: 7/29/2020  
        return DELETE_SUCCESS;
    }

    private List<Company> getCompanyList() {
        List<Company> companies = new ArrayList<>();
        companies.add(new Company(1, "a", 0, getEmployeeList()));
        companies.add(new Company(2, "b", 0, getEmployeeList()));
        companies.add(new Company(3, "c", 0, getEmployeeList()));
        companies.add(new Company(4, "d", 0, getEmployeeList()));
        return companies;
    }

    private List<Employee> getEmployeeList() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "q", 18, "male", new BigDecimal(3000)));
        employees.add(new Employee(2, "w", 18, "male", new BigDecimal(3000)));
        return employees;
    }

}
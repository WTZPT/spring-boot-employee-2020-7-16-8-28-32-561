package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Exception.IllegalOperationException;
import com.thoughtworks.springbootemployee.Exception.NoSuchDataException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(params = {"page", "pageSize"})
    public Page<Employee> getEmployeePage(@RequestParam(value = "page") Integer page,
                                          @RequestParam(value = "pageSize") Integer pageSize) {
        return employeeService.queryEmployeesByPage(page, pageSize);
    }

    @GetMapping(params = {"gender"})
    public List<Employee> getEmployeesByGender(@RequestParam(value = "gender") String gender) {
        return employeeService.queryEmployeesByGender(gender);
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.queryEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable("id") int id) {
        return employeeService.queryEmployee(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee) throws NoSuchDataException, IllegalOperationException {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") int id) throws NoSuchDataException {
        employeeService.deleteEmployee(id);
    }

}

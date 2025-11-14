package com.departmentapp.controller;

import com.departmentapp.service.DepartmentEmployeeService;
import com.departmentapp.entities.EmployeeEntity;
import com.departmentapp.service.DepartmentEmployeeAdminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department-employee")
public class DepartmentEmployeeController {
    @Autowired
    private DepartmentEmployeeService departmentEmployeeService;

    @Autowired
    private DepartmentEmployeeAdminService departmentEmployeeAdminService;

    @GetMapping("/employees")
    public String getDepartmentEmployees() {
        return departmentEmployeeService.getDepartmentEmployees();
    }

    @GetMapping("/employee/{id}")
    public String getEmployeeInfo(@PathVariable Long id) {
        return departmentEmployeeService.getEmployeeInfo(id);
    }

    @GetMapping("/summary")
    public String getEmployeeSummary() {
        return departmentEmployeeService.getEmployeeSummary();
    }

    @GetMapping("/findAllEmployees")
    public List<EmployeeEntity> findAllEmployees() {
        return departmentEmployeeService.findAllEmployees();
    }

    @GetMapping("/promote/{id}")
    public String promoteEmployee(@PathVariable Long id, @RequestParam String newRole) {
        return departmentEmployeeAdminService.promoteEmployee(id, newRole);
    }

    @GetMapping("/deactivate/{id}")
    public String deactivateEmployee(@PathVariable Long id) {
        return departmentEmployeeAdminService.deactivateEmployee(id);
    }

    @GetMapping("/add")
    public String addEmployee(@RequestParam String employeeJson) {
        return departmentEmployeeAdminService.addEmployee(employeeJson);
    }

    @GetMapping("/update/{id}")
    public String updateEmployee(@PathVariable Long id, @RequestParam String employeeJson) {
        return departmentEmployeeAdminService.updateEmployee(id, employeeJson);
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        return departmentEmployeeAdminService.deleteEmployee(id);
    }
}

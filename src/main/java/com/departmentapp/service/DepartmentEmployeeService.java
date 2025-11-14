package com.departmentapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DepartmentEmployeeService {
    private final RestTemplate restTemplate = new RestTemplate();

    public String getDepartmentEmployees() {
        String employeeApiUrl = "http://localhost:8081/api/employee/info/search?department=HR";
        try {
            return restTemplate.getForObject(employeeApiUrl, String.class);
        } catch (Exception e) {
            return "Failed to fetch employees from EmployeeApp: " + e.getMessage();
        }
    }

    public String getEmployeeInfo(Long id) {
        String url = "http://localhost:8081/api/employee/info/" + id;
        try {
            return restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            return "Failed to fetch employee info: " + e.getMessage();
        }
    }

    public String getEmployeeSummary() {
        String url = "http://localhost:8081/api/employee/info/summary";
        try {
            return restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            return "Failed to fetch employee summary: " + e.getMessage();
        }
    }
    // ...existing code...
}

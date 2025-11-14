package com.departmentapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DepartmentEmployeeAdminService {
    private final RestTemplate restTemplate = new RestTemplate();

    public String promoteEmployee(Long id, String newRole) {
        String url = "http://localhost:8081/api/employee/admin/promote/" + id + "?newRole=" + newRole;
        try {
            return restTemplate.postForObject(url, null, String.class);
        } catch (Exception e) {
            return "Failed to promote employee: " + e.getMessage();
        }
    }

    public String deactivateEmployee(Long id) {
        String url = "http://localhost:8081/api/employee/admin/deactivate/" + id;
        try {
            return restTemplate.postForObject(url, null, String.class);
        } catch (Exception e) {
            return "Failed to deactivate employee: " + e.getMessage();
        }
    }

    public String addEmployee(String employeeJson) {
        String url = "http://localhost:8081/api/employee/action/add";
        try {
            return restTemplate.postForObject(url, employeeJson, String.class);
        } catch (Exception e) {
            return "Failed to add employee: " + e.getMessage();
        }
    }

    public String updateEmployee(Long id, String employeeJson) {
        String url = "http://localhost:8081/api/employee/action/update/" + id;
        try {
            restTemplate.put(url, employeeJson);
            return "Employee updated";
        } catch (Exception e) {
            return "Failed to update employee: " + e.getMessage();
        }
    }

    public String deleteEmployee(Long id) {
        String url = "http://localhost:8081/api/employee/action/delete/" + id;
        try {
            restTemplate.delete(url);
            return "Employee deleted";
        } catch (Exception e) {
            return "Failed to delete employee: " + e.getMessage();
        }
    }
}

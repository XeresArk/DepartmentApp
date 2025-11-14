package com.departmentapp.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.departmentapp.entities.EmployeeEntity;

@Service
public class DepartmentEmployeeService {
    private final RestTemplate restTemplate = new RestTemplate();

    public String getDepartmentEmployees() {
        String url = "http://localhost:8081/api/employee/info/search?department=HR";
        try {
            return restTemplate.getForObject(url, String.class);
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
    
    public List<EmployeeEntity> findAllEmployees() {
        String url = "http://localhost:8081/api/employee/info/findAllEmployees";
        try {
            ParameterizedTypeReference<List<EmployeeEntity>> typeRef =
                new ParameterizedTypeReference<>() {};
            return restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, typeRef).getBody();
        } catch (Exception e) {
            return List.of();
        }
    }

}

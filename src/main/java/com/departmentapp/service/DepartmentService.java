package com.departmentapp.service;

import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    public String getDepartmentInfo() {
        return "Department info: HR, Finance, Engineering";
    }
}

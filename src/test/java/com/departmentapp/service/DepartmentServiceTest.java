package com.departmentapp.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DepartmentServiceTest {

    @Test
    void testGetDepartmentInfo() {
        DepartmentService service = new DepartmentService();

        String result = service.getDepartmentInfo();

        assertThat(result)
                .isEqualTo("Department info: HR, Finance, Engineering");
    }
}

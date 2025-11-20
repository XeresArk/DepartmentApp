package com.departmentapp.controller;

import com.departmentapp.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    @Test
    void testGetDepartmentInfo() throws Exception {

        when(departmentService.getDepartmentInfo())
                .thenReturn("Finance Department");

        mockMvc.perform(get("/department/info"))
                .andExpect(status().isOk())
                .andExpect(content().string("Finance Department"));

        verify(departmentService).getDepartmentInfo();
    }
}

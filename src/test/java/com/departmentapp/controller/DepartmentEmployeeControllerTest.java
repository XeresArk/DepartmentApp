package com.departmentapp.controller;

import com.departmentapp.entities.EmployeeEntity;
import com.departmentapp.service.DepartmentEmployeeAdminService;
import com.departmentapp.service.DepartmentEmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentEmployeeController.class)
class DepartmentEmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentEmployeeService departmentEmployeeService;

    @MockBean
    private DepartmentEmployeeAdminService departmentEmployeeAdminService;

    private EmployeeEntity emp(long id, String name) {
        EmployeeEntity e = new EmployeeEntity();
        e.setId(id);
        e.setName(name);
        return e;
    }

    // ----------------------------------------------------------
    // TEST 1: getDepartmentEmployees()
    // ----------------------------------------------------------
    @Test
    void testGetDepartmentEmployees() throws Exception {

        when(departmentEmployeeService.getDepartmentEmployees())
                .thenReturn("Employees List");

        mockMvc.perform(get("/department-employee/employees"))
                .andExpect(status().isOk())
                .andExpect(content().string("Employees List"));

        verify(departmentEmployeeService).getDepartmentEmployees();
    }

    // ----------------------------------------------------------
    // TEST 2: getEmployeeInfo()
    // ----------------------------------------------------------
    @Test
    void testGetEmployeeInfo() throws Exception {

        when(departmentEmployeeService.getEmployeeInfo(10L))
                .thenReturn("Employee Info 10");

        mockMvc.perform(get("/department-employee/employee/10"))
                .andExpect(status().isOk())
                .andExpect(content().string("Employee Info 10"));

        verify(departmentEmployeeService).getEmployeeInfo(10L);
    }

    // ----------------------------------------------------------
    // TEST 3: getEmployeeSummary()
    // ----------------------------------------------------------
    @Test
    void testGetEmployeeSummary() throws Exception {

        when(departmentEmployeeService.getEmployeeSummary())
                .thenReturn("Summary Info");

        mockMvc.perform(get("/department-employee/summary"))
                .andExpect(status().isOk())
                .andExpect(content().string("Summary Info"));

        verify(departmentEmployeeService).getEmployeeSummary();
    }

    // ----------------------------------------------------------
    // TEST 4: findAllEmployees()
    // ----------------------------------------------------------
    @Test
    void testFindAllEmployees() throws Exception {

        List<EmployeeEntity> list = Arrays.asList(
                emp(1L, "John"),
                emp(2L, "Alice")
        );

        when(departmentEmployeeService.findAllEmployees())
                .thenReturn(list);

        mockMvc.perform(get("/department-employee/findAllEmployees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].name").value("Alice"));

        verify(departmentEmployeeService).findAllEmployees();
    }

    // ----------------------------------------------------------
    // TEST 5: promoteEmployee()
    // ----------------------------------------------------------
    @Test
    void testPromoteEmployee() throws Exception {

        when(departmentEmployeeAdminService.promoteEmployee(5L, "Lead"))
                .thenReturn("Promoted");

        mockMvc.perform(get("/department-employee/promote/5")
                        .param("newRole", "Lead"))
                .andExpect(status().isOk())
                .andExpect(content().string("Promoted"));

        verify(departmentEmployeeAdminService).promoteEmployee(5L, "Lead");
    }

    // ----------------------------------------------------------
    // TEST 6: deactivateEmployee()
    // ----------------------------------------------------------
    @Test
    void testDeactivateEmployee() throws Exception {

        when(departmentEmployeeAdminService.deactivateEmployee(7L))
                .thenReturn("Deactivated");

        mockMvc.perform(get("/department-employee/deactivate/7"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deactivated"));

        verify(departmentEmployeeAdminService).deactivateEmployee(7L);
    }

    // ----------------------------------------------------------
    // TEST 7: addEmployee()
    // ----------------------------------------------------------
    @Test
    void testAddEmployee() throws Exception {

        when(departmentEmployeeAdminService.addEmployee("{\"id\":1}"))
                .thenReturn("Added");

        mockMvc.perform(get("/department-employee/add")
                        .param("employeeJson", "{\"id\":1}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Added"));

        verify(departmentEmployeeAdminService).addEmployee("{\"id\":1}");
    }

    // ----------------------------------------------------------
    // TEST 8: updateEmployee()
    // ----------------------------------------------------------
    @Test
    void testUpdateEmployee() throws Exception {

        when(departmentEmployeeAdminService.updateEmployee(3L, "{\"name\":\"A\"}"))
                .thenReturn("Updated");

        mockMvc.perform(get("/department-employee/update/3")
                        .param("employeeJson", "{\"name\":\"A\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Updated"));

        verify(departmentEmployeeAdminService).updateEmployee(3L, "{\"name\":\"A\"}");
    }

    // ----------------------------------------------------------
    // TEST 9: deleteEmployee()
    // ----------------------------------------------------------
    @Test
    void testDeleteEmployee() throws Exception {

        when(departmentEmployeeAdminService.deleteEmployee(11L))
                .thenReturn("Deleted");

        mockMvc.perform(get("/department-employee/delete/11"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted"));

        verify(departmentEmployeeAdminService).deleteEmployee(11L);
    }
}

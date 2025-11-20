package com.departmentapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class DepartmentEmployeeAdminServiceTest {

    private DepartmentEmployeeAdminService service;
    private RestTemplate mockRestTemplate;

    @BeforeEach
    void setUp() throws Exception {
        service = new DepartmentEmployeeAdminService();

        mockRestTemplate = Mockito.mock(RestTemplate.class);

        // Replace the private final RestTemplate using reflection
        Field field = DepartmentEmployeeAdminService.class.getDeclaredField("restTemplate");
        field.setAccessible(true);
        field.set(service, mockRestTemplate);
    }

    // ----------------------------------------------------------
    // TEST 1: promoteEmployee SUCCESS
    // ----------------------------------------------------------
    @Test
    void testPromoteEmployee_Success() {
        when(mockRestTemplate.postForObject(anyString(), any(), eq(String.class)))
                .thenReturn("Promoted");

        String result = service.promoteEmployee(1L, "Lead");

        assertThat(result).isEqualTo("Promoted");
    }

    // ----------------------------------------------------------
    // TEST 2: promoteEmployee FAILURE
    // ----------------------------------------------------------
    @Test
    void testPromoteEmployee_Failure() {
        when(mockRestTemplate.postForObject(anyString(), any(), eq(String.class)))
                .thenThrow(new RuntimeException("Server down"));

        String result = service.promoteEmployee(1L, "Lead");

        assertThat(result).contains("Failed to promote employee");
    }

    // ----------------------------------------------------------
    // TEST 3: deactivateEmployee SUCCESS
    // ----------------------------------------------------------
    @Test
    void testDeactivateEmployee_Success() {
        when(mockRestTemplate.postForObject(anyString(), any(), eq(String.class)))
                .thenReturn("Deactivated");

        String result = service.deactivateEmployee(2L);

        assertThat(result).isEqualTo("Deactivated");
    }

    // ----------------------------------------------------------
    // TEST 4: deactivateEmployee FAILURE
    // ----------------------------------------------------------
    @Test
    void testDeactivateEmployee_Failure() {
        when(mockRestTemplate.postForObject(anyString(), any(), eq(String.class)))
                .thenThrow(new RuntimeException("Timeout"));

        String result = service.deactivateEmployee(2L);

        assertThat(result).contains("Failed to deactivate employee");
    }

    // ----------------------------------------------------------
    // TEST 5: addEmployee SUCCESS
    // ----------------------------------------------------------
    @Test
    void testAddEmployee_Success() {
        when(mockRestTemplate.postForObject(anyString(), any(), eq(String.class)))
                .thenReturn("Added");

        String result = service.addEmployee("{\"name\":\"John\"}");

        assertThat(result).isEqualTo("Added");
    }

    // ----------------------------------------------------------
    // TEST 6: addEmployee FAILURE
    // ----------------------------------------------------------
    @Test
    void testAddEmployee_Failure() {
        when(mockRestTemplate.postForObject(anyString(), any(), eq(String.class)))
                .thenThrow(new RuntimeException("API error"));

        String result = service.addEmployee("{}");

        assertThat(result).contains("Failed to add employee");
    }

    // ----------------------------------------------------------
    // TEST 7: updateEmployee SUCCESS
    // ----------------------------------------------------------
    @Test
    void testUpdateEmployee_Success() {
        String result = service.updateEmployee(3L, "{\"role\":\"Manager\"}");

        assertThat(result).isEqualTo("Employee updated");
    }

    // ----------------------------------------------------------
    // TEST 8: updateEmployee FAILURE
    // ----------------------------------------------------------
    @Test
    void testUpdateEmployee_Failure() {
        doThrow(new RuntimeException("Connection refused"))
                .when(mockRestTemplate).put(anyString(), any());

        String result = service.updateEmployee(3L, "{}");

        assertThat(result).contains("Failed to update employee");
    }

    // ----------------------------------------------------------
    // TEST 9: deleteEmployee SUCCESS
    // ----------------------------------------------------------
    @Test
    void testDeleteEmployee_Success() {
        String result = service.deleteEmployee(4L);

        assertThat(result).isEqualTo("Employee deleted");
    }

    // ----------------------------------------------------------
    // TEST 10: deleteEmployee FAILURE
    // ----------------------------------------------------------
    @Test
    void testDeleteEmployee_Failure() {
        doThrow(new RuntimeException("Not reachable"))
                .when(mockRestTemplate).delete(anyString());

        String result = service.deleteEmployee(4L);

        assertThat(result).contains("Failed to delete employee");
    }
}

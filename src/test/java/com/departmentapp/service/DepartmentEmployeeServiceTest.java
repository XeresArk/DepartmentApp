package com.departmentapp.service;

import com.departmentapp.entities.EmployeeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

class DepartmentEmployeeServiceTest {

    private DepartmentEmployeeService service;
    private RestTemplate mockRestTemplate;

    @BeforeEach
    void setUp() throws Exception {
        service = new DepartmentEmployeeService();

        mockRestTemplate = Mockito.mock(RestTemplate.class);

        // Patch the private final restTemplate using reflection
        Field field = DepartmentEmployeeService.class.getDeclaredField("restTemplate");
        field.setAccessible(true);
        field.set(service, mockRestTemplate);
    }

    // ----------------------------------------------------------
    // TEST 1: getDepartmentEmployees SUCCESS
    // ----------------------------------------------------------
    @Test
    void testGetDepartmentEmployees_Success() {
        when(mockRestTemplate.getForObject(anyString(), eq(String.class)))
                .thenReturn("HR EMPLOYEES");

        String result = service.getDepartmentEmployees();

        assertThat(result).isEqualTo("HR EMPLOYEES");
    }

    // ----------------------------------------------------------
    // TEST 2: getDepartmentEmployees FAILURE
    // ----------------------------------------------------------
    @Test
    void testGetDepartmentEmployees_Failure() {
        when(mockRestTemplate.getForObject(anyString(), eq(String.class)))
                .thenThrow(new RuntimeException("Service down"));

        String result = service.getDepartmentEmployees();

        assertThat(result).contains("Failed to fetch employees");
    }

    // ----------------------------------------------------------
    // TEST 3: getEmployeeInfo SUCCESS
    // ----------------------------------------------------------
    @Test
    void testGetEmployeeInfo_Success() {
        when(mockRestTemplate.getForObject(contains("/5"), eq(String.class)))
                .thenReturn("Employee 5 info");

        String result = service.getEmployeeInfo(5L);

        assertThat(result).isEqualTo("Employee 5 info");
    }

    // ----------------------------------------------------------
    // TEST 4: getEmployeeInfo FAILURE
    // ----------------------------------------------------------
    @Test
    void testGetEmployeeInfo_Failure() {
        when(mockRestTemplate.getForObject(anyString(), eq(String.class)))
                .thenThrow(new RuntimeException("Not found"));

        String result = service.getEmployeeInfo(5L);

        assertThat(result).contains("Failed to fetch employee info");
    }

    // ----------------------------------------------------------
    // TEST 5: getEmployeeSummary SUCCESS
    // ----------------------------------------------------------
    @Test
    void testGetEmployeeSummary_Success() {
        when(mockRestTemplate.getForObject(anyString(), eq(String.class)))
                .thenReturn("Summary OK");

        String result = service.getEmployeeSummary();

        assertThat(result).isEqualTo("Summary OK");
    }

    // ----------------------------------------------------------
    // TEST 6: getEmployeeSummary FAILURE
    // ----------------------------------------------------------
    @Test
    void testGetEmployeeSummary_Failure() {
        when(mockRestTemplate.getForObject(anyString(), eq(String.class)))
                .thenThrow(new RuntimeException("Something wrong"));

        String result = service.getEmployeeSummary();

        assertThat(result).contains("Failed to fetch employee summary");
    }

    // ----------------------------------------------------------
    // TEST 7: findAllEmployees SUCCESS
    // ----------------------------------------------------------
    @Test
    void testFindAllEmployees_Success() {

        EmployeeEntity e1 = new EmployeeEntity();
        e1.setId(1L);
        e1.setName("John");

        EmployeeEntity e2 = new EmployeeEntity();
        e2.setId(2L);
        e2.setName("Alice");

        List<EmployeeEntity> mockList = Arrays.asList(e1, e2);

        ResponseEntity<List<EmployeeEntity>> mockResponse =
                new ResponseEntity<>(mockList, HttpStatus.OK);

        when(mockRestTemplate.exchange(
                anyString(),
                eq(HttpMethod.GET),
                eq(HttpEntity.EMPTY),
                any(ParameterizedTypeReference.class)
        )).thenReturn(mockResponse);

        List<EmployeeEntity> result = service.findAllEmployees();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("John");
        assertThat(result.get(1).getName()).isEqualTo("Alice");
    }

    // ----------------------------------------------------------
    // TEST 8: findAllEmployees FAILURE
    // ----------------------------------------------------------
    @Test
    void testFindAllEmployees_Failure() {

        when(mockRestTemplate.exchange(
                anyString(),
                eq(HttpMethod.GET),
                eq(HttpEntity.EMPTY),
                any(ParameterizedTypeReference.class)
        )).thenThrow(new RuntimeException("Timeout"));

        List<EmployeeEntity> result = service.findAllEmployees();

        assertThat(result).isEmpty(); // because catch block returns List.of()
    }
}

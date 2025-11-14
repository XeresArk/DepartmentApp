# DepartmentApp

Spring Boot application with Swagger UI, two controllers, two services, and inter-service REST calls to EmployeeApp.

## Features
- `DepartmentController`: Returns department info
- `DepartmentEmployeeController`: Returns employees by calling EmployeeApp API
- Swagger UI enabled at `/swagger-ui.html`

## How to Run
1. Build and start EmployeeApp (on port 8081)
2. Build and start DepartmentApp (on port 8082)
3. Access Swagger UI: [http://localhost:8082/swagger-ui.html](http://localhost:8082/swagger-ui.html)


## Service Split
- `DepartmentEmployeeService`: Handles info, summary, and search endpoints
- `DepartmentEmployeeAdminService`: Handles admin endpoints (promote, deactivate, add, update, delete)

## Endpoints
- `GET /department/info`: Department info
- `GET /department-employee/employees`: Employees from EmployeeApp (search by department)
- `GET /department-employee/employee/{id}`: Get employee info by ID (from EmployeeApp)
- `GET /department-employee/summary`: Get employee summary (from EmployeeApp)
- `GET /department-employee/promote/{id}?newRole=...`: Promote employee (calls EmployeeApp, via DepartmentEmployeeAdminService)
- `GET /department-employee/deactivate/{id}`: Deactivate employee (calls EmployeeApp, via DepartmentEmployeeAdminService)
- `GET /department-employee/add?employeeJson=...`: Add employee (calls EmployeeApp, via DepartmentEmployeeAdminService)
- `GET /department-employee/update/{id}?employeeJson=...`: Update employee (calls EmployeeApp, via DepartmentEmployeeAdminService)
- `GET /department-employee/delete/{id}`: Delete employee (calls EmployeeApp, via DepartmentEmployeeAdminService)

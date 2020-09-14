package com.servicevirtualization.employee.representation.response;

import java.util.ArrayList;
import java.util.List;

public class GetEmployeesResponseDTO {
    private int employeeCount;
    private List<GetEmployeeResponseDTO> employees = new ArrayList();

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public List<GetEmployeeResponseDTO> getEmployees() {
        return employees;
    }

    public void insertEmployee(GetEmployeeResponseDTO employee) {
        this.employees.add(employee);
    }
}

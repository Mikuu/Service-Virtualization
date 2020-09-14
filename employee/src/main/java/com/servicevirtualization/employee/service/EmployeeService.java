package com.servicevirtualization.employee.service;

import com.servicevirtualization.employee.model.Employee;
import com.servicevirtualization.employee.representation.request.CreateEmployeeRequestDTO;
import com.servicevirtualization.employee.representation.request.UpdateEmployeeRequestDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    public Employee retrieveEmployee(String uid) {
        Employee employee = new Employee();

        return employee;
    }

    public String createEmployee(CreateEmployeeRequestDTO requestDTO) {
        Employee employee = new Employee();

        return employee.getUid();
    }

    public int deleteEmployeeByUid(String uid) {
        int deletedEmployeeCount = 0;

        return deletedEmployeeCount;
    }

    public Employee updateEmployee(UpdateEmployeeRequestDTO requestDTO, String uid) {
        Employee employee = new Employee();

        return employee;
    }

    public List<Employee> fetchEmployeesWithName(String name) {
        List<Employee> employees = new ArrayList<>();

        return employees;
    }
}

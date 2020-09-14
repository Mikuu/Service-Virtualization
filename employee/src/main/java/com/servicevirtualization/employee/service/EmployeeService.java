package com.servicevirtualization.employee.service;

import com.servicevirtualization.employee.model.Employee;
import com.servicevirtualization.employee.model.User;
import com.servicevirtualization.employee.representation.request.CreateEmployeeRequestDTO;
import com.servicevirtualization.employee.representation.request.UpdateEmployeeRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private UserService userService;

    @Autowired
    private SalaryService salaryService;


    public String createEmployee(CreateEmployeeRequestDTO requestDTO) {
        User user = new User();
        user.setAge(requestDTO.getAge());
        user.setGender(requestDTO.getGender());
        user.setName(requestDTO.getName());
        user.setNationality(requestDTO.getNationality());

        String uid = userService.createUser(user);
        salaryService.createSalary(uid);

        return uid;
    }

    public Employee retrieveEmployee(String uid) {
        Employee employee = new Employee();

        return employee;
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

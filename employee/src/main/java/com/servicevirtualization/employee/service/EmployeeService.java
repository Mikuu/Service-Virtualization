package com.servicevirtualization.employee.service;

import com.servicevirtualization.employee.exception.SVException;
import com.servicevirtualization.employee.model.Employee;
import com.servicevirtualization.employee.model.Salary;
import com.servicevirtualization.employee.model.User;
import com.servicevirtualization.employee.representation.request.CreateEmployeeRequestDTO;
import com.servicevirtualization.employee.representation.request.UpdateEmployeeRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

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

    public Employee retrieveEmployee(String uid) throws SVException {
        User user;
        Salary salary;

        try {
            user = userService.fetchUserByUid(uid);
            salary = salaryService.fetchSalary(uid);
        } catch (Exception e) {
            logger.error(MessageFormat.format("FBI --> fetching user or salary Error: {0}", e.getLocalizedMessage()));
            throw new SVException(MessageFormat.format("retrieve employee with uid {0} failed", uid));
        }


        if (!user.getUid().equals(salary.getUid())) {
            throw new SVException(MessageFormat.format("fetch employee with uid {0} failed", uid));
        }

        Employee employee = new Employee();
        employee.setUid(user.getUid());
        employee.setNationality(user.getNationality());
        employee.setName(user.getName());
        employee.setGender(user.getGender());
        employee.setAge(user.getAge());
        employee.setSalary(salary.getAmount());

        return employee;
    }

    public void deleteEmployeeByUid(String uid) throws SVException {
        User user = userService.fetchUserByUid(uid);
        Salary salary = salaryService.fetchSalary(uid);

        if (!user.getUid().equals(salary.getUid())) {
            throw new SVException(MessageFormat.format("delete employee with uid {0} failed", uid));
        }

        userService.deleteUserByUid(uid);
        salaryService.deleteSalaryByUid(uid);
    }

    public Employee updateEmployee(UpdateEmployeeRequestDTO requestDTO, String uid) throws SVException {
        User user = userService.fetchUserByUid(uid);
        Salary salary = salaryService.fetchSalary(uid);

        if (!user.getUid().equals(salary.getUid())) {
            throw new SVException(MessageFormat.format("update employee with uid {0} failed", uid));
        }

        userService.updateUser(requestDTO, uid);
        salaryService.updateSalary(uid, requestDTO.getSalary());

        Employee employee = new Employee();
        employee.setAge(requestDTO.getAge());
        employee.setGender(requestDTO.getGender());
        employee.setName(requestDTO.getName());
        employee.setNationality(requestDTO.getNationality());
        employee.setSalary(requestDTO.getSalary());
        employee.setUid(uid);

        return employee;
    }

    public List<Employee> fetchEmployeesWithName(String name) throws SVException {
        List<User> users = userService.getUsersByName(name);

        List<Employee> employees = new ArrayList<>();
        for (User user: users) {
            Salary salary = salaryService.fetchSalary(user.getUid());

            Employee employee = new Employee();
            employee.setSalary(salary.getAmount());
            employee.setAge(user.getAge());
            employee.setGender(user.getGender());
            employee.setName(user.getName());
            employee.setUid(user.getUid());
            employee.setNationality(user.getNationality());

            employees.add(employee);
        }

        return employees;
    }
}

package com.servicevirtualization.employee.controller;

import com.servicevirtualization.employee.exception.SVException;
import com.servicevirtualization.employee.model.Employee;
import com.servicevirtualization.employee.representation.request.CreateEmployeeRequestDTO;
import com.servicevirtualization.employee.representation.request.UpdateEmployeeRequestDTO;
import com.servicevirtualization.employee.representation.response.*;
import com.servicevirtualization.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;

@RestController()
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public CreateEmployeeResponseDTO createEmployee(@RequestBody CreateEmployeeRequestDTO createEmployeeRequest) {
        String uid = employeeService.createEmployee(createEmployeeRequest);

        CreateEmployeeResponseDTO responseDTO = new CreateEmployeeResponseDTO();
        responseDTO.setUid(uid);
        responseDTO.setResult("Succeed");

        return responseDTO;
    }

    @PutMapping("/employee/{uid}")
    public UpdateEmployeeResponseDTO updateEmployee(@RequestBody UpdateEmployeeRequestDTO updateEmployeeRequest, @PathVariable String uid) throws SVException {
        Employee employee = employeeService.updateEmployee(updateEmployeeRequest, uid);

        UpdateEmployeeResponseDTO responseDTO = new UpdateEmployeeResponseDTO();
        responseDTO.setAge(employee.getAge());
        responseDTO.setGender(employee.getGender());
        responseDTO.setName(employee.getName());
        responseDTO.setNationality(employee.getNationality());
        responseDTO.setUid(employee.getUid());
        responseDTO.setSalary(employee.getSalary());

        return responseDTO;
    }

    @DeleteMapping("/employee/{uid}")
    public DeleteEmployeeResponseDTO deleteEmployee(@PathVariable String uid) throws SVException {
        try {
            employeeService.deleteEmployeeByUid(uid);
        } catch (Exception e) {
            throw new SVException(MessageFormat.format("delete employee failed: {0}", e.getMessage()));
        }

        DeleteEmployeeResponseDTO responseDTO = new DeleteEmployeeResponseDTO();
        responseDTO.setUid(uid);
        responseDTO.setResult("Succeed");

        return  responseDTO;
    }

    @GetMapping("/employee/{uid}")
    public GetEmployeeResponseDTO getEmployee(@PathVariable String uid) throws SVException {
        Employee employee = employeeService.retrieveEmployee(uid);

        if (employee == null) {
            throw new SVException(MessageFormat.format("uid {0} doesn't exist", uid));
        }

        GetEmployeeResponseDTO responseDTO = new GetEmployeeResponseDTO();
        responseDTO.setAge(employee.getAge());
        responseDTO.setGender(employee.getGender());
        responseDTO.setName(employee.getName());
        responseDTO.setNationality(employee.getNationality());
        responseDTO.setUid(employee.getUid());
        responseDTO.setSalary(employee.getSalary());

        return responseDTO;
    }

    @GetMapping("/employee")
    public GetEmployeesResponseDTO getEmployees(@RequestParam String name) throws SVException {
        List<Employee> employees = employeeService.fetchEmployeesWithName(name);

        GetEmployeesResponseDTO responseDTO = new GetEmployeesResponseDTO();
        responseDTO.setEmployeeCount(employees.size());
        for (Employee employee: employees) {
            GetEmployeeResponseDTO getEmployeeResponseDTO = new GetEmployeeResponseDTO();

            getEmployeeResponseDTO.setAge(employee.getAge());
            getEmployeeResponseDTO.setGender(employee.getGender());
            getEmployeeResponseDTO.setName(employee.getName());
            getEmployeeResponseDTO.setNationality(employee.getNationality());
            getEmployeeResponseDTO.setUid(employee.getUid());
            getEmployeeResponseDTO.setSalary(employee.getSalary());

            responseDTO.insertEmployee(getEmployeeResponseDTO);
        }

        return responseDTO;
    }

}

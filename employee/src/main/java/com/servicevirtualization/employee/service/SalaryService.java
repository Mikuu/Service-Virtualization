package com.servicevirtualization.employee.service;

import com.servicevirtualization.employee.exception.SVException;
import com.servicevirtualization.employee.model.Salary;
import com.servicevirtualization.employee.property.SalaryProperty;
import com.servicevirtualization.employee.property.UserProperty;
import com.servicevirtualization.employee.representation.request.CreateSalaryRequestDTO;
import com.servicevirtualization.employee.representation.response.CreateSalaryResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.MessageFormat;

@Service
public class SalaryService {

    @Autowired
    private SalaryProperty salaryProperty;

    @Autowired
    private RestTemplate restTemplate;

    public String createSalary(String uid) {

        String url = UriComponentsBuilder
                .fromUriString(salaryProperty.getUrl())
                .path(salaryProperty.getPathCreateSalary())
                .toUriString();

        CreateSalaryRequestDTO request = new CreateSalaryRequestDTO();
        request.setUid(uid);

        CreateSalaryResponseDTO response = restTemplate.postForObject(url, request, CreateSalaryResponseDTO.class);

        return response.getUid();
    }

//    public int deleteSalaryByUid(String uid) {
//        return salaryRepository.deleteSalaryByUid(uid);
//    }
//
//    public Salary getSalary(String uid) throws SVException {
//        Salary salary = salaryRepository.findByUid(uid);
//
//        if (salary == null) {
//            throw new SVException(MessageFormat.format("no salary found by uid {0}.", uid));
//        }
//
//        return salary;
//    }
//
//    public Salary updateSalary(String uid, int newSalary) throws SVException {
//        Salary salary = salaryRepository.findByUid(uid);
//
//        if (salary == null) {
//            throw new SVException(MessageFormat.format("no salary found by uid {0}.", uid));
//        }
//
//        salary.setAmount(newSalary);
//        salaryRepository.save(salary);
//
//        return salary;
//    }
}

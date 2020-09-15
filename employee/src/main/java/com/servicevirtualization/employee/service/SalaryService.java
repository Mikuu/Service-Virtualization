package com.servicevirtualization.employee.service;

import com.servicevirtualization.employee.exception.SVException;
import com.servicevirtualization.employee.model.Salary;
import com.servicevirtualization.employee.property.SalaryProperty;
import com.servicevirtualization.employee.representation.request.CreateSalaryRequestDTO;
import com.servicevirtualization.employee.representation.request.UpdateSalaryRequestDTO;
import com.servicevirtualization.employee.representation.response.CreateSalaryResponseDTO;
import com.servicevirtualization.employee.representation.response.GetSalaryResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

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

        // ToDo -> Error handling for invalid uid fetch.

        return response.getUid();
    }

    public void deleteSalaryByUid(String uid) {
        Map<String, String> pathParameters = new HashMap<>();
        pathParameters.put("uid", uid);

        String url = UriComponentsBuilder
                .fromUriString(salaryProperty.getUrl())
                .path(salaryProperty.getPathDeleteSalary())
                .buildAndExpand(pathParameters)
                .toUriString();

        restTemplate.delete(url);
    }

    public Salary fetchSalary(String uid) throws SVException {
        Map<String, String> pathParameters = new HashMap<>();
        pathParameters.put("uid", uid);

        String url = UriComponentsBuilder
                .fromUriString(salaryProperty.getUrl())
                .path(salaryProperty.getPathFetchSalary())
                .buildAndExpand(pathParameters)
                .toUriString();

        GetSalaryResponseDTO response = restTemplate.getForObject(url, GetSalaryResponseDTO.class);

        // ToDo -> Error handling for invalid uid fetch.

        Salary salary = new Salary();
        salary.setUid(response.getUid());
        salary.setAmount(response.getSalary());

        return salary;
    }

    public void updateSalary(String uid, int newSalary) throws SVException {
        Map<String, String> pathParameters = new HashMap<>();
        pathParameters.put("uid", uid);

        String url = UriComponentsBuilder
                .fromUriString(salaryProperty.getUrl())
                .path(salaryProperty.getPathUpdateSalary())
                .buildAndExpand(pathParameters)
                .toUriString();

        UpdateSalaryRequestDTO request = new UpdateSalaryRequestDTO();
        request.setSalary(newSalary);

        restTemplate.put(url, request);

        // ToDo -> Error handling for invalid uid fetch.
    }
}

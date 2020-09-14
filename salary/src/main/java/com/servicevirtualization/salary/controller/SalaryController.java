package com.servicevirtualization.salary.controller;

import com.servicevirtualization.salary.exception.SVException;
import com.servicevirtualization.salary.model.Salary;
import com.servicevirtualization.salary.representation.request.CreateSalaryRequestDTO;
import com.servicevirtualization.salary.representation.request.UpdateSalaryRequestDTO;
import com.servicevirtualization.salary.representation.response.CreateSalaryResponseDTO;
import com.servicevirtualization.salary.representation.response.DeleteSalaryResponseDTO;
import com.servicevirtualization.salary.representation.response.GetSalaryResponseDTO;
import com.servicevirtualization.salary.representation.response.UpdateSalaryResponseDTO;
import com.servicevirtualization.salary.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @PostMapping("/salary")
    public CreateSalaryResponseDTO createSalary(@RequestBody CreateSalaryRequestDTO createSalaryRequest) throws SVException {
        String uid = salaryService.createSalary(createSalaryRequest.getUid());

        CreateSalaryResponseDTO responseDTO = new CreateSalaryResponseDTO();
        responseDTO.setUid(uid);
        responseDTO.setResult("Succeed");

        return responseDTO;
    }

    @GetMapping("/salary/{uid}")
    public GetSalaryResponseDTO getSalary(@PathVariable String uid) throws SVException {
        Salary salary = salaryService.getSalary(uid);

        GetSalaryResponseDTO responseDTO = new GetSalaryResponseDTO();
        responseDTO.setUid(uid);
        responseDTO.setSalary(salary.getAmount());

        return responseDTO;
    }

    @PutMapping("/salary/{uid}")
    public UpdateSalaryResponseDTO updateSalary(@PathVariable String uid, @RequestBody UpdateSalaryRequestDTO updateSalaryRequestDTO) throws SVException {
        Salary currentSalary = salaryService.getSalary(uid);
        Salary updatedSalary = salaryService.updateSalary(uid, updateSalaryRequestDTO.getSalary());

        UpdateSalaryResponseDTO responseDTO = new UpdateSalaryResponseDTO();
        responseDTO.setUid(uid);
        responseDTO.setPreviousSalary(currentSalary.getAmount());
        responseDTO.setCurrentSalary(updatedSalary.getAmount());

        return responseDTO;
    }

    @DeleteMapping("/salary/{uid}")
    public DeleteSalaryResponseDTO deleteSalary(@PathVariable String uid) {
        int deleteCount = salaryService.deleteSalaryByUid(uid);

        DeleteSalaryResponseDTO responseDTO = new DeleteSalaryResponseDTO();
        responseDTO.setUid(uid);

        if (deleteCount > 0) {
            responseDTO.setResult("Succeed");
        } else {
            responseDTO.setResult("Failed");
        }

        return responseDTO;
    }

}

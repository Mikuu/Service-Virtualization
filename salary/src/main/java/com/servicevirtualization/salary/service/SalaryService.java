package com.servicevirtualization.salary.service;

import com.servicevirtualization.salary.exception.SVException;
import com.servicevirtualization.salary.model.Salary;
import com.servicevirtualization.salary.repository.ISalaryRepository;
import com.servicevirtualization.salary.utils.SalaryGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class SalaryService {

    @Autowired
    private ISalaryRepository salaryRepository;

    public int deleteSalaryByUid(String uid) {
        return salaryRepository.deleteSalaryByUid(uid);
    }

    public String createSalary(String uid) throws SVException {

        if (salaryRepository.existsByUid(uid)) {
            throw new SVException(MessageFormat.format("uid {0} is already existed.", uid));
        }

        Salary salary = new Salary();
        salary.setUid(uid);
        salary.setAmount(SalaryGenerator.generateRandomSalary(20000, 50000));

        salaryRepository.save(salary);

        return salary.getUid();
    }

    public Salary getSalary(String uid) throws SVException {
        Salary salary = salaryRepository.findByUid(uid);

        if (salary == null) {
            throw new SVException(MessageFormat.format("no salary found by uid {0}.", uid));
        }

        return salary;
    }

    public Salary updateSalary(String uid, int newSalary) throws SVException {
        Salary salary = salaryRepository.findByUid(uid);

        if (salary == null) {
            throw new SVException(MessageFormat.format("no salary found by uid {0}.", uid));
        }

        salary.setAmount(newSalary);
        salaryRepository.save(salary);

        return salary;
    }
}

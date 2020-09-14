package com.servicevirtualization.salary.repository;

import com.servicevirtualization.salary.model.Salary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ISalaryRepository extends MongoRepository<Salary, String> {

    @Transactional
    Integer deleteSalaryByUid(String uid);

    boolean existsByUid(String uid);

    Salary findByUid(String uid);
}

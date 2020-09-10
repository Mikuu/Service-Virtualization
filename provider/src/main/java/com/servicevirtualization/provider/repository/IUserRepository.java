package com.servicevirtualization.provider.repository;

import com.servicevirtualization.provider.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IUserRepository extends MongoRepository<User, String> {

    @Transactional
    Integer deleteUserByUid(String uid);

    User findByUid(String uid);

    List<User> findByName(String name);

    List<User> findByNationality(String nationality);

    List<User> findByNameAndNationality(String name, String nationality);
}

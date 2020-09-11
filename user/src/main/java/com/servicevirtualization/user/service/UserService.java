package com.servicevirtualization.user.service;

import com.servicevirtualization.user.exception.SVException;
import com.servicevirtualization.user.model.User;
import com.servicevirtualization.user.repository.IUserRepository;
import com.servicevirtualization.user.representation.request.CreateUserRequestDTO;
import com.servicevirtualization.user.representation.request.UpdateUserRequestDTO;
import com.servicevirtualization.user.utils.FakeId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private IUserRepository userRepository;

    public String createUser(CreateUserRequestDTO createUserRequest) {
        User user = new User();
        user.setUid(FakeId.getFakeUUID());
        user.setName(createUserRequest.getName());
        user.setGender(createUserRequest.getGender());
        user.setNationality(createUserRequest.getNationality());
        user.setAge(createUserRequest.getAge());

        userRepository.save(user);

        return user.getUid();
    }

    public User getUserByUid(String uid) {
        return userRepository.findByUid(uid);
    }

    public List<User> getUsersByName(String name) {
        return userRepository.findByName(name);
    }

    public List<User> getUsersByNationality(String nationality) {
        return userRepository.findByNationality(nationality);
    }

    public List<User> getUsersByNameAndNationality(String name, String nationality) {
        return userRepository.findByNameAndNationality(name, nationality);
    }

    public int deleteUserByUid(String uid) {
        return userRepository.deleteUserByUid(uid);
    }

    public User updateUser(UpdateUserRequestDTO updateUserRequest, String uid) throws SVException {
        User user = userRepository.findByUid(uid);

        if (user == null) {
            throw new SVException(MessageFormat.format("uid {0} is not found", uid));
        }

        user.setAge(updateUserRequest.getAge());
        user.setNationality(updateUserRequest.getNationality());
        user.setGender(updateUserRequest.getGender());
        user.setName(updateUserRequest.getName());

        userRepository.save(user);

        return user;
    }
}

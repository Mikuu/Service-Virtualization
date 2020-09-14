package com.servicevirtualization.employee.service;

import com.servicevirtualization.employee.exception.SVException;
import com.servicevirtualization.employee.model.User;
import com.servicevirtualization.employee.property.UserProperty;
import com.servicevirtualization.employee.representation.request.CreateUserRequestDTO;
import com.servicevirtualization.employee.representation.request.UpdateUserRequestDTO;
import com.servicevirtualization.employee.representation.response.CreateUserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.MessageFormat;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserProperty userProperty;

    @Bean
    private RestTemplate restTemplate() {
        return new RestTemplate();
    }

    String createUser(User user) {

        String url = UriComponentsBuilder
                .fromUriString(userProperty.getUrl())
                .path(userProperty.getPathCreateUser())
                .toUriString();

        CreateUserRequestDTO request = new CreateUserRequestDTO();
        request.setAge(user.getAge());
        request.setGender(user.getGender());
        request.setName(user.getName());
        request.setNationality(user.getNationality());

        CreateUserResponseDTO response = restTemplate().postForObject(url, request, CreateUserResponseDTO.class);

        return response.getUid();
    }

//    public User getUserByUid(String uid) {
//        return userRepository.findByUid(uid);
//    }
//
//    public List<User> getUsersByName(String name) {
//        return userRepository.findByName(name);
//    }
//
//    public List<User> getUsersByNationality(String nationality) {
//        return userRepository.findByNationality(nationality);
//    }
//
//    public List<User> getUsersByNameAndNationality(String name, String nationality) {
//        return userRepository.findByNameAndNationality(name, nationality);
//    }
//
//    public int deleteUserByUid(String uid) {
//        return userRepository.deleteUserByUid(uid);
//    }
//
//    public User updateUser(UpdateUserRequestDTO updateUserRequest, String uid) throws SVException {
//        User user = userRepository.findByUid(uid);
//
//        if (user == null) {
//            throw new SVException(MessageFormat.format("uid {0} is not found", uid));
//        }
//
//        user.setAge(updateUserRequest.getAge());
//        user.setNationality(updateUserRequest.getNationality());
//        user.setGender(updateUserRequest.getGender());
//        user.setName(updateUserRequest.getName());
//
//        userRepository.save(user);
//
//        return user;
//    }
}

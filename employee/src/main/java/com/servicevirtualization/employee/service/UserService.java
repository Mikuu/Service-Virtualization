package com.servicevirtualization.employee.service;

import com.servicevirtualization.employee.exception.SVException;
import com.servicevirtualization.employee.model.User;
import com.servicevirtualization.employee.property.UserProperty;
import com.servicevirtualization.employee.representation.request.CreateUserRequestDTO;
import com.servicevirtualization.employee.representation.request.UpdateEmployeeRequestDTO;
import com.servicevirtualization.employee.representation.request.UpdateUserRequestDTO;
import com.servicevirtualization.employee.representation.response.CreateUserResponseDTO;
import com.servicevirtualization.employee.representation.response.GetUserResponseDTO;
import com.servicevirtualization.employee.representation.response.GetUsersResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

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

        // ToDo -> Error handling for invalid uid fetch.

        return response.getUid();
    }

    User fetchUserByUid(String uid) {
        Map<String, String> pathParameters = new HashMap<>();
        pathParameters.put("uid", uid);

        String url = UriComponentsBuilder
                .fromUriString(userProperty.getUrl())
                .path(userProperty.getPathFetchUser())
                .buildAndExpand(pathParameters)
                .toUriString();

        GetUserResponseDTO response = restTemplate().getForObject(url, GetUserResponseDTO.class);

        // ToDo -> Error handling for invalid uid fetch.

        User user = new User();
        user.setAge(response.getAge());
        user.setGender(response.getGender());
        user.setName(response.getName());
        user.setNationality(response.getNationality());
        user.setUid(response.getUid());

        return user;
    }

    public List<User> getUsersByName(String name) {
        String url = UriComponentsBuilder
                .fromUriString(userProperty.getUrl())
                .path(userProperty.getPathGetUsers())
                .queryParam("name", name)
                .toUriString();

        GetUsersResponseDTO getUsersResponse = restTemplate().getForObject(url, GetUsersResponseDTO.class);

        List<User> users = new ArrayList<>();
        for (GetUserResponseDTO getUserResponse: getUsersResponse.getUsers()) {
            User user = new User();
            user.setUid(getUserResponse.getUid());
            user.setNationality(getUserResponse.getNationality());
            user.setName(getUserResponse.getName());
            user.setGender(getUserResponse.getGender());
            user.setAge(getUserResponse.getAge());

            users.add(user);
        }

        return users;
    }

//    public List<User> getUsersByNationality(String nationality) {
//        return userRepository.findByNationality(nationality);
//    }

//    public List<User> getUsersByNameAndNationality(String name, String nationality) {
//        return userRepository.findByNameAndNationality(name, nationality);
//    }

    public void deleteUserByUid(String uid) {
        Map<String, String> pathParameters = new HashMap<>();
        pathParameters.put("uid", uid);

        String url = UriComponentsBuilder
                .fromUriString(userProperty.getUrl())
                .path(userProperty.getPathDeleteUser())
                .buildAndExpand(pathParameters)
                .toUriString();

        restTemplate().delete(url);
    }

    public void updateUser(UpdateEmployeeRequestDTO updateEmployeeRequest, String uid) throws SVException {
        Map<String, String> pathParameters = new HashMap<>();
        pathParameters.put("uid", uid);

        String url = UriComponentsBuilder
                .fromUriString(userProperty.getUrl())
                .path(userProperty.getPathUpdateUser())
                .buildAndExpand(pathParameters)
                .toUriString();

        UpdateUserRequestDTO request = new UpdateUserRequestDTO();
        request.setAge(updateEmployeeRequest.getAge());
        request.setGender(updateEmployeeRequest.getGender());
        request.setName(updateEmployeeRequest.getName());
        request.setNationality(updateEmployeeRequest.getNationality());

        restTemplate().put(url, request);

        // ToDo -> Error handling for invalid uid fetch.
    }
}

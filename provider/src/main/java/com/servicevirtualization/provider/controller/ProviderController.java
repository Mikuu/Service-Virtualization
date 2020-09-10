package com.servicevirtualization.provider.controller;

import com.servicevirtualization.provider.exception.SVException;
import com.servicevirtualization.provider.model.User;
import com.servicevirtualization.provider.representation.request.CreateUserRequestDTO;
import com.servicevirtualization.provider.representation.request.UpdateUserRequestDTO;
import com.servicevirtualization.provider.representation.response.*;
import com.servicevirtualization.provider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@RestController()
public class ProviderController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public CreateUserResponseDTO createUser(@RequestBody CreateUserRequestDTO createUserRequest) {
        String uid = userService.createUser(createUserRequest);

        CreateUserResponseDTO responseDTO = new CreateUserResponseDTO();
        responseDTO.setUid(uid);
        responseDTO.setResult("Succeed");

        return responseDTO;
    }

    @PutMapping("/user/{uid}")
    public UpdateUserResponseDTO updateUser(@RequestBody UpdateUserRequestDTO updateUserRequest, @PathVariable String uid) throws SVException {
        User user = userService.updateUser(updateUserRequest, uid);

        UpdateUserResponseDTO responseDTO = new UpdateUserResponseDTO();
        responseDTO.setAge(user.getAge());
        responseDTO.setGender(user.getGender());
        responseDTO.setName(user.getName());
        responseDTO.setNationality(user.getNationality());
        responseDTO.setUid(user.getUid());

        return responseDTO;
    }

    @DeleteMapping("/user/{uid}")
    public DeleteUserResponseDTO deleteUser(@PathVariable String uid){
        int deletedCount = userService.deleteUserByUid(uid);
        DeleteUserResponseDTO responseDTO = new DeleteUserResponseDTO();
        responseDTO.setUid(uid);

        if (deletedCount >= 1) {
            responseDTO.setResult("Succeed");
        } else {
            responseDTO.setResult("Failed");
        }

        return  responseDTO;
    }

    @GetMapping("/user/{uid}")
    public GetUserResponseDTO getUser(@PathVariable String uid) throws SVException {
        User user = userService.getUserByUid(uid);

        if (user == null) {
            throw new SVException(MessageFormat.format("uid {0} doesn't exist", uid));
        }

        GetUserResponseDTO responseDTO = new GetUserResponseDTO();
        responseDTO.setAge(user.getAge());
        responseDTO.setGender(user.getGender());
        responseDTO.setName(user.getName());
        responseDTO.setNationality(user.getNationality());
        responseDTO.setUid(user.getUid());

        return responseDTO;
    }

    @GetMapping("/users")
    public GetUsersResponseDTO getUsers(@RequestParam(required = false) String name, @RequestParam(required = false) String nationality) {
        List<User> users;

        if (name != null && nationality != null) {
            users = userService.getUsersByNameAndNationality(name, nationality);
        } else if (name != null) {
            users = userService.getUsersByName(name);
        } else if (nationality != null) {
            users = userService.getUsersByNationality(nationality);
        } else {
            users = new ArrayList<>();
        }

        GetUsersResponseDTO responseDTO = new GetUsersResponseDTO();
        responseDTO.setUserCount(users.size());
        for (User user: users) {
            GetUserResponseDTO getUserResponseDTO = new GetUserResponseDTO();
            getUserResponseDTO.setFromUser(user);
            responseDTO.insertUser(getUserResponseDTO);
        }

        return responseDTO;
    }

}

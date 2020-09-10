package com.servicevirtualization.provider.representation.response;

import com.servicevirtualization.provider.model.User;

import java.util.ArrayList;
import java.util.List;

public class GetUsersResponseDTO {
    private int userCount;
    private List<GetUserResponseDTO> users = new ArrayList();

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public List<GetUserResponseDTO> getUsers() {
        return users;
    }

    public void insertUser(GetUserResponseDTO user) {
        this.users.add(user);
    }
}

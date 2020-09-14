package com.servicevirtualization.employee.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "user-svc")
public class UserProperty {
    private String url;
    private String pathCreateUser;
    private String pathGetUsers;
    private String pathFetchUser;
    private String pathUpdateUser;
    private String pathDeleteUser;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPathCreateUser() {
        return pathCreateUser;
    }

    public void setPathCreateUser(String pathCreateUser) {
        this.pathCreateUser = pathCreateUser;
    }

    public String getPathGetUsers() {
        return pathGetUsers;
    }

    public void setPathGetUsers(String pathGetUsers) {
        this.pathGetUsers = pathGetUsers;
    }

    public String getPathFetchUser() {
        return pathFetchUser;
    }

    public void setPathFetchUser(String pathFetchUser) {
        this.pathFetchUser = pathFetchUser;
    }

    public String getPathUpdateUser() {
        return pathUpdateUser;
    }

    public void setPathUpdateUser(String pathUpdateUser) {
        this.pathUpdateUser = pathUpdateUser;
    }

    public String getPathDeleteUser() {
        return pathDeleteUser;
    }

    public void setPathDeleteUser(String pathDeleteUser) {
        this.pathDeleteUser = pathDeleteUser;
    }
}

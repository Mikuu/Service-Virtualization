package com.servicevirtualization.provider.representation.response;

import com.servicevirtualization.provider.model.User;

public class GetUserResponseDTO {
    private String uid;
    private String name;
    private String gender;
    private String nationality;
    private int age;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFromUser(User user) {
        this.uid = user.getUid();
        this.name = user.getName();
        this.gender = user.getGender();
        this.nationality = user.getNationality();
        this.age = user.getAge();
    }
}

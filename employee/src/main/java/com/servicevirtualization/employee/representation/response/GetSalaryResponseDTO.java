package com.servicevirtualization.employee.representation.response;

public class GetSalaryResponseDTO {
    private String uid;
    private int salary;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}

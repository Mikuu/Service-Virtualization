package com.servicevirtualization.employee.representation.response;

public class UpdateSalaryResponseDTO {
    private String uid;
    private int previousSalary;
    private int currentSalary;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getPreviousSalary() {
        return previousSalary;
    }

    public void setPreviousSalary(int previousSalary) {
        this.previousSalary = previousSalary;
    }

    public int getCurrentSalary() {
        return currentSalary;
    }

    public void setCurrentSalary(int currentSalary) {
        this.currentSalary = currentSalary;
    }
}

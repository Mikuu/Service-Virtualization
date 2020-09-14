package com.servicevirtualization.salary.utils;

public class SalaryGenerator {
    public static int generateRandomSalary(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}

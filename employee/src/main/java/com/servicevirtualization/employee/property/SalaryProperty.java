package com.servicevirtualization.employee.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "salary-svc")
public class SalaryProperty {

    private String url;
    private String pathCreateSalary;
    private String pathFetchSalary;
    private String pathUpdateSalary;
    private String pathDeleteSalary;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPathCreateSalary() {
        return pathCreateSalary;
    }

    public void setPathCreateSalary(String pathCreateSalary) {
        this.pathCreateSalary = pathCreateSalary;
    }

    public String getPathFetchSalary() {
        return pathFetchSalary;
    }

    public void setPathFetchSalary(String pathFetchSalary) {
        this.pathFetchSalary = pathFetchSalary;
    }

    public String getPathUpdateSalary() {
        return pathUpdateSalary;
    }

    public void setPathUpdateSalary(String pathUpdateSalary) {
        this.pathUpdateSalary = pathUpdateSalary;
    }

    public String getPathDeleteSalary() {
        return pathDeleteSalary;
    }

    public void setPathDeleteSalary(String pathDeleteSalary) {
        this.pathDeleteSalary = pathDeleteSalary;
    }
}

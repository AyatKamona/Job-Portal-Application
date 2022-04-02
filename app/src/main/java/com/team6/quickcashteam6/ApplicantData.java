package com.team6.quickcashteam6;

public class ApplicantData {

    String employeeName;
    String employeeID;
    String jobTitle;
    String employeePhone;
    String key;
    String jobID;

    public ApplicantData(String employeeName, String employeeID, String jobTitle, String employeePhone, String key, String jobID){
        this.employeeName = employeeName;
        this.employeeID = employeeID;
        this.jobTitle = jobTitle;
        this.employeePhone = employeePhone;
        this.key = key;
        this.jobID = jobID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getJobID() {
        return jobID;
    }

    public void setJobID(String jobID) {
        this.jobID = jobID;
    }

    public ApplicantData(){

    }
}

package com.team6.quickcashteam6;

public class ApplicantData {

    String employeeName;
    String employeeID;
    String jobTitle;
    String employeePhone;

    public ApplicantData(String employeeName, String employeeID, String jobTitle, String employeePhone){
        this.employeeName = employeeName;
        this.employeeID = employeeID;
        this.jobTitle = jobTitle;
        this.employeePhone = employeePhone;
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

    public ApplicantData(){

    }
}

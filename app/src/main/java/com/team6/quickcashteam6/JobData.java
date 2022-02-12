package com.team6.quickcashteam6;

// This class acts as a container for all a job's information to be stored into FireBase.

public class JobData {

    String jobTitle;
    String payment;
    String startTime;
    String skills;
    String jobDescription;

    public JobData(String jobTitle, String payment, String startTime, String skills, String jobDescription) {
        this.jobTitle = jobTitle;
        this.payment = payment;
        this.startTime = startTime;
        this.skills = skills;
        this.jobDescription = jobDescription;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
}

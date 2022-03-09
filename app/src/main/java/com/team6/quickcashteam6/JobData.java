package com.team6.quickcashteam6;

// This class acts as a container for all a job's information to be stored into FireBase.

import com.google.android.gms.maps.model.LatLng;

public class JobData {

    String jobTitle;
    String payment;
    String startTime;
    String skills;
    String jobDescription;
    double jobLng;
    double jobLat;

    public JobData(String jobTitle, String payment, String startTime, String skills, String jobDescription, double jobLng, double jobLat) {
        this.jobTitle = jobTitle;
        this.payment = payment;
        this.startTime = startTime;
        this.skills = skills;
        this.jobDescription = jobDescription;
        this.jobLng = jobLng;
        this.jobLat = jobLat;
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

    public double getLng() { return jobLng; }

    public void setLng(double jobLng){ this.jobLng = jobLng;}

    public double getLat() { return jobLat; }

    public void setLat(double jobLat){ this.jobLat = jobLat;}
}

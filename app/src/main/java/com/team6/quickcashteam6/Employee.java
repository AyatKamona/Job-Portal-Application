package com.team6.quickcashteam6;

import java.util.ArrayList;

public class Employee extends User{
    float rating;
    int weight;
    private String currentJobID;
    public Employee (String ID, String name){
        super(ID, name);
        employeeSkills= new ArrayList<>();
        this.currentJobID = null;
    }
    public Employee (){

    }
    private ArrayList<String> employeeSkills;

    public void addSkills(ArrayList<String> skills){
        employeeSkills = skills;
    }

    public void addSingleSkill (String skill){
        employeeSkills.add(skill);
    }
    public ArrayList<String> getSkills(){
        return employeeSkills;
    }

    public String getCurrentJobID() {
        return currentJobID;
    }

    public float getRating() {
        return rating;
    }

    public void setCurrentJobID(String currentJobID) {
        this.currentJobID = currentJobID;
    }

}

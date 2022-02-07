package com.team6.quickcashteam6;

import java.util.ArrayList;

public class Employee extends User{
    public Employee (String name){
        super(name);
    }

    private ArrayList<String> employeeSkills;

    public void addSkills(ArrayList<String> skills){
        employeeSkills = skills;
    }

    public ArrayList<String> getSkills(){
        return employeeSkills;
    }
}

package com.team6.quickcashteam6;

import java.util.ArrayList;

public class Employee extends User{
    public Employee (String ID, String name){
        super(ID, name);
        employeeSkills= new ArrayList<>();
    }

    public Employee() {
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
}

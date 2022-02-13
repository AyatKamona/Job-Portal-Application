package com.team6.quickcashteam6;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

public class EmployerRecommendationActivityTest  {

    static RecommendationService recommendationService;
    String ID = RegisterActivity.userID;

    @Test
    public void getNameTest()  {
        User user= new User(ID, "Name");
        assertNotNull("wrong name retrieved", user.getName());
    }
    @Test
    public void getUserEmailTest(){
        User user= new User(ID, "Eli");
        user.setEmail("Eli@gmail.com");
        assertEquals("Wrong email returned",user.getEmail(),"Eli@gmail.com");
    }
    @Test
    public void getSkillsTest(){
        Employee user = new Employee(RegisterActivity.userID, "Guy");
        ArrayList<String> skills = new ArrayList<String>();
        skills.add("Dog Walking");
        user.addSkills(skills);
        assertEquals("Skills do not match", user.getSkills(), skills);
    }

    @Test
    public void addSingleSkill()  {

        Employee user = new Employee(RegisterActivity.userID, "John");
        user.addSingleSkill("Dog Walking");
        assertTrue(user.getSkills().contains("Dog Walking"));
    }

    @Test
    public void testRecommendation() {

        ArrayList<String> jobSkills = new ArrayList<>();
        ArrayList<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee(RegisterActivity.userID,"John Smith");
        Employee employee2 = new Employee(RegisterActivity.userID, "John Doe");
        Employee employee3 = new Employee(RegisterActivity.userID, "David Lee");

        employee1.addSingleSkill("Cleaning");
        employee1.addSingleSkill("Furniture assembly");
        employee2.addSingleSkill("Pet sitting");
        employee2.addSingleSkill("Yard Work");
        employee3.addSingleSkill("Cleaning");
        employee3.addSingleSkill("Heavy lifting");

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);

        jobSkills.add("Cleaning");
        jobSkills.add("Furniture assembly");

        ArrayList<Employee> potentialEmployee = new ArrayList<>();
        potentialEmployee = recommendationService.employerRecommendation(jobSkills, employees);

        assertEquals("Employee not found", "John Smith", potentialEmployee.get(0).getName());

    }
}
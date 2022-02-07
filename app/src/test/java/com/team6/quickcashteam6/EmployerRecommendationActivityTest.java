package com.team6.quickcashteam6;

import junit.framework.TestCase;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class EmployerRecommendationActivityTest  {
    @Test
    public void testRecommendation() {
        User user;
    }
    @Test
    public void getNameTest()  {
        User user= new User("blah");
        assertNotNull("wrong name retrieved", user.getName());
    }
    @Test
    public void getUserEmailTest(){
        User user= new User("Eli");
        user.setEmail("Eli@gmail.com");
        assertEquals("Wrong email returned",user.getEmail(),"Eli@gmail.com");
    }
    @Test
    public void getSkillsTest(){
        Employee user = new Employee("Guy");
        ArrayList<String> skills = new ArrayList<String>();
        skills.add("Dog Walking");
        user.setSkills(skills);
        assertEquals("Skills do not match", user.getSkills(), skills);
    }
}
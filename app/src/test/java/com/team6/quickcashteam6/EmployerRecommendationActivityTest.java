package com.team6.quickcashteam6;

import junit.framework.TestCase;

import org.junit.Test;
import static org.junit.Assert.*;
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
        assertEquals("Eli@gmail.com",user.getEmail(),"Wrong email returned");
    }
}
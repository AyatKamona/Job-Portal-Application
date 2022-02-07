package com.team6.quickcashteam6;

public class User {
    private String name;
    private String email;
    public User (String name){
        this.name=name;
    };

    public String getName(){return name;}

    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }
}

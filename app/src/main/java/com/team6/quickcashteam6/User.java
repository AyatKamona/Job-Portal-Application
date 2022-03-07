package com.team6.quickcashteam6;

public class User {
    private String ID;
    private String name;
    private String email;
    public User (String ID, String name){
        this.ID = ID;
        this.name=name;
    };

    public String getID() {
        return ID;
    }

    public String getName(){return name;}

    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }
}

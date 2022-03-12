package com.team6.quickcashteam6;

public class User {
    private String ID;
    private String name;
    private String email;
    private int age;
    private String phone;
    private String gender;
    private boolean isEmployee = false;
    private boolean isEmployer = false;
    public User (String ID, String name){
        this.ID = ID;
        this.name=name;
    };

    public String getID() {
        return ID;
    }
    public void setID(String ID){this.ID=ID;}
    public String getName(){return name;}

    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }

    public void setEmployee()  {
        isEmployee = true;
    }

    public void setEmployer()  {
        isEmployer = true;
    }

    public boolean isEmployee()  {
        return isEmployee;
    }

    public boolean isEmployer()  {
        return isEmployer;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }
}

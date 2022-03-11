package com.team6.quickcashteam6;

public class IDPairs {
    String userID;
    String databaseKey;
    public IDPairs (String UID, String databaseKey){
        this.userID =UID;
        this.databaseKey=databaseKey;
    }
    public String getUserID(){return userID;}
    public String getDatabaseKey(){return databaseKey;}

}

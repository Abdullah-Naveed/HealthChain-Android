package com.abdullahnaveed.fyp_health_chain;

public class UserStatus
{
    // static variable single_instance of type UserStatus
    private static UserStatus single_instance = null;

    // variable of type String
    private String userName;

    // private constructor restricted to this class itself
    private UserStatus(){}

    // static method to create instance of UserStatus class
    public static UserStatus getInstance() {
        if (single_instance == null)
            single_instance = new UserStatus();

        return single_instance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}

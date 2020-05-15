package com.example.coffeeshopinventorytracking;

import java.util.UUID;

public class User {

    private UUID mUUID;
    private String mUserName;
    private String mPassword;

    public User(){this(UUID.randomUUID());}

    private User(UUID id){mUUID = id;}

    public UUID getUUID() {
        return mUUID;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }
}

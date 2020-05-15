package com.example.coffeeshopinventorytracking.Coffees;

import java.util.UUID;

public class Coffee {

    private UUID mUUID;
    private String mGroundWhole;
    private String mRoast;
    private String mQuantity;
    private String mMinimum;

    Coffee(){this(UUID.randomUUID());}

    public Coffee(UUID id){mUUID = id;}

    public UUID getUUID() {
        return mUUID;
    }

    public String getGroundWhole() {
        return mGroundWhole;
    }

    public void setGroundWhole(String groundWhole) {
        mGroundWhole = groundWhole;
    }

    public String getRoast() {
        return mRoast;
    }

    public void setRoast(String roast) {
        mRoast = roast;
    }

    public String getQuantity() {
        return mQuantity;
    }

    public void setQuantity(String quantity) {
        mQuantity = quantity;
    }

    public String getMinimum() {
        return mMinimum;
    }

    public void setMinimum(String minimum) {
        mMinimum = minimum;
    }
}

package com.example.coffeeshopinventorytracking.Creams;

import java.util.UUID;

public class Cream {

    private UUID mUUID;
    private String mName;
    private String mDairy;
    private String mQuantity;
    private String mMinimum;

    Cream(){this(UUID.randomUUID());}

    public Cream(UUID id){
        mUUID = id;
    }

    public UUID getUUID() {return mUUID;}

    public String getName() {return mName;}

    public void setName(String name) {mName = name;}

    public String getDairy() {return mDairy;}

    public void setDairy(String dairy) {mDairy = dairy;}

    public String getQuantity() {return mQuantity;}

    public void setQuantity(String quantity) {mQuantity = quantity;}

    public String getMinimum() {return mMinimum;}

    public void setMinimum(String minimum) {mMinimum = minimum;}
}

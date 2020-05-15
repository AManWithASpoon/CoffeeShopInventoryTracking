package com.example.coffeeshopinventorytracking.Pastries;

import java.util.UUID;

public class Pastry {

    private UUID mUUID;
    private String mName;
    private String mQuantity;
    private String mMinimum;

    Pastry(){this(UUID.randomUUID());}

    public Pastry(UUID id){
        mUUID = id;
    }

    public UUID getUUID() {return mUUID;}

    public String getName() {return mName;}

    public void setName(String name) {mName = name;}

    public String getQuantity() {return mQuantity;}

    public void setQuantity(String quantity) {mQuantity = quantity;}

    public String getMinimum() {return mMinimum;}

    public void setMinimum(String minimum) {mMinimum = minimum;}
}

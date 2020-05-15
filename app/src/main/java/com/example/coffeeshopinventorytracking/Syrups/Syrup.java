package com.example.coffeeshopinventorytracking.Syrups;

import java.util.UUID;

public class Syrup {

    private UUID mUUID;
    private String mName;
    private String mSugar;
    private String mQuantity;
    private String mMinimum;

    public Syrup(){this(UUID.randomUUID());}

    public Syrup(UUID id){
        mUUID = id;
    }

    public UUID getUUID() {return mUUID;}

    public String getName() {return mName;}

    public void setName(String name) {mName = name;}

    public String getSugar() {return mSugar;}

    public void setSugar(String sugar) {mSugar = sugar;}

    public String getQuantity() {return mQuantity;}

    public void setQuantity(String quantity) {
        mQuantity = quantity;}

    public String getMinimum() {return mMinimum;}

    public void setMinimum(String minimum) {mMinimum = minimum;}
}

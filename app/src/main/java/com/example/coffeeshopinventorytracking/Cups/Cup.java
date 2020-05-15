package com.example.coffeeshopinventorytracking.Cups;

import java.util.UUID;

public class Cup {

    private UUID mUUID;
    private String mHotCold;
    private String mSize;
    private String mQuantity;
    private String mMinimum;
    private String LidId;

    Cup(){this(UUID.randomUUID());}

    public Cup(UUID id){ mUUID = id;}

    public UUID getUUID() {return mUUID;}

    public String getLidId() {return LidId;}

    public void setLidId(String lidId) {LidId = lidId;}

    public String getHotCold() {return mHotCold;}

    public void setHotCold(String hotCold) {mHotCold = hotCold;}

    public String getSize() {return mSize;}

    public void setSize(String size) {mSize = size;}

    public String getQuantity() {return mQuantity;}

    public void setQuantity(String quantity) {mQuantity = quantity;}

    public String getMinimum() {return mMinimum;}

    public void setMinimum(String minimum) {mMinimum = minimum;}

}

package com.example.coffeeshopinventorytracking.Lids;

import java.util.UUID;

public class Lid {

    private UUID mUUID;
    private String mHotCold;
    private String mSize;
    private String mQuantity;
    private String mMinimum;
    private String mCupId;

    Lid(){this(UUID.randomUUID());}

    public Lid(UUID id){mUUID = id;}

    public UUID getUUID() {
        return mUUID;
    }

    public String getHotCold() {
        return mHotCold;
    }

    public void setHotCold(String hotCold) {
        mHotCold = hotCold;
    }

    public String getSize() {
        return mSize;
    }

    public void setSize(String size) {
        mSize = size;
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

    public String getCupId() {
        return mCupId;
    }

    public void setCupId(String cupId) {
        mCupId = cupId;
    }
}

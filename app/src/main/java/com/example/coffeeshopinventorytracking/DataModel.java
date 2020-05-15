package com.example.coffeeshopinventorytracking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coffeeshopinventorytracking.Coffees.Coffee;
import com.example.coffeeshopinventorytracking.Creams.Cream;
import com.example.coffeeshopinventorytracking.Cups.Cup;
import com.example.coffeeshopinventorytracking.Database.DBHelper;
import com.example.coffeeshopinventorytracking.Database.DataCursorWrapper;
import com.example.coffeeshopinventorytracking.Lids.Lid;
import com.example.coffeeshopinventorytracking.Pastries.Pastry;
import com.example.coffeeshopinventorytracking.Syrups.Syrup;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.coffeeshopinventorytracking.Database.DbSchema.*;

public class DataModel {

    private static DataModel sDataModel;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static DataModel get(Context context){
        if(sDataModel == null){
            sDataModel = new DataModel(context);
        }
        return sDataModel;
    }

    private DataModel(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new DBHelper(mContext).getWritableDatabase();
    }

    /*
     *
     * Syrup Section
     *
     */

    public void addSyrup(Syrup syrup){
        ContentValues values = getSyrupValues(syrup);
        mDatabase.insert(SyrupTable.NAME, null, values);
    }

    public void deleteSyrup(Syrup syrup){
        String id = syrup.getUUID().toString();
        mDatabase.delete(SyrupTable.NAME,
                SyrupTable.Cols.UUID + " = ?", new String[]{id});
    }

    public void deleteAllSyrups(){
        mDatabase.delete(SyrupTable.NAME, null, null);
    }

    public void updateSyrup(Syrup syrup){
        String uuidString = syrup.getUUID().toString();
        ContentValues values = getSyrupValues(syrup);
        mDatabase.update(SyrupTable.NAME, values, SyrupTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    public List<Syrup> getSyrups(){

        List<Syrup> syrups = new ArrayList<>();
        DataCursorWrapper cursor = queryData(SyrupTable.NAME, null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                syrups.add(cursor.getSyrup());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return syrups;

    }

    public Syrup getSyrup(UUID id){

        DataCursorWrapper cursor = queryData(SyrupTable.NAME,
                SyrupTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getSyrup();
        }finally {
            cursor.close();
        }

    }

    private static ContentValues getSyrupValues(Syrup syrup){
        ContentValues values = new ContentValues();
        values.put(SyrupTable.Cols.UUID, syrup.getUUID().toString());
        values.put(SyrupTable.Cols.NAME, syrup.getName());
        values.put(SyrupTable.Cols.SUGAR, syrup.getSugar());
        values.put(SyrupTable.Cols.STOCK, syrup.getQuantity());
        values.put(SyrupTable.Cols.MINIMUM, syrup.getMinimum());
        return values;
    }

    /*
     *
     * Cups Section
     *
     */

    public void addCup(Cup cup){
        ContentValues values = getCupValues(cup);
        mDatabase.insert(CupTable.NAME, null, values);
    }

    public void deleteCup(Cup cup){
        String id = cup.getUUID().toString();
        mDatabase.delete(CupTable.NAME,
                CupTable.Cols.UUID + " = ?", new String[]{id});
    }

    public void updateCup(Cup cup){
        String uuidString = cup.getUUID().toString();
        ContentValues values = getCupValues(cup);
        mDatabase.update(CupTable.NAME, values, CupTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    public List<Cup> getCups(){

        List<Cup> cups = new ArrayList<>();
        DataCursorWrapper cursor = queryData(CupTable.NAME, null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                cups.add(cursor.getCup());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return cups;

    }

    public Cup getCup(UUID id){

        DataCursorWrapper cursor = queryData(CupTable.NAME,
                CupTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getCup();
        }finally {
            cursor.close();
        }

    }

    public Cup getCupSize(String size){

        DataCursorWrapper cursor = queryData(CupTable.NAME,
                CupTable.Cols.SIZE + " = ?",
                new String[]{size}
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getCup();
        }finally {
            cursor.close();
        }
    }


    private static ContentValues getCupValues(Cup cup){
        ContentValues values = new ContentValues();
        values.put(CupTable.Cols.UUID, cup.getUUID().toString());
        values.put(CupTable.Cols.HOTCOLD, cup.getHotCold());
        values.put(CupTable.Cols.SIZE, cup.getSize());
        values.put(CupTable.Cols.QUANTITY, cup.getQuantity());
        values.put(CupTable.Cols.MINIMUM, cup.getMinimum());
        values.put(CupTable.Cols.LID_FOREIGN_ID, cup.getLidId());
        return values;
    }

    /*
    *
    * Lids Section
    *
     */
    public void addLid(Lid lid){
        ContentValues values = getLidValues(lid);
        mDatabase.insert(LidTable.NAME, null, values);
    }

    public void deleteLid(Lid lid){
        String id = lid.getUUID().toString();
        mDatabase.delete(LidTable.NAME,
                LidTable.Cols.UUID + " = ?", new String[]{id});
    }

    public void updateLid(Lid lid){
        String uuidString = lid.getUUID().toString();
        ContentValues values = getLidValues(lid);
        mDatabase.update(LidTable.NAME, values, LidTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    public List<Lid> getLids(){

        List<Lid> lids = new ArrayList<>();
        DataCursorWrapper cursor = queryData(LidTable.NAME, null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                lids.add(cursor.getLid());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return lids;

    }

    public Lid getLid(UUID id){

        DataCursorWrapper cursor = queryData(LidTable.NAME,
                LidTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getLid();
        }finally {
            cursor.close();
        }
    }

    public Lid getLidSize(String size){

        DataCursorWrapper cursor = queryData(LidTable.NAME,
                LidTable.Cols.SIZE + " = ?",
                new String[]{size}
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getLid();
        }finally {
            cursor.close();
        }
    }

    private static ContentValues getLidValues(Lid lid){
        ContentValues values = new ContentValues();
        values.put(LidTable.Cols.UUID, lid.getUUID().toString());
        values.put(LidTable.Cols.HOTCOLD, lid.getHotCold());
        values.put(LidTable.Cols.SIZE, lid.getSize());
        values.put(LidTable.Cols.QUANTITY, lid.getQuantity());
        values.put(LidTable.Cols.MINIMUM, lid.getMinimum());
        values.put(LidTable.Cols.CUP_FOREIGN_ID, lid.getCupId());
        return values;
    }

    /*
     *
     * Pastry Section
     *
     */
    public void addPastry(Pastry pastry){
        ContentValues values = getPastryValues(pastry);
        mDatabase.insert(PastryTable.NAME, null, values);
    }

    public void deletePastry(Pastry pastry){
        String id = pastry.getUUID().toString();
        mDatabase.delete(PastryTable.NAME,
                PastryTable.Cols.UUID + " = ?", new String[]{id});
    }

    public void updatePastry(Pastry pastry){
        String uuidString = pastry.getUUID().toString();
        ContentValues values = getPastryValues(pastry);
        mDatabase.update(PastryTable.NAME, values, PastryTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    public List<Pastry> getPastries(){

        List<Pastry> pastries = new ArrayList<>();
        DataCursorWrapper cursor = queryData(PastryTable.NAME, null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                pastries.add(cursor.getPastry());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return pastries;

    }

    public Pastry getPastry(UUID id){

        DataCursorWrapper cursor = queryData(PastryTable.NAME,
                PastryTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getPastry();
        }finally {
            cursor.close();
        }
    }

    private static ContentValues getPastryValues(Pastry pastry){
        ContentValues values = new ContentValues();
        values.put(PastryTable.Cols.UUID, pastry.getUUID().toString());
        values.put(PastryTable.Cols.NAME, pastry.getName());
        values.put(PastryTable.Cols.QUANTITY, pastry.getQuantity());
        values.put(PastryTable.Cols.MINIMUM, pastry.getMinimum());
        return values;
    }

    /*
     *
     * Coffee Section
     *
     */
    public void addCoffee(Coffee coffee){
        ContentValues values = getCoffeeValues(coffee);
        mDatabase.insert(CoffeeTable.NAME, null, values);
    }

    public void deleteCoffee(Coffee coffee){
        String id = coffee.getUUID().toString();
        mDatabase.delete(CoffeeTable.NAME,
                CoffeeTable.Cols.UUID + " = ?", new String[]{id});
    }

    public void updateCoffee(Coffee coffee){
        String uuidString = coffee.getUUID().toString();
        ContentValues values = getCoffeeValues(coffee);
        mDatabase.update(CoffeeTable.NAME, values, CoffeeTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    public List<Coffee> getCoffees(){

        List<Coffee> coffees = new ArrayList<>();
        DataCursorWrapper cursor = queryData(CoffeeTable.NAME, null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                coffees.add(cursor.getCoffee());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return coffees;

    }

    public Coffee getCoffee(UUID id){

        DataCursorWrapper cursor = queryData(CoffeeTable.NAME,
                CoffeeTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getCoffee();
        }finally {
            cursor.close();
        }
    }

    private static ContentValues getCoffeeValues(Coffee coffee){
        ContentValues values = new ContentValues();
        values.put(CoffeeTable.Cols.UUID, coffee.getUUID().toString());
        values.put(CoffeeTable.Cols.GROUNDWHOLE, coffee.getGroundWhole());
        values.put(CoffeeTable.Cols.ROAST, coffee.getRoast());
        values.put(CoffeeTable.Cols.QUANTITY, coffee.getQuantity());
        values.put(CoffeeTable.Cols.MINIMUM, coffee.getMinimum());
        return values;
    }

    /*
     *
     * Cream Section
     *
     */
    public void addCream(Cream cream){
        ContentValues values = getCreamValues(cream);
        mDatabase.insert(CreamTable.NAME, null, values);
    }

    public void deleteCream(Cream cream){
        String id = cream.getUUID().toString();
        mDatabase.delete(CreamTable.NAME,
                CreamTable.Cols.UUID + " = ?", new String[]{id});
    }

    public void updateCream(Cream cream){
        String uuidString = cream.getUUID().toString();
        ContentValues values = getCreamValues(cream);
        mDatabase.update(CreamTable.NAME, values, CreamTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    public List<Cream> getCreams(){

        List<Cream> creams = new ArrayList<>();
        DataCursorWrapper cursor = queryData(CreamTable.NAME, null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                creams.add(cursor.getCream());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return creams;

    }

    public Cream getCream(UUID id){

        DataCursorWrapper cursor = queryData(CreamTable.NAME,
                CreamTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getCream();
        }finally {
            cursor.close();
        }
    }

    private static ContentValues getCreamValues(Cream cream){
        ContentValues values = new ContentValues();
        values.put(CreamTable.Cols.UUID, cream.getUUID().toString());
        values.put(CreamTable.Cols.NAME, cream.getName());
        values.put(CreamTable.Cols.DAIRY, cream.getDairy());
        values.put(CreamTable.Cols.QUANTITY, cream.getQuantity());
        values.put(CreamTable.Cols.MINIMUM, cream.getMinimum());
        return values;
    }

    public boolean getUsername(String username, String password){
        DataCursorWrapper cursor = queryData(UserTable.NAME,
                UserTable.Cols.USERNAME + " = ? and "+
                        UserTable.Cols.PASSWORD + " = ?",
                new String[]{username, password});

        try {
            if (cursor.getCount() == 0) {
                return false;
            }
            cursor.moveToFirst();
            return true;
        }finally {
            cursor.close();
        }
    }
    public List<Cream> generateReport(){

        List<Cream> creams = new ArrayList<>();

        DataCursorWrapper cursor = queryData(CreamTable.NAME,
                CreamTable.Cols.QUANTITY + " <= " + CreamTable.Cols.MINIMUM,null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                creams.add(cursor.getCream());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return creams;
    }

    public Cursor getData(){
        Cursor res = mDatabase.rawQuery("select * from "+CreamTable.NAME + " where "+ CreamTable.Cols.QUANTITY +" <= " + CreamTable.Cols.MINIMUM,null);
        return res;
    }
    private DataCursorWrapper queryData(String tableName, String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                tableName,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null

        );

        return new DataCursorWrapper(cursor);
    }

}

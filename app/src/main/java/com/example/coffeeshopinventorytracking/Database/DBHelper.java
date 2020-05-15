package com.example.coffeeshopinventorytracking.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.coffeeshopinventorytracking.User;

import static com.example.coffeeshopinventorytracking.Database.DbSchema.CoffeeTable;
import static com.example.coffeeshopinventorytracking.Database.DbSchema.CreamTable;
import static com.example.coffeeshopinventorytracking.Database.DbSchema.CupTable;
import static com.example.coffeeshopinventorytracking.Database.DbSchema.LidTable;
import static com.example.coffeeshopinventorytracking.Database.DbSchema.PastryTable;
import static com.example.coffeeshopinventorytracking.Database.DbSchema.SyrupTable;
import static com.example.coffeeshopinventorytracking.Database.DbSchema.UserTable;

public class DBHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "coffee_shop_db";

    public DBHelper(Context context){super(context, DATABASE_NAME, null, VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + SyrupTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                SyrupTable.Cols.UUID + ", " +
                SyrupTable.Cols.NAME + ", " +
                SyrupTable.Cols.SUGAR + ", " +
                SyrupTable.Cols.STOCK + ", " +
                SyrupTable.Cols.MINIMUM + ")");

        db.execSQL("create table " + CupTable.NAME +"(" +
                "_id integer primary key autoincrement, " +
                CupTable.Cols.UUID + ", " +
                CupTable.Cols.SIZE + ", " +
                CupTable.Cols.HOTCOLD + ", " +
                CupTable.Cols.QUANTITY + ", " +
                CupTable.Cols.MINIMUM + ", " +
                CupTable.Cols.LID_FOREIGN_ID + ", " +
                "foreign key(" +CupTable.Cols.LID_FOREIGN_ID + ") references " +
                LidTable.NAME + "(" + LidTable.Cols.UUID + "))");

        db.execSQL("create table " + LidTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                LidTable.Cols.UUID + ", " +
                LidTable.Cols.SIZE + ", " +
                LidTable.Cols.HOTCOLD + ", " +
                LidTable.Cols.QUANTITY + ", " +
                LidTable.Cols.MINIMUM + ", " +
                LidTable.Cols.CUP_FOREIGN_ID + ", " +
                "foreign key(" + LidTable.Cols.CUP_FOREIGN_ID + ") references " +
                CupTable.NAME + "(" + CupTable.Cols.UUID +"))");

        db.execSQL("create table " + PastryTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                PastryTable.Cols.UUID + ", " +
                PastryTable.Cols.NAME + ", " +
                PastryTable.Cols.QUANTITY + ", " +
                PastryTable.Cols.MINIMUM + ")");

        db.execSQL("create table " + CreamTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                CreamTable.Cols.UUID + ", " +
                CreamTable.Cols.NAME + ", " +
                CreamTable.Cols.DAIRY + ", " +
                CreamTable.Cols.QUANTITY + " integer, " +
                CreamTable.Cols.MINIMUM + " integer )");

        db.execSQL("create table " + CoffeeTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                CoffeeTable.Cols.UUID + ", " +
                CoffeeTable.Cols.GROUNDWHOLE + ", " +
                CoffeeTable.Cols.ROAST + ", " +
                CoffeeTable.Cols.QUANTITY + ", " +
                CoffeeTable.Cols.MINIMUM + ")");

        db.execSQL("create table " + UserTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                UserTable.Cols.UUID + ", " +
                UserTable.Cols.USERNAME + ", " +
                UserTable.Cols.PASSWORD + ")");
        users(db);
    }


    private void users(SQLiteDatabase db){
        User u = new User();
        Cursor c = db.query(UserTable.NAME,
                null,
                null,
                null,
                null,
                null,
                null);
        c.moveToFirst();
        if (c.getCount() == 0){
            u.setUserName("admin");
            u.setPassword("admin");
        }


        ContentValues values = getUserValues(u);
        db.insert(UserTable.NAME, null, values);

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private static ContentValues getUserValues(User user){
        ContentValues values = new ContentValues();
        values.put(UserTable.Cols.UUID, user.getUUID().toString());
        values.put(UserTable.Cols.USERNAME, user.getUserName());
        values.put(UserTable.Cols.PASSWORD, user.getPassword());
        return values;
    }
}

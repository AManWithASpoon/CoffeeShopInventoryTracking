package com.example.coffeeshopinventorytracking.Database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.coffeeshopinventorytracking.Coffees.Coffee;
import com.example.coffeeshopinventorytracking.Creams.Cream;
import com.example.coffeeshopinventorytracking.Cups.Cup;
import com.example.coffeeshopinventorytracking.Lids.Lid;
import com.example.coffeeshopinventorytracking.Pastries.Pastry;
import com.example.coffeeshopinventorytracking.Syrups.Syrup;

import java.util.UUID;

import static com.example.coffeeshopinventorytracking.Database.DbSchema.*;

public class DataCursorWrapper extends CursorWrapper {

    public DataCursorWrapper(Cursor cursor){super(cursor);}

    public Syrup getSyrup(){
        String uuidString = getString(getColumnIndex(SyrupTable.Cols.UUID));
        String name = getString(getColumnIndex(SyrupTable.Cols.NAME));
        String sugar = getString(getColumnIndex(SyrupTable.Cols.SUGAR));
        String setStockAmount = getString(getColumnIndex(SyrupTable.Cols.STOCK));
        String minimum = getString(getColumnIndex(SyrupTable.Cols.MINIMUM));

        Syrup syrup = new Syrup(UUID.fromString(uuidString));
        syrup.setName(name);
        syrup.setSugar(sugar);
        syrup.setQuantity(setStockAmount);
        syrup.setMinimum(minimum);

        return syrup;
    }

    public Cup getCup(){
        String uuidString = getString(getColumnIndex(CupTable.Cols.UUID));
        String hotCold = getString(getColumnIndex(CupTable.Cols.HOTCOLD));
        String size = getString(getColumnIndex(CupTable.Cols.SIZE));
        String stock = getString(getColumnIndex(CupTable.Cols.QUANTITY));
        String minimum = getString(getColumnIndex(CupTable.Cols.MINIMUM));
        String lidId = getString(getColumnIndex(CupTable.Cols.LID_FOREIGN_ID));

        Cup cup = new Cup(UUID.fromString(uuidString));
        cup.setHotCold(hotCold);
        cup.setSize(size);
        cup.setQuantity(stock);
        cup.setMinimum(minimum);
        cup.setLidId(lidId);

        return cup;
    }

    public Lid getLid(){
        String uuidString = getString(getColumnIndex(LidTable.Cols.UUID));
        String hotCold = getString(getColumnIndex(LidTable.Cols.HOTCOLD));
        String size = getString(getColumnIndex(LidTable.Cols.SIZE));
        String stock = getString(getColumnIndex(LidTable.Cols.QUANTITY));
        String minimum = getString(getColumnIndex(LidTable.Cols.MINIMUM));
        String cupId = getString(getColumnIndex(LidTable.Cols.CUP_FOREIGN_ID));

        Lid lid = new Lid(UUID.fromString(uuidString));
        lid.setHotCold(hotCold);
        lid.setSize(size);
        lid.setQuantity(stock);
        lid.setMinimum(minimum);
        lid.setCupId(cupId);

        return lid;
    }

    public Cream getCream(){
        String uuidString = getString(getColumnIndex(CreamTable.Cols.UUID));
        String name = getString(getColumnIndex(CreamTable.Cols.NAME));
        String dairy = getString(getColumnIndex(CreamTable.Cols.DAIRY));
        String quantity = getString(getColumnIndex(CreamTable.Cols.QUANTITY));
        String minimum = getString(getColumnIndex(CreamTable.Cols.MINIMUM));

        Cream cream = new Cream(UUID.fromString(uuidString));
        cream.setName(name);
        cream.setDairy(dairy);
        cream.setQuantity(quantity);
        cream.setMinimum(minimum);

        return cream;
    }

    public Coffee getCoffee(){
        String uuidString = getString(getColumnIndex(CoffeeTable.Cols.UUID));
        String grind = getString(getColumnIndex(CoffeeTable.Cols.GROUNDWHOLE));
        String roast = getString(getColumnIndex(CoffeeTable.Cols.ROAST));
        String stock = getString(getColumnIndex(CoffeeTable.Cols.QUANTITY));
        String minimum = getString(getColumnIndex(CoffeeTable.Cols.MINIMUM));

        Coffee coffee = new Coffee(UUID.fromString(uuidString));
        coffee.setGroundWhole(grind);
        coffee.setRoast(roast);
        coffee.setQuantity(stock);
        coffee.setMinimum(minimum);

        return coffee;
    }

    public Pastry getPastry(){
        String uuidString = getString(getColumnIndex(PastryTable.Cols.UUID));
        String name = getString(getColumnIndex(PastryTable.Cols.NAME));
        String stock = getString(getColumnIndex(PastryTable.Cols.QUANTITY));
        String minimum = getString(getColumnIndex(PastryTable.Cols.MINIMUM));

        Pastry pastry = new Pastry(UUID.fromString(uuidString));
        pastry.setName(name);
        pastry.setQuantity(stock);
        pastry.setMinimum(minimum);

        return pastry;
    }
}

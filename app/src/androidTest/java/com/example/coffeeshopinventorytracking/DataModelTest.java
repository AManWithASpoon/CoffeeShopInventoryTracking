package com.example.coffeeshopinventorytracking;

import androidx.test.platform.app.InstrumentationRegistry;

import com.example.coffeeshopinventorytracking.Syrups.Syrup;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DataModelTest {

    private DataModel mDataModel;

    @Before
    public void setUp(){
        mDataModel = DataModel.get(InstrumentationRegistry.getInstrumentation().getTargetContext());
    }

    @Test
    public void testPreConditions(){
        assertNotNull(mDataModel);
    }

    @Test
    public void addSyrup() {
        List<Syrup> syrups = mDataModel.getSyrups();
        int syrupSize = syrups.size() + 1;
        Syrup s = new Syrup();
        String name = "TestName" + syrupSize;
        String type = "TestType" + syrupSize;
        String quan = "TestQ" + syrupSize;
        String min = "TestM" + syrupSize;
        s.setName(name);
        s.setSugar(type);
        s.setMinimum(min);
        s.setQuantity(quan);
        mDataModel.addSyrup(s);
        List<Syrup> syrupsNew = mDataModel.getSyrups();

        int location = syrupsNew.size()-1;
        assertThat(syrupsNew.size(), is(syrups.size() + 1));
        assertEquals(syrupsNew.get(location).getName(), name);
        assertEquals(syrupsNew.get(location).getSugar(), type);
        assertEquals(syrupsNew.get(location).getQuantity(), quan);
        assertEquals(syrupsNew.get(location).getMinimum(), min);

    }

    @Test
    public void updateSyrup() {
        List<Syrup> syrups = mDataModel.getSyrups();
        int size = syrups.size();
        Syrup s = new Syrup();
        String name = "UpdateName" + size;
        String type = "UpdateType" + size;
        String quan = "UpdateQ" + size;
        String min = "UpdateM" + size;
        s.setName(name);
        s.setSugar(type);
        s.setMinimum(min);
        s.setQuantity(quan);
        mDataModel.addSyrup(s);

        String name2 = "UpdateName" + size + "A";
        String type2 = "UpdateType" + size + "A";
        String quan2 = "UpdateQ" + size + "A";
        String min2 = "UpdateM" + size + "A";
        s.setName(name2);
        s.setSugar(type2);
        s.setMinimum(min2);
        s.setQuantity(quan2);
        mDataModel.updateSyrup(s);
        List<Syrup> syrupsUpdate = mDataModel.getSyrups();

        int location = syrupsUpdate.size()-1;
        assertThat(syrupsUpdate.size(), is(syrups.size() + 1));
        assertEquals(syrupsUpdate.get(location).getName(), name2);
        assertEquals(syrupsUpdate.get(location).getSugar(), type2);
        assertEquals(syrupsUpdate.get(location).getQuantity(), quan2);
        assertEquals(syrupsUpdate.get(location).getMinimum(), min2);
        mDataModel.deleteSyrup(s);

    }

    @Test
    public void deleteSyrup() {
        List<Syrup> syrups = mDataModel.getSyrups();

        mDataModel.deleteSyrup(syrups.get(0));

        List<Syrup> updatedSyrups = mDataModel.getSyrups();
        assertThat(updatedSyrups.size(), is(syrups.size() - 1));

        mDataModel.deleteAllSyrups();
    }

}
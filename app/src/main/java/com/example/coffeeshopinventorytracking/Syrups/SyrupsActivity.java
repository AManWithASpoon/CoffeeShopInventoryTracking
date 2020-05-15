package com.example.coffeeshopinventorytracking.Syrups;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.coffeeshopinventorytracking.SingleFragmentActivity;

import java.util.UUID;

public class SyrupsActivity extends SingleFragmentActivity {

    private static final String EXTRA_SYRUP_ID = "com.example.coffeeshopinventorytracking.syrup_id";

    public static Intent newIntent(Context context, UUID syrupId){
        Intent intent = new Intent(context, SyrupsActivity.class);
        intent.putExtra(EXTRA_SYRUP_ID, syrupId);
        return intent;
    }
    @Override
    protected Fragment createFragment() {
        UUID syrupId = (UUID) getIntent().getSerializableExtra(EXTRA_SYRUP_ID);
        return SyrupsFragment.newInstance(syrupId);
    }
}

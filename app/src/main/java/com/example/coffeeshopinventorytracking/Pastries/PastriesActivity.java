package com.example.coffeeshopinventorytracking.Pastries;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.coffeeshopinventorytracking.SingleFragmentActivity;

import java.util.UUID;

public class PastriesActivity extends SingleFragmentActivity {

    private static final String EXTRA_PASTRY_ID = "com.example.coffeeshopinventorytracking.pastry_id";

    public static Intent newIntent(Context context, UUID uuid){
        Intent intent = new Intent(context, PastriesActivity.class);
        intent.putExtra(EXTRA_PASTRY_ID, uuid);
        return intent;
    }
    @Override
    protected Fragment createFragment() {
        UUID uuid = (UUID)getIntent().getSerializableExtra(EXTRA_PASTRY_ID);
        return PastriesFragment.newInstance(uuid);
    }
}

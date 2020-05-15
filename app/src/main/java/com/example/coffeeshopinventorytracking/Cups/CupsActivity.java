package com.example.coffeeshopinventorytracking.Cups;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.coffeeshopinventorytracking.SingleFragmentActivity;

import java.util.UUID;

public class CupsActivity extends SingleFragmentActivity {

    private static final String EXTRA_CUP_ID = "com.example.coffeeshopinventorytracking.cup_id";

    public static Intent newIntent(Context context, UUID cupId){
        Intent intent = new Intent(context, CupsActivity.class);
        intent.putExtra(EXTRA_CUP_ID, cupId);
        return intent;
    }
    @Override
    protected Fragment createFragment() {
        UUID cupId = (UUID) getIntent().getSerializableExtra(EXTRA_CUP_ID);
        return CupsFragment.newInstance(cupId);
    }
}

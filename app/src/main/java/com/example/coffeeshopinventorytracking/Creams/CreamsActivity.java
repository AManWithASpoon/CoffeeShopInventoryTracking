package com.example.coffeeshopinventorytracking.Creams;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.coffeeshopinventorytracking.SingleFragmentActivity;

import java.util.UUID;

public class CreamsActivity extends SingleFragmentActivity {

    private static final String EXTRA_CREAM_ID = "com.example.coffeeshopinventorytracking.cream_id";

    public static Intent newIntent(Context context, UUID uuid){
        Intent intent = new Intent(context, CreamsActivity.class);
        intent.putExtra(EXTRA_CREAM_ID, uuid);
        return intent;
    }
    @Override
    protected Fragment createFragment() {
        UUID uuid = (UUID) getIntent().getSerializableExtra(EXTRA_CREAM_ID);
        return CreamsFragment.newInstance(uuid);
    }
}

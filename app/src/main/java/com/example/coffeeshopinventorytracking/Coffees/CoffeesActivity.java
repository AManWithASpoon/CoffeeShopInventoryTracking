package com.example.coffeeshopinventorytracking.Coffees;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.coffeeshopinventorytracking.SingleFragmentActivity;

import java.util.UUID;

public class CoffeesActivity extends SingleFragmentActivity {

    private static final String EXTRA_COFFEE_ID = "com.example.coffeeshopinventorytracking.coffee_id";

    public static Intent newIntent(Context context, UUID uuid){
        Intent intent = new Intent(context, CoffeesActivity.class);
        intent.putExtra(EXTRA_COFFEE_ID, uuid);
        return intent;
    }
    @Override
    protected Fragment createFragment() {
        UUID uuid = (UUID) getIntent().getSerializableExtra(EXTRA_COFFEE_ID);
        return CoffeesFragment.newInstance(uuid);
    }
}

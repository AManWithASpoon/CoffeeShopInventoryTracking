package com.example.coffeeshopinventorytracking.Lids;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.coffeeshopinventorytracking.SingleFragmentActivity;

import java.util.UUID;

public class LidsActivity extends SingleFragmentActivity {

    private static final String EXTRA_LID_ID = "com.example.coffeeshopinventorytracking.lid_id";

    public static Intent newIntent(Context context, UUID lidId){
        Intent intent = new Intent(context, LidsActivity.class);
        intent.putExtra(EXTRA_LID_ID, lidId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID lidId = (UUID) getIntent().getSerializableExtra(EXTRA_LID_ID);
        return LidsFragment.newInstance(lidId);
    }
}

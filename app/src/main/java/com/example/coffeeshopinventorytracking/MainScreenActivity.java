package com.example.coffeeshopinventorytracking;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

public class MainScreenActivity extends SingleFragmentActivity {

    private static final String EXTRA_ = "com.example.coffeeshopinventorytracking";

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context, MainScreenActivity.class);
        return intent;
    }
    @Override
    protected Fragment createFragment(){
        return MainScreenFragment.newInstance();
    }

}

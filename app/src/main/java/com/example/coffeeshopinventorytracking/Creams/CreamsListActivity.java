package com.example.coffeeshopinventorytracking.Creams;

import androidx.fragment.app.Fragment;

import com.example.coffeeshopinventorytracking.SingleFragmentActivity;

public class CreamsListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CreamsListFragment();
    }
}

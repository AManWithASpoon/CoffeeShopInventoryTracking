package com.example.coffeeshopinventorytracking.Pastries;

import androidx.fragment.app.Fragment;

import com.example.coffeeshopinventorytracking.SingleFragmentActivity;

public class PastriesListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new PastriesListFragment();
    }
}

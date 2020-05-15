package com.example.coffeeshopinventorytracking.Cups;

import androidx.fragment.app.Fragment;

import com.example.coffeeshopinventorytracking.SingleFragmentActivity;

public class CupsListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CupsListFragment();
    }
}

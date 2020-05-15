package com.example.coffeeshopinventorytracking.Syrups;

import androidx.fragment.app.Fragment;

import com.example.coffeeshopinventorytracking.SingleFragmentActivity;

public class SyrupsListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new SyrupsListFragment();
    }
}

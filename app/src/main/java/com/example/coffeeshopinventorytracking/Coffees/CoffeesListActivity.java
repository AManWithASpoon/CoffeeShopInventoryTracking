package com.example.coffeeshopinventorytracking.Coffees;

import androidx.fragment.app.Fragment;

import com.example.coffeeshopinventorytracking.SingleFragmentActivity;

public class CoffeesListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CoffeesListFragment();
    }
}

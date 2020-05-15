package com.example.coffeeshopinventorytracking.Lids;

import androidx.fragment.app.Fragment;

import com.example.coffeeshopinventorytracking.SingleFragmentActivity;

public class LidsListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new LidsListFragment();
    }
}

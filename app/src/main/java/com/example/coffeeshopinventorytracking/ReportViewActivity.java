package com.example.coffeeshopinventorytracking;

import androidx.fragment.app.Fragment;

public class ReportViewActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ReportViewFragment();
    }
}

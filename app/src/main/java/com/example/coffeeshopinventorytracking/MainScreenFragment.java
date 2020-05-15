package com.example.coffeeshopinventorytracking;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.coffeeshopinventorytracking.Coffees.CoffeesListActivity;
import com.example.coffeeshopinventorytracking.Creams.Cream;
import com.example.coffeeshopinventorytracking.Creams.CreamsListActivity;
import com.example.coffeeshopinventorytracking.Cups.CupsListActivity;
import com.example.coffeeshopinventorytracking.Database.DataCursorWrapper;
import com.example.coffeeshopinventorytracking.Lids.LidsListActivity;
import com.example.coffeeshopinventorytracking.Pastries.PastriesListActivity;
import com.example.coffeeshopinventorytracking.Syrups.SyrupsListActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainScreenFragment extends Fragment {

    private Button mSyrups;
    private Button mCoffee;
    private Button mLids;
    private Button mCups;
    private Button mPastries;
    private Button mCreams;
    private Button mReport;

    public static MainScreenFragment newInstance(){
        Bundle args = new Bundle();
        MainScreenFragment fragment = new MainScreenFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.screen_main_fragment, container, false);

        mSyrups = v.findViewById(R.id.mainScreenSyrupButton);
        mSyrups.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), SyrupsListActivity.class);
            startActivity(intent);
        });

        mCups = v.findViewById(R.id.mainScreenCupsButton);
        mCups.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), CupsListActivity.class);
            startActivity(intent);
        });

        mLids = v.findViewById(R.id.mainScreenLidsButton);
        mLids.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), LidsListActivity.class);
            startActivity(intent);
        });

        mCoffee = v.findViewById(R.id.mainScreenCoffeeButton);
        mCoffee.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), CoffeesListActivity.class);
            startActivity(intent);
        });

        mPastries = v.findViewById(R.id.mainScreenPastriesButton);
        mPastries.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), PastriesListActivity.class);
            startActivity(intent);
        });

        mCreams = v.findViewById(R.id.mainScreenCreamsButton);
        mCreams.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), CreamsListActivity.class);
            startActivity(intent);
        });

        mReport = v.findViewById(R.id.generate_report_button);
        mReport.setOnClickListener(view -> {
            DataModel dataModel = DataModel.get(getActivity());
            if (dataModel.generateReport().size() <= 0 || dataModel.generateReport() == null){
                Toast.makeText(getActivity(), "No Cream is low on inventory", Toast.LENGTH_LONG).show();
            }else {
                Intent intent = new Intent(getActivity(), ReportViewActivity.class);
                startActivity(intent);
            }

        });
        return v;
    }
}

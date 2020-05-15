package com.example.coffeeshopinventorytracking;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.coffeeshopinventorytracking.Creams.Cream;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class ReportViewFragment extends Fragment {

    ListView mListView;
    ArrayList<Cream> mCreams;
    ArrayList<String> mStrings;
    TextView mTitle;
    TextView mTimeStamp;
    Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            mActivity = (Activity) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.report_list,container,  false);
        mCreams = new ArrayList<>(DataModel.get(getActivity()).generateReport());
        mStrings = new ArrayList<>();
        for (Cream cream : mCreams) {
            String builder = ("Name: " + cream.getName() + "\n") +
                    "Dairy: " + cream.getDairy() + "\n" +
                    "Minimum: " + cream.getMinimum() + "\n" +
                    "Quantity: " + cream.getQuantity() + "\n\n";
            mStrings.add(builder);
        }

        ListAdapter adapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_list_item_1, mStrings);
        mListView = v.findViewById(R.id.list_view_report);
        mListView.setAdapter(adapter);

        mTitle = v.findViewById(R.id.title_report);
        mTitle.setText(R.string.report_title);

        mTimeStamp = v.findViewById(R.id.date_time_stamp);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm a", Locale.getDefault());
        mTimeStamp.setText(format1.format(calendar.getTime()));

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }




}

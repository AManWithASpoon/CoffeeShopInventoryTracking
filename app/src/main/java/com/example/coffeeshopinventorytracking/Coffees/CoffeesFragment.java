package com.example.coffeeshopinventorytracking.Coffees;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.coffeeshopinventorytracking.DataModel;
import com.example.coffeeshopinventorytracking.DeleteDialog;
import com.example.coffeeshopinventorytracking.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class CoffeesFragment extends Fragment {

    private static final String ARG_COFFEE_ID = "coffee_id";
    private static final String DIALOG_DELETE = "delete";

    private static final int REQUEST_DELETE = 1;
    private static final int REQUEST_OK = 0;

    private Coffee mCoffee;
    private boolean closeT;
    private Activity mActivity;
    private TextView mTitleTv;
    private TextView mTypeTv;
    private TextView mSizeTv;
    private TextView mStockTv;
    private TextView mMinTv;
    private TextView mLidsTv;
    private EditText mStockEt;
    private EditText mMinEt;
    private Spinner mTypeSpinner;
    private Spinner mRoastSpinner;
    private Spinner mLidSpinner;
    private Button mSave;
    private Button mDelete;
    private Button mCancel;
    private List<Coffee> coffees;
    private DataModel mDataModel;
    private Boolean mSaveExit = false;
    ArrayAdapter mTypeAdapter;
    ArrayAdapter mRoastAdapter;

    public static CoffeesFragment newInstance(UUID uuid){
        Bundle args = new Bundle();
        args.putSerializable(ARG_COFFEE_ID, uuid);
        CoffeesFragment fragment = new CoffeesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID uuid = (UUID) getArguments().getSerializable(ARG_COFFEE_ID);
        mCoffee = DataModel.get(getActivity()).getCoffee(uuid);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            mActivity = (Activity) context;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mSaveExit) {
            DataModel.get(getActivity()).updateCoffee(mCoffee);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.screen_2_fragment, container, false);

        mTitleTv = v.findViewById(R.id.itemView_Title_Fragment2);
        mTitleTv.setText(R.string.coffee);

        mTypeTv = v.findViewById(R.id.text_1_Fragment2);
        mTypeTv.setText(R.string.ground_whole);

        mSizeTv = v.findViewById(R.id.text_2_Fragment2);
        mSizeTv.setText(R.string.roast);

        mStockTv = v.findViewById(R.id.text_3_Fragment2);
        mStockTv.setText(R.string.stock);

        mMinTv = v.findViewById(R.id.text_4_Fragment2);
        mMinTv.setText(R.string.minimum);

        mLidsTv = v.findViewById(R.id.text_5_Fragment2);
        mLidsTv.setVisibility(View.GONE);

        mStockEt = v.findViewById(R.id.edit_3_Fragment2);
        mStockEt.setText(mCoffee.getQuantity());
        mStockEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mCoffee.setQuantity(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        mMinEt = v.findViewById(R.id.edit_4_Fragment2);
        mMinEt.setText(mCoffee.getMinimum());
        mMinEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mCoffee.setMinimum(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        mLidSpinner = v.findViewById(R.id.spinner_3_Fragment2);
        mLidSpinner.setVisibility(View.GONE);

        mRoastSpinner = v.findViewById(R.id.spinner_2_Fragment2);
        ArrayList<String> roastList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.roast)));
        mRoastAdapter = new ArrayAdapter(mActivity, android.R.layout.simple_spinner_item, roastList);
        mRoastAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mRoastSpinner.setAdapter(mRoastAdapter);
        if (mCoffee.getRoast() != null){
            String s = mCoffee.getRoast();
            mRoastSpinner.setSelection(roastList.indexOf(s));
        }
        mRoastSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String roast = adapterView.getItemAtPosition(i).toString();
                mCoffee.setRoast(roast);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mTypeSpinner = v.findViewById(R.id.spinner_1_Fragment2);
        ArrayList<String> typeList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.beans)));
        mTypeAdapter = new ArrayAdapter(mActivity, android.R.layout.simple_spinner_item, typeList);
        mTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mTypeSpinner.setAdapter(mTypeAdapter);
        if (mCoffee.getGroundWhole() != null){
            String s = mCoffee.getGroundWhole();
            mTypeSpinner.setSelection(typeList.indexOf(s));
        }
        mTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String type = adapterView.getItemAtPosition(i).toString();
                mCoffee.setGroundWhole(type);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        mSave = v.findViewById(R.id.saveButton_Fragment2);
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataModel.get(mActivity).updateCoffee(mCoffee);
                close(true);
            }
        });
        mDelete = v.findViewById(R.id.deleteButton_Fragment2);
        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();

            }
        });

        mCancel = v.findViewById(R.id.cancelButton_Fragment2);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                close(false);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        closeT = (boolean) data.getSerializableExtra(DeleteDialog.EXTRA_DELETE);
        DataModel.get(mActivity).deleteCoffee(mCoffee);
        if (closeT){
            close(true);
        }
    }

    private void close(Boolean save){
        mSaveExit = save;
        Intent intent = new Intent(getActivity(), CoffeesListActivity.class);
        startActivity(intent);
    }

    private void openDialog(){
        FragmentManager manager = getFragmentManager();
        String title = getResources().getString(R.string.delete_coffee_title) +" "+ mCoffee.getRoast();
        String message = getResources().getString(R.string.delete_coffee_message) +" "+ mCoffee.getRoast();
        DeleteDialog dialog = DeleteDialog.newInstance(message, title);
        dialog.setTargetFragment(CoffeesFragment.this, REQUEST_OK);
        dialog.show(manager, DIALOG_DELETE);
    }
}

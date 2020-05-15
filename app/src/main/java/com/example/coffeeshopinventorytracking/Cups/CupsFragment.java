package com.example.coffeeshopinventorytracking.Cups;

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
import com.example.coffeeshopinventorytracking.Lids.Lid;
import com.example.coffeeshopinventorytracking.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class CupsFragment extends Fragment {

    private static final String ARG_CUP_ID = "cup_id";
    private static final String DIALOG_DELETE = "delete";

    private static final int REQUEST_DELETE = 1;
    private static final int REQUEST_OK = 0;

    private Cup mCup;
    private boolean closeT;
    private Lid mLid;
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
    private Spinner mSizeSpinner;
    private Spinner mLidSpinner;
    private Button mSave;
    private Button mDelete;
    private Button mCancel;
    private List<Lid> lids;
    private List<String> lidSizes;
    private DataModel mDataModel;
    private Boolean mSaveExit = false;
    ArrayAdapter mTypeAdapter;
    ArrayAdapter mSizeAdapter;
    ArrayAdapter mLidsAdapter;

    public static CupsFragment newInstance(UUID id){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CUP_ID, id);
        CupsFragment fragment = new CupsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID cupId = (UUID) getArguments().getSerializable(ARG_CUP_ID);
        mCup = DataModel.get(getActivity()).getCup(cupId);
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
            DataModel.get(getActivity()).updateCup(mCup);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.screen_2_fragment, container, false);
        setSpinner();

        mTitleTv = v.findViewById(R.id.itemView_Title_Fragment2);
        mTitleTv.setText(R.string.cup);

        mTypeTv = v.findViewById(R.id.text_1_Fragment2);
        mTypeTv.setText(R.string.type);

        mSizeTv = v.findViewById(R.id.text_2_Fragment2);
        mSizeTv.setText(R.string.size);

        mStockTv = v.findViewById(R.id.text_3_Fragment2);
        mStockTv.setText(R.string.stock);

        mMinTv = v.findViewById(R.id.text_4_Fragment2);
        mMinTv.setText(R.string.minimum);

        mLidsTv = v.findViewById(R.id.text_5_Fragment2);
        mLidsTv.setText(R.string.lid);

        mStockEt = v.findViewById(R.id.edit_3_Fragment2);
        mStockEt.setText(mCup.getQuantity());
        mStockEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mCup.setQuantity(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        mMinEt = v.findViewById(R.id.edit_4_Fragment2);
        mMinEt.setText(mCup.getMinimum());
        mMinEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mCup.setMinimum(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mLidSpinner = v.findViewById(R.id.spinner_3_Fragment2);
        mLidsAdapter = new ArrayAdapter(mActivity, android.R.layout.simple_spinner_dropdown_item, lidSizes);
        mLidSpinner.setAdapter(mLidsAdapter);
        if (mCup.getLidId() != null){
            String s = mLid.getSize();
            mLidSpinner.setSelection(lidSizes.indexOf(s), true);
        }
        mLidSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String lid = adapterView.getItemAtPosition(i).toString();
                Lid lid1 = mDataModel.getLidSize(lid);
                mCup.setLidId(lid1.getUUID().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mSizeSpinner = v.findViewById(R.id.spinner_2_Fragment2);
        ArrayList<String> sizeList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.ounces)));
        mSizeAdapter = new ArrayAdapter(mActivity, android.R.layout.simple_spinner_item, sizeList);
        mSizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSizeSpinner.setAdapter(mSizeAdapter);
        if (mCup.getSize() != null){
            String s = mCup.getSize();
            mSizeSpinner.setSelection(sizeList.indexOf(s));
        }
        mSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String size = adapterView.getItemAtPosition(i).toString();
                mCup.setSize(size);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mTypeSpinner = v.findViewById(R.id.spinner_1_Fragment2);
        ArrayList<String> typeList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.hot_cold)));
        mTypeAdapter = new ArrayAdapter(mActivity, android.R.layout.simple_spinner_item, typeList);
        mTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mTypeSpinner.setAdapter(mTypeAdapter);
        if (mCup.getHotCold() != null){
            String s = mCup.getHotCold();
            mTypeSpinner.setSelection(typeList.indexOf(s));
        }
        mTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String type = adapterView.getItemAtPosition(i).toString();
                mCup.setHotCold(type);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        mSave = v.findViewById(R.id.saveButton_Fragment2);
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataModel.get(mActivity).updateCup(mCup);
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

    private void setSpinner(){
        mDataModel = DataModel.get(mActivity);
        lids = mDataModel.getLids();
        lidSizes = new ArrayList<>();
        if (mCup.getLidId() != null){
            mLid = mDataModel.getLid(UUID.fromString(mCup.getLidId()));
        }
        for (Lid lid: lids){
            lidSizes.add(lid.getSize());
        }
    }

    private void close(Boolean save){
        mSaveExit = save;
        Intent intent = new Intent(getActivity(), CupsListActivity.class);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        closeT = (boolean) data.getSerializableExtra(DeleteDialog.EXTRA_DELETE);
        DataModel.get(mActivity).deleteCup(mCup);
        if (closeT){
            close(true);
        }
    }

    private void openDialog(){
        FragmentManager manager = getFragmentManager();
        String title = getResources().getString(R.string.delete_cup_title)
                +" "+ mCup.getHotCold() +" "+ mCup.getSize();
        String message = getResources().getString(R.string.delete_cup_message)
                +" "+ mCup.getHotCold() +" "+ mCup.getSize();
        DeleteDialog dialog = DeleteDialog.newInstance(message, title);
        dialog.setTargetFragment(CupsFragment.this, REQUEST_OK);
        dialog.show(manager, DIALOG_DELETE);
    }
}

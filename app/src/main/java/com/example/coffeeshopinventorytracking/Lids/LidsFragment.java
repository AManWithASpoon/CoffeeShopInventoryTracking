package com.example.coffeeshopinventorytracking.Lids;

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

import com.example.coffeeshopinventorytracking.Cups.Cup;
import com.example.coffeeshopinventorytracking.DataModel;
import com.example.coffeeshopinventorytracking.DeleteDialog;
import com.example.coffeeshopinventorytracking.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class LidsFragment extends Fragment {

    private static final String ARG_LID_ID = "lid_id";
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
    private TextView mCupsTv;
    private EditText mStockEt;
    private EditText mMinEt;
    private Spinner mTypeSpinner;
    private Spinner mSizeSpinner;
    private Spinner mCupSpinner;
    private Button mSave;
    private Button mDelete;
    private Button mCancel;
    private List<Cup> cups;
    private List<String> cupSizes;
    private DataModel mDataModel;
    private Boolean mSaveExit = false;
    ArrayAdapter mTypeAdapter;
    ArrayAdapter mSizeAdapter;
    ArrayAdapter mCupsAdapter;

    public static LidsFragment newInstance(UUID id){
        Bundle args = new Bundle();
        args.putSerializable(ARG_LID_ID, id);
        LidsFragment fragment = new LidsFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID lidId = (UUID) getArguments().getSerializable(ARG_LID_ID);
        mLid = DataModel.get(getActivity()).getLid(lidId);
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
            DataModel.get(getActivity()).updateLid(mLid);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.screen_2_fragment, container, false);
        setSpinner();

        mTitleTv = v.findViewById(R.id.itemView_Title_Fragment2);
        mTitleTv.setText(R.string.lid);

        mTypeTv = v.findViewById(R.id.text_1_Fragment2);
        mTypeTv.setText(R.string.type);

        mSizeTv = v.findViewById(R.id.text_2_Fragment2);
        mSizeTv.setText(R.string.size);

        mStockTv = v.findViewById(R.id.text_3_Fragment2);
        mStockTv.setText(R.string.stock);

        mMinTv = v.findViewById(R.id.text_4_Fragment2);
        mMinTv.setText(R.string.minimum);

        mCupsTv = v.findViewById(R.id.text_5_Fragment2);
        mCupsTv.setText(R.string.cup);

        mStockEt = v.findViewById(R.id.edit_3_Fragment2);
        mStockEt.setText(mLid.getQuantity());
        mStockEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mLid.setQuantity(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        mMinEt = v.findViewById(R.id.edit_4_Fragment2);
        mMinEt.setText(mLid.getMinimum());
        mMinEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mLid.setMinimum(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mCupSpinner = v.findViewById(R.id.spinner_3_Fragment2);
        mCupsAdapter = new ArrayAdapter(mActivity, android.R.layout.simple_spinner_dropdown_item, cupSizes);
        mCupSpinner.setAdapter(mCupsAdapter);
        if (mLid.getCupId() != null){
            String s = mCup.getSize();
            mCupSpinner.setSelection(cupSizes.indexOf(s), true);
        }
        mCupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String c = adapterView.getItemAtPosition(i).toString();
                Cup cup = mDataModel.getCupSize(c);
                mLid.setCupId(cup.getUUID().toString());
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
        if (mLid.getSize() != null){
            String s = mLid.getSize();
            mSizeSpinner.setSelection(sizeList.indexOf(s));
        }
        mSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String size = adapterView.getItemAtPosition(i).toString();
                mLid.setSize(size);
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
        if (mLid.getHotCold() != null){
            String s = mLid.getHotCold();
            mTypeSpinner.setSelection(typeList.indexOf(s));
        }
        mTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String type = adapterView.getItemAtPosition(i).toString();
                mLid.setHotCold(type);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        mSave = v.findViewById(R.id.saveButton_Fragment2);
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataModel.get(mActivity).updateLid(mLid);
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
        cups = mDataModel.getCups();
        cupSizes = new ArrayList<>();
        if (mLid.getCupId() != null){
            mCup = mDataModel.getCup(UUID.fromString(mLid.getCupId()));
        }
        for (Cup cup: cups){
            cupSizes.add(cup.getSize());
        }
    }

    private void close(Boolean save){
        mSaveExit = save;
        Intent intent = new Intent(getActivity(), LidsListActivity.class);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        closeT = (boolean) data.getSerializableExtra(DeleteDialog.EXTRA_DELETE);
        DataModel.get(mActivity).deleteLid(mLid);
        if (closeT){
            close(true);
        }
    }

    private void openDialog(){
        FragmentManager manager = getFragmentManager();
        String title = getResources().getString(R.string.delete_lid_title)
                +" "+ mLid.getHotCold() +" "+ mLid.getSize();
        String message = getResources().getString(R.string.delete_lid_message)
                +" "+ mLid.getHotCold() +" "+ mLid.getSize();
        DeleteDialog dialog = DeleteDialog.newInstance(message, title);
        dialog.setTargetFragment(LidsFragment.this, REQUEST_OK);
        dialog.show(manager, DIALOG_DELETE);
    }
}

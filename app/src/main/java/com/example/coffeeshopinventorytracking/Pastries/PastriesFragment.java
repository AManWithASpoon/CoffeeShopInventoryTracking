package com.example.coffeeshopinventorytracking.Pastries;

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

import java.util.UUID;

public class PastriesFragment extends Fragment {

    private static final String ARG_PASTRY_ID = "pastry_id";
    private static final String DIALOG_DELETE = "delete";

    private static final int REQUEST_DELETE = 1;
    private static final int REQUEST_OK = 0;


    private Pastry mPastry;
    private boolean closeT;
    private EditText mNameEt;
    private EditText mStockAmount;
    private EditText mMinimum;
    private Spinner mSpinner;
    private Button mSave;
    private Button mCancel;
    private Button mDelete;
    private Activity mActivity;
    private TextView mNameTv;
    private TextView mTypeTv;
    private TextView mStock;
    private TextView mMin;
    private TextView mTitle;
    private Boolean mSaveExit = false;

    Context mContext;

    public static PastriesFragment newInstance(UUID uuid){
        Bundle args = new Bundle();
        args.putSerializable(ARG_PASTRY_ID, uuid);
        PastriesFragment fragment = new PastriesFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        UUID pastry = (UUID) getArguments().getSerializable(ARG_PASTRY_ID);
        mPastry = DataModel.get(getActivity()).getPastry(pastry);
        mActivity = getActivity();
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
            DataModel.get(getActivity()).updatePastry(mPastry);
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.screen_1_fragment, container, false);

        mTitle = v.findViewById(R.id.itemView_Title_Fragment1);
        mTitle.setText(getResources().getString(R.string.pastry));
        mNameTv = v.findViewById(R.id.text_1_Fragment1);
        mNameTv.setText(getResources().getString(R.string.name));
        mTypeTv = v.findViewById(R.id.text_2_Fragment1);
        mTypeTv.setVisibility(View.GONE);
        mStock = v.findViewById(R.id.text_3_Fragment1);
        mStock.setText(getResources().getString(R.string.stock));
        mMin = v.findViewById(R.id.text_4_Fragment1);
        mMin.setText(getResources().getString(R.string.minimum));
        mNameEt = v.findViewById(R.id.edit_1_Fragment1);
        mNameEt.setText(mPastry.getName());
        mNameEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPastry.setName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mSpinner = v.findViewById(R.id.spinner_1_Fragment1);
        mSpinner.setVisibility(View.GONE);

        mStockAmount = v.findViewById(R.id.edit_3_fragment1);
        mStockAmount.setText(mPastry.getQuantity());
        mStockAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPastry.setQuantity(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mMinimum = v.findViewById(R.id.edit_4_Fragment1);
        mMinimum.setText(mPastry.getMinimum());
        mMinimum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPastry.setMinimum(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mSave = v.findViewById(R.id.saveButton_Fragment1);
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataModel.get(mActivity).updatePastry(mPastry);
                close(true);
            }
        });
        mDelete = v.findViewById(R.id.deleteButton_Fragment1);
        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();

            }
        });
        mCancel = v.findViewById(R.id.cancelButton_Fragment1);
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
        DataModel.get(mActivity).deletePastry(mPastry);
        if (closeT){
            close(true);
        }
    }
    private void close(Boolean save){
        mSaveExit = save;
        Intent intent = new Intent(getActivity(), PastriesListActivity.class);
        startActivity(intent);
    }


    private void openDialog(){
        FragmentManager manager = getFragmentManager();
        String title = getResources().getString(R.string.delete_pastry_title) +" "+ mPastry.getName();
        String message = getResources().getString(R.string.delete_pastry_message) +" "+ mPastry.getName();
        DeleteDialog dialog = DeleteDialog.newInstance(message, title);
        dialog.setTargetFragment(PastriesFragment.this, REQUEST_OK);
        dialog.show(manager, DIALOG_DELETE);
    }
}

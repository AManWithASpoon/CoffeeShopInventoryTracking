package com.example.coffeeshopinventorytracking.Creams;

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
import java.util.UUID;

public class CreamsFragment extends Fragment {

    private static final String ARG_CREAM_ID = "cream_id";
    private static final String DIALOG_DELETE = "delete";

    private static final int REQUEST_DELETE = 1;
    private static final int REQUEST_OK = 0;


    private Cream mCream;
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
    private boolean mSaveExit = false;
    ArrayAdapter mAdapter;

    Context mContext;

    public static CreamsFragment newInstance(UUID id){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CREAM_ID, id);
        CreamsFragment fragment = new CreamsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        UUID id = (UUID) getArguments().getSerializable(ARG_CREAM_ID);
        mCream = DataModel.get(getActivity()).getCream(id);
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
            DataModel.get(getActivity()).updateCream(mCream);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.screen_1_fragment, container, false);

        mTitle = v.findViewById(R.id.itemView_Title_Fragment1);
        mTitle.setText(getResources().getString(R.string.cream));
        mNameTv = v.findViewById(R.id.text_1_Fragment1);
        mNameTv.setText(getResources().getString(R.string.name));
        mTypeTv = v.findViewById(R.id.text_2_Fragment1);
        mTypeTv.setText(getResources().getString(R.string.type));
        mStock = v.findViewById(R.id.text_3_Fragment1);
        mStock.setText(getResources().getString(R.string.stock));
        mMin = v.findViewById(R.id.text_4_Fragment1);
        mMin.setText(getResources().getString(R.string.minimum));
        mNameEt = v.findViewById(R.id.edit_1_Fragment1);
        mNameEt.setText(mCream.getName());
        mNameEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mCream.setName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mSpinner = v.findViewById(R.id.spinner_1_Fragment1);
        ArrayList<String> spinner = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.dairy)));
        mAdapter = new ArrayAdapter(mActivity, android.R.layout.simple_spinner_item, spinner);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mAdapter);

        if (mCream.getDairy() != null){
            String s = mCream.getDairy();
            mSpinner.setSelection(spinner.indexOf(s));
        }
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String type = adapterView.getItemAtPosition(i).toString();
                mCream.setDairy(type);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mStockAmount = v.findViewById(R.id.edit_3_fragment1);
        mStockAmount.setText(mCream.getQuantity());
        mStockAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mCream.setQuantity(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mMinimum = v.findViewById(R.id.edit_4_Fragment1);
        mMinimum.setText(mCream.getMinimum());
        mMinimum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mCream.setMinimum(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mSave = v.findViewById(R.id.saveButton_Fragment1);
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataModel.get(mActivity).updateCream(mCream);
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

    private void close(Boolean save){
        mSaveExit = save;
        Intent intent = new Intent(getActivity(), CreamsListActivity.class);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        closeT = (boolean) data.getSerializableExtra(DeleteDialog.EXTRA_DELETE);
        DataModel.get(mActivity).deleteCream(mCream);
        if (closeT){
            close(true);
        }
    }

    private void openDialog(){
        FragmentManager manager = getFragmentManager();
        String title = getResources().getString(R.string.delete_cream_title) +" "+ mCream.getName();
        String message = getResources().getString(R.string.delete_cream_message) +" "+ mCream.getName();
        DeleteDialog dialog = DeleteDialog.newInstance(message, title);
        dialog.setTargetFragment(CreamsFragment.this, REQUEST_OK);
        dialog.show(manager, DIALOG_DELETE);
    }

}

package com.example.coffeeshopinventorytracking.Syrups;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.coffeeshopinventorytracking.DataModel;
import com.example.coffeeshopinventorytracking.DeleteDialog;
import com.example.coffeeshopinventorytracking.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;


public class SyrupsFragment extends Fragment {

    private static final String ARG_SYRUP_ID = "syrup_id";
    private static final String DIALOG_DELETE = "delete";

    private static final int REQUEST_DELETE = 1;
    private static final int REQUEST_OK = 0;

    private boolean closeT;
    private Syrup mSyrup;
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
    ArrayAdapter mAdapter;
    private Boolean mSaveExit = false;

    Context mContext;

    public static SyrupsFragment newInstance(UUID id){
        Bundle args = new Bundle();
        args.putSerializable(ARG_SYRUP_ID, id);
        SyrupsFragment fragment = new SyrupsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_list, menu);
        MenuItem searchItem = menu.findItem(R.id.search_syrup);

        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        UUID SyrupId = (UUID) getArguments().getSerializable(ARG_SYRUP_ID);
        mSyrup = DataModel.get(getActivity()).getSyrup(SyrupId);
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
        DataModel.get(getActivity()).updateSyrup(mSyrup);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.screen_1_fragment, container, false);

        mTitle = v.findViewById(R.id.itemView_Title_Fragment1);
        mTitle.setText(getResources().getString(R.string.syrup));
        mNameTv = v.findViewById(R.id.text_1_Fragment1);
        mNameTv.setText(getResources().getString(R.string.name));
        mTypeTv = v.findViewById(R.id.text_2_Fragment1);
        mTypeTv.setText(getResources().getString(R.string.type));
        mStock = v.findViewById(R.id.text_3_Fragment1);
        mStock.setText(getResources().getString(R.string.stock));
        mMin = v.findViewById(R.id.text_4_Fragment1);
        mMin.setText(getResources().getString(R.string.minimum));
        mNameEt = v.findViewById(R.id.edit_1_Fragment1);
        mNameEt.setText(mSyrup.getName());
        mNameEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mSyrup.setName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mSpinner = v.findViewById(R.id.spinner_1_Fragment1);
        ArrayList<String> spinner = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.sugar)));
        mAdapter = new ArrayAdapter(mActivity, android.R.layout.simple_spinner_item, spinner);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mAdapter);

        if (mSyrup.getSugar() != null){
            String s = mSyrup.getSugar();
            mSpinner.setSelection(spinner.indexOf(s));
        }
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String type = adapterView.getItemAtPosition(i).toString();
                mSyrup.setSugar(type);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mStockAmount = v.findViewById(R.id.edit_3_fragment1);
        mStockAmount.setText(mSyrup.getQuantity());
        mStockAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mSyrup.setQuantity(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mMinimum = v.findViewById(R.id.edit_4_Fragment1);
        mMinimum.setText(mSyrup.getMinimum());
        mMinimum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mSyrup.setMinimum(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mSave = v.findViewById(R.id.saveButton_Fragment1);
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataModel.get(mActivity).updateSyrup(mSyrup);
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
        DataModel.get(mActivity).deleteSyrup(mSyrup);
        if (closeT){
            close(true);
        }
    }

    private void close(Boolean save){
        mSaveExit = save;
        Intent intent = new Intent(getActivity(), SyrupsListActivity.class);
        startActivity(intent);
    }

    private void openDialog(){
        FragmentManager manager = getFragmentManager();
        String title = getResources().getString(R.string.delete_syrup_title) +" "+ mSyrup.getName();
        String message = getResources().getString(R.string.delete_syrup_message) +" "+ mSyrup.getName();
        DeleteDialog dialog = DeleteDialog.newInstance(message, title);
        dialog.setTargetFragment(SyrupsFragment.this, REQUEST_OK);
        dialog.show(manager, DIALOG_DELETE);
    }
}

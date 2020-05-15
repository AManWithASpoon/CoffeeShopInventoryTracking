package com.example.coffeeshopinventorytracking.Cups;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeshopinventorytracking.DataModel;
import com.example.coffeeshopinventorytracking.R;

import java.util.List;

public class CupsListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private CupAdapter mAdapter;

    int selected_position = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.new_object){
            Cup cup = new Cup();
            DataModel.get(getActivity()).addCup(cup);
            Intent intent = CupsActivity.newIntent(getActivity(), cup.getUUID());
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_list, menu);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_fragment, container, false);
        mRecyclerView = v.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return v;
    }

    private class CupHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Cup mCup;

        private TextView mHotCold;
        private TextView mSize;
        private TextView mQuantity;
        private TextView mMinimum;

        public CupHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_4, parent, false));
            itemView.setOnClickListener(this);
            mHotCold = itemView.findViewById(R.id.list_item_4_text_1);
            mSize = itemView.findViewById(R.id.list_item_4_text_2);
            mQuantity = itemView.findViewById(R.id.list_item_4_text_3);
            mMinimum = itemView.findViewById(R.id.list_item_4_text_4);
        }

        public void bind(Cup cup){
            mCup = cup;
            String type = getResources().getString(R.string.type_list)+" "+ mCup.getHotCold();
            String size = getResources().getString(R.string.size_list)+" "+ mCup.getSize();
            String stock = getResources().getString(R.string.stock_list) +" "+ mCup.getQuantity();
            String min = getResources().getString(R.string.min_list) +" "+ mCup.getMinimum();
            mHotCold.setText(type);
            mSize.setText(size);
            mQuantity.setText(stock);
            mMinimum.setText(min);
        }

        @Override
        public void onClick(View view) {
            Intent intent = CupsActivity.newIntent(getActivity(), mCup.getUUID());
            startActivity(intent);
            /*
            if (getAdapterPosition() == RecyclerView.NO_POSITION) return;
            mAdapter.notifyItemChanged(selected_position);
            selected_position = getAdapterPosition();
            mAdapter.notifyItemChanged(selected_position);
            */
        }
    }

    private class CupAdapter extends RecyclerView.Adapter<CupHolder>{

        private List<Cup> mCups;

        public CupAdapter(List<Cup> cups){mCups = cups;}

        @NonNull
        @Override
        public CupHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater= LayoutInflater.from(getActivity());
            return new CupHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull CupHolder holder, int position) {
            Cup cup = mCups.get(position);
            holder.bind(cup);
        }

        @Override
        public int getItemCount() {
            return mCups.size();
        }

        void setCups(List<Cup> cups){mCups = cups;}
    }

    private void updateUI(){
        DataModel dataModel = DataModel.get(getActivity());
        List<Cup> cups = dataModel.getCups();

        if (mAdapter == null){
            mAdapter = new CupAdapter(cups);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setCups(cups);
            mAdapter.notifyDataSetChanged();
        }
    }
}

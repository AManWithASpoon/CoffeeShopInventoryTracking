package com.example.coffeeshopinventorytracking.Pastries;

import android.content.Intent;
import android.graphics.Color;
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

public class PastriesListFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private PastryAdapter mAdapter;
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
            Pastry pastry = new Pastry();
            DataModel.get(getActivity()).addPastry(pastry);
            Intent intent = PastriesActivity.newIntent(getActivity(), pastry.getUUID());
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    private void updateUI(){
        DataModel dataModel = DataModel.get(getActivity());
        List<Pastry> pastries = dataModel.getPastries();

        if (mAdapter == null){
            mAdapter = new PastryAdapter(pastries);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setPastries(pastries);
            mAdapter.notifyDataSetChanged();
        }
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

    private class PastryHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Pastry mPastry;

        private TextView mName;
        private TextView mSugar;
        private TextView mQuantity;
        private TextView mMinimum;

        public PastryHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_4, parent, false));
            itemView.setOnClickListener(this);
            mName = itemView.findViewById(R.id.list_item_4_text_1);
            mQuantity = itemView.findViewById(R.id.list_item_4_text_3);
            mMinimum = itemView.findViewById(R.id.list_item_4_text_4);
            mSugar = itemView.findViewById(R.id.list_item_4_text_2);
        }

        public void bind(Pastry pastry){
            mPastry = pastry;
            String name = getResources().getString(R.string.name_list) +" "+ mPastry.getName();
            String stock = getResources().getString(R.string.stock_list) +" "+ mPastry.getQuantity();
            String min = getResources().getString(R.string.min_list) +" "+ mPastry.getMinimum();
            mName.setText(name);
            mSugar.setVisibility(View.GONE);
            mQuantity.setText(stock);
            mMinimum.setText(min);
        }

        @Override
        public void onClick(View view) {
            Intent intent = PastriesActivity.newIntent(getActivity(), mPastry.getUUID());
            startActivity(intent);

            /*
            if (getAdapterPosition() == RecyclerView.NO_POSITION) return;
            mAdapter.notifyItemChanged(selected_position);
            selected_position = getAdapterPosition();
            mAdapter.notifyItemChanged(selected_position);
*/
        }
    }

    private class PastryAdapter extends RecyclerView.Adapter<PastryHolder>{

        private List<Pastry> mPastries;

        public PastryAdapter(List<Pastry> pastries){
            mPastries = pastries;}

        @NonNull
        @Override
        public PastryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new PastryHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull PastryHolder holder, int position) {
            Pastry pastry = mPastries.get(position);
            holder.bind(pastry);

        }

        @Override
        public int getItemCount() {return mPastries.size();}

        void setPastries(List<Pastry> pastries){
            mPastries = pastries;}
    }
}

package com.example.coffeeshopinventorytracking.Syrups;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeshopinventorytracking.DataModel;
import com.example.coffeeshopinventorytracking.R;

import java.util.ArrayList;
import java.util.List;

public class SyrupsListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private SyrupAdapter mAdapter;
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
            Syrup syrup = new Syrup();
            DataModel.get(getActivity()).addSyrup(syrup);
            Intent intent = SyrupsActivity.newIntent(getActivity(), syrup.getUUID());
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

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

    private void updateUI(){
        DataModel dataModel = DataModel.get(getActivity());
        List<Syrup> syrups = dataModel.getSyrups();

        if (mAdapter == null){
            mAdapter = new SyrupAdapter(syrups);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setSyrups(syrups);
            mAdapter.notifyDataSetChanged();
        }
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

    private class SyrupHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Syrup mSyrup;

        private TextView mName;
        private TextView mSugar;
        private TextView mStockAmount;
        private TextView mMinimum;

        public SyrupHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_4, parent, false));
            itemView.setOnClickListener(this);
            mName = itemView.findViewById(R.id.list_item_4_text_1);
            mSugar = itemView.findViewById(R.id.list_item_4_text_2);
            mStockAmount = itemView.findViewById(R.id.list_item_4_text_3);
            mMinimum = itemView.findViewById(R.id.list_item_4_text_4);
        }

        public void bind(Syrup syrup){
            mSyrup = syrup;
            String name = getResources().getString(R.string.name_list) +" "+ mSyrup.getName();
            String sugar = getResources().getString(R.string.type_list) +" "+ mSyrup.getSugar();
            String stock = getResources().getString(R.string.stock_list) +" "+ mSyrup.getQuantity();
            String min = getResources().getString(R.string.min_list) +" "+ mSyrup.getMinimum();
            mName.setText(name);
            mSugar.setText(sugar);
            mStockAmount.setText(stock);
            mMinimum.setText(min);
        }

        @Override
        public void onClick(View view) {
            Intent intent = SyrupsActivity.newIntent(getActivity(), mSyrup.getUUID());
            startActivity(intent);

        }
    }

    private class SyrupAdapter extends RecyclerView.Adapter<SyrupHolder> implements Filterable {

        private List<Syrup> mSyrups;
        private List<Syrup> mSyrupsFull;

        public SyrupAdapter(List<Syrup> syrups){mSyrups = syrups;}

        @NonNull
        @Override
        public SyrupHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new SyrupHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull SyrupHolder holder, int position) {
            Syrup syrup = mSyrups.get(position);
            holder.bind(syrup);

        }

        @Override
        public int getItemCount() {return mSyrups.size();}

        void setSyrups(List<Syrup> syrups){
            mSyrups = syrups;
            mSyrupsFull = new ArrayList<>(syrups);
        }

        @Override
        public Filter getFilter() {
            return syrupFilter;
        }

        private Filter syrupFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                List<Syrup> filteredList = new ArrayList<>();
                if (charSequence == null || charSequence.length() == 0){
                    filteredList.addAll(mSyrupsFull);
                }else {
                    String filterPattern = charSequence.toString().toLowerCase().trim();

                    for (Syrup syrup : mSyrupsFull){
                        if (syrup.getName().toLowerCase().contains(filterPattern)){
                            filteredList.add(syrup);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredList;

                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mSyrups.clear();
                mSyrups.addAll((List) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }
}

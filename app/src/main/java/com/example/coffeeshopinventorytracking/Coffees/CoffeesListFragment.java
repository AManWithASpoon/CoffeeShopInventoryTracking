package com.example.coffeeshopinventorytracking.Coffees;

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

public class CoffeesListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private CoffeeAdapter mAdapter;
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
            Coffee coffee = new Coffee();
            DataModel.get(getActivity()).addCoffee(coffee);
            Intent intent = CoffeesActivity.newIntent(getActivity(), coffee.getUUID());
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

    private void updateUI(){
        DataModel dataModel = DataModel.get(getActivity());
        List<Coffee> coffees = dataModel.getCoffees();

        if (mAdapter == null){
            mAdapter = new CoffeeAdapter(coffees);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setCoffees(coffees);
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

    private class CoffeeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Coffee mCoffee;

        private TextView mGroundWhole;
        private TextView mRoast;
        private TextView mQuantity;
        private TextView mMinimum;

        public CoffeeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_4, parent, false));
            itemView.setOnClickListener(this);
            mGroundWhole = itemView.findViewById(R.id.list_item_4_text_1);
            mRoast = itemView.findViewById(R.id.list_item_4_text_2);
            mQuantity = itemView.findViewById(R.id.list_item_4_text_3);
            mMinimum = itemView.findViewById(R.id.list_item_4_text_4);
        }

        public void bind(Coffee coffee) {
            mCoffee = coffee;
            String ground = getResources().getString(R.string.grind_list) +" "+ mCoffee.getGroundWhole();
            String roast = getResources().getString(R.string.roast_list) +" "+ mCoffee.getRoast();
            String stock = getResources().getString(R.string.stock_list) +" "+ mCoffee.getQuantity();
            String min = getResources().getString(R.string.min_list) +" "+ mCoffee.getMinimum();
            mGroundWhole.setText(ground);
            mRoast.setText(roast);
            mQuantity.setText(stock);
            mMinimum.setText(min);
        }

        @Override
        public void onClick(View view) {
            Intent intent = CoffeesActivity.newIntent(getActivity(), mCoffee.getUUID());
            startActivity(intent);
            /*
            if (getAdapterPosition() == RecyclerView.NO_POSITION) return;
            mAdapter.notifyItemChanged(selected_position);
            selected_position = getAdapterPosition();
            mAdapter.notifyItemChanged(selected_position);
*/
        }
    }

    private class CoffeeAdapter extends RecyclerView.Adapter<CoffeeHolder> {

        private List<Coffee> mCoffees;

        public CoffeeAdapter(List<Coffee> coffees) {
            mCoffees = coffees;
        }

        @NonNull
        @Override
        public CoffeeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CoffeeHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull CoffeeHolder holder, int position) {
            Coffee coffee = mCoffees.get(position);
            holder.bind(coffee);

        }

        @Override
        public int getItemCount() {
            return mCoffees.size();
        }
        void setCoffees(List<Coffee> coffees){mCoffees = coffees;}
    }
}

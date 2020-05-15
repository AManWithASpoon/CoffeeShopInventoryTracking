package com.example.coffeeshopinventorytracking.Creams;

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

public class CreamsListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private CreamAdapter mAdapter;
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
            Cream cream = new Cream();
            DataModel.get(getActivity()).addCream(cream);
            Intent intent = CreamsActivity.newIntent(getActivity(), cream.getUUID());
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
        List<Cream> creams = dataModel.getCreams();

        if (mAdapter == null){
            mAdapter = new CreamAdapter(creams);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setCreams(creams);
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

    private class CreamHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Cream mCream;

        private TextView mName;
        private TextView mDairy;
        private TextView mQuantity;
        private TextView mMinimum;

        public CreamHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_4, parent, false));
            itemView.setOnClickListener(this);
            mName = itemView.findViewById(R.id.list_item_4_text_1);
            mDairy = itemView.findViewById(R.id.list_item_4_text_2);
            mQuantity = itemView.findViewById(R.id.list_item_4_text_3);
            mMinimum = itemView.findViewById(R.id.list_item_4_text_4);
        }

        public void bind(Cream cream){
            mCream = cream;
            String name = getResources().getString(R.string.name_list) +" "+ mCream.getName();
            String dairy = getResources().getString(R.string.dairy_list) +" "+ mCream.getDairy();
            String stock = getResources().getString(R.string.stock_list) +" "+ mCream.getQuantity();
            String min = getResources().getString(R.string.min_list) +" "+ mCream.getMinimum();
            mName.setText(name);
            mDairy.setText(dairy);
            mQuantity.setText(stock);
            mMinimum.setText(min);
        }

        @Override
        public void onClick(View view) {
            Intent intent = CreamsActivity.newIntent(getActivity(), mCream.getUUID());
            startActivity(intent);
            /*
            if (getAdapterPosition() == RecyclerView.NO_POSITION) return;
            mAdapter.notifyItemChanged(selected_position);
            selected_position = getAdapterPosition();
            mAdapter.notifyItemChanged(selected_position);
*/
        }
    }

    private class CreamAdapter extends RecyclerView.Adapter<CreamHolder>{

        private List<Cream> mCreams;

        public CreamAdapter(List<Cream> creams){
            mCreams = creams;}

        @NonNull
        @Override
        public CreamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CreamHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull CreamHolder holder, int position) {
            Cream cream = mCreams.get(position);
            holder.bind(cream);

        }

        @Override
        public int getItemCount() {return mCreams.size();}

        void setCreams(List<Cream> creams){
            mCreams = creams;}
    }
}

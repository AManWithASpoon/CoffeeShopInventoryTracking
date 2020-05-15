package com.example.coffeeshopinventorytracking.Lids;

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

public class LidsListFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private LidAdapter mAdapter;
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
            Lid lid = new Lid();
            DataModel.get(getActivity()).addLid(lid);
            Intent intent = LidsActivity.newIntent(getActivity(), lid.getUUID());
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

    private class LidHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Lid mLid;

        private TextView mHotCold;
        private TextView mSize;
        private TextView mQuantity;
        private TextView mMinimum;

        public LidHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_4, parent, false));
            itemView.setOnClickListener(this);
            mHotCold = itemView.findViewById(R.id.list_item_4_text_1);
            mSize = itemView.findViewById(R.id.list_item_4_text_2);
            mQuantity = itemView.findViewById(R.id.list_item_4_text_3);
            mMinimum = itemView.findViewById(R.id.list_item_4_text_4);
        }

        public void bind(Lid lid){
            mLid = lid;
            String type = getResources().getString(R.string.type_list)+" "+ mLid.getHotCold();
            String size = getResources().getString(R.string.size_list)+" "+ mLid.getSize();
            String stock = getResources().getString(R.string.stock_list) +" "+ mLid.getQuantity();
            String min = getResources().getString(R.string.min_list) +" "+ mLid.getMinimum();
            mHotCold.setText(type);
            mSize.setText(size);
            mQuantity.setText(stock);
            mMinimum.setText(min);
        }

        @Override
        public void onClick(View view) {
            Intent intent = LidsActivity.newIntent(getActivity(), mLid.getUUID());
            startActivity(intent);
            /*
            if (getAdapterPosition() == RecyclerView.NO_POSITION) return;
            mAdapter.notifyItemChanged(selected_position);
            selected_position = getAdapterPosition();
            mAdapter.notifyItemChanged(selected_position);
            */
        }
    }

    private class LidAdapter extends RecyclerView.Adapter<LidHolder>{

        private List<Lid> mLids;

        public LidAdapter(List<Lid> lids){mLids = lids;}

        @NonNull
        @Override
        public LidHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new LidHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull LidHolder holder, int position) {
            Lid lid = mLids.get(position);
            holder.bind(lid);
        }

        @Override
        public int getItemCount() {
            return mLids.size();
        }

        void setLids(List<Lid> lids){mLids = lids;}
    }



    private void updateUI(){
        DataModel dataModel = DataModel.get(getActivity());
        List<Lid> lids = dataModel.getLids();

        if (mAdapter == null){
            mAdapter = new LidAdapter(lids);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setLids(lids);
            mAdapter.notifyDataSetChanged();
        }
    }
}

package com.teammotoyori.motoyori_app;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 1503015 on 2016/07/12.
 */
public class KeyValueAdapter extends RecyclerView.Adapter<KeyValueHolder> {
    private ArrayList<KeyValueData> dict;

    public KeyValueAdapter(ArrayList<KeyValueData> dict) {
        this.dict = dict;
    }

    @Override
    public KeyValueHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_layout, parent, false);
        return new KeyValueHolder(view);
    }

    @Override
    public void onBindViewHolder(final KeyValueHolder holder, final int listPosition) {
        holder.key.setText(dict.get(listPosition).getKey());
        holder.base.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), dict.get(listPosition).getValue(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dict.size();
    }
}

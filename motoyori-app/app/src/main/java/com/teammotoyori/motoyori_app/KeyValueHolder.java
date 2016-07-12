package com.teammotoyori.motoyori_app;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class KeyValueHolder extends RecyclerView.ViewHolder {

    View base;
    TextView key;

    public KeyValueHolder(View v) {
        super(v);
        this.base = v;
        this.key = (TextView) v.findViewById(R.id.key);
    }
}

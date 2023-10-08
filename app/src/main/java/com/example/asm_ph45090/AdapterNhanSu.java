package com.example.asm_ph45090;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class AdapterNhanSu extends BaseAdapter {

    ArrayList<ModelNhanSu> dsNs = new ArrayList<>();

    @Override
    public int getCount() {
        return dsNs.size();
    }

    @Override
    public Object getItem(int i) {
        return dsNs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return dsNs.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}

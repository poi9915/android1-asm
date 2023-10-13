package com.example.asm_ph45090.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asm_ph45090.Model.ModelPhongBan;
import com.example.asm_ph45090.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterPhongBan extends BaseAdapter {
    ArrayList<ModelPhongBan> dsPB = new ArrayList<>();
    ArrayList<ModelPhongBan> filterPB;
    ArrayList<ModelPhongBan> originPB;

    public AdapterPhongBan(ArrayList<ModelPhongBan> dsPB) {
        this.dsPB = dsPB;
        this.originPB = new ArrayList<>(dsPB);
    }

    @Override
    public int getCount() {
        return dsPB.size();
    }

    @Override
    public Object getItem(int i) {
        return dsPB.get(i);
    }

    @Override
    public long getItemId(int i) {
        return dsPB.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row;
        if (view != null) {
            row = view;
        } else {
            LayoutInflater inflater = ((Activity) (viewGroup.getContext())).getLayoutInflater();
            row = inflater.inflate(R.layout.lsv_pb_acitivity, null);
        }
        ImageView ivPbIcon = row.findViewById(R.id.ivPbIcon);
        TextView tvPbName = row.findViewById(R.id.tvPbName);
        ModelPhongBan pbObj = dsPB.get(i);

        ivPbIcon.setImageResource(pbObj.getIcon());
        tvPbName.setText(pbObj.getName());

        return row;
    }

    public Filter getFilter() {
        Filter filter = new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                if (constraint == null || constraint.length() == 0) {
                    results.values = originPB;
                    results.count = originPB.size();
                } else {
                    List<ModelPhongBan> filteredList = new ArrayList<>();
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    for (ModelPhongBan item : dsPB) {
                        if (item.getName().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }

                    results.values = filteredList;
                    results.count = filteredList.size();
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                dsPB.clear();
                dsPB.addAll((ArrayList<ModelPhongBan>) results.values);
                notifyDataSetChanged();
            }
        };

        return filter;
    }

}

package com.example.asm_ph45090;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class AdapterNhanSu extends BaseAdapter {

    private  Context context;


    ArrayList<ModelNhanSu> dsNs = new ArrayList<>();

    public AdapterNhanSu(Context context ,ArrayList<ModelNhanSu> dsNs) {
        this.context = context;
        this.dsNs = dsNs;
    }

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
        View row;
        if (view != null){
            row = view;
        }else {
            LayoutInflater inflater = ((Activity) (viewGroup.getContext())).getLayoutInflater();
            row = inflater.inflate(R.layout.lsv_ns_activity , null);
        }
        ModelNhanSu nsObj = dsNs.get(i);
        TextView nsName = row.findViewById(R.id.nsName);
        TextView nsID = row.findViewById(R.id.nsID);
        TextView nsPB = row.findViewById(R.id.nsPB);

        ImageButton btnEdit = row.findViewById(R.id.btnEdit);
        ImageButton btnRemove = row.findViewById(R.id.btnRemove);

        nsID.setText(nsObj.getMaNV());
        nsName.setText(nsObj.getHoTen());
        nsPB.setText(nsObj.getPhongBan());
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEditDialog(i);

            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context ,"Đã xoá NS :" + dsNs.get(i).getId() , Toast.LENGTH_SHORT).show();
                dsNs.remove(i);
                notifyDataSetChanged();

            }
        });

        return row;
    }
    private void showEditDialog(int id ){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_ns_acitvity , null);
        TextInputLayout edNewNsId  = view.findViewById(R.id.edNewNsID);
        TextInputLayout edNewNsName = view.findViewById(R.id.edNewNsName);
        Spinner spinNewPb = view.findViewById(R.id.spinNewPB);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                context,
                android.R.layout.simple_spinner_item,
                new String[]{"Hành chính", "Nhân Sự", "Đào tạo"});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinNewPb.setAdapter(adapter);
        builder.setView(view)
                .setTitle("Sửa Thông Tin NS")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String newNSId = edNewNsId.getEditText().getText().toString();
                        String newNSName = edNewNsName.getEditText().getText().toString();
                        String newNsPB = spinNewPb.getSelectedItem().toString();
                        ModelNhanSu newNS = new ModelNhanSu(getCount() + 1 , newNSId , newNSName , newNsPB);
                        dsNs.set(id , newNS);
                        notifyDataSetChanged();
                    }
                });
        builder.create().show();
    }
}

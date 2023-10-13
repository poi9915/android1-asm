package com.example.asm_ph45090;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.asm_ph45090.Adapter.AdapterPhongBan;
import com.example.asm_ph45090.Model.ModelPhongBan;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class PbActivity extends AppCompatActivity {
    MaterialToolbar topAppBarPB;
    ListView lsvPB ;
    SearchView seaPB;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pb_activity);
        topAppBarPB = findViewById(R.id.topAppBarPB);
        setSupportActionBar(topAppBarPB);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        lsvPB = findViewById(R.id.lsvPB);
        seaPB = findViewById(R.id.seaPB);
        ArrayList<ModelPhongBan> dsPB = new ArrayList<>();
        dsPB.add(new ModelPhongBan(0 , R.drawable.ic_groups , "Nhân sự"));
        dsPB.add(new ModelPhongBan(1 , R.drawable.ic_groups , "Hành chính"));
        dsPB.add(new ModelPhongBan(2 , R.drawable.ic_groups , "Đào tạo"));

        AdapterPhongBan adapterPhongBan = new AdapterPhongBan(dsPB);
        lsvPB.setAdapter(adapterPhongBan);
        seaPB.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapterPhongBan.getFilter().filter(s);
                adapterPhongBan.notifyDataSetChanged();
                return false;
            }
        });

    }


}

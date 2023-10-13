package com.example.asm_ph45090;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class NsActivity extends AppCompatActivity implements NsDialog.NsDialogListener {
    MaterialToolbar topAppBarNS;
    ListView lsvNS;
    ImageButton btnAddNS;
    ArrayList<ModelNhanSu> dsNS = new ArrayList<>();
    AdapterNhanSu adapterNhanSu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ns_activity);
        btnAddNS = findViewById(R.id.btn_addNS);

        lsvNS = findViewById(R.id.lsvNS);

        dsNS.add(new ModelNhanSu(0, "NV01", "Nguyen Van A", "Hành chính"));
        dsNS.add(new ModelNhanSu(1, "NV02", "Nguyen Van A", "Nhân Sự"));
        dsNS.add(new ModelNhanSu(2, "NV03", "Nguyen Van A", "Đào tạo"));
        adapterNhanSu = new AdapterNhanSu(this , dsNS);
        lsvNS.setAdapter(adapterNhanSu);


        topAppBarNS = findViewById(R.id.topAppBarNS);
        setSupportActionBar(topAppBarNS);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        btnAddNS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOpenAddDialog();
            }
        });
    }

    private void onOpenAddDialog() {
        NsDialog nsDialog = new NsDialog();
        nsDialog.show(getSupportFragmentManager(), "add");
    }

    @Override
    public void applyText(String newId, String newName, String newPB) {
        ModelNhanSu newNS = new ModelNhanSu(dsNS.size() + 1 , newId , newName , newPB );
        dsNS.add(newNS);
        adapterNhanSu.notifyDataSetChanged();
    }
}

package com.example.asm_ph45090;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

public class NsActivity extends AppCompatActivity {
    MaterialToolbar topAppBarNS;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ns_activity);
        topAppBarNS = findViewById(R.id.topAppBarNS);
        setSupportActionBar(topAppBarNS);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);


    }
}

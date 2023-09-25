package com.example.asm_ph45090;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

public class HomeActivity extends AppCompatActivity {
    MaterialCardView cardPb , cardNs;
    Button btnExit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        cardPb = findViewById(R.id.cardPb);
        cardNs = findViewById(R.id.cardNs);
        btnExit = findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(HomeActivity.this , LoginActivity.class));
            }
        });
        cardNs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this , NsActivity.class);
                startActivity(intent);
            }
        });
        cardPb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this , PbActivity.class);
                startActivity(intent);
            }
        });
    }
}

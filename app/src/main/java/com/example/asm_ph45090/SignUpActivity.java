package com.example.asm_ph45090;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    Button btnSignUp;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this , LoginActivity.class);
                startActivity(intent);
                Toast.makeText(SignUpActivity.this , "Đăng ký thành công" ,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onTextBackToLogin(View view) {
        Intent intent = new Intent(SignUpActivity.this , LoginActivity.class);
        startActivity(intent);
    }
}

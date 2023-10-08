package com.example.asm_ph45090;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.io.FileOutputStream;
import java.io.IOException;

public class SignUpActivity extends AppCompatActivity {
    String TAG = "SignUp log";
    Button btnSignUp;
    TextInputLayout etSignUserName;
    TextInputLayout etSignPassword;
    TextInputLayout etSignConfPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);
        btnSignUp = findViewById(R.id.btnSignUp);
        etSignUserName = findViewById(R.id.etSignUserName);
        etSignPassword = findViewById(R.id.etSignPassword);
        etSignConfPassword = findViewById(R.id.etSignConfPassword);



        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserName = etSignUserName.getEditText().getText().toString();
                String Password = etSignPassword.getEditText().getText().toString();
                String ConfPassword = etSignConfPassword.getEditText().getText().toString();
                if (!Password.equals(ConfPassword)) {
                    Toast.makeText(SignUpActivity.this, "Mật khẩu xác nhận không khớp ", Toast.LENGTH_SHORT).show();
                    return;
                }


                try {
                    String fileContents = "username:" + UserName + ",password:" + Password;
                    FileOutputStream fos = openFileOutput("data.txt" , Context.MODE_PRIVATE);
                    fos.write(fileContents.getBytes());
                    fos.close();
                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(intent);
                    Toast.makeText(SignUpActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();

                }catch (IOException e){
                    Log.d(TAG , e.toString());
                }


            }
        });
    }

    public void onTextBackToLogin(View view) {
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}

package com.example.asm_ph45090;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginActivity extends AppCompatActivity {

    String TAG = "LoginLog";
    CheckBox cbRemMe;
    Button btnLogin;
    TextInputLayout etUserName;
    TextInputLayout etPassword;
    String name = "";
    String pass = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        //khởi tạo Shared Preferences và editor
        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
//      Ánh xạ
        btnLogin = findViewById(R.id.btnLogin);
        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassWord);
        cbRemMe = findViewById(R.id.cbRemMe);
        //nếu trước đó cbRemMe dc chọn thì lập tức chuyển sang HomeActivity
        if (sharedPreferences.getBoolean("RememberMe", false)) {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        }
        //xử lý Login button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Lấy text từ EditTextLayout
                String Usermame = etUserName.getEditText().getText().toString();
                String Password = etPassword.getEditText().getText().toString();
                //đọc username và password từ data.txt
                try {
                    FileInputStream fis = openFileInput("data.txt");
                    if (fis != null) {
                        InputStreamReader isr = new InputStreamReader(fis);
                        BufferedReader buffRead = new BufferedReader(isr);
                        StringBuilder stringBuild = new StringBuilder();
                        String receiveString = "";
                        while ((receiveString = buffRead.readLine()) != null) {
                            stringBuild.append(receiveString);
                        }
                        fis.close();
                        String data = stringBuild.toString();
                        //Cắt chuỗi thành 2 : name:'name' và pass:'pass' sau đó lưu vào parts
                        String[] parts = data.split(",");
                        //lấy chuỗi name:'name' sau đó căt chuỗi làm 2 và sau đó lưu 'name' vào biến
                        name = parts[0].split(":")[1];
                        //tương tự name
                        pass = parts[1].split(":")[1];
                    }
                } catch (IOException e) {
                    Log.d(TAG, e.toString());
                }

                //Kiểm tra username và password
                Boolean isName = name.equals(Usermame);
                Boolean isPass = pass.equals(Password);

                //nếu username và pass khớp thì chuyển sang màn home
                if (isName && isPass) {
                    //nếu cbRemMe dc chọn thì put data vào SharedPreferences = true , ngược lại false
                    if (cbRemMe.isChecked()) {
                        editor.putBoolean("RememberMe", true);
                    } else {
                        editor.putBoolean("RememberMe", true);
                    }
                    editor.apply();


                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "UserName hoặc Password Không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    //onlick của textView , khi click sẽ chuyển sang SignUp activity
    public void onTextViewClick(View view) {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}

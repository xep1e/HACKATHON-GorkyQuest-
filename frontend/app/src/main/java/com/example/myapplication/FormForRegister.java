package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import androidx.activity.EdgeToEdge;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FormForRegister extends AppCompatActivity {
    private EditText etName, etEmail, etPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_for_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        }) ;
    etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    public void registerUser() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Все поля должны быть заполнены", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiServise apiService = RetrofitClient.getClient().create(ApiServise.class);
        Users registerRequest = new Users(name, email, password);

        Call<RegisterResponseJSON> call = apiService.registerUser(registerRequest);
        call.enqueue(new Callback<RegisterResponseJSON>() {
            @Override
            public void onResponse(Call<RegisterResponseJSON> call, Response<RegisterResponseJSON> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().isSuccess()) {
                        Toast.makeText(FormForRegister.this, "Регистрация прошла успешно", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(FormForRegister.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(FormForRegister.this, "Регистрация не прошла", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponseJSON> call, Throwable t) {
                Log.e("RegisterActivity", "API call failed: " + t.getMessage());
                Toast.makeText(FormForRegister.this, "An error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }

// кнопка вернуться назад
    public void GoMainForm(View v){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
package com.redsegura.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.redsegura.R;
import com.redsegura.utils.SessionManager;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameInput;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Si ya hay un usuario guardado, saltar login
        String savedUsername = SessionManager.getUsername(this);
        if (savedUsername != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_login);
        usernameInput = findViewById(R.id.editTextUsername);
        loginBtn = findViewById(R.id.buttonLogin);

        loginBtn.setOnClickListener(v -> {
            String username = usernameInput.getText().toString().trim();
            if (!username.isEmpty()) {
                SessionManager.saveUsername(this, username);
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Ingrese un nombre válido", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

package com.redsegura.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.redsegura.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameInput;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameInput = findViewById(R.id.editTextRegisterName);
        registerBtn = findViewById(R.id.buttonRegister);

        registerBtn.setOnClickListener(v -> {
            String username = nameInput.getText().toString().trim();
            if (!username.isEmpty()) {
                // Aquí podrías guardar en base de datos local o backend
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, LoginActivity.class);
                intent.putExtra("registered_user", username);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Ingrese un nombre válido", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

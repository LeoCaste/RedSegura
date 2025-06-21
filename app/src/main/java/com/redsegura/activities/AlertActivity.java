package com.redsegura.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.redsegura.R;
import com.redsegura.services.AlertService;

public class AlertActivity extends AppCompatActivity {

    private Button sendAlertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        sendAlertButton = findViewById(R.id.buttonSendAlert);
        sendAlertButton.setOnClickListener(v -> {
            AlertService.sendAlert(this, success -> {
                if (success) {
                    Toast.makeText(this, "Alerta enviada", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Error al enviar alerta", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}

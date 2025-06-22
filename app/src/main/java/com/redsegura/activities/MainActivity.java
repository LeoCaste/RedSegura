package com.redsegura.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.redsegura.R;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fabMain, fabMap, fabIA;
    private boolean isFabOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestNotificationPermission();

        fabMain = findViewById(R.id.fab_main);
        fabMap = findViewById(R.id.fab_map);
        fabIA = findViewById(R.id.fab_ia);

        fabMain.setOnClickListener(view -> toggleFabs());

        fabMap.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, MapActivity.class))
        );

        fabIA.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, AIChatActivity.class))
        );
    }

    private void toggleFabs() {
        if (isFabOpen) {
            fabMap.hide();
            fabIA.hide();
        } else {
            fabMap.show();
            fabIA.show();
        }
        isFabOpen = !isFabOpen;
    }

    private void requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);
            }
        }
    }
}

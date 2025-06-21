package com.redsegura.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.redsegura.redseguraapp.R;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fabMain, fabMap, fabIA;
    private boolean isFabOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabMain = findViewById(R.id.fab_main);
        fabMap = findViewById(R.id.fab_map);
        fabIA = findViewById(R.id.fab_ia);

        fabMain.setOnClickListener(view -> toggleFabs());

        fabMap.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, MapActivity.class));
        });

        fabIA.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AIChatActivity.class));
        });
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
}

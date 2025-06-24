package com.redsegura.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstLaunch = prefs.getBoolean("first_launch", true);

        if (firstLaunch) {
            startActivity(new Intent(this, TutorialActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }

        finish(); // Evita que el usuario regrese a esta pantalla
    }
}

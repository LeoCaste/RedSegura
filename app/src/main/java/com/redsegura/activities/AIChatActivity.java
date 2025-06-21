package com.redsegura.activities;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.redsegura.R;


import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AIChatActivity extends AppCompatActivity {
    private TextView tvResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_chat);

        tvResponse = findViewById(R.id.tv_ai_response);
        getAIAnalysis();
    }

    private void getAIAnalysis() {
        new Thread(() -> {
            try {
                URL url = new URL("http://52.206.230.134:8081/ai/analysis");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                JSONObject json = new JSONObject(response.toString());
                String analysis = json.getString("analysis");

                runOnUiThread(() -> tvResponse.setText(analysis));
            } catch (Exception e) {
                runOnUiThread(() -> tvResponse.setText("Error al obtener análisis de IA."));
                e.printStackTrace();
            }
        }).start();
    }
}

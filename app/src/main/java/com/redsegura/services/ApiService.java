package com.redsegura.services;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiService {
    private static final String BASE_URL = "http://52.206.230.134:8081";

    public static String getAIAnalysis() {
        try {
            URL url = new URL(BASE_URL + "/ai/analysis");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) response.append(inputLine);
            in.close();
            return response.toString();

        } catch (Exception e) {
            Log.e("ApiService", "Error en getAIAnalysis", e);
            return null;
        }
    }

    // Puedes agregar más métodos como POST /alert, GET /alert/recent, etc.
}

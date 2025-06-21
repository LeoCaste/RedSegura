package com.redsegura.services;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.function.Consumer;

public class ChatService {

    private static final String BASE_URL = "http://52.206.230.134:8081"; // <-- Reemplaza esto

    // Enviar mensaje al backend
    public static void sendMessage(String sender, String message, Consumer<Boolean> callback) {
        AsyncTask.execute(() -> {
            try {
                URL url = new URL(BASE_URL + "/messages/send");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json; utf-8");
                conn.setDoOutput(true);

                JSONObject json = new JSONObject();
                json.put("sender", sender);
                json.put("receiver", "destinatario"); // puedes hacer dinámico esto luego
                json.put("message", message);
                json.put("timestamp", System.currentTimeMillis());

                try (OutputStream os = conn.getOutputStream();
                     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"))) {
                    writer.write(json.toString());
                    writer.flush();
                }

                int responseCode = conn.getResponseCode();
                callback.accept(responseCode == 200);

            } catch (Exception e) {
                Log.e("ChatService", "Error enviando mensaje", e);
                callback.accept(false);
            }
        });
    }

    // Obtener mensajes desde el backend
    public static void getMessages(String username, Consumer<JSONArray> callback) {
        AsyncTask.execute(() -> {
            try {
                URL url = new URL(BASE_URL + "/messages/get/" + username);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                InputStream inputStream = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                JSONArray jsonArray = new JSONArray(result.toString());
                callback.accept(jsonArray);

            } catch (Exception e) {
                Log.e("ChatService", "Error obteniendo mensajes", e);
                callback.accept(null);
            }
        });
    }
}

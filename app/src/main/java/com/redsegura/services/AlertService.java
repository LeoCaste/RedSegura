package com.redsegura.services;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import org.json.JSONObject;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AlertService {

    private static final String ALERT_URL = "http://52.206.230.134:8081/alert"; // cambia si tu IP elástica cambia

    public static void sendAlert(Context context, AlertCallback callback) {
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                try {
                    URL url = new URL(ALERT_URL);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setDoOutput(true);

                    // Puedes personalizar los datos de la alerta
                    JSONObject alert = new JSONObject();
                    alert.put("type", "emergencia");
                    alert.put("latitude", -38.7359);  // simulado
                    alert.put("longitude", -72.5904); // simulado

                    OutputStream os = conn.getOutputStream();
                    os.write(alert.toString().getBytes());
                    os.flush();
                    os.close();

                    int responseCode = conn.getResponseCode();
                    return responseCode == 200;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }

            @Override
            protected void onPostExecute(Boolean success) {
                if (!success) {
                    Toast.makeText(context, "No se pudo enviar la alerta.", Toast.LENGTH_SHORT).show();
                }
                callback.onResult(success);
            }
        }.execute();
    }

    public interface AlertCallback {
        void onResult(boolean success);
    }
}

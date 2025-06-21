// Ruta: com.redsegura.RedSeguraApp.java
package com.redsegura;

import android.app.Application;
import android.content.Context;

public class RedSeguraApp extends Application {

    private static RedSeguraApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        // Aquí podrías iniciar algún SDK o servicio global si se requiere.
    }

    public static Context getAppContext() {
        return instance.getApplicationContext();
    }
}

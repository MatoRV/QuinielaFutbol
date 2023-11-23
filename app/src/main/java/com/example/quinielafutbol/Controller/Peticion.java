package com.example.quinielafutbol.Controller;

import android.os.Looper;

import androidx.annotation.NonNull;

import java.io.IOException;
import android.os.Handler;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Peticion {

    public Peticion() {

    }

    public void requestData(String URL) {
        OkHttpClient cliente = new OkHttpClient();
        // Construye la peticion
        Request peticion = new Request.Builder()
                .url(URL)
                .get()
                .addHeader("cache-control","no-cache")
                .build();
        // Realiza una llamada al server, pero en otro thread
        Call llamada = cliente.newCall(peticion);

        llamada.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                String respuesta = e.getMessage();
                Handler manejador = new Handler(Looper.getMainLooper());

                manejador.post(new Runnable() {
                    @Override
                    public void run() {
                        MainController.getSingleton().setDataFromHttp("");
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                // Recibe respuesta
                String respuesta = response.body().string();

                Handler manejador = new Handler(Looper.getMainLooper());
                manejador.post(new Runnable() {
                    @Override
                    public void run() {
                        // El codigo es ejecutado en el hilo principal
                        MainController.getSingleton().setDataFromHttp(respuesta);
                    }
                });
            }
        });
    }
}

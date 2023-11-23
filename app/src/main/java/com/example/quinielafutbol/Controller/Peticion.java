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

        Request peticion = new Request.Builder()
                .url(URL)
                .get()
                .addHeader("cache-control","no-cache")
                .build();
        Call llamada = cliente.newCall(peticion);

        llamada.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String respuesta = response.body().string();
                Handler manejador = new Handler(Looper.getMainLooper());
                manejador.post(new Runnable() {
                    @Override
                    public void run() {
                        MainController.getSingleton().setDataFromHttp(respuesta);
                    }
                });
            }
        });
    }
}

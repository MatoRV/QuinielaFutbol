package com.example.quinielafutbol.Controller;

import com.example.quinielafutbol.Model.Quiniela;
import com.example.quinielafutbol.View.MainActivity;

import java.util.LinkedList;
import java.util.List;

public class MainController {

    private static final String URL = "https://www.loteriasyapuestas.es/es/la-quiniela/escrutinios/la-quiniela-premios-y-ganadores-del-22-de-octubre-de-2023";

    private static MainController mySingleController;

    private List<Quiniela> dataRequested;

    private static MainActivity activeActivity;

    private MainController() {
        dataRequested = new LinkedList<>();
    }

    public static MainController getSingleton() {
        if (MainController.mySingleController == null) {
            mySingleController = new MainController();
        }
        return mySingleController;
    }

    public List<Quiniela> getDataFromHttp() {
        return this.dataRequested;
    }

    public void requestDataFromHttp() {
        Peticion p = new Peticion();
        p.requestData(URL);
    }

    public void setDataFromHttp(String html) {
        Respuesta answer = new Respuesta(html);
        dataRequested = answer.getData();
        MainController.activeActivity.accessData();
    }

    public static void setActivity(MainActivity myAct) {
        activeActivity = myAct;
    }
}

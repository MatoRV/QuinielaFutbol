package com.example.quinielafutbol.Controller;

import android.provider.DocumentsContract;

import com.example.quinielafutbol.Model.Quiniela;

import java.util.LinkedList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Respuesta {

    protected String datos;

    public Respuesta(String entrada) {
        datos = entrada;
    }

    public List<Quiniela> getData() {
        LinkedList<Quiniela> dataList = new LinkedList<>();
        Quiniela quiniela = new Quiniela();
        Document document = Jsoup.parse(this.datos);
        Element equipos = document.selectFirst("div.cuerpoRegionLeft");
        Element resultados = document.selectFirst("div.cuerpoRegionRight");
        //Verifica si el elemento existe
        if (equipos != null) {
            Element listaLocales = equipos.select("ul").get(1);
            if (listaLocales != null) {
                Elements locales = listaLocales.select("li");
                for (Element local : locales) {
                    quiniela.setLocal(String.valueOf(local));
                }
            }
            Element listaVisitantes = equipos.select("ul").get(2);
            if (listaVisitantes != null) {
                Elements visitantes = listaVisitantes.select("li");
                for (Element visitante : visitantes) {
                    quiniela.setVisitante(String.valueOf(visitante));
                }
            }
        }
        if (resultados != null) {
            Element listaResultados = resultados.select("ul").get(0);
            if (listaResultados != null) {
                Elements results = listaResultados.select("li");
                for (Element resultado : results) {
                    quiniela.setResultado(String.valueOf(resultado));
                }
            }
        }

        dataList.add(quiniela);

        return dataList;
    }
}

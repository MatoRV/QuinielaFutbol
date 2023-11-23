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
        // Accede a la url
        Document document = Jsoup.parse(this.datos);
        // Busca un div con class="cuerpoRegionLeft"
        Element equipos = document.selectFirst("div.cuerpoRegionLeft");
        // Busca un div con class="cuerpoRegionRight"
        Element resultados = document.selectFirst("div.cuerpoRegionRight");
        if (equipos != null) {
            // Obtiene los datos de la etiqueta <ul> en la posicion 2
            Element listaLocales = equipos.select("ul").get(1);
            // Obtiene los datos de la etiqueta <ul> en la posicion 3
            Element listaVisitantes = equipos.select("ul").get(2);
            // Obtiene los datos de la etiqueta <ul> en la posicion 1
            Element listaResultados = resultados.select("ul").get(1);
            if (listaLocales != null && listaVisitantes != null && listaResultados != null) {
                // Obtiene los datos de cada <li>
                Elements locales = listaLocales.select("li");
                Elements visitantes = listaVisitantes.select("li");
                Elements results = listaResultados.select("li");
                for (int i = 0; i < results.size(); i++) {
                    // Crea un nuevo objeto y los carga en la lista de datos
                    Quiniela quiniela = new Quiniela(locales.get(i).text(),visitantes.get(i).text(),results.get(i).text());
                    dataList.add(quiniela);
                }
            }
        }
        Element plenoIzq = document.select("div.cuerpoRegionLeft").get(1);
        Element plenoDrc = document.select("div.cuerpoRegionRight").get(1);
        if (plenoIzq != null && plenoDrc != null) {
            Element localPleno = plenoIzq.select("ul").get(1);
            Element visitantePleno = plenoIzq.select("ul").get(2);
            Element resultPleno = plenoDrc.select("ul").get(1);
            if (localPleno != null && visitantePleno != null && resultPleno != null) {
                Element local = localPleno.selectFirst("li");
                Element visitante = visitantePleno.selectFirst("li");
                Element resultado = resultPleno.selectFirst("li");
                Quiniela quiniela = new Quiniela(local.text(),visitante.text(),resultado.text());
                dataList.add(quiniela);
            }
        }
        return dataList;
    }
}

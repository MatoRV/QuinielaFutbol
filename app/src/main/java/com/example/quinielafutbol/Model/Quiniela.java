package com.example.quinielafutbol.Model;

public class Quiniela {

    private String local;

    private String visitante;

    private String resultado;

    public Quiniela(String local, String visitante, String resultado) {
        this.local = local;
        this.visitante = visitante;
        this.resultado = resultado;
    }

    public String getLocal() {
        return local;
    }

    public String getVisitante() {
        return visitante;
    }

    public String getResultado() {
        return resultado;
    }
}

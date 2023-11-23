package com.example.quinielafutbol.Controller;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.quinielafutbol.R;

public class QuinielaViewHolder extends RecyclerView.ViewHolder {

    final QuinielaAdapter mAdapter;

    private TextView tvLocal;

    private TextView tvVisitante;

    private TextView tvResultado;

    public QuinielaViewHolder(View itemView, QuinielaAdapter adapter) {
        super(itemView);

        tvLocal = itemView.findViewById(R.id.local);
        tvVisitante = itemView.findViewById(R.id.visitante);
        tvResultado = itemView.findViewById(R.id.resultado);
        this.mAdapter = adapter;
    }

    public void setTvLocal(String data) {
        tvLocal.setText(data);
    }

    public void setTvVisitante(String data) {
        tvVisitante.setText(data);
    }

    public void setTvResultado(String data) {
        tvResultado.setText(data);
    }
}

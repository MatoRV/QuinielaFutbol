package com.example.quinielafutbol.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quinielafutbol.Model.Quiniela;
import com.example.quinielafutbol.R;

import java.util.LinkedList;

public class QuinielaAdapter extends RecyclerView.Adapter<QuinielaViewHolder> {

    private final LinkedList<Quiniela> mList;

    private LayoutInflater mInflater;

    public QuinielaAdapter(Context context, LinkedList<Quiniela> list) {
        mInflater = LayoutInflater.from(context);
        this.mList = list;
    }

    @NonNull
    @Override
    public QuinielaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.lista_partidos,
                parent, false);
        return new QuinielaViewHolder(mItemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull QuinielaViewHolder holder, int position) {
        holder.setTvLocal(this.mList.get(position).getLocal());
        holder.setTvVisitante(this.mList.get(position).getVisitante());
        holder.setTvResultado(this.mList.get(position).getResultado());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

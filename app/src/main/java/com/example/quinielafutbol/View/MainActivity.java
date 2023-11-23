package com.example.quinielafutbol.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.quinielafutbol.Controller.MainController;
import com.example.quinielafutbol.Controller.QuinielaAdapter;
import com.example.quinielafutbol.Model.Quiniela;
import com.example.quinielafutbol.R;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinkedList<Quiniela> mList = new LinkedList<>();
    private RecyclerView mRecyclerView;

    private QuinielaAdapter mAdapter;

    private static MainActivity myActiveActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Marca el RecyclerView
        mRecyclerView = findViewById(R.id.rv_quiniela);
        // Declara el adaptador del RecyclerView
        mAdapter = new QuinielaAdapter(this,mList);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Pide los datos
        MainController.getSingleton().requestDataFromHttp();

        MainActivity.myActiveActivity = this;
        MainController.setActivity(this);
    }

    public void accessData() {
        // Carga los datos en una lista
        List<Quiniela> lista = MainController.getSingleton().getDataFromHttp();
        // Actualiza la lista
        mList.clear();
        for (Quiniela q : lista) {
            mList.add(q);
        }
        mAdapter.notifyDataSetChanged();
    }


}
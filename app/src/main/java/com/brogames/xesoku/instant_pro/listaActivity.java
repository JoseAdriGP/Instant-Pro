package com.brogames.xesoku.instant_pro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

public class listaActivity extends AppCompatActivity {
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        setContentView(R.layout.activity_lista);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LenguajeListAdapter adapter = new LenguajeListAdapter(this, intent.getStringArrayListExtra("Nombres"), intent.getStringArrayListExtra("Descuentos"), intent.getIntegerArrayListExtra("Imagenes"));
        lista = (ListView) findViewById(R.id.mi_lista);
        lista.setAdapter(adapter);
    }
}

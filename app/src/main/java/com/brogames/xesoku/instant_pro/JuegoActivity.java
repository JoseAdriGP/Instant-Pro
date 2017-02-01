package com.brogames.xesoku.instant_pro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

public class JuegoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        TextView tNombre = (TextView) findViewById(R.id.txtNombre);
        TextView tPrecio = (TextView) findViewById(R.id.txtPrecio);
        TextView tDes = (TextView) findViewById(R.id.txtDescuento);

        ImageView imIcon = (ImageView) findViewById(R.id.platIcon);

        tNombre.setText("Nombre: " + intent.getStringExtra("Nombre"));
        tPrecio.setText("Precio: " + intent.getStringExtra("Precio") + "€");
        tDes.setText("Descuento: " + intent.getStringExtra("Descuento") + "%");

        switch (intent.getStringExtra("Plataforma")) {
            case "1":
                imIcon.setImageResource(R.mipmap.ic_steam);
                break;
            case "2":
                imIcon.setImageResource(R.mipmap.ic_origin);
                break;
            case "3":
                imIcon.setImageResource(R.mipmap.ic_uplay);
                break;
            case "4":
                imIcon.setImageResource(R.mipmap.ic_battle);
                break;
            case "5":
                imIcon.setImageResource(R.mipmap.ic_gog);
                break;
        }

        tNombre.setEnabled(false);
        tPrecio.setEnabled(false);
        tDes.setEnabled(false);
    }

}

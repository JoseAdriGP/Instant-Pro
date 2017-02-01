package com.brogames.xesoku.instant_pro;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView txtSpeechInput;
    private ImageButton btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ManejadorBD dbJuegos = new ManejadorBD(getBaseContext());

        db = dbJuegos.getWritableDatabase();

        Toast.makeText(getBaseContext(), "Base de datos preparada", Toast.LENGTH_LONG).show();

        txtSpeechInput = (TextView) findViewById(R.id.txtSpeechInput);
        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);

        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });

    }

    /**
     * Showing google speech input dialog
     */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtSpeechInput.setText(result.get(0));
                    analizador(result.get(0));
                }
                break;
            }

        }
    }

    public void analizador(String txt) {
        Uri url;
        Intent intent;
        switch (txt.toUpperCase()) {
            case "STEAM":
                url = Uri.parse("https://www.instant-gaming.com/es/juegos/steam/");
                intent = new Intent(Intent.ACTION_VIEW, url);
                startActivity(intent);
                break;

            case "ORIGIN":
                url = Uri.parse("https://www.instant-gaming.com/es/juegos/origin/");
                intent = new Intent(Intent.ACTION_VIEW, url);
                startActivity(intent);
                break;

            case "UPLAY":
                url = Uri.parse("https://www.instant-gaming.com/es/juegos/uplay/");
                intent = new Intent(Intent.ACTION_VIEW, url);
                startActivity(intent);
                break;

            case "BATTLENET":
                url = Uri.parse("https://www.instant-gaming.com/es/juegos/battle-net/");
                intent = new Intent(Intent.ACTION_VIEW, url);
                startActivity(intent);
                break;
            default:
                busquedaBD(txt);
                break;
        }
    }

    public void busquedaBD(String s) {
        s = s.toUpperCase();
        String selectQuery = "";
        String[] divisionString = s.split(" ");
        String discount;

        if (divisionString[0].contains("DESCUENTO")) {
            if (divisionString[1].compareTo("MAYOR") == 0) {
                discount = ">=";
            } else {
                if (divisionString[1].compareTo("MENOR") == 0) {
                    discount = "<=";
                } else {
                    if (divisionString[1].compareTo("IGUAL") == 0) {
                        discount = "=";
                    } else {
                        discount = "!=";
                    }
                }
            }
            selectQuery = "SELECT * FROM JUEGOS WHERE DESCUENTO " + discount + " " + divisionString[2];
            Cursor cursor = db.rawQuery(selectQuery, null);

            ArrayList<String> nombres = new ArrayList<>();
            ArrayList<String> descuentos = new ArrayList<>();
            ArrayList<Integer> imagenes = new ArrayList<>();
            while (cursor.moveToNext()) {
                nombres.add(cursor.getString(1));
                Double precio = cursor.getDouble(2);
                Integer descuento = cursor.getInt(3);
                descuentos.add("Precio: " + precio.toString() + "€ Descuento: " + descuento.toString() + "%");
                imagenes.add(rutaImagen(cursor.getInt(4)));
            }
            if (nombres.size() > 0) {
                Intent intent = new Intent(this, listaActivity.class);
                intent.putExtra("Nombres", nombres);
                intent.putExtra("Imagenes", imagenes);
                intent.putExtra("Descuentos", descuentos);
                startActivity(intent);
            } else {
                Toast.makeText(getBaseContext(), "La búsqueda a fracasado", Toast.LENGTH_LONG).show();
            }

        } else {
            if (divisionString[0].contains("TODO")) {
                selectQuery = "SELECT * FROM JUEGOS";
                Cursor cursor = db.rawQuery(selectQuery, null);

                ArrayList<String> nombres = new ArrayList<>();
                ArrayList<String> descuentos = new ArrayList<>();
                ArrayList<Integer> imagenes = new ArrayList<>();
                while (cursor.moveToNext()) {
                    nombres.add(cursor.getString(1));
                    Double precio = cursor.getDouble(2);
                    Integer descuento = cursor.getInt(3);
                    descuentos.add("Precio: " + precio.toString() + "€ Descuento: " + descuento.toString() + "%");
                    imagenes.add(rutaImagen(cursor.getInt(4)));
                }
                if (nombres.size() > 0) {
                    Intent intent = new Intent(this, listaActivity.class);
                    intent.putExtra("Nombres", nombres);
                    intent.putExtra("Imagenes", imagenes);
                    intent.putExtra("Descuentos", descuentos);
                    startActivity(intent);
                } else {
                    Toast.makeText(getBaseContext(), "La búsqueda a fracasado", Toast.LENGTH_LONG).show();
                }
            } else {
                selectQuery = "SELECT * FROM JUEGOS WHERE KEYWORD LIKE '%" + s + "%'";


                Cursor cursor = db.rawQuery(selectQuery, null);

                Juegos j = new Juegos();

                if (cursor.moveToFirst()) {
                    j.setId(cursor.getInt(0));
                    j.setNombre(cursor.getString(1));
                    j.setPrecio(cursor.getDouble(2));
                    j.setDescuento(cursor.getInt(3));
                    j.setPlataforma(cursor.getInt(4));
                    j.setKeyWord(cursor.getString(5));
                    //Log.i(this.getClass().toString(), "PARECE QUE LO PILLA"+ j.getNombre());
                }

                if (j.getId() != -1) {
                    Intent intent = new Intent(this, JuegoActivity.class);

                    intent.putExtra("Nombre", j.getNombre());
                    intent.putExtra("Precio", String.valueOf(j.getPrecio()));
                    intent.putExtra("Descuento", String.valueOf(j.getDescuento()));
                    intent.putExtra("Plataforma", String.valueOf(j.getPlataforma()));
                    startActivity(intent);
                } else {
                    Toast.makeText(getBaseContext(), "La búsqueda a fracasado", Toast.LENGTH_LONG).show();
                }
            }

        }
    }

    public int rutaImagen(int i) {
        int resultado = 0;
        switch (i) {
            case 1:
                resultado = R.mipmap.ic_steam;
                break;
            case 2:
                resultado = R.mipmap.ic_origin;
                break;
            case 3:
                resultado = R.mipmap.ic_uplay;
                break;
            case 4:
                resultado = R.mipmap.ic_battle;
                break;
            case 5:
                resultado = R.mipmap.ic_gog;
                break;

        }
        return resultado;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}

package com.brogames.xesoku.instant_pro;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Xesoku on 30/01/2017.
 */

public class LenguajeListAdapter extends ArrayAdapter {

    private final Activity context;
    private final ArrayList<String> itemname;
    private final ArrayList<String> itemdescount;
    private final ArrayList<Integer> integers;

    public LenguajeListAdapter(Activity context, ArrayList<String> itemname, ArrayList<String> itemname2, ArrayList<Integer> integers) {
        super(context, R.layout.fila_lista, itemname);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.itemname = itemname;
        this.integers = integers;
        this.itemdescount = itemname2;
    }

    public View getView(int posicion, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.fila_lista, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.texto_principal);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView etxDescripcion = (TextView) rowView.findViewById(R.id.texto_secundario);

        txtTitle.setText(itemname.get(posicion));
        imageView.setImageResource(integers.get(posicion));
        etxDescripcion.setText(itemdescount.get(posicion));

        return rowView;
    }

}

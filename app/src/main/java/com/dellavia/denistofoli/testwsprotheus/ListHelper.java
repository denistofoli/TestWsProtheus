package com.dellavia.denistofoli.testwsprotheus;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by denis.tofoli on 28/04/2016.
 */
public class ListHelper {
    private final Activity activity;

    public ListHelper(Activity activity) {
        this.activity = activity;
    }

    public void mostrar(ArrayList<TpServ> tpServs){
        if (tpServs != null){
            ArrayAdapter<TpServ> adapter = new ArrayAdapter<>(activity,android.R.layout.simple_list_item_1,tpServs);

            ListView lista = (ListView) activity.findViewById(R.id.listViewTp);
            lista.setAdapter(adapter);
        }
    }
}

package com.dellavia.denistofoli.testwsprotheus;

import android.app.Activity;
import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by denis.tofoli on 19/04/2016.
 */
public class WsTask extends AsyncTask<Object, Object, String> {
    private final Activity activity;
    private ArrayList<TpServ> tpServList;

    public WsTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        ListHelper helper = new ListHelper(this.activity);
        helper.mostrar(getTpServList());
    }


    @Override
    protected String doInBackground(Object... params) {
        HttpSoap wsProtheus = new HttpSoap();
        tpServList = wsProtheus.postThroughHttpUrlConnection();

        return null;
    }

    public ArrayList<TpServ> getTpServList() {
        return tpServList;
    }
}


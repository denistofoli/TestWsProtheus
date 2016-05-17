package com.dellavia.denistofoli.testwsprotheus;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] per = {android.Manifest.permission.INTERNET};

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(per,123);
        }

        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume(){
        super.onResume();
        new WsTask(this).execute();
    }
}

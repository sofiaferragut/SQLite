package ort.edu.sqliteapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Data extends AppCompatActivity {

    Bundle bundle;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        list = findViewById(R.id.idListView);
        bundle= getIntent().getExtras();
        String[] lista=  bundle.getStringArray("lista");

        Adaptador adaptador = new Adaptador(this, lista);
        list.setAdapter(adaptador);
    }

}

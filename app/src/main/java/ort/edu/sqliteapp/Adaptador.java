package ort.edu.sqliteapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class Adaptador extends BaseAdapter {
    Context context;
    ArrayList<String> items;
    public Adaptador(Context c, String[] is){
        this.context=c;
        this.items=new ArrayList(Arrays.asList(is));
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view= LayoutInflater.from(context).inflate(R.layout.item_lista,null);
        TextView elemento= view.findViewById(R.id.elemento);
        String item = (String) getItem(i);
        elemento.setText(item);
        return view;
    }
}

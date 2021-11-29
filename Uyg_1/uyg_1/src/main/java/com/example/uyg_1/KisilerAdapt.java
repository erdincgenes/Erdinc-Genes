package com.example.uyg_1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class KisilerAdapt extends BaseAdapter {
    private LayoutInflater mInflater;
    private ArrayList<Kisiler>KisilerList;

    public KisilerAdapt(Activity activity,ArrayList<Kisiler>KisilerList) {
        this.mInflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.KisilerList = KisilerList;
    }
    @Override
    public int getCount(){
        return KisilerList.size();
    }
    @Override
    public Object getItem(int position){
        return KisilerList.get(position);
    }
    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        convertView = mInflater.inflate(R.layout.kisi,null);
        TextView kisiad=(TextView)convertView.findViewById(R.id.isimText);
        TextView kisinumara=(TextView)convertView.findViewById(R.id.numaraText);
        ImageView kisiresim=(ImageView)convertView.findViewById(R.id.resim);
        Kisiler kisi=KisilerList.get(position);
        kisiad.setText(kisi.get_isim());
        kisinumara.setText(kisi.get_numara());
        if(kisi.get_resim()!=null)
            kisiresim.setImageBitmap(kisi.get_resim());
        else
            kisiresim.setImageResource(R.drawable.ic_launcher_background);
        convertView.setTag(kisi.get_isim());
        return convertView;
    }
}

package com.example.uyg_1;

import android.graphics.Bitmap;
import android.widget.Switch;
public class Kisiler {
    private  String isim;
    private  String numara;
    private Bitmap resim=null;
    public void set_resim(Bitmap resim){this.resim=resim;}
    public  Bitmap get_resim(){return resim;}
    public  void set_isim(String isim){this.isim=isim;}
    public   String get_isim(){return isim;}
    public  void set_numara(String numara){this.numara=numara;}
    public  String get_numara(){return numara; }
}


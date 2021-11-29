package com.example.uyg_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.text.BreakIterator;
import java.util.ArrayList;

public class RehberList extends AppCompatActivity {

    public static final int REQUEST_READ_CONTACTS=1;

    ListView rehber_list;
    Button btn;
    ImageView img;
    static String text;
    static String text2;
    public static final String SHARED_PREFS="sharedPrefs";
    public static final String TEXTB="a";
    public static final String TEXTB2="b";
    
    public static int idSnf;//

    private String[] SELECT = { "id", "kullanici_adi", "sifre","kullanilan_yer","hesap_no"};//select islemi için hangi alanlarin çekilecegini belirleyelim


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rehber_list);
        rehber_list=findViewById(R.id.rehber_list);

        

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)== PackageManager.PERMISSION_GRANTED){
            ArrayList<Kisiler> kisiler=new ArrayList<Kisiler>();

            Cursor rehber= getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null ,null,null,null);
            while (rehber.moveToNext())
            {
                @SuppressLint("Range") String adsoyad=rehber.getString(rehber.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                @SuppressLint("Range") String numara=rehber.getString(rehber.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                @SuppressLint("Range") String contactID=rehber.getString(rehber.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));

                Kisiler r_nesnesi=new Kisiler();
                r_nesnesi.set_isim(adsoyad);
                r_nesnesi.set_numara(numara);
                r_nesnesi.set_resim(ContactPhoto(contactID));
                kisiler.add(r_nesnesi);

            }
            rehber.close();
            KisilerAdapt KisilerAdapter = new KisilerAdapt(this,kisiler);
            if(rehber_list!=null){
                rehber_list.setAdapter(KisilerAdapter);
            }
        }
        else
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},REQUEST_READ_CONTACTS);

        btn=findViewById(R.id.smsButon);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsGecis= new Intent(RehberList.this,SmsPage.class);
                startActivity(smsGecis);
            }
        });
    }
    public void saveData(){
        SharedPreferences sharedPreferences =getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();

        editor.apply();

    }
    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        text=sharedPreferences.getString(TEXTB,"");
        text2=sharedPreferences.getString(TEXTB2,"");
    }
    public static void updateViews(TextView namearea,TextView numarea){
        namearea.setText(TEXTB);
        numarea.setText(TEXTB2);
    }

    public Bitmap ContactPhoto(String contactID){
        Uri contactUri= ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI,Long.valueOf(contactID));
        Uri PhotoUri=Uri.withAppendedPath(contactUri,ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);
        Cursor cursor = getContentResolver().query(PhotoUri,new String[]{ContactsContract.Contacts.Photo.PHOTO},null,null,null);
        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToNext();
            byte[] data =cursor.getBlob(0);
            if (data!=null){
                return BitmapFactory.decodeStream(new ByteArrayInputStream(data));
            }
            else
                return null;

        }
        cursor.close();
        return null;
    }
}
package com.example.uyg_1;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SmsPage extends AppCompatActivity {
    private Button btnsms;
    private EditText numaraSms,textSms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_page);
        btnsms=findViewById(R.id.btnSmsGonder);
        numaraSms=findViewById(R.id.numArea);
        textSms=findViewById(R.id.msgArea);
        btnsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED){
                        smsGonderMethod();
                    }
                    else{
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
                    }
                }

            }
        });
    }
    private void smsGonderMethod(){
        String telNo=numaraSms.getText().toString().trim();
        String sms=textSms.getText().toString().trim();

       try{
           SmsManager smsManager=SmsManager.getDefault();
           smsManager.sendTextMessage(telNo,null,sms,null,null);
           Toast.makeText(this,"Gönderildi.",Toast.LENGTH_SHORT).show();
       }
       catch(Exception e){
           e.printStackTrace();
           Toast.makeText(this,"Basarisiz.",Toast.LENGTH_SHORT).show();

        }
    }
}
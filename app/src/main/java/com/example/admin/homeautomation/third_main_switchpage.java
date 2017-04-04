package com.example.admin.homeautomation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.net.URL;
import java.net.URLConnection;

public class third_main_switchpage extends AppCompatActivity {
Intent in;
    boolean doubleBackToExitPressedOnce = false;
    ToggleButton fan_button,light_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_main_switchpage);
        fan_button=(ToggleButton)findViewById(R.id.toggleButton);
        light_button=(ToggleButton)findViewById(R.id.toggleButton2);
    }

    public void fan_click(View view){
        String check1=fan_button.getText().toString();
        if(check1.equalsIgnoreCase("on")){
            Toast.makeText(this, "ON", Toast.LENGTH_SHORT).show();
            String link = "http://ipaddress/gpio/0";
            try {
                URL url = new URL(link);
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else if(check1.equalsIgnoreCase("off")){
            Toast.makeText(this, "OFF", Toast.LENGTH_SHORT).show();
            String link = "http://ipaddress/gpio/1";
            try {
                URL url = new URL(link);
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void light_click(View view){
        String check2=light_button.getText().toString();
        if(check2.equalsIgnoreCase("on")){
            Toast.makeText(this, "ON", Toast.LENGTH_SHORT).show();
            String link = "http://ipaddress/gpio/0";
            try {
                URL url = new URL(link);
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else if(check2.equalsIgnoreCase("off")){
            Toast.makeText(this, "OFF", Toast.LENGTH_SHORT).show();
            String link = "http://ipaddress/gpio/1";
            try {
                URL url = new URL(link);
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public  void logout(View view){
        Log.i("homepage","logging out");
        LoginVerify.flag="false";
        Log.i("HomePage","Login Verify flag:"+LoginVerify.flag);
        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
        Toast.makeText(getApplicationContext(),"Logged out successfully!", Toast.LENGTH_SHORT).show();
        finish();
        in = new Intent(third_main_switchpage.this, MainActivity.class);
        //-------------to clear all activities on top of stack
        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(in);
    }

    @Override
    public void onBackPressed() {
        Log.i("Main Activity","onBackPressed");
        if (doubleBackToExitPressedOnce) {      //code to handle double-back-exit
            super.onBackPressed();
            //finish();
            onDestroy();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Main Activity","onDestroy");
    }
}

package com.example.admin.homeautomation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class second_main_homepage extends AppCompatActivity {
    String name="",hub_tag="";
    EditText hub_id;
    TextView welcome_msg;
    Intent in;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("HomePage","In onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_main_homepage);
        welcome_msg=(TextView)findViewById(R.id.WELCOME_USER);
        hub_id=(EditText)findViewById(R.id.hub_id);
        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        name=sharedpreferences.getString(MainActivity.Name,"nameKey");
        welcome_msg.setText("Welcome "+name + " !");
    }

    public void search(View view){
        hub_tag=hub_id.getText().toString();
        if(hub_tag.equals("123")){
            in = new Intent(second_main_homepage.this, third_main_switchpage.class);
            //-------------to clear all activities on top of stack
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
        }
        else{
            Toast.makeText(getApplicationContext(),"Invalid Hub ID!", Toast.LENGTH_SHORT).show();
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
        in = new Intent(second_main_homepage.this, MainActivity.class);
        //-------------to clear all activities on top of stack
        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(in);
    }

    @Override
    public void onBackPressed() {
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
    }

}

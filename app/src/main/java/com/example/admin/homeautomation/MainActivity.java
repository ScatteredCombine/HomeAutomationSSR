package com.example.admin.homeautomation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static EditText uname,pwd;
    static ProgressBar progressBar;
    Button login_button;
    TextView login_status;
    //Declaring variables for SharedPreferences
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    //public static final String Pass = "passKey";
    public static final String loggedIn = "flagKey";
    public  static boolean flag=false;
    //Declaring req. variables
    SharedPreferences sharedpreferences;
    String store_uname,store_pwd;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Entry in MainActivity","Entered onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initialising view components
        uname=(EditText)findViewById(R.id.username);
        pwd=(EditText)findViewById(R.id.password);
        login_button=(Button)findViewById(R.id.LOGIN_BUTTON);
        login_status=(TextView)findViewById(R.id.login_status);
        login_status.setGravity(Gravity.CENTER);    //Aligning status text to center
        progressBar=(ProgressBar)findViewById(R.id.progressBar);    //Progress bar feature added in v1.1
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);  //Initialising sharedpreferences

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Main Activity","LoginButton onClick");
                //-------- this is to hide the keyboard after pressing login button
                pwd.clearFocus();
                if (getCurrentFocus() != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
                //--------
                progressBar.setIndeterminate(true);
                progressBar.setVisibility(View.VISIBLE); //Show progress bar
                progressBar.setProgress(5);
                store_uname  = uname.getText().toString();  //Getting user input
                store_pwd  = pwd.getText().toString();
                //Calling AsyncTask to provide network connection
                Log.i("Main Activity","Calling AsyncTask..");
                new LoginVerify(MainActivity.this, login_status).execute(store_uname, store_pwd);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Main Activity","OnResume Method");
        flag = sharedpreferences.getBoolean(loggedIn,false);
        //If we will get true
        if(flag){
            //We will start the Profile Activity
            Log.i("Main Activity","User logged in already so calling homepage");
            Intent intent = new Intent(MainActivity.this, second_main_homepage.class);
            //-------clearing all activities on top of stack
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        Log.i("Main Activity","onBackPressed");
        if (doubleBackToExitPressedOnce) {      //code to handle double-back-exit
            super.onBackPressed();
            //finish();
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

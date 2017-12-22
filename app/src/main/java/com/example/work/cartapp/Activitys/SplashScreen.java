package com.example.work.cartapp.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

import com.example.work.cartapp.R;
import com.example.work.cartapp.Extras.SessionManager.SessionManager;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashScreen extends AppCompatActivity {

    public static final int TIME_OUT=500;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_splash_screen);
        sessionManager=new SessionManager(this);



        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


//                if(sessionManager.getUsername().equals("1"))
//                {
//                    Intent intent=new Intent(getBaseContext(),Login1.class);
//                    startActivity(intent);
//                    finish();
//
//                }
//                else
//                {
                if(sessionManager.getUsername().equals("1")){
                    Intent intent=new Intent(getBaseContext(),LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent=new Intent(getBaseContext(),HomeScreen.class);
                    startActivity(intent);
                    finish();
                }

//                }


            }
        },TIME_OUT);

    }

}

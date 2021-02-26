package com.example.mopmas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.example.mopmas.LandingPage.MyPREFERENCES;
import static com.example.mopmas.LandingPage.user;
import static com.example.mopmas.LandingPage.userth;
import static com.example.mopmas.LandingPage.usertype;

public class SplashScreen extends AppCompatActivity {
    Window window;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        if(Build.VERSION.SDK_INT>=21){
            window=SplashScreen.this.getWindow();
            window.setStatusBarColor(SplashScreen.this.getResources().getColor(R.color.gradEnd));


        }
        final Handler handler = new Handler();
        SharedPreferences sharedPreferences=getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        final String type=sharedPreferences.getString(usertype,"");







        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (type.equals("PATIENT")){

                        startActivity(new Intent(SplashScreen.this, UserLogin.class));
                        Toast.makeText(SplashScreen.this, type, Toast.LENGTH_LONG).show();
                        finish();



                } else if(type.equals("THERAPIST")){
                    startActivity(new Intent(SplashScreen.this,TherapistLogin.class));
                    Toast.makeText(SplashScreen.this,type,Toast.LENGTH_LONG).show();
                    finish();


                }
                else {
                    startActivity(new Intent(SplashScreen.this, LandingPage.class));
                    finish();
                }


            }
        },1000);


    }
}

package com.example.mopmas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class LandingPage extends AppCompatActivity {
    Window window;
/*    private Button bt4,bt5;*/
    public static final String MyPREFERENCES="Mypref";
    public static final String user="PATIENT";
    public static final String userth="THERAPIST";
    public static final String usertype="type";
    CardView bt4,bt5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>=21){
            window=LandingPage.this.getWindow();
            window.setStatusBarColor(LandingPage.this.getResources().getColor(R.color.gradEnd));
        }
        setContentView(R.layout.activity_landing_page);
       /* bt4=findViewById(R.id.button4);
        bt5=findViewById(R.id.button5);*/
       bt4=findViewById(R.id.cardv1);
       bt5=findViewById(R.id.cardv2);
        final SharedPreferences sharedPreferences=getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(usertype,user);
                editor.commit();

              startActivity(new Intent(LandingPage.this,UserLogin.class));

            }
        });

        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(usertype,userth);
                editor.commit();

                startActivity(new Intent(LandingPage.this,TherapistLogin.class));


            }
        });
    }
}

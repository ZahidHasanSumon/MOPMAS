package com.example.mopmas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mopmas.TherapistActivity.MainActivityTherapist;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TherapistLogin extends AppCompatActivity {
    private Button bt2,bt3;
    private EditText email,pass;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT>=21){
            window=TherapistLogin.this.getWindow();
            window.setStatusBarColor(TherapistLogin.this.getResources().getColor(R.color.gradEnd));
        }
        setContentView(R.layout.activity_therapist_login);
        bt2=findViewById(R.id.button2);
        bt3=findViewById(R.id.button3);
        email=findViewById(R.id.editText5);
        pass=findViewById(R.id.editText6);
        mFirebaseAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if( mFirebaseUser != null ){
                    Toast.makeText(TherapistLogin.this,"You are logged in",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(TherapistLogin.this, MainActivityTherapist .class);
                    startActivity(i);
                    finish();

                }

            }
        };


        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail = email.getText().toString();
                String pwd = pass.getText().toString();
                if(mail.isEmpty()){
                    email.setError("Please enter email id");
                    email.requestFocus();
                }
                else  if(pwd.isEmpty()){
                    pass.setError("Please enter your password");
                    pass.requestFocus();
                }
                else  if(mail.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(TherapistLogin.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
                }
                else  if(!(mail.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(mail, pwd).addOnCompleteListener(TherapistLogin.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(TherapistLogin.this,"Login Error, Please Login Again",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Intent i = new Intent(TherapistLogin.this, MainActivityTherapist.class);
                                startActivity(i);
                                finish();



                            }
                        }
                    });
                }
                else{
                    Toast.makeText(TherapistLogin.this,"Error Occurred!",Toast.LENGTH_SHORT).show();

                }

                /*startActivity(new Intent(TherapistLogin.this,MainActivity.class));*/
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TherapistLogin.this,TherapistRegistration.class));

            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onStop() {
        if (mAuthStateListener != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthStateListener);
        }
        super.onStop();
    }

}

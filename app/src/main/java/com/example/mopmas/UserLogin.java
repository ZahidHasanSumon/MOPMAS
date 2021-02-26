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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserLogin extends AppCompatActivity {
    Window window;
    private Button bt2,bt3;
    private EditText email,pass;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        if(Build.VERSION.SDK_INT>=21){
            window=UserLogin.this.getWindow();
            window.setStatusBarColor(UserLogin.this.getResources().getColor(R.color.gradEnd));
        }

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
                    Toast.makeText(UserLogin.this,"You are logged in",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(UserLogin.this, MainActivity.class);
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
                    Toast.makeText(UserLogin.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
                }
                else  if(!(mail.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(mail, pwd).addOnCompleteListener(UserLogin.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(UserLogin.this,"Login Error, Please Login Again",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Intent i = new Intent(UserLogin.this,MainActivity.class);
                                startActivity(i);
                                finish();



                            }
                        }
                    });
                }
                else{
                    Toast.makeText(UserLogin.this,"Error Occurred!",Toast.LENGTH_SHORT).show();

                }

            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserLogin.this,UserRegistration.class));
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

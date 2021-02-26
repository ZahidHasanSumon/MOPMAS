package com.example.mopmas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserRegistration extends AppCompatActivity {

    private Button bt;
    private Spinner sp;
    private EditText email, username, pass, repass;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    private NewUser user;
    Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        if(Build.VERSION.SDK_INT>=21){
            window=UserRegistration.this.getWindow();
            window.setStatusBarColor(UserRegistration.this.getResources().getColor(R.color.gradEnd));
        }


        bt = findViewById(R.id.button);

        email = findViewById(R.id.editText2);
        username = findViewById(R.id.editText3);
        pass = findViewById(R.id.editText4);
        repass = findViewById(R.id.editText5);
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //insert data into firebase database

                final String mail = email.getText().toString().trim();
                final String name = username.getText().toString().trim();
                final String pwd = pass.getText().toString().trim();
                String repwd = repass.getText().toString().trim();



                if (mail.isEmpty()) {
                    email.setError("Please enter Email");
                    email.requestFocus();
                    return;
                } else if (name.isEmpty()) {
                    username.setError("Please enter username");
                    username.requestFocus();
                    return;
                } else if (pwd.isEmpty()) {
                    pass.setError("Please enter password");
                    pass.requestFocus();
                    return;
                } else if (repwd.isEmpty()) {
                    repass.setError("Re-type password");
                    repass.requestFocus();
                    return;
                } else if (!repwd.equals(pwd)) {
                    repass.setError("Password doesn't match");
                    pass.setText("");
                    repass.setText("");
                    return;

                }else {
                    mAuth.createUserWithEmailAndPassword(mail, pwd)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {

                                        final String userid=String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getUid());

                                        NewUser user = new NewUser(mail,name, pwd,userid,"");

                                        FirebaseDatabase.getInstance().getReference("Users")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                if (task.isSuccessful()) {
                                                    Toast.makeText(UserRegistration.this, "Successful!", Toast.LENGTH_LONG).show();

                                                    startActivity(new Intent(UserRegistration.this,MainActivity.class));
                                                    finish();
                                                } else {
                                                    //display a failure message
                                                    Toast.makeText(UserRegistration.this,"Please try again",Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });

                                    } else {
                                        Toast.makeText(UserRegistration.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }


                            });
                }
            }
        });

    }



}
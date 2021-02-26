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
import com.example.mopmas.TherapistActivity.NewTherapist;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TherapistRegistration extends AppCompatActivity {
    private Button bt;
    private EditText et1,et2,et3,et4,et5;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    private NewTherapist newTherapist;
    Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>=21){
            window=TherapistRegistration.this.getWindow();
            window.setStatusBarColor(TherapistRegistration.this.getResources().getColor(R.color.gradEnd));
        }
        setContentView(R.layout.activity_therapist_registration);
        bt=findViewById(R.id.button);
        et1=findViewById(R.id.editText);
        et2=findViewById(R.id.editText2);
        et3=findViewById(R.id.editText7);
        et4=findViewById(R.id.editText3);
        et5=findViewById(R.id.editText4);

        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String mail = et1.getText().toString().trim();
                final String name = et2.getText().toString().trim();
                final String license = et3.getText().toString().trim();
                final String pwd = et4.getText().toString().trim();
                String repwd = et5.getText().toString().trim();


                if (mail.isEmpty()) {
                    et1.setError("Please enter Email");
                    et1.requestFocus();
                    return;
                } else if (name.isEmpty()) {
                    et2.setError("Please enter username");
                    et2.requestFocus();
                    return;
                } else if (license.isEmpty()) {
                    et3.setError("Please enter license number");
                    et3.requestFocus();
                    return;
                } else if (pwd.isEmpty()) {
                    et4.setError("Please enter password");
                    et4.requestFocus();
                    return;
                } else if (repwd.isEmpty()) {
                    et5.setError("Re-type password");
                    et5.requestFocus();
                    return;
                } else if (!repwd.equals(pwd)) {
                    et5.setError("Password doesn't match");
                    et4.setText("");
                    et5.setText("");
                    return;

                }else {
                    mAuth.createUserWithEmailAndPassword(mail, pwd)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {

                                        NewTherapist user = new NewTherapist(mail,name,license,pwd);

                                        FirebaseDatabase.getInstance().getReference("Therapist")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                if (task.isSuccessful()) {
                                                   // startActivities(new Intent(TherapistRegistration.this,MainActivity.class));
                                                    startActivity(new Intent(TherapistRegistration.this, MainActivityTherapist.class));
                                                    Toast.makeText(TherapistRegistration.this, "Successful!", Toast.LENGTH_LONG).show();
                                                    finish();
                                                } else {
                                                    //display a failure message
                                                    Toast.makeText(TherapistRegistration.this,"Please try again",Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });

                                    } else {
                                        Toast.makeText(TherapistRegistration.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }


                            });
                }
                //startActivity(new Intent(TherapistRegistration.this,MainActivity.class));
            }
        });
    }
}

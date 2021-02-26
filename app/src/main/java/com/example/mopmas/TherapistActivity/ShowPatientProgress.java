package com.example.mopmas.TherapistActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mopmas.AdapterExercise;
import com.example.mopmas.NewUser;
import com.example.mopmas.R;
import com.example.mopmas.UserProgress;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShowPatientProgress extends AppCompatActivity {
    private RecyclerView recyclerView;
    AdapterExercise adapterExercise;
    List<NewUser> userList;
    private String id;
    private ProgressDialog progressDialog;
    LinearLayoutManager manager;
    Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_patient_progress);
        if(Build.VERSION.SDK_INT>=21){
            window=ShowPatientProgress.this.getWindow();
            window.setStatusBarColor(ShowPatientProgress.this.getResources().getColor(R.color.gradEnd));
        }

        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Searching.\nWait a moment.");
        progressDialog.show();
        recyclerView=findViewById(R.id.recycler_view10);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        if(getIntent().hasExtra("Pid")){
            id=getIntent().getStringExtra("Pid");



        }

        userList=new ArrayList<>();


        getAllUsers(id);
    }
    private void getAllUsers(String id){
        Toast.makeText(ShowPatientProgress.this,id,Toast.LENGTH_LONG).show();

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Users").child(id).child("ExerciseData");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear();

                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    NewUser newUser=ds.getValue(NewUser.class);

                    userList.add(newUser);
                    Collections.sort(userList);
                    adapterExercise=new AdapterExercise(ShowPatientProgress.this,userList);
                    recyclerView.setAdapter(adapterExercise);



                }
                progressDialog.dismiss();




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
}

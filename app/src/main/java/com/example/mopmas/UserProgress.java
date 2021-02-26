package com.example.mopmas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;

import com.example.mopmas.TherapistActivity.AdapterUsers;
import com.example.mopmas.TherapistActivity.ModelUser;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserProgress extends AppCompatActivity {
    private RecyclerView rc;
     AdapterExercise adapterExercise;
    List<NewUser> userList;
    private ProgressDialog progressDialog;
    Window window;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_progress);
        if(Build.VERSION.SDK_INT>=21){
            window=UserProgress.this.getWindow();
            window.setStatusBarColor(UserProgress.this.getResources().getColor(R.color.gradEnd));
        }

        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Searching.\nWait a moment.");
        progressDialog.show();
        rc=findViewById(R.id.recyclerView);


        rc.setHasFixedSize(true);
        rc.setLayoutManager(new LinearLayoutManager(this));



        userList=new ArrayList<>();

      getAllUsers();

    }


    private void getAllUsers() {
        final FirebaseUser fuser= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("ExerciseData");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear();

                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    NewUser newUser=ds.getValue(NewUser.class);

                    /*if(!modelUser.getUid().equals(fuser.getUid())){
                        userList.add(modelUser);


                    }*/

                    userList.add(newUser);
                    adapterExercise=new AdapterExercise(UserProgress.this,userList);
                    rc.setAdapter(adapterExercise);



                }
                progressDialog.dismiss();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

}

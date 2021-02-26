package com.example.mopmas.ExerciseActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mopmas.NewUser;
import com.example.mopmas.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;

public class BottomSheetDialog extends BottomSheetDialogFragment {

    String time,exercise,exerciseType,duration;
    NewUser newUser;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    private String rep;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public BottomSheetDialog() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.activity_bottom_sheet_layout, container, false);



        Button button1 = v.findViewById(R.id.button1);
        final Button button2 = v.findViewById(R.id.button2);

        TextView tx=v.findViewById(R.id.textView44);
        final TextView tx1=v.findViewById(R.id.textView100);

        final EditText edt=v.findViewById(R.id.editText200);









        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();



         tx.setText(time+"s");
         /*tx1.setText(exercise);*/



   button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rep=edt.getText().toString().trim();


                final FirebaseUser user=mAuth.getCurrentUser();
                if(rep.isEmpty()){
                    edt.setError("Enter repetition time");
                    edt.requestFocus();

                }

                else if(user !=null){


                    Calendar calendar=Calendar.getInstance();
                    String date= DateFormat.getDateInstance().format(calendar.getTime());
                    newUser=new NewUser(exerciseType,date,time,rep);


                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("ExerciseData").push()
                            .setValue(newUser);
                    Toast.makeText(getActivity(),"Data save successfully",Toast.LENGTH_LONG).show();


                    dismiss();


                }else {
                    Toast.makeText(getActivity(),"Something went wrong",Toast.LENGTH_LONG).show();
                }



            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
            }
        });

        return v;
    }


}

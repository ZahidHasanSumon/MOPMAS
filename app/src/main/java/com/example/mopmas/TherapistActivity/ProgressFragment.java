package com.example.mopmas.TherapistActivity;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mopmas.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProgressFragment extends Fragment {
    RecyclerView recyclerView;
    AdapterPatient adapterUsers;
    List<ModelUser> userList;
    private ProgressDialog progressDialog;

    public ProgressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v=inflater.inflate(R.layout.fragment_progress, container, false);
        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setTitle("Searching.\nWait a moment.");
        progressDialog.show();

        recyclerView=v.findViewById(R.id.recycler_view5);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        userList=new ArrayList<>();

        getAllUsers();
        return v;
    }

    private void getAllUsers() {
        final FirebaseUser fuser= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Therapist").child(FirebaseAuth.getInstance().getCurrentUser()
                .getUid()).child("AddedPatient");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear();

                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    ModelUser modelUser=ds.getValue(ModelUser.class);

                    /*if(!modelUser.getUid().equals(fuser.getUid())){
                        userList.add(modelUser);


                    }*/

                    userList.add(modelUser);

                    adapterUsers=new AdapterPatient(getActivity(),userList);

                    recyclerView.setAdapter(adapterUsers);



                }
                progressDialog.dismiss();




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}

package com.example.mopmas.TherapistActivity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mopmas.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class AddPatientFragment extends Fragment {
    RecyclerView recyclerView;
    AdapterUsers adapterUsers;
    List<ModelUser> userList;
    private ProgressDialog progressDialog;
    Window window;
    Toolbar toolbar;
    private ImageView imageView;



    public AddPatientFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_add_patient, container, false);

        if(Build.VERSION.SDK_INT>=21){
            window=getActivity().getWindow();
            window.setStatusBarColor(getActivity().getResources().getColor(R.color.gradEnd));



        }
        recyclerView=v.findViewById(R.id.users_recyclerView);
        toolbar=(Toolbar)v.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("Search your patient here");
        toolbar.setTitleTextColor(Color.WHITE);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        /*imageView=v.findViewById(R.id.add_patient);*/

        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setTitle("Searching.\nWait a moment.");
        progressDialog.show();

        userList=new ArrayList<>();

        getAllUsers();


      /*  imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"You have clicked",Toast.LENGTH_LONG).show();
            }
        });*/


        return v;
    }

    private void getAllUsers() {
        final FirebaseUser fuser= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Users");
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
                    adapterUsers=new AdapterUsers(getActivity(),userList);
                    recyclerView.setAdapter(adapterUsers);



                }
                progressDialog.dismiss();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }






    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        inflater.inflate(R.menu.search_bar, menu);

        MenuItem item=menu.findItem(R.id.action_search);

        SearchView searchView= (SearchView) MenuItemCompat.getActionView(item);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!TextUtils.isEmpty(query.trim())){
                    Toast.makeText(getActivity(),query,Toast.LENGTH_LONG).show();
                    searchUsers(query);



                }else{
                    getAllUsers();

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(!TextUtils.isEmpty(newText.trim())){
                    searchUsers(newText);


                }else{
                    getAllUsers();

                }
                return false;
            }
        });



        super.onCreateOptionsMenu(menu, inflater);
    }




    private void searchUsers(final String newText) {

        final FirebaseUser fuser= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Users");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear();

                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    ModelUser modelUser=ds.getValue(ModelUser.class);

                    /*if(!modelUser.getUid().equals(fuser.getUid())){
                        userList.add(modelUser);


                    }*/

                    if (modelUser.getUsername().toLowerCase().contains(newText.toLowerCase())||
                    modelUser.email.toLowerCase().contains(newText.toLowerCase())){
                        userList.add(modelUser);

                    }


                    adapterUsers=new AdapterUsers(getActivity(),userList);
                    adapterUsers.notifyDataSetChanged();
                    recyclerView.setAdapter(adapterUsers);



                }
                progressDialog.dismiss();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }







    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}

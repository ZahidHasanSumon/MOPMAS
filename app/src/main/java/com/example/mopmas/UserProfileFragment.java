package com.example.mopmas;



import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.mopmas.ExerciseActivity.DatabaseHelper;
import com.example.mopmas.ExerciseActivity.RunningActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileFragment extends Fragment {
    private TextView tv,tv1,prog,run;
    private Spinner spinner;
    private TextView tx,tx1,tx2;
    private static final String TAG = "UserProfileFragment";

    private CircleImageView imageView;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;

    SharedPreferences lastselect;
    SharedPreferences.Editor editor;
    FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase database;
    private FirebaseUser user;

    private ProgressDialog progressBar;
    private ProgressDialog progressDialog;

    private long fileSize = 0;
    public static final String MyPREF="Mypref";
    public static final String exercisetype="All Exercise";

    String name,email,password,useruid;
    Window window;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_user_profile, container, false);

        if(Build.VERSION.SDK_INT>=21) {
            window = getActivity().getWindow();
            window.setStatusBarColor(getActivity().getResources().getColor(R.color.gradEnd));
        }


        lastselect=this.getActivity().getSharedPreferences("LastSetting",Context.MODE_PRIVATE);
        editor=lastselect.edit();

        final int LastClick=lastselect.getInt("LastClick",0);

        spinner=v.findViewById(R.id.spinner5);
        tx=v.findViewById(R.id.textView122);
        tx1=v.findViewById(R.id.tv_name);
        tx2=v.findViewById(R.id.email);
        prog=v.findViewById(R.id.prog);
        imageView=v.findViewById(R.id.profilepic);
        run=v.findViewById(R.id.run);

        user=FirebaseAuth.getInstance().getCurrentUser();
        if(user.getPhotoUrl()!=null){


            Glide.with(getActivity()).load(user.getPhotoUrl()).into(imageView);



        }


        mFirebaseAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=database.getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setTitle("We are setting up your profile.\nWait a moment.");
        progressDialog.show();


        databaseReference.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                NewUser user=dataSnapshot.getValue(NewUser.class);
                tx1.setText(user.getUsername());
                tx2.setText(user.getEmail());
                progressDialog.dismiss();

                name=user.getUsername();
                email=user.getEmail();
                password=user.getPassword();
                useruid=user.getUid();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(),databaseError.getCode(),Toast.LENGTH_LONG).show();

            }
        });

        prog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),UserProgress.class));
            }
        });


//spinner for workout
        final SharedPreferences sharedPreferences=getActivity().getSharedPreferences(MyPREF,0);

        final ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(getActivity(),R.array.exercise_type,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(LastClick);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                editor.putInt("LastClick",position).commit();
                String text = parent.getItemAtPosition(position).toString();

                if (!text.equals("All Exercise") & !text.equals("Legs") & !text.equals("Arms")& !text.equals("Balance and Core")& !text.equals("Shoulders and Upper Extremity")&!text.equals("Wrists, Hands, and Fingers")){
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString(exercisetype,text);
                    editor.commit();

                }else if(!text.equals("All Exercise") & !text.equals("Legs") & !text.equals("Arms")& !text.equals("Balance and Core")& !text.equals("Shoulders and Upper Extremity")&!text.equals("Exercises for Paralysis")){
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString(exercisetype,text);
                    editor.commit();

                }else if(!text.equals("All Exercise") & !text.equals("Legs") & !text.equals("Arms")& !text.equals("Balance and Core")& !text.equals("Wrists, Hands, and Fingers")&!text.equals("Exercises for Paralysis")){
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString(exercisetype,text);
                    editor.commit();


                }else if(!text.equals("All Exercise") & !text.equals("Legs") & !text.equals("Arms")& !text.equals("Shoulders and Upper Extremity")& !text.equals("Wrists, Hands, and Fingers")&!text.equals("Exercises for Paralysis")){
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString(exercisetype,text);
                    editor.commit();

                }else if(!text.equals("All Exercise") & !text.equals("Legs") & !text.equals("Balance and Core")& !text.equals("Shoulders and Upper Extremity")& !text.equals("Wrists, Hands, and Fingers")&!text.equals("Exercises for Paralysis")){
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString(exercisetype,text);
                    editor.commit();


                }else if(!text.equals("All Exercise") & !text.equals("Arms") & !text.equals("Balance and Core")& !text.equals("Shoulders and Upper Extremity")& !text.equals("Wrists, Hands, and Fingers")&!text.equals("Exercises for Paralysis")){
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString(exercisetype,text);
                    editor.commit();


                }else if(!text.equals("Legs") & !text.equals("Arms") & !text.equals("Balance and Core")& !text.equals("Shoulders and Upper Extremity")& !text.equals("Wrists, Hands, and Fingers")&!text.equals("Exercises for Paralysis")){
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString(exercisetype,text);
                    editor.commit();


                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RunningActivity.class));
            }
        });

        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(getActivity(), LandingPage.class);
                startActivity(i);
                getActivity().finish();
            }
        });

        //Profile image
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery, "Select Picture"), PICK_IMAGE);

            }
        });



       return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();

            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),imageUri);
                imageView.setImageBitmap(bitmap);
                handleUpload(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void handleUpload(Bitmap bitmap) {
        progressBar=new ProgressDialog(getActivity());
        progressBar.setTitle("Wait a moment.\nFile is uploading ...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.show();



        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        final StorageReference reference = FirebaseStorage.getInstance().getReference()
                .child("profileImages")
                .child(uid + ".jpeg");


        reference.putBytes(baos.toByteArray())
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        //reference.getDownloadUrl();



                        //trying
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String url=String.valueOf(uri);
                                NewUser user=new NewUser(email,name,password,useruid,url);
                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                    }
                                });

                            }
                        });
                        //finish here



                        getDownloadUrl(reference);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "onFailure: ",e.getCause() );
                    }
                });
    }
    private void getDownloadUrl(StorageReference reference) {

        reference.getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {


                    @Override
                    public void onSuccess(Uri uri) {


                        Log.d(TAG, "onSuccess: " + uri);
                        setUserProfileUrl(uri);
                    }
                });
    }




    private void setUserProfileUrl(Uri uri) {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                .setPhotoUri(uri)
                .build();

        user.updateProfile(request)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        progressBar.dismiss();
                        Toast.makeText(getActivity(), "Updated succesfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.dismiss();
                        Toast.makeText(getActivity(), "Profile image failed...", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

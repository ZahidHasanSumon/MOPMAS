package com.example.mopmas.TherapistActivity;

import android.app.ProgressDialog;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mopmas.LandingPage;
import com.example.mopmas.NewUser;
import com.example.mopmas.R;
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

import static android.app.Activity.RESULT_OK;


public class ProfileFragment extends Fragment {
    Window window;

    private TextView tv,tv1;
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

    public ProfileFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_profile, container, false);
        tx=v.findViewById(R.id.textView122);
        tx1=v.findViewById(R.id.tv_name);
        tx2=v.findViewById(R.id.email);
        imageView=v.findViewById(R.id.profilepic);

        if(Build.VERSION.SDK_INT>=21){
            window=getActivity().getWindow();
            window.setStatusBarColor(getActivity().getResources().getColor(R.color.gradEnd));


        }

        user=FirebaseAuth.getInstance().getCurrentUser();
        if(user.getPhotoUrl()!=null){

            Glide.with(getActivity()).load(user.getPhotoUrl()).into(imageView);


        }


        mFirebaseAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=database.getReference("Therapist").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setTitle("We are setting up your profile.\nWait a moment.");
        progressDialog.show();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                NewTherapist user=dataSnapshot.getValue(NewTherapist.class);
                tx1.setText(user.getUsername());
                tx2.setText(user.getEmail());
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(),databaseError.getCode(),Toast.LENGTH_LONG).show();

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

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        final StorageReference reference = FirebaseStorage.getInstance().getReference()
                .child("profileImages")
                .child(uid + ".jpeg");


        reference.putBytes(baos.toByteArray())
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        //reference.getDownloadUrl();
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
                        Toast.makeText(getActivity(), "Profile image failed...", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

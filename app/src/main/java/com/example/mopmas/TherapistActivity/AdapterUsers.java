package com.example.mopmas.TherapistActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mopmas.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.EventListener;
import java.util.List;

public class AdapterUsers extends RecyclerView.Adapter<AdapterUsers.MyHolder> {
    String MyPREFERENCES="Mypref";
    String userId;


    Context context;
    List<ModelUser> userList;



    public AdapterUsers(Context context, List<ModelUser> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view= LayoutInflater.from(context).inflate(R.layout.row_users,parent,false);




        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int position) {

        String userImage=userList.get(position).getImageUrl();
        String userName=userList.get(position).getUsername();
        final String userEmail=userList.get(position).getEmail();
        final String uid=userList.get(position).getUid();
        final ModelUser modelUser=new ModelUser(userName,userEmail,userImage,uid);






        holder.mNameTv.setText(userName);
        holder.mEmailTv.setText(userEmail);
        final SharedPreferences sharedPreferences=context.getSharedPreferences(MyPREFERENCES,0);







        try {
            Picasso.get().load(userImage)
                    .placeholder(R.drawable.ic_action_name_face).into(holder.mAvataIv);


        }catch (Exception e){

        }



        holder.add_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.add_patient.setImageResource(R.drawable.ic_check_black_24dp);

                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("Id",uid);
                editor.commit();
                FirebaseDatabase.getInstance().getReference("Therapist").child(FirebaseAuth.getInstance().getCurrentUser()
                        .getUid()).child("AddedPatient").child(uid).setValue(modelUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(context,"Success",Toast.LENGTH_LONG).show();


                        }else {
                            Toast.makeText(context,"Fail",Toast.LENGTH_LONG).show();

                        }
                    }
                });







            }
        });





    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        ImageView mAvataIv,add_patient;
        TextView mNameTv, mEmailTv;



        public MyHolder(@NonNull View itemView) {
            super(itemView);


            mAvataIv=itemView.findViewById(R.id.avatarIv);
            mNameTv=itemView.findViewById(R.id.nameTv);
            mEmailTv=itemView.findViewById(R.id.emailTv);
            add_patient=itemView.findViewById(R.id.add_patient);



        }
    }




}

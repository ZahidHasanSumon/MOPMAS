package com.example.mopmas.TherapistActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mopmas.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterPatient extends RecyclerView.Adapter<AdapterPatient.MyHolder> {


    Context context;
    List<ModelUser> userList;



    public AdapterPatient(Context context, List<ModelUser> userList) {
        this.context = context;
        this.userList = userList;
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.addedpatient,parent,false);




        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        String userImage=userList.get(position).getImageUrl();
        String userName=userList.get(position).getUsername();
        final String userEmail=userList.get(position).getEmail();
        final String uid=userList.get(position).getUid();

        holder.mNameTv.setText(userName);
        holder.mEmailTv.setText(userEmail);


        try {
            Picasso.get().load(userImage)
                    .placeholder(R.drawable.ic_action_name_face).into(holder.mAvataIv);


        }catch (Exception e){

        }

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,ShowPatientProgress.class);
                intent.putExtra("Pid",uid);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView mAvataIv;
        TextView mNameTv, mEmailTv;
        CardView card;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            mAvataIv=itemView.findViewById(R.id.avatarIv);
            mNameTv=itemView.findViewById(R.id.nameTv);
            mEmailTv=itemView.findViewById(R.id.emailTv);
            card=itemView.findViewById(R.id.card);

        }
    }
}

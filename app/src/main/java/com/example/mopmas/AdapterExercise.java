package com.example.mopmas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mopmas.TherapistActivity.AdapterUsers;

import java.util.List;

public class AdapterExercise extends RecyclerView.Adapter<AdapterExercise.MyHolder> {

    Context context;
    List<NewUser> userList;

    public AdapterExercise(Context context, List<NewUser> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.exercise_row,parent,false);


        return new MyHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        String tarikh=userList.get(position).getDate();
        String nam=userList.get(position).getExerciseName();
        String somoy=userList.get(position).getDuration();
        String bar=userList.get(position).getRepetition();

        holder.date.setText(tarikh);
        holder.exname.setText(nam);
        holder.duration.setText(somoy);
        holder.rep.setText(bar);

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        TextView date,exname,duration,rep;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.date);
            exname=itemView.findViewById(R.id.exname);
            duration=itemView.findViewById(R.id.duration);
            rep=itemView.findViewById(R.id.rep);

        }
    }
}

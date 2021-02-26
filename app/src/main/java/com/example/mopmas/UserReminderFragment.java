package com.example.mopmas;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mopmas.ReminderActivity.Reminder1;
import com.example.mopmas.ReminderActivity.Reminder2;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserReminderFragment extends Fragment {
    private Button bt7,bt8,bt9,bt10,bt11;



    public UserReminderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_reminder, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bt7=(Button)view.findViewById(R.id.button7);
        bt8=(Button)view.findViewById(R.id.button8);
        bt9=(Button)view.findViewById(R.id.button9);
        bt10=(Button)view.findViewById(R.id.button10);
        bt11=(Button)view.findViewById(R.id.button11);

        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Reminder1.class));

            }
        });
        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Reminder2.class));

            }
        });
    }
}

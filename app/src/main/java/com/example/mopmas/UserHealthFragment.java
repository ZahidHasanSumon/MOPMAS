package com.example.mopmas;


import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mopmas.ExerciseActivity.AnkleDorsiflexion;
import com.example.mopmas.ExerciseActivity.DatabaseHelper;
import com.example.mopmas.ExerciseActivity.KneeExtensions;
import com.example.mopmas.ExerciseActivity.OpenArmExercise;
import com.example.mopmas.ExerciseActivity.SeatedMarching;
import com.example.mopmas.ExerciseActivity.TabletopCircleExercise;
import com.example.mopmas.ExerciseActivity.TrunkRotation;
import com.example.mopmas.ExerciseActivity.UnweightedBicepCurls;
import com.example.mopmas.ExerciseActivity.WeightBearingLean;

import static android.content.Context.MODE_WORLD_READABLE;
import static com.example.mopmas.UserProfileFragment.MyPREF;
import static com.example.mopmas.UserProfileFragment.exercisetype;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserHealthFragment extends Fragment {
    Window window;
    private TextView tx,tx1;
    private double MagnitudePrevious = 0;
    private Integer stepCount = 0;
    private Integer integer = 0;
    private Button bt;
    private CardView cv1,cv2,cv3,cv4,cv5,cv6,cv7,cv8,cv9,cv10,cv11,cv12,cv13,cv14,cv15,cv16,cv17,cv18,cv19,cv20,cv21;
    private Spinner sp;
    private RelativeLayout rl;
    DatabaseHelper db;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_health, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(Build.VERSION.SDK_INT>=21){
            window=getActivity().getWindow();
            window.setStatusBarColor(getActivity().getResources().getColor(R.color.gradEnd));


        }
        cv1=(CardView)view.findViewById(R.id.cardv3);
        cv2= (CardView)view.findViewById(R.id.cardv1);
        cv3=(CardView)view.findViewById(R.id.cardv2);
        cv4=(CardView)view.findViewById(R.id.cardv4);
        cv5=(CardView)view.findViewById(R.id.cardv5);
        cv6=(CardView)view.findViewById(R.id.cardv6);
        cv7=(CardView)view.findViewById(R.id.cardv7);
        cv8=(CardView)view.findViewById(R.id.cardv8);
        cv9=(CardView)view.findViewById(R.id.cardv9);
        cv10=(CardView)view.findViewById(R.id.cardv10);
        cv11=(CardView)view.findViewById(R.id.cardv11);
        cv12=(CardView)view.findViewById(R.id.cardv12);
        cv13=(CardView)view.findViewById(R.id.cardv13);
        cv14=(CardView)view.findViewById(R.id.cardv14);
        cv15=(CardView)view.findViewById(R.id.cardv15);
        cv16=(CardView)view.findViewById(R.id.cardv16);
        cv17=(CardView)view.findViewById(R.id.cardv17);
        cv18=(CardView)view.findViewById(R.id.cardv18);
        cv19=(CardView)view.findViewById(R.id.cardv19);
        cv20=(CardView)view.findViewById(R.id.cardv20);
        cv21=(CardView)view.findViewById(R.id.cardv21);
       // SharedPreferences sharedPreferences=getSharedPreferences(MyPREF, 0);
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences(MyPREF,0);
        final String text=sharedPreferences.getString(exercisetype,"All Exercise");
        Toast.makeText(getActivity(),"Goal: "+text,Toast.LENGTH_LONG).show();






               if (text.equals("Legs")){

                   cv1.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           startActivity(new Intent(getActivity(), KneeExtensions.class));

                       }
                   });

                   cv2.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           startActivity(new Intent(getActivity(), SeatedMarching.class));
                       }
                   });

                   cv3.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           startActivity(new Intent(getActivity(), AnkleDorsiflexion.class));
                       }
                   });
                   cv1.setVisibility(View.VISIBLE);
                   cv2.setVisibility(View.VISIBLE);
                   cv3.setVisibility(View.VISIBLE);
                   cv4.setVisibility(View.GONE);
                   cv5.setVisibility(View.GONE);
                   cv6.setVisibility(View.GONE);
                   cv7.setVisibility(View.GONE);
                   cv8.setVisibility(View.GONE);
                   cv9.setVisibility(View.GONE);
                   cv10.setVisibility(View.GONE);
                   cv11.setVisibility(View.GONE);
                   cv12.setVisibility(View.GONE);
                   cv13.setVisibility(View.GONE);
                   cv14.setVisibility(View.GONE);
                   cv15.setVisibility(View.GONE);
                   cv16.setVisibility(View.GONE);
                   cv17.setVisibility(View.GONE);
                   cv18.setVisibility(View.GONE);
                   cv19.setVisibility(View.GONE);
                   cv20.setVisibility(View.GONE);
                   cv21.setVisibility(View.GONE);

               }else if(text.equals("Arms")){


               cv1.setVisibility(View.GONE);

               cv2.setVisibility(View.GONE);
               cv3.setVisibility(View.GONE);
               cv4.setVisibility(View.VISIBLE);
               cv5.setVisibility(View.VISIBLE);
               cv6.setVisibility(View.VISIBLE);
               cv7.setVisibility(View.VISIBLE);
               cv8.setVisibility(View.GONE);
               cv9.setVisibility(View.GONE);
               cv10.setVisibility(View.GONE);
               cv11.setVisibility(View.GONE);
               cv12.setVisibility(View.GONE);
               cv13.setVisibility(View.GONE);
               cv14.setVisibility(View.GONE);
               cv15.setVisibility(View.GONE);
               cv16.setVisibility(View.GONE);
               cv17.setVisibility(View.GONE);
               cv18.setVisibility(View.GONE);
               cv19.setVisibility(View.GONE);
               cv20.setVisibility(View.GONE);
               cv21.setVisibility(View.GONE);

               cv4.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       startActivity(new Intent(getActivity(), TabletopCircleExercise.class));

                   }
               });

                   cv5.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           startActivity(new Intent(getActivity(), WeightBearingLean.class));
                       }
                   });

               cv6.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       startActivity(new Intent(getActivity(), UnweightedBicepCurls.class));
                   }
               });
                   cv7.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           startActivity(new Intent(getActivity(), OpenArmExercise.class));
                       }
                   });


               }else if (text.equals("Balance and Core")){


                   cv1.setVisibility(View.GONE);
                   cv2.setVisibility(View.GONE);
                   cv3.setVisibility(View.GONE);
                   cv4.setVisibility(View.GONE);
                   cv5.setVisibility(View.GONE);
                   cv6.setVisibility(View.GONE);
                   cv7.setVisibility(View.GONE);
                   cv8.setVisibility(View.VISIBLE);
                   cv9.setVisibility(View.VISIBLE);
                   cv10.setVisibility(View.VISIBLE);
                   cv11.setVisibility(View.GONE);
                   cv12.setVisibility(View.GONE);
                   cv13.setVisibility(View.GONE);
                   cv14.setVisibility(View.GONE);
                   cv15.setVisibility(View.GONE);
                   cv16.setVisibility(View.GONE);
                   cv17.setVisibility(View.GONE);
                   cv18.setVisibility(View.GONE);
                   cv19.setVisibility(View.GONE);
                   cv20.setVisibility(View.GONE);
                   cv21.setVisibility(View.GONE);

                   cv8.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           startActivity(new Intent(getActivity(),TrunkRotation.class));

                       }
                   });


               }else if (text.equals("Shoulders and Upper Extremity")){


                   cv1.setVisibility(View.GONE);
                   cv2.setVisibility(View.GONE);
                   cv3.setVisibility(View.GONE);
                   cv4.setVisibility(View.GONE);
                   cv5.setVisibility(View.GONE);
                   cv6.setVisibility(View.GONE);
                   cv7.setVisibility(View.GONE);
                   cv8.setVisibility(View.GONE);
                   cv9.setVisibility(View.GONE);
                   cv10.setVisibility(View.GONE);
                   cv11.setVisibility(View.VISIBLE);
                   cv12.setVisibility(View.VISIBLE);
                   cv13.setVisibility(View.VISIBLE);
                   cv14.setVisibility(View.GONE);
                   cv15.setVisibility(View.GONE);
                   cv16.setVisibility(View.GONE);
                   cv17.setVisibility(View.GONE);
                   cv18.setVisibility(View.GONE);
                   cv19.setVisibility(View.GONE);
                   cv20.setVisibility(View.GONE);
                   cv21.setVisibility(View.GONE);

                   cv11.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           startActivity(new Intent(getActivity(),TrunkRotation.class));


                       }
                   });


               }else if (text.equals("Wrists, Hands, and Fingers")){


                   cv1.setVisibility(View.GONE);
                   cv2.setVisibility(View.GONE);
                   cv3.setVisibility(View.GONE);
                   cv4.setVisibility(View.GONE);
                   cv5.setVisibility(View.GONE);
                   cv6.setVisibility(View.GONE);
                   cv7.setVisibility(View.GONE);
                   cv8.setVisibility(View.GONE);
                   cv9.setVisibility(View.GONE);
                   cv10.setVisibility(View.GONE);
                   cv11.setVisibility(View.GONE);
                   cv12.setVisibility(View.GONE);
                   cv13.setVisibility(View.GONE);
                   cv14.setVisibility(View.VISIBLE);
                   cv15.setVisibility(View.VISIBLE);
                   cv16.setVisibility(View.VISIBLE);
                   cv17.setVisibility(View.VISIBLE);
                   cv18.setVisibility(View.VISIBLE);
                   cv19.setVisibility(View.GONE);
                   cv20.setVisibility(View.GONE);
                   cv21.setVisibility(View.GONE);
                   cv14.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           startActivity(new Intent(getActivity(), KneeExtensions.class));
                       }
                   });


               }else if (text.equals("Exercises for Paralysis")){


                   cv1.setVisibility(View.GONE);
                   cv2.setVisibility(View.GONE);
                   cv3.setVisibility(View.GONE);
                   cv4.setVisibility(View.GONE);
                   cv5.setVisibility(View.GONE);
                   cv6.setVisibility(View.GONE);
                   cv7.setVisibility(View.GONE);
                   cv8.setVisibility(View.GONE);
                   cv9.setVisibility(View.GONE);
                   cv10.setVisibility(View.GONE);
                   cv11.setVisibility(View.GONE);
                   cv12.setVisibility(View.GONE);
                   cv13.setVisibility(View.GONE);
                   cv14.setVisibility(View.GONE);
                   cv15.setVisibility(View.GONE);
                   cv16.setVisibility(View.GONE);
                   cv17.setVisibility(View.GONE);
                   cv18.setVisibility(View.GONE);
                   cv19.setVisibility(View.VISIBLE);
                   cv20.setVisibility(View.VISIBLE);
                   cv21.setVisibility(View.VISIBLE);

                   cv19.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           startActivity(new Intent(getActivity(), KneeExtensions.class));
                       }
                   });


               }else if (text.equals("All Exercise")){

                   cv1.setVisibility(View.VISIBLE);
                   cv2.setVisibility(View.VISIBLE);
                   cv3.setVisibility(View.VISIBLE);
                   cv4.setVisibility(View.VISIBLE);
                   cv5.setVisibility(View.GONE);
                   cv6.setVisibility(View.VISIBLE);
                   cv7.setVisibility(View.VISIBLE);
                   cv8.setVisibility(View.VISIBLE);
                   cv9.setVisibility(View.VISIBLE);
                   cv10.setVisibility(View.VISIBLE);
                   cv11.setVisibility(View.VISIBLE);
                   cv12.setVisibility(View.VISIBLE);
                   cv13.setVisibility(View.VISIBLE);
                   cv14.setVisibility(View.VISIBLE);
                   cv15.setVisibility(View.VISIBLE);
                   cv16.setVisibility(View.VISIBLE);
                   cv17.setVisibility(View.VISIBLE);
                   cv18.setVisibility(View.VISIBLE);
                   cv19.setVisibility(View.VISIBLE);
                   cv20.setVisibility(View.VISIBLE);
                   cv21.setVisibility(View.VISIBLE);

                   cv1.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           startActivity(new Intent(getActivity(), KneeExtensions.class));

                       }
                   });

                   cv2.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           startActivity(new Intent(getActivity(), SeatedMarching.class));
                       }
                   });

                   cv3.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           startActivity(new Intent(getActivity(), AnkleDorsiflexion.class));
                       }
                   });

                   cv4.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           startActivity(new Intent(getActivity(), TabletopCircleExercise.class));

                       }
                   });
                   cv6.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           startActivity(new Intent(getActivity(), UnweightedBicepCurls.class));
                       }
                   });
                   cv7.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           startActivity(new Intent(getActivity(), OpenArmExercise.class));
                       }
                   });

                   cv5.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           startActivity(new Intent(getActivity(), WeightBearingLean.class));
                       }
                   });

                   cv8.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           startActivity(new Intent(getActivity(),TrunkRotation.class));

                       }
                   });





               }

               }







}

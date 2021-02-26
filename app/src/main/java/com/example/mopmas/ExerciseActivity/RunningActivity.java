package com.example.mopmas.ExerciseActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mopmas.R;

public class RunningActivity extends AppCompatActivity {
    private TextView tx,tx1;
    private double MagnitudePrevious = 0;
    private Integer stepCount = 0;
    private Integer integer = 0;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);

        // sp=view.findViewById(R.id.sp1);

       /* tx1=view.findViewById(R.id.tx1);
        tx1.setText(item);*/



         tx=findViewById(R.id.textView);
         bt=findViewById(R.id.button6);
        SensorManager sensorManager = (SensorManager)RunningActivity.this.getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        SensorEventListener stepDetector = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (sensorEvent!= null){
                    float x_acceleration = sensorEvent.values[0];
                    float y_acceleration = sensorEvent.values[1];
                    float z_acceleration = sensorEvent.values[2];

                    double Magnitude = Math.sqrt(x_acceleration*x_acceleration + y_acceleration*y_acceleration + z_acceleration*z_acceleration);
                    double MagnitudeDelta = (Magnitude - MagnitudePrevious);
                    MagnitudePrevious = Magnitude;

                    if (MagnitudeDelta > 6){
                        stepCount++;
                    }
                    tx.setText("Total Step: "+stepCount.toString());
                    bt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                           tx.setText("Total Step: "+String.valueOf(stepCount=0));

                        }
                    });
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };

        sensorManager.registerListener(stepDetector, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onPause() {
        super.onPause();

        SharedPreferences sharedPreferences = RunningActivity.this.getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putInt("stepCount", stepCount);
        editor.apply();
    }

    public void onStop() {
        super.onStop();

        SharedPreferences sharedPreferences = RunningActivity.this.getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putInt(  "stepCount", stepCount);
        editor.apply();
    }

    public void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = RunningActivity.this.getPreferences(MODE_PRIVATE);
        stepCount = sharedPreferences.getInt("stepCount", 0);

    }

    }


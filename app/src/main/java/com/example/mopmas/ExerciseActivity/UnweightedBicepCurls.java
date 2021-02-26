package com.example.mopmas.ExerciseActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mopmas.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.Locale;

public class UnweightedBicepCurls extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 300000;
    private Button bt,bt1,bt2;
    private TextView tx,tx1,tx2;
    private CardView cardView;
    private ImageView img;

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    String timeLeftFormatted1;
    int Extime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unweighted_bicep_curls);
        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        bt=findViewById(R.id.button6);
        bt1=findViewById(R.id.button7);
        bt2=findViewById(R.id.button8);
        tx=findViewById(R.id.textView23);
        tx1=findViewById(R.id.textView30);
        tx2=findViewById(R.id.textView31);
        cardView=findViewById(R.id.cardv4);
        img=findViewById(R.id.imageView13);


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cardView.setVisibility(View.VISIBLE);
                img.setVisibility(View.GONE);
                bt.setVisibility(View.GONE);
                startTimer();

            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseTimer();
                String time=tx1.getText().toString().trim();

                BottomSheetDialog bottomSheetDialog=new BottomSheetDialog();
                bottomSheetDialog.setTime(timeLeftFormatted1);

                bottomSheetDialog.show(getSupportFragmentManager(),"BottomSheetDialog");


            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }

            }
        });




    }
    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                tx1.setText("0.00s");

            }
        }.start();

        mTimerRunning = true;
        bt2.setBackgroundResource(R.drawable.capsule_maroon);
        bt2.setText("Pause");

    }
    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        bt2.setBackgroundResource(R.drawable.capsule_shape);
        bt2.setText("Re-Start");
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        //timeLeftFormatted1 = String.format(Locale.getDefault(), "%02d%02d", minutes, seconds);
        Extime=300-((minutes*60)+seconds);

        timeLeftFormatted1=String.valueOf(Extime);

        tx1.setText(timeLeftFormatted+"s");





    }
}

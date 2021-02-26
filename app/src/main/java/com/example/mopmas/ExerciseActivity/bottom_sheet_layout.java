package com.example.mopmas.ExerciseActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mopmas.R;

public class bottom_sheet_layout extends AppCompatActivity {
    private Button bt1,bt2;
    private TextView tx;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_layout);
        tx=findViewById(R.id.textView44);
        editText=findViewById(R.id.editText200);
        tx=findViewById(R.id.textView100);


    }
}

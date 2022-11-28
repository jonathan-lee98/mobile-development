package com.assignment.temperatureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    TextView deg,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        deg=findViewById(R.id.detailsDeg);
        date=findViewById(R.id.detailsDate);


        String d=getIntent().getStringExtra("deg");
        String t=getIntent().getStringExtra("date");


        deg.setText(d);
        date.setText(t);
    }
}
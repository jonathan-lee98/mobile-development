package com.assignment.temperatureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ImageView image;

    Button historyBtn;
    SqlDb sqlDb;

    SensorManager sm = null;
    TextView sensorsData = null;
    List list;

    SensorEventListener sel = new SensorEventListener(){
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            sensorsData.setText(""+values[0]+" °C");
            Date currentTime = Calendar.getInstance().getTime();
            sqlDb.insertTemp(String.valueOf(values[0]),String.valueOf(currentTime));

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorsData=findViewById(R.id.textTemp);
        historyBtn=findViewById(R.id.history);
        image=findViewById(R.id.image);

        sqlDb=new SqlDb(this);


        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,HistoryActivity.class));
            }
        });


        image.setImageResource(R.drawable.sun);

        //sensors code here
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);

        list = sm.getSensorList(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if(list.size()>0){
            sm.registerListener(sel, (Sensor) list.get(0), SensorManager.SENSOR_DELAY_NORMAL);
        }else{
            Toast.makeText(getBaseContext(), "No Temperature Sensor Available", Toast.LENGTH_LONG).show();
        }

    }
}
package com.example.supun.sensorreading;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.hardware.SensorEvent;
import android.hardware.Sensor;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sm=(SensorManager) getSystemService(SENSOR_SERVICE);
        setContentView(R.layout.activity_main);
        TextView tv=findViewById(R.id.textView2);
        tv.setText("Sensor Offline");
        final Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                toggleSensorStatus(v);
                // Code here executes on main thread after user presses button
            }
        });

    }

    public void toggleSensorStatus(View v){
        Button button = (Button)findViewById(R.id.button2);
        System.out.println(button.getText());
        if(button.getText().equals("Start")){
            button.setText("Stop");
            Sensor s=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sm.registerListener(this,s, SensorManager.SENSOR_DELAY_NORMAL);

        }
        else if(button.getText().equals("Stop")){
            button.setText("Start");
            sm.unregisterListener(this);
            TextView tv=findViewById(R.id.textView2);
            tv.setText("Sensor Offline");
        }

    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;
        TextView tv=findViewById(R.id.textView2);
        String value="Sensor Value : " + values[0];
        tv.setText(value);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

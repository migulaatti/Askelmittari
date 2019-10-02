package com.example.askelmittari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView steps;
    SensorManager sensorManager;

    boolean running = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        steps = findViewById(R.id.steps);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }
    /*
    Jos sensorit löytyvät, aloittaa askelten tallentamisen
     */
    @Override
    protected void onResume() {
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Sensor not found!", Toast.LENGTH_SHORT).show();
        }
    }
    /*
    "sammuttaa" sensorin jos sovellus suljetaan
     */
    @Override
    protected void onPause() {
        super.onPause();
        running = true;
    }

    /*
    päivittää mittarin lukeman, kun sensori havaitsee askeleen/askelia
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        steps.setText(String.valueOf(event.values[0]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

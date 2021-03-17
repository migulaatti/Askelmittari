package com.example.askelmittari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;
/*
@author Mikael Marttila
Askelmittari, mittaa askeleita niin kauan kunnes kutsutaan resetSteps-metodia. / Stepcounter, counts steps until resetSteps-method is called
 */

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private final String PREFS = "PREFS";
    private int stepsSensor = 0;
    private int stepsReset;
    double cm;

    TextView steps;
    TextView km;
    SensorManager sensorManager;
    AnimationDrawable walkingAnim;

    private Button BtnMove;
    private Button BtnMove2;

    boolean running = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_main);

        steps = findViewById(R.id.tvSteps);
        BtnMove = findViewById(R.id.buttonBmi);
        BtnMove2 = findViewById(R.id.buttonCalories);
        km = findViewById(R.id.tvKm);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        ImageView walking = (ImageView) findViewById(R.id.imageView2);
        walking.setBackgroundResource(R.drawable.walkingman);
        walkingAnim = (AnimationDrawable) walking.getBackground();

        SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
        stepsReset = prefs.getInt("stepsOnReset", 0);

        BtnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToActivityBMI();
            }
        });
        BtnMove2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToActivityCalorie();
            }
        });
    }

    /*
     * siirrytään BMI-aktiviteettiin/kaloriaktiviteettiin   / transition to other activity
     */
    public void moveToActivityBMI(){
        Intent intent = new Intent (MainActivity.this, BMIActivity.class);
        startActivity(intent);
    }

    public void moveToActivityCalorie(){
        Intent intent = new Intent (MainActivity.this, CalorieCounter.class);
        startActivity(intent);
    }

    /*
     * Nollaa askelmittarin/resets the counter
     *
     */


    public void resetStep(View v) {
        stepsReset = stepsSensor;

        SharedPreferences.Editor editor = getSharedPreferences(PREFS, MODE_PRIVATE).edit();
        editor.putInt("stepsOnReset", stepsReset);
        editor.commit();
        steps.setText(String.valueOf(0));
        km.setText("Kilometrit: " + (0.0));
    }
    /*
    Jos sensorit löytyvät, aloittaa askelten tallentamisen/if sensors are found, starts recording steps
     */
    @Override
    protected void onStart() {
        super.onStart();
        walkingAnim.start();
    }
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
    
    @Override
    protected void onPause() {
        super.onPause();
        running = true;
    }

    /*
    päivittää mittarin lukeman, kun sensori havaitsee askeleen. / refreshes the counter value, when steps are detected by the sensor
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        if(running){
            stepsSensor = Integer.valueOf((int)event.values[0]);
            int stepsSinceReset = stepsSensor - stepsReset;
            cm = 1000;
            double kms = stepsSinceReset/cm;
            steps.setText(String.valueOf(stepsSinceReset));
            km.setText("Kilometrit: " + kms);
        }else{
            event.values[0] = 0;
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

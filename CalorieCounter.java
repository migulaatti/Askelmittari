package com.example.askelmittari;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class CalorieCounter extends AppCompatActivity {

    private EditText Kg;
    private EditText Time;
    private TextView Result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie);
        Time = (EditText) findViewById(R.id.Time);
        Kg = (EditText) findViewById(R.id.Kg);
        Result = (TextView) findViewById(R.id.Result);


    }

    public void Calories(View view) {
        Log.i("laske", "buttonPressed()");
        String TimeStr = Time.getText().toString();
        String KgStr = Kg.getText().toString();
        double TimeValue = Double.parseDouble(TimeStr);
        double KgValue = Double.parseDouble(KgStr);
        double KgTime = KgValue * 2.9;
        double calories = KgTime / TimeValue;
        String.format("%.2f", calories);
        TextView tv = findViewById(R.id.Result);
        tv.setText(Double.toString(calories));
    }
}
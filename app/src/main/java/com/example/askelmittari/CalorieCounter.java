package com.example.askelmittari;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
/*
@author Janne Muukkonen
 */

public class CalorieCounter extends AppCompatActivity {

    private EditText Kg;
    private EditText Time;
    private TextView Resulth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_counter);
        Time = (EditText) findViewById(R.id.Time);
        Kg = (EditText) findViewById(R.id.Kg);
        Resulth = (TextView) findViewById(R.id.Resulth);


    }
    /*laskee poltetut kalorit paino*MET-arvo(2.9 jos kävelee)*tunnit= kalorit poltettu

     */
    public void Calories(View view) {
        Log.i("laske", "buttonPressed()");
        String TimeStr = Time.getText().toString();
        String KgStr = Kg.getText().toString();
        if (TimeStr.isEmpty() || KgStr.isEmpty()) {
            Toast.makeText(this, "Paino tai liikuttu aika tyhjä!", Toast.LENGTH_SHORT).show();
            return; //tarkistaa onko EditText tyhjä
        } else {
            double TimeValue = Double.parseDouble(TimeStr);
            double KgValue = Double.parseDouble(KgStr);
            double KgTime = KgValue * 2.9 * TimeValue;
            TextView tv = findViewById(R.id.Resulth);
            tv.setText(String.format("%.2f", KgTime));
        }

    }
}
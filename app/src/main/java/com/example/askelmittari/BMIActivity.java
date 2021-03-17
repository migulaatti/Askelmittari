package com.example.askelmittari;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
/*
@author Teemu Tirkkonen
 */

public class BMIActivity extends AppCompatActivity {

    private EditText Pituus;
    private EditText Paino;
    private TextView Tulos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        Pituus = (EditText) findViewById(R.id.Pituus);
        Paino = (EditText) findViewById(R.id.Paino);
        Tulos = (TextView) findViewById(R.id.Tulos);


    }
    /*laskee BMI:n BMI-kaavan mukaan

     */
    public void BMI(View view) {
        Log.i("laske", "buttonPressed()");
        String PituusStr = Pituus.getText().toString();
        String PainoStr = Paino.getText().toString();
        if (PituusStr.isEmpty() || PainoStr.isEmpty()) {
            Toast.makeText(this, "Paino tai pituus on tyhjä!", Toast.LENGTH_SHORT).show();
            return;  //tarkistaa onko EditText tyhjä

        } else {
            double PituusArvo = Double.parseDouble(PituusStr);
            double PainoArvo = Double.parseDouble(PainoStr);
            double bmiPituus = PituusArvo / 100 * PituusArvo / 100;
            double bmi = PainoArvo / bmiPituus;
            TextView tv = findViewById(R.id.Tulos);
            tv.setText(String.format("%.2f", bmi));
        }
    }
}

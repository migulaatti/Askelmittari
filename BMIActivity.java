package com.example.askelmittari;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


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

    public void BMI(View view) {
        Log.i("laske", "buttonPressed()");
        String PituusStr = Pituus.getText().toString();
        String PainoStr = Paino.getText().toString();
        float PituusArvo = Float.parseFloat(PituusStr);
        float PainoArvo = Float.parseFloat(PainoStr);
        float bmiPituus = PituusArvo / 100 * PituusArvo / 100;
        float bmi =  PainoArvo / bmiPituus;
        String.format("%.2f", bmi);
        TextView tv = findViewById(R.id.Tulos);
        tv.setText(Float.toString(bmi));
    }
}


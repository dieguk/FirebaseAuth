package com.example.firebaseauth;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EscalasanElian extends AppCompatActivity {
    TextView textView2;

    String rutdelpaciente, llave = "rutdelpaciente";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanelian);
        Bundle bundle = getIntent().getExtras();
        textView2=findViewById(R.id.textView2);
        rutdelpaciente = bundle.getString(llave);
        textView2.setText(rutdelpaciente);


    }
}
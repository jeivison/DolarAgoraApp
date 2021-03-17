package com.example.dolarhoje.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.dolarhoje.R;

public class TelaConversor extends AppCompatActivity {


    private EditText editTextDolar, editTextReais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_conversor);

        editTextDolar = findViewById(R.id.editTextDolar);
        editTextReais = findViewById(R.id.editTextReais);



    }

}
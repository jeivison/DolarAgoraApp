package com.example.dolarhoje.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dolarhoje.R;

public class TelaConversor extends AppCompatActivity {


    private EditText editTextDolar;
    private EditText editTextReais;
    private TextView testeDolar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_conversor);

        editTextDolar = findViewById(R.id.editTextDolar);
        editTextReais = findViewById(R.id.editTextReais);
        testeDolar = findViewById(R.id.textCode);


        DolarValor objk = new DolarValor();
        Double f = objk.getValorMoeda();
        testandoDolar(f);

    }


    public void testandoDolar(Double texito){
        TextView test = (TextView)findViewById(R.id.textCode);
        test.setText(String.valueOf(texito));
    }

    //String.valueOf(texito)


}


package com.example.dolarhoje.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dolarhoje.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

public class TelaConversor extends AppCompatActivity {


    private EditText editTextDolar, num2, num1;
    private EditText editTextReais;
    private TextView resultadoD;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_conversor);

        editTextDolar = findViewById(R.id.editTextDolar);
        editTextReais = findViewById(R.id.editTextReais);


        resultadoD = findViewById(R.id.textViewResultado);

        MyTask task = new TelaConversor.MyTask();
        String urlApi = "https://economia.awesomeapi.com.br/all/USD-BRL";
        task.execute(urlApi);

        editTextDolar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {



                String number1 = editTextDolar.getText().toString();
                Double valorDolla = Double.parseDouble(number1);
                String number2 = editTextReais.getText().toString();
                Double valorReais = Double.parseDouble(number2);

                Double resul = valorDolla * valorReais;

                editTextReais.setText(String.valueOf(resul));

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    public void carregarValor(String dolarV){
        EditText valorD = (EditText)findViewById(R.id.editTextReais);
       valorD.setText(dolarV);
            /*
            int number1 = Integer.valueOf(num1.toString());
            int number2 = Integer.valueOf(num2.getText().toString());
            int res = number1 * number2;*/

        /*
        String dollarD = String.valueOf(1);
        EditText numD = (EditText)findViewById(R.id.editTextDolar);
        numD.setText(dollarD);*/


    }

    class MyTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            String stringUrl = strings[0];
            InputStream inputStream = null;
            InputStreamReader inputStreamReader = null;
            StringBuffer buffer = null;

            try {
                URL url = new URL(stringUrl);
                HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

                //Recuperando os dados em Bytes
                inputStream = conexao.getInputStream();

                inputStreamReader = new InputStreamReader(inputStream);

                BufferedReader reader = new BufferedReader(inputStreamReader);
                buffer = new StringBuffer();
                String linha = "";

                while ((linha = reader.readLine()) != null){
                    buffer.append(linha);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return buffer.toString();
        }

        @Override
        protected void onPostExecute(String resultado) {
            super.onPostExecute(resultado);

            /*
            String number1 = editTextDolar.getText().toString();
            Double valorDolla = Double.parseDouble(number1);
            String number2 = editTextReais.getText().toString();
            Double valorReais = Double.parseDouble(number2);

            Double resul = valorDolla * valorReais;

            resultadoD.setText(String.valueOf(resul));*/


            String objetoValor = null;
            //String simbolo = null;
            String date = null;
            Double valorMoeda = null;

            DecimalFormat formatD = new DecimalFormat("#.##");

            try {
                JSONObject jsonObject = new JSONObject(resultado);
                objetoValor = jsonObject.getString("USD");

                JSONObject jsonObjectReal = new JSONObject(objetoValor);
                valorMoeda = jsonObjectReal.getDouble("low");
                //simbolo = jsonObjectReal.getString("codein");
                date = jsonObjectReal.getString("create_date");
            } catch (JSONException e) {
                e.printStackTrace();
            }


            //editUsd.setText(mCode);
            //editUsd.setText(simbolo+" "+valorMoeda);
            //textDate.setText("Última atualização: " + date);
            carregarValor(formatD.format(valorMoeda));
            //carregarData("Última atualização: " + date);

        }

    }

}


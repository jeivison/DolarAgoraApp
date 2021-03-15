package com.example.dolarhoje.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
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

public class TelaDolar extends AppCompatActivity {

    private TextView editUsd, textDate;
    private Button buttonConverter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_dolar);

        editUsd = findViewById(R.id.editUsd);
        textDate = findViewById(R.id.textDate);


        MyTask task = new MyTask();
        String urlApi = "https://economia.awesomeapi.com.br/all/USD-BRL";
        task.execute(urlApi);

/*
        buttonConverter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTask task = new MyTask();
                String urlApi = "https://economia.awesomeapi.com.br/all/USD-BRL";
                task.execute(urlApi);
            }
        });*/

    }

    private void carregarTexto(String texto){
        TextView text = (TextView)findViewById(R.id.editUsd);
        text.setText(texto);

    }

    private void carregarData(String date){
        TextView tDate = (TextView)findViewById(R.id.textDate);
        tDate.setText(date);
    }


    class MyTask extends AsyncTask<String, Void, String>{

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

            String objetoValor = null;
            String valorMoeda = null;
            String simbolo = null;
            String date = null;

            try {
                JSONObject jsonObject = new JSONObject(resultado);
                objetoValor = jsonObject.getString("USD");

                JSONObject jsonObjectReal = new JSONObject(objetoValor);
                valorMoeda = jsonObjectReal.getString("low");
                simbolo = jsonObjectReal.getString("codein");
                date = jsonObjectReal.getString("create_date");
            } catch (JSONException e) {
                e.printStackTrace();
            }


            //editUsd.setText(mCode);
            //editUsd.setText(simbolo+" "+valorMoeda);
            //textDate.setText("Última atualização: " + date);
            carregarTexto(simbolo + valorMoeda);
            carregarData("Última atualização: " + date);

        }
    }
}
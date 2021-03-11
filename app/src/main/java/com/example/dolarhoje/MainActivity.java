package com.example.dolarhoje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dolarhoje.activity.TelaCadastro;
import com.example.dolarhoje.activity.TelaLogin;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;

public class MainActivity extends IntroActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);


        setButtonBackVisible(false);
        setButtonNextVisible(false);


        addSlide(new FragmentSlide.Builder()
                .background(R.color.purple_200)
                .fragment(R.layout.intro_1)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(R.color.purple_200)
                .fragment(R.layout.intro_2)
                .canGoForward(false)
                .build());

        /*
        addSlide(new SimpleSlide.Builder()
                .title("")
                .description("descricao")
                //.image(R.drawable.)
                .background(android.R.color.white)
                .build()

        );

        addSlide(new SimpleSlide.Builder()
                .title("")
                .description("descricao")
                //.image(R.drawable.)
                .background(android.R.color.white)
                .build()

        );*/
    }

    public void btCadastrar(View view){
        startActivity(new Intent(this, TelaCadastro.class));
    }

    public void btEntrar(View view){
        startActivity(new Intent(this, TelaLogin.class));
    }

}
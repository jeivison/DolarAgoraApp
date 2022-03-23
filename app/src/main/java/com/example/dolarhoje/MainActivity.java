package com.example.dolarhoje;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dolarhoje.activity.TelaCadastro;
import com.example.dolarhoje.activity.TelaLogin;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

public class MainActivity extends IntroActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

    }

    public void btCadastrar(View view){
        startActivity(new Intent(this, TelaCadastro.class));
    }

    public void btEntrar(View view){
        startActivity(new Intent(this, TelaLogin.class));
    }

}
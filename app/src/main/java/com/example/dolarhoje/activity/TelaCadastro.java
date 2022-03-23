package com.example.dolarhoje.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dolarhoje.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class TelaCadastro extends AppCompatActivity {

    private EditText mNome, mEmail, mSenha;
    private Button mButtonCadastrar;
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        mNome = findViewById(R.id.editTextNome);
        mEmail = findViewById(R.id.editTextEmail);
        mSenha = findViewById(R.id.editTextSenha);
        mButtonCadastrar = findViewById(R.id.buttonCad);

        fAuth = FirebaseAuth.getInstance();

        mButtonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = mNome.getText().toString();
                String email = mEmail.getText().toString();
                String senha = mSenha.getText().toString();


                if (nome.isEmpty()){
                    mNome.setError("Por favor, Digite seu nome!");
                    return;
                }
                if (email.isEmpty()){
                    mEmail.setError("Por favor, Digite seu email!");
                    return;
                }
                if (senha.isEmpty()){
                    mSenha.setError("Por favor, Digite seu nome!");
                    return;
                }

                Toast.makeText(TelaCadastro.this, "Registrado com sucesso!", Toast.LENGTH_SHORT).show();
            fAuth.createUserWithEmailAndPassword(email, senha).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    startActivity(new Intent(getApplicationContext(), TelaDolar.class));
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(TelaCadastro.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            }

        });


    }


}
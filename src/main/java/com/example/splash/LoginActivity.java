package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
EditText usuario,contra;
Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuario = findViewById(R.id.usuario);
        contra= findViewById(R.id.contra);
        btnlogin= findViewById(R.id.btnlogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(usuario.getText().toString().equals("brandon") && contra.getText().toString().equals("sistemas")){
                    startActivity(new Intent(LoginActivity.this, PrincipalActivity.class));
                    Toast.makeText(LoginActivity.this,"BIENVENIDO", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(LoginActivity.this,"HAY UN ERROR CUATE",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
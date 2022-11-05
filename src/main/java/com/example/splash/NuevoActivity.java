package com.example.splash;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import db.DbJugadores;

public class NuevoActivity extends AppCompatActivity {

    EditText txtPais,txtregion,txtcapitan,txtranking,txtmundiales;
    Button btnguardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        txtPais = findViewById(R.id.txtPais);
        txtregion= findViewById(R.id.txtregion);
        txtcapitan = findViewById(R.id.txtcapitan);
        txtranking = findViewById(R.id.txtranking);
        txtmundiales = findViewById(R.id.txtmundiales);
        btnguardar = findViewById(R.id.btnguardar);

        btnguardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                DbJugadores dbJugadores = new DbJugadores(NuevoActivity.this );
                long id = dbJugadores.insertaJugador(txtPais.getText().toString(),txtregion.getText().toString(),txtcapitan.getText().toString(),txtranking.getText().toString(),txtmundiales.getText().toString());

                if(id>0){
                    Toast.makeText(NuevoActivity.this,"REGISTRO GUARDADO",Toast.LENGTH_LONG).show();
                    limpiar();
                }else{
                    Toast.makeText(NuevoActivity.this,"ERROR CUATE",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    private void limpiar(){
        txtPais.setText("");
        txtregion.setText("");
        txtcapitan.setText("");
        txtranking.setText("");
        txtmundiales.setText("");
    }
}
package com.example.splash;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import db.DbJugadores;

public class EditarActivity extends AppCompatActivity {

    EditText txtPais, txtregion, txtcapitan, txtranking, txtmundiales;
    Button btnguardar;
    FloatingActionButton fabeditar,fabeliminar;
    boolean correcto = false;


    JugadoresDTO jugador;
    int Id = 0;
    @SuppressLint("RestrictedApi")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtPais = findViewById(R.id.txtPais);
        txtregion = findViewById(R.id.txtregion);
        txtcapitan = findViewById(R.id.txtcapitan);
        txtranking = findViewById(R.id.txtranking);
        txtmundiales = findViewById(R.id.txtmundiales);

        btnguardar = findViewById(R.id.btnguardar);
        fabeditar = findViewById(R.id.fabeditar);
        fabeditar.setVisibility(View.INVISIBLE);
        fabeliminar = findViewById(R.id.fabeliminar);
        fabeliminar.setVisibility(View.INVISIBLE);



        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                Id = Integer.parseInt(null);
            } else {
                Id = extras.getInt("ID");
            }
        } else {
            Id = (int) savedInstanceState.getSerializable("ID");
        }
        final DbJugadores dbJugadores = new DbJugadores(EditarActivity.this);//aqui es donde abrimos la conexion
        jugador = dbJugadores.verJugadores(Id);

        if (jugador != null) {
            txtPais.setText(jugador.getPais());
            txtregion.setText(jugador.getRegion());
            txtcapitan.setText(jugador.getCapitan());
            txtranking.setText(jugador.getRanking());
            txtmundiales.setText(jugador.getMundiales_Ganados());



        }
        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtPais.getText().toString().equals("") && !txtregion.getText().toString().equals("")) {
                //esto para indicar si es igual a vacio sino se pasa
                    correcto=dbJugadores.editarJugador( Id,txtPais.getText().toString(), txtregion.getText().toString(), txtcapitan.getText().toString(), txtranking.getText().toString(), txtmundiales.getText().toString());

                    if (correcto) {
                        Toast.makeText(EditarActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(EditarActivity.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                    }else {
                        Toast.makeText(EditarActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                    }
                }



        });
    }
    private void verRegistro(){
        Intent intent = new Intent(this, VerActivity.class);
        intent.putExtra("ID", Id);
        startActivity(intent);
}
}


package com.example.splash;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import db.DbJugadores;

public class VerActivity extends AppCompatActivity {

    EditText txtPais, txtregion, txtcapitan,txtranking,txtmundiales;
    Button btnguardar;

    FloatingActionButton fabeditar,fabeliminar;


    JugadoresDTO jugador;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtPais = findViewById(R.id.txtPais);
        txtregion = findViewById(R.id.txtregion);
        txtcapitan = findViewById(R.id.txtcapitan);
        txtranking= findViewById(R.id.txtranking);
        txtmundiales = findViewById(R.id.txtmundiales);

        btnguardar = findViewById(R.id.btnguardar);
        fabeditar = findViewById(R.id.fabeditar);
        fabeliminar = findViewById(R.id.fabeliminar);


        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }
         final DbJugadores dbJugadores = new DbJugadores(VerActivity.this);
        jugador = dbJugadores.verJugadores(id);

        if(jugador != null){
            txtPais.setText(jugador.getPais());
            txtregion.setText(jugador.getRegion());
            txtcapitan.setText(jugador.getCapitan());
            txtranking.setText(jugador.getRanking());
            txtmundiales.setText(jugador.getMundiales_Ganados());
            btnguardar.setVisibility(View.INVISIBLE);

            txtPais.setInputType(InputType.TYPE_NULL);
            txtregion.setInputType(InputType.TYPE_NULL);
            txtcapitan.setInputType(InputType.TYPE_NULL);
            txtranking.setInputType(InputType.TYPE_NULL);
            txtmundiales.setInputType(InputType.TYPE_NULL);

        }
        fabeditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerActivity.this, EditarActivity.class);
                intent.putExtra("ID", id);//le enviamos el parametro id
                startActivity(intent);
            }
        });
        fabeliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerActivity.this);
                builder.setMessage("¿Desea eliminar este contacto?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(dbJugadores.eliminarJugador(id)){
                                    lista();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();

            }
        });
    }
    private void lista(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
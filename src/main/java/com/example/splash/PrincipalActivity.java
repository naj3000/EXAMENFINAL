package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import java.util.ArrayList;

import db.DbJugadores;

public class PrincipalActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    SearchView txtbuscar;
    RecyclerView listaJugadores;

    ArrayList<JugadoresDTO> listaArrayJugadores;
    ListaJugadoresAdapter adapter;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal2);


        txtbuscar = findViewById(R.id.txtbuscar);
        listaJugadores = findViewById(R.id.listaJugadores);
        listaJugadores.setLayoutManager(new LinearLayoutManager(this));

        DbJugadores dbJugadores = new DbJugadores(PrincipalActivity.this);

        listaArrayJugadores = new ArrayList<>();

        adapter = new ListaJugadoresAdapter(dbJugadores.mostrarJugadores());
        listaJugadores.setAdapter(adapter);


        txtbuscar.setOnQueryTextListener(this);// funcionalidad que al colocar texto lo empieze a leer y se ejecute


    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuNuevo:
                nuevoRegistro();
                return true;


            default:
                return super.onOptionsItemSelected(item);

        }


    }

    private void nuevoRegistro() {
        Intent intent = new Intent(this, NuevoActivity.class); // para que nos pase a otra ventana
        startActivity(intent);

    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtrado(s);
        return false;
    }



}
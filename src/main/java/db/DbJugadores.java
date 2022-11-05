package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;


import com.example.splash.JugadoresDTO;

import java.util.ArrayList;

public class DbJugadores extends Conn {

    Context context;


    public DbJugadores(@Nullable Context context) {
        super(context);
        this.context = context;
    }
    public long insertaJugador(String Pais, String Region, String Capitan, String Ranking,String Mundiales_Ganados){
        long Id = 0;

        try {
            Conn dbHelper = new Conn(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("Pais", Pais);
            values.put("Region", Region);
            values.put("Capitan", Capitan);
            values.put("Ranking", Ranking);
            values.put("Mundiales_Ganados", Mundiales_Ganados);

            Id = db.insert(TABLE_JUGADORES, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return Id;
    }

    public ArrayList<JugadoresDTO> mostrarJugadores(){
        Conn dbHelper = new Conn(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<JugadoresDTO> listaJugadores = new ArrayList<>();
        JugadoresDTO jugador = null ;
        Cursor cursorJugadores= null;

        // vamos a hacer una consulta a la tabla jugadors para que nos traiga todos los contacto y esto nos va devolver uno tipo cursor

        cursorJugadores=db.rawQuery("SELECT * FROM "+ TABLE_JUGADORES,null); //resultados de la columna

        //hacemos validacion
        if(cursorJugadores.moveToFirst()){
            do{
                jugador = new JugadoresDTO();
                jugador.setId(cursorJugadores.getInt(0));
                jugador.setPais(cursorJugadores.getString(1));
                jugador.setRegion(cursorJugadores.getString(2));
                jugador.setCapitan(cursorJugadores.getString(3));
                jugador.setRanking(cursorJugadores.getString(4));
                jugador.setMundiales_Ganados(cursorJugadores.getString(5));
                listaJugadores.add(jugador);
            } while (cursorJugadores.moveToNext());
            }
        cursorJugadores.close();
        return listaJugadores;
        }
    public JugadoresDTO verJugadores(int Id){
        Conn dbHelper = new Conn(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        JugadoresDTO jugador = null;
        Cursor cursorJugadores;

        // vamos a hacer una consulta a la tabla contactos para que nos traiga todos los contacto y esto nos va devolver uno tipo cursor

        cursorJugadores=db.rawQuery("SELECT * FROM "+ TABLE_JUGADORES,null ); //resultados de la columna

        //hacemos validacion
        if(cursorJugadores.moveToFirst()){

                jugador = new JugadoresDTO();
                jugador.setId(cursorJugadores.getInt(0));
                jugador.setPais(cursorJugadores.getString(1));
                jugador.setRegion(cursorJugadores.getString(2));
                jugador.setCapitan(cursorJugadores.getString(3));
                jugador.setRanking(cursorJugadores.getString(4));
                jugador.setMundiales_Ganados(cursorJugadores.getString(5));


        }
        cursorJugadores.close();
        return jugador;
    }
    public boolean editarJugador(int Id,String pais, String Region, String Capitan, String Ranking, String Mundiales_Ganados){
        boolean correcto = false;

        Conn dbHelper = new Conn(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_JUGADORES + " SET pais = '" + pais + "', region = '" + Region + "', capitan = '" + Capitan +"'ranking= '"+Ranking+"'Mundiales_Ganados= '"+Mundiales_Ganados+  "' WHERE Id = '" + Id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
    public boolean eliminarJugador(int id) {

        boolean correcto = false;

        Conn dbHelper = new Conn(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_JUGADORES + " WHERE Id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
    }





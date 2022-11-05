package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conn extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE="dbmundial";
    public static final String TABLE_JUGADORES="jugadores";



    public Conn(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_JUGADORES + "(" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Pais TEXT NOT NULL," +
                "Region  TEXT NOT NULL," +
                "Capitan  TEXT NOT NULL," +
                "Ranking  TEXT NOT NULL," +
                "Mundiales_Ganados TEXT NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_JUGADORES);
        onCreate(sqLiteDatabase);
    }
}

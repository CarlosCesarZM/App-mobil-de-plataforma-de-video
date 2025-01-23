package com.example.a01081123_a21tecfilms;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminBDEventos extends SQLiteOpenHelper {

    public static final String NOMBRE_BD = "bdEventos";
    public static final int VERSION_BD = 1;

    // IDENTIFICADORES PARA LA TABLA Y SUS CAMPOS
    public static final String NOMBRE_TABLA = "usuarios";
    public static final String CAMPO1 = "clave";
    public static final String CAMPO2 = "nombre";
    public static final String CAMPO3 = "correo";

    // Constructor alt+enter
    public AdminBDEventos(@Nullable Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Creación de la tabla
        String queryCrearTabla = "CREATE TABLE " + NOMBRE_TABLA + " (" +
                CAMPO1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CAMPO2 + " TEXT, " +
                CAMPO3 + " TEXT)";
        // Ejecución del query
        sqLiteDatabase.execSQL(queryCrearTabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String queryBorrarTabla = "DROP TABLE IF EXISTS "+NOMBRE_TABLA;
        sqLiteDatabase.execSQL(queryBorrarTabla);
        onCreate(sqLiteDatabase);//sqLiteDatabase es el objeto a la base de datos
    }
}
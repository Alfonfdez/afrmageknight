package com.afr.afrmageknight.databaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.afr.afrmageknight.model.Carta;
import com.afr.afrmageknight.model.CartaAccion;
import com.afr.afrmageknight.model.CartaAccionAvanzada;
import com.afr.afrmageknight.model.CartaAccionBasica;
import com.afr.afrmageknight.model.CartaAccionAvanzadaEspecial;
import com.afr.afrmageknight.model.CartaTactica;
import com.afr.afrmageknight.model.Cristal;
import com.afr.afrmageknight.model.FichaHabilidad;
import com.afr.afrmageknight.model.Hechizo;
import com.afr.afrmageknight.model.Heroe;
import com.afr.afrmageknight.model.TipoTactica;

import java.util.ArrayList;
import java.util.Collections;

public abstract class DatabaseHelper extends SQLiteOpenHelper {

    // Nombre de la base de datos
    public static final String DATABASE_NAME = "mageknight.db";

    //Constructor
    //'Harcodeamos' 3 argumentos: name, factory, version
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        Log.d("DATABASE", "1º) - CONSTRUCTOR");
    }

    //Métodos abstractos que sobreescriben los métodos de 'SQLiteOpenHelper'
    @Override
    public abstract void onCreate(SQLiteDatabase db);

    @Override
    public abstract void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);


    //Métodos
    public abstract void insertAllData();

}

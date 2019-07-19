package com.afr.afrmageknight.databaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mageknight.db";

    public static final String CARTAS_TABLE = "CARTAS";

    public static final String COL_1 = "NUMERO";
    public static final String COL_2 = "NOMBRE";

    //'Harcodeamos' 3 argumentos: name, factory, version
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        Log.d("DATABASE", "PRIMERO - CONSTRUCTOR");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d("DATABASE", "SEGUNDO - ONCREATE");

        StringBuilder strSQL = new StringBuilder();

        strSQL.append("CREATE TABLE").append(CARTAS_TABLE).append(" ").append(" (")
                .append(COL_1).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(COL_2).append(" TEXT NOT NULL, ")
                .append(")");

        Log.d("DATABASE", "TERCERO - ONCREATE");
        Log.d("DATABASE", strSQL.toString());

        db.execSQL(strSQL.toString());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CARTAS_TABLE);
        onCreate(db);
    }

    //Métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
    public boolean insertData(int numero, String nombre){

        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva


        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1, numero);
        contentValues.put(COL_2, nombre);

        long resultado = db.insert(CARTAS_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal
        //Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados

        return resultado == -1 ? false : true;
    }

    //Un 'Cursor' es una tabla virtual
    public Cursor getAll(){

        SQLiteDatabase db = getWritableDatabase();

        // 'selectionArgs' es un array de Strings -> Array[]
        // En la consulta pueden haber ?s que serán sustituidos por los valores de este array de String
        Cursor cursor = db.rawQuery("SELECT * FROM "+ CARTAS_TABLE, null);

        //Ejemplo
        // SELECT * FROM AMIGOS WHERE nombre=? AND apellido LIKE '?%';
        // String[] = {"Adolfo","D"};
        return cursor;
    }
}

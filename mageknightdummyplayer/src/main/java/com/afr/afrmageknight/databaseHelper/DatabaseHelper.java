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

public class DatabaseHelper extends SQLiteOpenHelper {

    // Nombre de la base de datos
    public static final String DATABASE_NAME = "mageknight.db";

    // Nombre de las tablas
    public static final String HEROES_TABLE = "HEROES";
    public static final String CRISTALES_TABLE = "CRISTALES";
    public static final String HEROES_CRISTALES_TABLE = "HEROES_CRISTALES_TABLE";
    public static final String CARTAS_TABLE = "CARTAS";
    public static final String CARTAS_ACCIONES_BASICAS_TABLE = "CARTAS_ACCIONES_BASICAS";
    public static final String CARTAS_ACCIONES_AVANZADAS_TABLE = "CARTAS_ACCIONES_AVANZADAS";
    public static final String CARTAS_ACCIONES_AVANZADAS_ESPECIALES_TABLE = "CARTAS_ACCIONES_AVANZADAS_ESPECIALES";
    public static final String CARTAS_HECHIZOS_TABLE = "CARTAS_HECHIZOS";
    public static final String CARTAS_TACTICAS_TABLE = "CARTAS_TACTICAS";
    public static final String FICHAS_HABILIDAD_TABLE = "FICHAS_HABILIDAD";


    // Nombre de las columnas
    // HEROES_TABLE
    public static final String COL_1_HEROES_TABLE = "NOMBRE";

    // CRISTALES_TABLE
    public static final String COL_1_CRISTALES_TABLE = "CRISTAL";

    // HEROES_CRISTALES_TABLE
    public static final String COL_1_HEROES_CRISTALES_TABLE = "NOMBRE";
    public static final String COL_2_HEROES_CRISTALES_TABLE = "CRISTAL";

    // CARTAS_TABLE
    public static final String COL_1_CARTAS_TABLE = "NUMERO";
    public static final String COL_2_CARTAS_TABLE = "NOMBRE";

    // CARTAS_ACCIONES_AVANZADAS_TABLE
    public static final String COL_1_CARTAS_ACCIONES_AVANZADAS_TABLE = "NUMERO";
    public static final String COL_2_CARTAS_ACCIONES_AVANZADAS_TABLE = "COLOR";
    public static final String COL_3_CARTAS_ACCIONES_AVANZADAS_TABLE = "DESCRIPCION_BASICA";
    public static final String COL_4_CARTAS_ACCIONES_AVANZADAS_TABLE = "DESCRIPCION_AVANZADA";
    public static final String COL_5_CARTAS_ACCIONES_AVANZADAS_TABLE = "DESCARTADA";

    // CARTAS_ACCIONES_BASICAS_TABLE
    public static final String COL_1_CARTAS_ACCIONES_BASICAS_TABLE = "NUMERO";
    public static final String COL_2_CARTAS_ACCIONES_BASICAS_TABLE = "HEROE";

    // CARTAS_ACCIONES_AVANZADAS_ESPECIALES_TABLE
    public static final String COL_1_CARTAS_ACCIONES_AVANZADAS_ESPECIALES_TABLE = "NUMERO";
    public static final String COL_2_CARTAS_ACCIONES_AVANZADAS_ESPECIALES_TABLE = "COLOR_SECUNDARIO";

    // CARTAS_HECHIZOS_TABLE
    public static final String COL_1_CARTAS_HECHIZOS_TABLE = "NUMERO";
    public static final String COL_2_CARTAS_HECHIZOS_TABLE = "NOMBRE_SECUNDARIO";

    // CARTAS_TACTICAS_TABLE
    public static final String COL_1_CARTAS_TACTICAS_TABLE = "NUMERO";
    public static final String COL_2_CARTAS_TACTICAS_TABLE = "TIPO_TACTICA";
    public static final String COL_3_CARTAS_TACTICAS_TABLE = "NUMERO_ORDEN";
    public static final String COL_4_CARTAS_TACTICAS_TABLE = "DESCRIPCION";

    // FICHAS_HABILIDAD_TABLE
    public static final String COL_1_FICHAS_HABILIDAD_TABLE = "ID";
    public static final String COL_2_FICHAS_HABILIDAD_TABLE = "NOMBRE";
    public static final String COL_3_FICHAS_HABILIDAD_TABLE = "DESCRIPCION";
    public static final String COL_4_FICHAS_HABILIDAD_TABLE = "HEROE";


    //'Harcodeamos' 3 argumentos: name, factory, version
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        Log.d("DATABASE", "1º) - CONSTRUCTOR");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d("DATABASE", "2º) - ONCREATE");

        // HEROES_TABLE
        StringBuilder strSQLHeroesTable = new StringBuilder();

        strSQLHeroesTable.append("CREATE TABLE ").append(HEROES_TABLE).append(" (")
                .append(COL_1_HEROES_TABLE).append(" TEXT PRIMARY KEY")
                .append(")");

        Log.d("DATABASE", "PRIMERO - ONCREATE - HEROES_TABLE");
        Log.d("DATABASE", strSQLHeroesTable.toString());

        db.execSQL(strSQLHeroesTable.toString());


        // CRISTALES_TABLE
        StringBuilder strSQLCristalesTable = new StringBuilder();

        strSQLCristalesTable.append("CREATE TABLE ").append(CRISTALES_TABLE).append(" (")
                .append(COL_1_CRISTALES_TABLE).append(" TEXT PRIMARY KEY")
                .append(")");

        Log.d("DATABASE", "SEGUNDO - ONCREATE - CRISTALES_TABLE");
        Log.d("DATABASE", strSQLCristalesTable.toString());

        db.execSQL(strSQLCristalesTable.toString());


        // HEROES_CRISTALES_TABLE
        StringBuilder strSQLHeroesCristalesTable = new StringBuilder();

        strSQLHeroesCristalesTable.append("CREATE TABLE ").append(HEROES_CRISTALES_TABLE).append(" (")
                .append(COL_1_HEROES_CRISTALES_TABLE).append(" TEXT NOT NULL,")
                .append(COL_2_HEROES_CRISTALES_TABLE).append(" TEXT NOT NULL")
                .append(")");

        Log.d("DATABASE", "TERCERO - ONCREATE - HEROES_CRISTALES_TABLE");
        Log.d("DATABASE", strSQLHeroesCristalesTable.toString());

        db.execSQL(strSQLHeroesCristalesTable.toString());


        // CARTAS_TABLE
        StringBuilder strSQLCartasTable = new StringBuilder();

        strSQLCartasTable.append("CREATE TABLE ").append(CARTAS_TABLE).append(" (")
                .append(COL_1_CARTAS_TABLE).append(" INTEGER PRIMARY KEY,")
                .append(COL_2_CARTAS_TABLE).append(" TEXT NOT NULL")
                .append(")");

        Log.d("DATABASE", "CUARTO - ONCREATE - CARTAS_TABLE");
        Log.d("DATABASE", strSQLCartasTable.toString());

        db.execSQL(strSQLCartasTable.toString());


        // CARTAS_ACCIONES_AVANZADAS_TABLE
        StringBuilder strSQLCartasAccionesAvanzadasTable = new StringBuilder();

        strSQLCartasAccionesAvanzadasTable.append("CREATE TABLE ").append(CARTAS_ACCIONES_AVANZADAS_TABLE).append(" (")
                .append(COL_1_CARTAS_ACCIONES_AVANZADAS_TABLE).append(" INTEGER PRIMARY KEY,")
                .append(COL_2_CARTAS_ACCIONES_AVANZADAS_TABLE).append(" TEXT NOT NULL,")
                .append(COL_3_CARTAS_ACCIONES_AVANZADAS_TABLE).append(" TEXT NOT NULL,")
                .append(COL_4_CARTAS_ACCIONES_AVANZADAS_TABLE).append(" TEXT NOT NULL,")
                .append(COL_5_CARTAS_ACCIONES_AVANZADAS_TABLE).append(" BIT DEFAULT 0")
                .append(")");

        Log.d("DATABASE", "QUINTO - ONCREATE - CARTAS_ACCIONES_AVANZADAS_TABLE");
        Log.d("DATABASE", strSQLCartasAccionesAvanzadasTable.toString());

        db.execSQL(strSQLCartasAccionesAvanzadasTable.toString());


        // CARTAS_ACCIONES_BASICAS_TABLE
        StringBuilder strSQLCartasAccionesBasicasTable = new StringBuilder();

        strSQLCartasAccionesBasicasTable.append("CREATE TABLE ").append(CARTAS_ACCIONES_BASICAS_TABLE).append(" (")
                .append(COL_1_CARTAS_ACCIONES_BASICAS_TABLE).append(" INTEGER PRIMARY KEY,")
                .append(COL_2_CARTAS_ACCIONES_BASICAS_TABLE).append(" TEXT NOT NULL")
                .append(")");

        Log.d("DATABASE", "SEXTO - ONCREATE - CARTAS_ACCIONES_BASICAS_TABLE");
        Log.d("DATABASE", strSQLCartasAccionesBasicasTable.toString());

        db.execSQL(strSQLCartasAccionesBasicasTable.toString());


        // CARTAS_ACCIONES_AVANZADAS_ESPECIALES_TABLE
        StringBuilder strSQLCartasAccionesAvanzadasEspecialesTable = new StringBuilder();

        strSQLCartasAccionesAvanzadasEspecialesTable.append("CREATE TABLE ").append(CARTAS_ACCIONES_AVANZADAS_ESPECIALES_TABLE).append(" (")
                .append(COL_1_CARTAS_ACCIONES_AVANZADAS_ESPECIALES_TABLE).append(" INTEGER PRIMARY KEY,")
                .append(COL_2_CARTAS_ACCIONES_AVANZADAS_ESPECIALES_TABLE).append(" TEXT NOT NULL")
                .append(")");

        Log.d("DATABASE", "SEPTIMO - ONCREATE - CARTAS_ACCIONES_AVANZADAS_ESPECIALES_TABLE");
        Log.d("DATABASE", strSQLCartasAccionesAvanzadasEspecialesTable.toString());

        db.execSQL(strSQLCartasAccionesAvanzadasEspecialesTable.toString());


        // CARTAS_HECHIZOS_TABLE
        StringBuilder strSQLCartasHechizosTable = new StringBuilder();

        strSQLCartasHechizosTable.append("CREATE TABLE ").append(CARTAS_HECHIZOS_TABLE).append(" (")
                .append(COL_1_CARTAS_HECHIZOS_TABLE).append(" INTEGER PRIMARY KEY,")
                .append(COL_2_CARTAS_HECHIZOS_TABLE).append(" TEXT NOT NULL")
                .append(")");

        Log.d("DATABASE", "OCTAVO - ONCREATE - CARTAS_HECHIZOS_TABLE");
        Log.d("DATABASE", strSQLCartasHechizosTable.toString());

        db.execSQL(strSQLCartasHechizosTable.toString());


        // CARTAS_TACTICAS_TABLE
        StringBuilder strSQLCartasTacticasTable = new StringBuilder();

        strSQLCartasTacticasTable.append("CREATE TABLE ").append(CARTAS_TACTICAS_TABLE).append(" (")
                .append(COL_1_CARTAS_TACTICAS_TABLE).append(" INTEGER PRIMARY KEY,")
                .append(COL_2_CARTAS_TACTICAS_TABLE).append(" TEXT NOT NULL,")
                .append(COL_3_CARTAS_TACTICAS_TABLE).append(" INTEGER NOT NULL,")
                .append(COL_4_CARTAS_TACTICAS_TABLE).append(" TEXT NOT NULL")
                .append(")");

        Log.d("DATABASE", "NOVENO - ONCREATE - CARTAS_TACTICAS_TABLE");
        Log.d("DATABASE", strSQLCartasTacticasTable.toString());

        db.execSQL(strSQLCartasTacticasTable.toString());


        // FICHAS_HABILIDAD_TABLE
        StringBuilder strSQLFichasHabilidadTable = new StringBuilder();

        strSQLFichasHabilidadTable.append("CREATE TABLE ").append(FICHAS_HABILIDAD_TABLE).append(" (")
                .append(COL_1_FICHAS_HABILIDAD_TABLE).append(" INTEGER PRIMARY KEY,")
                .append(COL_2_FICHAS_HABILIDAD_TABLE).append(" TEXT NOT NULL,")
                .append(COL_3_FICHAS_HABILIDAD_TABLE).append(" TEXT NOT NULL,")
                .append(COL_4_FICHAS_HABILIDAD_TABLE).append(" TEXT NOT NULL")
                .append(")");

        Log.d("DATABASE", "DECIMO - ONCREATE - FICHAS_HABILIDAD_TABLE");
        Log.d("DATABASE", strSQLFichasHabilidadTable.toString());

        db.execSQL(strSQLFichasHabilidadTable.toString());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + HEROES_TABLE);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + CRISTALES_TABLE);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + HEROES_CRISTALES_TABLE);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + CARTAS_TABLE);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + CARTAS_ACCIONES_AVANZADAS_TABLE);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + CARTAS_ACCIONES_BASICAS_TABLE);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + CARTAS_ACCIONES_AVANZADAS_ESPECIALES_TABLE);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + CARTAS_HECHIZOS_TABLE);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + CARTAS_TACTICAS_TABLE);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + FICHAS_HABILIDAD_TABLE);
        onCreate(db);

    }

    //Métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
    public boolean insertDataHeroe(String nombre){

        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_HEROES_TABLE, nombre);

        long resultado = db.insert(HEROES_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    //Métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
    public boolean insertDataCristal(String cristal){

        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_CRISTALES_TABLE, cristal);

        long resultado = db.insert(CRISTALES_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    //Métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
    public boolean insertDataHeroeCristal(String nombre, String cristal){

        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_HEROES_CRISTALES_TABLE, nombre);
        contentValues.put(COL_2_HEROES_CRISTALES_TABLE, cristal);

        long resultado = db.insert(HEROES_CRISTALES_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    //Métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
    public boolean insertDataCarta(int numero, String nombre){

        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_CARTAS_TABLE, numero);
        contentValues.put(COL_2_CARTAS_TABLE, nombre);

        long resultado = db.insert(CARTAS_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    //Métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
    public boolean insertDataCartaAccionAvanzada(int numero, Cristal color, String descripcionBasica, String descripcionAvanzada, boolean descartada){

        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_CARTAS_ACCIONES_AVANZADAS_TABLE, numero);
        contentValues.put(COL_2_CARTAS_ACCIONES_AVANZADAS_TABLE, color.toString());
        contentValues.put(COL_3_CARTAS_ACCIONES_AVANZADAS_TABLE, descripcionBasica);
        contentValues.put(COL_4_CARTAS_ACCIONES_AVANZADAS_TABLE, descripcionAvanzada);
        contentValues.put(COL_5_CARTAS_ACCIONES_AVANZADAS_TABLE, descartada);

        long resultado = db.insert(CARTAS_ACCIONES_AVANZADAS_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    //Métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
    public boolean insertDataCartaAccionBasica(int numero, Heroe heroe){

        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_CARTAS_ACCIONES_BASICAS_TABLE, numero);
        contentValues.put(COL_2_CARTAS_ACCIONES_BASICAS_TABLE, heroe.getNombre());

        long resultado = db.insert(CARTAS_ACCIONES_BASICAS_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }


    //Métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
    public boolean insertDataCartaAccionAvanzadaEspecial(int numero, Cristal cristalSecundario){

        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_CARTAS_ACCIONES_AVANZADAS_ESPECIALES_TABLE, numero);
        contentValues.put(COL_2_CARTAS_ACCIONES_AVANZADAS_ESPECIALES_TABLE, cristalSecundario.toString());

        long resultado = db.insert(CARTAS_ACCIONES_AVANZADAS_ESPECIALES_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    //Métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
    public boolean insertDataCartaHechizo(int numero, String nombreSecundario){

        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_CARTAS_HECHIZOS_TABLE, numero);
        contentValues.put(COL_2_CARTAS_HECHIZOS_TABLE, nombreSecundario);

        long resultado = db.insert(CARTAS_HECHIZOS_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    //Métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
    public boolean insertDataCartaTactica(int numero, TipoTactica tipoTactica, int numeroOrden, String descripcion){

        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_CARTAS_TACTICAS_TABLE, numero);
        contentValues.put(COL_2_CARTAS_TACTICAS_TABLE, tipoTactica.toString());
        contentValues.put(COL_3_CARTAS_TACTICAS_TABLE, numeroOrden);
        contentValues.put(COL_4_CARTAS_TACTICAS_TABLE, descripcion);

        long resultado = db.insert(CARTAS_TACTICAS_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    //Métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
    public boolean insertDataFichaHabilidad(long id, String nombre, String descripcion, Heroe heroe){

        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_FICHAS_HABILIDAD_TABLE, id);
        contentValues.put(COL_2_FICHAS_HABILIDAD_TABLE, nombre);
        contentValues.put(COL_3_FICHAS_HABILIDAD_TABLE, descripcion);
        contentValues.put(COL_4_FICHAS_HABILIDAD_TABLE, heroe.getNombre());

        long resultado = db.insert(FICHAS_HABILIDAD_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    //Un 'Cursor' es una tabla virtual
    public Cursor getAllHeroes(){

        SQLiteDatabase db = getWritableDatabase();

        // 'selectionArgs' es un array de Strings -> Array[]
        // En la consulta pueden haber ?s que serán sustituidos por los valores de este array de String
        Cursor cursor = db.rawQuery("SELECT * FROM "+ HEROES_TABLE, null);

        //Ejemplo
        // SELECT * FROM AMIGOS WHERE nombre=? AND apellido LIKE '?%';
        // String[] = {"Adolfo","D"};
        return cursor;
    }

    //Un 'Cursor' es una tabla virtual
    public Cursor getAllCristales(){

        SQLiteDatabase db = getWritableDatabase();

        // 'selectionArgs' es un array de Strings -> Array[]
        // En la consulta pueden haber ?s que serán sustituidos por los valores de este array de String
        Cursor cursor = db.rawQuery("SELECT * FROM "+ CRISTALES_TABLE, null);

        return cursor;
    }

    //Un 'Cursor' es una tabla virtual
    public Cursor getAllHeroesCristales(){

        SQLiteDatabase db = getWritableDatabase();

        // 'selectionArgs' es un array de Strings -> Array[]
        // En la consulta pueden haber ?s que serán sustituidos por los valores de este array de String
        Cursor cursor = db.rawQuery("SELECT * FROM "+ HEROES_CRISTALES_TABLE, null);

        return cursor;
    }

    //Un 'Cursor' es una tabla virtual
    public Cursor getAllCartas(){

        SQLiteDatabase db = getWritableDatabase();

        // 'selectionArgs' es un array de Strings -> Array[]
        // En la consulta pueden haber ?s que serán sustituidos por los valores de este array de String
        Cursor cursor = db.rawQuery("SELECT * FROM "+ CARTAS_TABLE, null);

        //Ejemplo
        // SELECT * FROM AMIGOS WHERE nombre=? AND apellido LIKE '?%';
        // String[] = {"Adolfo","D"};
        return cursor;
    }

    public Cursor getAllCartasAccionesAvanzadas(){

        SQLiteDatabase db = getWritableDatabase();

        // 'selectionArgs' es un array de Strings -> Array[]
        // En la consulta pueden haber ?s que serán sustituidos por los valores de este array de String
        Cursor cursor = db.rawQuery("SELECT * FROM "+ CARTAS_ACCIONES_AVANZADAS_TABLE, null);

        return cursor;
    }

    public Cursor getAllCartasAccionesBasicas(){

        SQLiteDatabase db = getWritableDatabase();

        // 'selectionArgs' es un array de Strings -> Array[]
        // En la consulta pueden haber ?s que serán sustituidos por los valores de este array de String
        Cursor cursor = db.rawQuery("SELECT * FROM "+ CARTAS_ACCIONES_BASICAS_TABLE, null);

        return cursor;
    }

    public Cursor getAllCartasAccionesAvanzadasEspeciales(){

        SQLiteDatabase db = getWritableDatabase();

        // 'selectionArgs' es un array de Strings -> Array[]
        // En la consulta pueden haber ?s que serán sustituidos por los valores de este array de String
        Cursor cursor = db.rawQuery("SELECT * FROM "+ CARTAS_ACCIONES_AVANZADAS_ESPECIALES_TABLE, null);

        return cursor;
    }

    public Cursor getAllCartasHechizos(){

        SQLiteDatabase db = getWritableDatabase();

        // 'selectionArgs' es un array de Strings -> Array[]
        // En la consulta pueden haber ?s que serán sustituidos por los valores de este array de String
        Cursor cursor = db.rawQuery("SELECT * FROM "+ CARTAS_HECHIZOS_TABLE, null);

        return cursor;
    }

    public Cursor getAllCartasTacticas(){

        SQLiteDatabase db = getWritableDatabase();

        // 'selectionArgs' es un array de Strings -> Array[]
        // En la consulta pueden haber ?s que serán sustituidos por los valores de este array de String
        Cursor cursor = db.rawQuery("SELECT * FROM "+ CARTAS_TACTICAS_TABLE, null);

        return cursor;
    }

    public Cursor getAllFichasHabilidad(){

        SQLiteDatabase db = getWritableDatabase();

        // 'selectionArgs' es un array de Strings -> Array[]
        // En la consulta pueden haber ?s que serán sustituidos por los valores de este array de String
        Cursor cursor = db.rawQuery("SELECT * FROM "+ FICHAS_HABILIDAD_TABLE, null);

        return cursor;
    }

    //Insertar datos de la carta en su correspondiente tabla
    public Heroe createHeroe(Heroe heroe){
        insertDataHeroe(heroe.getNombre());
        return null;
    }

    public Cristal createCristal(Cristal cristal){
        insertDataCristal(cristal.toString());
        return null;
    }

    public void createHeroeCristal(Heroe heroe, Cristal cristal){
        insertDataHeroeCristal(heroe.getNombre(), cristal.toString());
    }

    public Carta createCarta(Carta carta){

        //TODO insertar carta en las tablas....
        // 1.- Se guarda la información genérica, independientemente del tipo de carta (tabla CARTAS)
        insertDataCarta(carta.getNumero(), carta.getNombre());

        if(carta.getClass() == CartaTactica.class){
            CartaTactica cartaTactica = (CartaTactica) carta;
            insertDataCartaTactica(carta.getNumero(), cartaTactica.getTipoTactica(), cartaTactica.getNumeroOrden(), cartaTactica.getDescripcion());

            return null;
        }

        CartaAccion cartaAccion = (CartaAccion) carta;

        // 2.- En función del tipo de carta, insertamos en la tabla que toque....
        if(carta.getClass() == CartaAccionAvanzada.class){
            insertDataCartaAccionAvanzada(carta.getNumero(), cartaAccion.getColor(), cartaAccion.getDescripcionBasica(), cartaAccion.getDescripcionAvanzada(), cartaAccion.isDescartada());
        } else if (carta.getClass() == CartaAccionBasica.class){
            CartaAccionBasica cartaAccionBasica = (CartaAccionBasica) cartaAccion;
            insertDataCartaAccionBasica(carta.getNumero(),cartaAccionBasica.getHeroe());
        } else if(carta.getClass() == CartaAccionAvanzadaEspecial.class) {
            CartaAccionAvanzadaEspecial cartaAccionAvanzadaEspecial = (CartaAccionAvanzadaEspecial) cartaAccion;
            insertDataCartaAccionAvanzadaEspecial(carta.getNumero(), cartaAccionAvanzadaEspecial.getColorSecundario());
        } else {  //(carta.getClass() == Hechizo.class)
            Hechizo hechizo = (Hechizo) cartaAccion;
            insertDataCartaHechizo(carta.getNumero(), hechizo.getNombreSecundario());
        }

        return null;
    }

    //Insertar datos de la Ficha de Habilidad en su correspondiente tabla
    public FichaHabilidad createFicha(FichaHabilidad fichaHabilidad){
       insertDataFichaHabilidad(fichaHabilidad.getId(), fichaHabilidad.getNombre(), fichaHabilidad.getDescripcion(), fichaHabilidad.getHeroe());

       return null;
    }

    public void insertAllData(){

        // Los 7 Heroes
        /*Heroe arythea = new Heroe("Arythea",  (ArrayList<Cristal>) Arrays.asList(Cristal.ROJO, Cristal.ROJO,Cristal.BLANCO));
        Heroe tovak = new Heroe("Tovak",  (ArrayList<Cristal>) Arrays.asList(Cristal.VERDE, Cristal.VERDE,Cristal.AZUL));
        Heroe norowas = new Heroe("Norowas",  (ArrayList<Cristal>) Arrays.asList(Cristal.AZUL, Cristal.AZUL,Cristal.ROJO));
        Heroe goldyx = new Heroe("Goldyx",  (ArrayList<Cristal>) Arrays.asList(Cristal.BLANCO, Cristal.BLANCO,Cristal.VERDE));
        Heroe wolfhawk = new Heroe("Wolfhawk",  (ArrayList<Cristal>) Arrays.asList(Cristal.BLANCO, Cristal.BLANCO,Cristal.AZUL));
        Heroe krang = new Heroe("Krang",  (ArrayList<Cristal>) Arrays.asList(Cristal.ROJO, Cristal.ROJO,Cristal.VERDE));
        Heroe braevalar = new Heroe("Braevalar",  (ArrayList<Cristal>) Arrays.asList(Cristal.AZUL, Cristal.AZUL,Cristal.VERDE));*/

        ArrayList<Cristal> cristalesArythea = new ArrayList<>();
        Collections.addAll(cristalesArythea, Cristal.ROJO, Cristal.ROJO, Cristal.BLANCO);

        ArrayList<Cristal> cristalesTovak = new ArrayList<>();
        Collections.addAll( cristalesTovak, Cristal.VERDE, Cristal.VERDE, Cristal.AZUL);

        ArrayList<Cristal> cristalesNorowas = new ArrayList<>();
        Collections.addAll( cristalesNorowas, Cristal.AZUL, Cristal.AZUL, Cristal.ROJO);

        ArrayList<Cristal> cristalesGoldyx = new ArrayList<>();
        Collections.addAll( cristalesGoldyx, Cristal.BLANCO, Cristal.BLANCO, Cristal.VERDE);

        ArrayList<Cristal> cristalesWolfhawk = new ArrayList<>();
        Collections.addAll( cristalesWolfhawk, Cristal.BLANCO, Cristal.BLANCO, Cristal.AZUL);

        ArrayList<Cristal> cristalesKrang = new ArrayList<>();
        Collections.addAll( cristalesKrang, Cristal.ROJO, Cristal.ROJO, Cristal.VERDE);

        ArrayList<Cristal> cristalesBraevalar = new ArrayList<>();
        Collections.addAll( cristalesBraevalar, Cristal.AZUL, Cristal.AZUL, Cristal.VERDE);

        Heroe arythea = new Heroe("Arythea",  cristalesArythea);
        Heroe tovak = new Heroe("Tovak",  cristalesTovak);
        Heroe norowas = new Heroe("Norowas",  cristalesNorowas);
        Heroe goldyx = new Heroe("Goldyx",  cristalesGoldyx);
        Heroe wolfhawk = new Heroe("Wolfhawk",  cristalesWolfhawk);
        Heroe krang = new Heroe("Krang",  cristalesKrang);
        Heroe braevalar = new Heroe("Braevalar",  cristalesBraevalar);

        createHeroe(arythea);
        createHeroe(tovak);
        createHeroe(norowas);
        createHeroe(goldyx);
        createHeroe(wolfhawk);
        createHeroe(krang);
        createHeroe(braevalar);

        createCristal(Cristal.ROJO);
        createCristal(Cristal.AZUL);
        createCristal(Cristal.BLANCO);
        createCristal(Cristal.VERDE);

        createHeroeCristal(arythea, arythea.getCristales().get(0));
        createHeroeCristal(arythea, arythea.getCristales().get(1));
        createHeroeCristal(arythea, arythea.getCristales().get(2));

        createHeroeCristal(tovak, tovak.getCristales().get(0));
        createHeroeCristal(tovak, tovak.getCristales().get(1));
        createHeroeCristal(tovak, tovak.getCristales().get(2));

        createHeroeCristal(norowas, norowas.getCristales().get(0));
        createHeroeCristal(norowas, norowas.getCristales().get(1));
        createHeroeCristal(norowas, norowas.getCristales().get(2));

        createHeroeCristal(goldyx, goldyx.getCristales().get(0));
        createHeroeCristal(goldyx, goldyx.getCristales().get(1));
        createHeroeCristal(goldyx, goldyx.getCristales().get(2));

        createHeroeCristal(wolfhawk, wolfhawk.getCristales().get(0));
        createHeroeCristal(wolfhawk, wolfhawk.getCristales().get(1));
        createHeroeCristal(wolfhawk, wolfhawk.getCristales().get(2));

        createHeroeCristal(krang, krang.getCristales().get(0));
        createHeroeCristal(krang, krang.getCristales().get(1));
        createHeroeCristal(krang, krang.getCristales().get(2));

        createHeroeCristal(braevalar, braevalar.getCristales().get(0));
        createHeroeCristal(braevalar, braevalar.getCristales().get(1));
        createHeroeCristal(braevalar, braevalar.getCristales().get(2));

        // Las 176 Cartas Avanzadas
        // 16 cartas del Mazo de Gesta de Arythea (Acción Básica)
        createCarta(new CartaAccionBasica(1, "Versatilidad en la Batalla", Cristal.ROJO, "Ataque 2, Bloqueo 2 o Ataque a Distancia 1.", "Ataque 4, Bloqueo 4, Ataque de Fuego 3, Bloqueo de Fuego 3, Ataque a Distancia 3 o Ataque de Asedio 2.", false, arythea));
        createCarta(new CartaAccionBasica(2, "Rabia", Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", false, arythea));
        createCarta(new CartaAccionBasica(3, "Determinación", Cristal.AZUL, "Ataque o Bloqueo 2.", "Bloqueo 5.", false, arythea));
        createCarta(new CartaAccionBasica(4, "Rapidez", Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", false, arythea));
        createCarta(new CartaAccionBasica(5, "Rapidez", Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", false, arythea));
        createCarta(new CartaAccionBasica(6, "Marcha", Cristal.VERDE, "Movimiento 2.", "Movimiento 4.", false, arythea));
        createCarta(new CartaAccionBasica(7, "Marcha", Cristal.VERDE, "Movimiento 2.", "Movimiento 4.", false, arythea));
        createCarta(new CartaAccionBasica(8, "Resistencia", Cristal.AZUL, "Movimiento 2.", "Movimiento 4.", false, arythea));
        createCarta(new CartaAccionBasica(9, "Resistencia", Cristal.AZUL, "Movimiento 2.", "Movimiento 4.", false, arythea));
        createCarta(new CartaAccionBasica(10, "Tranquilidad", Cristal.VERDE, "Curación 1 o roba 1 carta.", "Curación 2 o roba 2 cartas.", false, arythea));
        createCarta(new CartaAccionBasica(11, "Promesa", Cristal.BLANCO, "Influencia 2.", "Influencia 4.", false, arythea));
        createCarta(new CartaAccionBasica(12, "Amenaza", Cristal.ROJO, "Influencia 2.", "Influencia 5. Reputación -1.", false, arythea));
        createCarta(new CartaAccionBasica(13, "Cristalización", Cristal.AZUL, "Cuando juegues esta carta, paga 1 maná de color básico. Gana 1 cristal de ese color para tu inventario.", "Gana 1 cristal de cualquier color para tu Inventario.", false, arythea));
        createCarta(new CartaAccionBasica(14, "Extracción de Maná", Cristal.BLANCO, "Puedes usar 1 dado de maná adicional de la Fuente en este turno. Si es negro, úsalo para producir maná de cualquier color.", "Toma 2 dados de maná de la Fuente y déjalos en el color que quieras excepto dorado. Gana 1 ficha de maná de cada color. No relances los dados cuando los devuelvas a la Fuente.", false, arythea));
        createCarta(new CartaAccionBasica(15, "Concentración", Cristal.VERDE, "Gana 1 ficha de maná azul, blanco o rojo.", "Cuando juegues esta carta, juega otra carta de Acción con ella. Gana el efecto avanzado de esa carta sin coste. Si el efecto te otorga Movimiento, Influencia, Bloqueo o cualquier tipo de Ataque, gana la cantidad indicada +2.", false, arythea));
        createCarta(new CartaAccionBasica(16, "Improvisación", Cristal.ROJO, "Descarta otra carta de tu mano para obtener Movimiento 3, Influencia 3, Ataque 3 o Bloqueo 3.", "Descarta otra carta de tu mano para obtener Movimiento 5, Influencia 5, Ataque 5 o Bloqueo 5.", false, arythea));

        // 16 cartas del Mazo de Gesta de Tovak (Acción Básica)
        createCarta(new CartaAccionBasica(17, "Rabia", Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", false, tovak));
        createCarta(new CartaAccionBasica(18, "Rabia", Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", false, tovak));
        createCarta(new CartaAccionBasica(19, "Dureza Fría", Cristal.AZUL, "Ataque 2 o Bloqueo de Hielo 3.", "Bloqueo de Hielo 5. Gana +1 Bloqueo de Hielo por cada aptitud o color del ataque de la ficha de enemigo bloqueada (a menos que tenga Inmunidad Arcana), o por cada maná utilizado por el oponente durante el ataque.", false, tovak));
        createCarta(new CartaAccionBasica(20, "Rapidez", Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", false, tovak));
        createCarta(new CartaAccionBasica(21, "Rapidez", Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", false, tovak));
        createCarta(new CartaAccionBasica(22, "Marcha", Cristal.VERDE, "Movimiento 2.", "Movimiento 4.", false, tovak));
        createCarta(new CartaAccionBasica(23, "Marcha", Cristal.VERDE, "Movimiento 2.", "Movimiento 4.", false, tovak));
        createCarta(new CartaAccionBasica(24, "Resistencia", Cristal.AZUL, "Movimiento 2.", "Movimiento 4.", false, tovak));
        createCarta(new CartaAccionBasica(25, "Resistencia", Cristal.AZUL, "Movimiento 2.", "Movimiento 4.", false, tovak));
        createCarta(new CartaAccionBasica(26, "Tranquilidad", Cristal.VERDE, "Curación 1 o roba 1 carta.", "Curación 2 o roba 2 cartas.", false, tovak));
        createCarta(new CartaAccionBasica(27, "Promesa", Cristal.BLANCO, "Influencia 2.", "Influencia 4.", false, tovak));
        createCarta(new CartaAccionBasica(28, "Amenaza", Cristal.ROJO, "Influencia 2.", "Influencia 5. Reputación -1.", false, tovak));
        createCarta(new CartaAccionBasica(29, "Cristalización", Cristal.AZUL, "Cuando juegues esta carta, paga 1 maná de color básico. Gana 1 cristal de ese color para tu inventario.", "Gana 1 cristal de cualquier color para tu Inventario.", false, tovak));
        createCarta(new CartaAccionBasica(30, "Atracción de Maná", Cristal.BLANCO, "Puedes usar 1 dado de maná adicional de la Fuente en este turno.", "Toma 1 dado de maná de la Fuente y escoge una cara de cualquier color excepto dorado. Gana 2 fichas de maná de ese color. No relances este dado cuando lo devuelvas a la Fuente.", false, tovak));
        createCarta(new CartaAccionBasica(31, "Concentración", Cristal.VERDE, "Gana 1 ficha de maná azul, blanco o rojo.", "Cuando juegues esta carta, juega otra carta de Acción con ella. Gana el efecto avanzado de esa carta sin coste. Si el efecto te otorga Movimiento, Influencia, Bloqueo o cualquier tipo de Ataque, gana la cantidad indicada +2.", false, tovak));
        createCarta(new CartaAccionBasica(32, "Instinto", Cristal.ROJO, "Movimiento 2, Influencia 2, Ataque 2 o Bloqueo 2.", "Movimiento 4, Influencia 4, Ataque 4 o Bloqueo 4.", false, tovak));

        // 16 cartas del Mazo de Gesta de Norowas (Acción Básica)
        createCarta(new CartaAccionBasica(33, "Rabia", Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", false, norowas));
        createCarta(new CartaAccionBasica(34, "Rabia", Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", false, norowas));
        createCarta(new CartaAccionBasica(35, "Determinación", Cristal.AZUL, "Ataque o Bloqueo 2.", "Bloqueo 5.", false, norowas));
        createCarta(new CartaAccionBasica(36, "Rapidez", Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", false, norowas));
        createCarta(new CartaAccionBasica(37, "Rapidez", Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", false, norowas));
        createCarta(new CartaAccionBasica(38, "Marcha", Cristal.VERDE, "Movimiento 2.", "Movimiento 4.", false, norowas));
        createCarta(new CartaAccionBasica(39, "Marcha", Cristal.VERDE, "Movimiento 2.", "Movimiento 4.", false, norowas));
        createCarta(new CartaAccionBasica(40, "Resistencia", Cristal.AZUL, "Movimiento 2.", "Movimiento 4.", false, norowas));
        createCarta(new CartaAccionBasica(41, "Resistencia", Cristal.AZUL, "Movimiento 2.", "Movimiento 4.", false, norowas));
        createCarta(new CartaAccionBasica(42, "Rejuvenecer", Cristal.VERDE, "Curación 1, roba 1 carta, gana 1 ficha de maná verde, o prepara 1 Unidad de nivel I o II.", "Curación 2, roba 2 cartas, gana 1 cristal de maná verde para tu inventario, o prepara 1 Unidad de nivel I, II o III.", false, norowas));
        createCarta(new CartaAccionBasica(43, "Actos Nobles", Cristal.BLANCO, "Influencia 2. Fama +1 si usas esta carta durante la interacción.", "Influencia 4. Fama +1 y Reputación +1 si usas esta carta durante la interacción.", false, norowas));
        createCarta(new CartaAccionBasica(44, "Amenaza", Cristal.ROJO, "Influencia 2.", "Influencia 5. Reputación -1.", false, norowas));
        createCarta(new CartaAccionBasica(45, "Cristalización", Cristal.AZUL, "Cuando juegues esta carta, paga 1 maná de color básico. Gana 1 cristal de ese color para tu inventario.", "Gana 1 cristal de cualquier color para tu Inventario.", false, norowas));
        createCarta(new CartaAccionBasica(46, "Atracción de Maná", Cristal.BLANCO, "Puedes usar 1 dado de maná adicional de la Fuente en este turno.", "Toma 1 dado de maná de la Fuente y escoge una cara de cualquier color excepto dorado. Gana 2 fichas de maná de ese color. No relances este dado cuando lo devuelvas a la Fuente.", false, norowas));
        createCarta(new CartaAccionBasica(47, "Concentración", Cristal.VERDE, "Gana 1 ficha de maná azul, blanco o rojo.", "Cuando juegues esta carta, juega otra carta de Acción con ella. Gana el efecto avanzado de esa carta sin coste. Si el efecto te otorga Movimiento, Influencia, Bloqueo o cualquier tipo de Ataque, gana la cantidad indicada +2.", false, norowas));
        createCarta(new CartaAccionBasica(48, "Improvisación", Cristal.ROJO, "Descarta otra carta de tu mano para obtener Movimiento 3, Influencia 3, Ataque 3 o Bloqueo 3.", "Descarta otra carta de tu mano para obtener Movimiento 5, Influencia 5, Ataque 5 o Bloqueo 5.", false, norowas));

        // 16 cartas del Mazo de Gesta de Goldyx (Acción Básica)
        createCarta(new CartaAccionBasica(49, "Rabia", Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", false, goldyx));
        createCarta(new CartaAccionBasica(50, "Rabia", Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", false, goldyx));
        createCarta(new CartaAccionBasica(51, "Determinación", Cristal.AZUL, "Ataque o Bloqueo 2.", "Bloqueo 5.", false, goldyx));
        createCarta(new CartaAccionBasica(52, "Rapidez", Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", false, goldyx));
        createCarta(new CartaAccionBasica(53, "Rapidez", Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", false, goldyx));
        createCarta(new CartaAccionBasica(54, "Marcha", Cristal.VERDE, "Movimiento 2.", "Movimiento 4.", false, goldyx));
        createCarta(new CartaAccionBasica(55, "Marcha", Cristal.VERDE, "Movimiento 2.", "Movimiento 4.", false, goldyx));
        createCarta(new CartaAccionBasica(56, "Resistencia", Cristal.AZUL, "Movimiento 2.", "Movimiento 4.", false, goldyx));
        createCarta(new CartaAccionBasica(57, "Resistencia", Cristal.AZUL, "Movimiento 2.", "Movimiento 4.", false, goldyx));
        createCarta(new CartaAccionBasica(58, "Tranquilidad", Cristal.VERDE, "Curación 1 o roba 1 carta.", "Curación 2 o roba 2 cartas.", false, goldyx));
        createCarta(new CartaAccionBasica(59, "Promesa", Cristal.BLANCO, "Influencia 2.", "Influencia 4.", false, goldyx));
        createCarta(new CartaAccionBasica(60, "Amenaza", Cristal.ROJO, "Influencia 2.", "Influencia 5. Reputación -1.", false, goldyx));
        createCarta(new CartaAccionBasica(61, "Alegría Cristalizada", Cristal.AZUL, "Paga 1 maná de cualquier color básico para ganar 1 cristal de ese color para tu inventario. Al final de tu turno, puedes descartar otra carta que no sea Herida para devolver esta carta a tu mano.", "Gana 1 cristal de cualquier color básico para tu inventario. Al final de tu turno, puedes descartar otra carta (incluso una Herida) para", false, goldyx));
        createCarta(new CartaAccionBasica(62, "Atracción de Maná", Cristal.BLANCO, "Puedes usar 1 dado de maná adicional de la Fuente en este turno.", "Toma 1 dado de maná de la Fuente y escoge una cara de cualquier color excepto dorado. Gana 2 fichas de maná de ese color. No relances este dado cuando lo devuelvas a la Fuente.", false, goldyx));
        createCarta(new CartaAccionBasica(63, "Enfoque Decisivo", Cristal.VERDE, "Gana 1 ficha de maná azul, blanco o rojo o bien 1 cristal verde para tu inventario.", "Cuando juegues esta carta, juega otra carta de Acción con ella. Gana el efecto avanzado de esa carta sin coste. Si el efecto te otorga Movimiento, Influencia, Bloqueo o cualquier tipo de Ataque, gana la cantidad indicada +3.", false, goldyx));
        createCarta(new CartaAccionBasica(64, "Improvisación", Cristal.ROJO, "Descarta otra carta de tu mano para obtener Movimiento 3, Influencia 3, Ataque 3 o Bloqueo 3.", "Descarta otra carta de tu mano para obtener Movimiento 5, Influencia 5, Ataque 5 o Bloqueo 5.", false, goldyx));

        // 16 cartas del Mazo de Gesta de Wolfhawk (Acción Básica)
        createCarta(new CartaAccionBasica(271, "Rabia", Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", false, wolfhawk));
        createCarta(new CartaAccionBasica(272, "Rabia", Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", false, wolfhawk));
        createCarta(new CartaAccionBasica(273, "Determinación", Cristal.AZUL, "Ataque o Bloqueo 2.", "Bloqueo 5.", false, wolfhawk));
        createCarta(new CartaAccionBasica(274, "Reflejos Instantáneos", Cristal.BLANCO, "Movimiento 2, Ataque a Distancia 1, o reduce el ataque de un enemigo en 1.", "Movimiento 4, Ataque a Distancia 3, o reduce el ataque de un enemigo en 2.", false, wolfhawk));
        createCarta(new CartaAccionBasica(275, "Rapidez", Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", false, wolfhawk));
        createCarta(new CartaAccionBasica(276, "Marcha", Cristal.VERDE, "Movimiento 2.", "Movimiento 4.", false, wolfhawk));
        createCarta(new CartaAccionBasica(277, "Marcha", Cristal.VERDE, "Movimiento 2.", "Movimiento 4.", false, wolfhawk));
        createCarta(new CartaAccionBasica(278, "Incansable", Cristal.AZUL, "Movimiento 2. Con la siguiente carta que otorgue Movimiento (Incluidas las cartas giradas) obtienes +1 Movimiento en este turno.", "Movimiento 4. Con cualquier otra carta que otorgue Movimiento (Incluidas las cartas giradas) obtienes +1 Movimiento en este turno.", false, wolfhawk));
        createCarta(new CartaAccionBasica(279, "Resistencia", Cristal.AZUL, "Movimiento 2.", "Movimiento 4.", false, wolfhawk));
        createCarta(new CartaAccionBasica(280, "Tranquilidad", Cristal.VERDE, "Curación 1 o roba 1 carta.", "Curación 2 o roba 2 cartas.", false, wolfhawk));
        createCarta(new CartaAccionBasica(281, "Promesa", Cristal.BLANCO, "Influencia 2.", "Influencia 4.", false, wolfhawk));
        createCarta(new CartaAccionBasica(282, "Amenaza", Cristal.ROJO, "Influencia 2.", "Influencia 5. Reputación -1.", false, wolfhawk));
        createCarta(new CartaAccionBasica(283, "Cristalización", Cristal.AZUL, "Cuando juegues esta carta, paga 1 maná de color básico. Gana 1 cristal de ese color para tu inventario.", "Gana 1 cristal de cualquier color para tu Inventario.", false, wolfhawk));
        createCarta(new CartaAccionBasica(284, "Atracción de Maná", Cristal.BLANCO, "Puedes usar 1 dado de maná adicional de la Fuente en este turno.", "Toma 1 dado de maná de la Fuente y escoge una cara de cualquier color excepto dorado. Gana 2 fichas de maná de ese color. No relances este dado cuando lo devuelvas a la Fuente.", false, wolfhawk));
        createCarta(new CartaAccionBasica(285, "Concentración", Cristal.VERDE, "Gana 1 ficha de maná azul, blanco o rojo.", "Cuando juegues esta carta, juega otra carta de Acción con ella. Gana el efecto avanzado de esa carta sin coste. Si el efecto te otorga Movimiento, Influencia, Bloqueo o cualquier tipo de Ataque, gana la cantidad indicada +2.", false, wolfhawk));
        createCarta(new CartaAccionBasica(286, "Improvisación", Cristal.ROJO, "Descarta otra carta de tu mano para obtener Movimiento 3, Influencia 3, Ataque 3 o Bloqueo 3.", "Descarta otra carta de tu mano para obtener Movimiento 5, Influencia 5, Ataque 5 o Bloqueo 5.", false, wolfhawk));

        // 16 cartas del Mazo de Gesta de Krang (Acción Básica)
        createCarta(new CartaAccionBasica(337, "Rabia", Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", false, krang));
        createCarta(new CartaAccionBasica(338, "Rabia", Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", false, krang));
        createCarta(new CartaAccionBasica(339, "Determinación", Cristal.AZUL, "Ataque o Bloqueo 2.", "Bloqueo 5.", false, krang));
        createCarta(new CartaAccionBasica(340, "Rapidez", Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", false, krang));
        createCarta(new CartaAccionBasica(341, "Rapidez", Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", false, krang));
        createCarta(new CartaAccionBasica(342, "Cosecha Salvaje", Cristal.VERDE, "Movimiento 2. Una vez en este turno, cuando te muevas un espacio, puedes descartar 1 carta para ganar 1 cristal del mismo color. Si descartas 1 Artefacto puedes escoger el color.", "Movimiento 4. Cada vez que te muevas un espacio en este turno, puedes descartar 1 carta para ganar 1 cristal del mismo color. Si descartas 1 Artefacto puedes escoger el color.", false, krang));
        createCarta(new CartaAccionBasica(343, "Marcha", Cristal.VERDE, "Movimiento 2.", "Movimiento 4.", false, krang));
        createCarta(new CartaAccionBasica(344, "Resistencia", Cristal.AZUL, "Movimiento 2.", "Movimiento 4.", false, krang));
        createCarta(new CartaAccionBasica(345, "Resistencia", Cristal.AZUL, "Movimiento 2.", "Movimiento 4.", false, krang));
        createCarta(new CartaAccionBasica(346, "Tranquilidad", Cristal.VERDE, "Curación 1 o roba 1 carta.", "Curación 2 o roba 2 cartas.", false, krang));
        createCarta(new CartaAccionBasica(347, "Promesa", Cristal.BLANCO, "Influencia 2.", "Influencia 4.", false, krang));
        createCarta(new CartaAccionBasica(348, "Extorsión Implacable", Cristal.ROJO, "Influencia 2. Puedes obtener un descuento de 2 en el coste de reclutamiento de 1 Unidad. Si reclutas esa Unidad en este turno, obtienes Reputación -1.", "Influencia 6. Reputación -1. Puedes preparar Unidades de nivel I y II que controles pagando 2 de Influencia por cada nivel de la Unidad.", false, krang));
        createCarta(new CartaAccionBasica(349, "Cristalización", Cristal.AZUL, "Cuando juegues esta carta, paga 1 maná de color básico. Gana 1 cristal de ese color para tu inventario.", "Gana 1 cristal de cualquier color para tu Inventario.", false, krang));
        createCarta(new CartaAccionBasica(350, "Atracción de Maná", Cristal.BLANCO, "Puedes usar 1 dado de maná adicional de la Fuente en este turno.", "Toma 1 dado de maná de la Fuente y escoge una cara de cualquier color excepto dorado. Gana 2 fichas de maná de ese color. No relances este dado cuando lo devuelvas a la Fuente.", false, krang));
        createCarta(new CartaAccionBasica(351, "Concentración", Cristal.VERDE, "Gana 1 ficha de maná azul, blanco o rojo.", "Cuando juegues esta carta, juega otra carta de Acción con ella. Gana el efecto avanzado de esa carta sin coste. Si el efecto te otorga Movimiento, Influencia, Bloqueo o cualquier tipo de Ataque, gana la cantidad indicada +2.", false, krang));
        createCarta(new CartaAccionBasica(352, "Improvisación", Cristal.ROJO, "Descarta otra carta de tu mano para obtener Movimiento 3, Influencia 3, Ataque 3 o Bloqueo 3.", "Descarta otra carta de tu mano para obtener Movimiento 5, Influencia 5, Ataque 5 o Bloqueo 5.", false, krang));

        // 16 cartas del Mazo de Gesta de Braevalar (Acción Básica)
        createCarta(new CartaAccionBasica(356, "Rabia", Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", false, braevalar));
        createCarta(new CartaAccionBasica(357, "Rabia", Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", false, braevalar));
        createCarta(new CartaAccionBasica(358, "Determinación", Cristal.AZUL, "Ataque o Bloqueo 2.", "Bloqueo 5.", false, braevalar));
        createCarta(new CartaAccionBasica(359, "Rapidez", Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", false, braevalar));
        createCarta(new CartaAccionBasica(360, "Rapidez", Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", false, braevalar));
        createCarta(new CartaAccionBasica(361, "Marcha", Cristal.VERDE, "Movimiento 2.", "Movimiento 4.", false, braevalar));
        createCarta(new CartaAccionBasica(362, "Unido con la Tierra", Cristal.VERDE, "Movimiento 2, Curación 1, o Bloqueo 2.", "Movimiento 4, Curación 2, o Bloqueo igual al coste de Movimiento sin modificar del espacio en el que te encuentras (Montañas 5, Lagos 2). Bloqueo de Fuego durante el Día y Bloqueo de Hielo durante la Noche.", false, braevalar));
        createCarta(new CartaAccionBasica(363, "Resistencia", Cristal.AZUL, "Movimiento 2.", "Movimiento 4.", false, braevalar));
        createCarta(new CartaAccionBasica(364, "Sendas del Druida", Cristal.AZUL, "Movimiento 2. El coste de Movimiento de un espacio se reduce en 1 en este turno, hasta un mínimo de 2.", "Movimiento 4. El coste de Movimiento de un tipo de terreno se reduce en 1 en este turno, hasta un mínimo de 2.", false, braevalar));
        createCarta(new CartaAccionBasica(365, "Tranquilidad", Cristal.VERDE, "Curación 1 o roba 1 carta.", "Curación 2 o roba 2 cartas.", false, braevalar));
        createCarta(new CartaAccionBasica(366, "Promesa", Cristal.BLANCO, "Influencia 2.", "Influencia 4.", false, braevalar));
        createCarta(new CartaAccionBasica(367, "Amenaza", Cristal.ROJO, "Influencia 2.", "Influencia 5. Reputación -1.", false, braevalar));
        createCarta(new CartaAccionBasica(368, "Cristalización", Cristal.AZUL, "Cuando juegues esta carta, paga 1 maná de color básico. Gana 1 cristal de ese color para tu inventario.", "Gana 1 cristal de cualquier color para tu Inventario.", false, braevalar));
        createCarta(new CartaAccionBasica(369, "Atracción de Maná", Cristal.BLANCO, "Puedes usar 1 dado de maná adicional de la Fuente en este turno.", "Toma 1 dado de maná de la Fuente y escoge una cara de cualquier color excepto dorado. Gana 2 fichas de maná de ese color. No relances este dado cuando lo devuelvas a la Fuente.", false, braevalar));
        createCarta(new CartaAccionBasica(370, "Concentración", Cristal.VERDE, "Gana 1 ficha de maná azul, blanco o rojo.", "Cuando juegues esta carta, juega otra carta de Acción con ella. Gana el efecto avanzado de esa carta sin coste. Si el efecto te otorga Movimiento, Influencia, Bloqueo o cualquier tipo de Ataque, gana la cantidad indicada +2.", false, braevalar));
        createCarta(new CartaAccionBasica(371, "Improvisación", Cristal.ROJO, "Descarta otra carta de tu mano para obtener Movimiento 3, Influencia 3, Ataque 3 o Bloqueo 3.", "Descarta otra carta de tu mano para obtener Movimiento 5, Influencia 5, Ataque 5 o Bloqueo 5.", false, braevalar));

        // 28 cartas de Acción Avanzada (juego base)
        createCarta(new CartaAccionAvanzada(65, "Rayo de Fuego", Cristal.ROJO, "Gana 1 cristal rojo para tu Inventario.", "Ataque a Distancia de Fuego 3.", false));
        createCarta(new CartaAccionAvanzada(66, "Rayo de Hielo", Cristal.AZUL, "Gana 1 cristal azul para tu Inventario.", "Ataque a Distancia de Hielo 3.", false));
        createCarta(new CartaAccionAvanzada(67, "Rayo Fulminante", Cristal.BLANCO, "Gana 1 cristal blanco para tu Inventario.", "Ataque a Distancia 4.", false));
        createCarta(new CartaAccionAvanzada(68, "Rayo Devastador", Cristal.VERDE, "Gana 1 cristal verde para tu Inventario.", "Ataque de Asedio 3.", false));
        createCarta(new CartaAccionAvanzada(69, "Furia Sangrienta", Cristal.ROJO, "Ataque 2. Puedes recibir 1 Herida para aumentar a Ataque 5.", "Ataque 4. Puedes recibir 1 Herida para aumentar a Ataque 9.", false));
        createCarta(new CartaAccionAvanzada(70, "Escudo de Hielo", Cristal.AZUL, "Bloqueo de Hielo 3.", "Bloqueo de Hielo 3. Reduce en 3 la Armadura de un enemigo bloqueado de esta manera. La Armadura no puede ser reducida por debajo de 1.", false));
        createCarta(new CartaAccionAvanzada(71, "Agilidad", Cristal.BLANCO, "Movimiento 2. Durante un combate en este turno, puedes gastar puntos de Movimiento para obtener Ataque 1: uno por cada punto.", "Movimiento 4. Durante un combate en este turno, puedes gastar puntos de Movimiento: 2 para Ataque a Distancia 1 y/o 1 para Ataque 1.", false));
        createCarta(new CartaAccionAvanzada(72, "Paseo Reparador", Cristal.VERDE, "Movimiento 2 y Curación 1. Durante el combate: solo Movimiento 2.", "Movimiento 4 y Curación 2. Durante el combate: solo Movimiento 4.", false));
        createCarta(new CartaAccionAvanzada(73, "Intimidación", Cristal.ROJO, "Influencia 4 o Ataque 3. Reputación -1.", "Influencia 8 o Ataque 7. Reputación -2.", false));
        createCarta(new CartaAccionAvanzada(74, "Puente Helado", Cristal.AZUL, "Movimiento 2. El coste de Movimiento de pantanos se reduce a 1 en este turno.", "Movimiento 4. Puedes moverte a través de lagos. El coste de Movimiento de lagos y pantanos se reduce a 1 en este turno.", false));
        createCarta(new CartaAccionAvanzada(75, "Canción del Viento", Cristal.BLANCO, "Movimiento 2. El coste de Movimiento de llanuras, desiertos y páramos se reduce en 1, hasta un mínimo de 0 en este turno.", "Movimiento 2. El Movimiento de llanuras, desiertos y páramos se reduce en 2, hasta un mínimo de 0 este turno. Puedes pagar un maná azul para moverte a través de lagos con un coste de Movimiento 0 este turno.", false));
        createCarta(new CartaAccionAvanzada(76, "Búsqueda del Camino", Cristal.VERDE, "Movimiento 2. El coste de Movimiento de todos los terrenos se reduce en 1, hasta un mínimo de 2 en este turno.", "Movimiento 4. El coste de Movimiento de todos los terrenos se reduce a 2 en este turno.", false));
        createCarta(new CartaAccionAvanzada(77, "Ritual de Sangre", Cristal.ROJO, "Recibe 1 Herida. Gana 1 cristal de maná rojo para tu inventario y 1 ficha de maná de cualquier color (incluidos los no básicos).", "Recibe 1 Herida. Gana 3 fichas de maná de cualquier color (Incluidos los no básicos). Puedes pagar 1 maná de cualquier color básico para ganar 1 cristal de ese color para tu inventario.", false));
        createCarta(new CartaAccionAvanzada(78, "Magia Pura", Cristal.AZUL, "Cuando juegues esta carta, paga 1 maná. Si pagas verde, Movimiento 4. Si pagas blanco, Influencia 4. Si pagas azul, Bloqueo 4. Si pagas rojo, Ataque 4.", "Cuando juegues esta carta, paga 1 maná. Si pagas verde, Movimiento 7. Si pagas blanco, Influencia 7. Si pagas azul, Bloqueo 7. Si pagas rojo, Ataque 7.", false));
        createCarta(new CartaAccionAvanzada(79, "Cuento Heroico", Cristal.BLANCO, "Influencia 3. Por cada Unidad que hayas reclutado en este turno, Reputación +1.", "Influencia 6. Por cada Unidad que hayas reclutado en este turno, Fama +1 y Reputación +1.", false));
        createCarta(new CartaAccionAvanzada(80, "Regeneración", Cristal.VERDE, "Curación 1. Prepara 1 Unidad de Nivel I o II que controles.", "Curación 2. Prepara 1 Unidad de Nivel I, II o III que controles.", false));
        createCarta(new CartaAccionAvanzada(81, "Hacia el Fragor", Cristal.ROJO, "Juega esta carta al principio del combate. Todas tus Unidades aumentan sus valores de Ataque y Bloqueo en +2 durante este combate. No puedes asignar daño a tus Unidades en este turno.", "Juega esta carta al principio del combate. Todas tus Unidades aumentan sus valores de Ataque y Bloqueo en +3 durante este combate. No puedes asignar daño a tus Unidades en este turno.", false));
        createCarta(new CartaAccionAvanzada(82, "Ritmo Constante", Cristal.AZUL, "Movimiento 2. Al final de tu turno, en lugar de colocar esta carta en tu pila de descarte, puedes colocarla en la parte inferior de tu mazo de Gesta, siempre y cuando no esté vacío.", "Movimiento 4. Al final de tu turno, en lugar de colocar esta carta en tu pila de descarte, puedes colocarla en la parte superior de tu mazo de Gesta.", false));
        createCarta(new CartaAccionAvanzada(83, "Diplomacia", Cristal.BLANCO, "Influencia 2. Puedes usar Influencia como Bloqueo en este turno.", "Influencia 4. Elige Hielo o Fuego. Puedes usar Influencia como Bloqueo del elemento elegido en este turno.", false));
        createCarta(new CartaAccionAvanzada(84, "Necesitado", Cristal.VERDE, "Influencia 3. Gana Influencia, +1 adicional por cada carta de Herida en tu mano o en Unidades que controles.", "Influencia 5. Gana Influencia, +2 adicional por cada carta de Herida en tu mano o en Unidades que controles.", false));
        createCarta(new CartaAccionAvanzada(85, "Descomposición", Cristal.ROJO, "Cuando juegues esta carta, retira 1 carta de Acción de tu mano. Gana para tu inventario 2 cristales del mismo color que la carta retirada.", "Cuando juegues esta carta, retira 1 carta de Acción de tu mano. Gana para tu inventario 1 cristal de cada color básico que no coincida con el color de la carta retirada.", false));
        createCarta(new CartaAccionAvanzada(86, "Dominio del Cristal", Cristal.AZUL, "Gana 1 cristal para tu Inventario del mismo color que un cristal que ya poseas.", "Al final del turno, cualquier cristal que hayas utilizado en este turno se devuelve a tu inventario.", false));
        createCarta(new CartaAccionAvanzada(87, "Tormenta de Maná", Cristal.BLANCO, "Elige de la Fuente 1 dado de maná de color básico. Gana 1 cristal de ese color para tu inventario. Relanza ese dado inmediatamente y devuélvelo a la Fuente.", "Relanza los dados de la Fuente. Puedes utilizar 3 dados extra, y puedes usar dados de maná negro o dorado como cualquier color básico, independientemente de la Ronda.", false));
        createCarta(new CartaAccionAvanzada(88, "Emboscada", Cristal.VERDE, "Movimiento 2. Añade +1 a tu primera carta de Ataque de cualquier tipo o +2 a tu primera carta de Bloqueo de cualquier tipo, lo primero que juegues en este turno.", "Movimiento 4. Añade +2 a tu primera carta de Ataque de cualquier tipo o +4 a tu primera carta de Bloqueo, lo primero que juegues en este turno.", false));
        createCarta(new CartaAccionAvanzada(89, "Máxima Efectividad", Cristal.ROJO, "Cuando juegues esta carta, retira otra carta de Acción de tu mano. Usa 3 veces el efecto básico de la carta retirada.", "Cuando juegues esta carta, retira otra carta de Acción de tu mano. Usa 2 veces el efecto avanzado de la carta retirada (sin coste).", false));
        createCarta(new CartaAccionAvanzada(90, "Talento Mágico", Cristal.AZUL, "Descarta 1 carta de cualquier color. Puedes jugar 1 carta de Hechizo de ese color de la oferta de Hechizos en este turno como si estuviera en tu mano. La carta permanece en la oferta de Hechizos.", "Cuando juegues esta carta, paga 1 maná de cualquier color. Gana 1 carta de Hechizo de ese color de la oferta de Hechizos y colócala en tu pila de descarte.", false));
        createCarta(new CartaAccionAvanzada(91, "Aprendizaje", Cristal.BLANCO, "Influencia 2. Una vez en este turno, puedes pagar Influencia 6 para obtener 1 carta de Acción Avanzada de la oferta de Acciones Avanzadas y colocarla en tu pila de descarte.", "Influencia 4. Una vez en este turno, puedes pagar Influencia 9 para obtener 1 carta de Acción Avanzada de la oferta de Acciones Avanzadas y colocarla en tu mano.", false));
        createCarta(new CartaAccionAvanzada(92, "Entrenamiento", Cristal.VERDE, "Retira 1 carta de Acción de tu mano para tomar 1 carta del mismo color de la oferta de Acciones Avanzadas y colocarla en tu pila de descarte.", "Retira 1 carta de Acción de tu mano para tomar 1 carta del mismo color de la oferta de Acciones Avanzadas y colocarla en tu mano.", false));

        // 16 cartas de Hechizo (juego base)
        createCarta(new Hechizo(93, "Bola de Fuego", Cristal.ROJO, "Ataque a Distancia de Fuego 5.", "Recibe 1 Herida. Ataque de Asedio de Fuego 8.", false, "Tormenta de Fuego"));
        createCarta(new Hechizo(94, "Tormenta de Nieve", Cristal.AZUL, "Ataque a Distancia de Hielo 5.", "Recibe 1 Herida. Ataque de Asedio de Hielo 8.",false,"Ventisca"));
        createCarta(new Hechizo(95, "Exposición", Cristal.BLANCO, "El enemigo objetivo pierde todas las fortificaciones y resistencias durante este combate. Ataque a Distancia 2.", "Los enemigos pierden todas las fortificaciones, o pierden todas las resistencias durante este combate. Ataque a Distancia 3.", false,"Exposición Masiva"));
        createCarta(new Hechizo(96, "Temblor", Cristal.VERDE, "El enemigo objetivo obtiene Armadura -3, o todos los enemigos obtienen Armadura -2. La Armadura no puede ser reducida por debajo de 1.", "El enemigo objetivo obtiene Armadura -3 (-6 si está fortificado), o todos los enemigos obtienen Armadura -2 (-4 si están fortificados). La Armadura no puede ser reducida por debajo de 1.", false,"Terremoto"));
        createCarta(new Hechizo(97, "Muro de Llamas", Cristal.ROJO, "Ataque de Fuego 5, o Bloqueo de Fuego 7.", "Ataque de Fuego 5, o Bloqueo de Fuego 7. Este Ataque o Bloqueo incrementa en 2 por cada ficha de enemigo a la que te estás enfrentando.", false, "Ola de Llamas"));
        createCarta(new Hechizo(98, "Rayo de Maná", Cristal.AZUL, "Cuando juegues esta carta, paga 1 maná. Si es azul, Ataque de Hielo 8. Si es rojo, Ataque de Fuego Frío 7. Si es blanco, Ataque a Distancia de Hielo 6. Si es verde, Ataque de Asedio de Hielo 5.", "Cuando juegues esta carta, paga 1 maná. Si es azul, Ataque de Hielo 11. Si es rojo, Ataque de Fuego Frío 10. Si es blanco, Ataque a Distancia de Hielo 9. Si es verde, Ataque de Asedio de Hielo 8.", false, "Trueno de Maná"));
        createCarta(new Hechizo(99, "Torbellino", Cristal.BLANCO, "El enemigo objetivo no ataca durante este combate.", "Juega esta carta solo en la fase de Ataque del combate. Destruye al enemigo objetivo.", false,"Tornado"));
        createCarta(new Hechizo(100, "Viaje Subterráneo", Cristal.VERDE, "Muévete hasta 3 espacios revelados en el mapa. No puedes moverte a o a través de pantanos o lagos de este modo, y debes terminar tu movimiento en un espacio seguro. Este movimiento no provoca a los enemigos acechantes.", "Igual que el efecto básico, excepto que debes terminar tu movimiento en un lugar fortificado (o en un espacio ocupado por otro jugador). Esto termina tu movimiento e inicia un asalto (o un ataque a otro jugador). Ignora las fortificaciones del lugar. Si te retiras después del combate, vuelve a tu posición inicial.", false,"Ataque Subterráneo"));
        createCarta(new Hechizo(101, "Escudo Ardiente", Cristal.ROJO, "Bloqueo de Fuego 4. Si usas esta carta en un Bloqueo exitoso , puedes usarla también en la fase de Ataque como Ataque de Fuego 4.", "Bloqueo de Fuego 4. Si usas esta carta en un Bloqueo exitoso, destruye al enemigo bloqueado.", false, "Escudo Explosivo"));
        createCarta(new Hechizo(102, "Escalofrío", Cristal.AZUL, "El enemigo objetivo no ataca durante este combate. Si tiene Resistencia al Fuego, la pierde para el resto del turno.", "El enemigo objetivo no ataca durante este combate y obtiene Armadura -4 (hasta un mínimo de 1) para el resto del turno.", false, "Escalofrío Letal"));
        createCarta(new Hechizo(103, "Alas de Viento", Cristal.BLANCO, "Cuando juegues esta carta, gasta 1-5 puntos de Movimiento y muévete a un espacio revelado por cada punto. Debes terminar tu movimiento en un espacio seguro. Este movimiento no provoca a los enemigos acechantes.", "El enemigo objetivo no ataca durante este combate. Puedes hacer objetivo a enemigos adicionales: paga 1 punto de Movimiento por el segundo enemigo, 2 puntos por el tercero, etc.", false, "Alas de Noche"));
        createCarta(new Hechizo(104, "Restaurar", Cristal.VERDE, "Curación 3. Si estás en un bosque, Curación 5 en su lugar.", "Curación 3. Si estás en un bosque, Curación 5 en su lugar. Prepara Unidades hasta un valor de 3 niveles. Si estás en un Bosque, Prepara Unidades hasta un valor de 5 niveles en su lugar.", false, "Renacer"));
        createCarta(new Hechizo(105, "Demoler", Cristal.ROJO, "Ignora las fortificaciones de los lugares en este turno. Los enemigos obtienen Armadura -1 (hasta un mínimo de 1).", "Juega esta carta solamente durante la fase de Ataque del combate. Destruye al enemigo objetivo. Otros enemigos obtienen Armadura -1 (hasta un mínimo de 1).", false, "Desintegrar"));
        createCarta(new Hechizo(106, "Salto en el Espacio", Cristal.AZUL, "En este turno, puedes moverte a espacios y explorar nuevas losetas que estén a 2 espacios de distancia de ti como si estuvieran adyacentes. Ignora cualquier espacio por el que saltes de este modo. Este movimiento no provoca a los enemigos acechantes.", "Al final de tu turno, deja esta carta apartada para el resto de la Ronda. Coloca todas las demás cartas que hayas jugado en este turno (pero no las que hayas descartado o retirado) de nuevo en tu mano. Ignora la parte \"roba cartas nuevas\" del paso final de tu turno. Vuelve a realizar inmediatamente otro turno completo.", false, "Salto en el Tiempo"));
        createCarta(new Hechizo(107, "Llamada a las Armas", Cristal.BLANCO, "Puedes usar una aptitud de una Unidad en la oferta de Unidades en este turno, como si fuese una de las reclutadas por ti. No puedes asignar daño a esta Unidad.", "Recluta cualquier Unidad de la oferta de Unidades sin coste. (Si no tienes espacio para añadirla, debes disolver una Unidad primero.)", false, "Llamada a la Gloria"));
        createCarta(new Hechizo(108, "Meditación", Cristal.VERDE, "Toma 2 cartas al azar de tu pila de descarte y colócalas en la parte inferior o superior de tu mazo de Gesta. Tu límite de mano aumenta en 2 la próxima vez que robes cartas.", "Igual que el efecto básico, excepto que eliges las cartas en vez de tomarlas al azar.", false, "Trance"));

        // 4 cartas de Acción Avanzada Especiales(Ultimate Edition) (2 colores)
        createCarta(new CartaAccionAvanzadaEspecial(266, "Poder de los Cristales", Cristal.VERDE,  "Gana 1 cristal para tu Inventario de un color básico que aún no tengas.", "Movimiento 4, o Curación 2, o roba dos cartas. Por cada conjunto de 4 cristales de diferentes colores en tu Inventario: Movimiento 2, o Curación 1, o roba una carta.", false, Cristal.AZUL));
        createCarta(new CartaAccionAvanzadaEspecial(267, "Descarga de Adrenalina", Cristal.ROJO, "Por cada una de las primeras 3 Heridas que llevas a la mano en este turno, roba 1 carta.", "Tras tomar la primera Herida a la mano en este turno, retírala y roba 1 carta. Por cada 1 de las 3 Heridas siguientes que tomes, roba 1 carta.", false, Cristal.VERDE ));
        createCarta(new CartaAccionAvanzadaEspecial(268, "Rayo Explosivo", Cristal.BLANCO, "Recibe 1 Herida. Gana 1 cristal blanco y rojo para tu Inventario.", "Ataque a Distancia 3. Por cada enemigo derrotado con este ataque, otro enemigo obtiene Armadura -1 (hasta un mínimo de 1).", false, Cristal.ROJO));
        createCarta(new CartaAccionAvanzadaEspecial(269, "Mirada Penetrante", Cristal.AZUL, "Influencia 3, o el ataque del enemigo objetivo pierde todas sus aptitudes (pero no su color).", "Influencia 5, o el enemigo objetivo no ataca en este turno.", false, Cristal.BLANCO));

        // 12 cartas de Acción Avanzada (Legión Perdida)
        createCarta(new CartaAccionAvanzada(287, "Contraataque", Cristal.ROJO, "Ataque 2. Gana Ataque 2 adicional por cada enemigo bloqueado en este turno.", "Ataque 4. Gana Ataque 3 adicional por cada enemigo bloqueado en este turno.", false));
        createCarta(new CartaAccionAvanzada(288, "Ritual de Ataque", Cristal.ROJO, "Retira otra carta de acción. En función de su color, gana: Rojo: Ataque 5, Azul: Ataque de Hielo 3, Blanco: Ataque a Distancia 3, Verde: Ataque de Asedio 2.", "Retira otra carta de acción. En función de su color, gana: Rojo: Ataque de Fuego 6, Azul: Ataque de Fuego Frío 4, Blanco: Ataque a Distancia de Fuego 4, Verde: Ataque de Asedio de Fuego 3.", false));
        createCarta(new CartaAccionAvanzada(289, "Sangre Ancestral", Cristal.ROJO, "Recibe 1 Herida. Paga 1 maná de cualquier color. Gana 1 carta de ese color de la oferta de Acciones Avanzadas y colócala en tu mano.", "Recibe 1 Herida a tu mano o pila de descarte. Utiliza el efecto avanzado de cualquier carta de la oferta de Acciones Avanzadas sin pagar su coste. La carta permanece en la oferta.", false));
        createCarta(new CartaAccionAvanzada(290, "Golpe de Escudo", Cristal.AZUL, "Bloqueo 3. Cuenta el doble contra un ataque Veloz.", "Bloqueo 5. Cuenta el doble contra un ataque Veloz. El enemigo bloqueado obtiene Armadura -1 por cada punto de bloqueo sobrante (hasta un mínimo de 1).", false));
        createCarta(new CartaAccionAvanzada(291, "Portal Temporal", Cristal.AZUL, "Juega esta carta como tu acción en este turno. Puedes moverte a un espacio seguro adyacente revelado (sin provocar enemigos acechantes). Tanto si te mueves como si no, tu límite de mano aumenta en 1 la próxima vez que robes cartas.", "Lo mismo que el efecto básico, excepto que puedes o moverte hacia un espacio seguro 2 espacios en vez de uno, o aumentar tu límite de mano en 2 en vez de 1.", false));
        createCarta(new CartaAccionAvanzada(292, "Hechizo Forjado", Cristal.AZUL, "Gana 1 cristal para tu Inventario del mismo color de 1 de los Hechizos en la oferta de Hechizos.", "Gana 2 cristales para tu Inventario de los mismos colores que 2 Hechizos diferentes en la oferta de Hechizos.", false));
        createCarta(new CartaAccionAvanzada(293, "Cortesía", Cristal.BLANCO, "Ataque 3, o Ataque 2 y Reputación +1 por cada enemigo derrotado por este ataque.", "Ataque 6, o Ataque 4 y Reputación +1 y Fama +1 por cada enemigo derrotado por este ataque.", false));
        createCarta(new CartaAccionAvanzada(294, "Momento de Paz", Cristal.BLANCO, "Influencia 3. Puedes jugar esta carta como tu acción en este turno: si lo haces, puedes obtener Curación 1 por cada 2 de Influencia que hayas gastado.", "Influencia 6. Puedes jugar esta carta como tu acción en este turno: si lo haces, puedes obtener Curación 1 por cada 2 de Influencia que hayas gastado y/o preparar una Unidad pagando 2 de Influencia por cada nivel de la Unidad.", false));
        createCarta(new CartaAccionAvanzada(295, "Esquivar y Evitar", Cristal.BLANCO, "Reduce el ataque de un enemigo en 2. Gana Ataque 1 en la fase de Ataque si no añadiste Heridas a tu mano en las fases previas del combate.", "Reduce el ataque de un enemigo en 4 o 2 ataques de 1 o 2 enemigos en 2. Gana Ataque 2 en la fase de Ataque si no añadiste Heridas a tu mano en las fases previas del combate.", false));
        createCarta(new CartaAccionAvanzada(296, "Decisión Valiente", Cristal.VERDE, "Movimiento 2, Influencia 2, Ataque 2 o Bloqueo 2. Puedes descartar 1 Herida para aumentar el efecto en 1.", "Movimiento 3, Influencia 3, Ataque 3 o Bloqueo 3. Puedes descartar cualquier número de cartas, incluyendo 1 Herida, para incrementar el efecto en 2 por cada una.", false));
        createCarta(new CartaAccionAvanzada(297, "Fuerza de la Naturaleza", Cristal.VERDE, "La Unidad elegida gana Resistencia Física en este combate.", "Ataque de Asedio 3 o Bloqueo 6", false));
        createCarta(new CartaAccionAvanzada(298, "Sabiduría de la Montaña", Cristal.VERDE, "Movimiento 3. Si finalizas tu turno en la colinas, tu límite de Mano aumenta en 1 la próxima vez que robes cartas.", "Movimiento 5. Puedes entrar en Montañas con un coste de Movimiento 5 y se considerarán un espacio seguro para ti hasta el final de este turno. Si finalizas tu turno en montañas/colinas, tu límite de Mano aumenta en 2/1 la próxima vez que robes cartas.", false));

        // 4 cartas de Hechizo (Legión Perdida)
        createCarta(new Hechizo(299, "Ofrenda", Cristal.ROJO, "Gana un cristal rojo para tu Inventario. Puedes descartar hasta 3 cartas que no sean Herida de tu mano. Por cada carta descartada gana un cristal del mismo color que la carta para tu Inventario (si descartas Artefactos, puedes escoger el color).", "Elige verde o blanco, luego elige rojo o azul. Gana Ataque de Asedio 4 (si escogiste verde) o Ataque a Distancia 6 (si escogiste blanco) por cada pareja de cristales de uno de estos dos colores en tu Inventario. Este Ataque es de Fuego (si escogiste rojo) o Hielo (si escogiste azul). Luego, convierte todas las parejas de cristales en maná.", false, "Sacrificio"));
        createCarta(new Hechizo(300, "Forma Nebulosa", Cristal.AZUL, "Movimiento 4. El coste de Movimiento de todos los terrenos, incluidos los lagos, es 2. No puedes entrar en colinas y montañas durante el resto de este turno.", "Todas tus Unidades obtienen todas las resistencias. Tu héroe ignora la primera Herida de los enemigos en este turno. Ignora también cualquier efecto adicional de esa Herida.", false,  "Velo Nebuloso"));
        createCarta(new Hechizo(301, "Encanto", Cristal.BLANCO, "Influencia 4. Si usas esta carta durante la interacción puedes ganar un cristal de cualquier color para tu Inventario o bien obtener un descuento de 3 en el coste de una Unidad.", "El enemigo objetivo no ataca. En la fase de Ataque ganas el mismo Ataque que el del enemigo, incluidos sus elementos (si tuviera alguno), pero ignorando las aptitudes especiales. Con ese Ataque solo puedes hacer objetivo a otros enemigos.", false, "Posesión"));
        createCarta(new Hechizo(302, "Cura", Cristal.VERDE, "Curación 2. Roba una carta por cada Herida curada de tu mano en este turno, y prepara las Unidades curadas en este turno.", "Todos los enemigos bloqueados durante la fase de Bloqueo reducen su Armadura a 1.", false, "Enfermedad"));

        // 12 cartas de Táctica
        createCarta(new CartaTactica(129, "Madrugador", TipoTactica.DIA, 1, ""));
        createCarta(new CartaTactica(130, "Rectificar", TipoTactica.DIA, 2, "Cuando elijas esta Táctica, descarta hasta 3 cartas (incluyendo Heridas) de tu mano, luego roba la misma cantidad de cartas. Baraja tu pila de descarte junto con tu mazo de Gesta."));
        createCarta(new CartaTactica(131, "Robar Maná", TipoTactica.DIA, 3, "Cuando elijas esta Táctica, toma 1 dado de maná de color básico de la Fuente y colócalo sobre esta carta. Puedes usar este maná en uno de tus turnos durante este Día. Si lo haces, relánzalo al final de ese turno y devuélvelo a la Fuente."));
        createCarta(new CartaTactica(132, "Planificar", TipoTactica.DIA, 4, "Al final de cada turno, si tienes al menos 2 cartas en tu mano antes de robar, roba 1 carta más que tu límite de mano."));
        createCarta(new CartaTactica(133, "Buen Comienzo", TipoTactica.DIA, 5, "Cuando elijas esta Táctica, roba inmediatamente 2 cartas."));
        createCarta(new CartaTactica(134, "El Momento Adecuado", TipoTactica.DIA, 6, "Una vez durante este Día, en tu turno, puedes anunciar que realizarás otro turno inmediatamente después de este. Si lo haces, voltea esta carta boca abajo."));
        createCarta(new CartaTactica(135, "Desde la Oscuridad", TipoTactica.NOCHE, 1, ""));
        createCarta(new CartaTactica(136, "Larga Noche", TipoTactica.NOCHE, 2, "Una vez durante esta Noche, si tu mazo de Gesta está vacío, puedes barajar tu pila de descarte y colocar 3 cartas al azar de vuelta en el mazo de Gesta. Luego, voltea esta carta boca abajo."));
        createCarta(new CartaTactica(137, "Búsqueda de Maná", TipoTactica.NOCHE, 3, "Una vez por turno, antes de usar maná de la Fuente, puedes relanzar hasta 2 dados de maná de la Fuente. Cuando escojas los dados para relanzar, debes elegir primero los dados dorados (inactivos) si hay alguno."));
        createCarta(new CartaTactica(138, "Meditación a Medianoche", TipoTactica.NOCHE, 4, "Una vez durante esta Noche, antes de empezar uno de tus turnos, puedes barajar hasta 5 cartas de tu mano (incluyendo Heridas) junto con tu mazo de Gesta, y luego robar la misma cantidad de cartas. Después, voltea esta carta boca abajo."));
        createCarta(new CartaTactica(139, "Preparación", TipoTactica.NOCHE, 5, "Cuando elijas esta Táctica, busca en tu mazo de Gesta 1 carta que quieras y colócala en tu mano. Luego, baraja el mazo."));
        createCarta(new CartaTactica(140, "Reservar Poder", TipoTactica.NOCHE, 6, "Antes del inicio de cada uno de tus turnos, elige una opción: Coloca la carta de la parte superior de tu mazo de Gesta boca abajo, debajo de esta carta de Táctica, o voltea esta carta de Táctica boca abajo y coloca en tu mano todas las cartas de Gesta que estuvieran debajo."));


        // Las 70 Fichas de Habilidad
        // 10 Fichas de Habilidad de Arythea
        createFicha(new FichaHabilidad(1, "CAMINOS OSCUROS", "Una vez por turno: Movimiento 1 (durante el Día), o Movimiento 2 (durante la Noche).", arythea));
        createFicha(new FichaHabilidad(2, "PODER ABRASADOR", "Una vez por turno: Ataque de Asedio 1, o Ataque de Asedio de Fuego 1.", arythea));
        createFicha(new FichaHabilidad(3, "ESPADA ARDIENTE", "Una vez por turno: Ataque 2, o Ataque de Fuego 2.", arythea));
        createFicha(new FichaHabilidad(4, "NEGOCIACIÓN TURBIA", "Una vez por turno: Influencia 2 (durante el Día) o Influencia 3 (durante la Noche).", arythea));
        createFicha(new FichaHabilidad(5, "MAGIA OSCURA DE FUEGO", "Una vez por ronda: Voltea para ganar 1 cristal rojo para tu inventario y 1 ficha de maná rojo o negro.", arythea));
        createFicha(new FichaHabilidad(6, "EL PODER DEL DOLOR", "Una vez por turno: Puedes jugar 1 Herida girada como si no fuese una carta de Herida. Otorga +2 en vez de +1. Al final de tu turno, coloca esa Herida en tu pila de descarte.", arythea));
        createFicha(new FichaHabilidad(7, "INVOCACIÓN", "Una vez por turno: Descarta 1 carta de Herida para ganar 1 ficha de maná rojo o negro, o descarta 1 carta que no sea Herida para ganar 1 ficha de maná verde o blanco. El maná conseguido de esta manera debe ser usado inmediatamente, o no podrás usar esta Habilidad.", arythea));
        createFicha(new FichaHabilidad(8, "POLARIZACIÓN", "Una vez por turno: Puedes usar 1 maná como maná del color opuesto (ver el diagrama). Durante el día, puedes usar maná negro como si fuera otro color (no puedes usarlo como maná negro para potenciar los Hechizos). Durante la noche, puedes usar maná dorado como si fuera maná negro y potenciar un Hechizo, pero no como cualquier otro color.", arythea));
        createFicha(new FichaHabilidad(9, "MOTIVACIÓN", "Una vez por Ronda, en el turno de cualquier jugador: Voltea para robar 2 cartas. Si eres quien menos Fama tiene (no cuenta el empate), gana también 1 ficha de maná rojo. No puedes usar otra Habilidad de Motivación hasta el final de tu siguiente turno.", arythea));
        createFicha(new FichaHabilidad(10, "RITUAL DEL DOLOR", "Una vez por ronda, excepto durante el combate: Retira hasta 2 cartas de Herida de tu mano. Coloca esta ficha de Habilidad en el centro. Cualquier jugador puede devolvértela, boca abajo, para jugar una carta de Herida girada como si no fuera una carta de Herida, y obtener +3 en vez de +1.", arythea));

        // 10 Fichas de Habilidad de Tovak
        createFicha(new FichaHabilidad(11, "DOBLE RITMO", "Una vez por turno: Movimiento 2 (durante el día), o Movimiento 1 (durante la Noche).", tovak));
        createFicha(new FichaHabilidad(12, "PUNTERÍA DE NOCHE", "Una vez por turno: Ataque a Distancia 2 (durante la Noche), o Ataque a Distancia 1 (durante el Día).", tovak));
        createFicha(new FichaHabilidad(13, "ESPADA GÉLIDA", "Una vez por turno: Ataque 2, o Ataque de Hielo 2.", tovak));
        createFicha(new FichaHabilidad(14, "DOMINIO DEL ESCUDO", "Una vez por turno: Bloqueo 3, o Bloqueo de Hielo 2, o Bloqueo de Fuego 2.", tovak));
        createFicha(new FichaHabilidad(15, "RESISTENCIA QUEBRADA", "Una vez por turno: Escoge 1 ficha de enemigo. Obtiene Armadura -1 por cada resistencia que tenga (hasta un mínimo de 1).", tovak));
        createFicha(new FichaHabilidad(16, "NO SIENTO DOLOR…", "Una vez por turno, excepto durante el combate: Descarta 1 Herida de tu mano. Si lo haces, roba 1 carta.", tovak));
        createFicha(new FichaHabilidad(17, "¡NO ME IMPORTA NADA!", "Una vez por turno: Una carta que juegues girada otorga +2 en vez de +1. Si es una Acción Avanzada, Hechizo o Artefacto, otorga +3.", tovak));
        createFicha(new FichaHabilidad(18, "¿QUIÉN NECESITA MAGIA?", "Una vez por turno: Una carta que juegues girada otorga +2 en vez de +1. Si no usas ningún dado de maná de la Fuente en este turno, otorga +3.", tovak));
        createFicha(new FichaHabilidad(19, "MOTIVACIÓN", "Una vez por Ronda, en el turno de cualquier jugador: Voltea para robar 2 cartas. Si eres quien menos Fama tiene (no cuenta el empate), gana también 1 ficha de maná azul. No puedes usar otra Habilidad de Motivación hasta el final de tu siguiente turno.", tovak));
        createFicha(new FichaHabilidad(20, "SOBRECARGA DE MANÁ", "Una vez por Ronda: Elige un color que no sea dorado y gana 1 ficha de maná de ese color. Coloca esta ficha de Habilidad en el centro y márcala con una segunda ficha de maná de ese color. El primer jugador que utilice maná de ese color para potenciar una carta que otorgue Movimiento, Influencia, o cualquier tipo de Ataque o Bloqueo, obtiene +4 para esa carta y te devuelve la ficha de Habilidad, boca abajo.", tovak));

        // 10 Fichas de Habilidad de Norowas
        createFicha(new FichaHabilidad(21, "¡MARCHA LIGERA!", "Una vez por turno: Gana Movimiento 1 por cada Unidad Preparada y no Herida que controles, hasta un máximo de Movimiento 3.", norowas));
        createFicha(new FichaHabilidad(22, "PUNTERÍA DE DÍA", "Una vez por turno: Ataque a Distancia 2 (durante el Día), o Ataque a Distancia 1 (durante la Noche).", norowas));
        createFicha(new FichaHabilidad(23, "INSPIRACIÓN", "Una vez por Ronda, excepto durante el combate: Voltea para Preparar o Curar 1 Unidad.", norowas));
        createFicha(new FichaHabilidad(24, "NEGOCIACIÓN INGENIOSA", "Una vez por turno: Influencia 3 (durante el Día), o Influencia 2 (durante la Noche).", norowas));
        createFicha(new FichaHabilidad(25, "HOJAS EN EL VIENTO", "Una vez por Ronda: Voltea para ganar 1 cristal verde para tu inventario y 1 ficha de maná blanco.", norowas));
        createFicha(new FichaHabilidad(26, "SUSURRO EN LOS ÁRBOLES", "Una vez por Ronda: Voltea para ganar 1 cristal blanco para tu inventario y 1 ficha de maná verde.", norowas));
        createFicha(new FichaHabilidad(27, "LIDERAZGO", "Una vez por turno: Cuando actives una Unidad, añade +3 a su Bloqueo, o +2 a su Ataque, o +1 a su Ataque a Distancia (no de Asedio), sin importar su elemento.", norowas));
        createFicha(new FichaHabilidad(28, "VÍNCULOS DE LEALTAD", "Cuando obtengas esta Habilidad, añade 2 Unidades Básicas a la oferta de Unidades. Coloca esta ficha de Habilidad en tu área de Unidades como si fuese una ficha de Mando. Reclutar 1 Unidad y colocarla debajo de esta ficha te cuesta 5 puntos de Influencia menos (mínimo 0). Esta Unidad se puede utilizar incluso cuando el uso de Unidades no esté permitido. No puedes disolverla.", norowas));
        createFicha(new FichaHabilidad(29, "MOTIVACIÓN", "Una vez por Ronda, en el turno de cualquier jugador: Voltea para robar 2 cartas. Si eres quien menos Fama tiene (no cuenta el empate), gana también 1 ficha de maná blanco. No puedes usar otra Habilidad de Motivación hasta el final de tu siguiente turno.", norowas));
        createFicha(new FichaHabilidad(30, "CALMANDO EL TIEMPO", "Una vez por Ronda: El coste de Movimiento de todos los terrenos se reduce en 2 (hasta un mínimo de 1) para ti en este turno. Coloca la ficha de Habilidad en el centro. Cualquier jugador puede devolvértela, boca abajo, para reducir el coste de Movimiento de todos los terrenos en 1 para él en este turno.", norowas));

        // 10 Fichas de Habilidad de Goldyx
        createFicha(new FichaHabilidad(31, "PODER DE CONGELACIÓN", "Una vez por turno: Ataque de Asedio 1 o Ataque de Asedio de Hielo 1.", goldyx));
        createFicha(new FichaHabilidad(32, "ELABORACIÓN DE POCIONES", "Una vez por Ronda, excepto durante el combate: Voltea para obtener Curación 2.", goldyx));
        createFicha(new FichaHabilidad(33, "ARTESANÍA DE CRISTAL BLANCO", "Una vez por Ronda: Voltea para obtener 1 cristal azul para tu inventario y 1 ficha de maná blanco.", goldyx));
        createFicha(new FichaHabilidad(34, "ARTESANÍA DE CRISTAL VERDE", "Una vez por Ronda: Voltea para obtener 1 cristal azul para tu inventario y una ficha de maná verde.", goldyx));
        createFicha(new FichaHabilidad(35, "ARTESANÍA DE CRISTAL ROJO", "Una vez por Ronda: Voltea para obtener 1 cristal azul para tu inventario y 1 ficha de maná rojo.", goldyx));
        createFicha(new FichaHabilidad(36, "FORTUNA RELUCIENTE", "Una vez por turno, durante la interacción: Gana Influencia +1 por cada cristal de color diferente en tu inventario. Esta Influencia no puede ser utilizada fuera de una interacción.", goldyx));
        createFicha(new FichaHabilidad(37, "VUELO", "Una vez por Ronda: Voltea para moverte a 1 espacio adyacente sin coste, o a 2 espacios por 2 puntos de Movimiento. Debes terminar este Movimiento en un espacio seguro. Este Movimiento no provoca a los enemigos acechantes.", goldyx));
        createFicha(new FichaHabilidad(38, "PODER UNIVERSAL", "Una vez por turno: Puedes añadir maná a una carta girada. Si lo haces, la carta otorga +3 en vez de +1. Si es una Acción o un Hechizo del mismo color que el maná, otorga +4.", goldyx));
        createFicha(new FichaHabilidad(39, "MOTIVACIÓN", "Una vez por Ronda, en el turno de cualquier jugador: Voltea para robar 2 cartas. Si eres quien menos Fama tiene (no cuenta el empate), gana también 1 ficha de maná verde. No puedes usar otra Habilidad de Motivación hasta el final de tu siguiente turno.", goldyx));
        createFicha(new FichaHabilidad(40, "APERTURA DE LA FUENTE", "Una vez por Ronda: Coloca esta ficha de Habilidad en el centro. Puedes relanzar un dado de maná en la Fuente. Cualquier jugador puede elegir devolverte la ficha, boca abajo, para usar 1 dado de maná adicional de color básico de la Fuente y darte 1 cristal de ese color. Pueden decidir relanzar o no ese dado al final de su turno.", goldyx));

        // 10 Fichas de Habilidad de Wolfhawk
        createFicha(new FichaHabilidad(41, "BAÑO REFRESCANTE", "Una vez por Ronda, excepto durante el combate: Voltea para obtener Curación 1 y 1 cristal azul para tu Inventario.", wolfhawk));
        createFicha(new FichaHabilidad(42, "BRISA REFRESCANTE", "Una vez por Ronda, excepto durante el combate: Voltea para obtener Curación 1 y 1 cristal blanco para tu Inventario.", wolfhawk));
        createFicha(new FichaHabilidad(43, "OJOS DE HALCÓN", "Una vez por turno: Movimiento 1. Además: durante la Noche, la exploración cuesta 1 Movimiento menos; durante el Día, puedes revelar defensores de lugares fortificados a una distancia de 2.", wolfhawk));
        createFicha(new FichaHabilidad(44, "POR SU CUENTA", "Una vez por turno: Influencia 1. Influencia 3 si no reclutas ninguna Unidad usando Influencia en este turno.", wolfhawk));
        createFicha(new FichaHabilidad(45, "PUNTERÍA MORTAL", "Una vez por turno: Añade +1 a una carta que otorgue cualquier tipo de Ataque en la fase de Ataque a Distancia y de Asedio, o añade +2 a una carta que otorgue cualquier tipo de Ataque (incluidas las cartas giradas) en la fase de Ataque.", wolfhawk));
        createFicha(new FichaHabilidad(46, "CONOCE A TU PRESA", "Una vez por Ronda, durante el combate: Voltea para ignorar una aptitud ofensiva o defensiva de una ficha de enemigo, o eliminar un elemento de un ataque enemigo (Hielo y Fuego se convierten en Físico, Fuego Frío se convierte en Hielo o Fuego). No se puede utilizar contra enemigos con Inmunidad Arcana.", wolfhawk));
        createFicha(new FichaHabilidad(47, "BURLA", "Una vez por turno, durante la fase de Bloqueo: Puedes o reducir un ataque de un enemigo en 1, o incrementar un ataque de un enemigo en 2 y reducir la Armadura del mismo enemigo en 2 (hasta un mínimo de 1). La reducción de la Armadura no sucede si como resultado el enemigo no puede atacar.", wolfhawk));
        createFicha(new FichaHabilidad(48, "DUELO", "Una vez por turno, durante la fase de Bloqueo: Bloqueo 1. Ataque 1 contra el mismo enemigo en la fase de Ataque. Si no usas aptitudes de ninguna Unidad para bloquear, atacar, afectar a este enemigo ni asignas daño del enemigo a cualquier Unidad, ganas 1 más de Fama por derrotarlo. JcJ: Bloqueo 1 en la fase de Ataques Cuerpo a Cuerpo, Ataque 1 en tu próximo Ataque, Fama 1 si no has usado aptitudes de ninguna Unidad durante este Bloqueo y Ataque.", wolfhawk));
        createFicha(new FichaHabilidad(49, "MOTIVACIÓN", "Una vez por Ronda, en el turno de cualquier jugador: Voltea la ficha para robar 2 cartas. Si eres quien menos Fama tiene (sin contar empates), gana también Fama 1. No puedes usar otra Habilidad de Motivación hasta el final de tu siguiente turno.", wolfhawk));
        createFicha(new FichaHabilidad(50, "AULLIDO DE LA MANADA", "Una vez por Ronda, excepto durante la interacción: Una carta girada otorga +4 en vez de +1. Por cada una de tus fichas de Mando sin Unidades asignadas, gana otro +1. Coloca esta ficha de Habilidad en el centro. El primer jugador que entre en combate con enemigos te devuelve la Habilidad boca abajo, reduce la Armadura de un enemigo elegido en 1, y un ataque del mismo u otro enemigo en 1.", wolfhawk));

        // 10 Fichas de Habilidad de Krang
        createFicha(new FichaHabilidad(51, "GUÍAS ESPIRITUALES", "Una vez por turno: Movimiento 1 y puedes añadir +1 a un Bloqueo de cualquier tipo en la fase de Bloqueo.", krang));
        createFicha(new FichaHabilidad(52, "CURTIDO EN BATALLAS", "Una vez por turno: Ignora o los siguientes 2 puntos de daño asignados a tu Héroe de un Ataque Físico o 1 punto de daño de un Ataque no físico.", krang));
        createFicha(new FichaHabilidad(53, "FRENESÍ DE LA BATALLA", "Una vez por turno: Ataque 2, o puedes voltear esta ficha para obtener Ataque 4 en este turno. Si usas tu turno para descansar, puedes voltear esta ficha de nuevo.", krang));
        createFicha(new FichaHabilidad(54, "RITUAL DEL CHAMÁN", "Voltea para ganar una ficha de maná de cualquier color. Puedes voltearla de nuevo como tu acción en este turno.", krang));
        createFicha(new FichaHabilidad(55, "REGENERAR", "Una vez por turno: Paga un maná de cualquier color y retira una Herida de tu mano. Si para hacerlo usaste maná rojo, o si eres quien menos Fama tiene (sin contar empates), roba también una carta.", krang));
        createFicha(new FichaHabilidad(56, "DISFRAZ ARCANO", "Una vez por turno: Influencia 2, o puedes voltear esta ficha para ignorar todos los efectos de tu posición en el marcador de Reputación en este turno. Puedes voltearla de nuevo en cualquier turno pagando un maná verde.", krang));
        createFicha(new FichaHabilidad(57, "MAESTRO DE MARIONETAS", "Una vez por turno: Puedes o quedarte 1 ficha de enemigo que hayas derrotado en este turno o descartar 1 ficha que te hayas quedado previamente. Si decides descartar, obtienes Ataque(s) igual a la mitad de su valor de Ataque o Bloqueo igual a la mitad de su valor de Armadura (todo redondeando hacia arriba). Si el Ataque de la ficha es de Hielo o Fuego, el Ataque que obtienes también lo será. Si la ficha tiene Resistencia al Hielo o Fuego, el Bloqueo que obtienes será del elemento opuesto.", krang));
        createFicha(new FichaHabilidad(58, "MAESTRO DEL CAOS", "Cuando ganes esta Habilidad, lanza 1 dado de maná y márcalo con 1 ficha de Escudo encima del color lanzado. Una vez por turno puedes mover la ficha de Escudo al siguiente color en sentido horario para obtener, dependiendo del color: Azul: Bloqueo 3, Verde: Movimiento 1, Negro: Ataque a Distancia de Fuego Frío 1, Blanco: Influencia 2, Rojo: Ataque 2, Dorado: Tú eliges. Si no usas esta Habilidad durante tu turno, podrás mover la ficha de Escudo al siguiente color en sentido horario en cualquier momento antes de tu siguiente turno.", krang));
        createFicha(new FichaHabilidad(59, "MALDICIÓN", "Una vez por turno: Puedes reducir un Ataque de un enemigo en 1 o reducir la Armadura de un enemigo en 1 (hasta un mínimo de 1). No puedes usar esta Habilidad contra un enemigo fortificado en las fases de Ataque a Distancia y de Asedio.", krang));
        createFicha(new FichaHabilidad(60, "AUMENTO DE MANÁ", "Una vez por Ronda: Cuando gastes maná de un color básico, coloca esta ficha de Habilidad en el centro y márcala con 1 ficha de maná del mismo color. Gana 1 cristal de ese color. Hasta el comienzo de tu siguiente turno, cualquier jugador puede devolverte la ficha de Habilidad boca abajo, para usar la ficha de maná que está encima de ella.", krang));

        // 10 Fichas de Habilidad de Braevalar
        createFicha(new FichaHabilidad(61, "RESISTENCIA ELEMENTAL", "Una vez por turno: Ignora los 2 próximos puntos de daño asignados a tu Héroe de un Ataque de Fuego o Hielo o bien 1 punto de daño de otro tipo de Ataque.", braevalar));
        createFicha(new FichaHabilidad(62, "ALIADOS SALVAJES", "Explorar cuesta 1 menos de Movimiento. Una vez por turno: Ataque 1, o reduce un ataque de un enemigo en 1.", braevalar));
        createFicha(new FichaHabilidad(63, "TEMPESTAD", "Una vez por Ronda: Voltea para ganar una ficha de maná verde o azul, y una ficha de maná verde o blanca.", braevalar));
        createFicha(new FichaHabilidad(64, "TORMENTA ELÉCTRICA", "Una vez por Ronda: Voltea para ganar una ficha de maná azul o verde, y una ficha de maná azul o roja.", braevalar));
        createFicha(new FichaHabilidad(65, "CAUTIVAR", "Una vez por turno: Influencia 3, Influencia 2 en un sitio fortificado, o Influencia 4 en un Claro Mágico.", braevalar));
        createFicha(new FichaHabilidad(66, "RAYO BIFURCADO", "Una vez por turno: Ataque a Distancia de Fuego Frío 1 contra hasta 3 enemigos diferentes.", braevalar));
        createFicha(new FichaHabilidad(67, "CAMBIAFORMAS", "Una vez por turno: Una carta de Acción Básica que otorgue un número concreto de Movimiento/Ataque/Bloqueo, en vez de su acción habitual puede ofrecer la misma cantidad de cualquiera de las otras dos en su lugar (los tipos de elementos se conservan en Ataques y Bloqueos).", braevalar));
        createFicha(new FichaHabilidad(68, "CAMINOS SECRETOS", "Una vez por turno: Movimiento 1. Puedes entrar en montañas por un coste de Movimiento de 5 y se consideran un espacio seguro para ti. Si pagas 1 maná azul, puedes entrar en lagos por un coste de Movimiento de 2 en este turno y los lagos se consideran un espacio seguro para ti al final de este turno.", braevalar));
        createFicha(new FichaHabilidad(69, "REGENERAR", "Una vez por turno: Paga 1 maná de cualquier color y retira una carta de Herida de tu mano. Si usas maná verde, o si eres quien menos Fama tiene (sin contar empates), roba también 1 carta.", braevalar));
        createFicha(new FichaHabilidad(70, "APOYO DE LA NATURALEZA", "Una vez por Ronda: Reduce un ataque de un enemigo en 1, ese enemigo gana la aptitud de \"Pesado\" en este turno. Coloca esta ficha de Habilidad en el centro. Hasta el comienzo de tu siguiente turno, cualquier jugador puede devolvértela boca abajo, para reducir un ataque de un enemigo en 1 y darle a esa ficha de enemigo la aptitud de \"Pesado\" en este turno.", braevalar));

    }

}

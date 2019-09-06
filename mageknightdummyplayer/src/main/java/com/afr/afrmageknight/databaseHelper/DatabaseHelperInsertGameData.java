package com.afr.afrmageknight.databaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.afr.afrmageknight.model.Heroe;
import com.afr.afrmageknight.model.TipoPartida;

public class DatabaseHelperInsertGameData extends DatabaseHelper{

    //Nombre de las tablas
    public static final String PARTIDA_MODO_TABLE = "PARTIDA_MODO";
    public static final String PARTIDA_HEROES_JUGADOR_TABLE = "PARTIDA_HEROES_JUGADOR";
    public static final String PARTIDA_HEROE_DUMMY_TABLE = "PARTIDA_HEROE_DUMMY";
    public static final String PARTIDA_CARTAS_HEROE_DUMMY_TABLE = "PARTIDA_CARTAS_HEROE_DUMMY";
    public static final String PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE = "PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY";

    //Nombre de las columnas
    //PARTIDA_MODO_TABLE
    public static final String COL_1_PARTIDA_MODO_TABLE = "TIPO";

    // PARTIDA_HEROES_JUGADOR_TABLE
    public static final String COL_1_PARTIDA_HEROES_JUGADOR_TABLE = "NOMBRE";

    // PARTIDA_HEROE_DUMMY_TABLE
    public static final String COL_1_PARTIDA_HEROE_DUMMY_TABLE = "NOMBRE";

    // PARTIDA_CARTAS_HEROE_DUMMY_TABLE
    public static final String COL_1_PARTIDA_CARTAS_HEROE_DUMMY_TABLE = "NUMERO";

    //PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE
    public static final String COL_1_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE = "ID";

    //Constructor
    public DatabaseHelperInsertGameData(Context context) {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //PARTIDA_MODO_TABLE
        StringBuilder strSQLPartidaModoTable = new StringBuilder();

        strSQLPartidaModoTable.append("CREATE TABLE ").append(PARTIDA_MODO_TABLE).append(" (")
                .append(COL_1_PARTIDA_MODO_TABLE).append(" TEXT PRIMARY KEY")
                .append(")");

        Log.d("DATABASE", strSQLPartidaModoTable.toString());

        db.execSQL(strSQLPartidaModoTable.toString());


        //PARTIDA_HEROES_JUGADOR_TABLE
        StringBuilder strSQLPartidaHeroesJugadorTable = new StringBuilder();

        strSQLPartidaHeroesJugadorTable.append("CREATE TABLE ").append(PARTIDA_HEROES_JUGADOR_TABLE).append(" (")
                .append(COL_1_PARTIDA_HEROES_JUGADOR_TABLE).append(" TEXT PRIMARY KEY")
                .append(")");

        Log.d("DATABASE", strSQLPartidaHeroesJugadorTable.toString());

        db.execSQL(strSQLPartidaHeroesJugadorTable.toString());


        //PARTIDA_HEROE_DUMMY_TABLE
        StringBuilder strSQLPartidaHeroeDummyTable = new StringBuilder();

        strSQLPartidaHeroeDummyTable.append("CREATE TABLE ").append(PARTIDA_HEROE_DUMMY_TABLE).append(" (")
                .append(COL_1_PARTIDA_HEROE_DUMMY_TABLE).append(" TEXT PRIMARY KEY")
                .append(")");

        Log.d("DATABASE", strSQLPartidaHeroeDummyTable.toString());

        db.execSQL(strSQLPartidaHeroeDummyTable.toString());


        //PARTIDA_CARTAS_HEROE_DUMMY_TABLE
        StringBuilder strSQLPartidaCartasHeroeDummyTable = new StringBuilder();

        strSQLPartidaCartasHeroeDummyTable.append("CREATE TABLE ").append(PARTIDA_CARTAS_HEROE_DUMMY_TABLE).append(" (")
                .append(COL_1_PARTIDA_CARTAS_HEROE_DUMMY_TABLE).append(" INTEGER PRIMARY KEY")
                .append(")");

        Log.d("DATABASE", strSQLPartidaCartasHeroeDummyTable.toString());

        db.execSQL(strSQLPartidaCartasHeroeDummyTable.toString());


        //PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE
        StringBuilder strSQLPartidaFichasHabilidadHeroeDummyTable = new StringBuilder();

        strSQLPartidaFichasHabilidadHeroeDummyTable.append("CREATE TABLE ").append(PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE).append(" (")
                .append(COL_1_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE).append(" INTEGER PRIMARY KEY")
                .append(")");

        Log.d("DATABASE", strSQLPartidaFichasHabilidadHeroeDummyTable.toString());

        db.execSQL(strSQLPartidaFichasHabilidadHeroeDummyTable.toString());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + PARTIDA_MODO_TABLE);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + PARTIDA_HEROES_JUGADOR_TABLE);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + PARTIDA_HEROE_DUMMY_TABLE);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + PARTIDA_CARTAS_HEROE_DUMMY_TABLE);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE);
        onCreate(db);

    }

    @Override
    public void insertAllData() {

    }

    public boolean insertDataGameMode(TipoPartida tipoPartida){
        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_PARTIDA_MODO_TABLE, tipoPartida.toString());

        long resultado = db.insert(PARTIDA_MODO_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    public boolean insertDataHeroeSelectedByPlayer(Heroe heroe){
        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_PARTIDA_HEROES_JUGADOR_TABLE, heroe.getNombre());

        long resultado = db.insert(PARTIDA_HEROES_JUGADOR_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    public boolean insertDataHeroeSelectedByDummyPlayer(Heroe randomHeroeDummyPlayer){
        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_PARTIDA_HEROE_DUMMY_TABLE, randomHeroeDummyPlayer.getNombre());

        long resultado = db.insert(PARTIDA_HEROE_DUMMY_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    public void insertAllDataFromOneHeroeSelected(Heroe heroe) {

    }

}

package com.afr.afrmageknight.databaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.afr.afrmageknight.model.CartaAccionBasica;
import com.afr.afrmageknight.model.FichaHabilidad;
import com.afr.afrmageknight.model.Heroe;
import com.afr.afrmageknight.model.TipoPartida;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelperInsertGameData extends DatabaseHelper{

    //Nombre de las tablas
    public static final String PARTIDA_MODO_TABLE = "PARTIDA_MODO";
    public static final String PARTIDA_HEROES_JUGADOR_TABLE = "PARTIDA_HEROES_JUGADOR";
    public static final String PARTIDA_HEROE_DUMMY_TABLE = "PARTIDA_HEROE_DUMMY";
    public static final String PARTIDA_CARTAS_BASICAS_HEROE_DUMMY_TABLE = "PARTIDA_CARTAS_BASICAS_HEROE_DUMMY";
    public static final String PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE = "PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY";

    //Nombre de las columnas
    //PARTIDA_MODO_TABLE
    public static final String COL_1_PARTIDA_MODO_TABLE = "TIPO";

    // PARTIDA_HEROES_JUGADOR_TABLE
    public static final String COL_1_PARTIDA_HEROES_JUGADOR_TABLE = "NOMBRE";

    // PARTIDA_HEROE_DUMMY_TABLE
    public static final String COL_1_PARTIDA_HEROE_DUMMY_TABLE = "NOMBRE";

    // PARTIDA_CARTAS_HEROE_DUMMY_TABLE
    public static final String COL_1_PARTIDA_CARTAS_BASICAS_HEROE_DUMMY_TABLE = "NUMERO";
    public static final String COL_2_PARTIDA_CARTAS_BASICAS_HEROE_DUMMY_TABLE = "NOMBRE";
    public static final String COL_3_PARTIDA_CARTAS_BASICAS_HEROE_DUMMY_TABLE = "CRISTAL";
    public static final String COL_4_PARTIDA_CARTAS_BASICAS_HEROE_DUMMY_TABLE = "DESCRIPCION_BASICA";
    public static final String COL_5_PARTIDA_CARTAS_BASICAS_HEROE_DUMMY_TABLE = "DESCRIPCION_AVANZADA";
    public static final String COL_6_PARTIDA_CARTAS_BASICAS_HEROE_DUMMY_TABLE = "DESCARTADA";

    //PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE
    public static final String COL_1_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE = "ID";
    public static final String COL_2_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE = "NOMBRE";
    public static final String COL_3_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE = "DESCRIPCION";


    //Constructor
    public DatabaseHelperInsertGameData(Context context) {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d("DATABASE", "2º) - ONCREATE_GAME DATA");

        //PARTIDA_MODO_TABLE
        StringBuilder strSQLPartidaModoTable = new StringBuilder();

        strSQLPartidaModoTable.append("CREATE TABLE ").append(PARTIDA_MODO_TABLE).append(" (")
                .append(COL_1_PARTIDA_MODO_TABLE).append(" TEXT PRIMARY KEY")
                .append(")");

        Log.d("DATABASE", "PRIMERO - ONCREATE_GAME DATA - PARTIDA_MODO_TABLE");
        Log.d("DATABASE", strSQLPartidaModoTable.toString());

        db.execSQL(strSQLPartidaModoTable.toString());


        //PARTIDA_HEROES_JUGADOR_TABLE
        StringBuilder strSQLPartidaHeroesJugadorTable = new StringBuilder();

        strSQLPartidaHeroesJugadorTable.append("CREATE TABLE ").append(PARTIDA_HEROES_JUGADOR_TABLE).append(" (")
                .append(COL_1_PARTIDA_HEROES_JUGADOR_TABLE).append(" TEXT PRIMARY KEY")
                .append(")");

        Log.d("DATABASE", "SEGUNDO - ONCREATE_GAME DATA - PARTIDA_HEROES_JUGADOR_TABLE");
        Log.d("DATABASE", strSQLPartidaHeroesJugadorTable.toString());

        db.execSQL(strSQLPartidaHeroesJugadorTable.toString());


        //PARTIDA_HEROE_DUMMY_TABLE
        StringBuilder strSQLPartidaHeroeDummyTable = new StringBuilder();

        strSQLPartidaHeroeDummyTable.append("CREATE TABLE ").append(PARTIDA_HEROE_DUMMY_TABLE).append(" (")
                .append(COL_1_PARTIDA_HEROE_DUMMY_TABLE).append(" TEXT PRIMARY KEY")
                .append(")");

        Log.d("DATABASE", "TERCERO - ONCREATE_GAME DATA - PARTIDA_HEROE_DUMMY_TABLE");
        Log.d("DATABASE", strSQLPartidaHeroeDummyTable.toString());

        db.execSQL(strSQLPartidaHeroeDummyTable.toString());


        //PARTIDA_CARTAS_BASICAS_HEROE_DUMMY_TABLE
        StringBuilder strSQLPartidaCartasHeroeDummyTable = new StringBuilder();

        strSQLPartidaCartasHeroeDummyTable.append("CREATE TABLE ").append(PARTIDA_CARTAS_BASICAS_HEROE_DUMMY_TABLE).append(" (")
                .append(COL_1_PARTIDA_CARTAS_BASICAS_HEROE_DUMMY_TABLE).append(" INTEGER PRIMARY KEY,")
                .append(COL_2_PARTIDA_CARTAS_BASICAS_HEROE_DUMMY_TABLE).append(" TEXT NOT NULL,")
                .append(COL_3_PARTIDA_CARTAS_BASICAS_HEROE_DUMMY_TABLE).append(" TEXT NOT NULL,")
                .append(COL_4_PARTIDA_CARTAS_BASICAS_HEROE_DUMMY_TABLE).append(" TEXT NOT NULL,")
                .append(COL_5_PARTIDA_CARTAS_BASICAS_HEROE_DUMMY_TABLE).append(" TEXT NOT NULL,")
                .append(COL_6_PARTIDA_CARTAS_BASICAS_HEROE_DUMMY_TABLE).append(" BIT DEFAULT 0")
                .append(")");

        Log.d("DATABASE", "CUARTO - ONCREATE_GAME DATA - PARTIDA_CARTAS_BASICAS_HEROE_DUMMY_TABLE");
        Log.d("DATABASE", strSQLPartidaCartasHeroeDummyTable.toString());

        db.execSQL(strSQLPartidaCartasHeroeDummyTable.toString());


        //PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE
        StringBuilder strSQLPartidaFichasHabilidadHeroeDummyTable = new StringBuilder();

        strSQLPartidaFichasHabilidadHeroeDummyTable.append("CREATE TABLE ").append(PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE).append(" (")
                .append(COL_1_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE).append(" INTEGER PRIMARY KEY")
                .append(")");

        Log.d("DATABASE", "QUINTO - ONCREATE_GAME DATA - PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE");
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

        db.execSQL("DROP TABLE IF EXISTS " + PARTIDA_CARTAS_BASICAS_HEROE_DUMMY_TABLE);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE);
        onCreate(db);

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

    public boolean insertDataShuffledBasicCardsHeroeSelectedByDummyPlayer( int numeroCartaAccionBasica, String nombreCartaAccionBasica, String colorCartaAccionBasica, String descripcionBasicaCartaAccionBasica, String descripcionAvanzadaCartaAccionBasica, boolean isDescartadaCartaAccionBasica){
        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_PARTIDA_CARTAS_BASICAS_HEROE_DUMMY_TABLE, numeroCartaAccionBasica);
        contentValues.put(COL_2_PARTIDA_CARTAS_BASICAS_HEROE_DUMMY_TABLE, nombreCartaAccionBasica);
        contentValues.put(COL_3_PARTIDA_CARTAS_BASICAS_HEROE_DUMMY_TABLE, colorCartaAccionBasica);
        contentValues.put(COL_4_PARTIDA_CARTAS_BASICAS_HEROE_DUMMY_TABLE, descripcionBasicaCartaAccionBasica);
        contentValues.put(COL_5_PARTIDA_CARTAS_BASICAS_HEROE_DUMMY_TABLE, descripcionAvanzadaCartaAccionBasica);
        contentValues.put(COL_6_PARTIDA_CARTAS_BASICAS_HEROE_DUMMY_TABLE, isDescartadaCartaAccionBasica);

        long resultado = db.insert(PARTIDA_CARTAS_BASICAS_HEROE_DUMMY_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    public boolean insertDataShuffledSkillTokensHeroeSelectedByDummyPlayer( int numeroFichaHabilidad, String nombreFichaHabilidad, String descripcionFichaHabilidad){
        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE, numeroFichaHabilidad);
        contentValues.put(COL_2_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE, nombreFichaHabilidad);
        contentValues.put(COL_3_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE, descripcionFichaHabilidad);

        long resultado = db.insert(PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    public void createTipoPartida(TipoPartida tipoPartida){
        insertDataGameMode(tipoPartida);
    }

    public void createHeroeSelectedByPlayer(Heroe heroSelectedByPlayer){
        insertDataHeroeSelectedByPlayer(heroSelectedByPlayer);
    }

    public void createRandomHeroeDummyPlayer(Heroe randomHeroeDummyPlayer){
        insertDataHeroeSelectedByDummyPlayer(randomHeroeDummyPlayer);
    }
    
    public void createAllBasicCardsFromDummyPlayer(List<CartaAccionBasica> cartasAccionBasicasBarajadasDummyPlayer){
        int i = 0;
        while(i<cartasAccionBasicasBarajadasDummyPlayer.size()){
            int numeroCartaAccionBasica = cartasAccionBasicasBarajadasDummyPlayer.get(i).getNumero();
            String nombreCartaAccionBasica = cartasAccionBasicasBarajadasDummyPlayer.get(i).getNombre();
            String colorCartaAccionBasica = cartasAccionBasicasBarajadasDummyPlayer.get(i).getColor().toString();
            String descripcionBasicaCartaAccionBasica = cartasAccionBasicasBarajadasDummyPlayer.get(i).getDescripcionBasica();
            String descripcionAvanzadaCartaAccionBasica = cartasAccionBasicasBarajadasDummyPlayer.get(i).getDescripcionAvanzada();
            boolean isDescartadaCartaAccionBasica = cartasAccionBasicasBarajadasDummyPlayer.get(i).isDescartada();

            insertDataShuffledBasicCardsHeroeSelectedByDummyPlayer(numeroCartaAccionBasica, nombreCartaAccionBasica, colorCartaAccionBasica, descripcionBasicaCartaAccionBasica, descripcionAvanzadaCartaAccionBasica, isDescartadaCartaAccionBasica);
            ++i;
        }
    }

    public void createAllSkillTokensFromDummyPlayer(List<FichaHabilidad> fichaHabilidadesBarajadasDummyPlayer){
        int i = 0;
        while(i<fichaHabilidadesBarajadasDummyPlayer.size()){
            int numeroFichaHabilidad = fichaHabilidadesBarajadasDummyPlayer.get(i).getIdFicha();
            String nombreFichaHabilidad = fichaHabilidadesBarajadasDummyPlayer.get(i).getNombre();
            String descripcionFichaHabilidad = fichaHabilidadesBarajadasDummyPlayer.get(i).getDescripcion();

            insertDataShuffledSkillTokensHeroeSelectedByDummyPlayer(numeroFichaHabilidad, nombreFichaHabilidad, descripcionFichaHabilidad);
            ++i;
        }
    }

    @Override
    public void insertAllData() {

    }

    @Override
    public void insertAllGameData(TipoPartida tipoPartida, Heroe heroeSelectedByPlayer, Heroe randomHeroeDummyPlayer, List<CartaAccionBasica> cartasAccionBasicasBarajadasDummyPlayer, List<FichaHabilidad> fichaHabilidadesBarajadasDummyPlayer) {

        createTipoPartida(tipoPartida);

        createHeroeSelectedByPlayer(heroeSelectedByPlayer);

        createRandomHeroeDummyPlayer(randomHeroeDummyPlayer);

        createAllBasicCardsFromDummyPlayer(cartasAccionBasicasBarajadasDummyPlayer);

        createAllSkillTokensFromDummyPlayer(fichaHabilidadesBarajadasDummyPlayer);

    }


}

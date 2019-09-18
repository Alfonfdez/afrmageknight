package com.afr.afrmageknight.databaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.afr.afrmageknight.model.CartaAccionBasica;
import com.afr.afrmageknight.model.Cristal;
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
    public static final String PARTIDA_CARTAS_HEROE_DUMMY_TABLE = "PARTIDA_CARTAS_HEROE_DUMMY";
    public static final String PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE = "PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY";
    public static final String PARTIDA_CRISTALES_HEROE_DUMMY_TABLE = "PARTIDA_CRISTALES_HEROE_DUMMY";

    //Nombre de las columnas
    //PARTIDA_MODO_TABLE
    public static final String COL_1_PARTIDA_MODO_TABLE = "TIPO";

    // PARTIDA_HEROES_JUGADOR_TABLE
    public static final String COL_1_PARTIDA_HEROES_JUGADOR_TABLE = "NOMBRE";

    // PARTIDA_HEROE_DUMMY_TABLE
    public static final String COL_1_PARTIDA_HEROE_DUMMY_TABLE = "NOMBRE";

    // PARTIDA_CARTAS_HEROE_DUMMY_TABLE
    public static final String COL_1_PARTIDA_CARTAS_HEROE_DUMMY_TABLE = "NUMERO";
    public static final String COL_2_PARTIDA_CARTAS_HEROE_DUMMY_TABLE = "NOMBRE";
    public static final String COL_3_PARTIDA_CARTAS_HEROE_DUMMY_TABLE = "DESCARTADA";
    public static final String COL_4_PARTIDA_CARTAS_HEROE_DUMMY_TABLE = "COLOR";
    public static final String COL_5_PARTIDA_CARTAS_HEROE_DUMMY_TABLE = "COLOR_SECUNDARIO";
    public static final String COL_6_PARTIDA_CARTAS_HEROE_DUMMY_TABLE = "DESCRIPCION_BASICA";
    public static final String COL_7_PARTIDA_CARTAS_HEROE_DUMMY_TABLE = "DESCRIPCION_AVANZADA";
    public static final String COL_8_PARTIDA_CARTAS_HEROE_DUMMY_TABLE = "HEROE_DUMMY";
    public static final String COL_9_PARTIDA_CARTAS_HEROE_DUMMY_TABLE = "INDICE";


    //PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE
    public static final String COL_1_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE = "ID_FICHA";
    public static final String COL_2_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE = "NOMBRE";
    public static final String COL_3_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE = "DESCRIPCION";
    public static final String COL_4_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE = "DESCARTADA";
    public static final String COL_5_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE = "HEROE_DUMMY";
    public static final String COL_6_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE = "INDICE";

    // PARTIDA_CRISTALES_HEROE_DUMMY_TABLE
    public static final String COL_1_PARTIDA_CRISTALES_HEROE_DUMMY_TABLE = "CRISTAL";


    //Constructor
    public DatabaseHelperInsertGameData(Context context) {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void insertAllData() {

    }

    //Métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
    public boolean insertDataGameMode(String tipoPartida){
        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_PARTIDA_MODO_TABLE, tipoPartida);

        long resultado = db.insert(PARTIDA_MODO_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    //Métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
    public boolean insertDataHeroeSelectedByPlayer(String heroeNameSelectedByPlayer){
        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_PARTIDA_HEROES_JUGADOR_TABLE, heroeNameSelectedByPlayer);

        long resultado = db.insert(PARTIDA_HEROES_JUGADOR_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    //Métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
    public boolean insertDataHeroeSelectedByDummyPlayer(String randomHeroeDummyPlayer){
        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_PARTIDA_HEROE_DUMMY_TABLE, randomHeroeDummyPlayer);

        long resultado = db.insert(PARTIDA_HEROE_DUMMY_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    //Métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
    public boolean insertDataCardDummyPlayer( int numeroCartaAccion, String nombreCartaAccion, boolean isDescartadaCartaAccion, String colorCartaAccion, String colorCartaAccionAvanzadaEspecial, String descripcionBasicaCartaAccion, String descripcionAvanzadaCartaAccion, String heroNameDummyPlayer, int numeroIndice){
        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_PARTIDA_CARTAS_HEROE_DUMMY_TABLE, numeroCartaAccion);
        contentValues.put(COL_2_PARTIDA_CARTAS_HEROE_DUMMY_TABLE, nombreCartaAccion);
        contentValues.put(COL_3_PARTIDA_CARTAS_HEROE_DUMMY_TABLE, isDescartadaCartaAccion);
        contentValues.put(COL_4_PARTIDA_CARTAS_HEROE_DUMMY_TABLE, colorCartaAccion);
        contentValues.put(COL_5_PARTIDA_CARTAS_HEROE_DUMMY_TABLE, colorCartaAccionAvanzadaEspecial);
        contentValues.put(COL_6_PARTIDA_CARTAS_HEROE_DUMMY_TABLE, descripcionBasicaCartaAccion);
        contentValues.put(COL_7_PARTIDA_CARTAS_HEROE_DUMMY_TABLE, descripcionAvanzadaCartaAccion);
        contentValues.put(COL_8_PARTIDA_CARTAS_HEROE_DUMMY_TABLE, heroNameDummyPlayer);
        contentValues.put(COL_9_PARTIDA_CARTAS_HEROE_DUMMY_TABLE, numeroIndice);

        long resultado = db.insert(PARTIDA_CARTAS_HEROE_DUMMY_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    //Métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
    public boolean insertDataShuffledSkillTokenDummyPlayer( int numeroFichaHabilidad, String nombreFichaHabilidad, String descripcionFichaHabilidad, boolean isDescartadaFichaHabilidad, String heroNameDummyPlayer, int numeroIndice){
        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE, numeroFichaHabilidad);
        contentValues.put(COL_2_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE, nombreFichaHabilidad);
        contentValues.put(COL_3_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE, descripcionFichaHabilidad);
        contentValues.put(COL_4_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE, isDescartadaFichaHabilidad);
        contentValues.put(COL_5_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE, heroNameDummyPlayer);
        contentValues.put(COL_6_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE, numeroIndice);

        long resultado = db.insert(PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    //Métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
    public boolean insertDataCristalesDummyPlayer( String cristal){
        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_PARTIDA_CRISTALES_HEROE_DUMMY_TABLE, cristal);

        long resultado = db.insert(PARTIDA_CRISTALES_HEROE_DUMMY_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    public void deleteGameData(){
        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        db.delete(PARTIDA_MODO_TABLE,null, null);
        db.delete(PARTIDA_HEROES_JUGADOR_TABLE,null, null);
        db.delete(PARTIDA_HEROE_DUMMY_TABLE,null, null);
        db.delete(PARTIDA_CARTAS_HEROE_DUMMY_TABLE,null, null);
        db.delete(PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE,null, null);
        db.delete(PARTIDA_CRISTALES_HEROE_DUMMY_TABLE,null, null);
    }




    //Insertar datos en su correspondiente tabla
    public TipoPartida createTipoPartida(String tipoPartida){
        insertDataGameMode(tipoPartida);
        return null;
    }

    public Heroe createHeroeSelectedByPlayer(String heroSelectedByPlayer){
        insertDataHeroeSelectedByPlayer(heroSelectedByPlayer);
        return null;
    }

    public Heroe createRandomHeroeDummyPlayer(String randomHeroeDummyPlayer){
        insertDataHeroeSelectedByDummyPlayer(randomHeroeDummyPlayer);
        return null;
    }

    public CartaAccionBasica createBasicCardFromDummyPlayer(int numeroCartaAccionBasica, String nombreCartaAccionBasica, boolean isDescartadaCartaAccionBasica, String colorCartaAccionBasica, String descripcionBasicaCartaAccionBasica, String descripcionAvanzadaCartaAccionBasica, String heroNameDummy, int numeroIndice){
        insertDataCardDummyPlayer(numeroCartaAccionBasica, nombreCartaAccionBasica, isDescartadaCartaAccionBasica, colorCartaAccionBasica, null, descripcionBasicaCartaAccionBasica, descripcionAvanzadaCartaAccionBasica, heroNameDummy, numeroIndice);
        return null;
    }

    public FichaHabilidad createSkillTokenFromDummyPlayer(int numeroFichaHabilidad, String nombreFichaHabilidad, String descripcionFichaHabilidad, boolean isDescartadaFichaHabilidad, String heroNameDummy, int numeroIndice){
        insertDataShuffledSkillTokenDummyPlayer(numeroFichaHabilidad, nombreFichaHabilidad, descripcionFichaHabilidad, isDescartadaFichaHabilidad, heroNameDummy, numeroIndice);
        return null;
    }

    public Cristal createCristal(String cristal){
        insertDataCristalesDummyPlayer(cristal);
        return null;
    }

    //Métodos
    public void insertAllGameData(TipoPartida tipoPartida, Heroe heroeSelectedByPlayer, Heroe randomHeroeDummyPlayer, List<CartaAccionBasica> cartasAccionBasicasBarajadasDummyPlayer, List<FichaHabilidad> fichaHabilidadesBarajadasDummyPlayer, List<Cristal> cristalesDummyPlayer) {

        createTipoPartida(tipoPartida.toString());

        createHeroeSelectedByPlayer(heroeSelectedByPlayer.getNombre());

        createRandomHeroeDummyPlayer(randomHeroeDummyPlayer.getNombre());

        int i = 0;
        for(CartaAccionBasica cartaAccionBasica: cartasAccionBasicasBarajadasDummyPlayer){
            Log.d("DATABASE","INSERT DUMMY PLAYER RANDOM BASIC ACTION CARDS -> Numero: "+cartaAccionBasica.getNumero()+" - Nombre: "+cartaAccionBasica.getNombre());
            createBasicCardFromDummyPlayer(cartaAccionBasica.getNumero(), cartaAccionBasica.getNombre(), cartaAccionBasica.isDescartada(), cartaAccionBasica.getColor().toString(), cartaAccionBasica.getDescripcionBasica(), cartaAccionBasica.getDescripcionAvanzada(), randomHeroeDummyPlayer.getNombre(), i);
            ++i;
        }

        int j = 0;
        for(FichaHabilidad fichaHabilidad: fichaHabilidadesBarajadasDummyPlayer){
            Log.d("DATABASE","INSERT DUMMY PLAYER RANDOM SKILL TOKENS -> Numero: "+fichaHabilidad.getIdFicha()+" - Nombre: "+fichaHabilidad.getNombre());
            createSkillTokenFromDummyPlayer(fichaHabilidad.getIdFicha(), fichaHabilidad.getNombre(), fichaHabilidad.getDescripcion(), fichaHabilidad.isDescartada(), randomHeroeDummyPlayer.getNombre(), j);
            ++j;
        }

        for(Cristal cristal: cristalesDummyPlayer){
            createCristal(cristal.toString());
        }

    }

}

package com.afr.afrmageknight.databaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.afr.afrmageknight.model.CartaAccionAvanzada;
import com.afr.afrmageknight.model.CartaAccionAvanzadaEspecial;
import com.afr.afrmageknight.model.CartaAccionBasica;
import com.afr.afrmageknight.model.CartaTactica;
import com.afr.afrmageknight.model.Cristal;
import com.afr.afrmageknight.model.FichaHabilidad;
import com.afr.afrmageknight.model.Heroe;
import com.afr.afrmageknight.model.TipoEstado;
import com.afr.afrmageknight.model.TipoPartida;
import com.afr.afrmageknight.model.TipoRonda;

import java.util.List;

public class SQLiteDatabaseHelper extends AbstractSQLiteDatabaseHelper {

    //Constructor
    public SQLiteDatabaseHelper(Context context) {
        super(context);
    }

    //Métodos a sobreescribir de la superclase
    @Override
    public void onCreate(SQLiteDatabase db) {
        super.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
    }

    // ******************************************************************

    //Métodos públicos
    public void insertAllGameDataSolitaire(TipoEstado estadoPartida, TipoRonda rondaPartida, TipoPartida tipoPartida, List<CartaTactica> cartasTacticas, List<CartaAccionAvanzada> cartasAccionAvanzadas, List<CartaAccionAvanzadaEspecial> cartasAccionAvanzadaEspeciales, Heroe heroeSelectedByPlayer, Heroe randomHeroeDummyPlayer, List<CartaAccionBasica> cartasAccionBasicasBarajadasDummyPlayer, List<FichaHabilidad> fichaHabilidadesBarajadasDummyPlayer, List<Cristal> cristalesDummyPlayer) {
        createEstadoPartida(estadoPartida.toString(), rondaPartida.toString(), true, false, 0, 0);
        createTipoPartida(tipoPartida.toString());

        for(CartaTactica cartaTactica: cartasTacticas){
            createCartaTacticaPartida(cartaTactica);
        }

        for(CartaAccionAvanzada cartaAccionAvanzada : cartasAccionAvanzadas){
            createCartaAccionAvanzada(cartaAccionAvanzada);
        }

        for(CartaAccionAvanzadaEspecial cartaAccionAvanzadaEspecial : cartasAccionAvanzadaEspeciales){
            createCartaAccionAvanzadaEspecial(cartaAccionAvanzadaEspecial);
        }

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

    public void insertAllGameDataCooperative(TipoEstado estadoPartida, TipoRonda rondaPartida, TipoPartida tipoPartida, List<CartaTactica> cartasTacticas, List<CartaAccionAvanzada> cartasAccionAvanzadas, List<CartaAccionAvanzadaEspecial> cartasAccionAvanzadaEspeciales, List<Heroe> heroesSelectedByPlayer, Heroe randomHeroeDummyPlayer, List<CartaAccionBasica> cartasAccionBasicasBarajadasDummyPlayer, List<Cristal> cristalesDummyPlayer) {
        createEstadoPartida(estadoPartida.toString(), rondaPartida.toString(), true, false, 0, 0);
        createTipoPartida(tipoPartida.toString());

        for(CartaTactica cartaTactica: cartasTacticas){
            createCartaTacticaPartida(cartaTactica);
        }

        for(CartaAccionAvanzada cartaAccionAvanzada: cartasAccionAvanzadas){
            createCartaAccionAvanzada(cartaAccionAvanzada);
        }

        for(CartaAccionAvanzadaEspecial cartaAccionAvanzadaEspecial : cartasAccionAvanzadaEspeciales){
            createCartaAccionAvanzadaEspecial(cartaAccionAvanzadaEspecial);
        }

        for(Heroe heroe: heroesSelectedByPlayer){
            createHeroeSelectedByPlayer(heroe.getNombre());
        }

        createRandomHeroeDummyPlayer(randomHeroeDummyPlayer.getNombre());

        int i = 0;
        for(CartaAccionBasica cartaAccionBasica: cartasAccionBasicasBarajadasDummyPlayer){
            Log.d("DATABASE","INSERT DUMMY PLAYER RANDOM BASIC ACTION CARDS -> Numero: "+cartaAccionBasica.getNumero()+" - Nombre: "+cartaAccionBasica.getNombre());
            createBasicCardFromDummyPlayer(cartaAccionBasica.getNumero(), cartaAccionBasica.getNombre(), cartaAccionBasica.isDescartada(), cartaAccionBasica.getColor().toString(), cartaAccionBasica.getDescripcionBasica(), cartaAccionBasica.getDescripcionAvanzada(), randomHeroeDummyPlayer.getNombre(), i);
            ++i;
        }

        for(Cristal cristal: cristalesDummyPlayer){
            createCristal(cristal.toString());
        }
    }

    // ******************************************************************

    //Insertar datos en su correspondiente tabla
    private void createEstadoPartida(String estadoPartida, String rondaPartida, boolean esRondaInicio, boolean esRondaFinalizada, int turno, int experiencia){
        insertDataGameStatus(estadoPartida, rondaPartida, esRondaInicio, esRondaFinalizada, turno, experiencia);
    }

    private void createTipoPartida(String tipoPartida){
        insertDataGameMode(tipoPartida);
    }

    private void createCartaTacticaPartida(CartaTactica cartaTactica){
        insertDataGameTacticCard(cartaTactica.getNumero(), cartaTactica.getNombre(),false, cartaTactica.getTipoTactica().toString(), cartaTactica.getNumeroOrden(),cartaTactica.getDescripcion());
    }

    private void createCartaAccionAvanzada(CartaAccionAvanzada cartaAccionAvanzada){
        insertDataGameAdvancedActionAndSpecialCard(cartaAccionAvanzada.getNumero(), cartaAccionAvanzada.getNombre(), cartaAccionAvanzada.isDescartada(), cartaAccionAvanzada.getColor().toString(), null, cartaAccionAvanzada.getDescripcionBasica(), cartaAccionAvanzada.getDescripcionBasica(), false);
    }

    private void createCartaAccionAvanzadaEspecial(CartaAccionAvanzadaEspecial cartaAccionAvanzadaEspecial){
        insertDataGameAdvancedActionAndSpecialCard(cartaAccionAvanzadaEspecial.getNumero(), cartaAccionAvanzadaEspecial.getNombre(), cartaAccionAvanzadaEspecial.isDescartada(), cartaAccionAvanzadaEspecial.getColor().toString(),  cartaAccionAvanzadaEspecial.getColorSecundario().toString(), cartaAccionAvanzadaEspecial.getDescripcionBasica(), cartaAccionAvanzadaEspecial.getDescripcionBasica(), true);
    }

    private void createHeroeSelectedByPlayer(String heroSelectedByPlayer){
        insertDataHeroeSelectedByPlayer(heroSelectedByPlayer);
    }

    private void createRandomHeroeDummyPlayer(String randomHeroeDummyPlayer){
        insertDataHeroeSelectedByDummyPlayer(randomHeroeDummyPlayer);
    }

    private void createBasicCardFromDummyPlayer(int numeroCartaAccionBasica, String nombreCartaAccionBasica, boolean isDescartadaCartaAccionBasica, String colorCartaAccionBasica, String descripcionBasicaCartaAccionBasica, String descripcionAvanzadaCartaAccionBasica, String heroNameDummy, int numeroIndice){
        insertDataCardDummyPlayer(numeroCartaAccionBasica, nombreCartaAccionBasica, isDescartadaCartaAccionBasica, colorCartaAccionBasica, null, descripcionBasicaCartaAccionBasica, descripcionAvanzadaCartaAccionBasica, heroNameDummy, numeroIndice);
    }

    private void createSkillTokenFromDummyPlayer(int numeroFichaHabilidad, String nombreFichaHabilidad, String descripcionFichaHabilidad, boolean isDescartadaFichaHabilidad, String heroNameDummy, int numeroIndice){
        insertDataShuffledSkillTokenDummyPlayer(numeroFichaHabilidad, nombreFichaHabilidad, descripcionFichaHabilidad, isDescartadaFichaHabilidad, heroNameDummy, numeroIndice);
    }

    private void createCristal(String cristal){
        insertDataCristalesDummyPlayer(cristal);
    }

    // ******************************************************************

    public void createGameInformation(String gameInformation){
        insertDataGameInformation(gameInformation);
    }

    public void createGameAddedAdvancedActionCard(int numeroCarta, String nombre, boolean esDescartada, String colorCristal, String colorSecundarioCristal, String descripcionBasica, String descripcionAvanzada, String heroe, int indice){
        insertDataGameAddedAdvancedActionCard(numeroCarta, nombre, esDescartada, colorCristal, colorSecundarioCristal, descripcionBasica, descripcionAvanzada, heroe, indice);
    }

    public void createGameAddedCristal(String nombreCristal){
        insertDataGameAddedCristal(nombreCristal);
    }

    // ******************************************************************

    //Modificar datos en su correspondiente tabla
    public void updateGameTacticCardAvailabilityByName(String nombreCartaTactica, boolean esDescartada){
        updateDataGameTacticCardAvailabilityByName(nombreCartaTactica, esDescartada);
    }

    public void updateGameAdvancedActionCardAvailabilityByCardNumber(int numeroCartaAccionAvanzada, boolean esDescartada){
        updateDataGameAdvancedActionCardAvailabilityByCardNumber(numeroCartaAccionAvanzada, esDescartada);
    }

    public void updateGameDummyPlayerCardAvailabilityByCardNumber(int numeroCarta, boolean esDescartada){
        updateDataGameDummyPlayerCardAvailabilityByCardNumber(numeroCarta, esDescartada);
    }

    public void updateGameStatus(String estadoPartida){
        updateDataGameGameStatus(estadoPartida);
    }

    public void updateGameRound(String estadoRondaSiguiente){
        updateDataGameGameRound(estadoRondaSiguiente);
    }

    public void updateGameStatusRoundBeginning(boolean esRondaInicio){
        updateDataGameGameStatusRoundBeginning(esRondaInicio);
    }

    public void updateGameStatusRoundEnding(boolean esRondaFinalizada){
        updateDataGameGameStatusRoundEnding(esRondaFinalizada);
    }

    public void updateGameStatusRoundTurn(int numeroTurno){
        updateDataGameGameStatusRoundTurn(numeroTurno);
    }

    public void updateGameStatusPlayerExperience(int experienciaJugador){
        updateDataGameGameStatusPlayerExperience(experienciaJugador);
    }

    public void updateGameShuffledCardsDummyPlayer(int numeroCartaBarajadoJugadorVirtual, int indice, boolean esDescartada){
        updateDataGameShuffledCardsDummyPlayer(numeroCartaBarajadoJugadorVirtual, indice, esDescartada);
    }

    // ******************************************************************

    //Métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
    private boolean insertDataGameStatus(String estadoPartida, String rondaPartida, boolean esRondaInicio, boolean esRondaFinalizada, int turno, int experiencia){
        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_PARTIDA_DATOS_TABLE, estadoPartida);
        contentValues.put(COL_2_PARTIDA_DATOS_TABLE, rondaPartida);
        contentValues.put(COL_3_PARTIDA_DATOS_TABLE, esRondaInicio);
        contentValues.put(COL_4_PARTIDA_DATOS_TABLE, esRondaFinalizada);
        contentValues.put(COL_5_PARTIDA_DATOS_TABLE, turno);
        contentValues.put(COL_6_PARTIDA_DATOS_TABLE, experiencia);

        long resultado = db.insert(PARTIDA_DATOS_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    //Métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
    private boolean insertDataGameMode(String tipoPartida){
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
    private boolean insertDataGameInformation(String informacionPartida){
        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_PARTIDA_INFORMACION_RONDA_TABLE, informacionPartida);

        long resultado = db.insert(PARTIDA_INFORMACION_RONDA_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }


    //Métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
    private boolean insertDataGameTacticCard(int numero, String nombre, boolean isDescartadaCartaTactica, String tipoTactica, int numeroOrden, String descripcion){
        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_PARTIDA_CARTAS_TACTICAS_TABLE, numero);
        contentValues.put(COL_2_PARTIDA_CARTAS_TACTICAS_TABLE, nombre);
        contentValues.put(COL_3_PARTIDA_CARTAS_TACTICAS_TABLE, isDescartadaCartaTactica);
        contentValues.put(COL_4_PARTIDA_CARTAS_TACTICAS_TABLE, tipoTactica);
        contentValues.put(COL_5_PARTIDA_CARTAS_TACTICAS_TABLE, numeroOrden);
        contentValues.put(COL_6_PARTIDA_CARTAS_TACTICAS_TABLE, descripcion);

        long resultado = db.insert(PARTIDA_CARTAS_TACTICAS_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    //Métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
    private boolean insertDataGameAdvancedActionAndSpecialCard(int numero, String nombre, boolean isDescartadaCartaTactica, String colorCristal, String colorSecundarioCristal, String descripcionBasica, String descripcionAvanzada, boolean esEspecial){
        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE, numero);
        contentValues.put(COL_2_PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE, nombre);
        contentValues.put(COL_3_PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE, isDescartadaCartaTactica);
        contentValues.put(COL_4_PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE, colorCristal);
        contentValues.put(COL_5_PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE, colorSecundarioCristal);
        contentValues.put(COL_6_PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE, descripcionBasica);
        contentValues.put(COL_7_PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE, descripcionAvanzada);
        contentValues.put(COL_8_PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE, esEspecial);

        long resultado = db.insert(PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    //Métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
    private boolean insertDataHeroeSelectedByPlayer(String heroeNameSelectedByPlayer){
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
    private boolean insertDataHeroeSelectedByDummyPlayer(String randomHeroeDummyPlayer){
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
    private boolean insertDataCardDummyPlayer( int numeroCartaAccion, String nombreCartaAccion, boolean isDescartadaCartaAccion, String colorCartaAccion, String colorCartaAccionAvanzadaEspecial, String descripcionBasicaCartaAccion, String descripcionAvanzadaCartaAccion, String heroNameDummyPlayer, int numeroIndice){
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
    private boolean insertDataShuffledSkillTokenDummyPlayer( int numeroFichaHabilidad, String nombreFichaHabilidad, String descripcionFichaHabilidad, boolean isDescartadaFichaHabilidad, String heroNameDummyPlayer, int numeroIndice){
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
    private boolean insertDataCristalesDummyPlayer( String cristal){
        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_PARTIDA_CRISTALES_HEROE_DUMMY_TABLE, cristal);

        long resultado = db.insert(PARTIDA_CRISTALES_HEROE_DUMMY_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    // ******************************************************************

    public void deleteGameData(){
        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        db.delete(PARTIDA_DATOS_TABLE,null, null);
        db.delete(PARTIDA_MODO_TABLE,null, null);
        db.delete(PARTIDA_INFORMACION_RONDA_TABLE,null, null);
        db.delete(PARTIDA_CARTAS_TACTICAS_TABLE,null, null);
        db.delete(PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE,null, null);
        db.delete(PARTIDA_HEROES_JUGADOR_TABLE,null, null);
        db.delete(PARTIDA_HEROE_DUMMY_TABLE,null, null);
        db.delete(PARTIDA_CARTAS_HEROE_DUMMY_TABLE,null, null);
        db.delete(PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE,null, null);
        db.delete(PARTIDA_CRISTALES_HEROE_DUMMY_TABLE,null, null);
    }

    // ******************************************************************

    private boolean insertDataGameAddedAdvancedActionCard(int numeroCarta, String nombre, boolean esDescartada, String colorCristal, String colorSecundarioCristal, String descripcionBasica, String descripcionAvanzada, String heroe, int indice) {
        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_PARTIDA_CARTAS_HEROE_DUMMY_TABLE, numeroCarta);
        contentValues.put(COL_2_PARTIDA_CARTAS_HEROE_DUMMY_TABLE, nombre);
        contentValues.put(COL_3_PARTIDA_CARTAS_HEROE_DUMMY_TABLE, esDescartada);
        contentValues.put(COL_4_PARTIDA_CARTAS_HEROE_DUMMY_TABLE, colorCristal);
        contentValues.put(COL_5_PARTIDA_CARTAS_HEROE_DUMMY_TABLE, colorSecundarioCristal);
        contentValues.put(COL_6_PARTIDA_CARTAS_HEROE_DUMMY_TABLE, descripcionBasica);
        contentValues.put(COL_7_PARTIDA_CARTAS_HEROE_DUMMY_TABLE, descripcionAvanzada);
        contentValues.put(COL_8_PARTIDA_CARTAS_HEROE_DUMMY_TABLE, heroe);
        contentValues.put(COL_9_PARTIDA_CARTAS_HEROE_DUMMY_TABLE, indice);

        long resultado = db.insert(PARTIDA_CARTAS_HEROE_DUMMY_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    private boolean insertDataGameAddedCristal(String nombreCristal) {
        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_PARTIDA_CRISTALES_HEROE_DUMMY_TABLE, nombreCristal);

        long resultado = db.insert(PARTIDA_CRISTALES_HEROE_DUMMY_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    // ******************************************************************

    private boolean updateDataGameTacticCardAvailabilityByName(String nombreCartaTactica, boolean esDescartada) {
        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_3_PARTIDA_CARTAS_TACTICAS_TABLE, esDescartada);

        String[] args = new String[]{nombreCartaTactica};
        long resultado = db.update(PARTIDA_CARTAS_TACTICAS_TABLE, contentValues, COL_2_PARTIDA_CARTAS_TACTICAS_TABLE+"=?", args);
        //long resultado = db.update(TABLE_NAME, contentValues (valores a modificar), WHERE FILA = args, args);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    private boolean updateDataGameAdvancedActionCardAvailabilityByCardNumber(int numeroCartaAccionAvanzada, boolean esDescartada) {
        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_3_PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE, esDescartada);

        String[] args = new String[]{Integer.toString(numeroCartaAccionAvanzada)};
        long resultado = db.update(PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE, contentValues, COL_1_PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE+"=?", args);
        //long resultado = db.update(TABLE_NAME, contentValues (valores a modificar), WHERE FILA = args, args);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    private boolean updateDataGameDummyPlayerCardAvailabilityByCardNumber(int numeroCarta, boolean esDescartada) {
        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_3_PARTIDA_CARTAS_HEROE_DUMMY_TABLE, esDescartada);

        String[] args = new String[]{Integer.toString(numeroCarta)};
        long resultado = db.update(PARTIDA_CARTAS_HEROE_DUMMY_TABLE, contentValues, COL_1_PARTIDA_CARTAS_HEROE_DUMMY_TABLE+"=?", args);
        //long resultado = db.update(TABLE_NAME, contentValues (valores a modificar), WHERE FILA = args, args);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    private boolean updateDataGameGameStatus(String estadoPartida) {
        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_PARTIDA_DATOS_TABLE, estadoPartida);

        long resultado = db.update(PARTIDA_DATOS_TABLE, contentValues, null, null);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    private boolean updateDataGameGameRound(String estadoRondaSiguiente) {
        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2_PARTIDA_DATOS_TABLE, estadoRondaSiguiente);

        long resultado = db.update(PARTIDA_DATOS_TABLE, contentValues, null, null);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    private boolean updateDataGameGameStatusRoundBeginning(boolean esRondaInicio) {
        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_3_PARTIDA_DATOS_TABLE, esRondaInicio);

        long resultado = db.update(PARTIDA_DATOS_TABLE, contentValues, null, null);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    private boolean updateDataGameGameStatusRoundEnding(boolean esRondaFinalizada) {
        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_4_PARTIDA_DATOS_TABLE, esRondaFinalizada);

        long resultado = db.update(PARTIDA_DATOS_TABLE, contentValues, null, null);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }


    private boolean updateDataGameGameStatusRoundTurn(int numeroTurno) {
        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_5_PARTIDA_DATOS_TABLE, numeroTurno);

        long resultado = db.update(PARTIDA_DATOS_TABLE, contentValues, null, null);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    private boolean updateDataGameGameStatusPlayerExperience(int experienciaJugador) {
        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_6_PARTIDA_DATOS_TABLE, experienciaJugador);

        long resultado = db.update(PARTIDA_DATOS_TABLE, contentValues, null, null);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    private boolean updateDataGameShuffledCardsDummyPlayer(int numeroCartaBarajadoJugadorVirtual, int indice, boolean esDescartada) {
        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_3_PARTIDA_CARTAS_HEROE_DUMMY_TABLE, esDescartada);
        contentValues.put(COL_9_PARTIDA_CARTAS_HEROE_DUMMY_TABLE, indice);

        String[] args = new String[]{Integer.toString(numeroCartaBarajadoJugadorVirtual)};
        long resultado = db.update(PARTIDA_CARTAS_HEROE_DUMMY_TABLE, contentValues, COL_1_PARTIDA_CARTAS_HEROE_DUMMY_TABLE+"=?", args);
        //long resultado = db.update(TABLE_NAME, contentValues (valores a modificar), WHERE FILA = args, args);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }


}

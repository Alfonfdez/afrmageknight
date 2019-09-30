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
import com.afr.afrmageknight.model.CartaAccionAvanzadaEspecial;
import com.afr.afrmageknight.model.CartaAccionBasica;
import com.afr.afrmageknight.model.CartaTactica;
import com.afr.afrmageknight.model.Cristal;
import com.afr.afrmageknight.model.FichaHabilidad;
import com.afr.afrmageknight.model.CartaAccionHechizo;
import com.afr.afrmageknight.model.Heroe;
import com.afr.afrmageknight.model.TipoEstado;
import com.afr.afrmageknight.model.TipoTactica;

import java.util.ArrayList;
import java.util.Collections;

public abstract class AbstractSQLiteDatabaseHelper extends SQLiteOpenHelper {

    // Nombre de la base de datos
    public static final String DATABASE_NAME = "mageknight.db";

    // Nombre de las tablas
    protected static final String HEROES_TABLE = "HEROES";
    protected static final String CRISTALES_TABLE = "CRISTALES";
    protected static final String HEROES_CRISTALES_TABLE = "HEROES_CRISTALES_TABLE";
    protected static final String CARTAS_TABLE = "CARTAS";
    protected static final String CARTAS_ACCIONES_TABLE = "CARTAS_ACCIONES";
    protected static final String CARTAS_ACCIONES_BASICAS_TABLE = "CARTAS_ACCIONES_BASICAS";
    protected static final String CARTAS_ACCIONES_AVANZADAS_TABLE = "CARTAS_ACCIONES_AVANZADAS";
    protected static final String CARTAS_ACCIONES_AVANZADAS_ESPECIALES_TABLE = "CARTAS_ACCIONES_AVANZADAS_ESPECIALES";
    protected static final String CARTAS_HECHIZOS_TABLE = "CARTAS_HECHIZOS";
    protected static final String CARTAS_TACTICAS_TABLE = "CARTAS_TACTICAS";
    protected static final String FICHAS_HABILIDAD_TABLE = "FICHAS_HABILIDAD";

    protected static final String PARTIDA_DATOS_TABLE = "PARTIDA_DATOS";
    protected static final String PARTIDA_MODO_TABLE = "PARTIDA_MODO";
    protected static final String PARTIDA_INFORMACION_RONDA_TABLE = "PARTIDA_INFORMACION_RONDA";
    protected static final String PARTIDA_CARTAS_TACTICAS_TABLE = "PARTIDA_CARTAS_TACTICAS";
    protected static final String PARTIDA_HEROES_JUGADOR_TABLE = "PARTIDA_HEROES_JUGADOR";
    protected static final String PARTIDA_HEROE_DUMMY_TABLE = "PARTIDA_HEROE_DUMMY";
    protected static final String PARTIDA_CARTAS_HEROE_DUMMY_TABLE = "PARTIDA_CARTAS_HEROE_DUMMY";
    protected static final String PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE = "PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY";
    protected static final String PARTIDA_CRISTALES_HEROE_DUMMY_TABLE = "PARTIDA_CRISTALES_HEROE_DUMMY";

    // Nombre de las columnas
    // HEROES_TABLE
    protected static final String COL_1_HEROES_TABLE = "NOMBRE";

    // CRISTALES_TABLE
    protected static final String COL_1_CRISTALES_TABLE = "CRISTAL";

    // HEROES_CRISTALES_TABLE
    protected static final String COL_1_HEROES_CRISTALES_TABLE = "NOMBRE";
    protected static final String COL_2_HEROES_CRISTALES_TABLE = "CRISTAL";

    // CARTAS_TABLE
    protected static final String COL_1_CARTAS_TABLE = "NUMERO";
    protected static final String COL_2_CARTAS_TABLE = "NOMBRE";
    protected static final String COL_3_CARTAS_TABLE = "DESCARTADA";

    // CARTAS_ACCIONES_TABLE
    protected static final String COL_1_CARTAS_ACCIONES_TABLE = "NUMERO";
    protected static final String COL_2_CARTAS_ACCIONES_TABLE = "COLOR";
    protected static final String COL_3_CARTAS_ACCIONES_TABLE = "DESCRIPCION_BASICA";
    protected static final String COL_4_CARTAS_ACCIONES_TABLE = "DESCRIPCION_AVANZADA";

    // CARTAS_ACCIONES_BASICAS_TABLE
    protected static final String COL_1_CARTAS_ACCIONES_BASICAS_TABLE = "NUMERO";
    protected static final String COL_2_CARTAS_ACCIONES_BASICAS_TABLE = "HEROE";

    // CARTAS_ACCIONES_AVANZADAS_TABLE
    protected static final String COL_1_CARTAS_ACCIONES_AVANZADAS_TABLE = "NUMERO";

    // CARTAS_ACCIONES_AVANZADAS_ESPECIALES_TABLE
    protected static final String COL_1_CARTAS_ACCIONES_AVANZADAS_ESPECIALES_TABLE = "NUMERO";
    protected static final String COL_2_CARTAS_ACCIONES_AVANZADAS_ESPECIALES_TABLE = "COLOR_SECUNDARIO";

    // CARTAS_HECHIZOS_TABLE
    protected static final String COL_1_CARTAS_HECHIZOS_TABLE = "NUMERO";
    protected static final String COL_2_CARTAS_HECHIZOS_TABLE = "NOMBRE_SECUNDARIO";

    // CARTAS_TACTICAS_TABLE
    protected static final String COL_1_CARTAS_TACTICAS_TABLE = "NUMERO";
    protected static final String COL_2_CARTAS_TACTICAS_TABLE = "TIPO_TACTICA";
    protected static final String COL_3_CARTAS_TACTICAS_TABLE = "NUMERO_ORDEN";
    protected static final String COL_4_CARTAS_TACTICAS_TABLE = "DESCRIPCION";

    // FICHAS_HABILIDAD_TABLE
    protected static final String COL_1_FICHAS_HABILIDAD_TABLE = "ID_FICHA";
    protected static final String COL_2_FICHAS_HABILIDAD_TABLE = "NOMBRE";
    protected static final String COL_3_FICHAS_HABILIDAD_TABLE = "DESCRIPCION";
    protected static final String COL_4_FICHAS_HABILIDAD_TABLE = "DESCARTADA";
    protected static final String COL_5_FICHAS_HABILIDAD_TABLE = "HEROE";


    // *** PARTIDA ***

    //PARTIDA_DATOS_TABLE
    protected static final String COL_1_PARTIDA_DATOS_TABLE = "PARTIDA_ESTADO";
    protected static final String COL_2_PARTIDA_DATOS_TABLE = "RONDA";
    protected static final String COL_3_PARTIDA_DATOS_TABLE = "RONDA_ESTADO_INICIO";
    protected static final String COL_4_PARTIDA_DATOS_TABLE = "RONDA_ESTADO_FINALIZADO";
    protected static final String COL_5_PARTIDA_DATOS_TABLE = "TURNO";
    protected static final String COL_6_PARTIDA_DATOS_TABLE = "EXPERIENCIA";

    //PARTIDA_MODO_TABLE
    protected static final String COL_1_PARTIDA_MODO_TABLE = "TIPO";

    //PARTIDA_INFORMACION_RONDA_TABLE
    protected static final String COL_1_PARTIDA_INFORMACION_RONDA_TABLE = "INFORMACION";

    //PARTIDA_CARTAS_TACTICAS_TABLE
    protected static final String COL_1_PARTIDA_CARTAS_TACTICAS_TABLE = "NUMERO";
    protected static final String COL_2_PARTIDA_CARTAS_TACTICAS_TABLE = "NOMBRE";
    protected static final String COL_3_PARTIDA_CARTAS_TACTICAS_TABLE = "DESCARTADA";
    protected static final String COL_4_PARTIDA_CARTAS_TACTICAS_TABLE = "TIPO_TACTICA";
    protected static final String COL_5_PARTIDA_CARTAS_TACTICAS_TABLE = "NUMERO_ORDEN";
    protected static final String COL_6_PARTIDA_CARTAS_TACTICAS_TABLE = "DESCRIPCION";

    // PARTIDA_HEROES_JUGADOR_TABLE
    protected static final String COL_1_PARTIDA_HEROES_JUGADOR_TABLE = "NOMBRE";

    // PARTIDA_HEROE_DUMMY_TABLE
    protected static final String COL_1_PARTIDA_HEROE_DUMMY_TABLE = "NOMBRE";

    // PARTIDA_CARTAS_HEROE_DUMMY_TABLE
    protected static final String COL_1_PARTIDA_CARTAS_HEROE_DUMMY_TABLE = "NUMERO";
    protected static final String COL_2_PARTIDA_CARTAS_HEROE_DUMMY_TABLE = "NOMBRE";
    protected static final String COL_3_PARTIDA_CARTAS_HEROE_DUMMY_TABLE = "DESCARTADA";
    protected static final String COL_4_PARTIDA_CARTAS_HEROE_DUMMY_TABLE = "COLOR";
    protected static final String COL_5_PARTIDA_CARTAS_HEROE_DUMMY_TABLE = "COLOR_SECUNDARIO";
    protected static final String COL_6_PARTIDA_CARTAS_HEROE_DUMMY_TABLE = "DESCRIPCION_BASICA";
    protected static final String COL_7_PARTIDA_CARTAS_HEROE_DUMMY_TABLE = "DESCRIPCION_AVANZADA";
    protected static final String COL_8_PARTIDA_CARTAS_HEROE_DUMMY_TABLE = "HEROE_DUMMY";
    protected static final String COL_9_PARTIDA_CARTAS_HEROE_DUMMY_TABLE = "INDICE";

    //PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE
    protected static final String COL_1_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE = "ID_FICHA";
    protected static final String COL_2_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE = "NOMBRE";
    protected static final String COL_3_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE = "DESCRIPCION";
    protected static final String COL_4_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE = "DESCARTADA";
    protected static final String COL_5_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE = "HEROE_DUMMY";
    protected static final String COL_6_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE = "INDICE";

    // PARTIDA_CRISTALES_HEROE_DUMMY_TABLE
    protected static final String COL_1_PARTIDA_CRISTALES_HEROE_DUMMY_TABLE = "CRISTAL";


    //Constructor
    public AbstractSQLiteDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        Log.d("DATABASE", "1º) - CONSTRUCTOR");
    }

    //Métodos que sobreescriben e implementan los métodos de DatabaseHelp y, por consiguiente, de SQLiteOpenHelper
    @Override
    public void onCreate(SQLiteDatabase db) {
        createBoxTables(db);
        createGameTables(db);
        insertBoxData(db);
        insertGameStatus(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        /*
        db.execSQL("DROP TABLE IF EXISTS " + HEROES_TABLE);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + CRISTALES_TABLE);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + HEROES_CRISTALES_TABLE);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + CARTAS_TABLE);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + CARTAS_ACCIONES_TABLE);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + CARTAS_ACCIONES_BASICAS_TABLE);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + CARTAS_ACCIONES_AVANZADAS_TABLE);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + CARTAS_ACCIONES_AVANZADAS_ESPECIALES_TABLE);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + CARTAS_HECHIZOS_TABLE);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + CARTAS_TACTICAS_TABLE);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + FICHAS_HABILIDAD_TABLE);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + PARTIDA_DATOS_TABLE);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + PARTIDA_MODO_TABLE);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + PARTIDA_INFORMACION_RONDA_TABLE);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + PARTIDA_CARTAS_TACTICAS_TABLE);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + PARTIDA_HEROES_JUGADOR_TABLE);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + PARTIDA_HEROE_DUMMY_TABLE);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + PARTIDA_CARTAS_HEROE_DUMMY_TABLE);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + PARTIDA_CRISTALES_HEROE_DUMMY_TABLE);
        onCreate(db);
        */
    }

    private void createBoxTables(SQLiteDatabase db){
        Log.d("DATABASE", " ** 2º) - ONCREATE_INITIAL DATA ** ");

        // HEROES_TABLE
        StringBuilder strSQLHeroesTable = new StringBuilder();

        strSQLHeroesTable.append("CREATE TABLE ").append(HEROES_TABLE).append(" (")
                .append(COL_1_HEROES_TABLE).append(" TEXT PRIMARY KEY")
                .append(")");

        Log.d("DATABASE", "PRIMERO - ONCREATE_INITIAL DATA - HEROES_TABLE");
        Log.d("DATABASE", strSQLHeroesTable.toString());

        db.execSQL(strSQLHeroesTable.toString());


        // CRISTALES_TABLE
        StringBuilder strSQLCristalesTable = new StringBuilder();

        strSQLCristalesTable.append("CREATE TABLE ").append(CRISTALES_TABLE).append(" (")
                .append(COL_1_CRISTALES_TABLE).append(" TEXT PRIMARY KEY")
                .append(")");

        Log.d("DATABASE", "SEGUNDO - ONCREATE_INITIAL DATA - CRISTALES_TABLE");
        Log.d("DATABASE", strSQLCristalesTable.toString());

        db.execSQL(strSQLCristalesTable.toString());


        // HEROES_CRISTALES_TABLE
        StringBuilder strSQLHeroesCristalesTable = new StringBuilder();

        strSQLHeroesCristalesTable.append("CREATE TABLE ").append(HEROES_CRISTALES_TABLE).append(" (")
                .append(COL_1_HEROES_CRISTALES_TABLE).append(" TEXT NOT NULL,")
                .append(COL_2_HEROES_CRISTALES_TABLE).append(" TEXT NOT NULL")
                .append(")");

        Log.d("DATABASE", "TERCERO - ONCREATE_INITIAL DATA - HEROES_CRISTALES_TABLE");
        Log.d("DATABASE", strSQLHeroesCristalesTable.toString());

        db.execSQL(strSQLHeroesCristalesTable.toString());


        // CARTAS_TABLE
        StringBuilder strSQLCartasTable = new StringBuilder();

        strSQLCartasTable.append("CREATE TABLE ").append(CARTAS_TABLE).append(" (")
                .append(COL_1_CARTAS_TABLE).append(" INTEGER PRIMARY KEY,")
                .append(COL_2_CARTAS_TABLE).append(" TEXT NOT NULL,")
                .append(COL_3_CARTAS_TABLE).append(" BIT NOT NULL")
                .append(")");

        Log.d("DATABASE", "CUARTO - ONCREATE_INITIAL DATA - CARTAS_TABLE");
        Log.d("DATABASE", strSQLCartasTable.toString());

        db.execSQL(strSQLCartasTable.toString());


        // CARTAS_ACCIONES_TABLE
        StringBuilder strSQLCartasAccionesTable = new StringBuilder();

        strSQLCartasAccionesTable.append("CREATE TABLE ").append(CARTAS_ACCIONES_TABLE).append(" (")
                .append(COL_1_CARTAS_ACCIONES_TABLE).append(" INTEGER PRIMARY KEY,")
                .append(COL_2_CARTAS_ACCIONES_TABLE).append(" TEXT NOT NULL,")
                .append(COL_3_CARTAS_ACCIONES_TABLE).append(" TEXT NOT NULL,")
                .append(COL_4_CARTAS_ACCIONES_TABLE).append(" TEXT NOT NULL")
                .append(")");

        Log.d("DATABASE", "QUINTO - ONCREATE_INITIAL DATA - CARTAS_ACCIONES_TABLE");
        Log.d("DATABASE", strSQLCartasAccionesTable.toString());

        db.execSQL(strSQLCartasAccionesTable.toString());


        // CARTAS_ACCIONES_BASICAS_TABLE
        StringBuilder strSQLCartasAccionesBasicasTable = new StringBuilder();

        strSQLCartasAccionesBasicasTable.append("CREATE TABLE ").append(CARTAS_ACCIONES_BASICAS_TABLE).append(" (")
                .append(COL_1_CARTAS_ACCIONES_BASICAS_TABLE).append(" INTEGER PRIMARY KEY,")
                .append(COL_2_CARTAS_ACCIONES_BASICAS_TABLE).append(" TEXT NOT NULL")
                .append(")");

        Log.d("DATABASE", "SEXTO - ONCREATE_INITIAL DATA - CARTAS_ACCIONES_BASICAS_TABLE");
        Log.d("DATABASE", strSQLCartasAccionesBasicasTable.toString());

        db.execSQL(strSQLCartasAccionesBasicasTable.toString());


        // CARTAS_ACCIONES_AVANZADAS_TABLE
        StringBuilder strSQLCartasAccionesAvanzadasTable = new StringBuilder();

        strSQLCartasAccionesAvanzadasTable.append("CREATE TABLE ").append(CARTAS_ACCIONES_AVANZADAS_TABLE).append(" (")
                .append(COL_1_CARTAS_ACCIONES_AVANZADAS_TABLE).append(" INTEGER PRIMARY KEY")
                .append(")");

        Log.d("DATABASE", "SEPTIMO - ONCREATE_INITIAL DATA - CARTAS_ACCIONES_AVANZADAS_TABLE");
        Log.d("DATABASE", strSQLCartasAccionesAvanzadasTable.toString());

        db.execSQL(strSQLCartasAccionesAvanzadasTable.toString());


        // CARTAS_ACCIONES_AVANZADAS_ESPECIALES_TABLE
        StringBuilder strSQLCartasAccionesAvanzadasEspecialesTable = new StringBuilder();

        strSQLCartasAccionesAvanzadasEspecialesTable.append("CREATE TABLE ").append(CARTAS_ACCIONES_AVANZADAS_ESPECIALES_TABLE).append(" (")
                .append(COL_1_CARTAS_ACCIONES_AVANZADAS_ESPECIALES_TABLE).append(" INTEGER PRIMARY KEY,")
                .append(COL_2_CARTAS_ACCIONES_AVANZADAS_ESPECIALES_TABLE).append(" TEXT NOT NULL")
                .append(")");

        Log.d("DATABASE", "OCTAVO - ONCREATE_INITIAL DATA - CARTAS_ACCIONES_AVANZADAS_ESPECIALES_TABLE");
        Log.d("DATABASE", strSQLCartasAccionesAvanzadasEspecialesTable.toString());

        db.execSQL(strSQLCartasAccionesAvanzadasEspecialesTable.toString());


        // CARTAS_HECHIZOS_TABLE
        StringBuilder strSQLCartasHechizosTable = new StringBuilder();

        strSQLCartasHechizosTable.append("CREATE TABLE ").append(CARTAS_HECHIZOS_TABLE).append(" (")
                .append(COL_1_CARTAS_HECHIZOS_TABLE).append(" INTEGER PRIMARY KEY,")
                .append(COL_2_CARTAS_HECHIZOS_TABLE).append(" TEXT NOT NULL")
                .append(")");

        Log.d("DATABASE", "NOVENO - ONCREATE_INITIAL DATA - CARTAS_HECHIZOS_TABLE");
        Log.d("DATABASE", strSQLCartasHechizosTable.toString());

        db.execSQL(strSQLCartasHechizosTable.toString());


        // CARTAS_TACTICAS_TABLE
        StringBuilder strSQLCartasTacticasTable = new StringBuilder();

        strSQLCartasTacticasTable.append("CREATE TABLE ").append(CARTAS_TACTICAS_TABLE).append(" (")
                .append(COL_1_CARTAS_TACTICAS_TABLE).append(" INTEGER PRIMARY KEY,")
                .append(COL_2_CARTAS_TACTICAS_TABLE).append(" TEXT NOT NULL,")
                .append(COL_3_CARTAS_TACTICAS_TABLE).append(" INTEGER NOT NULL,")
                .append(COL_4_CARTAS_TACTICAS_TABLE).append(" TEXT")
                .append(")");

        Log.d("DATABASE", "DECIMO - ONCREATE_INITIAL DATA - CARTAS_TACTICAS_TABLE");
        Log.d("DATABASE", strSQLCartasTacticasTable.toString());

        db.execSQL(strSQLCartasTacticasTable.toString());


        // FICHAS_HABILIDAD_TABLE
        StringBuilder strSQLFichasHabilidadTable = new StringBuilder();

        strSQLFichasHabilidadTable.append("CREATE TABLE ").append(FICHAS_HABILIDAD_TABLE).append(" (")
                .append(COL_1_FICHAS_HABILIDAD_TABLE).append(" INTEGER PRIMARY KEY,")
                .append(COL_2_FICHAS_HABILIDAD_TABLE).append(" TEXT NOT NULL,")
                .append(COL_3_FICHAS_HABILIDAD_TABLE).append(" TEXT NOT NULL,")
                .append(COL_4_FICHAS_HABILIDAD_TABLE).append(" BIT NOT NULL,")
                .append(COL_5_FICHAS_HABILIDAD_TABLE).append(" TEXT NOT NULL")
                .append(")");

        Log.d("DATABASE", "DECIMO PRIMERO - ONCREATE_INITIAL DATA - FICHAS_HABILIDAD_TABLE");
        Log.d("DATABASE", strSQLFichasHabilidadTable.toString());

        db.execSQL(strSQLFichasHabilidadTable.toString());
    }

    // *** PARTIDA ***
    private void createGameTables(SQLiteDatabase db){
        Log.d("DATABASE", " ** 2º) - ONCREATE_GAME DATA ** ");

        //PARTIDA_MODO_TABLE
        StringBuilder strSQLPartidaDatosTable = new StringBuilder();

        strSQLPartidaDatosTable.append("CREATE TABLE ").append(PARTIDA_DATOS_TABLE).append(" (")
                .append(COL_1_PARTIDA_DATOS_TABLE).append(" TEXT PRIMARY KEY,")
                .append(COL_2_PARTIDA_DATOS_TABLE).append(" TEXT,")
                .append(COL_3_PARTIDA_DATOS_TABLE).append(" BIT NOT NULL,")
                .append(COL_4_PARTIDA_DATOS_TABLE).append(" BIT NOT NULL,")
                .append(COL_5_PARTIDA_DATOS_TABLE).append(" INTEGER NOT NULL,")
                .append(COL_6_PARTIDA_DATOS_TABLE).append(" INTEGER NOT NULL")
                .append(")");

        Log.d("DATABASE", "PRIMERO - ONCREATE_GAME DATA - PARTIDA_DATOS_TABLE");
        Log.d("DATABASE", strSQLPartidaDatosTable.toString());

        db.execSQL(strSQLPartidaDatosTable.toString());

        //PARTIDA_MODO_TABLE
        StringBuilder strSQLPartidaModoTable = new StringBuilder();

        strSQLPartidaModoTable.append("CREATE TABLE ").append(PARTIDA_MODO_TABLE).append(" (")
                .append(COL_1_PARTIDA_MODO_TABLE).append(" TEXT PRIMARY KEY")
                .append(")");

        Log.d("DATABASE", "SEGUNDO - ONCREATE_GAME DATA - PARTIDA_MODO_TABLE");
        Log.d("DATABASE", strSQLPartidaModoTable.toString());

        db.execSQL(strSQLPartidaModoTable.toString());

        //PARTIDA_INFORMACION_RONDA_TABLE
        StringBuilder strSQLPartidaInformacionTable = new StringBuilder();

        strSQLPartidaInformacionTable.append("CREATE TABLE ").append(PARTIDA_INFORMACION_RONDA_TABLE).append(" (")
                .append(COL_1_PARTIDA_INFORMACION_RONDA_TABLE).append(" TEXT NOT NULL")
                .append(")");

        Log.d("DATABASE", "TERCERO - ONCREATE_GAME DATA - PARTIDA_INFORMACION_RONDA_TABLE");
        Log.d("DATABASE", strSQLPartidaInformacionTable.toString());

        db.execSQL(strSQLPartidaInformacionTable.toString());

        //PARTIDA_CARTAS_TACTICAS_TABLE
        StringBuilder strSQLPartidaCartasTacticaTable = new StringBuilder();

        strSQLPartidaCartasTacticaTable.append("CREATE TABLE ").append(PARTIDA_CARTAS_TACTICAS_TABLE).append(" (")
                .append(COL_1_PARTIDA_CARTAS_TACTICAS_TABLE).append(" TEXT PRIMARY KEY,")
                .append(COL_2_PARTIDA_CARTAS_TACTICAS_TABLE).append(" TEXT NOT NULL,")
                .append(COL_3_PARTIDA_CARTAS_TACTICAS_TABLE).append(" BIT NOT NULL,")
                .append(COL_4_PARTIDA_CARTAS_TACTICAS_TABLE).append(" TEXT NOT NULL,")
                .append(COL_5_PARTIDA_CARTAS_TACTICAS_TABLE).append(" INTEGER NOT NULL,")
                .append(COL_6_PARTIDA_CARTAS_TACTICAS_TABLE).append(" TEXT")
                .append(")");

        Log.d("DATABASE", "CUARTO - ONCREATE_GAME DATA - PARTIDA_CARTAS_TACTICAS_TABLE");
        Log.d("DATABASE", strSQLPartidaCartasTacticaTable.toString());

        db.execSQL(strSQLPartidaCartasTacticaTable.toString());

        //PARTIDA_HEROES_JUGADOR_TABLE
        StringBuilder strSQLPartidaHeroesJugadorTable = new StringBuilder();

        strSQLPartidaHeroesJugadorTable.append("CREATE TABLE ").append(PARTIDA_HEROES_JUGADOR_TABLE).append(" (")
                .append(COL_1_PARTIDA_HEROES_JUGADOR_TABLE).append(" TEXT PRIMARY KEY")
                .append(")");

        Log.d("DATABASE", "QUINTO - ONCREATE_GAME DATA - PARTIDA_HEROES_JUGADOR_TABLE");
        Log.d("DATABASE", strSQLPartidaHeroesJugadorTable.toString());

        db.execSQL(strSQLPartidaHeroesJugadorTable.toString());


        //PARTIDA_HEROE_DUMMY_TABLE
        StringBuilder strSQLPartidaHeroeDummyTable = new StringBuilder();

        strSQLPartidaHeroeDummyTable.append("CREATE TABLE ").append(PARTIDA_HEROE_DUMMY_TABLE).append(" (")
                .append(COL_1_PARTIDA_HEROE_DUMMY_TABLE).append(" TEXT PRIMARY KEY")
                .append(")");

        Log.d("DATABASE", "SEXTO - ONCREATE_GAME DATA - PARTIDA_HEROE_DUMMY_TABLE");
        Log.d("DATABASE", strSQLPartidaHeroeDummyTable.toString());

        db.execSQL(strSQLPartidaHeroeDummyTable.toString());


        //PARTIDA_CARTAS_BASICAS_HEROE_DUMMY_TABLE
        StringBuilder strSQLPartidaCartasHeroeDummyTable = new StringBuilder();

        strSQLPartidaCartasHeroeDummyTable.append("CREATE TABLE ").append(PARTIDA_CARTAS_HEROE_DUMMY_TABLE).append(" (")
                .append(COL_1_PARTIDA_CARTAS_HEROE_DUMMY_TABLE).append(" INTEGER PRIMARY KEY,")
                .append(COL_2_PARTIDA_CARTAS_HEROE_DUMMY_TABLE).append(" TEXT NOT NULL,")
                .append(COL_3_PARTIDA_CARTAS_HEROE_DUMMY_TABLE).append(" BIT NOT NULL,")
                .append(COL_4_PARTIDA_CARTAS_HEROE_DUMMY_TABLE).append(" TEXT NOT NULL,")
                .append(COL_5_PARTIDA_CARTAS_HEROE_DUMMY_TABLE).append(" TEXT,")
                .append(COL_6_PARTIDA_CARTAS_HEROE_DUMMY_TABLE).append(" TEXT NOT NULL,")
                .append(COL_7_PARTIDA_CARTAS_HEROE_DUMMY_TABLE).append(" TEXT NOT NULL,")
                .append(COL_8_PARTIDA_CARTAS_HEROE_DUMMY_TABLE).append(" TEXT,")
                .append(COL_9_PARTIDA_CARTAS_HEROE_DUMMY_TABLE).append(" INTEGER NOT NULL")
                .append(")");

        Log.d("DATABASE", "SEPTIMO - ONCREATE_GAME DATA - PARTIDA_CARTAS_HEROE_DUMMY_TABLE");
        Log.d("DATABASE", strSQLPartidaCartasHeroeDummyTable.toString());

        db.execSQL(strSQLPartidaCartasHeroeDummyTable.toString());


        //PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE
        StringBuilder strSQLPartidaFichasHabilidadHeroeDummyTable = new StringBuilder();

        strSQLPartidaFichasHabilidadHeroeDummyTable.append("CREATE TABLE ").append(PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE).append(" (")
                .append(COL_1_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE).append(" INTEGER PRIMARY KEY,")
                .append(COL_2_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE).append(" TEXT NOT NULL,")
                .append(COL_3_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE).append(" TEXT NOT NULL,")
                .append(COL_4_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE).append(" BIT NOT NULL,")
                .append(COL_5_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE).append(" TEXT NOT NULL,")
                .append(COL_6_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE).append(" INTEGER NOT NULL")
                .append(")");

        Log.d("DATABASE", "OCTAVO - ONCREATE_GAME DATA - PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE");
        Log.d("DATABASE", strSQLPartidaFichasHabilidadHeroeDummyTable.toString());

        db.execSQL(strSQLPartidaFichasHabilidadHeroeDummyTable.toString());


        //PARTIDA_CRISTALES_HEROE_DUMMY_TABLE
        StringBuilder strSQLPartidaCristalesHeroeDummyTable = new StringBuilder();

        strSQLPartidaCristalesHeroeDummyTable.append("CREATE TABLE ").append(PARTIDA_CRISTALES_HEROE_DUMMY_TABLE).append(" (")
                .append(COL_1_PARTIDA_CRISTALES_HEROE_DUMMY_TABLE).append(" TEXT NOT NULL")
                .append(")");

        Log.d("DATABASE", "NOVENO - ONCREATE_GAME DATA - PARTIDA_CRISTALES_HEROE_DUMMY_TABLE");
        Log.d("DATABASE", strSQLPartidaCristalesHeroeDummyTable.toString());

        db.execSQL(strSQLPartidaCristalesHeroeDummyTable.toString());
    }

    private void insertBoxData(SQLiteDatabase db){

        // Los 7 Heroes
        Heroe arythea = getHeroeByName("Arythea");
        Heroe tovak = getHeroeByName("Tovak");
        Heroe norowas = getHeroeByName("Norowas");
        Heroe goldyx = getHeroeByName("Goldyx");
        Heroe wolfhawk = getHeroeByName("Wolfhawk");
        Heroe krang = getHeroeByName("Krang");
        Heroe braevalar = getHeroeByName("Braevalar");

        // Insertar en la tabla correspondiente los nombres de cada héroe
        createHeroe(arythea, db);
        createHeroe(tovak, db);
        createHeroe(norowas, db);
        createHeroe(goldyx, db);
        createHeroe(wolfhawk, db);
        createHeroe(krang, db);
        createHeroe(braevalar, db);

        // Insertar en la tabla correspondiente los 4 colores de los cristales
        createCristal(Cristal.ROJO, db);
        createCristal(Cristal.AZUL, db);
        createCristal(Cristal.BLANCO, db);
        createCristal(Cristal.VERDE, db);

        // Insertar en la tabla correspondiente cada héroe con sus 3 cristales correspondientes
        createHeroeCristal(arythea, arythea.getCristales().get(0), db);
        createHeroeCristal(arythea, arythea.getCristales().get(1), db);
        createHeroeCristal(arythea, arythea.getCristales().get(2), db);

        createHeroeCristal(tovak, tovak.getCristales().get(0), db);
        createHeroeCristal(tovak, tovak.getCristales().get(1), db);
        createHeroeCristal(tovak, tovak.getCristales().get(2), db);

        createHeroeCristal(norowas, norowas.getCristales().get(0), db);
        createHeroeCristal(norowas, norowas.getCristales().get(1), db);
        createHeroeCristal(norowas, norowas.getCristales().get(2), db);

        createHeroeCristal(goldyx, goldyx.getCristales().get(0), db);
        createHeroeCristal(goldyx, goldyx.getCristales().get(1), db);
        createHeroeCristal(goldyx, goldyx.getCristales().get(2), db);

        createHeroeCristal(wolfhawk, wolfhawk.getCristales().get(0), db);
        createHeroeCristal(wolfhawk, wolfhawk.getCristales().get(1), db);
        createHeroeCristal(wolfhawk, wolfhawk.getCristales().get(2), db);

        createHeroeCristal(krang, krang.getCristales().get(0), db);
        createHeroeCristal(krang, krang.getCristales().get(1), db);
        createHeroeCristal(krang, krang.getCristales().get(2), db);

        createHeroeCristal(braevalar, braevalar.getCristales().get(0), db);
        createHeroeCristal(braevalar, braevalar.getCristales().get(1), db);
        createHeroeCristal(braevalar, braevalar.getCristales().get(2), db);

        // Las 176 Cartas Avanzadas
        // 16 cartas del Mazo de Gesta de Arythea (Acción Básica)
        createCarta(new CartaAccionBasica(1, "Versatilidad en la Batalla",false, Cristal.ROJO, "Ataque 2, Bloqueo 2 o Ataque a Distancia 1.", "Ataque 4, Bloqueo 4, Ataque de Fuego 3, Bloqueo de Fuego 3, Ataque a Distancia 3 o Ataque de Asedio 2.", arythea), db);
        createCarta(new CartaAccionBasica(2, "Rabia", false, Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", arythea), db);
        createCarta(new CartaAccionBasica(3, "Determinación", false, Cristal.AZUL, "Ataque o Bloqueo 2.", "Bloqueo 5.", arythea), db);
        createCarta(new CartaAccionBasica(4, "Rapidez", false, Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", arythea), db);
        createCarta(new CartaAccionBasica(5, "Rapidez", false, Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", arythea), db);
        createCarta(new CartaAccionBasica(6, "Marcha", false, Cristal.VERDE, "Movimiento 2.", "Movimiento 4.", arythea), db);
        createCarta(new CartaAccionBasica(7, "Marcha", false, Cristal.VERDE, "Movimiento 2.", "Movimiento 4.", arythea), db);
        createCarta(new CartaAccionBasica(8, "Resistencia", false, Cristal.AZUL, "Movimiento 2.", "Movimiento 4.", arythea), db);
        createCarta(new CartaAccionBasica(9, "Resistencia", false, Cristal.AZUL, "Movimiento 2.", "Movimiento 4.",arythea), db);
        createCarta(new CartaAccionBasica(10, "Tranquilidad", false, Cristal.VERDE, "Curación 1 o roba 1 carta.", "Curación 2 o roba 2 cartas.", arythea), db);
        createCarta(new CartaAccionBasica(11, "Promesa", false, Cristal.BLANCO, "Influencia 2.", "Influencia 4.", arythea), db);
        createCarta(new CartaAccionBasica(12, "Amenaza", false, Cristal.ROJO, "Influencia 2.", "Influencia 5. Reputación -1.", arythea), db);
        createCarta(new CartaAccionBasica(13, "Cristalización", false, Cristal.AZUL, "Cuando juegues esta carta, paga 1 maná de color básico. Gana 1 cristal de ese color para tu inventario.", "Gana 1 cristal de cualquier color para tu Inventario.", arythea), db);
        createCarta(new CartaAccionBasica(14, "Extracción de Maná", false, Cristal.BLANCO, "Puedes usar 1 dado de maná adicional de la Fuente en este turno. Si es negro, úsalo para producir maná de cualquier color.", "Toma 2 dados de maná de la Fuente y déjalos en el color que quieras excepto dorado. Gana 1 ficha de maná de cada color. No relances los dados cuando los devuelvas a la Fuente.", arythea), db);
        createCarta(new CartaAccionBasica(15, "Concentración", false, Cristal.VERDE, "Gana 1 ficha de maná azul, blanco o rojo.", "Cuando juegues esta carta, juega otra carta de Acción con ella. Gana el efecto avanzado de esa carta sin coste. Si el efecto te otorga Movimiento, Influencia, Bloqueo o cualquier tipo de Ataque, gana la cantidad indicada +2.", arythea), db);
        createCarta(new CartaAccionBasica(16, "Improvisación", false, Cristal.ROJO, "Descarta otra carta de tu mano para obtener Movimiento 3, Influencia 3, Ataque 3 o Bloqueo 3.", "Descarta otra carta de tu mano para obtener Movimiento 5, Influencia 5, Ataque 5 o Bloqueo 5.",arythea), db);

        // 16 cartas del Mazo de Gesta de Tovak (Acción Básica)
        createCarta(new CartaAccionBasica(17, "Rabia", false, Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", tovak), db);
        createCarta(new CartaAccionBasica(18, "Rabia", false, Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", tovak), db);
        createCarta(new CartaAccionBasica(19, "Dureza Fría", false, Cristal.AZUL, "Ataque 2 o Bloqueo de Hielo 3.", "Bloqueo de Hielo 5. Gana +1 Bloqueo de Hielo por cada aptitud o color del ataque de la ficha de enemigo bloqueada (a menos que tenga Inmunidad Arcana), o por cada maná utilizado por el oponente durante el ataque.", tovak), db);
        createCarta(new CartaAccionBasica(20, "Rapidez", false, Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", tovak), db);
        createCarta(new CartaAccionBasica(21, "Rapidez", false, Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", tovak), db);
        createCarta(new CartaAccionBasica(22, "Marcha", false, Cristal.VERDE, "Movimiento 2.", "Movimiento 4.", tovak), db);
        createCarta(new CartaAccionBasica(23, "Marcha", false, Cristal.VERDE, "Movimiento 2.", "Movimiento 4.", tovak), db);
        createCarta(new CartaAccionBasica(24, "Resistencia", false, Cristal.AZUL, "Movimiento 2.", "Movimiento 4.", tovak), db);
        createCarta(new CartaAccionBasica(25, "Resistencia", false, Cristal.AZUL, "Movimiento 2.", "Movimiento 4.", tovak), db);
        createCarta(new CartaAccionBasica(26, "Tranquilidad", false, Cristal.VERDE, "Curación 1 o roba 1 carta.", "Curación 2 o roba 2 cartas.", tovak), db);
        createCarta(new CartaAccionBasica(27, "Promesa", false, Cristal.BLANCO, "Influencia 2.", "Influencia 4.", tovak), db);
        createCarta(new CartaAccionBasica(28, "Amenaza", false, Cristal.ROJO, "Influencia 2.", "Influencia 5. Reputación -1.", tovak), db);
        createCarta(new CartaAccionBasica(29, "Cristalización", false, Cristal.AZUL, "Cuando juegues esta carta, paga 1 maná de color básico. Gana 1 cristal de ese color para tu inventario.", "Gana 1 cristal de cualquier color para tu Inventario.", tovak), db);
        createCarta(new CartaAccionBasica(30, "Atracción de Maná", false, Cristal.BLANCO, "Puedes usar 1 dado de maná adicional de la Fuente en este turno.", "Toma 1 dado de maná de la Fuente y escoge una cara de cualquier color excepto dorado. Gana 2 fichas de maná de ese color. No relances este dado cuando lo devuelvas a la Fuente.", tovak), db);
        createCarta(new CartaAccionBasica(31, "Concentración", false, Cristal.VERDE, "Gana 1 ficha de maná azul, blanco o rojo.", "Cuando juegues esta carta, juega otra carta de Acción con ella. Gana el efecto avanzado de esa carta sin coste. Si el efecto te otorga Movimiento, Influencia, Bloqueo o cualquier tipo de Ataque, gana la cantidad indicada +2.", tovak), db);
        createCarta(new CartaAccionBasica(32, "Instinto", false, Cristal.ROJO, "Movimiento 2, Influencia 2, Ataque 2 o Bloqueo 2.", "Movimiento 4, Influencia 4, Ataque 4 o Bloqueo 4.", tovak), db);

        // 16 cartas del Mazo de Gesta de Norowas (Acción Básica)
        createCarta(new CartaAccionBasica(33, "Rabia", false, Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", norowas), db);
        createCarta(new CartaAccionBasica(34, "Rabia", false, Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", norowas), db);
        createCarta(new CartaAccionBasica(35, "Determinación", false, Cristal.AZUL, "Ataque o Bloqueo 2.", "Bloqueo 5.", norowas), db);
        createCarta(new CartaAccionBasica(36, "Rapidez", false, Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", norowas), db);
        createCarta(new CartaAccionBasica(37, "Rapidez", false, Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", norowas), db);
        createCarta(new CartaAccionBasica(38, "Marcha", false, Cristal.VERDE, "Movimiento 2.", "Movimiento 4.", norowas), db);
        createCarta(new CartaAccionBasica(39, "Marcha", false, Cristal.VERDE, "Movimiento 2.", "Movimiento 4.", norowas), db);
        createCarta(new CartaAccionBasica(40, "Resistencia", false, Cristal.AZUL, "Movimiento 2.", "Movimiento 4.", norowas), db);
        createCarta(new CartaAccionBasica(41, "Resistencia", false, Cristal.AZUL, "Movimiento 2.", "Movimiento 4.", norowas), db);
        createCarta(new CartaAccionBasica(42, "Rejuvenecer", false, Cristal.VERDE, "Curación 1, roba 1 carta, gana 1 ficha de maná verde, o prepara 1 Unidad de nivel I o II.", "Curación 2, roba 2 cartas, gana 1 cristal de maná verde para tu inventario, o prepara 1 Unidad de nivel I, II o III.", norowas), db);
        createCarta(new CartaAccionBasica(43, "Actos Nobles", false, Cristal.BLANCO, "Influencia 2. Fama +1 si usas esta carta durante la interacción.", "Influencia 4. Fama +1 y Reputación +1 si usas esta carta durante la interacción.", norowas), db);
        createCarta(new CartaAccionBasica(44, "Amenaza", false, Cristal.ROJO, "Influencia 2.", "Influencia 5. Reputación -1.", norowas), db);
        createCarta(new CartaAccionBasica(45, "Cristalización", false, Cristal.AZUL, "Cuando juegues esta carta, paga 1 maná de color básico. Gana 1 cristal de ese color para tu inventario.", "Gana 1 cristal de cualquier color para tu Inventario.", norowas), db);
        createCarta(new CartaAccionBasica(46, "Atracción de Maná", false, Cristal.BLANCO, "Puedes usar 1 dado de maná adicional de la Fuente en este turno.", "Toma 1 dado de maná de la Fuente y escoge una cara de cualquier color excepto dorado. Gana 2 fichas de maná de ese color. No relances este dado cuando lo devuelvas a la Fuente.", norowas), db);
        createCarta(new CartaAccionBasica(47, "Concentración", false, Cristal.VERDE, "Gana 1 ficha de maná azul, blanco o rojo.", "Cuando juegues esta carta, juega otra carta de Acción con ella. Gana el efecto avanzado de esa carta sin coste. Si el efecto te otorga Movimiento, Influencia, Bloqueo o cualquier tipo de Ataque, gana la cantidad indicada +2.", norowas), db);
        createCarta(new CartaAccionBasica(48, "Improvisación", false, Cristal.ROJO, "Descarta otra carta de tu mano para obtener Movimiento 3, Influencia 3, Ataque 3 o Bloqueo 3.", "Descarta otra carta de tu mano para obtener Movimiento 5, Influencia 5, Ataque 5 o Bloqueo 5.", norowas), db);

        // 16 cartas del Mazo de Gesta de Goldyx (Acción Básica)
        createCarta(new CartaAccionBasica(49, "Rabia", false, Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", goldyx), db);
        createCarta(new CartaAccionBasica(50, "Rabia", false, Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", goldyx), db);
        createCarta(new CartaAccionBasica(51, "Determinación", false, Cristal.AZUL, "Ataque o Bloqueo 2.", "Bloqueo 5.", goldyx), db);
        createCarta(new CartaAccionBasica(52, "Rapidez", false, Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", goldyx), db);
        createCarta(new CartaAccionBasica(53, "Rapidez", false, Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", goldyx), db);
        createCarta(new CartaAccionBasica(54, "Marcha", false, Cristal.VERDE, "Movimiento 2.", "Movimiento 4.", goldyx), db);
        createCarta(new CartaAccionBasica(55, "Marcha", false, Cristal.VERDE, "Movimiento 2.", "Movimiento 4.", goldyx), db);
        createCarta(new CartaAccionBasica(56, "Resistencia", false, Cristal.AZUL, "Movimiento 2.", "Movimiento 4.", goldyx), db);
        createCarta(new CartaAccionBasica(57, "Resistencia", false, Cristal.AZUL, "Movimiento 2.", "Movimiento 4.", goldyx), db);
        createCarta(new CartaAccionBasica(58, "Tranquilidad", false, Cristal.VERDE, "Curación 1 o roba 1 carta.", "Curación 2 o roba 2 cartas.", goldyx), db);
        createCarta(new CartaAccionBasica(59, "Promesa", false, Cristal.BLANCO, "Influencia 2.", "Influencia 4.", goldyx), db);
        createCarta(new CartaAccionBasica(60, "Amenaza", false, Cristal.ROJO, "Influencia 2.", "Influencia 5. Reputación -1.", goldyx), db);
        createCarta(new CartaAccionBasica(61, "Alegría Cristalizada", false, Cristal.AZUL, "Paga 1 maná de cualquier color básico para ganar 1 cristal de ese color para tu inventario. Al final de tu turno, puedes descartar otra carta que no sea Herida para devolver esta carta a tu mano.", "Gana 1 cristal de cualquier color básico para tu inventario. Al final de tu turno, puedes descartar otra carta (incluso una Herida) para", goldyx), db);
        createCarta(new CartaAccionBasica(62, "Atracción de Maná", false, Cristal.BLANCO, "Puedes usar 1 dado de maná adicional de la Fuente en este turno.", "Toma 1 dado de maná de la Fuente y escoge una cara de cualquier color excepto dorado. Gana 2 fichas de maná de ese color. No relances este dado cuando lo devuelvas a la Fuente.", goldyx), db);
        createCarta(new CartaAccionBasica(63, "Enfoque Decisivo", false, Cristal.VERDE, "Gana 1 ficha de maná azul, blanco o rojo o bien 1 cristal verde para tu inventario.", "Cuando juegues esta carta, juega otra carta de Acción con ella. Gana el efecto avanzado de esa carta sin coste. Si el efecto te otorga Movimiento, Influencia, Bloqueo o cualquier tipo de Ataque, gana la cantidad indicada +3.", goldyx), db);
        createCarta(new CartaAccionBasica(64, "Improvisación", false, Cristal.ROJO, "Descarta otra carta de tu mano para obtener Movimiento 3, Influencia 3, Ataque 3 o Bloqueo 3.", "Descarta otra carta de tu mano para obtener Movimiento 5, Influencia 5, Ataque 5 o Bloqueo 5.", goldyx), db);

        // 28 cartas de Acción Avanzada (juego base)
        createCarta(new CartaAccionAvanzada(65, "Rayo de Fuego", false, Cristal.ROJO, "Gana 1 cristal rojo para tu Inventario.", "Ataque a Distancia de Fuego 3."), db);
        createCarta(new CartaAccionAvanzada(66, "Rayo de Hielo", false, Cristal.AZUL, "Gana 1 cristal azul para tu Inventario.", "Ataque a Distancia de Hielo 3."), db);
        createCarta(new CartaAccionAvanzada(67, "Rayo Fulminante", false, Cristal.BLANCO, "Gana 1 cristal blanco para tu Inventario.", "Ataque a Distancia 4."), db);
        createCarta(new CartaAccionAvanzada(68, "Rayo Devastador", false, Cristal.VERDE, "Gana 1 cristal verde para tu Inventario.", "Ataque de Asedio 3."), db);
        createCarta(new CartaAccionAvanzada(69, "Furia Sangrienta", false, Cristal.ROJO, "Ataque 2. Puedes recibir 1 Herida para aumentar a Ataque 5.", "Ataque 4. Puedes recibir 1 Herida para aumentar a Ataque 9."), db);
        createCarta(new CartaAccionAvanzada(70, "Escudo de Hielo", false, Cristal.AZUL, "Bloqueo de Hielo 3.", "Bloqueo de Hielo 3. Reduce en 3 la Armadura de un enemigo bloqueado de esta manera. La Armadura no puede ser reducida por debajo de 1."), db);
        createCarta(new CartaAccionAvanzada(71, "Agilidad", false, Cristal.BLANCO, "Movimiento 2. Durante un combate en este turno, puedes gastar puntos de Movimiento para obtener Ataque 1: uno por cada punto.", "Movimiento 4. Durante un combate en este turno, puedes gastar puntos de Movimiento: 2 para Ataque a Distancia 1 y/o 1 para Ataque 1."), db);
        createCarta(new CartaAccionAvanzada(72, "Paseo Reparador", false, Cristal.VERDE, "Movimiento 2 y Curación 1. Durante el combate: solo Movimiento 2.", "Movimiento 4 y Curación 2. Durante el combate: solo Movimiento 4."), db);
        createCarta(new CartaAccionAvanzada(73, "Intimidación", false, Cristal.ROJO, "Influencia 4 o Ataque 3. Reputación -1.", "Influencia 8 o Ataque 7. Reputación -2."), db);
        createCarta(new CartaAccionAvanzada(74, "Puente Helado", false, Cristal.AZUL, "Movimiento 2. El coste de Movimiento de pantanos se reduce a 1 en este turno.", "Movimiento 4. Puedes moverte a través de lagos. El coste de Movimiento de lagos y pantanos se reduce a 1 en este turno."), db);
        createCarta(new CartaAccionAvanzada(75, "Canción del Viento", false, Cristal.BLANCO, "Movimiento 2. El coste de Movimiento de llanuras, desiertos y páramos se reduce en 1, hasta un mínimo de 0 en este turno.", "Movimiento 2. El Movimiento de llanuras, desiertos y páramos se reduce en 2, hasta un mínimo de 0 este turno. Puedes pagar un maná azul para moverte a través de lagos con un coste de Movimiento 0 este turno."), db);
        createCarta(new CartaAccionAvanzada(76, "Búsqueda del Camino", false, Cristal.VERDE, "Movimiento 2. El coste de Movimiento de todos los terrenos se reduce en 1, hasta un mínimo de 2 en este turno.", "Movimiento 4. El coste de Movimiento de todos los terrenos se reduce a 2 en este turno."), db);
        createCarta(new CartaAccionAvanzada(77, "Ritual de Sangre", false, Cristal.ROJO, "Recibe 1 Herida. Gana 1 cristal de maná rojo para tu inventario y 1 ficha de maná de cualquier color (incluidos los no básicos).", "Recibe 1 Herida. Gana 3 fichas de maná de cualquier color (Incluidos los no básicos). Puedes pagar 1 maná de cualquier color básico para ganar 1 cristal de ese color para tu inventario."), db);
        createCarta(new CartaAccionAvanzada(78, "Magia Pura", false, Cristal.AZUL, "Cuando juegues esta carta, paga 1 maná. Si pagas verde, Movimiento 4. Si pagas blanco, Influencia 4. Si pagas azul, Bloqueo 4. Si pagas rojo, Ataque 4.", "Cuando juegues esta carta, paga 1 maná. Si pagas verde, Movimiento 7. Si pagas blanco, Influencia 7. Si pagas azul, Bloqueo 7. Si pagas rojo, Ataque 7."), db);
        createCarta(new CartaAccionAvanzada(79, "Cuento Heroico", false, Cristal.BLANCO, "Influencia 3. Por cada Unidad que hayas reclutado en este turno, Reputación +1.", "Influencia 6. Por cada Unidad que hayas reclutado en este turno, Fama +1 y Reputación +1."), db);
        createCarta(new CartaAccionAvanzada(80, "Regeneración", false, Cristal.VERDE, "Curación 1. Prepara 1 Unidad de Nivel I o II que controles.", "Curación 2. Prepara 1 Unidad de Nivel I, II o III que controles."), db);
        createCarta(new CartaAccionAvanzada(81, "Hacia el Fragor", false, Cristal.ROJO, "Juega esta carta al principio del combate. Todas tus Unidades aumentan sus valores de Ataque y Bloqueo en +2 durante este combate. No puedes asignar daño a tus Unidades en este turno.", "Juega esta carta al principio del combate. Todas tus Unidades aumentan sus valores de Ataque y Bloqueo en +3 durante este combate. No puedes asignar daño a tus Unidades en este turno."), db);
        createCarta(new CartaAccionAvanzada(82, "Ritmo Constante", false, Cristal.AZUL, "Movimiento 2. Al final de tu turno, en lugar de colocar esta carta en tu pila de descarte, puedes colocarla en la parte inferior de tu mazo de Gesta, siempre y cuando no esté vacío.", "Movimiento 4. Al final de tu turno, en lugar de colocar esta carta en tu pila de descarte, puedes colocarla en la parte superior de tu mazo de Gesta."), db);
        createCarta(new CartaAccionAvanzada(83, "Diplomacia", false, Cristal.BLANCO, "Influencia 2. Puedes usar Influencia como Bloqueo en este turno.", "Influencia 4. Elige Hielo o Fuego. Puedes usar Influencia como Bloqueo del elemento elegido en este turno."), db);
        createCarta(new CartaAccionAvanzada(84, "Necesitado", false, Cristal.VERDE, "Influencia 3. Gana Influencia, +1 adicional por cada carta de Herida en tu mano o en Unidades que controles.", "Influencia 5. Gana Influencia, +2 adicional por cada carta de Herida en tu mano o en Unidades que controles."), db);
        createCarta(new CartaAccionAvanzada(85, "Descomposición", false, Cristal.ROJO, "Cuando juegues esta carta, retira 1 carta de Acción de tu mano. Gana para tu inventario 2 cristales del mismo color que la carta retirada.", "Cuando juegues esta carta, retira 1 carta de Acción de tu mano. Gana para tu inventario 1 cristal de cada color básico que no coincida con el color de la carta retirada."), db);
        createCarta(new CartaAccionAvanzada(86, "Dominio del Cristal", false, Cristal.AZUL, "Gana 1 cristal para tu Inventario del mismo color que un cristal que ya poseas.", "Al final del turno, cualquier cristal que hayas utilizado en este turno se devuelve a tu inventario."), db);
        createCarta(new CartaAccionAvanzada(87, "Tormenta de Maná", false, Cristal.BLANCO, "Elige de la Fuente 1 dado de maná de color básico. Gana 1 cristal de ese color para tu inventario. Relanza ese dado inmediatamente y devuélvelo a la Fuente.", "Relanza los dados de la Fuente. Puedes utilizar 3 dados extra, y puedes usar dados de maná negro o dorado como cualquier color básico, independientemente de la TipoRonda."), db);
        createCarta(new CartaAccionAvanzada(88, "Emboscada", false, Cristal.VERDE, "Movimiento 2. Añade +1 a tu primera carta de Ataque de cualquier tipo o +2 a tu primera carta de Bloqueo de cualquier tipo, lo primero que juegues en este turno.", "Movimiento 4. Añade +2 a tu primera carta de Ataque de cualquier tipo o +4 a tu primera carta de Bloqueo, lo primero que juegues en este turno."), db);
        createCarta(new CartaAccionAvanzada(89, "Máxima Efectividad", false, Cristal.ROJO, "Cuando juegues esta carta, retira otra carta de Acción de tu mano. Usa 3 veces el efecto básico de la carta retirada.", "Cuando juegues esta carta, retira otra carta de Acción de tu mano. Usa 2 veces el efecto avanzado de la carta retirada (sin coste)."), db);
        createCarta(new CartaAccionAvanzada(90, "Talento Mágico", false, Cristal.AZUL, "Descarta 1 carta de cualquier color. Puedes jugar 1 carta de Hechizo de ese color de la oferta de Hechizos en este turno como si estuviera en tu mano. La carta permanece en la oferta de Hechizos.", "Cuando juegues esta carta, paga 1 maná de cualquier color. Gana 1 carta de Hechizo de ese color de la oferta de Hechizos y colócala en tu pila de descarte."), db);
        createCarta(new CartaAccionAvanzada(91, "Aprendizaje", false, Cristal.BLANCO, "Influencia 2. Una vez en este turno, puedes pagar Influencia 6 para obtener 1 carta de Acción Avanzada de la oferta de Acciones Avanzadas y colocarla en tu pila de descarte.", "Influencia 4. Una vez en este turno, puedes pagar Influencia 9 para obtener 1 carta de Acción Avanzada de la oferta de Acciones Avanzadas y colocarla en tu mano."), db);
        createCarta(new CartaAccionAvanzada(92, "Entrenamiento", false, Cristal.VERDE, "Retira 1 carta de Acción de tu mano para tomar 1 carta del mismo color de la oferta de Acciones Avanzadas y colocarla en tu pila de descarte.", "Retira 1 carta de Acción de tu mano para tomar 1 carta del mismo color de la oferta de Acciones Avanzadas y colocarla en tu mano."), db);

        // 16 cartas de Hechizo (juego base)
        createCarta(new CartaAccionHechizo(93, "Bola de Fuego", false, Cristal.ROJO, "Ataque a Distancia de Fuego 5.", "Recibe 1 Herida. Ataque de Asedio de Fuego 8.", "Tormenta de Fuego"), db);
        createCarta(new CartaAccionHechizo(94, "Tormenta de Nieve", false, Cristal.AZUL, "Ataque a Distancia de Hielo 5.", "Recibe 1 Herida. Ataque de Asedio de Hielo 8.","Ventisca"), db);
        createCarta(new CartaAccionHechizo(95, "Exposición", false, Cristal.BLANCO, "El enemigo objetivo pierde todas las fortificaciones y resistencias durante este combate. Ataque a Distancia 2.", "Los enemigos pierden todas las fortificaciones, o pierden todas las resistencias durante este combate. Ataque a Distancia 3.", "Exposición Masiva"), db);
        createCarta(new CartaAccionHechizo(96, "Temblor", false, Cristal.VERDE, "El enemigo objetivo obtiene Armadura -3, o todos los enemigos obtienen Armadura -2. La Armadura no puede ser reducida por debajo de 1.", "El enemigo objetivo obtiene Armadura -3 (-6 si está fortificado), o todos los enemigos obtienen Armadura -2 (-4 si están fortificados). La Armadura no puede ser reducida por debajo de 1.", "Terremoto"), db);
        createCarta(new CartaAccionHechizo(97, "Muro de Llamas", false, Cristal.ROJO, "Ataque de Fuego 5, o Bloqueo de Fuego 7.", "Ataque de Fuego 5, o Bloqueo de Fuego 7. Este Ataque o Bloqueo incrementa en 2 por cada ficha de enemigo a la que te estás enfrentando.", "Ola de Llamas"), db);
        createCarta(new CartaAccionHechizo(98, "Rayo de Maná", false, Cristal.AZUL, "Cuando juegues esta carta, paga 1 maná. Si es azul, Ataque de Hielo 8. Si es rojo, Ataque de Fuego Frío 7. Si es blanco, Ataque a Distancia de Hielo 6. Si es verde, Ataque de Asedio de Hielo 5.", "Cuando juegues esta carta, paga 1 maná. Si es azul, Ataque de Hielo 11. Si es rojo, Ataque de Fuego Frío 10. Si es blanco, Ataque a Distancia de Hielo 9. Si es verde, Ataque de Asedio de Hielo 8.", "Trueno de Maná"), db);
        createCarta(new CartaAccionHechizo(99, "Torbellino", false, Cristal.BLANCO, "El enemigo objetivo no ataca durante este combate.", "Juega esta carta solo en la fase de Ataque del combate. Destruye al enemigo objetivo.", "Tornado"), db);
        createCarta(new CartaAccionHechizo(100, "Viaje Subterráneo", false, Cristal.VERDE, "Muévete hasta 3 espacios revelados en el mapa. No puedes moverte a o a través de pantanos o lagos de este modo, y debes terminar tu movimiento en un espacio seguro. Este movimiento no provoca a los enemigos acechantes.", "Igual que el efecto básico, excepto que debes terminar tu movimiento en un lugar fortificado (o en un espacio ocupado por otro jugador). Esto termina tu movimiento e inicia un asalto (o un ataque a otro jugador). Ignora las fortificaciones del lugar. Si te retiras después del combate, vuelve a tu posición inicial.", "Ataque Subterráneo"), db);
        createCarta(new CartaAccionHechizo(101, "Escudo Ardiente", false, Cristal.ROJO, "Bloqueo de Fuego 4. Si usas esta carta en un Bloqueo exitoso , puedes usarla también en la fase de Ataque como Ataque de Fuego 4.", "Bloqueo de Fuego 4. Si usas esta carta en un Bloqueo exitoso, destruye al enemigo bloqueado.", "Escudo Explosivo"), db);
        createCarta(new CartaAccionHechizo(102, "Escalofrío", false, Cristal.AZUL, "El enemigo objetivo no ataca durante este combate. Si tiene Resistencia al Fuego, la pierde para el resto del turno.", "El enemigo objetivo no ataca durante este combate y obtiene Armadura -4 (hasta un mínimo de 1) para el resto del turno.", "Escalofrío Letal"), db);
        createCarta(new CartaAccionHechizo(103, "Alas de Viento", false, Cristal.BLANCO, "Cuando juegues esta carta, gasta 1-5 puntos de Movimiento y muévete a un espacio revelado por cada punto. Debes terminar tu movimiento en un espacio seguro. Este movimiento no provoca a los enemigos acechantes.", "El enemigo objetivo no ataca durante este combate. Puedes hacer objetivo a enemigos adicionales: paga 1 punto de Movimiento por el segundo enemigo, 2 puntos por el tercero, etc.", "Alas de Noche"), db);
        createCarta(new CartaAccionHechizo(104, "Restaurar", false, Cristal.VERDE, "Curación 3. Si estás en un bosque, Curación 5 en su lugar.", "Curación 3. Si estás en un bosque, Curación 5 en su lugar. Prepara Unidades hasta un valor de 3 niveles. Si estás en un Bosque, Prepara Unidades hasta un valor de 5 niveles en su lugar.", "Renacer"), db);
        createCarta(new CartaAccionHechizo(105, "Demoler", false, Cristal.ROJO, "Ignora las fortificaciones de los lugares en este turno. Los enemigos obtienen Armadura -1 (hasta un mínimo de 1).", "Juega esta carta solamente durante la fase de Ataque del combate. Destruye al enemigo objetivo. Otros enemigos obtienen Armadura -1 (hasta un mínimo de 1).", "Desintegrar"), db);
        createCarta(new CartaAccionHechizo(106, "Salto en el Espacio", false, Cristal.AZUL, "En este turno, puedes moverte a espacios y explorar nuevas losetas que estén a 2 espacios de distancia de ti como si estuvieran adyacentes. Ignora cualquier espacio por el que saltes de este modo. Este movimiento no provoca a los enemigos acechantes.", "Al final de tu turno, deja esta carta apartada para el resto de la TipoRonda. Coloca todas las demás cartas que hayas jugado en este turno (pero no las que hayas descartado o retirado) de nuevo en tu mano. Ignora la parte \"roba cartas nuevas\" del paso final de tu turno. Vuelve a realizar inmediatamente otro turno completo.", "Salto en el Tiempo"), db);
        createCarta(new CartaAccionHechizo(107, "Llamada a las Armas", false, Cristal.BLANCO, "Puedes usar una aptitud de una Unidad en la oferta de Unidades en este turno, como si fuese una de las reclutadas por ti. No puedes asignar daño a esta Unidad.", "Recluta cualquier Unidad de la oferta de Unidades sin coste. (Si no tienes espacio para añadirla, debes disolver una Unidad primero.)", "Llamada a la Gloria"), db);
        createCarta(new CartaAccionHechizo(108, "Meditación", false, Cristal.VERDE, "Toma 2 cartas al azar de tu pila de descarte y colócalas en la parte inferior o superior de tu mazo de Gesta. Tu límite de mano aumenta en 2 la próxima vez que robes cartas.", "Igual que el efecto básico, excepto que eliges las cartas en vez de tomarlas al azar.", "Trance"), db);

        // 12 cartas de Táctica
        createCarta(new CartaTactica(129, "Madrugador", false, TipoTactica.DIA, 1, ""), db);
        createCarta(new CartaTactica(130, "Rectificar", false, TipoTactica.DIA, 2, "Cuando elijas esta Táctica, descarta hasta 3 cartas (incluyendo Heridas) de tu mano, luego roba la misma cantidad de cartas. Baraja tu pila de descarte junto con tu mazo de Gesta."), db);
        createCarta(new CartaTactica(131, "Robar Maná", false, TipoTactica.DIA, 3, "Cuando elijas esta Táctica, toma 1 dado de maná de color básico de la Fuente y colócalo sobre esta carta. Puedes usar este maná en uno de tus turnos durante este Día. Si lo haces, relánzalo al final de ese turno y devuélvelo a la Fuente."), db);
        createCarta(new CartaTactica(132, "Planificar", false, TipoTactica.DIA, 4, "Al final de cada turno, si tienes al menos 2 cartas en tu mano antes de robar, roba 1 carta más que tu límite de mano."), db);
        createCarta(new CartaTactica(133, "Buen Comienzo", false, TipoTactica.DIA, 5, "Cuando elijas esta Táctica, roba inmediatamente 2 cartas."), db);
        createCarta(new CartaTactica(134, "El Momento Adecuado", false, TipoTactica.DIA, 6, "Una vez durante este Día, en tu turno, puedes anunciar que realizarás otro turno inmediatamente después de este. Si lo haces, voltea esta carta boca abajo."), db);
        createCarta(new CartaTactica(135, "Desde la Oscuridad", false, TipoTactica.NOCHE, 1, ""), db);
        createCarta(new CartaTactica(136, "Larga Noche", false, TipoTactica.NOCHE, 2, "Una vez durante esta Noche, si tu mazo de Gesta está vacío, puedes barajar tu pila de descarte y colocar 3 cartas al azar de vuelta en el mazo de Gesta. Luego, voltea esta carta boca abajo."), db);
        createCarta(new CartaTactica(137, "Búsqueda de Maná", false, TipoTactica.NOCHE, 3, "Una vez por turno, antes de usar maná de la Fuente, puedes relanzar hasta 2 dados de maná de la Fuente. Cuando escojas los dados para relanzar, debes elegir primero los dados dorados (inactivos) si hay alguno."), db);
        createCarta(new CartaTactica(138, "Meditación a Medianoche", false, TipoTactica.NOCHE, 4, "Una vez durante esta Noche, antes de empezar uno de tus turnos, puedes barajar hasta 5 cartas de tu mano (incluyendo Heridas) junto con tu mazo de Gesta, y luego robar la misma cantidad de cartas. Después, voltea esta carta boca abajo."), db);
        createCarta(new CartaTactica(139, "Preparación", false, TipoTactica.NOCHE, 5, "Cuando elijas esta Táctica, busca en tu mazo de Gesta 1 carta que quieras y colócala en tu mano. Luego, baraja el mazo."), db);
        createCarta(new CartaTactica(140, "Reservar Poder", false, TipoTactica.NOCHE, 6, "Antes del inicio de cada uno de tus turnos, elige una opción: Coloca la carta de la parte superior de tu mazo de Gesta boca abajo, debajo de esta carta de Táctica, o voltea esta carta de Táctica boca abajo y coloca en tu mano todas las cartas de Gesta que estuvieran debajo."), db);

        // 4 cartas de Acción Avanzada Especiales(Ultimate Edition) (2 colores)
        createCarta(new CartaAccionAvanzadaEspecial(266, "Poder de los Cristales", false, Cristal.VERDE,  "Gana 1 cristal para tu Inventario de un color básico que aún no tengas.", "Movimiento 4, o Curación 2, o roba dos cartas. Por cada conjunto de 4 cristales de diferentes colores en tu Inventario: Movimiento 2, o Curación 1, o roba una carta.", Cristal.AZUL), db);
        createCarta(new CartaAccionAvanzadaEspecial(267, "Descarga de Adrenalina", false, Cristal.ROJO, "Por cada una de las primeras 3 Heridas que llevas a la mano en este turno, roba 1 carta.", "Tras tomar la primera Herida a la mano en este turno, retírala y roba 1 carta. Por cada 1 de las 3 Heridas siguientes que tomes, roba 1 carta.", Cristal.VERDE ), db);
        createCarta(new CartaAccionAvanzadaEspecial(268, "Rayo Explosivo", false, Cristal.BLANCO, "Recibe 1 Herida. Gana 1 cristal blanco y rojo para tu Inventario.", "Ataque a Distancia 3. Por cada enemigo derrotado con este ataque, otro enemigo obtiene Armadura -1 (hasta un mínimo de 1).", Cristal.ROJO), db);
        createCarta(new CartaAccionAvanzadaEspecial(269, "Mirada Penetrante", false, Cristal.AZUL, "Influencia 3, o el ataque del enemigo objetivo pierde todas sus aptitudes (pero no su color).", "Influencia 5, o el enemigo objetivo no ataca en este turno.", Cristal.BLANCO), db);

        // 16 cartas del Mazo de Gesta de Wolfhawk (Acción Básica)
        createCarta(new CartaAccionBasica(271, "Rabia", false, Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", wolfhawk), db);
        createCarta(new CartaAccionBasica(272, "Rabia", false, Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", wolfhawk), db);
        createCarta(new CartaAccionBasica(273, "Determinación", false, Cristal.AZUL, "Ataque o Bloqueo 2.", "Bloqueo 5.", wolfhawk), db);
        createCarta(new CartaAccionBasica(274, "Reflejos Instantáneos", false, Cristal.BLANCO, "Movimiento 2, Ataque a Distancia 1, o reduce el ataque de un enemigo en 1.", "Movimiento 4, Ataque a Distancia 3, o reduce el ataque de un enemigo en 2.", wolfhawk), db);
        createCarta(new CartaAccionBasica(275, "Rapidez", false, Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", wolfhawk), db);
        createCarta(new CartaAccionBasica(276, "Marcha", false, Cristal.VERDE, "Movimiento 2.", "Movimiento 4.", wolfhawk), db);
        createCarta(new CartaAccionBasica(277, "Marcha", false, Cristal.VERDE, "Movimiento 2.", "Movimiento 4.", wolfhawk), db);
        createCarta(new CartaAccionBasica(278, "Incansable", false, Cristal.AZUL, "Movimiento 2. Con la siguiente carta que otorgue Movimiento (Incluidas las cartas giradas) obtienes +1 Movimiento en este turno.", "Movimiento 4. Con cualquier otra carta que otorgue Movimiento (Incluidas las cartas giradas) obtienes +1 Movimiento en este turno.", wolfhawk), db);
        createCarta(new CartaAccionBasica(279, "Resistencia", false, Cristal.AZUL, "Movimiento 2.", "Movimiento 4.", wolfhawk), db);
        createCarta(new CartaAccionBasica(280, "Tranquilidad", false, Cristal.VERDE, "Curación 1 o roba 1 carta.", "Curación 2 o roba 2 cartas.", wolfhawk), db);
        createCarta(new CartaAccionBasica(281, "Promesa", false, Cristal.BLANCO, "Influencia 2.", "Influencia 4.", wolfhawk), db);
        createCarta(new CartaAccionBasica(282, "Amenaza", false, Cristal.ROJO, "Influencia 2.", "Influencia 5. Reputación -1.", wolfhawk), db);
        createCarta(new CartaAccionBasica(283, "Cristalización", false, Cristal.AZUL, "Cuando juegues esta carta, paga 1 maná de color básico. Gana 1 cristal de ese color para tu inventario.", "Gana 1 cristal de cualquier color para tu Inventario.", wolfhawk), db);
        createCarta(new CartaAccionBasica(284, "Atracción de Maná", false, Cristal.BLANCO, "Puedes usar 1 dado de maná adicional de la Fuente en este turno.", "Toma 1 dado de maná de la Fuente y escoge una cara de cualquier color excepto dorado. Gana 2 fichas de maná de ese color. No relances este dado cuando lo devuelvas a la Fuente.", wolfhawk), db);
        createCarta(new CartaAccionBasica(285, "Concentración", false, Cristal.VERDE, "Gana 1 ficha de maná azul, blanco o rojo.", "Cuando juegues esta carta, juega otra carta de Acción con ella. Gana el efecto avanzado de esa carta sin coste. Si el efecto te otorga Movimiento, Influencia, Bloqueo o cualquier tipo de Ataque, gana la cantidad indicada +2.", wolfhawk), db);
        createCarta(new CartaAccionBasica(286, "Improvisación", false, Cristal.ROJO, "Descarta otra carta de tu mano para obtener Movimiento 3, Influencia 3, Ataque 3 o Bloqueo 3.", "Descarta otra carta de tu mano para obtener Movimiento 5, Influencia 5, Ataque 5 o Bloqueo 5.", wolfhawk), db);

        // 12 cartas de Acción Avanzada (Legión Perdida)
        createCarta(new CartaAccionAvanzada(287, "Contraataque", false, Cristal.ROJO, "Ataque 2. Gana Ataque 2 adicional por cada enemigo bloqueado en este turno.", "Ataque 4. Gana Ataque 3 adicional por cada enemigo bloqueado en este turno."), db);
        createCarta(new CartaAccionAvanzada(288, "Ritual de Ataque", false, Cristal.ROJO, "Retira otra carta de acción. En función de su color, gana: Rojo: Ataque 5, Azul: Ataque de Hielo 3, Blanco: Ataque a Distancia 3, Verde: Ataque de Asedio 2.", "Retira otra carta de acción. En función de su color, gana: Rojo: Ataque de Fuego 6, Azul: Ataque de Fuego Frío 4, Blanco: Ataque a Distancia de Fuego 4, Verde: Ataque de Asedio de Fuego 3."), db);
        createCarta(new CartaAccionAvanzada(289, "Sangre Ancestral", false, Cristal.ROJO, "Recibe 1 Herida. Paga 1 maná de cualquier color. Gana 1 carta de ese color de la oferta de Acciones Avanzadas y colócala en tu mano.", "Recibe 1 Herida a tu mano o pila de descarte. Utiliza el efecto avanzado de cualquier carta de la oferta de Acciones Avanzadas sin pagar su coste. La carta permanece en la oferta."), db);
        createCarta(new CartaAccionAvanzada(290, "Golpe de Escudo", false, Cristal.AZUL, "Bloqueo 3. Cuenta el doble contra un ataque Veloz.", "Bloqueo 5. Cuenta el doble contra un ataque Veloz. El enemigo bloqueado obtiene Armadura -1 por cada punto de bloqueo sobrante (hasta un mínimo de 1)."), db);
        createCarta(new CartaAccionAvanzada(291, "Portal Temporal", false, Cristal.AZUL, "Juega esta carta como tu acción en este turno. Puedes moverte a un espacio seguro adyacente revelado (sin provocar enemigos acechantes). Tanto si te mueves como si no, tu límite de mano aumenta en 1 la próxima vez que robes cartas.", "Lo mismo que el efecto básico, excepto que puedes o moverte hacia un espacio seguro 2 espacios en vez de uno, o aumentar tu límite de mano en 2 en vez de 1."), db);
        createCarta(new CartaAccionAvanzada(292, "Hechizo Forjado", false, Cristal.AZUL, "Gana 1 cristal para tu Inventario del mismo color de 1 de los Hechizos en la oferta de Hechizos.", "Gana 2 cristales para tu Inventario de los mismos colores que 2 Hechizos diferentes en la oferta de Hechizos."), db);
        createCarta(new CartaAccionAvanzada(293, "Cortesía", false, Cristal.BLANCO, "Ataque 3, o Ataque 2 y Reputación +1 por cada enemigo derrotado por este ataque.", "Ataque 6, o Ataque 4 y Reputación +1 y Fama +1 por cada enemigo derrotado por este ataque."), db);
        createCarta(new CartaAccionAvanzada(294, "Momento de Paz", false, Cristal.BLANCO, "Influencia 3. Puedes jugar esta carta como tu acción en este turno: si lo haces, puedes obtener Curación 1 por cada 2 de Influencia que hayas gastado.", "Influencia 6. Puedes jugar esta carta como tu acción en este turno: si lo haces, puedes obtener Curación 1 por cada 2 de Influencia que hayas gastado y/o preparar una Unidad pagando 2 de Influencia por cada nivel de la Unidad."), db);
        createCarta(new CartaAccionAvanzada(295, "Esquivar y Evitar", false, Cristal.BLANCO, "Reduce el ataque de un enemigo en 2. Gana Ataque 1 en la fase de Ataque si no añadiste Heridas a tu mano en las fases previas del combate.", "Reduce el ataque de un enemigo en 4 o 2 ataques de 1 o 2 enemigos en 2. Gana Ataque 2 en la fase de Ataque si no añadiste Heridas a tu mano en las fases previas del combate."), db);
        createCarta(new CartaAccionAvanzada(296, "Decisión Valiente", false, Cristal.VERDE, "Movimiento 2, Influencia 2, Ataque 2 o Bloqueo 2. Puedes descartar 1 Herida para aumentar el efecto en 1.", "Movimiento 3, Influencia 3, Ataque 3 o Bloqueo 3. Puedes descartar cualquier número de cartas, incluyendo 1 Herida, para incrementar el efecto en 2 por cada una."), db);
        createCarta(new CartaAccionAvanzada(297, "Fuerza de la Naturaleza", false, Cristal.VERDE, "La Unidad elegida gana Resistencia Física en este combate.", "Ataque de Asedio 3 o Bloqueo 6"), db);
        createCarta(new CartaAccionAvanzada(298, "Sabiduría de la Montaña", false, Cristal.VERDE, "Movimiento 3. Si finalizas tu turno en la colinas, tu límite de Mano aumenta en 1 la próxima vez que robes cartas.", "Movimiento 5. Puedes entrar en Montañas con un coste de Movimiento 5 y se considerarán un espacio seguro para ti hasta el final de este turno. Si finalizas tu turno en montañas/colinas, tu límite de Mano aumenta en 2/1 la próxima vez que robes cartas."), db);

        // 4 cartas de Hechizo (Legión Perdida)
        createCarta(new CartaAccionHechizo(299, "Ofrenda", false, Cristal.ROJO, "Gana un cristal rojo para tu Inventario. Puedes descartar hasta 3 cartas que no sean Herida de tu mano. Por cada carta descartada gana un cristal del mismo color que la carta para tu Inventario (si descartas Artefactos, puedes escoger el color).", "Elige verde o blanco, luego elige rojo o azul. Gana Ataque de Asedio 4 (si escogiste verde) o Ataque a Distancia 6 (si escogiste blanco) por cada pareja de cristales de uno de estos dos colores en tu Inventario. Este Ataque es de Fuego (si escogiste rojo) o Hielo (si escogiste azul). Luego, convierte todas las parejas de cristales en maná.", "Sacrificio"), db);
        createCarta(new CartaAccionHechizo(300, "Forma Nebulosa", false, Cristal.AZUL, "Movimiento 4. El coste de Movimiento de todos los terrenos, incluidos los lagos, es 2. No puedes entrar en colinas y montañas durante el resto de este turno.", "Todas tus Unidades obtienen todas las resistencias. Tu héroe ignora la primera Herida de los enemigos en este turno. Ignora también cualquier efecto adicional de esa Herida.", "Velo Nebuloso"), db);
        createCarta(new CartaAccionHechizo(301, "Encanto", false, Cristal.BLANCO, "Influencia 4. Si usas esta carta durante la interacción puedes ganar un cristal de cualquier color para tu Inventario o bien obtener un descuento de 3 en el coste de una Unidad.", "El enemigo objetivo no ataca. En la fase de Ataque ganas el mismo Ataque que el del enemigo, incluidos sus elementos (si tuviera alguno), pero ignorando las aptitudes especiales. Con ese Ataque solo puedes hacer objetivo a otros enemigos.", "Posesión"), db);
        createCarta(new CartaAccionHechizo(302, "Cura", false, Cristal.VERDE, "Curación 2. Roba una carta por cada Herida curada de tu mano en este turno, y prepara las Unidades curadas en este turno.", "Todos los enemigos bloqueados durante la fase de Bloqueo reducen su Armadura a 1.", "Enfermedad"), db);

        // 16 cartas del Mazo de Gesta de Krang (Acción Básica)
        createCarta(new CartaAccionBasica(337, "Rabia", false, Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", krang), db);
        createCarta(new CartaAccionBasica(338, "Rabia", false, Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", krang), db);
        createCarta(new CartaAccionBasica(339, "Determinación", false, Cristal.AZUL, "Ataque o Bloqueo 2.", "Bloqueo 5.", krang), db);
        createCarta(new CartaAccionBasica(340, "Rapidez", false, Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", krang), db);
        createCarta(new CartaAccionBasica(341, "Rapidez", false, Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", krang), db);
        createCarta(new CartaAccionBasica(342, "Cosecha Salvaje", false, Cristal.VERDE, "Movimiento 2. Una vez en este turno, cuando te muevas un espacio, puedes descartar 1 carta para ganar 1 cristal del mismo color. Si descartas 1 Artefacto puedes escoger el color.", "Movimiento 4. Cada vez que te muevas un espacio en este turno, puedes descartar 1 carta para ganar 1 cristal del mismo color. Si descartas 1 Artefacto puedes escoger el color.", krang), db);
        createCarta(new CartaAccionBasica(343, "Marcha", false, Cristal.VERDE, "Movimiento 2.", "Movimiento 4.", krang), db);
        createCarta(new CartaAccionBasica(344, "Resistencia", false, Cristal.AZUL, "Movimiento 2.", "Movimiento 4.", krang), db);
        createCarta(new CartaAccionBasica(345, "Resistencia", false, Cristal.AZUL, "Movimiento 2.", "Movimiento 4.", krang), db);
        createCarta(new CartaAccionBasica(346, "Tranquilidad", false, Cristal.VERDE, "Curación 1 o roba 1 carta.", "Curación 2 o roba 2 cartas.", krang), db);
        createCarta(new CartaAccionBasica(347, "Promesa", false, Cristal.BLANCO, "Influencia 2.", "Influencia 4.", krang), db);
        createCarta(new CartaAccionBasica(348, "Extorsión Implacable", false, Cristal.ROJO, "Influencia 2. Puedes obtener un descuento de 2 en el coste de reclutamiento de 1 Unidad. Si reclutas esa Unidad en este turno, obtienes Reputación -1.", "Influencia 6. Reputación -1. Puedes preparar Unidades de nivel I y II que controles pagando 2 de Influencia por cada nivel de la Unidad.", krang), db);
        createCarta(new CartaAccionBasica(349, "Cristalización", false, Cristal.AZUL, "Cuando juegues esta carta, paga 1 maná de color básico. Gana 1 cristal de ese color para tu inventario.", "Gana 1 cristal de cualquier color para tu Inventario.", krang), db);
        createCarta(new CartaAccionBasica(350, "Atracción de Maná", false, Cristal.BLANCO, "Puedes usar 1 dado de maná adicional de la Fuente en este turno.", "Toma 1 dado de maná de la Fuente y escoge una cara de cualquier color excepto dorado. Gana 2 fichas de maná de ese color. No relances este dado cuando lo devuelvas a la Fuente.", krang), db);
        createCarta(new CartaAccionBasica(351, "Concentración", false, Cristal.VERDE, "Gana 1 ficha de maná azul, blanco o rojo.", "Cuando juegues esta carta, juega otra carta de Acción con ella. Gana el efecto avanzado de esa carta sin coste. Si el efecto te otorga Movimiento, Influencia, Bloqueo o cualquier tipo de Ataque, gana la cantidad indicada +2.",krang), db);
        createCarta(new CartaAccionBasica(352, "Improvisación", false, Cristal.ROJO, "Descarta otra carta de tu mano para obtener Movimiento 3, Influencia 3, Ataque 3 o Bloqueo 3.", "Descarta otra carta de tu mano para obtener Movimiento 5, Influencia 5, Ataque 5 o Bloqueo 5.", krang), db);

        // 16 cartas del Mazo de Gesta de Braevalar (Acción Básica)
        createCarta(new CartaAccionBasica(356, "Rabia", false, Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", braevalar), db);
        createCarta(new CartaAccionBasica(357, "Rabia", false, Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", braevalar), db);
        createCarta(new CartaAccionBasica(358, "Determinación", false, Cristal.AZUL, "Ataque o Bloqueo 2.", "Bloqueo 5.", braevalar), db);
        createCarta(new CartaAccionBasica(359, "Rapidez", false, Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", braevalar), db);
        createCarta(new CartaAccionBasica(360, "Rapidez", false, Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", braevalar), db);
        createCarta(new CartaAccionBasica(361, "Marcha", false, Cristal.VERDE, "Movimiento 2.", "Movimiento 4.", braevalar), db);
        createCarta(new CartaAccionBasica(362, "Unido con la Tierra", false, Cristal.VERDE, "Movimiento 2, Curación 1, o Bloqueo 2.", "Movimiento 4, Curación 2, o Bloqueo igual al coste de Movimiento sin modificar del espacio en el que te encuentras (Montañas 5, Lagos 2). Bloqueo de Fuego durante el Día y Bloqueo de Hielo durante la Noche.", braevalar), db);
        createCarta(new CartaAccionBasica(363, "Resistencia", false, Cristal.AZUL, "Movimiento 2.", "Movimiento 4.", braevalar), db);
        createCarta(new CartaAccionBasica(364, "Sendas del Druida", false, Cristal.AZUL, "Movimiento 2. El coste de Movimiento de un espacio se reduce en 1 en este turno, hasta un mínimo de 2.", "Movimiento 4. El coste de Movimiento de un tipo de terreno se reduce en 1 en este turno, hasta un mínimo de 2.", braevalar), db);
        createCarta(new CartaAccionBasica(365, "Tranquilidad", false, Cristal.VERDE, "Curación 1 o roba 1 carta.", "Curación 2 o roba 2 cartas.", braevalar), db);
        createCarta(new CartaAccionBasica(366, "Promesa", false, Cristal.BLANCO, "Influencia 2.", "Influencia 4.", braevalar), db);
        createCarta(new CartaAccionBasica(367, "Amenaza", false, Cristal.ROJO, "Influencia 2.", "Influencia 5. Reputación -1.", braevalar), db);
        createCarta(new CartaAccionBasica(368, "Cristalización", false, Cristal.AZUL, "Cuando juegues esta carta, paga 1 maná de color básico. Gana 1 cristal de ese color para tu inventario.", "Gana 1 cristal de cualquier color para tu Inventario.", braevalar), db);
        createCarta(new CartaAccionBasica(369, "Atracción de Maná", false, Cristal.BLANCO, "Puedes usar 1 dado de maná adicional de la Fuente en este turno.", "Toma 1 dado de maná de la Fuente y escoge una cara de cualquier color excepto dorado. Gana 2 fichas de maná de ese color. No relances este dado cuando lo devuelvas a la Fuente.", braevalar), db);
        createCarta(new CartaAccionBasica(370, "Concentración", false, Cristal.VERDE, "Gana 1 ficha de maná azul, blanco o rojo.", "Cuando juegues esta carta, juega otra carta de Acción con ella. Gana el efecto avanzado de esa carta sin coste. Si el efecto te otorga Movimiento, Influencia, Bloqueo o cualquier tipo de Ataque, gana la cantidad indicada +2.", braevalar), db);
        createCarta(new CartaAccionBasica(371, "Improvisación", false, Cristal.ROJO, "Descarta otra carta de tu mano para obtener Movimiento 3, Influencia 3, Ataque 3 o Bloqueo 3.", "Descarta otra carta de tu mano para obtener Movimiento 5, Influencia 5, Ataque 5 o Bloqueo 5.", braevalar), db);

        // Las 70 Fichas de Habilidad
        // 10 Fichas de Habilidad de Arythea
        createFicha(new FichaHabilidad(1, "CAMINOS OSCUROS", "Una vez por turno: Movimiento 1 (durante el Día), o Movimiento 2 (durante la Noche).",false, arythea), db);
        createFicha(new FichaHabilidad(2, "PODER ABRASADOR", "Una vez por turno: Ataque de Asedio 1, o Ataque de Asedio de Fuego 1.",false, arythea), db);
        createFicha(new FichaHabilidad(3, "ESPADA ARDIENTE", "Una vez por turno: Ataque 2, o Ataque de Fuego 2.",false, arythea), db);
        createFicha(new FichaHabilidad(4, "NEGOCIACIÓN TURBIA", "Una vez por turno: Influencia 2 (durante el Día) o Influencia 3 (durante la Noche).",false, arythea), db);
        createFicha(new FichaHabilidad(5, "MAGIA OSCURA DE FUEGO", "Una vez por ronda: Voltea para ganar 1 cristal rojo para tu inventario y 1 ficha de maná rojo o negro.",false, arythea), db);
        createFicha(new FichaHabilidad(6, "EL PODER DEL DOLOR", "Una vez por turno: Puedes jugar 1 Herida girada como si no fuese una carta de Herida. Otorga +2 en vez de +1. Al final de tu turno, coloca esa Herida en tu pila de descarte.",false, arythea), db);
        createFicha(new FichaHabilidad(7, "INVOCACIÓN", "Una vez por turno: Descarta 1 carta de Herida para ganar 1 ficha de maná rojo o negro, o descarta 1 carta que no sea Herida para ganar 1 ficha de maná verde o blanco. El maná conseguido de esta manera debe ser usado inmediatamente, o no podrás usar esta Habilidad.",false, arythea), db);
        createFicha(new FichaHabilidad(8, "POLARIZACIÓN", "Una vez por turno: Puedes usar 1 maná como maná del color opuesto (ver el diagrama). Durante el día, puedes usar maná negro como si fuera otro color (no puedes usarlo como maná negro para potenciar los Hechizos). Durante la noche, puedes usar maná dorado como si fuera maná negro y potenciar un Hechizo, pero no como cualquier otro color.",false, arythea), db);
        createFicha(new FichaHabilidad(9, "MOTIVACIÓN", "Una vez por TipoRonda, en el turno de cualquier jugador: Voltea para robar 2 cartas. Si eres quien menos Fama tiene (no cuenta el empate), gana también 1 ficha de maná rojo. No puedes usar otra Habilidad de Motivación hasta el final de tu siguiente turno.",false, arythea), db);
        createFicha(new FichaHabilidad(10, "RITUAL DEL DOLOR", "Una vez por ronda, excepto durante el combate: Retira hasta 2 cartas de Herida de tu mano. Coloca esta ficha de Habilidad en el centro. Cualquier jugador puede devolvértela, boca abajo, para jugar una carta de Herida girada como si no fuera una carta de Herida, y obtener +3 en vez de +1.",false, arythea), db);

        // 10 Fichas de Habilidad de Tovak
        createFicha(new FichaHabilidad(11, "DOBLE RITMO", "Una vez por turno: Movimiento 2 (durante el día), o Movimiento 1 (durante la Noche).",false, tovak), db);
        createFicha(new FichaHabilidad(12, "PUNTERÍA DE NOCHE", "Una vez por turno: Ataque a Distancia 2 (durante la Noche), o Ataque a Distancia 1 (durante el Día).",false, tovak), db);
        createFicha(new FichaHabilidad(13, "ESPADA GÉLIDA", "Una vez por turno: Ataque 2, o Ataque de Hielo 2.",false, tovak), db);
        createFicha(new FichaHabilidad(14, "DOMINIO DEL ESCUDO", "Una vez por turno: Bloqueo 3, o Bloqueo de Hielo 2, o Bloqueo de Fuego 2.",false, tovak), db);
        createFicha(new FichaHabilidad(15, "RESISTENCIA QUEBRADA", "Una vez por turno: Escoge 1 ficha de enemigo. Obtiene Armadura -1 por cada resistencia que tenga (hasta un mínimo de 1).",false, tovak), db);
        createFicha(new FichaHabilidad(16, "NO SIENTO DOLOR…", "Una vez por turno, excepto durante el combate: Descarta 1 Herida de tu mano. Si lo haces, roba 1 carta.",false, tovak), db);
        createFicha(new FichaHabilidad(17, "¡NO ME IMPORTA NADA!", "Una vez por turno: Una carta que juegues girada otorga +2 en vez de +1. Si es una Acción Avanzada, Hechizo o Artefacto, otorga +3.",false, tovak), db);
        createFicha(new FichaHabilidad(18, "¿QUIÉN NECESITA MAGIA?", "Una vez por turno: Una carta que juegues girada otorga +2 en vez de +1. Si no usas ningún dado de maná de la Fuente en este turno, otorga +3.",false, tovak), db);
        createFicha(new FichaHabilidad(19, "MOTIVACIÓN", "Una vez por TipoRonda, en el turno de cualquier jugador: Voltea para robar 2 cartas. Si eres quien menos Fama tiene (no cuenta el empate), gana también 1 ficha de maná azul. No puedes usar otra Habilidad de Motivación hasta el final de tu siguiente turno.",false, tovak), db);
        createFicha(new FichaHabilidad(20, "SOBRECARGA DE MANÁ", "Una vez por TipoRonda: Elige un color que no sea dorado y gana 1 ficha de maná de ese color. Coloca esta ficha de Habilidad en el centro y márcala con una segunda ficha de maná de ese color. El primer jugador que utilice maná de ese color para potenciar una carta que otorgue Movimiento, Influencia, o cualquier tipo de Ataque o Bloqueo, obtiene +4 para esa carta y te devuelve la ficha de Habilidad, boca abajo.",false, tovak), db);

        // 10 Fichas de Habilidad de Norowas
        createFicha(new FichaHabilidad(21, "¡MARCHA LIGERA!", "Una vez por turno: Gana Movimiento 1 por cada Unidad Preparada y no Herida que controles, hasta un máximo de Movimiento 3.",false, norowas), db);
        createFicha(new FichaHabilidad(22, "PUNTERÍA DE DÍA", "Una vez por turno: Ataque a Distancia 2 (durante el Día), o Ataque a Distancia 1 (durante la Noche).",false, norowas), db);
        createFicha(new FichaHabilidad(23, "INSPIRACIÓN", "Una vez por TipoRonda, excepto durante el combate: Voltea para Preparar o Curar 1 Unidad.",false, norowas), db);
        createFicha(new FichaHabilidad(24, "NEGOCIACIÓN INGENIOSA", "Una vez por turno: Influencia 3 (durante el Día), o Influencia 2 (durante la Noche).",false, norowas), db);
        createFicha(new FichaHabilidad(25, "HOJAS EN EL VIENTO", "Una vez por TipoRonda: Voltea para ganar 1 cristal verde para tu inventario y 1 ficha de maná blanco.",false, norowas), db);
        createFicha(new FichaHabilidad(26, "SUSURRO EN LOS ÁRBOLES", "Una vez por TipoRonda: Voltea para ganar 1 cristal blanco para tu inventario y 1 ficha de maná verde.",false, norowas), db);
        createFicha(new FichaHabilidad(27, "LIDERAZGO", "Una vez por turno: Cuando actives una Unidad, añade +3 a su Bloqueo, o +2 a su Ataque, o +1 a su Ataque a Distancia (no de Asedio), sin importar su elemento.",false, norowas), db);
        createFicha(new FichaHabilidad(28, "VÍNCULOS DE LEALTAD", "Cuando obtengas esta Habilidad, añade 2 Unidades Básicas a la oferta de Unidades. Coloca esta ficha de Habilidad en tu área de Unidades como si fuese una ficha de Mando. Reclutar 1 Unidad y colocarla debajo de esta ficha te cuesta 5 puntos de Influencia menos (mínimo 0). Esta Unidad se puede utilizar incluso cuando el uso de Unidades no esté permitido. No puedes disolverla.",false, norowas), db);
        createFicha(new FichaHabilidad(29, "MOTIVACIÓN", "Una vez por TipoRonda, en el turno de cualquier jugador: Voltea para robar 2 cartas. Si eres quien menos Fama tiene (no cuenta el empate), gana también 1 ficha de maná blanco. No puedes usar otra Habilidad de Motivación hasta el final de tu siguiente turno.",false, norowas), db);
        createFicha(new FichaHabilidad(30, "CALMANDO EL TIEMPO", "Una vez por TipoRonda: El coste de Movimiento de todos los terrenos se reduce en 2 (hasta un mínimo de 1) para ti en este turno. Coloca la ficha de Habilidad en el centro. Cualquier jugador puede devolvértela, boca abajo, para reducir el coste de Movimiento de todos los terrenos en 1 para él en este turno.",false, norowas), db);

        // 10 Fichas de Habilidad de Goldyx
        createFicha(new FichaHabilidad(31, "PODER DE CONGELACIÓN", "Una vez por turno: Ataque de Asedio 1 o Ataque de Asedio de Hielo 1.",false, goldyx), db);
        createFicha(new FichaHabilidad(32, "ELABORACIÓN DE POCIONES", "Una vez por TipoRonda, excepto durante el combate: Voltea para obtener Curación 2.",false, goldyx), db);
        createFicha(new FichaHabilidad(33, "ARTESANÍA DE CRISTAL BLANCO", "Una vez por TipoRonda: Voltea para obtener 1 cristal azul para tu inventario y 1 ficha de maná blanco.",false, goldyx), db);
        createFicha(new FichaHabilidad(34, "ARTESANÍA DE CRISTAL VERDE", "Una vez por TipoRonda: Voltea para obtener 1 cristal azul para tu inventario y una ficha de maná verde.",false, goldyx), db);
        createFicha(new FichaHabilidad(35, "ARTESANÍA DE CRISTAL ROJO", "Una vez por TipoRonda: Voltea para obtener 1 cristal azul para tu inventario y 1 ficha de maná rojo.",false, goldyx), db);
        createFicha(new FichaHabilidad(36, "FORTUNA RELUCIENTE", "Una vez por turno, durante la interacción: Gana Influencia +1 por cada cristal de color diferente en tu inventario. Esta Influencia no puede ser utilizada fuera de una interacción.",false, goldyx), db);
        createFicha(new FichaHabilidad(37, "VUELO", "Una vez por TipoRonda: Voltea para moverte a 1 espacio adyacente sin coste, o a 2 espacios por 2 puntos de Movimiento. Debes terminar este Movimiento en un espacio seguro. Este Movimiento no provoca a los enemigos acechantes.",false, goldyx), db);
        createFicha(new FichaHabilidad(38, "PODER UNIVERSAL", "Una vez por turno: Puedes añadir maná a una carta girada. Si lo haces, la carta otorga +3 en vez de +1. Si es una Acción o un Hechizo del mismo color que el maná, otorga +4.",false, goldyx), db);
        createFicha(new FichaHabilidad(39, "MOTIVACIÓN", "Una vez por TipoRonda, en el turno de cualquier jugador: Voltea para robar 2 cartas. Si eres quien menos Fama tiene (no cuenta el empate), gana también 1 ficha de maná verde. No puedes usar otra Habilidad de Motivación hasta el final de tu siguiente turno.",false, goldyx), db);
        createFicha(new FichaHabilidad(40, "APERTURA DE LA FUENTE", "Una vez por TipoRonda: Coloca esta ficha de Habilidad en el centro. Puedes relanzar un dado de maná en la Fuente. Cualquier jugador puede elegir devolverte la ficha, boca abajo, para usar 1 dado de maná adicional de color básico de la Fuente y darte 1 cristal de ese color. Pueden decidir relanzar o no ese dado al final de su turno.",false, goldyx), db);

        // 10 Fichas de Habilidad de Wolfhawk
        createFicha(new FichaHabilidad(41, "BAÑO REFRESCANTE", "Una vez por TipoRonda, excepto durante el combate: Voltea para obtener Curación 1 y 1 cristal azul para tu Inventario.",false, wolfhawk), db);
        createFicha(new FichaHabilidad(42, "BRISA REFRESCANTE", "Una vez por TipoRonda, excepto durante el combate: Voltea para obtener Curación 1 y 1 cristal blanco para tu Inventario.",false, wolfhawk), db);
        createFicha(new FichaHabilidad(43, "OJOS DE HALCÓN", "Una vez por turno: Movimiento 1. Además: durante la Noche, la exploración cuesta 1 Movimiento menos; durante el Día, puedes revelar defensores de lugares fortificados a una distancia de 2.",false, wolfhawk), db);
        createFicha(new FichaHabilidad(44, "POR SU CUENTA", "Una vez por turno: Influencia 1. Influencia 3 si no reclutas ninguna Unidad usando Influencia en este turno.",false, wolfhawk), db);
        createFicha(new FichaHabilidad(45, "PUNTERÍA MORTAL", "Una vez por turno: Añade +1 a una carta que otorgue cualquier tipo de Ataque en la fase de Ataque a Distancia y de Asedio, o añade +2 a una carta que otorgue cualquier tipo de Ataque (incluidas las cartas giradas) en la fase de Ataque.",false, wolfhawk), db);
        createFicha(new FichaHabilidad(46, "CONOCE A TU PRESA", "Una vez por TipoRonda, durante el combate: Voltea para ignorar una aptitud ofensiva o defensiva de una ficha de enemigo, o eliminar un elemento de un ataque enemigo (Hielo y Fuego se convierten en Físico, Fuego Frío se convierte en Hielo o Fuego). No se puede utilizar contra enemigos con Inmunidad Arcana.",false, wolfhawk), db);
        createFicha(new FichaHabilidad(47, "BURLA", "Una vez por turno, durante la fase de Bloqueo: Puedes o reducir un ataque de un enemigo en 1, o incrementar un ataque de un enemigo en 2 y reducir la Armadura del mismo enemigo en 2 (hasta un mínimo de 1). La reducción de la Armadura no sucede si como resultado el enemigo no puede atacar.",false, wolfhawk), db);
        createFicha(new FichaHabilidad(48, "DUELO", "Una vez por turno, durante la fase de Bloqueo: Bloqueo 1. Ataque 1 contra el mismo enemigo en la fase de Ataque. Si no usas aptitudes de ninguna Unidad para bloquear, atacar, afectar a este enemigo ni asignas daño del enemigo a cualquier Unidad, ganas 1 más de Fama por derrotarlo. JcJ: Bloqueo 1 en la fase de Ataques Cuerpo a Cuerpo, Ataque 1 en tu próximo Ataque, Fama 1 si no has usado aptitudes de ninguna Unidad durante este Bloqueo y Ataque.",false, wolfhawk), db);
        createFicha(new FichaHabilidad(49, "MOTIVACIÓN", "Una vez por TipoRonda, en el turno de cualquier jugador: Voltea la ficha para robar 2 cartas. Si eres quien menos Fama tiene (sin contar empates), gana también Fama 1. No puedes usar otra Habilidad de Motivación hasta el final de tu siguiente turno.",false, wolfhawk), db);
        createFicha(new FichaHabilidad(50, "AULLIDO DE LA MANADA", "Una vez por TipoRonda, excepto durante la interacción: Una carta girada otorga +4 en vez de +1. Por cada una de tus fichas de Mando sin Unidades asignadas, gana otro +1. Coloca esta ficha de Habilidad en el centro. El primer jugador que entre en combate con enemigos te devuelve la Habilidad boca abajo, reduce la Armadura de un enemigo elegido en 1, y un ataque del mismo u otro enemigo en 1.",false, wolfhawk), db);

        // 10 Fichas de Habilidad de Krang
        createFicha(new FichaHabilidad(51, "GUÍAS ESPIRITUALES", "Una vez por turno: Movimiento 1 y puedes añadir +1 a un Bloqueo de cualquier tipo en la fase de Bloqueo.",false, krang), db);
        createFicha(new FichaHabilidad(52, "CURTIDO EN BATALLAS", "Una vez por turno: Ignora o los siguientes 2 puntos de daño asignados a tu Héroe de un Ataque Físico o 1 punto de daño de un Ataque no físico.",false, krang), db);
        createFicha(new FichaHabilidad(53, "FRENESÍ DE LA BATALLA", "Una vez por turno: Ataque 2, o puedes voltear esta ficha para obtener Ataque 4 en este turno. Si usas tu turno para descansar, puedes voltear esta ficha de nuevo.",false, krang), db);
        createFicha(new FichaHabilidad(54, "RITUAL DEL CHAMÁN", "Voltea para ganar una ficha de maná de cualquier color. Puedes voltearla de nuevo como tu acción en este turno.",false, krang), db);
        createFicha(new FichaHabilidad(55, "REGENERAR", "Una vez por turno: Paga un maná de cualquier color y retira una Herida de tu mano. Si para hacerlo usaste maná rojo, o si eres quien menos Fama tiene (sin contar empates), roba también una carta.",false, krang), db);
        createFicha(new FichaHabilidad(56, "DISFRAZ ARCANO", "Una vez por turno: Influencia 2, o puedes voltear esta ficha para ignorar todos los efectos de tu posición en el marcador de Reputación en este turno. Puedes voltearla de nuevo en cualquier turno pagando un maná verde.",false, krang), db);
        createFicha(new FichaHabilidad(57, "MAESTRO DE MARIONETAS", "Una vez por turno: Puedes o quedarte 1 ficha de enemigo que hayas derrotado en este turno o descartar 1 ficha que te hayas quedado previamente. Si decides descartar, obtienes Ataque(s) igual a la mitad de su valor de Ataque o Bloqueo igual a la mitad de su valor de Armadura (todo redondeando hacia arriba). Si el Ataque de la ficha es de Hielo o Fuego, el Ataque que obtienes también lo será. Si la ficha tiene Resistencia al Hielo o Fuego, el Bloqueo que obtienes será del elemento opuesto.",false, krang), db);
        createFicha(new FichaHabilidad(58, "MAESTRO DEL CAOS", "Cuando ganes esta Habilidad, lanza 1 dado de maná y márcalo con 1 ficha de Escudo encima del color lanzado. Una vez por turno puedes mover la ficha de Escudo al siguiente color en sentido horario para obtener, dependiendo del color: Azul: Bloqueo 3, Verde: Movimiento 1, Negro: Ataque a Distancia de Fuego Frío 1, Blanco: Influencia 2, Rojo: Ataque 2, Dorado: Tú eliges. Si no usas esta Habilidad durante tu turno, podrás mover la ficha de Escudo al siguiente color en sentido horario en cualquier momento antes de tu siguiente turno.",false, krang), db);
        createFicha(new FichaHabilidad(59, "MALDICIÓN", "Una vez por turno: Puedes reducir un Ataque de un enemigo en 1 o reducir la Armadura de un enemigo en 1 (hasta un mínimo de 1). No puedes usar esta Habilidad contra un enemigo fortificado en las fases de Ataque a Distancia y de Asedio.",false, krang), db);
        createFicha(new FichaHabilidad(60, "AUMENTO DE MANÁ", "Una vez por TipoRonda: Cuando gastes maná de un color básico, coloca esta ficha de Habilidad en el centro y márcala con 1 ficha de maná del mismo color. Gana 1 cristal de ese color. Hasta el comienzo de tu siguiente turno, cualquier jugador puede devolverte la ficha de Habilidad boca abajo, para usar la ficha de maná que está encima de ella.",false, krang), db);

        // 10 Fichas de Habilidad de Braevalar
        createFicha(new FichaHabilidad(61, "RESISTENCIA ELEMENTAL", "Una vez por turno: Ignora los 2 próximos puntos de daño asignados a tu Héroe de un Ataque de Fuego o Hielo o bien 1 punto de daño de otro tipo de Ataque.",false, braevalar), db);
        createFicha(new FichaHabilidad(62, "ALIADOS SALVAJES", "Explorar cuesta 1 menos de Movimiento. Una vez por turno: Ataque 1, o reduce un ataque de un enemigo en 1.",false, braevalar), db);
        createFicha(new FichaHabilidad(63, "TEMPESTAD", "Una vez por TipoRonda: Voltea para ganar una ficha de maná verde o azul, y una ficha de maná verde o blanca.",false, braevalar), db);
        createFicha(new FichaHabilidad(64, "TORMENTA ELÉCTRICA", "Una vez por TipoRonda: Voltea para ganar una ficha de maná azul o verde, y una ficha de maná azul o roja.",false, braevalar), db);
        createFicha(new FichaHabilidad(65, "CAUTIVAR", "Una vez por turno: Influencia 3, Influencia 2 en un sitio fortificado, o Influencia 4 en un Claro Mágico.",false, braevalar), db);
        createFicha(new FichaHabilidad(66, "RAYO BIFURCADO", "Una vez por turno: Ataque a Distancia de Fuego Frío 1 contra hasta 3 enemigos diferentes.",false, braevalar), db);
        createFicha(new FichaHabilidad(67, "CAMBIAFORMAS", "Una vez por turno: Una carta de Acción Básica que otorgue un número concreto de Movimiento/Ataque/Bloqueo, en vez de su acción habitual puede ofrecer la misma cantidad de cualquiera de las otras dos en su lugar (los tipos de elementos se conservan en Ataques y Bloqueos).",false, braevalar), db);
        createFicha(new FichaHabilidad(68, "CAMINOS SECRETOS", "Una vez por turno: Movimiento 1. Puedes entrar en montañas por un coste de Movimiento de 5 y se consideran un espacio seguro para ti. Si pagas 1 maná azul, puedes entrar en lagos por un coste de Movimiento de 2 en este turno y los lagos se consideran un espacio seguro para ti al final de este turno.",false, braevalar), db);
        createFicha(new FichaHabilidad(69, "REGENERAR", "Una vez por turno: Paga 1 maná de cualquier color y retira una carta de Herida de tu mano. Si usas maná verde, o si eres quien menos Fama tiene (sin contar empates), roba también 1 carta.",false, braevalar), db);
        createFicha(new FichaHabilidad(70, "APOYO DE LA NATURALEZA", "Una vez por TipoRonda: Reduce un ataque de un enemigo en 1, ese enemigo gana la aptitud de \"Pesado\" en este turno. Coloca esta ficha de Habilidad en el centro. Hasta el comienzo de tu siguiente turno, cualquier jugador puede devolvértela boca abajo, para reducir un ataque de un enemigo en 1 y darle a esa ficha de enemigo la aptitud de \"Pesado\" en este turno.",false, braevalar), db);


    }

    private void insertGameStatus(SQLiteDatabase db){
        //Estado de la Partida
        insertEstadoPartida(TipoEstado.EN_PREPARACION.toString(), null, true, false, 0, 0, db);
    }

    // *******************************************************

    private Heroe getHeroeByName(String heroeName){
        Heroe heroe = new Heroe(heroeName, getHeroeCristalsByName(heroeName));
        return heroe;
    }

    private ArrayList<Cristal> getHeroeCristalsByName(String heroeName){

        ArrayList<Cristal> cristales = new ArrayList<>();

        switch(heroeName){
            case "Arythea": Collections.addAll(cristales, Cristal.ROJO, Cristal.ROJO, Cristal.BLANCO);
                break;
            case "Tovak": Collections.addAll( cristales, Cristal.VERDE, Cristal.VERDE, Cristal.AZUL);
                break;
            case "Norowas": Collections.addAll( cristales, Cristal.AZUL, Cristal.AZUL, Cristal.ROJO);
                break;
            case "Goldyx": Collections.addAll( cristales, Cristal.BLANCO, Cristal.BLANCO, Cristal.VERDE);
                break;
            case "Wolfhawk":  Collections.addAll( cristales, Cristal.BLANCO, Cristal.BLANCO, Cristal.AZUL);
                break;
            case "Krang": Collections.addAll( cristales, Cristal.ROJO, Cristal.ROJO, Cristal.VERDE);
                break;
            case "Braevalar": Collections.addAll( cristales, Cristal.AZUL, Cristal.AZUL, Cristal.VERDE);
                break;
        }

        return cristales;
    }

    //Insertar datos en su correspondiente tabla
    private void createHeroe(Heroe heroe, SQLiteDatabase db){
        insertDataHeroe(heroe.getNombre(), db);
    }

    private void createCristal(Cristal cristal, SQLiteDatabase db){
        insertDataCristal(cristal.toString(), db);
    }

    private void createHeroeCristal(Heroe heroe, Cristal cristal, SQLiteDatabase db){
        insertDataHeroeCristal(heroe.getNombre(), cristal.toString(), db);
    }

    private void createCarta(Carta carta, SQLiteDatabase db){
        // 1.- Se guarda la información genérica, independientemente del tipo de carta (tabla CARTAS)
        insertDataCarta(carta.getNumero(), carta.getNombre(), carta.isDescartada(), db);

        // 2.- Si la carta es de tipo TACTICA, insertamos en su tabla CARTAS_TACTICAS todos los datos y salimos del método
        if(carta.getClass() == CartaTactica.class){
            CartaTactica cartaTactica = (CartaTactica) carta;
            insertDataCartaTactica(carta.getNumero(), cartaTactica.getTipoTactica().toString(), cartaTactica.getNumeroOrden(), cartaTactica.getDescripcion(), db);
            return;
        }

        // 3.- Si la carta NO es de tipo TACTICA, sólo puede ser de tipo ACCION. Insertaremos los datos en su tabla CARTAS_ACCIONES
        CartaAccion cartaAccion = (CartaAccion) carta;

        insertDataCartaAccion(carta.getNumero(), cartaAccion.getColor().toString(), cartaAccion.getDescripcionBasica(), cartaAccion.getDescripcionAvanzada(), db);

        // 4.- Según el tipo específico de carta de ACCIÓN, insertaremos los datos extra de las cartas (AccionBasica, AccionAvanzada, AccionAvanzadaEspecial, Hechizo)
        if (carta.getClass() == CartaAccionBasica.class){
            CartaAccionBasica cartaAccionBasica = (CartaAccionBasica) cartaAccion;
            insertDataCartaAccionBasica(carta.getNumero(),cartaAccionBasica.getHeroe().getNombre(), db);
        } else if(carta.getClass() == CartaAccionAvanzada.class){
            insertDataCartaAccionAvanzada(carta.getNumero(), db);
        } else if(carta.getClass() == CartaAccionAvanzadaEspecial.class) {
            CartaAccionAvanzadaEspecial cartaAccionAvanzadaEspecial = (CartaAccionAvanzadaEspecial) cartaAccion;
            insertDataCartaAccionAvanzadaEspecial(carta.getNumero(), cartaAccionAvanzadaEspecial.getColorSecundario().toString(), db);
        } else {  //(carta.getClass() == Hechizo.class)
            CartaAccionHechizo cartaAccionHechizohechizo = (CartaAccionHechizo) cartaAccion;
            insertDataCartaHechizo(carta.getNumero(), cartaAccionHechizohechizo.getNombreSecundario(), db);
        }
    }

    //Insertar datos de la Ficha de Habilidad en su correspondiente tabla
    private void createFicha(FichaHabilidad fichaHabilidad, SQLiteDatabase db){
        insertDataFichaHabilidad(fichaHabilidad.getIdFicha(), fichaHabilidad.getNombre(), fichaHabilidad.getDescripcion(),  fichaHabilidad.isDescartada(), fichaHabilidad.getHeroe().getNombre(), db);
    }

    //Métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
    private boolean insertEstadoPartida(String estadoPartida, String rondaPartida, boolean esRondaInicio, boolean esRondaFinalizada, int turno, int experiencia, SQLiteDatabase db){

        //Necesito una referencia a la base de datos como tal
        //SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

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
    private boolean insertDataHeroe(String nombre, SQLiteDatabase db){
        //Necesito una referencia a la base de datos como tal
        //SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_HEROES_TABLE, nombre);

        long resultado = db.insert(HEROES_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    //Métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
    private boolean insertDataCristal(String cristal, SQLiteDatabase db){
        //Necesito una referencia a la base de datos como tal
        //SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_CRISTALES_TABLE, cristal);

        long resultado = db.insert(CRISTALES_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    //Métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
    private boolean insertDataHeroeCristal(String nombre, String cristal, SQLiteDatabase db){

        //Necesito una referencia a la base de datos como tal
        //SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_HEROES_CRISTALES_TABLE, nombre);
        contentValues.put(COL_2_HEROES_CRISTALES_TABLE, cristal);

        long resultado = db.insert(HEROES_CRISTALES_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    //Métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
    private boolean insertDataCarta(int numero, String nombre, boolean isDescartada, SQLiteDatabase db){

        //Necesito una referencia a la base de datos como tal
        //SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_CARTAS_TABLE, numero);
        contentValues.put(COL_2_CARTAS_TABLE, nombre);
        contentValues.put(COL_3_CARTAS_TABLE, isDescartada);

        long resultado = db.insert(CARTAS_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    //Métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
    private boolean insertDataCartaAccion(int numero, String color, String descripcionBasica, String descripcionAvanzada, SQLiteDatabase db){

        //Necesito una referencia a la base de datos como tal
        //SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_CARTAS_ACCIONES_TABLE, numero);
        contentValues.put(COL_2_CARTAS_ACCIONES_TABLE, color);
        contentValues.put(COL_3_CARTAS_ACCIONES_TABLE, descripcionBasica);
        contentValues.put(COL_4_CARTAS_ACCIONES_TABLE, descripcionAvanzada);

        long resultado = db.insert(CARTAS_ACCIONES_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    //Métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
    private boolean insertDataCartaAccionBasica(int numero, String heroeName, SQLiteDatabase db){

        //Necesito una referencia a la base de datos como tal
        //SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_CARTAS_ACCIONES_BASICAS_TABLE, numero);
        contentValues.put(COL_2_CARTAS_ACCIONES_BASICAS_TABLE, heroeName);

        long resultado = db.insert(CARTAS_ACCIONES_BASICAS_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    //Métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
    private boolean insertDataCartaAccionAvanzada(int numero, SQLiteDatabase db){

        //Necesito una referencia a la base de datos como tal
        //SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_CARTAS_ACCIONES_AVANZADAS_TABLE, numero);

        long resultado = db.insert(CARTAS_ACCIONES_AVANZADAS_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    //Métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
    private boolean insertDataCartaAccionAvanzadaEspecial(int numero, String colorSecundario, SQLiteDatabase db){

        //Necesito una referencia a la base de datos como tal
        //SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_CARTAS_ACCIONES_AVANZADAS_ESPECIALES_TABLE, numero);
        contentValues.put(COL_2_CARTAS_ACCIONES_AVANZADAS_ESPECIALES_TABLE, colorSecundario);

        long resultado = db.insert(CARTAS_ACCIONES_AVANZADAS_ESPECIALES_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    //Métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
    private boolean insertDataCartaHechizo(int numero, String colorSecundario, SQLiteDatabase db){

        //Necesito una referencia a la base de datos como tal
        //SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_CARTAS_HECHIZOS_TABLE, numero);
        contentValues.put(COL_2_CARTAS_HECHIZOS_TABLE, colorSecundario);

        long resultado = db.insert(CARTAS_HECHIZOS_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    //Métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
    private boolean insertDataCartaTactica(int numero, String tipoTactica, int numeroOrden, String descripcion, SQLiteDatabase db){

        //Necesito una referencia a la base de datos como tal
        //SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_CARTAS_TACTICAS_TABLE, numero);
        contentValues.put(COL_2_CARTAS_TACTICAS_TABLE, tipoTactica);
        contentValues.put(COL_3_CARTAS_TACTICAS_TABLE, numeroOrden);
        contentValues.put(COL_4_CARTAS_TACTICAS_TABLE, descripcion);

        long resultado = db.insert(CARTAS_TACTICAS_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    //Métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
    private boolean insertDataFichaHabilidad(int idFicha, String nombre, String descripcion, boolean isDescartada, String heroeName, SQLiteDatabase db){

        //Necesito una referencia a la base de datos como tal
        //SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva

        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_FICHAS_HABILIDAD_TABLE, idFicha);
        contentValues.put(COL_2_FICHAS_HABILIDAD_TABLE, nombre);
        contentValues.put(COL_3_FICHAS_HABILIDAD_TABLE, descripcion);
        contentValues.put(COL_4_FICHAS_HABILIDAD_TABLE, isDescartada);
        contentValues.put(COL_5_FICHAS_HABILIDAD_TABLE, heroeName);

        long resultado = db.insert(FICHAS_HABILIDAD_TABLE, null, contentValues);

        //Log.d("DATABASE","INSERT FICHA HABILIDAD -> ID FICHA:"+idFicha+" - Nombre: "+nombre+" - Resultado: "+resultado);
        //Si 'resultado' es igual a -1 es que algo ha ido mal - Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados
        return resultado == -1 ? false : true;
    }

    //CURSORES
    //Un 'Cursor' es una tabla virtual
    public Cursor getAllHeroesCursor(){
        SQLiteDatabase db = getWritableDatabase();

        // 'selectionArgs' es un array de Strings -> Array[]
        // En la consulta pueden haber ?s que serán sustituidos por los valores de este array de String
        Cursor cursor = db.rawQuery("SELECT * FROM "+ HEROES_TABLE, null);

        //Ejemplo
        // SELECT * FROM AMIGOS WHERE nombre=? AND apellido LIKE '?%';
        // String[] = {"Adolfo","D"};
        return cursor;
    }

    public Cursor getAllCristalesCursor(){
        SQLiteDatabase db = getWritableDatabase();

        // 'selectionArgs' es un array de Strings -> Array[]
        // En la consulta pueden haber ?s que serán sustituidos por los valores de este array de String
        Cursor cursor = db.rawQuery("SELECT * FROM "+ CRISTALES_TABLE, null);

        return cursor;
    }

    public Cursor getAllHeroesCristalesCursor(){
        SQLiteDatabase db = getWritableDatabase();

        // 'selectionArgs' es un array de Strings -> Array[]
        // En la consulta pueden haber ?s que serán sustituidos por los valores de este array de String
        Cursor cursor = db.rawQuery("SELECT * FROM "+ HEROES_CRISTALES_TABLE, null);

        return cursor;
    }

    public Cursor getAllCartasCursor(){
        SQLiteDatabase db = getWritableDatabase();

        // 'selectionArgs' es un array de Strings -> Array[]
        // En la consulta pueden haber ?s que serán sustituidos por los valores de este array de String
        Cursor cursor = db.rawQuery("SELECT * FROM "+ CARTAS_TABLE, null);

        return cursor;
    }

    public Cursor getAllCartasAccionesCursor(){
        SQLiteDatabase db = getWritableDatabase();

        // 'selectionArgs' es un array de Strings -> Array[]
        // En la consulta pueden haber ?s que serán sustituidos por los valores de este array de String
        Cursor cursor = db.rawQuery("SELECT * FROM "+ CARTAS_ACCIONES_TABLE, null);

        return cursor;
    }

    public Cursor getAllCartasAccionesBasicasCursor(){
        SQLiteDatabase db = getWritableDatabase();

        // 'selectionArgs' es un array de Strings -> Array[]
        // En la consulta pueden haber ?s que serán sustituidos por los valores de este array de String
        Cursor cursor = db.rawQuery("SELECT * FROM "+ CARTAS_ACCIONES_BASICAS_TABLE, null);

        return cursor;
    }

    public Cursor getAllCartasAccionesAvanzadasCursor(){
        SQLiteDatabase db = getWritableDatabase();

        // 'selectionArgs' es un array de Strings -> Array[]
        // En la consulta pueden haber ?s que serán sustituidos por los valores de este array de String
        Cursor cursor = db.rawQuery("SELECT * FROM "+ CARTAS_ACCIONES_AVANZADAS_TABLE, null);

        return cursor;
    }

    public Cursor getAllCartasAccionesAvanzadasEspecialesCursor(){
        SQLiteDatabase db = getWritableDatabase();

        // 'selectionArgs' es un array de Strings -> Array[]
        // En la consulta pueden haber ?s que serán sustituidos por los valores de este array de String
        Cursor cursor = db.rawQuery("SELECT * FROM "+ CARTAS_ACCIONES_AVANZADAS_ESPECIALES_TABLE, null);

        return cursor;
    }

    public Cursor getAllCartasHechizosCursor(){
        SQLiteDatabase db = getWritableDatabase();

        // 'selectionArgs' es un array de Strings -> Array[]
        // En la consulta pueden haber ?s que serán sustituidos por los valores de este array de String
        Cursor cursor = db.rawQuery("SELECT * FROM "+ CARTAS_HECHIZOS_TABLE, null);

        return cursor;
    }

    public Cursor getAllCartasTacticasCursor(){
        SQLiteDatabase db = getWritableDatabase();

        // 'selectionArgs' es un array de Strings -> Array[]
        // En la consulta pueden haber ?s que serán sustituidos por los valores de este array de String
        Cursor cursor = db.rawQuery("SELECT * FROM "+ CARTAS_TACTICAS_TABLE, null);

        return cursor;
    }

    public Cursor getAllFichasHabilidadCursor(){
        SQLiteDatabase db = getWritableDatabase();

        // 'selectionArgs' es un array de Strings -> Array[]
        // En la consulta pueden haber ?s que serán sustituidos por los valores de este array de String
        Cursor cursor = db.rawQuery("SELECT * FROM "+ FICHAS_HABILIDAD_TABLE, null);

        return cursor;
    }

    public Cursor getGameStatusCursor(){
        SQLiteDatabase db = getWritableDatabase();

        // 'selectionArgs' es un array de Strings -> Array[]
        // En la consulta pueden haber ?s que serán sustituidos por los valores de este array de String
        Cursor cursor = db.rawQuery("SELECT * FROM "+ PARTIDA_DATOS_TABLE, null);

        return cursor;
    }

    public Cursor getGameTypeCursor(){
        SQLiteDatabase db = getWritableDatabase();

        // 'selectionArgs' es un array de Strings -> Array[]
        // En la consulta pueden haber ?s que serán sustituidos por los valores de este array de String
        Cursor cursor = db.rawQuery("SELECT * FROM "+ PARTIDA_MODO_TABLE, null);

        return cursor;
    }

    public Cursor getGameRoundInformationCursor(){
        SQLiteDatabase db = getWritableDatabase();

        // 'selectionArgs' es un array de Strings -> Array[]
        // En la consulta pueden haber ?s que serán sustituidos por los valores de este array de String
        Cursor cursor = db.rawQuery("SELECT * FROM "+ PARTIDA_INFORMACION_RONDA_TABLE, null);

        return cursor;
    }

    public Cursor getGameTacticCardsCursor(){
        SQLiteDatabase db = getWritableDatabase();

        // 'selectionArgs' es un array de Strings -> Array[]
        // En la consulta pueden haber ?s que serán sustituidos por los valores de este array de String
        Cursor cursor = db.rawQuery("SELECT * FROM "+ PARTIDA_CARTAS_TACTICAS_TABLE, null);

        return cursor;
    }

    public Cursor getGameHeroesPlayerCursor(){
        SQLiteDatabase db = getWritableDatabase();

        // 'selectionArgs' es un array de Strings -> Array[]
        // En la consulta pueden haber ?s que serán sustituidos por los valores de este array de String
        Cursor cursor = db.rawQuery("SELECT * FROM "+ PARTIDA_HEROES_JUGADOR_TABLE, null);

        return cursor;
    }

    public Cursor getGameHeroeDummyPlayerCursor(){
        SQLiteDatabase db = getWritableDatabase();

        // 'selectionArgs' es un array de Strings -> Array[]
        // En la consulta pueden haber ?s que serán sustituidos por los valores de este array de String
        Cursor cursor = db.rawQuery("SELECT * FROM "+ PARTIDA_HEROE_DUMMY_TABLE, null);

        return cursor;
    }

    public Cursor getGameCardsDummyPlayerCursor(){
        SQLiteDatabase db = getWritableDatabase();

        // 'selectionArgs' es un array de Strings -> Array[]
        // En la consulta pueden haber ?s que serán sustituidos por los valores de este array de String
        Cursor cursor = db.rawQuery("SELECT * FROM "+ PARTIDA_CARTAS_HEROE_DUMMY_TABLE, null);

        return cursor;
    }

    public Cursor getGameSkillTokensDummyPlayerCursor(){
        SQLiteDatabase db = getWritableDatabase();

        // 'selectionArgs' es un array de Strings -> Array[]
        // En la consulta pueden haber ?s que serán sustituidos por los valores de este array de String
        Cursor cursor = db.rawQuery("SELECT * FROM "+ PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE, null);

        return cursor;
    }

    public Cursor getGameCristalsDummyPlayerCursor(){
        SQLiteDatabase db = getWritableDatabase();

        // 'selectionArgs' es un array de Strings -> Array[]
        // En la consulta pueden haber ?s que serán sustituidos por los valores de este array de String
        Cursor cursor = db.rawQuery("SELECT * FROM "+ PARTIDA_CRISTALES_HEROE_DUMMY_TABLE, null);

        return cursor;
    }

}

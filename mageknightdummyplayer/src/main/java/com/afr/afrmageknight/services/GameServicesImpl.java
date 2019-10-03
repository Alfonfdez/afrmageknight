package com.afr.afrmageknight.services;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.afr.afrmageknight.activities.InitialMenuActivity;
import com.afr.afrmageknight.databaseHelper.SQLiteDatabaseHelper;
import com.afr.afrmageknight.model.CartaAccionAvanzada;
import com.afr.afrmageknight.model.CartaAccionAvanzadaEspecial;
import com.afr.afrmageknight.model.CartaAccionBasica;
import com.afr.afrmageknight.model.CartaAccionHechizo;
import com.afr.afrmageknight.model.CartaTactica;
import com.afr.afrmageknight.model.Cristal;
import com.afr.afrmageknight.model.FichaHabilidad;
import com.afr.afrmageknight.model.Heroe;
import com.afr.afrmageknight.model.TipoEstado;
import com.afr.afrmageknight.model.TipoPartida;
import com.afr.afrmageknight.model.TipoRonda;
import com.afr.afrmageknight.model.TipoTactica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameServicesImpl implements GameServices {

    // Nombre de las columnas
    // HEROES_TABLE
    public static final String COL_1_HEROES_TABLE = "NOMBRE";

    // HEROES_CRISTALES_TABLE
    public static final String COL_1_HEROES_CRISTALES_TABLE = "NOMBRE";
    public static final String COL_2_HEROES_CRISTALES_TABLE = "CRISTAL";

    // CARTAS_TABLE
    public static final String COL_1_CARTAS_TABLE = "NUMERO";
    public static final String COL_2_CARTAS_TABLE = "NOMBRE";
    public static final String COL_3_CARTAS_TABLE = "DESCARTADA";

    // CARTAS_ACCIONES_TABLE
    public static final String COL_1_CARTAS_ACCIONES_TABLE = "NUMERO";
    public static final String COL_2_CARTAS_ACCIONES_TABLE = "COLOR";
    public static final String COL_3_CARTAS_ACCIONES_TABLE = "DESCRIPCION_BASICA";
    public static final String COL_4_CARTAS_ACCIONES_TABLE = "DESCRIPCION_AVANZADA";

    // CARTAS_ACCIONES_BASICAS_TABLE
    public static final String COL_1_CARTAS_ACCIONES_BASICAS_TABLE = "NUMERO";
    public static final String COL_2_CARTAS_ACCIONES_BASICAS_TABLE = "HEROE";

    // CARTAS_ACCIONES_AVANZADAS_TABLE
    public static final String COL_1_CARTAS_ACCIONES_AVANZADAS_TABLE = "NUMERO";

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
    public static final String COL_1_FICHAS_HABILIDAD_TABLE = "ID_FICHA";
    public static final String COL_2_FICHAS_HABILIDAD_TABLE = "NOMBRE";
    public static final String COL_3_FICHAS_HABILIDAD_TABLE = "DESCRIPCION";
    public static final String COL_5_FICHAS_HABILIDAD_TABLE = "HEROE";

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

    //PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE
    protected static final String COL_1_PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE = "NUMERO";
    protected static final String COL_2_PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE = "NOMBRE";
    protected static final String COL_3_PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE = "DESCARTADA";
    protected static final String COL_4_PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE = "COLOR";
    protected static final String COL_5_PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE = "COLOR_SECUNDARIO";
    protected static final String COL_6_PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE = "DESCRIPCION_BASICA";
    protected static final String COL_7_PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE = "DESCRIPCION_AVANZADA";
    protected static final String COL_8_PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE = "ESPECIAL";

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


    // DatabaseHel no entrega a través de sus métodos ni Heroes ni Cartas ni Cristales. Sólo cursores

    //I - Declarar las variables
    private SQLiteDatabaseHelper myDB;

    private Cursor heroesCursor;
    private Cursor heroesCristalesCursor;
    private Cursor cartasCursor;
    private Cursor cartasTacticasCursor;
    private Cursor cartasAccionesCursor;
    private Cursor cartasAccionesBasicasCursor;
    private Cursor fichasHabilidadesCursor;
    private Cursor cartasAccionesAvanzadasCursor;
    private Cursor cartasAccionesAvanzadasEspecialesCursor;
    private Cursor cartasAccionesHechizosCursor;

    private Cursor partidaEstadoCursor;
    private Cursor partidaModoCursor;
    private Cursor partidaInformacionRondaCursor;
    private Cursor partidaHeroeDummyCursor;
    private Cursor partidaCristalesDummyCursor;
    private Cursor partidaCartasDummyCursor;
    private Cursor partidaCartasAccionAvanzadaYEspecialCursor;
    private Cursor partidaCartasTacticas;
    private Cursor partidaFichasHabilidad;

    //Constructor
    public GameServicesImpl (Context context){
        myDB = InitialMenuActivity.myDB;
    }


    // Implementar los métodos de la interfaz 'GameServices'
    @Override
    public Heroe getAHeroeSelectedByPlayer(String heroeName) {
        Heroe heroe = new Heroe(heroeName, getCristalesFromAHeroe(heroeName));
        return heroe;
    }

    @Override
    public List<Heroe> getHeroesSelectedByPlayer(List<String> heroeNames) {

        List<Heroe> heroes = new ArrayList<Heroe>();

        for(String heroeName : heroeNames){
            Heroe heroe = new Heroe(heroeName, getCristalesFromAHeroe(heroeName));
            heroes.add(heroe);
        }

        return heroes;
    }

    @Override
    public ArrayList<Cristal> getCristalesFromAHeroe(String heroeName) {
        heroesCristalesCursor = myDB.getAllHeroesCristalesCursor();

        ArrayList<Cristal> cristalesHeroe = new ArrayList<Cristal>();

        if (heroesCristalesCursor.moveToFirst()){
            int i = 0;
            do{
                String heroeNameTable = heroesCristalesCursor.getString(heroesCristalesCursor.getColumnIndex(COL_1_HEROES_CRISTALES_TABLE));

                if(heroeNameTable.equals(heroeName)){
                    String cristalNameTable = heroesCristalesCursor.getString(heroesCristalesCursor.getColumnIndex(COL_2_HEROES_CRISTALES_TABLE));
                    cristalesHeroe.add(Cristal.valueOf(cristalNameTable));

                    Log.d("DATABASE","Heroe: "+heroeNameTable+" - Cristal: "+String.valueOf(cristalNameTable));
                    ++i;
                }

            }while(heroesCristalesCursor.moveToNext() && i<3);
        }
        heroesCristalesCursor.close();

        return cristalesHeroe;
    }

    @Override
    public Heroe getRandomHeroeFromOneHeroeSelectedByPlayer(Heroe heroeSelectedByPlayer) {

        List<String> heroeNames = new ArrayList<>();
        heroesCursor = myDB.getAllHeroesCursor();

        if(heroesCursor.moveToFirst()){
            do{
                String heroNameTable = heroesCursor.getString(heroesCursor.getColumnIndex(COL_1_HEROES_TABLE));
                heroeNames.add(heroNameTable);
            }while(heroesCursor.moveToNext());
        }
        heroesCursor.close();

        heroeNames.remove(heroeSelectedByPlayer.getNombre());

        //Selecciona un número aleatorio entre 0 y 5, ambos incluidos (heroeNames.size()=6)
        int randomNumber = (int)(Math.random() * heroeNames.size());
        String randomHeroeNameSelected = heroeNames.get(randomNumber);

        Heroe heroeDummyPlayer = new Heroe(randomHeroeNameSelected, getCristalesFromAHeroe(randomHeroeNameSelected));

        return heroeDummyPlayer;
    }

    @Override
    public Heroe getRandomHeroeFromHeroesSelectedByPlayer(List<Heroe> heroesSelectedByPlayer) {

        List<String> heroeNames = new ArrayList<>();
        heroesCursor = myDB.getAllHeroesCursor();

        if(heroesCursor.moveToFirst()){
            do{
                String heroNameTable = heroesCursor.getString(heroesCursor.getColumnIndex(COL_1_HEROES_TABLE));
                heroeNames.add(heroNameTable);
            }while(heroesCursor.moveToNext());
        }
        heroesCursor.close();

        for(Heroe heroe : heroesSelectedByPlayer){
            heroeNames.remove(heroe.getNombre());
        }

        //Selecciona un número aleatorio entre: 0 y 4, ambos incluidos, para 2 héroes seleccionados por el jugador (heroeNames.size()=5)
        //Selecciona un número aleatorio entre: 0 y 3, ambos incluidos, para 3 héroes seleccionados por el jugador (heroeNames.size()=4)
        int randomNumber = (int)(Math.random() * heroeNames.size());
        String randomHeroeNameSelected = heroeNames.get(randomNumber);

        Heroe heroeDummyPlayer = new Heroe(randomHeroeNameSelected, getCristalesFromAHeroe(randomHeroeNameSelected));

        return heroeDummyPlayer;
    }

    // ********************************************

    @Override
    public List<CartaTactica> getTacticCards() {

        List<CartaTactica> cartasTacticas = new ArrayList<CartaTactica>();
        cartasTacticasCursor = myDB.getAllCartasTacticasCursor();

        if(cartasTacticasCursor.moveToFirst()){
            String nombreCartaTactica = "";
            do{
                int numeroCartaTactica = cartasTacticasCursor.getInt(cartasTacticasCursor.getColumnIndex(COL_1_CARTAS_TACTICAS_TABLE));
                nombreCartaTactica = getNameCard(numeroCartaTactica);
                String tipoCartaTactica = cartasTacticasCursor.getString(cartasTacticasCursor.getColumnIndex(COL_2_CARTAS_TACTICAS_TABLE));
                int numeroOrdenCartaTactica = cartasTacticasCursor.getInt(cartasTacticasCursor.getColumnIndex(COL_3_CARTAS_TACTICAS_TABLE));
                String descripcionCartaTactica = cartasTacticasCursor.getString(cartasTacticasCursor.getColumnIndex(COL_4_CARTAS_TACTICAS_TABLE));

                CartaTactica cartaTactica = new CartaTactica(numeroCartaTactica, nombreCartaTactica, false, TipoTactica.valueOf(tipoCartaTactica),numeroOrdenCartaTactica,descripcionCartaTactica);
                cartasTacticas.add(cartaTactica);
            }while(cartasTacticasCursor.moveToNext());
        }
        cartasTacticasCursor.close();

        return cartasTacticas;
    }

    @Override
    public String getNameCard(int cardNumber) {
        cartasCursor = myDB.getAllCartasCursor();
        String nombreCartaTactica = "";

        if(cartasCursor.moveToFirst()){
            int i = 0;
            do{
                if(cardNumber==cartasCursor.getInt(cartasCursor.getColumnIndex(COL_1_CARTAS_TABLE))){
                    nombreCartaTactica = cartasCursor.getString(cartasCursor.getColumnIndex(COL_2_CARTAS_TABLE));
                    ++i;
                }
            }while(cartasCursor.moveToNext() && i < 1);
        }
        cartasCursor.close();

        return nombreCartaTactica;
    }

    @Override
    public List<CartaAccionBasica> getShuffledBasicActionCardsHeroeFromDummyPlayer(Heroe randomHeroeDummyPlayer) {

        List<CartaAccionBasica> cartaAccionBasicasBarajadas = getBasicActionCardsHeroeFromDummyPlayer(randomHeroeDummyPlayer);

        //Barajar aleatoriamente la baraja de cartas de Acción Básica del Jugador Virtual
        Collections.shuffle(cartaAccionBasicasBarajadas);

        for(CartaAccionBasica cartaAccionBasica: cartaAccionBasicasBarajadas){
            Log.d("DATABASE","** Cartas Accion Básica barajadas ** - Número carta Acción Básica: "+cartaAccionBasica.getNumero()+" - Nombre: "+cartaAccionBasica.getNombre()+" - Color: "+cartaAccionBasica.getColor()+ " - Descripción básica: "+cartaAccionBasica.getDescripcionBasica()+ " - Descripción avanzada: "+cartaAccionBasica.getDescripcionAvanzada()+" - Heroe: "+cartaAccionBasica.getHeroe().getNombre());
        }

        return cartaAccionBasicasBarajadas;
    }

    @Override
    public ArrayList<CartaAccionBasica> getBasicActionCardsHeroeFromDummyPlayer(Heroe randomHeroeDummyPlayer) {

        ArrayList<CartaAccionBasica> cartaAccionBasicas = new ArrayList<CartaAccionBasica>();
        cartasAccionesBasicasCursor = myDB.getAllCartasAccionesBasicasCursor();

        if (cartasAccionesBasicasCursor.moveToFirst()){
            int i = 0;
            do{
                String heroeNameTable = cartasAccionesBasicasCursor.getString(cartasAccionesBasicasCursor.getColumnIndex(COL_2_CARTAS_ACCIONES_BASICAS_TABLE));

                if(heroeNameTable.equals(randomHeroeDummyPlayer.getNombre())){
                    int numeroCartaBasica = cartasAccionesBasicasCursor.getInt(cartasAccionesBasicasCursor.getColumnIndex(COL_1_CARTAS_ACCIONES_BASICAS_TABLE));
                    cartaAccionBasicas.add(getBasicActionCard(numeroCartaBasica, randomHeroeDummyPlayer));
                    ++i;
                }
            }while(cartasAccionesBasicasCursor.moveToNext() && i<16);
        }
        cartasAccionesBasicasCursor.close();

        return cartaAccionBasicas;
    }

    @Override
    public CartaAccionBasica getBasicActionCard(int basicActionCardNumber, Heroe randomHeroeDummyPlayer) {

        cartasCursor = myDB.getAllCartasCursor();
        cartasAccionesCursor = myDB.getAllCartasAccionesCursor();

        String nombreCarta = "";
        String cristalColor = "";
        String descripcionBasica = "";
        String descripcionAvanzada = "";

        if (cartasCursor.moveToFirst()){
            int i = 0;
            do{
                int numeroCartaTable = cartasCursor.getInt(cartasCursor.getColumnIndex(COL_1_CARTAS_TABLE));

                if(numeroCartaTable==basicActionCardNumber){
                    nombreCarta = cartasCursor.getString(cartasCursor.getColumnIndex(COL_2_CARTAS_TABLE));
                    ++i;
                }
            }while(cartasCursor.moveToNext() && i < 1);
        }
        cartasCursor.close();

        if (cartasAccionesCursor.moveToFirst()){
            int i = 0;
            do{
                int numeroCartaTable = cartasAccionesCursor.getInt(cartasAccionesCursor.getColumnIndex(COL_1_CARTAS_ACCIONES_TABLE));

                if(numeroCartaTable==basicActionCardNumber){
                    cristalColor = cartasAccionesCursor.getString(cartasAccionesCursor.getColumnIndex(COL_2_CARTAS_ACCIONES_TABLE));
                    descripcionBasica = cartasAccionesCursor.getString(cartasAccionesCursor.getColumnIndex(COL_3_CARTAS_ACCIONES_TABLE));
                    descripcionAvanzada = cartasAccionesCursor.getString(cartasAccionesCursor.getColumnIndex(COL_4_CARTAS_ACCIONES_TABLE));
                    ++i;
                }
            }while(cartasAccionesCursor.moveToNext() && i < 1);
        }
        cartasAccionesCursor.close();

        Log.d("DATABASE","Número carta Acción Básica: "+basicActionCardNumber+" - Nombre: "+nombreCarta+" - Color: "+Cristal.valueOf(cristalColor)+ " - Descripción básica: "+descripcionBasica+ " - Descripción avanzada: "+descripcionAvanzada+" - Heroe: "+randomHeroeDummyPlayer.getNombre());

        CartaAccionBasica cartaAccionBasica = new CartaAccionBasica(basicActionCardNumber, nombreCarta, false, Cristal.valueOf(cristalColor), descripcionBasica, descripcionAvanzada, randomHeroeDummyPlayer);

        return cartaAccionBasica;
    }

    @Override
    public List<FichaHabilidad> getShuffledSkillTokensHeroeFromDummyPlayer(Heroe randomHeroeDummyPlayer) {

        List<FichaHabilidad> fichasHabilidadesBarajadas = getSkillTokensHeroeFromDummyPlayer(randomHeroeDummyPlayer);

        //Barajar aleatoriamente las fichas de Habilidad del Jugador Virtual
        Collections.shuffle(fichasHabilidadesBarajadas);

        for(FichaHabilidad fichaHabilidad: fichasHabilidadesBarajadas){
            Log.d("DATABASE","** Fichas Habilidad barajadas ** - Ficha habilidad número: "+fichaHabilidad.getIdFicha() +" - Nombre: "+fichaHabilidad.getNombre()+" - Descripción: "+fichaHabilidad.getDescripcion()+" - Heroe: "+fichaHabilidad.getHeroe().getNombre());
        }

        return fichasHabilidadesBarajadas;
    }

    @Override
    public List<FichaHabilidad> getSkillTokensHeroeFromDummyPlayer(Heroe randomHeroeDummyPlayer) {

        ArrayList<FichaHabilidad> fichasHabilidades = new ArrayList<FichaHabilidad>();
        fichasHabilidadesCursor = myDB.getAllFichasHabilidadCursor();

        if (fichasHabilidadesCursor.moveToFirst()){
            int i = 0;
            do{
                String heroeNameTable = fichasHabilidadesCursor.getString(fichasHabilidadesCursor.getColumnIndex(COL_5_FICHAS_HABILIDAD_TABLE));

                if(heroeNameTable.equals(randomHeroeDummyPlayer.getNombre())){
                    int numeroFichaHabilidad = fichasHabilidadesCursor.getInt(fichasHabilidadesCursor.getColumnIndex(COL_1_FICHAS_HABILIDAD_TABLE));
                    String nombreFichaHabilidad = fichasHabilidadesCursor.getString(fichasHabilidadesCursor.getColumnIndex(COL_2_FICHAS_HABILIDAD_TABLE));
                    String descripcionFichaHabilidad = fichasHabilidadesCursor.getString(fichasHabilidadesCursor.getColumnIndex(COL_3_FICHAS_HABILIDAD_TABLE));

                    Log.d("DATABASE","Ficha habilidad número: "+numeroFichaHabilidad +" - Nombre: "+nombreFichaHabilidad+" - Descripción: "+descripcionFichaHabilidad+" - Heroe: "+heroeNameTable);

                    fichasHabilidades.add(getSkillToken(numeroFichaHabilidad, nombreFichaHabilidad, descripcionFichaHabilidad, randomHeroeDummyPlayer));
                    ++i;
                }
            }while(fichasHabilidadesCursor.moveToNext() && i<10);
        }
        fichasHabilidadesCursor.close();

        return fichasHabilidades;
    }

    @Override
    public FichaHabilidad getSkillToken(int idFicha, String nombreFichaHabilidad,  String descripcionFichaHabilidad, Heroe randomHeroeDummyPlayer) {
        FichaHabilidad fichaHabilidad = new FichaHabilidad(idFicha, nombreFichaHabilidad, descripcionFichaHabilidad, false, randomHeroeDummyPlayer);
        return fichaHabilidad;
    }

    // ********************************************

    @Override
    public CartaAccionAvanzada getAdvancedActionCardByCardNumber(int advancedActionCardNumber) {

        cartasCursor = myDB.getAllCartasCursor();
        cartasAccionesCursor = myDB.getAllCartasAccionesCursor();

        String nombreCarta = "";
        boolean esDescatarda = false;
        String cristalColor = "";
        String descripcionBasica = "";
        String descripcionAvanzada = "";

        if (cartasCursor.moveToFirst()){
            int i = 0;
            do{
                int numeroCartaTable = cartasCursor.getInt(cartasCursor.getColumnIndex(COL_1_CARTAS_TABLE));

                if(numeroCartaTable==advancedActionCardNumber){
                    nombreCarta = cartasCursor.getString(cartasCursor.getColumnIndex(COL_2_CARTAS_TABLE));
                    esDescatarda = cartasCursor.getInt(cartasCursor.getColumnIndex(COL_3_CARTAS_TABLE)) > 0;
                    ++i;
                }
            }while(cartasCursor.moveToNext() && i < 1);
        }
        cartasCursor.close();

        if (cartasAccionesCursor.moveToFirst()){
            int i = 0;
            do{
                int numeroCartaTable = cartasAccionesCursor.getInt(cartasAccionesCursor.getColumnIndex(COL_1_CARTAS_ACCIONES_TABLE));

                if(numeroCartaTable==advancedActionCardNumber){
                    cristalColor = cartasAccionesCursor.getString(cartasAccionesCursor.getColumnIndex(COL_2_CARTAS_ACCIONES_TABLE));
                    descripcionBasica = cartasAccionesCursor.getString(cartasAccionesCursor.getColumnIndex(COL_3_CARTAS_ACCIONES_TABLE));
                    descripcionAvanzada = cartasAccionesCursor.getString(cartasAccionesCursor.getColumnIndex(COL_4_CARTAS_ACCIONES_TABLE));
                    ++i;
                }
            }while(cartasAccionesCursor.moveToNext() && i < 1);
        }
        cartasAccionesCursor.close();

        CartaAccionAvanzada cartaAccionAvanzada = new CartaAccionAvanzada(advancedActionCardNumber, nombreCarta, esDescatarda, Cristal.valueOf(cristalColor), descripcionBasica, descripcionAvanzada);

        return cartaAccionAvanzada;
    }

    @Override
    public CartaAccionAvanzadaEspecial getSpecialAdvancedActionCardByCardNumber(int specialAdvancedActionCardNumber) {
        cartasCursor = myDB.getAllCartasCursor();
        cartasAccionesCursor = myDB.getAllCartasAccionesCursor();
        cartasAccionesAvanzadasEspecialesCursor = myDB.getAllCartasAccionesAvanzadasEspecialesCursor();

        String nombreCarta = "";
        boolean esDescatarda = false;
        String cristalColor = "";
        String descripcionBasica = "";
        String descripcionAvanzada = "";
        String cristalColorSecundario = "";

        if (cartasCursor.moveToFirst()){
            int i = 0;
            do{
                int numeroCartaTable = cartasCursor.getInt(cartasCursor.getColumnIndex(COL_1_CARTAS_TABLE));

                if(numeroCartaTable==specialAdvancedActionCardNumber){
                    nombreCarta = cartasCursor.getString(cartasCursor.getColumnIndex(COL_2_CARTAS_TABLE));
                    esDescatarda = cartasCursor.getInt(cartasCursor.getColumnIndex(COL_3_CARTAS_TABLE)) > 0;
                    ++i;
                }
            }while(cartasCursor.moveToNext() && i < 1);
        }
        cartasCursor.close();

        if (cartasAccionesCursor.moveToFirst()){
            int i = 0;
            do{
                int numeroCartaTable = cartasAccionesCursor.getInt(cartasAccionesCursor.getColumnIndex(COL_1_CARTAS_ACCIONES_TABLE));

                if(numeroCartaTable==specialAdvancedActionCardNumber){
                    cristalColor = cartasAccionesCursor.getString(cartasAccionesCursor.getColumnIndex(COL_2_CARTAS_ACCIONES_TABLE));
                    descripcionBasica = cartasAccionesCursor.getString(cartasAccionesCursor.getColumnIndex(COL_3_CARTAS_ACCIONES_TABLE));
                    descripcionAvanzada = cartasAccionesCursor.getString(cartasAccionesCursor.getColumnIndex(COL_4_CARTAS_ACCIONES_TABLE));
                    ++i;
                }
            }while(cartasAccionesCursor.moveToNext() && i < 1);
        }
        cartasAccionesCursor.close();

        if (cartasAccionesAvanzadasEspecialesCursor.moveToFirst()){
            int i = 0;
            do{
                int numeroCartaTable = cartasAccionesAvanzadasEspecialesCursor.getInt(cartasAccionesAvanzadasEspecialesCursor.getColumnIndex(COL_1_CARTAS_ACCIONES_AVANZADAS_ESPECIALES_TABLE));

                if(numeroCartaTable==specialAdvancedActionCardNumber){
                    cristalColorSecundario = cartasAccionesAvanzadasEspecialesCursor.getString(cartasAccionesAvanzadasEspecialesCursor.getColumnIndex(COL_2_CARTAS_ACCIONES_AVANZADAS_ESPECIALES_TABLE));
                    ++i;
                }
            }while(cartasAccionesAvanzadasEspecialesCursor.moveToNext() && i < 1);
        }
        cartasAccionesAvanzadasEspecialesCursor.close();

        CartaAccionAvanzadaEspecial cartaAccionAvanzadaEspecial = new CartaAccionAvanzadaEspecial(specialAdvancedActionCardNumber, nombreCarta, esDescatarda, Cristal.valueOf(cristalColor), descripcionBasica, descripcionAvanzada, Cristal.valueOf(cristalColorSecundario));

        return cartaAccionAvanzadaEspecial;
    }

    @Override
    public CartaAccionAvanzadaEspecial getSpecialAdvancedActionCardByCardNumberSecondCristal(int specialAdvancedActionCardNumber, String colorSecundarioCristal) {
        cartasCursor = myDB.getAllCartasCursor();
        cartasAccionesCursor = myDB.getAllCartasAccionesCursor();

        String nombreCarta = "";
        boolean esDescatarda = false;
        String cristalColor = "";
        String descripcionBasica = "";
        String descripcionAvanzada = "";

        if (cartasCursor.moveToFirst()){
            int i = 0;
            do{
                int numeroCartaTable = cartasCursor.getInt(cartasCursor.getColumnIndex(COL_1_CARTAS_TABLE));

                if(numeroCartaTable==specialAdvancedActionCardNumber){
                    nombreCarta = cartasCursor.getString(cartasCursor.getColumnIndex(COL_2_CARTAS_TABLE));
                    esDescatarda = cartasCursor.getInt(cartasCursor.getColumnIndex(COL_3_CARTAS_TABLE)) > 0;
                    ++i;
                }
            }while(cartasCursor.moveToNext() && i < 1);
        }
        cartasCursor.close();

        if (cartasAccionesCursor.moveToFirst()){
            int i = 0;
            do{
                int numeroCartaTable = cartasAccionesCursor.getInt(cartasAccionesCursor.getColumnIndex(COL_1_CARTAS_ACCIONES_TABLE));

                if(numeroCartaTable==specialAdvancedActionCardNumber){
                    cristalColor = cartasAccionesCursor.getString(cartasAccionesCursor.getColumnIndex(COL_2_CARTAS_ACCIONES_TABLE));
                    descripcionBasica = cartasAccionesCursor.getString(cartasAccionesCursor.getColumnIndex(COL_3_CARTAS_ACCIONES_TABLE));
                    descripcionAvanzada = cartasAccionesCursor.getString(cartasAccionesCursor.getColumnIndex(COL_4_CARTAS_ACCIONES_TABLE));
                    ++i;
                }
            }while(cartasAccionesCursor.moveToNext() && i < 1);
        }
        cartasAccionesCursor.close();

        CartaAccionAvanzadaEspecial cartaAccionAvanzadaEspecial = new CartaAccionAvanzadaEspecial(specialAdvancedActionCardNumber, nombreCarta, esDescatarda, Cristal.valueOf(cristalColor), descripcionBasica, descripcionAvanzada, Cristal.valueOf(colorSecundarioCristal));

        return cartaAccionAvanzadaEspecial;
    }

    @Override
    public CartaAccionHechizo getSpellCardByCardNumber(int spellCardNumber) {
        cartasCursor = myDB.getAllCartasCursor();
        cartasAccionesCursor = myDB.getAllCartasAccionesCursor();
        cartasAccionesHechizosCursor = myDB.getAllCartasHechizosCursor();

        String nombreCarta = "";
        boolean esDescatarda = false;
        String cristalColor = "";
        String descripcionBasica = "";
        String descripcionAvanzada = "";
        String nombreSecundario = "";

        if (cartasCursor.moveToFirst()){
            int i = 0;
            do{
                int numeroCartaTable = cartasCursor.getInt(cartasCursor.getColumnIndex(COL_1_CARTAS_TABLE));

                if(numeroCartaTable==spellCardNumber){
                    nombreCarta = cartasCursor.getString(cartasCursor.getColumnIndex(COL_2_CARTAS_TABLE));
                    esDescatarda = cartasCursor.getInt(cartasCursor.getColumnIndex(COL_3_CARTAS_TABLE)) > 0;
                    ++i;
                }
            }while(cartasCursor.moveToNext() && i < 1);
        }
        cartasCursor.close();

        if (cartasAccionesCursor.moveToFirst()){
            int i = 0;
            do{
                int numeroCartaTable = cartasAccionesCursor.getInt(cartasAccionesCursor.getColumnIndex(COL_1_CARTAS_ACCIONES_TABLE));

                if(numeroCartaTable==spellCardNumber){
                    cristalColor = cartasAccionesCursor.getString(cartasAccionesCursor.getColumnIndex(COL_2_CARTAS_ACCIONES_TABLE));
                    descripcionBasica = cartasAccionesCursor.getString(cartasAccionesCursor.getColumnIndex(COL_3_CARTAS_ACCIONES_TABLE));
                    descripcionAvanzada = cartasAccionesCursor.getString(cartasAccionesCursor.getColumnIndex(COL_4_CARTAS_ACCIONES_TABLE));
                    ++i;
                }
            }while(cartasAccionesCursor.moveToNext() && i < 1);
        }
        cartasAccionesCursor.close();

        if (cartasAccionesHechizosCursor.moveToFirst()){
            int i = 0;
            do{
                int numeroCartaTable = cartasAccionesHechizosCursor.getInt(cartasAccionesHechizosCursor.getColumnIndex(COL_1_CARTAS_HECHIZOS_TABLE));

                if(numeroCartaTable==spellCardNumber){
                    nombreSecundario = cartasAccionesHechizosCursor.getString(cartasAccionesHechizosCursor.getColumnIndex(COL_2_CARTAS_HECHIZOS_TABLE));
                    ++i;
                }
            }while(cartasAccionesHechizosCursor.moveToNext() && i < 1);
        }
        cartasAccionesHechizosCursor.close();

        CartaAccionHechizo cartaAccionHechizo = new CartaAccionHechizo(spellCardNumber, nombreCarta, esDescatarda, Cristal.valueOf(cristalColor), descripcionBasica, descripcionAvanzada, nombreSecundario);

        return cartaAccionHechizo;
    }

    @Override
    public CartaAccionHechizo getSpellCardByCardNumberSecondName(int spellCardNumber, String nombreSecundario) {
        cartasCursor = myDB.getAllCartasCursor();
        cartasAccionesCursor = myDB.getAllCartasAccionesCursor();

        String nombreCarta = "";
        boolean esDescatarda = false;
        String cristalColor = "";
        String descripcionBasica = "";
        String descripcionAvanzada = "";

        if (cartasCursor.moveToFirst()){
            int i = 0;
            do{
                int numeroCartaTable = cartasCursor.getInt(cartasCursor.getColumnIndex(COL_1_CARTAS_TABLE));

                if(numeroCartaTable==spellCardNumber){
                    nombreCarta = cartasCursor.getString(cartasCursor.getColumnIndex(COL_2_CARTAS_TABLE));
                    esDescatarda = cartasCursor.getInt(cartasCursor.getColumnIndex(COL_3_CARTAS_TABLE)) > 0;
                    ++i;
                }
            }while(cartasCursor.moveToNext() && i < 1);
        }
        cartasCursor.close();

        if (cartasAccionesCursor.moveToFirst()){
            int i = 0;
            do{
                int numeroCartaTable = cartasAccionesCursor.getInt(cartasAccionesCursor.getColumnIndex(COL_1_CARTAS_ACCIONES_TABLE));

                if(numeroCartaTable==spellCardNumber){
                    cristalColor = cartasAccionesCursor.getString(cartasAccionesCursor.getColumnIndex(COL_2_CARTAS_ACCIONES_TABLE));
                    descripcionBasica = cartasAccionesCursor.getString(cartasAccionesCursor.getColumnIndex(COL_3_CARTAS_ACCIONES_TABLE));
                    descripcionAvanzada = cartasAccionesCursor.getString(cartasAccionesCursor.getColumnIndex(COL_4_CARTAS_ACCIONES_TABLE));
                    ++i;
                }
            }while(cartasAccionesCursor.moveToNext() && i < 1);
        }
        cartasAccionesCursor.close();

        CartaAccionHechizo cartaAccionHechizo = new CartaAccionHechizo(spellCardNumber, nombreCarta, esDescatarda, Cristal.valueOf(cristalColor), descripcionBasica, descripcionAvanzada, nombreSecundario);

        return cartaAccionHechizo;
    }

    @Override
    public List<CartaAccionAvanzada> getAdvancedActionCards() {

        List<CartaAccionAvanzada> cartaAccionAvanzadas = new ArrayList<CartaAccionAvanzada>();
        cartasAccionesAvanzadasCursor = myDB.getAllCartasAccionesAvanzadasCursor();

        if (cartasAccionesAvanzadasCursor.moveToFirst()){
            do{
                int numeroAccionAvanzada = cartasAccionesAvanzadasCursor.getInt(cartasAccionesAvanzadasCursor.getColumnIndex(COL_1_CARTAS_ACCIONES_AVANZADAS_TABLE));

                CartaAccionAvanzada cartaAccionAvanzada = getAdvancedActionCardByCardNumber(numeroAccionAvanzada);
                cartaAccionAvanzadas.add(cartaAccionAvanzada);

            }while(cartasAccionesAvanzadasCursor.moveToNext());
        }
        cartasAccionesAvanzadasCursor.close();

        return cartaAccionAvanzadas;
    }

    @Override
    public List<CartaAccionAvanzadaEspecial> getSpecialAdvancedActionCards() {
        List<CartaAccionAvanzadaEspecial> cartaAccionAvanzadasEspeciales = new ArrayList<CartaAccionAvanzadaEspecial>();
        cartasAccionesAvanzadasEspecialesCursor = myDB.getAllCartasAccionesAvanzadasEspecialesCursor();

        if (cartasAccionesAvanzadasEspecialesCursor.moveToFirst()){
            do{
                int numeroAccionAvanzadaEspecial =  cartasAccionesAvanzadasEspecialesCursor.getInt(cartasAccionesAvanzadasEspecialesCursor.getColumnIndex(COL_1_CARTAS_ACCIONES_AVANZADAS_ESPECIALES_TABLE));
                String colorSecundarioCristal =  cartasAccionesAvanzadasEspecialesCursor.getString(cartasAccionesAvanzadasEspecialesCursor.getColumnIndex(COL_2_CARTAS_ACCIONES_AVANZADAS_ESPECIALES_TABLE));

                CartaAccionAvanzadaEspecial cartaAccionAvanzadaEspecial = getSpecialAdvancedActionCardByCardNumberSecondCristal(numeroAccionAvanzadaEspecial, colorSecundarioCristal);
                cartaAccionAvanzadasEspeciales.add(cartaAccionAvanzadaEspecial);
            }while(cartasAccionesAvanzadasEspecialesCursor.moveToNext());
        }
        cartasAccionesAvanzadasEspecialesCursor.close();

        return cartaAccionAvanzadasEspeciales;
    }

    @Override
    public List<CartaAccionHechizo> getSpellCards() {
        List<CartaAccionHechizo> cartaAccionHechizos = new ArrayList<CartaAccionHechizo>();
        cartasAccionesHechizosCursor = myDB.getAllCartasHechizosCursor();

        if (cartasAccionesHechizosCursor.moveToFirst()){
            do{
                int numeroHechizo =  cartasAccionesHechizosCursor.getInt(cartasAccionesHechizosCursor.getColumnIndex(COL_1_CARTAS_HECHIZOS_TABLE));
                String nombreSecundario =  cartasAccionesHechizosCursor.getString(cartasAccionesHechizosCursor.getColumnIndex(COL_2_CARTAS_HECHIZOS_TABLE));

                CartaAccionHechizo cartaAccionHechizo = getSpellCardByCardNumberSecondName(numeroHechizo, nombreSecundario);
                cartaAccionHechizos.add(cartaAccionHechizo);
            }while(cartasAccionesHechizosCursor.moveToNext());
        }
        cartasAccionesHechizosCursor.close();

        return cartaAccionHechizos;
    }

    // ********************************************

    @Override
    public String getGameStatus() {
        partidaEstadoCursor = myDB.getGameStatusCursor();

        String estadoPartida = "";

        if (partidaEstadoCursor.moveToFirst()){
            estadoPartida = partidaEstadoCursor.getString(partidaEstadoCursor.getColumnIndex(COL_1_PARTIDA_DATOS_TABLE));
        }
        partidaEstadoCursor.close();

        return estadoPartida;
    }

    @Override
    public String getGameType() {
        partidaModoCursor = myDB.getGameTypeCursor();

        String modoPartida = "";

        if (partidaModoCursor.moveToFirst()){
            modoPartida = partidaModoCursor.getString(partidaModoCursor.getColumnIndex(COL_1_PARTIDA_MODO_TABLE));
        }
        partidaModoCursor.close();

        return modoPartida;
    }

    @Override
    public String getGameRound() {
        partidaEstadoCursor = myDB.getGameStatusCursor();

        String rondaPartida = "";

        if (partidaEstadoCursor.moveToFirst()){
            rondaPartida = partidaEstadoCursor.getString(partidaEstadoCursor.getColumnIndex(COL_2_PARTIDA_DATOS_TABLE));
        }
        partidaEstadoCursor.close();

        return rondaPartida;
    }

    @Override
    public int getGamePlayerExperience() {
        partidaEstadoCursor = myDB.getGameStatusCursor();

        int experienciaJugador = 0;

        if (partidaEstadoCursor.moveToFirst()){
            experienciaJugador = partidaEstadoCursor.getInt(partidaEstadoCursor.getColumnIndex(COL_6_PARTIDA_DATOS_TABLE));
        }
        partidaEstadoCursor.close();

        return experienciaJugador;
    }

    @Override
    public int getGamePlayerExperiencePlusTwo() {
        return getGamePlayerExperience()+2;
    }

    @Override
    public String getGameRoundInformation() {
        partidaInformacionRondaCursor = myDB.getGameRoundInformationCursor();

        StringBuilder strInformacionRonda = new StringBuilder();

        if (partidaInformacionRondaCursor.moveToFirst()){
            do{
                String informacion = partidaInformacionRondaCursor.getString(partidaInformacionRondaCursor.getColumnIndex(COL_1_PARTIDA_INFORMACION_RONDA_TABLE));
                strInformacionRonda.append(informacion);
                strInformacionRonda.append("\n");
            }while(partidaInformacionRondaCursor.moveToNext() );
        }
        partidaInformacionRondaCursor.close();

        return strInformacionRonda.toString();
    }

    @Override
    public String getGameHeroeNameDummyPlayer() {
        partidaHeroeDummyCursor = myDB.getGameHeroeDummyPlayerCursor();

        String nombreHeroeJugadorVirtual = "";

        if (partidaHeroeDummyCursor.moveToFirst()){
            nombreHeroeJugadorVirtual = partidaHeroeDummyCursor.getString(partidaHeroeDummyCursor.getColumnIndex(COL_1_PARTIDA_HEROE_DUMMY_TABLE));
        }
        partidaHeroeDummyCursor.close();

        return nombreHeroeJugadorVirtual;
    }

    @Override
    public String getGameHeroeCristalsDummyPlayer() {
        partidaCristalesDummyCursor = myDB.getGameCristalsDummyPlayerCursor();

        StringBuilder strCristalesJugadorVirtual = new StringBuilder();

        if (partidaCristalesDummyCursor.moveToFirst()){
            do{
                String cristal = partidaCristalesDummyCursor.getString(partidaCristalesDummyCursor.getColumnIndex(COL_1_PARTIDA_CRISTALES_HEROE_DUMMY_TABLE));
                strCristalesJugadorVirtual.append(cristal);
                strCristalesJugadorVirtual.append(" ");
            }while(partidaCristalesDummyCursor.moveToNext() );
        }
        partidaCristalesDummyCursor.close();

        return strCristalesJugadorVirtual.toString();
    }

    @Override
    public int getGameTotalAvailableCardsDummyPlayer() {
        partidaCartasDummyCursor = myDB.getGameCardsDummyPlayerCursor();

        int numeroCartasDisponiblesTotalJugadorVirtual = 0;

        if (partidaCartasDummyCursor.moveToFirst()){
            do{
                boolean esDescartada = partidaCartasDummyCursor.getInt(partidaCartasDummyCursor.getColumnIndex(COL_3_PARTIDA_CARTAS_HEROE_DUMMY_TABLE)) > 0;
                if(!esDescartada){
                    ++numeroCartasDisponiblesTotalJugadorVirtual;
                }
            }while(partidaCartasDummyCursor.moveToNext() );
        }
        partidaCartasDummyCursor.close();

        return numeroCartasDisponiblesTotalJugadorVirtual;
    }

    @Override
    public int getGameTotalCardsDummyPlayer() {
        partidaCartasDummyCursor = myDB.getGameCardsDummyPlayerCursor();

        int numeroCartasTotalJugadorVirtual = 0;

        if (partidaCartasDummyCursor.moveToFirst()){
            do{
                ++numeroCartasTotalJugadorVirtual;
            }while(partidaCartasDummyCursor.moveToNext());
        }
        partidaCartasDummyCursor.close();

        return numeroCartasTotalJugadorVirtual;
    }

    @Override
    public int getGameNumberOfAvailableSpecialAdvancedActionCards() {
        int numeroCartasAccionAvanzadasEspeciales = 0;
        partidaCartasAccionAvanzadaYEspecialCursor = myDB.getGameAdvancedActionCardsAndSpecialCursor();

        if(partidaCartasAccionAvanzadaYEspecialCursor.moveToFirst()){
            do{
                boolean esDescartada = partidaCartasAccionAvanzadaYEspecialCursor.getInt(partidaCartasAccionAvanzadaYEspecialCursor.getColumnIndex(COL_3_PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE)) > 0;
                boolean esEspecial = partidaCartasAccionAvanzadaYEspecialCursor.getInt(partidaCartasAccionAvanzadaYEspecialCursor.getColumnIndex(COL_8_PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE)) > 0;

                if(!esDescartada && esEspecial) {
                    ++numeroCartasAccionAvanzadasEspeciales;
                }
            }while(partidaCartasAccionAvanzadaYEspecialCursor.moveToNext());
        }
        partidaCartasAccionAvanzadaYEspecialCursor.close();

        return numeroCartasAccionAvanzadasEspeciales;
    }

    @Override
    public List<Integer> getGameAvailableCardsDummyPlayerByNumber() {

        List<Integer> cartasDisponiblesJugadorVirtualPorNumero = new ArrayList<Integer>();

        partidaCartasDummyCursor = myDB.getGameCardsDummyPlayerCursor();

        if (partidaCartasDummyCursor.moveToFirst()){
            do{
                boolean esDescartada = partidaCartasDummyCursor.getInt(partidaCartasDummyCursor.getColumnIndex(COL_3_PARTIDA_CARTAS_HEROE_DUMMY_TABLE)) > 0;
                if(!esDescartada){
                    int numeroCarta = partidaCartasDummyCursor.getInt(partidaCartasDummyCursor.getColumnIndex(COL_1_PARTIDA_CARTAS_HEROE_DUMMY_TABLE));
                    cartasDisponiblesJugadorVirtualPorNumero.add(numeroCarta);
                }
            }while(partidaCartasDummyCursor.moveToNext());
        }
        partidaCartasDummyCursor.close();

        return cartasDisponiblesJugadorVirtualPorNumero;
    }

    @Override
    public List<Integer> getGameCardsDummyPlayerByNumber() {
        List<Integer> cartasJugadorVirtualPorNumero = new ArrayList<Integer>();

        partidaCartasDummyCursor = myDB.getGameCardsDummyPlayerCursor();

        if (partidaCartasDummyCursor.moveToFirst()){
            do{
                int numeroCarta = partidaCartasDummyCursor.getInt(partidaCartasDummyCursor.getColumnIndex(COL_1_PARTIDA_CARTAS_HEROE_DUMMY_TABLE));
                cartasJugadorVirtualPorNumero.add(numeroCarta);
            }while(partidaCartasDummyCursor.moveToNext());
        }
        partidaCartasDummyCursor.close();

        return cartasJugadorVirtualPorNumero;
    }

    @Override
    public List<Integer> getShuffledGameCardsDummyPlayerByNumber(List<Integer> gameCardsDummyPlayerByNumber) {

        List<Integer> cartasBarajadasJugadorVirtualPorNumero = gameCardsDummyPlayerByNumber;

        Collections.shuffle(cartasBarajadasJugadorVirtualPorNumero);

        return cartasBarajadasJugadorVirtualPorNumero;
    }

    @Override
    public List<CartaTactica> getGameAvailableDayTacticsCards() {
        List<CartaTactica> cartasTacticas = new ArrayList<CartaTactica>();
        partidaCartasTacticas = myDB.getGameTacticCardsCursor();

        if(partidaCartasTacticas.moveToFirst()){
            do{
                boolean esDescartada = partidaCartasTacticas.getInt(partidaCartasTacticas.getColumnIndex(COL_3_PARTIDA_CARTAS_TACTICAS_TABLE)) > 0;
                String tipoCartaTactica = partidaCartasTacticas.getString(partidaCartasTacticas.getColumnIndex(COL_4_PARTIDA_CARTAS_TACTICAS_TABLE));

                if(!esDescartada && tipoCartaTactica.equals(TipoTactica.DIA.toString())) {
                    int numeroCartaTactica = partidaCartasTacticas.getInt(partidaCartasTacticas.getColumnIndex(COL_1_PARTIDA_CARTAS_TACTICAS_TABLE));
                    String nombreCartaTactica = partidaCartasTacticas.getString(partidaCartasTacticas.getColumnIndex(COL_2_PARTIDA_CARTAS_TACTICAS_TABLE));
                    int numeroOrdenCartaTactica = partidaCartasTacticas.getInt(partidaCartasTacticas.getColumnIndex(COL_5_PARTIDA_CARTAS_TACTICAS_TABLE));
                    String descripcionCartaTactica = partidaCartasTacticas.getString(partidaCartasTacticas.getColumnIndex(COL_6_PARTIDA_CARTAS_TACTICAS_TABLE));

                    CartaTactica cartaTactica = new CartaTactica(numeroCartaTactica, nombreCartaTactica, esDescartada, TipoTactica.valueOf(tipoCartaTactica), numeroOrdenCartaTactica, descripcionCartaTactica);
                    cartasTacticas.add(cartaTactica);
                }
            }while(partidaCartasTacticas.moveToNext());
        }
        partidaCartasTacticas.close();

        return cartasTacticas;
    }

    @Override
    public List<CartaTactica> getGameAvailableNightTacticsCards() {
        List<CartaTactica> cartasTacticas = new ArrayList<CartaTactica>();
        partidaCartasTacticas = myDB.getGameTacticCardsCursor();

        if(partidaCartasTacticas.moveToFirst()){
            do{
                boolean esDescartada = partidaCartasTacticas.getInt(partidaCartasTacticas.getColumnIndex(COL_3_PARTIDA_CARTAS_TACTICAS_TABLE)) > 0;
                String tipoCartaTactica = partidaCartasTacticas.getString(partidaCartasTacticas.getColumnIndex(COL_4_PARTIDA_CARTAS_TACTICAS_TABLE));

                if(!esDescartada && tipoCartaTactica.equals(TipoTactica.NOCHE.toString())) {
                    int numeroCartaTactica = partidaCartasTacticas.getInt(partidaCartasTacticas.getColumnIndex(COL_1_PARTIDA_CARTAS_TACTICAS_TABLE));
                    String nombreCartaTactica = partidaCartasTacticas.getString(partidaCartasTacticas.getColumnIndex(COL_2_PARTIDA_CARTAS_TACTICAS_TABLE));
                    int numeroOrdenCartaTactica = partidaCartasTacticas.getInt(partidaCartasTacticas.getColumnIndex(COL_5_PARTIDA_CARTAS_TACTICAS_TABLE));
                    String descripcionCartaTactica = partidaCartasTacticas.getString(partidaCartasTacticas.getColumnIndex(COL_6_PARTIDA_CARTAS_TACTICAS_TABLE));

                    CartaTactica cartaTactica = new CartaTactica(numeroCartaTactica, nombreCartaTactica, esDescartada, TipoTactica.valueOf(tipoCartaTactica), numeroOrdenCartaTactica, descripcionCartaTactica);
                    cartasTacticas.add(cartaTactica);
                }
            }while(partidaCartasTacticas.moveToNext());
        }
        partidaCartasTacticas.close();

        return cartasTacticas;
    }

    @Override
    public List<CartaAccionAvanzada> getGameAvailableAdvancedActionCards() {
        List<CartaAccionAvanzada> cartasAccionAvanzadasDisponibles = new ArrayList<CartaAccionAvanzada>();
        partidaCartasAccionAvanzadaYEspecialCursor = myDB.getGameAdvancedActionCardsAndSpecialCursor();

        if(partidaCartasAccionAvanzadaYEspecialCursor.moveToFirst()){
            do{
                boolean esDescartada = partidaCartasAccionAvanzadaYEspecialCursor.getInt(partidaCartasAccionAvanzadaYEspecialCursor.getColumnIndex(COL_3_PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE)) > 0;
                boolean esEspecial = partidaCartasAccionAvanzadaYEspecialCursor.getInt(partidaCartasAccionAvanzadaYEspecialCursor.getColumnIndex(COL_8_PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE)) > 0;

                if(!esDescartada && !esEspecial) {
                    int numeroCartaAccionAvanzada = partidaCartasAccionAvanzadaYEspecialCursor.getInt(partidaCartasAccionAvanzadaYEspecialCursor.getColumnIndex(COL_1_PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE));
                    String nombreCartaAccionAvanzada = partidaCartasAccionAvanzadaYEspecialCursor.getString(partidaCartasAccionAvanzadaYEspecialCursor.getColumnIndex(COL_2_PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE));
                    String colorCartaAccionAvanzada = partidaCartasAccionAvanzadaYEspecialCursor.getString(partidaCartasAccionAvanzadaYEspecialCursor.getColumnIndex(COL_4_PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE));
                    String descripcionBasicaCartaAccionAvanzada = partidaCartasAccionAvanzadaYEspecialCursor.getString(partidaCartasAccionAvanzadaYEspecialCursor.getColumnIndex(COL_6_PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE));
                    String descripcionAvanzadaCartaAccionAvanzada = partidaCartasAccionAvanzadaYEspecialCursor.getString(partidaCartasAccionAvanzadaYEspecialCursor.getColumnIndex(COL_7_PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE));

                    CartaAccionAvanzada cartaAccionAvanzada = new CartaAccionAvanzada(numeroCartaAccionAvanzada, nombreCartaAccionAvanzada, esDescartada, Cristal.valueOf(colorCartaAccionAvanzada), descripcionBasicaCartaAccionAvanzada, descripcionAvanzadaCartaAccionAvanzada);
                    cartasAccionAvanzadasDisponibles.add(cartaAccionAvanzada);
                }
            }while(partidaCartasAccionAvanzadaYEspecialCursor.moveToNext());
        }
        partidaCartasAccionAvanzadaYEspecialCursor.close();

        return cartasAccionAvanzadasDisponibles;
    }

    @Override
    public List<CartaAccionAvanzadaEspecial> getGameAvailableSpecialAdvancedActionCards() {
        List<CartaAccionAvanzadaEspecial> cartasAccionAvanzadasEspecialesDisponibles = new ArrayList<CartaAccionAvanzadaEspecial>();
        partidaCartasAccionAvanzadaYEspecialCursor = myDB.getGameAdvancedActionCardsAndSpecialCursor();

        if(partidaCartasAccionAvanzadaYEspecialCursor.moveToFirst()){
            do{
                boolean esDescartada = partidaCartasAccionAvanzadaYEspecialCursor.getInt(partidaCartasAccionAvanzadaYEspecialCursor.getColumnIndex(COL_3_PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE)) > 0;
                boolean esEspecial = partidaCartasAccionAvanzadaYEspecialCursor.getInt(partidaCartasAccionAvanzadaYEspecialCursor.getColumnIndex(COL_8_PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE)) > 0;

                if(!esDescartada && esEspecial) {
                    int numeroCartaAccionAvanzadaEspecial = partidaCartasAccionAvanzadaYEspecialCursor.getInt(partidaCartasAccionAvanzadaYEspecialCursor.getColumnIndex(COL_1_PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE));
                    String nombreCartaAccionAvanzadaEspecial = partidaCartasAccionAvanzadaYEspecialCursor.getString(partidaCartasAccionAvanzadaYEspecialCursor.getColumnIndex(COL_2_PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE));
                    String colorCartaAccionAvanzadaEspecial = partidaCartasAccionAvanzadaYEspecialCursor.getString(partidaCartasAccionAvanzadaYEspecialCursor.getColumnIndex(COL_4_PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE));
                    String descripcionBasicaCartaAccionAvanzadaEspecial = partidaCartasAccionAvanzadaYEspecialCursor.getString(partidaCartasAccionAvanzadaYEspecialCursor.getColumnIndex(COL_6_PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE));
                    String descripcionAvanzadaCartaAccionAvanzadaEspecial = partidaCartasAccionAvanzadaYEspecialCursor.getString(partidaCartasAccionAvanzadaYEspecialCursor.getColumnIndex(COL_7_PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE));
                    String colorSecundarioCartaAccionAvanzadaEspecial = partidaCartasAccionAvanzadaYEspecialCursor.getString(partidaCartasAccionAvanzadaYEspecialCursor.getColumnIndex(COL_5_PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE));

                    CartaAccionAvanzadaEspecial cartaAccionAvanzadaEspecial = new CartaAccionAvanzadaEspecial(numeroCartaAccionAvanzadaEspecial, nombreCartaAccionAvanzadaEspecial, esDescartada, Cristal.valueOf(colorCartaAccionAvanzadaEspecial), descripcionBasicaCartaAccionAvanzadaEspecial, descripcionAvanzadaCartaAccionAvanzadaEspecial, Cristal.valueOf(colorSecundarioCartaAccionAvanzadaEspecial));
                    cartasAccionAvanzadasEspecialesDisponibles.add(cartaAccionAvanzadaEspecial);
                }
            }while(partidaCartasAccionAvanzadaYEspecialCursor.moveToNext());
        }
        partidaCartasAccionAvanzadaYEspecialCursor.close();

        return cartasAccionAvanzadasEspecialesDisponibles;
    }

    @Override
    public CartaTactica getGameAvailableRandomTacticCard(List<CartaTactica> gameTacticCardsAvailable) {

        //Selecciona un número aleatorio entre 0 y gameTacticCardsAvailable.size()
        int randomNumber = (int)(Math.random() * gameTacticCardsAvailable.size());
        CartaTactica cartaTacticaAleatoriaDisponible = gameTacticCardsAvailable.get(randomNumber);

        return cartaTacticaAleatoriaDisponible;
    }

    @Override
    public String getGameInformationTacticCardDummyPlayerSolitaireType(CartaTactica cartaTactica) {
        StringBuilder strInformacionCartaTacticaJugadorVirtualSolitario = new StringBuilder();

        strInformacionCartaTacticaJugadorVirtualSolitario.append("- El JV ha seleccionado la carta Táctica: ");
        strInformacionCartaTacticaJugadorVirtualSolitario.append(cartaTactica.getNumeroOrden());
        strInformacionCartaTacticaJugadorVirtualSolitario.append(" - ");
        strInformacionCartaTacticaJugadorVirtualSolitario.append(cartaTactica.getNombre());
        strInformacionCartaTacticaJugadorVirtualSolitario.append(" (");
        strInformacionCartaTacticaJugadorVirtualSolitario.append(cartaTactica.getTipoTactica().toString());
        strInformacionCartaTacticaJugadorVirtualSolitario.append(").");

        return strInformacionCartaTacticaJugadorVirtualSolitario.toString();
    }

    @Override
    public String getGameInformationTacticCardDummyPlayerCooperativeType(CartaTactica cartaTactica) {

        StringBuilder strInformacionCartaTacticaJugadorVirtualCooperativo = new StringBuilder();

        strInformacionCartaTacticaJugadorVirtualCooperativo.append("- El JV ha seleccionado la carta Táctica: ");
        strInformacionCartaTacticaJugadorVirtualCooperativo.append(cartaTactica.getNumeroOrden());
        strInformacionCartaTacticaJugadorVirtualCooperativo.append(" - ");
        strInformacionCartaTacticaJugadorVirtualCooperativo.append(cartaTactica.getNombre());
        strInformacionCartaTacticaJugadorVirtualCooperativo.append(" (");
        strInformacionCartaTacticaJugadorVirtualCooperativo.append(cartaTactica.getTipoTactica().toString());
        strInformacionCartaTacticaJugadorVirtualCooperativo.append(").");
        strInformacionCartaTacticaJugadorVirtualCooperativo.append("\n");
        strInformacionCartaTacticaJugadorVirtualCooperativo.append("Esta carta Táctica volverá a estar disponible una vez los jugadores hayan descartado 1 carta usada por ellos.");

        return strInformacionCartaTacticaJugadorVirtualCooperativo.toString();
    }

    @Override
    public String getGameInformationSkillTokenDummyPlayerSolitaireType(FichaHabilidad fichaHabilidad) {

        StringBuilder strInformacionFichaHabilidadJugadorVirtualSolitario = new StringBuilder();

        strInformacionFichaHabilidadJugadorVirtualSolitario.append("- El JV ha seleccionado la Ficha de Habilidad: ");
        strInformacionFichaHabilidadJugadorVirtualSolitario.append(fichaHabilidad.getNombre());
        strInformacionFichaHabilidadJugadorVirtualSolitario.append(" (");
        strInformacionFichaHabilidadJugadorVirtualSolitario.append(fichaHabilidad.getHeroe().getNombre());
        strInformacionFichaHabilidadJugadorVirtualSolitario.append(") que estará disponible cuando subas a Nivel ");
        strInformacionFichaHabilidadJugadorVirtualSolitario.append((getGamePlayerExperiencePlusTwo()));

        return strInformacionFichaHabilidadJugadorVirtualSolitario.toString();
    }

    @Override
    public String getGameInformationAdvancedActionCardAddedToDummyPlayer(CartaAccionAvanzada cartaAccionAvanzada) {
        StringBuilder strInformacionCartaAccionAvanzadaJugadorVirtual = new StringBuilder();

        strInformacionCartaAccionAvanzadaJugadorVirtual.append("- Se añade al mazo del JV la carta de Acción Avanzada inferior de la Oferta: ");
        strInformacionCartaAccionAvanzadaJugadorVirtual.append(cartaAccionAvanzada.getNumero());
        strInformacionCartaAccionAvanzadaJugadorVirtual.append(" - ");
        strInformacionCartaAccionAvanzadaJugadorVirtual.append(cartaAccionAvanzada.getNombre());
        strInformacionCartaAccionAvanzadaJugadorVirtual.append(" (");
        strInformacionCartaAccionAvanzadaJugadorVirtual.append(cartaAccionAvanzada.getColor().toString());
        strInformacionCartaAccionAvanzadaJugadorVirtual.append(").");

        return strInformacionCartaAccionAvanzadaJugadorVirtual.toString();
    }

    @Override
    public String getGameInformationSpecialAdvancedActionCardAddedToDummyPlayer(CartaAccionAvanzadaEspecial cartaAccionAvanzadaEspecial) {
        StringBuilder strInformacionCartaAccionAvanzadaEspecialJugadorVirtual = new StringBuilder();

        strInformacionCartaAccionAvanzadaEspecialJugadorVirtual.append("- Se añade al mazo del JV la carta de Acción Avanzada Especial inferior de la Oferta: ");
        strInformacionCartaAccionAvanzadaEspecialJugadorVirtual.append(cartaAccionAvanzadaEspecial.getNumero());
        strInformacionCartaAccionAvanzadaEspecialJugadorVirtual.append(" - ");
        strInformacionCartaAccionAvanzadaEspecialJugadorVirtual.append(cartaAccionAvanzadaEspecial.getNombre());
        strInformacionCartaAccionAvanzadaEspecialJugadorVirtual.append(" (");
        strInformacionCartaAccionAvanzadaEspecialJugadorVirtual.append(cartaAccionAvanzadaEspecial.getColor().toString());
        strInformacionCartaAccionAvanzadaEspecialJugadorVirtual.append(") (");
        strInformacionCartaAccionAvanzadaEspecialJugadorVirtual.append(cartaAccionAvanzadaEspecial.getColorSecundario().toString());
        strInformacionCartaAccionAvanzadaEspecialJugadorVirtual.append(").");

        return strInformacionCartaAccionAvanzadaEspecialJugadorVirtual.toString();
    }

    @Override
    public String getGameInformationCristalAddedToDummyPlayer(CartaAccionHechizo cartaAccionHechizo) {
        StringBuilder strInformacionCristalJugadorVirtual = new StringBuilder();

        strInformacionCristalJugadorVirtual.append("- Se añade un cristal " + cartaAccionHechizo.getColor().toString() + " al Inventario del JV.");
        strInformacionCristalJugadorVirtual.append("\n");
        strInformacionCristalJugadorVirtual.append("El color viene dado por la carta de Hechizo inferior de la Oferta: ");
        strInformacionCristalJugadorVirtual.append(cartaAccionHechizo.getNumero());
        strInformacionCristalJugadorVirtual.append(" - ");
        strInformacionCristalJugadorVirtual.append(cartaAccionHechizo.getNombre());
        strInformacionCristalJugadorVirtual.append(" / ");
        strInformacionCristalJugadorVirtual.append(cartaAccionHechizo.getNombreSecundario());
        strInformacionCristalJugadorVirtual.append(".");

        return strInformacionCristalJugadorVirtual.toString();
    }

    @Override
    public String getGameInformationGameFinished() {
        String rondaFinalizada = "- ** PARTIDA FINALIZADA **";

        return rondaFinalizada;
    }

    @Override
    public String getGameInformationRoundFinishedByEmptyDeedDeckDummyPlayer() {
        StringBuilder strInformacionRondaFinalizadaMazoVacioJugadorVirtual = new StringBuilder();

        strInformacionRondaFinalizadaMazoVacioJugadorVirtual.append("- El mazo de Gesta del JV está vacío y anuncia el Fin de la Ronda.\n");

        if(isGameTypeSolitaire()){ //Si es una partida en SOLITARIO
            strInformacionRondaFinalizadaMazoVacioJugadorVirtual.append("El jugador tendrá un turno más y la Ronda terminará.");
        } else{ //Si es una partida en COOPERATIVO
            strInformacionRondaFinalizadaMazoVacioJugadorVirtual.append("Los demás jugadores tendrán un turno más y la Ronda terminará.");
        }

        return strInformacionRondaFinalizadaMazoVacioJugadorVirtual.toString();
    }

    @Override
    public List<FichaHabilidad> getGameSkillTokensDummyPlayerBasedOnPlayerExperience(int nivelExperienciaJugador) {

        List<FichaHabilidad> fichaHabilidadesJugadorVirtual = new ArrayList<FichaHabilidad>();

        if(nivelExperienciaJugador==10){
            for(int i = 0; i < 4 ; i++){
                FichaHabilidad fichaHabilidad = getGameSkillTokenDummyPlayerByIndex(i);
                fichaHabilidadesJugadorVirtual.add(fichaHabilidad);
            }
        } else if(nivelExperienciaJugador==8){
            for(int i = 0; i < 3 ; i++){
                FichaHabilidad fichaHabilidad = getGameSkillTokenDummyPlayerByIndex(i);
                fichaHabilidadesJugadorVirtual.add(fichaHabilidad);
            }
        } else if(nivelExperienciaJugador==6){
            for(int i = 0; i < 2 ; i++){
                FichaHabilidad fichaHabilidad = getGameSkillTokenDummyPlayerByIndex(i);
                fichaHabilidadesJugadorVirtual.add(fichaHabilidad);
            }
        } else if(nivelExperienciaJugador==4){
            FichaHabilidad fichaHabilidad = getGameSkillTokenDummyPlayerByIndex(0);
            fichaHabilidadesJugadorVirtual.add(fichaHabilidad);
        }

        return fichaHabilidadesJugadorVirtual;
    }

    @Override
    public FichaHabilidad getGameSkillTokenDummyPlayerByIndex(int indiceFichaHabilidad) {

        FichaHabilidad fichaHabilidad = null;
        partidaFichasHabilidad = myDB.getGameSkillTokensDummyPlayerCursor();

        if(partidaFichasHabilidad.moveToFirst()){
            int i = 0;
            do{
                int numeroIndiceFichaHabilidad = partidaFichasHabilidad.getInt(partidaFichasHabilidad.getColumnIndex(COL_6_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE));

                if(numeroIndiceFichaHabilidad==indiceFichaHabilidad) {
                    int idFicha = partidaFichasHabilidad.getInt(partidaFichasHabilidad.getColumnIndex(COL_1_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE));
                    String nombre = partidaFichasHabilidad.getString(partidaFichasHabilidad.getColumnIndex(COL_2_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE));
                    String descripcion = partidaFichasHabilidad.getString(partidaFichasHabilidad.getColumnIndex(COL_3_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE));
                    boolean esDescartada = partidaFichasHabilidad.getInt(partidaFichasHabilidad.getColumnIndex(COL_4_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE)) > 0;
                    String nombreHeroe = partidaFichasHabilidad.getString(partidaFichasHabilidad.getColumnIndex(COL_5_PARTIDA_FICHAS_HABILIDAD_HEROE_DUMMY_TABLE));

                    Heroe heroe = getAHeroeSelectedByPlayer(nombreHeroe);

                    fichaHabilidad = new FichaHabilidad(idFicha, nombre, descripcion, esDescartada, heroe);
                    ++i;
                }
            }while(partidaFichasHabilidad.moveToNext() && i < 1);
        }
        partidaFichasHabilidad.close();

        return fichaHabilidad;
    }

    @Override
    public FichaHabilidad getGameSkillTokenDummyPlayerByPlayerExperience(int nivelExperienciaJugador) {

        FichaHabilidad fichaHabilidad = null;

        if(nivelExperienciaJugador==2){
            fichaHabilidad = getGameSkillTokenDummyPlayerByIndex(0);
        } else if(nivelExperienciaJugador==4){
            fichaHabilidad = getGameSkillTokenDummyPlayerByIndex(1);
        } else if(nivelExperienciaJugador==6){
            fichaHabilidad = getGameSkillTokenDummyPlayerByIndex(2);
        } else if(nivelExperienciaJugador==8){
            fichaHabilidad = getGameSkillTokenDummyPlayerByIndex(3);
        }

        return fichaHabilidad;
    }

    // ********************************************

    @Override
    public void insertTableGameRoundInformation(String roundInformation) {
        myDB.createGameInformation(roundInformation);
    }

    @Override
    public void insertTableGameSkillTokenInformation(String roundInformation) {
        myDB.createGameInformation(roundInformation);
    }

    @Override
    public void insertTableGameNewAdvancedActionCard(int numeroCarta, String nombre, boolean esDescartada, String colorCristal, String colorSecundarioCristal, String descripcionBasica, String descripcionAvanzada, String heroe, int indice) {
        myDB.createGameAddedAdvancedActionCard(numeroCarta, nombre, esDescartada, colorCristal, colorSecundarioCristal, descripcionBasica, descripcionAvanzada, heroe, indice);
    }

    @Override
    public void insertTableGameNewCristal(String nombreCristal) {
        myDB.createGameAddedCristal(nombreCristal);
    }

    // ********************************************

    @Override
    public void modifyTableGameTacticCardAvailabilityByName(String tacticCardName, boolean esDescartada) {
        partidaCartasTacticas = myDB.getGameTacticCardsCursor();

        if(partidaCartasTacticas.moveToFirst()){
            int i = 0;
            do{
                String nombreCartaTactica = partidaCartasTacticas.getString(partidaCartasTacticas.getColumnIndex(COL_2_PARTIDA_CARTAS_TACTICAS_TABLE));

                if(nombreCartaTactica.equals(tacticCardName)){
                    //Modifica en la fila 'NOMBRE'="tacticCardName" de la tabla 'PARTIDA_CARTAS_TACTICAS': la columna 'DESCARTADA'=1
                    myDB.updateGameTacticCardAvailabilityByName(tacticCardName, esDescartada);
                    ++i;
                }
            }while(partidaCartasTacticas.moveToNext() && i < 1);
        }
        partidaCartasTacticas.close();
    }

    @Override
    public void modifyTableGameAdvancedActionAndSpecialCardAvailabilityByCardNumber(int advancedActionCardNumber, boolean esDescartada) {
        partidaCartasAccionAvanzadaYEspecialCursor = myDB.getGameAdvancedActionCardsAndSpecialCursor();

        if (partidaCartasAccionAvanzadaYEspecialCursor.moveToFirst()) {
            int i = 0;
            do {
                int numeroCartaAccionAvanzada = partidaCartasAccionAvanzadaYEspecialCursor.getInt(partidaCartasAccionAvanzadaYEspecialCursor.getColumnIndex(COL_1_PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE));

                if (numeroCartaAccionAvanzada==advancedActionCardNumber) {
                    //Modifica en la fila 'NUMERO'="advancedActionCardNumber" de la tabla 'PARTIDA_CARTAS_ACCIONES_AVANZADAS_Y_ESPECIALES_TABLE': la columna 'DESCARTADA'=1
                    myDB.updateGameAdvancedActionCardAvailabilityByCardNumber(advancedActionCardNumber, esDescartada);
                    ++i;
                }
            } while (partidaCartasAccionAvanzadaYEspecialCursor.moveToNext() && i < 1);
        }
        partidaCartasAccionAvanzadaYEspecialCursor.close();
    }


    @Override
    public void modifyTableGameStatus(String estadoPartida) {
        myDB.updateGameStatus(estadoPartida);
    }

    @Override
    public void modifyTableGameRound(String estadoRondaSiguiente) {
        myDB.updateGameRound(estadoRondaSiguiente);
    }

    @Override
    public void modifyTableGameStatusRoundBeginning(boolean esRondaInicio) {
        myDB.updateGameStatusRoundBeginning(esRondaInicio);
    }

    @Override
    public void modifyTableGameStatusRoundEnding(boolean esRondaFinalizada) {
        myDB.updateGameStatusRoundEnding(esRondaFinalizada);
    }

    @Override
    public void modifyTableGameStatusTurnNumber(int numeroTurno) {
        myDB.updateGameStatusRoundTurn(numeroTurno);
    }

    @Override
    public void modifyTableGameStatusPlayerExperience(int experienciaJugador) {
        myDB.updateGameStatusPlayerExperience(experienciaJugador);
    }

    @Override
    public void modifyTableGameShuffledCardsDummyPlayer(List<Integer> gameShuffledCardsDummyPlayerByNumber, boolean esDescartada) {

        int indice = 0;

        for(Integer numeroCartaBarajadoJugadorVirtual : gameShuffledCardsDummyPlayerByNumber){
            partidaCartasDummyCursor = myDB.getGameCardsDummyPlayerCursor();

            if (partidaCartasDummyCursor.moveToFirst()){
                int i = 0;
                do{
                    int numeroCarta = partidaCartasDummyCursor.getInt(partidaCartasDummyCursor.getColumnIndex(COL_1_PARTIDA_CARTAS_HEROE_DUMMY_TABLE));
                    if(numeroCarta==numeroCartaBarajadoJugadorVirtual){
                        //Modifica en la fila 'NUMERO'="numeroCartaBarajadoJugadorVirtual" de la tabla 'PARTIDA_CARTAS_HEROE_DUMMY': la columna 'DESCARTADA'=0 y columna 'INDICE'="indice"
                        myDB.updateGameShuffledCardsDummyPlayer(numeroCartaBarajadoJugadorVirtual, indice, esDescartada);
                        ++indice;
                        ++i;
                    }
                }while(partidaCartasDummyCursor.moveToNext() && i < 1);
            }
            partidaCartasDummyCursor.close();
        }
    }

    // ********************************************

    @Override
    public boolean isGameStatusInitiated() {
        if(getGameStatus().equals(TipoEstado.INICIADA.toString())){
            return true;
        }
        return false;
    }

    @Override
    public boolean isGameStatusFinished() {
        if(getGameStatus().equals(TipoEstado.FINALIZADA.toString())){
            return true;
        }
        return false;
    }

    @Override
    public boolean isGameTypeSolitaire() {
        if(getGameType().equals(TipoPartida.SOLITARIO.toString())){
            return true;
        }
        return false;
    }

    @Override
    public boolean isRoundBeginning() {
        partidaEstadoCursor = myDB.getGameStatusCursor();

        boolean esInicioRonda = false;

        if (partidaEstadoCursor.moveToFirst()){
            esInicioRonda = partidaEstadoCursor.getInt(partidaEstadoCursor.getColumnIndex(COL_3_PARTIDA_DATOS_TABLE)) > 0;
        }
        partidaEstadoCursor.close();

        return esInicioRonda;
    }

    @Override
    public boolean isRoundEnding() {
        partidaEstadoCursor = myDB.getGameStatusCursor();

        boolean esFinalTurno = false;

        if (partidaEstadoCursor.moveToFirst()){
            esFinalTurno = partidaEstadoCursor.getInt(partidaEstadoCursor.getColumnIndex(COL_4_PARTIDA_DATOS_TABLE)) > 0;
        }
        partidaEstadoCursor.close();

        return esFinalTurno;
    }

    @Override
    public boolean isFirstRound() {
        if(getGameRound().equals(TipoRonda.RONDA_1_DIA.toString())){
            return true;
        }
        return false;
    }

    @Override
    public boolean isLastRound() {
        if(getGameRound().equals(TipoRonda.RONDA_6_NOCHE.toString())){
            return true;
        }
        return false;
    }

    @Override
    public boolean isLastTwoRounds() {
        if(getGameRound().equals(TipoRonda.RONDA_5_DIA.toString()) || getGameRound().equals(TipoRonda.RONDA_6_NOCHE.toString())){
            return true;
        }
        return false;
    }

    @Override
    public boolean isDayRound() {
        if(getGameRound().equals(TipoRonda.RONDA_1_DIA.toString()) || getGameRound().equals(TipoRonda.RONDA_3_DIA.toString()) || getGameRound().equals(TipoRonda.RONDA_5_DIA.toString())){
            return true;
        }
        return false;
    }

    @Override
    public boolean isDummyPlayerCardsFinished() {
        int numeroCartasTotalJugadorVirtual = getGameTotalAvailableCardsDummyPlayer();

        if(numeroCartasTotalJugadorVirtual==0){
            return true;
        }
        return false;
    }

    @Override
    public boolean isPlayerExperienceLevelFourOrMore() {
        int nivelExperienciaJugador = getGamePlayerExperience();

        if(nivelExperienciaJugador >= 4){
            return true;
        }
        return false;
    }

    @Override
    public boolean isPlayerExperienceUpToTen() {
        int nivelExperienciaJugador = getGamePlayerExperience();

        if(nivelExperienciaJugador == 10){
            return true;
        }
        return false;
    }

    @Override
    public boolean isPlayerExperienceFromTwoToEight() {
        int nivelExperienciaJugador = getGamePlayerExperience();

        if(nivelExperienciaJugador == 2 || nivelExperienciaJugador == 4 || nivelExperienciaJugador == 6 || nivelExperienciaJugador == 8 ){
            return true;
        }
        return false;
    }

    @Override
    public boolean isAllSpecialAdvancedActionCardsDiscarded() {
        int numeroCartasAccionAvanzadaEspecialesJuego = getGameNumberOfAvailableSpecialAdvancedActionCards();

        if(numeroCartasAccionAvanzadaEspecialesJuego == 0){
            return true;
        }
        return false;
    }

    // ********************************************

    @Override
    public String getTacticCardNameFromString(String tacticCard) {
        String tacticCardName = "";
        int guion = tacticCard.indexOf("- ");

        if(guion != -1) {
            tacticCardName = tacticCard.substring(guion + 1);
        }

        return tacticCardName.trim();
    }

    @Override
    public int getActionCardNumberFromString(String actionCard) {
        String actionCardNumber = actionCard.substring(0, 3);

        int numeroCartaAccion = Integer.parseInt(actionCardNumber.trim());

        return numeroCartaAccion;
    }

    @Override
    public String getNextGameRoundFromCurrentRound(String estadoRondaActual) {
        String estadoRondaSiguiente = "";

        if(estadoRondaActual.equals(TipoRonda.RONDA_1_DIA.toString())){          // RONDA_1_DIA -> RONDA_2_NOCHE
            estadoRondaSiguiente = TipoRonda.RONDA_2_NOCHE.toString();
        } else if(estadoRondaActual.equals(TipoRonda.RONDA_2_NOCHE.toString())){ // RONDA_2_NOCHE -> RONDA_3_DIA
            estadoRondaSiguiente = TipoRonda.RONDA_3_DIA.toString();
        } else if(estadoRondaActual.equals(TipoRonda.RONDA_3_DIA.toString())){   // RONDA_3_DIA -> RONDA_4_NOCHE
            estadoRondaSiguiente = TipoRonda.RONDA_4_NOCHE.toString();
        } else if(estadoRondaActual.equals(TipoRonda.RONDA_4_NOCHE.toString())){ // RONDA_4_NOCHE -> RONDA_5_DIA
            estadoRondaSiguiente = TipoRonda.RONDA_5_DIA.toString();
        } else if(estadoRondaActual.equals(TipoRonda.RONDA_5_DIA.toString())){   // RONDA_5_DIA -> RONDA_6_NOCHE
            estadoRondaSiguiente = TipoRonda.RONDA_6_NOCHE.toString();
        }

        return estadoRondaSiguiente;
    }

    @Override
    public String getGameRoundWithoutUnderscores() {
        String rondaActual = getGameRound();
        int guionBajo = rondaActual.indexOf("_");

        StringBuilder strRondaActualSinGuionesBajos = new StringBuilder();

        strRondaActualSinGuionesBajos.append(rondaActual.substring(0,5));
        strRondaActualSinGuionesBajos.append(" ");
        strRondaActualSinGuionesBajos.append(rondaActual.substring(6,7));
        strRondaActualSinGuionesBajos.append(" ");
        if(guionBajo != -1) {
            strRondaActualSinGuionesBajos.append(rondaActual.substring(guionBajo + 3));
        }

        return strRondaActualSinGuionesBajos.toString();
    }

    // ********************************************



}

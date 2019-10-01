package com.afr.afrmageknight.services;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.afr.afrmageknight.activities.InitialMenuActivity;
import com.afr.afrmageknight.databaseHelper.SQLiteDatabaseHelper;
import com.afr.afrmageknight.model.CartaAccionBasica;
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

    private Cursor partidaEstadoCursor;
    private Cursor partidaModoCursor;
    private Cursor partidaInformacionRondaCursor;
    private Cursor partidaHeroeDummyCursor;
    private Cursor partidaCristalesDummyCursor;
    private Cursor partidaCartasDummyCursor;
    private Cursor partidaCartasTacticas;

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
    public int getGameTotalCardsDummyPlayer() {
        partidaCartasDummyCursor = myDB.getGameCardsDummyPlayerCursor();

        int numeroCartasTotalJugadorVirtual = 0;

        if (partidaCartasDummyCursor.moveToFirst()){
            do{
                boolean esDescartada = partidaCartasDummyCursor.getInt(partidaCartasDummyCursor.getColumnIndex(COL_3_PARTIDA_CARTAS_HEROE_DUMMY_TABLE)) > 0;
                if(!esDescartada){
                    ++numeroCartasTotalJugadorVirtual;
                }
            }while(partidaCartasDummyCursor.moveToNext() );
        }
        partidaCartasDummyCursor.close();

        return numeroCartasTotalJugadorVirtual;
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
    public CartaTactica getGameTacticCard(String gameTacticCard) {
        CartaTactica cartaTactica = null;
        partidaCartasTacticas = myDB.getGameTacticCardsCursor();

        if(partidaCartasTacticas.moveToFirst()){
            int i = 0;
            do{
                String nombreCartaTactica = partidaCartasTacticas.getString(partidaCartasTacticas.getColumnIndex(COL_2_PARTIDA_CARTAS_TACTICAS_TABLE));

                if(nombreCartaTactica.equals(gameTacticCard)) {
                    int numeroCartaTactica = partidaCartasTacticas.getInt(partidaCartasTacticas.getColumnIndex(COL_1_PARTIDA_CARTAS_TACTICAS_TABLE));
                    boolean esDescartada = partidaCartasTacticas.getInt(partidaCartasTacticas.getColumnIndex(COL_3_PARTIDA_CARTAS_TACTICAS_TABLE)) > 0;
                    String tipoCartaTactica = partidaCartasTacticas.getString(partidaCartasTacticas.getColumnIndex(COL_4_PARTIDA_CARTAS_TACTICAS_TABLE));
                    int numeroOrdenCartaTactica = partidaCartasTacticas.getInt(partidaCartasTacticas.getColumnIndex(COL_5_PARTIDA_CARTAS_TACTICAS_TABLE));
                    String descripcionCartaTactica = partidaCartasTacticas.getString(partidaCartasTacticas.getColumnIndex(COL_6_PARTIDA_CARTAS_TACTICAS_TABLE));

                    cartaTactica = new CartaTactica(numeroCartaTactica, nombreCartaTactica, esDescartada, TipoTactica.valueOf(tipoCartaTactica), numeroOrdenCartaTactica, descripcionCartaTactica);
                    ++i;
                }
            }while(partidaCartasTacticas.moveToNext() && i < 1);
        }
        partidaCartasTacticas.close();

        return cartaTactica;
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

    // ********************************************

    @Override
    public void insertTableGameRoundInformation(String roundInformation) {
        myDB.createGameInformation(roundInformation);
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
    public void modifyTableGameStatusRoundBeginning(boolean esRondaInicio) {
        myDB.updateGameStatusRoundBeginning(esRondaInicio);
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
        int numeroCartasTotalJugadorVirtual = getGameTotalCardsDummyPlayer();

        if(numeroCartasTotalJugadorVirtual==0){
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

    // ********************************************



}

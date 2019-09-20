package com.afr.afrmageknight.servicios;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.afr.afrmageknight.MainActivity;
import com.afr.afrmageknight.databaseHelper.SQLiteDatabaseHelper;
import com.afr.afrmageknight.model.Carta;
import com.afr.afrmageknight.model.CartaAccionBasica;
import com.afr.afrmageknight.model.CartaTactica;
import com.afr.afrmageknight.model.Cristal;
import com.afr.afrmageknight.model.FichaHabilidad;
import com.afr.afrmageknight.model.Heroe;

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


    // DatabaseHel no entrega a través de sus métodos ni Heroes ni Cartas ni Cristales. Sólo cursores

    //I - Declarar las variables
    private SQLiteDatabaseHelper myDB;

    private Cursor heroesCursor;
    private Cursor heroesCristalesCursor;
    private Cursor cartasCursor;
    private Cursor cartasAccionesCursor;
    private Cursor cartasAccionesBasicasCursor;
    private Cursor fichasHabilidadesCursor;


    public GameServicesImpl (Context context){
        myDB = MainActivity.myDB;
    }

    // Implementar los métodos de la interfaz 'GameServices'
    @Override
    public Heroe getAHeroeSelectedByPlayer(String heroeName) {
        Heroe heroe = new Heroe(heroeName, getCristalesFromAHeroe(heroeName));
        return heroe;
    }

    @Override
    public ArrayList<Cristal> getCristalesFromAHeroe(String heroeName) {
        heroesCristalesCursor = myDB.getAllHeroesCristales();

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
        heroesCursor = myDB.getAllHeroes();

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
        cartasAccionesBasicasCursor = myDB.getAllCartasAccionesBasicas();

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

        cartasCursor = myDB.getAllCartas();
        cartasAccionesCursor = myDB.getAllCartasAcciones();

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
        fichasHabilidadesCursor = myDB.getAllFichasHabilidad();

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


    @Override
    public Heroe getRandomHeroeFromHeroesSelectedByPlayer(List<Heroe> selectedByPlayer) {
        return null;
    }

    @Override
    public List<Heroe> getAllHeroes() {
        return null;
    }

    @Override
    public List<Carta> getDummyPlayerCards(Heroe heroe) {
        return null;
    }

    @Override
    public List<Carta> getShuffleCards(List<Carta> dummyPlayerCards) {
        return null;
    }

    @Override
    public List<FichaHabilidad> getShuffleSkillTokens(Heroe heroe) {
        return null;
    }

    @Override
    public CartaTactica getRandomTacticCard(List<CartaTactica> tacticsCards) {
        return null;
    }

    @Override
    public List<CartaTactica> getUpdatedTacticCards(List<CartaTactica> tacticsCards) {
        return null;
    }

    @Override
    public List<Carta> getUpdatedDummyPlayerCards(List<Carta> dummyPlayerCards) {
        return null;
    }

    @Override
    public boolean isLastCardColorAlikeInventoryManaCrystalsColor(Carta lastDummyPlayerCard, ArrayList<Cristal> dummyPlayerManaCrystals) {
        return false;
    }

    @Override
    public ArrayList<Cristal> getUpdatedDummyPlayerManaCrystals(ArrayList<Cristal> dummyPlayerManaCrystals) {
        return null;
    }


}

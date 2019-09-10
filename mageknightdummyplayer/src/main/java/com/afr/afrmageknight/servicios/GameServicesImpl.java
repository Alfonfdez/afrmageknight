package com.afr.afrmageknight.servicios;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.afr.afrmageknight.databaseHelper.DatabaseHelper;
import com.afr.afrmageknight.databaseHelper.DatabaseHelperInsertInitialData;
import com.afr.afrmageknight.model.Carta;
import com.afr.afrmageknight.model.CartaAccion;
import com.afr.afrmageknight.model.CartaAccionBasica;
import com.afr.afrmageknight.model.CartaTactica;
import com.afr.afrmageknight.model.Cristal;
import com.afr.afrmageknight.model.FichaHabilidad;
import com.afr.afrmageknight.model.Heroe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameServicesImpl implements GameServices {

    // DatabaseHelper no entrega a través de sus métodos ni Heroes no Cartas ni Cristales. Sólo cursores

    //I - Declarar las variables
    private DatabaseHelper databaseHelper;
    private DatabaseHelperInsertInitialData myInitialDB;

    private Cursor heroesCursor;
    private Cursor heroesCristalesCursor;
    private Cursor cartasCursor;
    private Cursor cartasAccionesCursor;
    private Cursor cartasAccionesBasicasCursor;
    private Cursor cartasAccionesAvanzadasCursor;
    private Cursor cartasAccionesAvanzadasEspecialesCursor;
    private Cursor cartasHechizosCursor;
    private Cursor cartasTacticasCursor;
    private Cursor fichasHabilidadesCursor;

    public GameServicesImpl (Context context){
        this.databaseHelper = new DatabaseHelperInsertInitialData(context);
        this.myInitialDB = new DatabaseHelperInsertInitialData(context);
    }

    // Implementar los métodos de la interfaz 'GameServices'
    @Override
    public Heroe getAHeroeSelectedByPlayer(String heroeName) {
        Heroe heroe = new Heroe(heroeName, getCristalesFromAHeroe(heroeName));
        return heroe;
    }

    @Override
    public ArrayList<Cristal> getCristalesFromAHeroe(String heroeName) {

        heroesCristalesCursor = myInitialDB.getAllHeroesCristales();

        ArrayList<Cristal> cristales = new ArrayList<Cristal>();

        if (heroesCristalesCursor.moveToFirst()){
            int i = 0;
            do{
                String heroeNameTable = heroesCristalesCursor.getString(heroesCristalesCursor.getColumnIndex("NOMBRE"));

                if(heroeNameTable.equals(heroeName)){
                    String cristalNameTable = heroesCristalesCursor.getString(heroesCristalesCursor.getColumnIndex("CRISTAL"));
                    cristales.add(Cristal.valueOf(cristalNameTable));

                    Log.d("DATABASE","Heroe: "+heroeNameTable+" - Cristal: "+String.valueOf(cristalNameTable));

                    ++i;
                }

            }while(heroesCristalesCursor.moveToNext() && i<3);
        }
        heroesCristalesCursor.close();

        return cristales;
    }

    @Override
    public Heroe getRandomHeroeFromOneHeroeSelectedByPlayer(Heroe heroeSelectedByPlayer) {

        List<String> heroeNames = new ArrayList<>();
        heroesCursor = myInitialDB.getAllHeroes();

        if(heroesCursor.moveToFirst()){
            do{
                String heroNameTable = heroesCursor.getString(heroesCursor.getColumnIndex("NOMBRE"));
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
        cartasAccionesBasicasCursor = myInitialDB.getAllCartasAccionesBasicas();

        if (cartasAccionesBasicasCursor.moveToFirst()){
            int i = 0;
            do{
                String heroeNameTable = cartasAccionesBasicasCursor.getString(cartasAccionesBasicasCursor.getColumnIndex("HEROE"));

                if(heroeNameTable.equals(randomHeroeDummyPlayer.getNombre())){
                    int numeroCartaBasica = cartasAccionesBasicasCursor.getInt(cartasAccionesBasicasCursor.getColumnIndex("NUMERO"));
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

        cartasCursor = myInitialDB.getAllCartas();
        cartasAccionesCursor = myInitialDB.getAllCartasAcciones();

        String nombreCarta = "";
        String cristalColor = "";
        String descripcionBasica = "";
        String descripcionAvanzada = "";

        if (cartasCursor.moveToFirst()){
            int i = 0;
            do{
                int numeroCartaTable = cartasCursor.getInt(cartasCursor.getColumnIndex("NUMERO"));

                if(numeroCartaTable==basicActionCardNumber){
                    nombreCarta = cartasCursor.getString(cartasCursor.getColumnIndex("NOMBRE"));
                    ++i;
                }
            }while(cartasCursor.moveToNext() && i < 1);
        }
        cartasCursor.close();

        if (cartasAccionesCursor.moveToFirst()){
            int i = 0;
            do{
                int numeroCartaTable = cartasAccionesCursor.getInt(cartasAccionesCursor.getColumnIndex("NUMERO"));

                if(numeroCartaTable==basicActionCardNumber){
                    cristalColor = cartasAccionesCursor.getString(cartasAccionesCursor.getColumnIndex("COLOR"));
                    descripcionBasica = cartasAccionesCursor.getString(cartasAccionesCursor.getColumnIndex("DESCRIPCION_BASICA"));
                    descripcionAvanzada = cartasAccionesCursor.getString(cartasAccionesCursor.getColumnIndex("DESCRIPCION_AVANZADA"));
                    ++i;
                }
            }while(cartasAccionesCursor.moveToNext() && i < 1);
        }
        cartasAccionesCursor.close();

        Log.d("DATABASE","Número carta Acción Básica: "+basicActionCardNumber+" - Nombre: "+nombreCarta+" - Color: "+Cristal.valueOf(cristalColor)+ " - Descripción básica: "+descripcionBasica+ " - Descripción avanzada: "+descripcionAvanzada+" - Heroe: "+randomHeroeDummyPlayer.getNombre());

        CartaAccionBasica cartaAccionBasica = new CartaAccionBasica(basicActionCardNumber, nombreCarta, Cristal.valueOf(cristalColor), descripcionBasica, descripcionAvanzada, false, randomHeroeDummyPlayer);

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
        fichasHabilidadesCursor = myInitialDB.getAllFichasHabilidad();

        if (fichasHabilidadesCursor.moveToFirst()){
            int i = 0;
            do{
                String heroeNameTable = fichasHabilidadesCursor.getString(fichasHabilidadesCursor.getColumnIndex("HEROE"));

                if(heroeNameTable.equals(randomHeroeDummyPlayer.getNombre())){
                    int numeroFichaHabilidad = fichasHabilidadesCursor.getInt(fichasHabilidadesCursor.getColumnIndex("ID_FICHA"));
                    String nombreFichaHabilidad = fichasHabilidadesCursor.getString(fichasHabilidadesCursor.getColumnIndex("NOMBRE"));
                    String descripcionFichaHabilidad = fichasHabilidadesCursor.getString(fichasHabilidadesCursor.getColumnIndex("DESCRIPCION"));

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

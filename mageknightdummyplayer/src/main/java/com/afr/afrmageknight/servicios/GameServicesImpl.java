package com.afr.afrmageknight.servicios;

import android.content.Context;
import android.database.Cursor;

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
    private DatabaseHelper databaseHelper = null;
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

    public GameServicesImpl (Context context){
        this.databaseHelper = new DatabaseHelperInsertInitialData(context);
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
                    ++i;
                }

            }while(heroesCristalesCursor.moveToNext() && i<3);
        }
        heroesCristalesCursor.close();

        return cristales;
    }

    @Override
    public Heroe getRandomHeroeFromOneHeroeSelectedByPlayer(Heroe selectedByPlayer) {

        List<String> heroeNames = new ArrayList<>();
        heroesCursor = myInitialDB.getAllHeroes();

        if(heroesCursor.moveToFirst()){
            do{
                String heroNameTable = heroesCursor.getString(heroesCursor.getColumnIndex("NOMBRE"));
                heroeNames.add(heroNameTable);
            }while(heroesCursor.moveToNext());
        }
        heroesCursor.close();

        heroeNames.remove(selectedByPlayer.getNombre());

        //Selecciona un número aleatorio entre 0 y 5, ambos incluidos (heroeNames.size()=6)
        int randomNumber = (int)(Math.random() * heroeNames.size());
        String randomHeroeNameSelected = heroeNames.get(randomNumber);

        Heroe heroe = new Heroe(randomHeroeNameSelected, getCristalesFromAHeroe(randomHeroeNameSelected));

        return heroe;
    }

    @Override
    public List<CartaAccionBasica> getShuffledBasicActionCardsHeroeFromDummyPlayer(Heroe randomHeroeDummyPlayer) {

        ArrayList<CartaAccionBasica> cartaAccionBasicas = getBasicActionCardsHeroeFromDummyPlayer(randomHeroeDummyPlayer);

        //Barajar aleatoriamente la baraja de cartas de Acción Básica del Jugador Virtual
        Collections.shuffle(cartaAccionBasicas);

        return cartaAccionBasicas;
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
                    cartasCursor.close();
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
                    ++i;
                }
            }while(cartasAccionesCursor.moveToNext() && i < 1);
        }

        if (cartasAccionesCursor.moveToFirst()){
            int i = 0;
            do{
                int numeroCartaTable = cartasAccionesCursor.getInt(cartasAccionesCursor.getColumnIndex("NUMERO"));

                if(numeroCartaTable==basicActionCardNumber){
                    descripcionBasica = cartasAccionesCursor.getString(cartasAccionesCursor.getColumnIndex("DESCRIPCION_BASICA"));
                    ++i;
                }
            }while(cartasAccionesCursor.moveToNext()&& i < 1);
        }

        if (cartasAccionesCursor.moveToFirst()){
            int i = 0;
            do{
                int numeroCartaTable = cartasAccionesCursor.getInt(cartasAccionesCursor.getColumnIndex("NUMERO"));

                if(numeroCartaTable==basicActionCardNumber){
                    descripcionAvanzada = cartasAccionesCursor.getString(cartasAccionesCursor.getColumnIndex("DESCRIPCION_AVANZADA"));
                    cartasAccionesCursor.close();
                    ++i;
                }
            }while(cartasAccionesCursor.moveToNext()&& i < 1);
        }

        CartaAccionBasica cartaAccionBasica = new CartaAccionBasica(basicActionCardNumber, nombreCarta, Cristal.valueOf(cristalColor), descripcionBasica, descripcionAvanzada, false, randomHeroeDummyPlayer);

        return cartaAccionBasica;
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

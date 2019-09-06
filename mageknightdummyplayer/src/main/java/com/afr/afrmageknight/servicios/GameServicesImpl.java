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

    public GameServicesImpl (Context context){
        this.databaseHelper = new DatabaseHelperInsertInitialData(context);
    }

    // Implementar los métodos de la interfaz 'GameServices'
    @Override
    public Heroe getAHeroe(String heroeName, Cursor heroesCristalesCursor) {

        ArrayList<Cristal> cristales = new ArrayList<Cristal>();

        if (heroesCristalesCursor.moveToFirst()){
            do{
                String heroeNameTable = heroesCristalesCursor.getString(heroesCristalesCursor.getColumnIndex("NOMBRE"));

                if(heroeNameTable.equals(heroeName)){
                    String cristalNameTable = heroesCristalesCursor.getString(heroesCristalesCursor.getColumnIndex("CRISTAL"));

                    cristales.add(Cristal.valueOf(cristalNameTable));
                }

            }while(heroesCristalesCursor.moveToNext());
        }

        heroesCristalesCursor.close();

        Heroe heroe = new Heroe(heroeName, cristales);

        return heroe;
    }

    @Override
    public List<Heroe> getAllHeroes(Cursor heroesCursor, Cursor heroesCristalesCursor) {
        List<Heroe> heroes = new ArrayList<Heroe>();

        if (heroesCursor.moveToFirst()){
            do{
                String heroeName = heroesCursor.getString(heroesCursor.getColumnIndex("NOMBRE"));

                ArrayList<Cristal> heroeCristales = getHeroeCristal(heroeName, heroesCristalesCursor);

                Heroe heroe = new Heroe(heroeName, heroeCristales);

                heroes.add(heroe);

            }while(heroesCursor.moveToNext());
        }

        heroesCursor.close();

        return heroes;
    }

    @Override
    public ArrayList<Cristal> getHeroeCristal(String heroeName, Cursor heroesCristalesCursor) {

        ArrayList<Cristal> cristales = new ArrayList<Cristal>();

        if (heroesCristalesCursor.moveToFirst()){
            do{
                String heroeNameTable = heroesCristalesCursor.getString(heroesCristalesCursor.getColumnIndex("NOMBRE"));

                if(heroeNameTable.equals(heroeName)){
                    String cristalNameTable = heroesCristalesCursor.getString(heroesCristalesCursor.getColumnIndex("CRISTAL"));

                    cristales.add(Cristal.valueOf(cristalNameTable));
                }

            }while(heroesCristalesCursor.moveToNext());
        }

        heroesCristalesCursor.close();

        return cristales;
    }

    @Override
    public Heroe getRandomHeroeFromOneHeroeSelectedByPlayer(Heroe selectedByPlayer, Cursor heroesCristalesCursor) {

        List<String> heroeNames = new ArrayList<>();
        Collections.addAll( heroeNames, "Arythea","Tovak","Norowas","Goldyx","Wolfhawk","Krang","Braevalar");

        String heroeName = selectedByPlayer.getNombre();
        heroeNames.remove(heroeName);

        //Selecciona un número aleatorio entre 0 y 5, ambos incluidos
        int randomNumber = (int)(Math.random() * 6);

        String randomHeroeNameSelected = heroeNames.get(randomNumber);

        ArrayList<Cristal> cristales = getHeroeCristal(randomHeroeNameSelected, heroesCristalesCursor);

        Heroe heroe = new Heroe(randomHeroeNameSelected, cristales);

        return heroe;
    }

    @Override
    public Heroe getRandomHeroeFromHeroesSelectedByPlayer(List<Heroe> selectedByPlayer) {
        return null;
    }

    @Override
    public List<CartaAccionBasica> getShuffledCardsHeroeFromDummyPlayer(Heroe randomHeroeDummyPlayer, Cursor cartasCursor, Cursor cartasAccionesCursor, Cursor cartasAccionesBasicasCursor) {

        ArrayList<CartaAccionBasica> cartaAccionBasicas = new ArrayList<CartaAccionBasica>();

        if (cartasAccionesBasicasCursor.moveToFirst()){
            do{
                String heroeNameTable = cartasAccionesBasicasCursor.getString(cartasAccionesBasicasCursor.getColumnIndex("HEROE"));

                if(heroeNameTable.equals(randomHeroeDummyPlayer.getNombre())){
                    int numeroCartaBasica = cartasAccionesBasicasCursor.getInt(cartasAccionesBasicasCursor.getColumnIndex("NUMERO"));
                    String nombreCarta = getCartaNombre(numeroCartaBasica, cartasCursor);
                    String cristalColor = getCartaCristal(numeroCartaBasica, cartasAccionesCursor);
                    String descripcionBasica = getCartaDescripcionBasica(numeroCartaBasica, cartasAccionesCursor);
                    String descripcionAvanzada= getCartaDescripcionAvanzada(numeroCartaBasica, cartasAccionesCursor);

                    CartaAccionBasica cartaAccionBasica = new CartaAccionBasica(numeroCartaBasica, nombreCarta, Cristal.valueOf(cristalColor), descripcionBasica, descripcionAvanzada, false, randomHeroeDummyPlayer);

                    cartaAccionBasicas.add(cartaAccionBasica);
                }

            }while(cartasAccionesBasicasCursor.moveToNext());
        }

        cartasAccionesBasicasCursor.close();

        //Barajar aleatoriamente la baraja de cartas de Acción Básica del Jugador Virtual
        Collections.shuffle(cartaAccionBasicas);

        return cartaAccionBasicas;
    }

    @Override
    public String getCartaNombre(int numeroCarta, Cursor cartasCursor) {

        if (cartasCursor.moveToFirst()){
            do{
                int numeroCartaTable = cartasCursor.getInt(cartasCursor.getColumnIndex("NUMERO"));

                if(numeroCartaTable==numeroCarta){
                    String nombreCartaTable = cartasCursor.getString(cartasCursor.getColumnIndex("NOMBRE"));

                    cartasCursor.close();

                    return nombreCartaTable;
                }

            }while(cartasCursor.moveToNext());
        }

        cartasCursor.close();

        return null;
    }

    @Override
    public String getCartaCristal(int numeroCarta, Cursor cartasAccionesCursor) {

        if (cartasAccionesCursor.moveToFirst()){
            do{
                int numeroCartaTable = cartasAccionesCursor.getInt(cartasAccionesCursor.getColumnIndex("NUMERO"));

                if(numeroCartaTable==numeroCarta){
                    String cristalCartaTable = cartasAccionesCursor.getString(cartasAccionesCursor.getColumnIndex("COLOR"));

                    cartasAccionesCursor.close();

                    return cristalCartaTable;
                }

            }while(cartasAccionesCursor.moveToNext());
        }

        cartasAccionesCursor.close();

        return null;
    }

    @Override
    public String getCartaDescripcionBasica(int numeroCarta, Cursor cartasAccionesCursor) {
        if (cartasAccionesCursor.moveToFirst()){
            do{
                int numeroCartaTable = cartasAccionesCursor.getInt(cartasAccionesCursor.getColumnIndex("NUMERO"));

                if(numeroCartaTable==numeroCarta){
                    String descripcionBasicaCartaTable = cartasAccionesCursor.getString(cartasAccionesCursor.getColumnIndex("DESCRIPCION_BASICA"));

                    cartasAccionesCursor.close();

                    return descripcionBasicaCartaTable;
                }

            }while(cartasAccionesCursor.moveToNext());
        }

        cartasAccionesCursor.close();

        return null;
    }

    @Override
    public String getCartaDescripcionAvanzada(int numeroCarta, Cursor cartasAccionesCursor) {
        if (cartasAccionesCursor.moveToFirst()){
            do{
                int numeroCartaTable = cartasAccionesCursor.getInt(cartasAccionesCursor.getColumnIndex("NUMERO"));

                if(numeroCartaTable==numeroCarta){
                    String descripcionAvanzadaCartaTable = cartasAccionesCursor.getString(cartasAccionesCursor.getColumnIndex("DESCRIPCION_AVANZADA"));

                    cartasAccionesCursor.close();

                    return descripcionAvanzadaCartaTable;
                }

            }while(cartasAccionesCursor.moveToNext());
        }

        cartasAccionesCursor.close();

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

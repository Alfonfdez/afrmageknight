package com.afr.afrmageknight.servicios;

import android.content.Context;

import com.afr.afrmageknight.databaseHelper.DatabaseHelper;
import com.afr.afrmageknight.databaseHelper.DatabaseHelperInsertInitialData;
import com.afr.afrmageknight.model.Carta;
import com.afr.afrmageknight.model.CartaAccionBasica;
import com.afr.afrmageknight.model.CartaTactica;
import com.afr.afrmageknight.model.Cristal;
import com.afr.afrmageknight.model.FichaHabilidad;
import com.afr.afrmageknight.model.Heroe;

import java.util.ArrayList;
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
    public Heroe getRandomHeroe(List<Heroe> selectedByPlayer) {
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

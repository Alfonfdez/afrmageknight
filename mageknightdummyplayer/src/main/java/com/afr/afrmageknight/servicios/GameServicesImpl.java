package com.afr.afrmageknight.servicios;

import android.content.Context;

import com.afr.afrmageknight.databaseHelper.DatabaseHelper;
import com.afr.afrmageknight.model.CartaAvanzada;
import com.afr.afrmageknight.model.FichaHabilidad;
import com.afr.afrmageknight.model.Heroe;

import java.util.List;

public class GameServicesImpl implements GameServices {

    //I - Declarar las variables
    private DatabaseHelper databaseHelper = null;

    public GameServicesImpl (Context context){
        this.databaseHelper = new DatabaseHelper(context);
    }

    // Implementar los métodos de la interfaz 'GameServices'
    @Override
    public Heroe getRandomHeroe(List<Heroe> selectedByPlayer) {
        return null;
    }

    @Override
    public List<CartaAvanzada> getShuffleCards(Heroe heroe) {
        return null;
    }

    @Override
    public List<FichaHabilidad> getShuffleSkillTokens(Heroe heroe) {
        return null;
    }

}

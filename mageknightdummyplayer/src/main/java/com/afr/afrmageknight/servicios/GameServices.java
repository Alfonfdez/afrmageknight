package com.afr.afrmageknight.servicios;

import com.afr.afrmageknight.model.CartaAccionBasica;
import com.afr.afrmageknight.model.FichaHabilidad;
import com.afr.afrmageknight.model.Heroe;

import java.util.List;

public interface GameServices {

    public Heroe getRandomHeroe(List<Heroe> selectedByPlayer);

    public List<CartaAccionBasica> getShuffleCards(Heroe heroe);

    public List<FichaHabilidad> getShuffleSkillTokens(Heroe heroe);




}

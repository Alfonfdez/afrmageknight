package com.afr.afrmageknight.servicios;

import com.afr.afrmageknight.model.Carta;
import com.afr.afrmageknight.model.CartaAccionBasica;
import com.afr.afrmageknight.model.CartaTactica;
import com.afr.afrmageknight.model.Cristal;
import com.afr.afrmageknight.model.FichaHabilidad;
import com.afr.afrmageknight.model.Heroe;

import java.util.ArrayList;
import java.util.List;

public interface GameServices {

    /**
     *  Método para seleccionar aleatoriamente el héroe que controlará el Jugador Virtual a partir de los héroes no seleccionados por los jugadores
     *
     * @param selectedByPlayer
     * @return
     */
    public Heroe getRandomHeroe(List<Heroe> selectedByPlayer);

    // Método para selecionar el mazo inicial de Gesta del Jugador Virtual: las correspondientes 16 cartas de su héroe
    public List<Carta> getDummyPlayerCards(Heroe heroe);

    // Método para barajar las cartas del mazo de cartas del Jugador Virtual
    public List<Carta> getShuffleCards(List<Carta> dummyPlayerCards);

    // Método para barajar las Fichas de Habilidad del Jugador Virtual
    public List<FichaHabilidad> getShuffleSkillTokens(Heroe heroe);

    // Método para seleccionar aleatoriamente una Táctica (de las disponibles) para el Jugador Virtual
    public CartaTactica getRandomTacticCard(List<CartaTactica> tacticsCards);

    // Método para actualizar el mazo de cartas Tácticas.
    // Cooperativo: eliminar 1 carta usada por los jugadores, a su elección.
    // Solitario: eliminar las 2 cartas en total, usadas por el jugador y el Jugador Virtual
    public List<CartaTactica> getUpdatedTacticCards(List<CartaTactica> tacticsCards);

    // Método para actualizar el mazo de cartas del Jugador Virtual.
    // Al final de la Ronda se eliminarán 3 cartas y si el color de la última carta coincide con algún cristal del Inventario del Jugador Virtual,
    // se eliminarán tantas cartas más como cristales tenga de ese color.
    public List<Carta> getUpdatedDummyPlayerCards(List<Carta> dummyPlayerCards);

    // Método para comprobar que el color de la última carta eliminada por el Jugador Virtual (de las 3 eliminadas al final de la Ronda) coincide con algún cristal de su Inventario
    public boolean isLastCardColorAlikeInventoryManaCrystalsColor(Carta lastDummyPlayerCard, ArrayList<Cristal> dummyPlayerManaCrystals);

    // Método para añadir al final de la Ronda un cristal del color de la carta Inferior de la Oferta de Hechizos al Inventario del Jugador Virtual
    public ArrayList<Cristal> getUpdatedDummyPlayerManaCrystals(ArrayList<Cristal> dummyPlayerManaCrystals);
}

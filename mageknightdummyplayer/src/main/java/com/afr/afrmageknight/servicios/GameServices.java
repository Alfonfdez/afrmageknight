package com.afr.afrmageknight.servicios;

import android.database.Cursor;

import com.afr.afrmageknight.model.Carta;
import com.afr.afrmageknight.model.CartaAccion;
import com.afr.afrmageknight.model.CartaAccionBasica;
import com.afr.afrmageknight.model.CartaTactica;
import com.afr.afrmageknight.model.Cristal;
import com.afr.afrmageknight.model.FichaHabilidad;
import com.afr.afrmageknight.model.Heroe;

import java.util.ArrayList;
import java.util.List;

public interface GameServices {

    // Método que devuelve 1 héroe del juego mediante un Cursor como parámetro
    public Heroe getAHeroe(String heroeName, Cursor heroesCristalesCursor);

    // Método que devuelve los héroes del juego mediante un Cursor como parámetro
    public List<Heroe> getAllHeroes(Cursor heroesCursor, Cursor heroesCristalesCursor);

    // Método que devuelve todos los cristales que perteneces a cada héroe
    public List<Cristal> getHeroeCristal(String heroeName, Cursor heroesCristalesCursor);

    //Método para seleccionar aleatoriamente un héroe a partir de la selección de un único héroe por parte del jugador
    public Heroe getRandomHeroeFromOneHeroeSelectedByPlayer(Heroe selectedByPlayer, Cursor heroesCristalesCursor);

    /**
     *  Método para seleccionar aleatoriamente el héroe que controlará el Jugador Virtual a partir de los héroes no seleccionados por los jugadores
     *
     * @param selectedByPlayer
     * @return
     */
    public Heroe getRandomHeroeFromHeroesSelectedByPlayer(List<Heroe> selectedByPlayer);

    //Método para seleccionar todas las cartas de Acción Básica del Jugador Virtual y obtenerlas ya barajadas
    public List<CartaAccionBasica> getShuffledCardsHeroeFromDummyPlayer(Heroe randomHeroeDummyPlayer, Cursor cartasCursor, Cursor cartasAccionesCursor, Cursor cartasAccionesBasicasCursor);

    //Método para obtener nombre de una Carta
    public String getCartaNombre(int numeroCarta, Cursor cartasCursor);

    //Método para obtener el cristal de una Carta
    public String getCartaCristal(int numeroCarta, Cursor cartasAccionesCursor);

    //Método para obtener la descripcion básica de una Carta
    public String getCartaDescripcionBasica(int numeroCarta, Cursor cartasAccionesCursor);

    //Método para obtener la descripcion avanzada de una Carta
    public String getCartaDescripcionAvanzada(int numeroCarta, Cursor cartasAccionesCursor);

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

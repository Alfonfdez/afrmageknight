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

    // Método para saber el Estado de la partida
    public String getGameStatus();

    // Método que devuelve 1 héroe concreto seleccionado por el jugador
    public Heroe getAHeroeSelectedByPlayer(String heroeName);

    // Método que devuelve 2 o 3 héroes concretos seleccionados por el jugador
    public List<Heroe> getHeroesSelectedByPlayer(List<String> heroeNames);

    //Método para recoger todos los cristales iniciales de un héroe en concreto
    public ArrayList<Cristal> getCristalesFromAHeroe(String heroeName);

    //Método para obtener aleatoriamente un héroe a partir de la selección de un único héroe por parte del jugador
    public Heroe getRandomHeroeFromOneHeroeSelectedByPlayer(Heroe heroeSelectedByPlayer);

    //Método para obtener aleatoriamente un héroe a partir de la selección de 2 o 3 héroes por parte del jugador
    public Heroe getRandomHeroeFromHeroesSelectedByPlayer(List<Heroe> heroesSelectedByPlayer);



    //Método para obtener todas las cartas de Acción Básica del Jugador Virtual ya barajadas
    public List<CartaAccionBasica> getShuffledBasicActionCardsHeroeFromDummyPlayer(Heroe randomHeroeDummyPlayer);

    //Método para obtener todas las cartas de Acción Básica del Jugador Virtual
    public List<CartaAccionBasica> getBasicActionCardsHeroeFromDummyPlayer(Heroe randomHeroeDummyPlayer);

    //Método para obtener una carta de Acción Básica
    public CartaAccionBasica getBasicActionCard(int basicActionCardNumber, Heroe randomHeroeDummyPlayer);

    //Método para obtener todas las fichas de Habilidad del Jugador Virtual ya barajadas
    public List<FichaHabilidad> getShuffledSkillTokensHeroeFromDummyPlayer(Heroe randomHeroeDummyPlayer);

    //Método para obtener todas las fichas de Habilidad del Jugador Virtual
    public List<FichaHabilidad> getSkillTokensHeroeFromDummyPlayer(Heroe randomHeroeDummyPlayer);

    //Método para obtener una ficha de Habilidad
    public FichaHabilidad getSkillToken(int idFicha, String nombreFichaHabilidad,  String descripcionFichaHabilidad, Heroe randomHeroeDummyPlayer);



    // Método que devuelve todos los héroes del juego
    public List<Heroe> getAllHeroes();

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
    // Al final de la TipoRonda se eliminarán 3 cartas y si el color de la última carta coincide con algún cristal del Inventario del Jugador Virtual,
    // se eliminarán tantas cartas más como cristales tenga de ese color.
    public List<Carta> getUpdatedDummyPlayerCards(List<Carta> dummyPlayerCards);

    // Método para comprobar que el color de la última carta eliminada por el Jugador Virtual (de las 3 eliminadas al final de la TipoRonda) coincide con algún cristal de su Inventario
    public boolean isLastCardColorAlikeInventoryManaCrystalsColor(Carta lastDummyPlayerCard, ArrayList<Cristal> dummyPlayerManaCrystals);

    // Método para añadir al final de la TipoRonda un cristal del color de la carta Inferior de la Oferta de Hechizos al Inventario del Jugador Virtual
    public ArrayList<Cristal> getUpdatedDummyPlayerManaCrystals(ArrayList<Cristal> dummyPlayerManaCrystals);
}

package com.afr.afrmageknight.services;

import com.afr.afrmageknight.model.Carta;
import com.afr.afrmageknight.model.CartaAccionBasica;
import com.afr.afrmageknight.model.CartaTactica;
import com.afr.afrmageknight.model.Cristal;
import com.afr.afrmageknight.model.FichaHabilidad;
import com.afr.afrmageknight.model.Heroe;

import java.util.ArrayList;
import java.util.List;

public interface GameServices {

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

    // ********************************************

    // Método para obtener todas las cartas Tácticas
    public List<CartaTactica> getTacticCards();

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

    // ********************************************

    // Método para saber el Estado de la partida (EN_PREPARACION, INICIADA, FINALIZADA)
    public String getGameStatus();

    // Método para saber el Modo de juego de la partida (SOLITARIO, COOPERATIVO)
    public String getGameType();

    // Método para saber en qué Ronda de la partida nos encontramos (RONDA_1_DIA, RONDA_2_NOCHE, RONDA_3_DIA, RONDA_4_NOCHE, RONDA_5_DIA, RONDA_6_NOCHE)
    public String getGameRound();

    // Método para saber el nombre del héroe que lleva el Jugador Virtual
    public String getHeroeNameDummyPlayer();

    // Método para saber los cristales del Jugador Virtual
    public String getHeroeCristalsDummyPlayer();

    // Método para saber el total de cartas del Jugador Virtual
    public int getTotalCardsDummyPlayer();

    // ********************************************

    //Método para saber si la Partida se encuentra 'INICIADA' o no ('EN_PREPARACION' / 'FINALIZADA')
    public boolean isGameStatusInitiated();

    //Método para saber si la Partida se encuentra 'FINALIZADA' o no ('EN_PREPARACION' / 'INICIADA')
    public boolean isGameStatusFinished();

    //Método para saber si el modo de la Partida es en 'SOLITARIO' o no ('COOPERATIVO')
    public boolean isGameTypeSolitaire();

    //Método para saber si estamos al comienzo de la Ronda o no
    public boolean isRoundBeginning();

    //Método para saber si estamos al comienzo de la Ronda o no
    public boolean isRoundEnding();

    //Método para saber si el mazo del Jugador Virtual está acabado o no
    public boolean isDummyPlayerCardsFinished();



}

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

    //Método para obtener el nombre de la carta
    public String getNameCard(int cardNumber);

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

    // Método para saber qué nivel de Experiencia tiene el Jugador (EXPERIENCIA) (0, 2, 4, 6, 8, 10)
    public int getGamePlayerExperience();

    // Método para saber qué nivel de Experiencia tendrá el Jugador con 2 niveles más (EXPERIENCIA) (2, 4, 6, 8, 10)
    public int getGamePlayerExperiencePlusTwo();

    // Método para saber la información de la Ronda
    public String getGameRoundInformation();

    // Método para saber el nombre del héroe que lleva el Jugador Virtual
    public String getGameHeroeNameDummyPlayer();

    // Método para saber los cristales del Jugador Virtual
    public String getGameHeroeCristalsDummyPlayer();

    // Método para saber el total de cartas del Jugador Virtual
    public int getGameTotalCardsDummyPlayer();

    // Método para obtener todas las cartas disponibles del Jugador Virtual por número de carta ("Descartada" = 0)
    public List<Integer> getGameAvailableCardsDummyPlayerByNumber();

    // Método para obtener todas las cartas del Jugador Virtual por número de carta
    public List<Integer> getGameCardsDummyPlayerByNumber();

    // Método para obtener ya barajadas todas las cartas del Jugador Virtual
    public List<Integer> getShuffledGameCardsDummyPlayerByNumber(List<Integer> gameCardsDummyPlayerByNumber);

    // Método para obtener aquellas cartas Tácticas de Día disponibles ("Descartada" = 0 && "Tipo_Tactica" = DIA)
    public List<CartaTactica> getGameAvailableDayTacticsCards();

    // Método para obtener aquellas cartas Tácticas de Noche disponibles ("Descartada" = 0 && "Tipo_Tactica" = NOCHE)
    public List<CartaTactica> getGameAvailableNightTacticsCards();

    // Método para obtener una carta Táctica a partir de su nombre
    public CartaTactica getGameTacticCard(String gameTacticCard);

    // Método para obtener una carta Táctica disponible al azar
    public CartaTactica getGameAvailableRandomTacticCard(List<CartaTactica> gameTacticCardsAvailable);

    //Método para obtener un "String" informativo sobre la Carta Táctica escogida por el Jugador Virtual en el modo Solitario
    public String getGameInformationTacticCardDummyPlayerSolitaireType(CartaTactica cartaTactica);

    //Método para obtener un "String" informativo sobre la Carta Táctica escogida por el Jugador Virtual en el modo Cooperativo
    public String getGameInformationTacticCardDummyPlayerCooperativeType(CartaTactica cartaTactica);

    //Método para obtener un "List<FichaHabilidad>" dependiendo del nivel de experiencia del Jugador (Niveles 4, 6, 8, 10)
    public List<FichaHabilidad> getGameSkillTokensDummyPlayerBasedOnPlayerExperience(int nivelExperienciaJugador);

    //Método para obtener un "FichaHabilidad" dependiendo del número de su 'INDICE'
    public FichaHabilidad getGameSkillTokenDummyPlayerByIndex(int indiceFichaHabilidad);

    // ********************************************

    //Método para insertar informacion en la tabla 'PARTIDA_INFORMACION_RONDA'
    public void insertTableGameRoundInformation(String roundInformation);

    // ********************************************

    // Método para modificar la columna 'DESCARTADA'=1 de la tabla 'PARTIDA_CARTAS_TACTICAS' en la fila 'NOMBRE'="tacticCardName"
    public void modifyTableGameTacticCardAvailabilityByName(String tacticCardName, boolean esDescartada);

    // Método para modificar la columna 'RONDA_ESTADO_INICIO'=0 de la tabla 'PARTIDA_DATOS'
    public void modifyTableGameStatusRoundBeginning(boolean esRondaInicio);

    // Método para modificar la columna 'EXPERIENCIA' de la tabla 'PARTIDA_DATOS'
    public void modifyTableGameStatusPlayerExperience(int experienciaJugador);

    // Método para modificar la columna 'INDICE' y 'DESCARTADA'=0, de la tabla 'PARTIDA_CARTAS_HEROE_DUMMY' con las cartas del Jugador Virtual ya barajadas
    public void modifyTableGameShuffledCardsDummyPlayer(List<Integer> gameShuffledCardsDummyPlayerByNumber, boolean esDescartada);

    // ********************************************

    //Método para saber si la Partida se encuentra 'INICIADA' o no ('EN_PREPARACION' / 'FINALIZADA')
    public boolean isGameStatusInitiated();

    //Método para saber si la Partida se encuentra 'FINALIZADA' o no ('EN_PREPARACION' / 'INICIADA')
    public boolean isGameStatusFinished();

    //Método para saber si el modo de la Partida es en 'SOLITARIO' o no ('COOPERATIVO')
    public boolean isGameTypeSolitaire();

    //Método para saber si estamos al comienzo de la Ronda o no
    public boolean isRoundBeginning();

    //Método para saber si estamos al final de la Ronda o no
    public boolean isRoundEnding();

    //Método para saber si estamos en la 1a Ronda del juego ('RONDA_1_DIA')
    public boolean isFirstRound();

    //Método para saber si estamos en la última ronda del juego ('RONDA_6_NOCHE')
    public boolean isLastRound();

    //Método para saber si estamos en las 2 últimas rondas del juego ('RONDA_5_DIA' / 'RONDA_6_NOCHE')
    public boolean isLastTwoRounds();

    //Método para saber si estamos en una Ronda de Día o no (Ronda de Noche)
    public boolean isDayRound();

    //Método para saber si el mazo del Jugador Virtual está acabado o no
    public boolean isDummyPlayerCardsFinished();

    //Método para saber si el Jugador tiene Experiencia 4 o más (6, 8 o 10)
    public boolean isPlayerExperienceLevelFourOrMore();

    //Método para saber si el Jugador ha alcanzado el máximo nivel de Experiencia (10)
    public boolean isPlayerExperienceUpToTen();

    // ********************************************

    //Método para obtener el nombre de una carta Táctica en concreto
    public String getTacticCardNameFromString(String tacticCard);


}

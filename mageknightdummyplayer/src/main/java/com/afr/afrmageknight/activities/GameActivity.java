package com.afr.afrmageknight.activities;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.afr.afrmageknight.R;
import com.afr.afrmageknight.fragments.AdvancedActionCardsDialogFragment;
import com.afr.afrmageknight.fragments.LevelUpDialogFragment;
import com.afr.afrmageknight.fragments.SpellCardsDialogFragment;
import com.afr.afrmageknight.fragments.TacticsDialogFragment;
import com.afr.afrmageknight.fragments.TacticsListDialogFragment;
import com.afr.afrmageknight.model.CartaTactica;
import com.afr.afrmageknight.model.TipoEstado;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    private Button buttonContinuarTurno;
    private Button buttonFinalizarRonda;
    private Button buttonMostrarTacticas;
    private Button buttonSubirNivel;

    private TextView textViewNombreJugadorVirtual;
    private TextView textViewCristalesInicialesJugadorVirtual;
    private TextView textViewCristalesExtrasJugadorVirtual;
    private TextView textViewNumeroTotalCartasJugadorVirtual;
    private TextView textViewTipoPartida;
    private TextView textViewRondaPartida;
    private TextView textViewInformacionPartida;

    private DialogFragment tacticasDialogFragment;
    private DialogFragment tacticasListadoDialogFragment;
    private DialogFragment subidaNivelDialogFragment;
    private DialogFragment accionesAvanzadasDialogFragment;
    private DialogFragment hechizosDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        buttonContinuarTurno = (Button) findViewById(R.id.idButtonContinuarTurno);
        buttonFinalizarRonda = (Button) findViewById(R.id.idButtonFinalizarRonda);
        buttonMostrarTacticas = (Button) findViewById(R.id.idButtonTacticasDisponibles);
        buttonSubirNivel = (Button) findViewById(R.id.idButtonSubidaNivel);

        textViewNombreJugadorVirtual = (TextView) findViewById(R.id.idTextViewNombreHeroeJV);
        textViewCristalesInicialesJugadorVirtual = (TextView) findViewById(R.id.idTextViewCristalesInicialesJV);
        textViewCristalesExtrasJugadorVirtual = (TextView) findViewById(R.id.idTextViewCristalesFinalesJV);
        textViewNumeroTotalCartasJugadorVirtual = (TextView) findViewById(R.id.idTextViewCartasJV);
        textViewTipoPartida = (TextView) findViewById(R.id.idTextViewTipoPartida);
        textViewRondaPartida = (TextView) findViewById(R.id.idTextViewRonda);
        textViewInformacionPartida = (TextView) findViewById(R.id.idTextViewInformacionPartida);

        tacticasDialogFragment = new TacticsDialogFragment();
        tacticasListadoDialogFragment = new TacticsListDialogFragment();
        subidaNivelDialogFragment = new LevelUpDialogFragment();
        accionesAvanzadasDialogFragment = new AdvancedActionCardsDialogFragment();
        hechizosDialogFragment = new SpellCardsDialogFragment();

        //Métodos de inicio en "onCreate"
        checkGameType();
        checkGameFinished();
        showGameDataOnScreen();
        startRound();

        //Jugar Turno a Turno
        buttonContinuarTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!InitialMenuActivity.gameServicesImpl.isGameStatusFinished()){ // PARTIDA_ESTADO = EN_PREPARACION || PARTIDA_ESTADO = INICIADA
                    if(!InitialMenuActivity.gameServicesImpl.isRoundEnding()){ // RONDA_ESTADO_FINALIZADO = 0

                        //Aumentaremos en 1 el número del Turno
                        int numeroTurno = InitialMenuActivity.gameServicesImpl.getGameTurnInformation();
                        int siguienteTurno = ++numeroTurno;
                        InitialMenuActivity.gameServicesImpl.modifyTableGameStatusTurnNumber(siguienteTurno);

                        if(InitialMenuActivity.gameServicesImpl.isDummyPlayerCardsFinished()){ // Si el mazo de cartas del Jugador Virtual está vacío (0), cambiaremos a 'RONDA_ESTADO_FINALIZADO' = 1
                            // Cambiaremos a 'RONDA_ESTADO_FINALIZADO' = 1 de la tabla 'PARTIDO_DATOS'
                            InitialMenuActivity.gameServicesImpl.modifyTableGameStatusRoundEnding(true);

                            //Insertar la información de la Ronda finalizada
                            String informacionPartida = InitialMenuActivity.gameServicesImpl.getGameInformationRoundFinishedByEmptyDeedDeckDummyPlayer();
                            InitialMenuActivity.gameServicesImpl.insertTableGameRoundInformation(informacionPartida);

                            showGameDataOnScreen();
                        } else {
                            int numeroCartasDescartadasPrincipio = 0;

                            //Seleccionaremos las 3 primeras cartas con índices inferiores del Jugador Virtual (Si es posible, sino 2 cartas y sino 1 carta solamente)
                            for(int i = 0 ; i < 3; i++){
                                if(!InitialMenuActivity.gameServicesImpl.isDummyPlayerCardsFinished()){ //Si el mazo de cartas del Jugador Virtual NO está vacío, seguiremos descartando cartas (hasta un máximo de 3 descartes)
                                    int numeroIndiceCartaADescartar = InitialMenuActivity.gameServicesImpl.getMinIndexFromFirstToBeDiscardedGameCardsDummyPlayerDuringTurn();
                                    int numeroCartaADescartar = InitialMenuActivity.gameServicesImpl.getCardNumberDummyPlayerTurnFromIndex(numeroIndiceCartaADescartar);

                                    //Método para descartar ('DESCARTADA' = 1) la carta del mazo del Jugador Virtual a partir de su numero de carta
                                    InitialMenuActivity.gameServicesImpl.modifyTableGameDummyPlayerCardAvailabilityByCardNumber(numeroCartaADescartar, true);

                                    ++numeroCartasDescartadasPrincipio;
                                }
                            }

                            int numeroIndiceUltimaCartaDescartada = InitialMenuActivity.gameServicesImpl.getMaxIndexFromDiscardedGameCardsDummyPlayerDuringTurn();
                            int numeroUltimaCartaDescartada = InitialMenuActivity.gameServicesImpl.getCardNumberDummyPlayerTurnFromIndex(numeroIndiceUltimaCartaDescartada);

                            String colorCarta = "";
                            List<String> coloresCarta = new ArrayList<String>();
                            int numeroCartasExtrasADescartar = 0;

                            if(!InitialMenuActivity.gameServicesImpl.isLastDiscardedCardDummyPlayerSpecialAdvancedActionCardType(numeroUltimaCartaDescartada)){ // La última carta descartada es de tipo Básica / Acción Avanzada (1 único color)
                                colorCarta = InitialMenuActivity.gameServicesImpl.getColorFromLastDiscardedCardDummyPlayerTurnByCardNumber(numeroUltimaCartaDescartada);

                                // Si la última carta descartada es de tipo Básica / Acción Avanzada (1 color), descartaremos tantas cartas extras del mazo del Jugador Virtual, como cristales de ese color tenga en el Inventario
                                numeroCartasExtrasADescartar = InitialMenuActivity.gameServicesImpl.getNumberOfExtraCardsToBeDiscardedByLastCardDiscardedColor(colorCarta);

                                //Insertar la información del Turno sobre las primeras cartas descartadas y el color de la última carta descartada
                                String informacionPartida = InitialMenuActivity.gameServicesImpl.getGameInformationTurnFirstDiscardedCardsLastCardBasicOrAdvancedActionCardType(numeroCartasDescartadasPrincipio, colorCarta, siguienteTurno);
                                InitialMenuActivity.gameServicesImpl.insertTableGameRoundInformation(informacionPartida);
                            } else {  // La última carta descartada es de tipo Acción Avanzada Especial (2 colores)
                                coloresCarta = InitialMenuActivity.gameServicesImpl.getColorsFromLastDiscardedCardDummyPlayerTurnByCardNumber(numeroUltimaCartaDescartada);

                                // Si la última carta descartada es de Acción Avanzada Especial (2 colores), descartaremos tantas cartas extras del mazo del Jugador Virtual, como cristales de esos colores tenga en el Inventario
                                numeroCartasExtrasADescartar = InitialMenuActivity.gameServicesImpl.getNumberOfExtraCardsToBeDiscardedByLastCardDiscardedColors(coloresCarta);

                                //Insertar la información del Turno sobre las primeras cartas descartadas y los colores de la última carta descartada
                                String informacionPartida = InitialMenuActivity.gameServicesImpl.getGameInformationTurnFirstDiscardedCardsLastCardSpecialAdvancedActionCardType(numeroCartasDescartadasPrincipio, coloresCarta, siguienteTurno);
                                InitialMenuActivity.gameServicesImpl.insertTableGameRoundInformation(informacionPartida);
                            }

                            //Si ha habido alguna coincidencia de color de última carta descartada con los cristales del Inventario del JV, descartaremos tantas cartas como coincidencias hayan habido (si esto es posible)
                            if(numeroCartasExtrasADescartar != 0){
                                int numeroCartasDescartadasExtras = 0;

                                //Descartaremos tantas cartas extras como número de coincidencias con el color o colores de la última carta descartada del mazo del Jugador Virtual (Si es posible todavía descartar más cartas del mazo)
                                for(int i = 0 ; i < numeroCartasExtrasADescartar; i++){
                                    if(!InitialMenuActivity.gameServicesImpl.isDummyPlayerCardsFinished()){ //Si el mazo de cartas del Jugador Virtual NO está vacío, seguiremos descartando cartas
                                        int numeroIndiceCartaADescartarExtra = InitialMenuActivity.gameServicesImpl.getMinIndexFromFirstToBeDiscardedGameCardsDummyPlayerDuringTurn();
                                        int numeroCartaADescartarExtra = InitialMenuActivity.gameServicesImpl.getCardNumberDummyPlayerTurnFromIndex(numeroIndiceCartaADescartarExtra);

                                        //Método para descartar ('DESCARTADA' = 1) la carta del mazo del Jugador Virtual a partir de su numero de carta
                                        InitialMenuActivity.gameServicesImpl.modifyTableGameDummyPlayerCardAvailabilityByCardNumber(numeroCartaADescartarExtra, true);

                                        ++numeroCartasDescartadasExtras;
                                    }
                                }

                                //Insertar la información del Turno sobre el número de cartas extras descartadas
                                String informacionPartida = InitialMenuActivity.gameServicesImpl.getGameInformationTurnSecondExtraDiscardedCards(numeroCartasDescartadasExtras);
                                InitialMenuActivity.gameServicesImpl.insertTableGameRoundInformation(informacionPartida);
                            }

                            showGameDataOnScreen();
                        }
                    }
                }
            }
        });

        //Finalizar la Ronda
        buttonFinalizarRonda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!InitialMenuActivity.gameServicesImpl.isGameStatusFinished()){ // PARTIDA_ESTADO = EN_PREPARACION || PARTIDA_ESTADO = INICIADA
                    if(InitialMenuActivity.gameServicesImpl.isLastRound()){ // RONDA = RONDA_6_NOCHE -> Cambiaremos el estado de la partida a PARTIDA_ESTADO = FINALIZADA
                        InitialMenuActivity.gameServicesImpl.modifyTableGameStatus(TipoEstado.FINALIZADA.toString());

                        //Insertar la información sobre la finalización de la partida
                        String informacionPartida = InitialMenuActivity.gameServicesImpl.getGameInformationGameFinished();
                        InitialMenuActivity.gameServicesImpl.insertTableGameRoundInformation(informacionPartida);
                    } else {
                        showHechizosDialog();
                        showAccionesAvanzadasDialog();
                        showGameDataOnScreen();
                    }
                }
            }
        });

        //Mostrar las Tácticas disponibles
        buttonMostrarTacticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!InitialMenuActivity.gameServicesImpl.isGameStatusFinished()){
                    showListadoTacticasDialog();
                    showGameDataOnScreen();
                }
            }
        });

        //Mostrar las Fichas de Habilidad del Jugador Virtual disponibles para el nivel del Jugador y aumento de nivel de Experiencia (solo en SOLITARIO)
        buttonSubirNivel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!InitialMenuActivity.gameServicesImpl.isGameStatusFinished()){
                    showSubidaNivelDialog();
                    showGameDataOnScreen();
                }
            }
        });


    }

    //Métodos privados
    private void checkGameType(){
        //Si la partida es en SOLITARIO, dejaremos activo el botón para 'Subir el nivel par' (2,4,6,8,10)
        if (InitialMenuActivity.gameServicesImpl.isGameTypeSolitaire()) {
            buttonSubirNivel.setEnabled(true);
        } else {
            buttonSubirNivel.setEnabled(false);
        }
    }

    private void checkGameFinished(){
        if(InitialMenuActivity.gameServicesImpl.isGameStatusFinished()){
            buttonContinuarTurno.setEnabled(false);
            buttonFinalizarRonda.setEnabled(false);
            buttonMostrarTacticas.setEnabled(false);
            buttonSubirNivel.setEnabled(false);
        }
    }

    private void showGameDataOnScreen(){
        textViewNombreJugadorVirtual.setText(InitialMenuActivity.gameServicesImpl.getGameHeroeNameDummyPlayer());
        textViewCristalesInicialesJugadorVirtual.setText(InitialMenuActivity.gameServicesImpl.getGameHeroeInitialCristalsDummyPlayer());
        textViewNumeroTotalCartasJugadorVirtual.setText(Integer.toString(InitialMenuActivity.gameServicesImpl.getGameTotalAvailableCardsDummyPlayer()));
        textViewTipoPartida.setText(InitialMenuActivity.gameServicesImpl.getGameType());
        textViewRondaPartida.setText(InitialMenuActivity.gameServicesImpl.getGameRoundWithoutUnderscores());

        textViewInformacionPartida.setText(InitialMenuActivity.gameServicesImpl.getGameRoundInformation());
        textViewInformacionPartida.setMovementMethod(new ScrollingMovementMethod());

        if(InitialMenuActivity.gameServicesImpl.isDummyPlayerWithExtraCristals()){ //Si el Jugador Virtual tiene cristales extras
            textViewCristalesExtrasJugadorVirtual.setText(InitialMenuActivity.gameServicesImpl.getGameHeroeAddedCristalsDummyPlayer());
        } else {
            textViewCristalesExtrasJugadorVirtual.setText("");
        }
        //TextView textView = new TextView(this);
        //textView.setText("Dummy");
        //scrollView.addView(textViewInformacionPartida.setText(InitialMenuActivity.gameServicesImpl.getGameRoundInformation()));
    }

    private void startRound(){
        if(!InitialMenuActivity.gameServicesImpl.isGameStatusFinished()){ // PARTIDA_ESTADO = EN_PREPARACION || PARTIDA_ESTADO = INICIADA
            if(InitialMenuActivity.gameServicesImpl.isRoundBeginning()){ // RONDA_ESTADO_INICIO = 1
                if(!InitialMenuActivity.gameServicesImpl.isLastTwoRounds()){ //Rondas 1, 2, 3, 4
                    showTacticasDialog();
                } else{ //Rondas 5, 6
                    if(!InitialMenuActivity.gameServicesImpl.isGameTypeSolitaire()){ // COOPERATIVO
                        //Método para actualizar la carta Táctica seleccionada al azar por el Jugador VIrtual a 'DESCARTADA'=1 en COOPERATIVO
                        CartaTactica cartaTacticaDescartadaPartidaCooperativo = discardGameTacticCardRandomlyBeforePlayersTacticCardSelectionInCooperative();

                        //Insertar la información de la Ronda sobre la carta Táctica escogida aleatoriamente por el Jugador Virtual en el modo COOPERATIVO
                        String informacionPartida = InitialMenuActivity.gameServicesImpl.getGameInformationTacticCardDummyPlayerCooperativeType(cartaTacticaDescartadaPartidaCooperativo);
                        InitialMenuActivity.gameServicesImpl.insertTableGameRoundInformation(informacionPartida);
                    }

                    //Barajar cartas del Jugador Virtual y actualizar todas las cartas a NO descartadas
                    shuffleDummyPlayerCardsAndUpdateToAvailable();

                    //Crear método para convertir RONDA_ESTADO_INICIO = 0
                    InitialMenuActivity.gameServicesImpl.modifyTableGameStatusRoundBeginning(false);
                }
            }
        }
    }

    private void showTacticasDialog(){
        tacticasDialogFragment.show(getSupportFragmentManager(), "Tácticas");
    }

    private void showListadoTacticasDialog(){
        tacticasListadoDialogFragment.show(getSupportFragmentManager(), "Listado Tácticas");
    }

    private void showSubidaNivelDialog(){
        subidaNivelDialogFragment.show(getSupportFragmentManager(), "Subida de Nivel");
    }

    private void showAccionesAvanzadasDialog(){
        accionesAvanzadasDialogFragment.show(getSupportFragmentManager(), "Acciones Avanzadas");
    }

    private void showHechizosDialog(){
        hechizosDialogFragment.show(getSupportFragmentManager(), "Hechizos");
    }

    private void shuffleDummyPlayerCardsAndUpdateToAvailable(){
        List<Integer> cartasJugadorVirtualPorNumero = InitialMenuActivity.gameServicesImpl.getGameCardsDummyPlayerByNumber();
        InitialMenuActivity.gameServicesImpl.getShuffledGameCardsDummyPlayerByNumber(cartasJugadorVirtualPorNumero);

        InitialMenuActivity.gameServicesImpl.modifyTableGameShuffledCardsDummyPlayer(cartasJugadorVirtualPorNumero, false);
    }

    private CartaTactica discardGameTacticCardRandomlyBeforePlayersTacticCardSelectionInCooperative(){
        if(!InitialMenuActivity.gameServicesImpl.isGameTypeSolitaire()){ //COOPERATIVO
            if(InitialMenuActivity.gameServicesImpl.isDayRound()){  //Ronda de DÍA
                List<CartaTactica> cartasTacticasDisponiblesJuego = InitialMenuActivity.gameServicesImpl.getGameAvailableDayTacticsCards();
                CartaTactica cartaTacticaAleatoriaDisponible = InitialMenuActivity.gameServicesImpl.getGameAvailableRandomTacticCard(cartasTacticasDisponiblesJuego);

                //Método para actualizar la carta Táctica seleccionada al azar por el Jugador VIrtual a 'DESCARTADA'=1
                InitialMenuActivity.gameServicesImpl.modifyTableGameTacticCardAvailabilityByName(cartaTacticaAleatoriaDisponible.getNombre(), true);

                return cartaTacticaAleatoriaDisponible;
            } else{ //Ronda de NOCHE
                List<CartaTactica> cartasTacticasDisponiblesJuego = InitialMenuActivity.gameServicesImpl.getGameAvailableNightTacticsCards();
                CartaTactica cartaTacticaAleatoriaDisponible = InitialMenuActivity.gameServicesImpl.getGameAvailableRandomTacticCard(cartasTacticasDisponiblesJuego);

                //Método para actualizar la carta Táctica seleccionada al azar por el Jugador VIrtual a 'DESCARTADA'=1
                InitialMenuActivity.gameServicesImpl.modifyTableGameTacticCardAvailabilityByName(cartaTacticaAleatoriaDisponible.getNombre(), true);

                return cartaTacticaAleatoriaDisponible;
            }
        }

        return null;
    }



}

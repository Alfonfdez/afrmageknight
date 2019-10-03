package com.afr.afrmageknight.activities;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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

import java.util.List;

public class GameActivity extends AppCompatActivity {

    private Button buttonContinuarTurno;
    private Button buttonFinalizarRonda;
    private Button buttonMostrarTacticas;
    private Button buttonSubirNivel;

    private TextView textViewNombreJugadorVirtual;
    private TextView textViewCristalesJugadorVirtual;
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
        textViewCristalesJugadorVirtual = (TextView) findViewById(R.id.idTextViewCristalesJV);
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

                        if(InitialMenuActivity.gameServicesImpl.isDummyPlayerCardsFinished()){ // Si el mazo de cartas del Jugador Virtual está vacío (0), cambiaremos a 'RONDA_ESTADO_FINALIZADO' = 1
                            // Cambiaremos a 'RONDA_ESTADO_FINALIZADO' = 1 de la tabla 'PARTIDO_DATOS'
                            InitialMenuActivity.gameServicesImpl.modifyTableGameStatusRoundEnding(true);

                            //Insertar la información de la Ronda finalizada
                            String informacionPartida = InitialMenuActivity.gameServicesImpl.getGameInformationGameFinished();
                            InitialMenuActivity.gameServicesImpl.insertTableGameRoundInformation(informacionPartida);
                        } else {

                            //todo











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
        textViewCristalesJugadorVirtual.setText(InitialMenuActivity.gameServicesImpl.getGameHeroeCristalsDummyPlayer());
        textViewNumeroTotalCartasJugadorVirtual.setText(Integer.toString(InitialMenuActivity.gameServicesImpl.getGameTotalAvailableCardsDummyPlayer()));
        textViewTipoPartida.setText(InitialMenuActivity.gameServicesImpl.getGameType());
        textViewRondaPartida.setText(InitialMenuActivity.gameServicesImpl.getGameRound());
        textViewInformacionPartida.setText(InitialMenuActivity.gameServicesImpl.getGameRoundInformation());
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

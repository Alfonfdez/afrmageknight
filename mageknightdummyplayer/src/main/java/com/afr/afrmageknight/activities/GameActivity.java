package com.afr.afrmageknight.activities;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.afr.afrmageknight.R;
import com.afr.afrmageknight.fragments.TacticasDialogFragment;
import com.afr.afrmageknight.model.CartaTactica;
import com.afr.afrmageknight.model.TipoRonda;

import java.util.ArrayList;
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

        tacticasDialogFragment = new TacticasDialogFragment();

        //Métodos de inicio en "onCreate"
        checkGameType();
        showGameDataOnScreen();
        startRound();



        //Mientras el juego no haya finalizado ('FINALIZADA') continuaremos en el bucle
        /*while(!InitialMenuActivity.gameServicesImpl.isGameStatusFinished()){
            Log.d("DATABASE","Mientras el juego no haya finalizado ('FINALIZADA') continuaremos en el bucle");

            //La información de la nueva Ronda se actualizará al final de la Ronda anterior
            textViewRondaPartida.setText(InitialMenuActivity.gameServicesImpl.getGameRound());

            //Los cristales del Jugador Virtual se actualizarán al final de la Ronda anterior
            textViewCristalesJugadorVirtual.setText(InitialMenuActivity.gameServicesImpl.getGameHeroeCristalsDummyPlayer());

            //Mientras la Ronda no haya finalizado (NO sea "true") continuaremos en el bucle
            while(!InitialMenuActivity.gameServicesImpl.isRoundEnding()){
                Log.d("DATABASE","Mientras la Ronda no haya finalizado (NO sea \"true\") continuaremos en el bucle");

                //El número de cartas disponibles por el Jugador Virtual se irá actualizando a medida que vayan pasando los turnos dentro de la Ronda
                textViewNumeroTotalCartasJugadorVirtual.setText(Integer.toString(InitialMenuActivity.gameServicesImpl.getGameTotalCardsDummyPlayer()));

                //La información general de las acciones que ocurran durante la Ronda se irá actualizando a medida que vayan pasando los turnos dentro de la Ronda
                textViewInformacionPartida.setText(InitialMenuActivity.gameServicesImpl.getGameRoundInformation());

                //Mientras la Ronda esté en la fase de Inicio (NO sea "false") continuaremos en el bucle
                while(InitialMenuActivity.gameServicesImpl.isRoundBeginning()){
                    Log.d("DATABASE","Mientras la Ronda esté en la fase de Inicio (NO sea \"false\") continuaremos en el bucle");

                    //Si NO estamos en las 2 últimas Rondas ('RONDA_5_DIA' / 'RONDA_6_NOCHE'), mostraremos un cuadro para seleccionar Tácticas
                    if(!InitialMenuActivity.gameServicesImpl.isLastTwoRounds()){
                        Log.d("DATABASE","Si NO estamos en las 2 últimas Rondas ('RONDA_5_DIA' / 'RONDA_6_NOCHE'), mostraremos un cuadro para seleccionar Tácticas");

                        if(InitialMenuActivity.gameServicesImpl.isGameTypeSolitaire()){ // TÁCTICAS - SOLITARIO
                            Log.d("DATABASE","Hemos llegado hasta mostrar cuadro de TACTICAS en modo SOLITARIO");
                        } else { // TÁCTICAS - COOPERATIVO
                            Log.d("DATABASE","Hemos llegado hasta mostrar cuadro de TACTICAS en modo COOPERATIVO");
                        }
                    }

                    //Si NO estamos en la 1a Ronda ('RONDA_1_DIA'), barajaremos las cartas del Jugador Virtual
                    if(!InitialMenuActivity.gameServicesImpl.isFirstRound()){

                    }
                }
            }
        }*/





        buttonContinuarTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GameActivity.this, "Soy el botón CONTINUAR TURNO", Toast.LENGTH_LONG).show();
            }
        });

        buttonFinalizarRonda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GameActivity.this, "Soy el botón FINALIZAR RONDA", Toast.LENGTH_LONG).show();
            }
        });

        buttonMostrarTacticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GameActivity.this, "Soy el botón MOSTRAR TACTICAS", Toast.LENGTH_LONG).show();
            }
        });

        buttonSubirNivel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GameActivity.this, "Soy el botón SUBIR NIVEL PAR en SOLITARIO", Toast.LENGTH_LONG).show();
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

    private void showGameDataOnScreen(){
        textViewNombreJugadorVirtual.setText(InitialMenuActivity.gameServicesImpl.getGameHeroeNameDummyPlayer());
        textViewCristalesJugadorVirtual.setText(InitialMenuActivity.gameServicesImpl.getGameHeroeCristalsDummyPlayer());
        textViewNumeroTotalCartasJugadorVirtual.setText(Integer.toString(InitialMenuActivity.gameServicesImpl.getGameTotalCardsDummyPlayer()));
        textViewTipoPartida.setText(InitialMenuActivity.gameServicesImpl.getGameType());
        textViewRondaPartida.setText(InitialMenuActivity.gameServicesImpl.getGameRound());
        textViewInformacionPartida.setText(InitialMenuActivity.gameServicesImpl.getGameRoundInformation());
    }

    private void checkGameFinished(){
        if(InitialMenuActivity.gameServicesImpl.isGameStatusFinished()){
            buttonContinuarTurno.setEnabled(false);
            buttonFinalizarRonda.setEnabled(false);
            buttonMostrarTacticas.setEnabled(false);
            buttonSubirNivel.setEnabled(false);
        }
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

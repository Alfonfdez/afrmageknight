package com.afr.afrmageknight.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.afr.afrmageknight.R;

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


        if(InitialMenuActivity.gameServicesImpl.isGameTypeSolitaire()){
            buttonSubirNivel.setEnabled(true);
        } else{
            buttonSubirNivel.setEnabled(false);
        }

        textViewNombreJugadorVirtual.setText(InitialMenuActivity.gameServicesImpl.getHeroeNameDummyPlayer());
        textViewCristalesJugadorVirtual.setText(InitialMenuActivity.gameServicesImpl.getHeroeCristalsDummyPlayer());
        textViewNumeroTotalCartasJugadorVirtual.setText(Integer.toString(InitialMenuActivity.gameServicesImpl.getTotalCardsDummyPlayer()));
        textViewTipoPartida.setText(InitialMenuActivity.gameServicesImpl.getGameType());
        textViewRondaPartida.setText(InitialMenuActivity.gameServicesImpl.getGameRound());








    }


}

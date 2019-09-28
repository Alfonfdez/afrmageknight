package com.afr.afrmageknight.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.afr.afrmageknight.R;

public class GameActivity extends AppCompatActivity {

    private Button buttonContinuarTurno;
    private Button buttonFinalizarRonda;
    private Button buttonMostrarTacticas;
    private Button buttonSubirNivel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        buttonContinuarTurno = (Button) findViewById(R.id.idButtonContinuarTurno);
        buttonFinalizarRonda = (Button) findViewById(R.id.idButtonFinalizarRonda);
        buttonMostrarTacticas = (Button) findViewById(R.id.idButtonTacticasDisponibles);
        buttonSubirNivel = (Button) findViewById(R.id.idButtonSubidaNivel);








    }




}

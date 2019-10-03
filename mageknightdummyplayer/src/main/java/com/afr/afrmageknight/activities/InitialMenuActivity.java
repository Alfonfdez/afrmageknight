package com.afr.afrmageknight.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.afr.afrmageknight.R;
import com.afr.afrmageknight.databaseHelper.SQLiteDatabaseHelper;
import com.afr.afrmageknight.services.GameServicesImpl;

public class InitialMenuActivity extends AppCompatActivity {

    // I - Declarar las variables
    public static SQLiteDatabaseHelper myDB;
    public static GameServicesImpl gameServicesImpl;

    private Button buttonResumeGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initialmenu);

        myDB = new SQLiteDatabaseHelper(this);
        gameServicesImpl = new GameServicesImpl(this);

        buttonResumeGame = (Button) findViewById(R.id.idButtonContinuarPartida);

        // Comprobar si hay una partida ya iniciada y habilitar el botón si es así
        checkGameResume();
    }

    //Métodos públicos
    public void newGame(View view){
        myDB.deleteGameData();
        Log.d("DATABASE","FIRST: DELETE ALL GAME DATA ON DATABASE");

        // 1) Vamos a instanciar un 'intent'
        Intent intent = new Intent(this, GameOptionsActivity.class);

        // 2) Vamos a cambiar de 'activity'
        startActivity(intent);
        finish();
    }

    public void resumeGame(View view){
        // 1) Vamos a instanciar un 'intent'
        Intent intent = new Intent(this, GameActivity.class);

        // 2) Vamos a cambiar de 'activity'
        startActivity(intent);
        finish();
    }

    //Métodos privados
    private void checkGameResume(){
        if(gameServicesImpl.isGameStatusInitiated()){
            buttonResumeGame.setEnabled(true);
        } else{
            buttonResumeGame.setEnabled(false);
        }
    }

}

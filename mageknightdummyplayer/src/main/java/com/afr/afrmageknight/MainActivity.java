package com.afr.afrmageknight;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.afr.afrmageknight.databaseHelper.SQLiteDatabaseHelper;

import com.afr.afrmageknight.servicios.GameServices;
import com.afr.afrmageknight.servicios.GameServicesImpl;

import com.afr.afrmageknight.model.CartaAccionBasica;
import com.afr.afrmageknight.model.Cristal;
import com.afr.afrmageknight.model.FichaHabilidad;
import com.afr.afrmageknight.model.Heroe;
import com.afr.afrmageknight.model.TipoPartida;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // I - Declarar las variables
    private SQLiteDatabaseHelper myDB;
    //private GameServices gameServices;

    private Switch switchModoJuego;
    private RadioGroup radioGroupHeroes;

    private Button buttonInsertGameData;
    private Button buttonDeleteGameData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new SQLiteDatabaseHelper(this);
        //gameServices = new GameServicesImpl(this);

        switchModoJuego = (Switch) findViewById(R.id.idSwitchTipoPartida);

        radioGroupHeroes = (RadioGroup) findViewById(R.id.idRadioGroup);

        buttonInsertGameData = (Button) findViewById(R.id.idButtonInsertAllGameData);
        buttonDeleteGameData = (Button) findViewById(R.id.idButtonDeleteAllGameData);

        // Botón para insertar toda la información de la partida:
        // 1) Modo de juego (Solitario/Cooperativo)
        // 2) Héroes seleccionados por el jugador/los jugadores
        // 3) Héroe aleatorio del Jugador Virtual (Dummy player)
        // 4) Barajar aleatoriamente el mazo del héroe del Jugador Virtual
        buttonInsertGameData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("DATABASE","INSERT ALL GAME DATA ON DATABASE");

                //Modo solitario
                if(!switchModoJuego.isChecked()){
                    if(radioGroupHeroes.getCheckedRadioButtonId() == -1){
                        Toast.makeText(MainActivity.this, "Se debe seleccionar 1 héroe", Toast.LENGTH_SHORT).show();
                    } else {
                        switch(radioGroupHeroes.getCheckedRadioButtonId()){
                            case R.id.idRadioButtonArythea:

                                /*Heroe heroeSelectedByPlayer = gameServices.getAHeroeSelectedByPlayer("Arythea");

                                Heroe randomHeroeDummyPlayer = gameServices.getRandomHeroeFromOneHeroeSelectedByPlayer(heroeSelectedByPlayer);

                                List<CartaAccionBasica> cartasAccionBasicasBarajadasDummyPlayer = gameServices.getShuffledBasicActionCardsHeroeFromDummyPlayer(randomHeroeDummyPlayer);

                                List<FichaHabilidad> fichaHabilidadesBarajadasDummyPlayer = gameServices.getShuffledSkillTokensHeroeFromDummyPlayer(randomHeroeDummyPlayer);

                                List<Cristal> cristalesDummyPlayer = gameServices.getCristalesFromAHeroe(randomHeroeDummyPlayer.getNombre());

                                myDB.insertAllGameData(TipoPartida.SOLITARIO, heroeSelectedByPlayer, randomHeroeDummyPlayer, cartasAccionBasicasBarajadasDummyPlayer, fichaHabilidadesBarajadasDummyPlayer, cristalesDummyPlayer);
                                */
                                break;

                            case R.id.idRadioButtonTovak:

                                break;

                            case R.id.idRadioButtonNorowas:

                                break;

                            case R.id.idRadioButtonGoldyx:

                                break;

                            case R.id.idRadioButtonWolfhawk:

                                break;

                            case R.id.idRadioButtonKrang:

                                break;

                            case R.id.idRadioButtonBraevalar:

                                break;
                        }
                    }

                } else{ //Modo cooperativo
                    if(radioGroupHeroes.getCheckedRadioButtonId() == -1){
                        Toast.makeText(MainActivity.this, "Se debe seleccionar 2 o 3 héroes", Toast.LENGTH_SHORT).show();
                    } else {

                    }
                }


                //myGameDB.insertAllData();
            }
        });

        buttonDeleteGameData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("DATABASE","DELETE ALL GAME DATA ON DATABASE");
                myDB.deleteGameData();
            }
        });

    }
}

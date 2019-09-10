package com.afr.afrmageknight;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.afr.afrmageknight.databaseHelper.DatabaseHelper;
import com.afr.afrmageknight.databaseHelper.DatabaseHelperInsertGameData;
import com.afr.afrmageknight.databaseHelper.DatabaseHelperInsertInitialData;
import com.afr.afrmageknight.model.CartaAccionBasica;
import com.afr.afrmageknight.model.FichaHabilidad;
import com.afr.afrmageknight.model.Heroe;
import com.afr.afrmageknight.model.TipoPartida;
import com.afr.afrmageknight.servicios.GameServices;
import com.afr.afrmageknight.servicios.GameServicesImpl;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // I - Declarar las variables
    //private DatabaseHelper myInitialDB;
    //private DatabaseHelper myGameDB;
    private DatabaseHelperInsertInitialData myInitialDB;
    private DatabaseHelperInsertGameData myGameDB;

    private GameServices gameServices;

    private Switch switchModoJuego;

    private RadioGroup radioGroupHeroes;
    private RadioButton radioButtonArythea;
    private RadioButton radioButtonTovak;
    private RadioButton radioButtonNorowas;
    private RadioButton radioButtonGoldyx;
    private RadioButton radioButtonWolfhawk;
    private RadioButton radioButtonKrang;
    private RadioButton radioButtonBraevalar;

    private Button buttonInsertAllDataOne;
    private Button buttonInsertAllDataTwo;
    private Button buttonInsertGameData;
    private Button buttonDeleteGameData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myInitialDB = new DatabaseHelperInsertInitialData(this);
        myGameDB = new DatabaseHelperInsertGameData(this);

        gameServices = new GameServicesImpl(this);

        switchModoJuego = (Switch) findViewById(R.id.idSwitchTipoPartida);

        radioGroupHeroes = (RadioGroup) findViewById(R.id.idRadioGroup);
        radioButtonArythea = (RadioButton) findViewById(R.id.idRadioButtonArythea);
        radioButtonTovak = (RadioButton) findViewById(R.id.idRadioButtonTovak);
        radioButtonNorowas = (RadioButton) findViewById(R.id.idRadioButtonNorowas);
        radioButtonGoldyx = (RadioButton) findViewById(R.id.idRadioButtonGoldyx);
        radioButtonWolfhawk = (RadioButton) findViewById(R.id.idRadioButtonWolfhawk);
        radioButtonKrang = (RadioButton) findViewById(R.id.idRadioButtonKrang);
        radioButtonBraevalar = (RadioButton) findViewById(R.id.idRadioButtonBraevalar);

        buttonInsertAllDataOne = (Button) findViewById(R.id.idButtonInsertAllDataOne);
        buttonInsertAllDataTwo = (Button) findViewById(R.id.idButtonInsertAllDataTwo);
        buttonInsertGameData = (Button) findViewById(R.id.idButtonInsertAllGameData);
        buttonDeleteGameData = (Button) findViewById(R.id.idButtonDeleteAllGameData);

        // Botón para insertar toda la información en la base de datos
        buttonInsertAllDataOne.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("DATABASE","INSERT ALL DATA ON DATABASE PART ONE");
                myInitialDB.insertAllDataPartOne();
            }
        });

        // Botón para insertar toda la información en la base de datos
        buttonInsertAllDataTwo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("DATABASE","INSERT ALL DATA ON DATABASE PART TWO");
                myInitialDB.insertAllDataPartTwo();
            }
        });

        // Botón para insertar toda la información de la partida:
        // 1) Modo de juego (Solitario/Cooperativo) - 2) Héroes seleccionados por el jugador/los jugadores
        // 3) Héroe aleatorio del Jugador Virtual (Dummy player) - 4) Barajar aleatoriamente el mazo del héroe del Jugador Virtual
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

                                Heroe heroeSelectedByPlayer = gameServices.getAHeroeSelectedByPlayer("Arythea");
                                //myGameDB.insertDataHeroeSelectedByPlayer(heroeSelectedByPlayer);

                                Heroe randomHeroeDummyPlayer = gameServices.getRandomHeroeFromOneHeroeSelectedByPlayer(heroeSelectedByPlayer);
                                //myGameDB.insertDataHeroeSelectedByDummyPlayer(randomHeroeDummyPlayer);

                                List<CartaAccionBasica> cartasAccionBasicasBarajadasDummyPlayer = gameServices.getShuffledBasicActionCardsHeroeFromDummyPlayer(randomHeroeDummyPlayer);
                                //myGameDB.createAllBasicCardsFromDummyPlayer(cartasAccionBasicasBarajadasDummyPlayer);

                                List<FichaHabilidad> fichaHabilidadesBarajadasDummyPlayer = gameServices.getShuffledSkillTokensHeroeFromDummyPlayer(randomHeroeDummyPlayer);
                                //myGameDB.createAllSkillTokensFromDummyPlayer(fichaHabilidadesBarajadasDummyPlayer);

                                myGameDB.insertAllGameData(TipoPartida.SOLITARIO, heroeSelectedByPlayer, randomHeroeDummyPlayer, cartasAccionBasicasBarajadasDummyPlayer, fichaHabilidadesBarajadasDummyPlayer);

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

                //myGameDB.deleteAllData();
            }
        });

    }
}

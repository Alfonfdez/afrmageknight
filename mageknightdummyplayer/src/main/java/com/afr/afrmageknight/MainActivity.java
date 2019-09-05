package com.afr.afrmageknight;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.afr.afrmageknight.databaseHelper.DatabaseHelper;
import com.afr.afrmageknight.databaseHelper.DatabaseHelperInsertInitialData;
import com.afr.afrmageknight.model.Cristal;
import com.afr.afrmageknight.model.Heroe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // I - Declarar las variables
    //private DatabaseHelperInsertInitialData myDB;
    private DatabaseHelper myDB;
    private DatabaseHelper myGameDB;

    private DatabaseHelperInsertInitialData myInitialDB;

    private Switch switchModoJuego;

    private RadioGroup radioGroupHeroes;
    private RadioButton radioButtonArythea;
    private RadioButton radioButtonTovak;
    private RadioButton radioButtonNorowas;
    private RadioButton radioButtonGoldyx;
    private RadioButton radioButtonWolfhawk;
    private RadioButton radioButtonKrang;
    private RadioButton radioButtonBraevalar;

    private Button buttonInsertAllData;
    private Button buttonInsertGameData;
    private Button buttonDeleteGameData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DatabaseHelperInsertInitialData(this);

        myInitialDB = new DatabaseHelperInsertInitialData(this);

        switchModoJuego = (Switch) findViewById(R.id.idSwitchTipoPartida);

        radioGroupHeroes = (RadioGroup) findViewById(R.id.idRadioGroup);
        radioButtonArythea = (RadioButton) findViewById(R.id.idRadioButtonArythea);
        radioButtonTovak = (RadioButton) findViewById(R.id.idRadioButtonTovak);
        radioButtonNorowas = (RadioButton) findViewById(R.id.idRadioButtonNorowas);
        radioButtonGoldyx = (RadioButton) findViewById(R.id.idRadioButtonGoldyx);
        radioButtonWolfhawk = (RadioButton) findViewById(R.id.idRadioButtonWolfhawk);
        radioButtonKrang = (RadioButton) findViewById(R.id.idRadioButtonKrang);
        radioButtonBraevalar = (RadioButton) findViewById(R.id.idRadioButtonBraevalar);

        buttonInsertAllData = (Button) findViewById(R.id.idButtonInsertAllData);
        buttonInsertGameData = (Button) findViewById(R.id.idButtonInsertAllGameData);
        buttonDeleteGameData = (Button) findViewById(R.id.idButtonDeleteAllGameData);

        // Botón para insertar toda la información en la base de datos
        buttonInsertAllData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.d("DATABASE","INSERT ALL DATA ON DATABASE");

                myDB.insertAllData();
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
                        List<Heroe> heroes = new ArrayList<Heroe>();

                        List<Cristal> cristales = new ArrayList<Cristal>();

                        Cursor heroesCursor = myInitialDB.getAllHeroes();

                        if (heroesCursor.moveToFirst()){
                            do{
                                String data = heroesCursor.getString(heroesCursor.getColumnIndex("NOMBRE"));

                                Heroe heroe = new Heroe(data,);


                            }while(heroesCursor.moveToNext());
                        }

                        heroesCursor.close();


                        switch(radioGroupHeroes.getCheckedRadioButtonId()){



                            case R.id.idRadioButtonArythea:




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

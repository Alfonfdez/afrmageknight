package com.afr.afrmageknight.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.afr.afrmageknight.R;
import com.afr.afrmageknight.model.CartaAccionBasica;
import com.afr.afrmageknight.model.CartaTactica;
import com.afr.afrmageknight.model.Cristal;
import com.afr.afrmageknight.model.FichaHabilidad;
import com.afr.afrmageknight.model.Heroe;
import com.afr.afrmageknight.model.TipoEstado;
import com.afr.afrmageknight.model.TipoPartida;
import com.afr.afrmageknight.model.TipoRonda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameOptionsActivity extends AppCompatActivity {

    // I - Declarar las variables
    private TextView arythea;
    private TextView tovak;
    private TextView norowas;
    private TextView goldyx;
    private TextView wolfhawk;
    private TextView krang;
    private TextView braevalar;

    private Switch switchTipoPartida;
    private Button buttonInsertGameData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameoptions);

        arythea = (TextView) findViewById(R.id.idTVArythea);
        tovak = (TextView) findViewById(R.id.idTVTovak);
        norowas = (TextView) findViewById(R.id.idTVNorowas);
        goldyx = (TextView) findViewById(R.id.idTVGoldyx);
        wolfhawk = (TextView) findViewById(R.id.idTVWolfhawk);
        krang = (TextView) findViewById(R.id.idTVKrang);
        braevalar = (TextView) findViewById(R.id.idTVBraevalar);

        switchTipoPartida = (Switch) findViewById(R.id.idSwitchTipoPartida);
        buttonInsertGameData = (Button) findViewById(R.id.idButtonInsertAllGameData);

        final List<TextView> heroesTextViews = new ArrayList<TextView>();
        Collections.addAll(heroesTextViews, arythea, tovak, norowas, goldyx, wolfhawk, krang, braevalar);

        // 1) Vamos a instanciar un 'intent'
        final Intent intent = new Intent(this, GameActivity.class);


        // Botón para insertar toda la información de la partida:
        // 1) Tipo de Partida (Solitario/Cooperativo)
        // 2) Héroes seleccionados por el jugador/los jugadores
        // 3) Héroe aleatorio del Jugador Virtual (Dummy player)
        // 4) Barajar aleatoriamente el mazo del héroe del Jugador Virtual
        buttonInsertGameData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int heroesSeleccionados = 0;

                if(!switchTipoPartida.isChecked()){ //MODO SOLITARIO
                    for(TextView textView : heroesTextViews){
                        if(textView.isActivated()){
                            ++heroesSeleccionados;
                        }
                    }

                    if(heroesSeleccionados==1){

                        Log.d("DATABASE","SECOND: INSERT ALL GAME DATA ON DATABASE - SOLITAIRE MODE");

                        for(TextView textView : heroesTextViews){
                            if(textView.isActivated()){
                                String heroeName = textView.getText().toString();

                                Toast.makeText(GameOptionsActivity.this, "¡TODO OK! Partida en SOLITARIO y 1 único héroe ("+heroeName+") seleccionado", Toast.LENGTH_LONG).show();

                                List<CartaTactica> cartasTacticas = InitialMenuActivity.gameServicesImpl.getTacticCards();

                                Heroe heroeSelectedByPlayer = InitialMenuActivity.gameServicesImpl.getAHeroeSelectedByPlayer(heroeName);

                                Heroe randomHeroeDummyPlayer = InitialMenuActivity.gameServicesImpl.getRandomHeroeFromOneHeroeSelectedByPlayer(heroeSelectedByPlayer);

                                List<CartaAccionBasica> cartasAccionBasicasBarajadasDummyPlayer = InitialMenuActivity.gameServicesImpl.getShuffledBasicActionCardsHeroeFromDummyPlayer(randomHeroeDummyPlayer);

                                List<FichaHabilidad> fichaHabilidadesBarajadasDummyPlayer = InitialMenuActivity.gameServicesImpl.getShuffledSkillTokensHeroeFromDummyPlayer(randomHeroeDummyPlayer);

                                List<Cristal> cristalesDummyPlayer = InitialMenuActivity.gameServicesImpl.getCristalesFromAHeroe(randomHeroeDummyPlayer.getNombre());

                                InitialMenuActivity.myDB.insertAllGameDataSolitaire(TipoEstado.INICIADA, TipoRonda.RONDA_1_DIA, TipoPartida.SOLITARIO, cartasTacticas, heroeSelectedByPlayer, randomHeroeDummyPlayer, cartasAccionBasicasBarajadasDummyPlayer, fichaHabilidadesBarajadasDummyPlayer, cristalesDummyPlayer);

                                // 2) Vamos a cambiar de 'activity'
                                startActivity(intent);
                                finish();
                            }
                        }
                    } else {
                        Toast.makeText(GameOptionsActivity.this, "Debes seleccionar 1 único héroe para una partida en SOLITARIO", Toast.LENGTH_LONG).show();
                    }

                } else { // MODO COOPERATIVO
                    for(TextView textView : heroesTextViews){
                        if(textView.isActivated()){
                            ++heroesSeleccionados;
                        }
                    }

                    if(heroesSeleccionados==2 || heroesSeleccionados==3){

                        Log.d("DATABASE","SECOND: INSERT ALL GAME DATA ON DATABASE - COOPERATIVE MODE");

                        List<String> heroeNames = new ArrayList<String>();

                        for(TextView textView : heroesTextViews){
                            if(textView.isActivated()){
                                String heroeName = textView.getText().toString();
                                heroeNames.add(heroeName);
                            }
                        }

                        List<CartaTactica> cartasTacticas = InitialMenuActivity.gameServicesImpl.getTacticCards();

                        List<Heroe> heroesSelectedByPlayer = InitialMenuActivity.gameServicesImpl.getHeroesSelectedByPlayer(heroeNames);

                        Heroe randomHeroeDummyPlayer = InitialMenuActivity.gameServicesImpl.getRandomHeroeFromHeroesSelectedByPlayer(heroesSelectedByPlayer);

                        List<CartaAccionBasica> cartasAccionBasicasBarajadasDummyPlayer = InitialMenuActivity.gameServicesImpl.getShuffledBasicActionCardsHeroeFromDummyPlayer(randomHeroeDummyPlayer);

                        List<Cristal> cristalesDummyPlayer = InitialMenuActivity.gameServicesImpl.getCristalesFromAHeroe(randomHeroeDummyPlayer.getNombre());

                        InitialMenuActivity.myDB.insertAllGameDataCooperative(TipoEstado.INICIADA, TipoRonda.RONDA_1_DIA, TipoPartida.COOPERATIVO, cartasTacticas, heroesSelectedByPlayer, randomHeroeDummyPlayer, cartasAccionBasicasBarajadasDummyPlayer, cristalesDummyPlayer);

                        // 2) Vamos a cambiar de 'activity'
                        startActivity(intent);
                    } else {
                        Toast.makeText(GameOptionsActivity.this, "Debes seleccionar 2 o 3 héroes para una partida en COOPERATIVO", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        arythea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!arythea.isActivated()){
                    arythea.setActivated(true);
                    arythea.setTextColor(Color.RED);
                } else{
                    arythea.setActivated(false);
                    arythea.setTextColor(Color.BLACK);
                }
            }
        });

        tovak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!tovak.isActivated()){
                    tovak.setActivated(true);
                    tovak.setTextColor(Color.RED);
                } else{
                    tovak.setActivated(false);
                    tovak.setTextColor(Color.BLACK);
                }
            }
        });

        norowas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!norowas.isActivated()){
                    norowas.setActivated(true);
                    norowas.setTextColor(Color.RED);
                } else{
                    norowas.setActivated(false);
                    norowas.setTextColor(Color.BLACK);
                }
            }
        });

        goldyx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!goldyx.isActivated()){
                    goldyx.setActivated(true);
                    goldyx.setTextColor(Color.RED);
                } else{
                    goldyx.setActivated(false);
                    goldyx.setTextColor(Color.BLACK);
                }
            }
        });

        wolfhawk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!wolfhawk.isActivated()){
                    wolfhawk.setActivated(true);
                    wolfhawk.setTextColor(Color.RED);
                } else{
                    wolfhawk.setActivated(false);
                    wolfhawk.setTextColor(Color.BLACK);
                }
            }
        });

        krang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!krang.isActivated()){
                    krang.setActivated(true);
                    krang.setTextColor(Color.RED);
                } else{
                    krang.setActivated(false);
                    krang.setTextColor(Color.BLACK);
                }
            }
        });

        braevalar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!braevalar.isActivated()){
                    braevalar.setActivated(true);
                    braevalar.setTextColor(Color.RED);
                } else{
                    braevalar.setActivated(false);
                    braevalar.setTextColor(Color.BLACK);
                }
            }
        });


    }
}

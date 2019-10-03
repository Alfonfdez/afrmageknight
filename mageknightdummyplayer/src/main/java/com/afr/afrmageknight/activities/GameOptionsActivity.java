package com.afr.afrmageknight.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.afr.afrmageknight.R;
import com.afr.afrmageknight.model.CartaAccionAvanzada;
import com.afr.afrmageknight.model.CartaAccionAvanzadaEspecial;
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
    private TextView textViewArythea;
    private TextView textViewTovak;
    private TextView textViewNorowas;
    private TextView textViewGoldyx;
    private TextView textViewWolfhawk;
    private TextView textViewKrang;
    private TextView textViewBraevalar;

    private ImageView imageViewArythea;
    private ImageView imageViewTovak;
    private ImageView imageViewNorowas;
    private ImageView imageViewGoldyx;
    private ImageView imageViewWolfhawk;
    private ImageView imageViewKrang;
    private ImageView imageViewBraevalar;

    private ImageView imageViewOKArythea;
    private ImageView imageViewOKTovak;
    private ImageView imageViewOKNorowas;
    private ImageView imageViewOKGoldyx;
    private ImageView imageViewOKWolfhawk;
    private ImageView imageViewOKKrang;
    private ImageView imageViewOKBraevalar;

    private Switch switchTipoPartida;
    private Button buttonInsertGameData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameoptions);

        textViewArythea = (TextView) findViewById(R.id.idTVArythea);
        textViewTovak = (TextView) findViewById(R.id.idTVTovak);
        textViewNorowas = (TextView) findViewById(R.id.idTVNorowas);
        textViewGoldyx = (TextView) findViewById(R.id.idTVGoldyx);
        textViewWolfhawk = (TextView) findViewById(R.id.idTVWolfhawk);
        textViewKrang = (TextView) findViewById(R.id.idTVKrang);
        textViewBraevalar = (TextView) findViewById(R.id.idTVBraevalar);

        imageViewArythea = (ImageView) findViewById(R.id.imageViewArythea);
        imageViewTovak = (ImageView) findViewById(R.id.imageViewTovak);
        imageViewNorowas = (ImageView) findViewById(R.id.imageViewNorowas);
        imageViewGoldyx = (ImageView) findViewById(R.id.imageViewGoldyx);
        imageViewWolfhawk = (ImageView) findViewById(R.id.imageViewWolfhawk);
        imageViewKrang = (ImageView) findViewById(R.id.imageViewKrang);
        imageViewBraevalar = (ImageView) findViewById(R.id.imageViewBraevalar);

        imageViewOKArythea = (ImageView) findViewById(R.id.imageViewOKArythea);
        imageViewOKTovak = (ImageView) findViewById(R.id.imageViewOKTovak);
        imageViewOKNorowas = (ImageView) findViewById(R.id.imageViewOKNorowas);
        imageViewOKGoldyx = (ImageView) findViewById(R.id.imageViewOKGoldyx);
        imageViewOKWolfhawk = (ImageView) findViewById(R.id.imageViewOKWolfhawk);
        imageViewOKKrang = (ImageView) findViewById(R.id.imageViewOKKrang);
        imageViewOKBraevalar = (ImageView) findViewById(R.id.imageViewOKBraevalar);

        switchTipoPartida = (Switch) findViewById(R.id.idSwitchTipoPartida);
        buttonInsertGameData = (Button) findViewById(R.id.idButtonInsertAllGameData);

        final List<TextView> heroesTextViews = new ArrayList<TextView>();
        Collections.addAll(heroesTextViews, textViewArythea, textViewTovak, textViewNorowas, textViewGoldyx, textViewWolfhawk, textViewKrang, textViewBraevalar);

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

                                insertSolitaireGameData(heroeName);

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

                        insertCooperativeGameData(heroeNames);

                        // 2) Vamos a cambiar de 'activity'
                        startActivity(intent);
                    } else {
                        Toast.makeText(GameOptionsActivity.this, "Debes seleccionar 2 o 3 héroes para una partida en COOPERATIVO", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        textViewArythea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!textViewArythea.isActivated()){
                    textViewArythea.setActivated(true);
                    textViewArythea.setTextColor(Color.argb(255,78, 154, 0));

                    imageViewArythea.setVisibility(View.VISIBLE);
                    imageViewOKArythea.setVisibility(View.VISIBLE);
                } else{
                    textViewArythea.setActivated(false);
                    textViewArythea.setTextColor(Color.BLACK);

                    imageViewArythea.setVisibility(View.INVISIBLE);
                    imageViewOKArythea.setVisibility(View.INVISIBLE);
                }
            }
        });

        textViewTovak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!textViewTovak.isActivated()){
                    textViewTovak.setActivated(true);
                    textViewTovak.setTextColor(Color.argb(255,78, 154, 0));

                    imageViewTovak.setVisibility(View.VISIBLE);
                    imageViewOKTovak.setVisibility(View.VISIBLE);
                } else{
                    textViewTovak.setActivated(false);
                    textViewTovak.setTextColor(Color.BLACK);

                    imageViewTovak.setVisibility(View.INVISIBLE);
                    imageViewOKTovak.setVisibility(View.INVISIBLE);
                }
            }
        });

        textViewNorowas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!textViewNorowas.isActivated()){
                    textViewNorowas.setActivated(true);
                    textViewNorowas.setTextColor(Color.argb(255,78, 154, 0));

                    imageViewNorowas.setVisibility(View.VISIBLE);
                    imageViewOKNorowas.setVisibility(View.VISIBLE);
                } else{
                    textViewNorowas.setActivated(false);
                    textViewNorowas.setTextColor(Color.BLACK);

                    imageViewNorowas.setVisibility(View.INVISIBLE);
                    imageViewOKNorowas.setVisibility(View.INVISIBLE);
                }
            }
        });

        textViewGoldyx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!textViewGoldyx.isActivated()){
                    textViewGoldyx.setActivated(true);
                    textViewGoldyx.setTextColor(Color.argb(255,78, 154, 0));

                    imageViewGoldyx.setVisibility(View.VISIBLE);
                    imageViewOKGoldyx.setVisibility(View.VISIBLE);
                } else{
                    textViewGoldyx.setActivated(false);
                    textViewGoldyx.setTextColor(Color.BLACK);

                    imageViewGoldyx.setVisibility(View.INVISIBLE);
                    imageViewOKGoldyx.setVisibility(View.INVISIBLE);
                }
            }
        });

        textViewWolfhawk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!textViewWolfhawk.isActivated()){
                    textViewWolfhawk.setActivated(true);
                    textViewWolfhawk.setTextColor(Color.argb(255,78, 154, 0));

                    imageViewWolfhawk.setVisibility(View.VISIBLE);
                    imageViewOKWolfhawk.setVisibility(View.VISIBLE);
                } else{
                    textViewWolfhawk.setActivated(false);
                    textViewWolfhawk.setTextColor(Color.BLACK);

                    imageViewWolfhawk.setVisibility(View.INVISIBLE);
                    imageViewOKWolfhawk.setVisibility(View.INVISIBLE);
                }
            }
        });

        textViewKrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!textViewKrang.isActivated()){
                    textViewKrang.setActivated(true);
                    textViewKrang.setTextColor(Color.argb(255,78, 154, 0));

                    imageViewKrang.setVisibility(View.VISIBLE);
                    imageViewOKKrang.setVisibility(View.VISIBLE);
                } else{
                    textViewKrang.setActivated(false);
                    textViewKrang.setTextColor(Color.BLACK);

                    imageViewKrang.setVisibility(View.INVISIBLE);
                    imageViewOKKrang.setVisibility(View.INVISIBLE);
                }
            }
        });

        textViewBraevalar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!textViewBraevalar.isActivated()){
                    textViewBraevalar.setActivated(true);
                    textViewBraevalar.setTextColor(Color.argb(255,78, 154, 0));

                    imageViewBraevalar.setVisibility(View.VISIBLE);
                    imageViewOKBraevalar.setVisibility(View.VISIBLE);
                } else{
                    textViewBraevalar.setActivated(false);
                    textViewBraevalar.setTextColor(Color.BLACK);

                    imageViewBraevalar.setVisibility(View.INVISIBLE);
                    imageViewOKBraevalar.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    //Métodos privados
    private void insertSolitaireGameData(String heroeName){

        List<CartaTactica> cartasTacticas = InitialMenuActivity.gameServicesImpl.getTacticCards();

        List<CartaAccionAvanzada> cartasAccionAvanzadas = InitialMenuActivity.gameServicesImpl.getAdvancedActionCards();

        List<CartaAccionAvanzadaEspecial> cartasAccionAvanzadaEspeciales = InitialMenuActivity.gameServicesImpl.getSpecialAdvancedActionCards();

        Heroe heroeSelectedByPlayer = InitialMenuActivity.gameServicesImpl.getAHeroeSelectedByPlayer(heroeName);

        Heroe randomHeroeDummyPlayer = InitialMenuActivity.gameServicesImpl.getRandomHeroeFromOneHeroeSelectedByPlayer(heroeSelectedByPlayer);

        List<CartaAccionBasica> cartasAccionBasicasBarajadasDummyPlayer = InitialMenuActivity.gameServicesImpl.getShuffledBasicActionCardsHeroeFromDummyPlayer(randomHeroeDummyPlayer);

        List<FichaHabilidad> fichaHabilidadesBarajadasDummyPlayer = InitialMenuActivity.gameServicesImpl.getShuffledSkillTokensHeroeFromDummyPlayer(randomHeroeDummyPlayer);

        List<Cristal> cristalesDummyPlayer = InitialMenuActivity.gameServicesImpl.getCristalesFromAHeroe(randomHeroeDummyPlayer.getNombre());

        InitialMenuActivity.myDB.insertAllGameDataSolitaire(TipoEstado.INICIADA, TipoRonda.RONDA_1_DIA, TipoPartida.SOLITARIO, cartasTacticas, cartasAccionAvanzadas, cartasAccionAvanzadaEspeciales, heroeSelectedByPlayer, randomHeroeDummyPlayer, cartasAccionBasicasBarajadasDummyPlayer, fichaHabilidadesBarajadasDummyPlayer, cristalesDummyPlayer);
    }

    private void insertCooperativeGameData(List<String> heroeNames){
        List<CartaTactica> cartasTacticas = InitialMenuActivity.gameServicesImpl.getTacticCards();

        List<CartaAccionAvanzada> cartasAccionAvanzadas = InitialMenuActivity.gameServicesImpl.getAdvancedActionCards();

        List<CartaAccionAvanzadaEspecial> cartasAccionAvanzadaEspeciales = InitialMenuActivity.gameServicesImpl.getSpecialAdvancedActionCards();

        List<Heroe> heroesSelectedByPlayer = InitialMenuActivity.gameServicesImpl.getHeroesSelectedByPlayer(heroeNames);

        Heroe randomHeroeDummyPlayer = InitialMenuActivity.gameServicesImpl.getRandomHeroeFromHeroesSelectedByPlayer(heroesSelectedByPlayer);

        List<CartaAccionBasica> cartasAccionBasicasBarajadasDummyPlayer = InitialMenuActivity.gameServicesImpl.getShuffledBasicActionCardsHeroeFromDummyPlayer(randomHeroeDummyPlayer);

        List<Cristal> cristalesDummyPlayer = InitialMenuActivity.gameServicesImpl.getCristalesFromAHeroe(randomHeroeDummyPlayer.getNombre());

        InitialMenuActivity.myDB.insertAllGameDataCooperative(TipoEstado.INICIADA, TipoRonda.RONDA_1_DIA, TipoPartida.COOPERATIVO, cartasTacticas, cartasAccionAvanzadas, cartasAccionAvanzadaEspeciales, heroesSelectedByPlayer, randomHeroeDummyPlayer, cartasAccionBasicasBarajadasDummyPlayer, cristalesDummyPlayer);
    }



}

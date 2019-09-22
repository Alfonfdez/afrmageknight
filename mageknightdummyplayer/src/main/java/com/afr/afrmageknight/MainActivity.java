package com.afr.afrmageknight;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.afr.afrmageknight.databaseHelper.SQLiteDatabaseHelper;
import com.afr.afrmageknight.model.CartaAccionBasica;
import com.afr.afrmageknight.model.Cristal;
import com.afr.afrmageknight.model.FichaHabilidad;
import com.afr.afrmageknight.model.Heroe;
import com.afr.afrmageknight.model.TipoPartida;
import com.afr.afrmageknight.servicios.GameServicesImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // I - Declarar las variables
    public static SQLiteDatabaseHelper myDB;
    private GameServicesImpl gameServicesImpl;

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
        setContentView(R.layout.activity_main);

        myDB = new SQLiteDatabaseHelper(this);
        gameServicesImpl = new GameServicesImpl(this);

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


        // Botón para insertar toda la información de la partida:
        // 1) Tipo de Partida (Solitario/Cooperativo)
        // 2) Héroes seleccionados por el jugador/los jugadores
        // 3) Héroe aleatorio del Jugador Virtual (Dummy player)
        // 4) Barajar aleatoriamente el mazo del héroe del Jugador Virtual
        buttonInsertGameData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
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

                                Log.d("DATABASE","INSERT ALL GAME DATA ON DATABASE - SOLITAIRE MODE");

                                for(TextView textView : heroesTextViews){
                                    if(textView.isActivated()){
                                        String heroeName = textView.getText().toString();

                                        Toast.makeText(MainActivity.this, "¡TODO OK! Partida en SOLITARIO y 1 único héroe ("+heroeName+") seleccionado", Toast.LENGTH_LONG).show();

                                        Heroe heroeSelectedByPlayer = gameServicesImpl.getAHeroeSelectedByPlayer(heroeName);

                                        Heroe randomHeroeDummyPlayer = gameServicesImpl.getRandomHeroeFromOneHeroeSelectedByPlayer(heroeSelectedByPlayer);

                                        List<CartaAccionBasica> cartasAccionBasicasBarajadasDummyPlayer = gameServicesImpl.getShuffledBasicActionCardsHeroeFromDummyPlayer(randomHeroeDummyPlayer);

                                        List<FichaHabilidad> fichaHabilidadesBarajadasDummyPlayer = gameServicesImpl.getShuffledSkillTokensHeroeFromDummyPlayer(randomHeroeDummyPlayer);

                                        List<Cristal> cristalesDummyPlayer = gameServicesImpl.getCristalesFromAHeroe(randomHeroeDummyPlayer.getNombre());

                                        myDB.insertAllGameData(TipoPartida.SOLITARIO, heroeSelectedByPlayer, randomHeroeDummyPlayer, cartasAccionBasicasBarajadasDummyPlayer, fichaHabilidadesBarajadasDummyPlayer, cristalesDummyPlayer);
                                    }
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "Debes seleccionar 1 único héroe para una partida en SOLITARIO", Toast.LENGTH_LONG).show();
                            }

                        } else { // MODO COOPERATIVO
                            for(TextView textView : heroesTextViews){
                                if(textView.isActivated()){
                                    ++heroesSeleccionados;
                                }
                            }

                            if(heroesSeleccionados==2 || heroesSeleccionados==3){
                                Log.d("DATABASE","INSERT ALL GAME DATA ON DATABASE - COOPERATIVE MODE");
                                Toast.makeText(MainActivity.this, "¡TODO OK! Partida en COOPERATIVO y "+heroesSeleccionados+" héroes seleccionados", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(MainActivity.this, "Debes seleccionar 2 o 3 héroes para una partida en COOPERATIVO", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
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

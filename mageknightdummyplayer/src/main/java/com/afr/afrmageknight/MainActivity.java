package com.afr.afrmageknight;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.afr.afrmageknight.databaseHelper.DatabaseHelper;
import com.afr.afrmageknight.model.Cristal;

public class MainActivity extends AppCompatActivity {

    // I - Declarar las variables
    private DatabaseHelper myDB;

    private Button buttonInsertAllData;
    private Button buttonListarCarta;
    private Button buttonListarCartaAccionBasica;
    private Button buttonListarCartaAccionAvanzada;
    private Button buttonListarCartaAccionAvanzadaEspecial;
    private Button buttonListarCartaHechizo;
    private Button buttonListarCartaTactica;
    private Button buttonListarFichaHabilidad;
    private Button buttonListarHeroes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonInsertAllData = (Button) findViewById(R.id.idButtonInsertAllData);
        buttonListarCarta = (Button) findViewById(R.id.idButtonListarCarta);
        buttonListarCartaAccionBasica = (Button) findViewById(R.id.idButtonListarCartaAccionBasica);
        buttonListarCartaAccionAvanzada = (Button) findViewById(R.id.idButtonListarCartaAccionAvanzada);
        buttonListarCartaAccionAvanzadaEspecial = (Button) findViewById(R.id.idButtonListarCartaAccionAvanzadaEspecial);
        buttonListarCartaHechizo = (Button) findViewById(R.id.idButtonListarCartaHechizo);
        buttonListarCartaTactica = (Button) findViewById(R.id.idButtonListarCartaTactica);
        buttonListarFichaHabilidad = (Button) findViewById(R.id.idButtonListarFichaHabilidad);
        buttonListarHeroes = (Button) findViewById(R.id.idButtonListarHeroes);

        myDB = new DatabaseHelper(this);

        // Botón para insertar toda la información en la base de datos
        buttonInsertAllData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.d("DATABASE","INSERT ALL DATA ON DATABASE");

                myDB.insertCards();
                myDB.insertSkillTokens();
                myDB.insertHeroes();
            }
        });

        buttonListarCarta.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.d("DATABASE","LIST CARDS ON DATABASE");

                Cursor cursor = myDB.getAllCartas();

                if(cursor == null || cursor.getCount() == 0){
                    return;
                }

                while(cursor.moveToNext()){
                    int numero = cursor.getInt(0);
                    String nombre = cursor.getString(1);

                    String cartaCompleta = numero + ": " + nombre;

                    Log.d("DATABASE", cartaCompleta);
                }
            }
        });

        buttonListarCartaAccionBasica.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.d("DATABASE","LIST DEED CARDS ON DATABASE");

                Cursor cursor = myDB.getAllCartasAccionesBasicas();

                if(cursor == null || cursor.getCount() == 0){
                    return;
                }

                while(cursor.moveToNext()){
                    int numero = cursor.getInt(0);
                    String color = cursor.getString(1);
                    String descripcionBasica = cursor.getString(2);
                    String descripcionAvanzada = cursor.getString(3);
                    String heroe = cursor.getString(4);

                    String cartaCompleta = numero + ": " + color + " " + descripcionBasica + " " + descripcionAvanzada + " " + heroe;

                    Log.d("DATABASE", cartaCompleta);
                }
            }
        });

        buttonListarCartaAccionAvanzada.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.d("DATABASE","LIST ADVANCED CARDS ON DATABASE");

                Cursor cursor = myDB.getAllCartasAccionesAvanzadas();

                if(cursor == null || cursor.getCount() == 0){
                    return;
                }

                while(cursor.moveToNext()){
                    int numero = cursor.getInt(0);
                    String color = cursor.getString(1);
                    String descripcionBasica = cursor.getString(2);
                    String descripcionAvanzada = cursor.getString(3);

                    String cartaCompleta = numero + ": " + color + " " + descripcionBasica + " " + descripcionAvanzada;

                    Log.d("DATABASE", cartaCompleta);
                }
            }
        });

        buttonListarCartaAccionAvanzadaEspecial.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.d("DATABASE","LIST SPECIAL ADVANCED CARDS ON DATABASE");

                Cursor cursor = myDB.getAllCartasAccionesAvanzadasEspeciales();

                if(cursor == null || cursor.getCount() == 0){
                    return;
                }

                while(cursor.moveToNext()){
                    int numero = cursor.getInt(0);
                    String colorPrimario = cursor.getString(1);
                    String colorSecundario = cursor.getString(2);
                    String descripcionBasica = cursor.getString(3);
                    String descripcionAvanzada = cursor.getString(4);

                    String cartaCompleta = numero + ": " + colorPrimario + " " + colorSecundario + " " + descripcionBasica + " " + descripcionAvanzada;

                    Log.d("DATABASE", cartaCompleta);
                }
            }
        });

        buttonListarCartaHechizo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.d("DATABASE","LIST SPELL CARDS ON DATABASE");

                Cursor cursor = myDB.getAllCartasHechizos();

                if(cursor == null || cursor.getCount() == 0){
                    return;
                }

                while(cursor.moveToNext()){
                    int numero = cursor.getInt(0);
                    String nombreSecundario = cursor.getString(1);
                    String color = cursor.getString(2);
                    String descripcionBasica = cursor.getString(3);
                    String descripcionAvanzada = cursor.getString(4);

                    String cartaCompleta = numero + ": " + nombreSecundario + " " + color + " "  + descripcionBasica + " " + descripcionAvanzada;

                    Log.d("DATABASE", cartaCompleta);
                }
            }
        });

        buttonListarCartaTactica.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.d("DATABASE","LIST TACTICS CARDS ON DATABASE");

                Cursor cursor = myDB.getAllCartasTacticas();

                if(cursor == null || cursor.getCount() == 0){
                    return;
                }

                while(cursor.moveToNext()){
                    int numero = cursor.getInt(0);
                    String tipoTactica = cursor.getString(1);
                    int numeroOrden = cursor.getInt(2);
                    String descripcion = cursor.getString(3);

                    String cartaCompleta = numero + ": " + tipoTactica + " " + numeroOrden + " " + descripcion;

                    Log.d("DATABASE", cartaCompleta);
                }

            }
        });

        buttonListarFichaHabilidad.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.d("DATABASE","LIST SKILL TOKENS ON DATABASE");

                Cursor cursor = myDB.getAllFichasHabilidad();

                if(cursor == null || cursor.getCount() == 0){
                    return;
                }

                while(cursor.moveToNext()){
                    int id = cursor.getInt(0);
                    String nombre = cursor.getString(1);
                    String descripcion = cursor.getString(2);
                    String heroe = cursor.getString(3);

                    String cartaCompleta = id + ": " + nombre + " " + descripcion + " " + heroe;

                    Log.d("DATABASE", cartaCompleta);
                }

            }
        });

        buttonListarHeroes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.d("DATABASE","LIST SKILL TOKENS ON DATABASE");

                Cursor cursor = myDB.getAllHeroes();

                if(cursor == null || cursor.getCount() == 0){
                    return;
                }

                while(cursor.moveToNext()){
                    String nombre = cursor.getString(0);
                    String cristal1 = cursor.getString(1);
                    String cristal2 = cursor.getString(2);
                    String cristal3 = cursor.getString(3);

                    String cartaCompleta = nombre + ": " + cristal1 + " " + cristal2 + " " + cristal3;

                    Log.d("DATABASE", cartaCompleta);
                }

            }
        });


    }
}

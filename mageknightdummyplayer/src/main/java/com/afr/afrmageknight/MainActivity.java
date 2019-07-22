package com.afr.afrmageknight;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.afr.afrmageknight.databaseHelper.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    // I - Declarar las variables
    private DatabaseHelper myDB;

    private Button buttonCrear;
    private Button buttonListar;
    private Button buttonEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCrear = (Button) findViewById(R.id.idButtonCrear);
        buttonListar = (Button) findViewById(R.id.idButtonListar);
        buttonEliminar = (Button) findViewById(R.id.idButtonEliminar);

        myDB = new DatabaseHelper(this);

        buttonCrear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int numero = 1;
                String nombre = "Versatilidad en la Batalla";

                String cartaCompleta = numero + " " + nombre;
                Log.d("DATABASE","INSERT DATA ON DATABASE");
                Toast.makeText(MainActivity.this, cartaCompleta, Toast.LENGTH_SHORT).show();

                myDB.insertData(numero, nombre);
            }
        });

        buttonListar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.d("DATABASE","LIST DATA ON DATABASE");

                Cursor cursor = myDB.getAll();

                // ¿Qué hace un cursor aquí? (no debería, pero va a funcionar)

                //Lo que debería SER
                //List<Amigo> amigos = interface.getAll();

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


        buttonEliminar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.d("DATABASE","DELETE TABLE ON DATABASE");

            }
        });














    }
}

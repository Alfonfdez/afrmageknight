package com.afr.afrmageknight;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.afr.afrmageknight.databaseHelper.DatabaseHelper;
import com.afr.afrmageknight.databaseHelper.DatabaseHelperInsertInitialData;
import com.afr.afrmageknight.model.Cristal;

public class MainActivity extends AppCompatActivity {

    // I - Declarar las variables
    //private DatabaseHelperInsertInitialData myDB;
    private DatabaseHelper myDB;

    private Button buttonInsertAllData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonInsertAllData = (Button) findViewById(R.id.idButtonInsertAllData);

        myDB = new DatabaseHelperInsertInitialData(this);

        // Botón para insertar toda la información en la base de datos
        buttonInsertAllData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.d("DATABASE","INSERT ALL DATA ON DATABASE");

                myDB.insertAllData();
            }
        });

    }
}

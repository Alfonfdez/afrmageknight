package com.afr.afrmageknight.databaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.afr.afrmageknight.model.Carta;
import com.afr.afrmageknight.model.CartaAvanzada;
import com.afr.afrmageknight.model.Cristal;
import com.afr.afrmageknight.model.Heroe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mageknight.db";

    public static final String CARTAS_TABLE = "CARTAS";

    public static final String COL_1 = "NUMERO";
    public static final String COL_2 = "NOMBRE";

    //'Harcodeamos' 3 argumentos: name, factory, version
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        Log.d("DATABASE", "PRIMERO - CONSTRUCTOR");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d("DATABASE", "SEGUNDO - ONCREATE");

        StringBuilder strSQL = new StringBuilder();

        strSQL.append("CREATE TABLE ").append(CARTAS_TABLE).append(" (")
                .append(COL_1).append(" INTEGER PRIMARY KEY NOT NULL, ")
                .append(COL_2).append(" TEXT NOT NULL")
                .append(")");

        Log.d("DATABASE", "TERCERO - ONCREATE");
        Log.d("DATABASE", strSQL.toString());

        db.execSQL(strSQL.toString());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CARTAS_TABLE);
        onCreate(db);
    }

    //Métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
    public boolean insertData(int numero, String nombre){

        //Necesito una referencia a la base de datos como tal
        SQLiteDatabase db = getWritableDatabase(); // El método 'getWritableDatabase()' nos da una referencia SÍ o SÍ. Si existe, ésa misma, y sino nos creará una nueva


        //Objeto específico de SQLite. Contenedor de valores: valores a insertar en la tabla.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1, numero);
        contentValues.put(COL_2, nombre);

        long resultado = db.insert(CARTAS_TABLE, null, contentValues);

        //Si 'resultado' es igual a -1 es que algo ha ido mal
        //Si 'resultado' es mayor o igual a 0, indicará el número de registros afectados

        return resultado == -1 ? false : true;
    }

    //Un 'Cursor' es una tabla virtual
    public Cursor getAll(){

        SQLiteDatabase db = getWritableDatabase();

        // 'selectionArgs' es un array de Strings -> Array[]
        // En la consulta pueden haber ?s que serán sustituidos por los valores de este array de String
        Cursor cursor = db.rawQuery("SELECT * FROM "+ CARTAS_TABLE, null);

        //Ejemplo
        // SELECT * FROM AMIGOS WHERE nombre=? AND apellido LIKE '?%';
        // String[] = {"Adolfo","D"};
        return cursor;
    }

    //Insertar datos de la carta en su correspondiente tabla
    public Carta create(Carta carta){

        //TODO insertar carta en las tablas....

        // 1.- Se guarda la información genérica, independientemente del tipo de carta (tabla CARTAS)


        // 2.- En función del tipo de carta, insertamos en la tabla que toque....

        if (carta.getClass() == CartaAvanzada.class){

        } else { // CartaTactica

        }


        return null;
    }

    private void insertCards(){

        // Los 7 Heroes
        Heroe arythea = new Heroe("Arythea",  (ArrayList<Cristal>) Arrays.asList(Cristal.ROJO, Cristal.ROJO,Cristal.BLANCO));
        Heroe tovak = new Heroe("Tovak",  (ArrayList<Cristal>) Arrays.asList(Cristal.VERDE, Cristal.VERDE,Cristal.AZUL));
        Heroe norowas = new Heroe("Norowas",  (ArrayList<Cristal>) Arrays.asList(Cristal.AZUL, Cristal.AZUL,Cristal.ROJO));
        Heroe goldyx = new Heroe("Goldyx",  (ArrayList<Cristal>) Arrays.asList(Cristal.BLANCO, Cristal.BLANCO,Cristal.VERDE));
        Heroe wolfhawk = new Heroe("Wolfhawk",  (ArrayList<Cristal>) Arrays.asList(Cristal.BLANCO, Cristal.BLANCO,Cristal.AZUL));
        Heroe krang = new Heroe("Krang",  (ArrayList<Cristal>) Arrays.asList(Cristal.ROJO, Cristal.ROJO,Cristal.VERDE));
        Heroe braevalar = new Heroe("Braevalar",  (ArrayList<Cristal>) Arrays.asList(Cristal.AZUL, Cristal.AZUL,Cristal.VERDE));

        // Las 176 Cartas Avanzadas
        // 16 cartas del Mazo de Gesta de Arythea (Acción Básica)
        create(new CartaAvanzada(1, "Versatilidad en la Batalla", Cristal.ROJO, "Ataque 2, Bloqueo 2 o Ataque a Distancia 1.", "Ataque 4, Bloqueo 4, Ataque de Fuego 3, Bloqueo de Fuego 3, Ataque a Distancia 3 o Ataque de Asedio 2.", arythea));
        create(new CartaAvanzada(2, "Rabia", Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", arythea));
        create(new CartaAvanzada(3, "Determinación", Cristal.AZUL, "Ataque o Bloqueo 2.", "Bloqueo 5.", arythea));
        create(new CartaAvanzada(4, "Rapidez", Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", arythea));
        create(new CartaAvanzada(5, "Rapidez", Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", arythea));
        create(new CartaAvanzada(6, "Marcha", Cristal.VERDE, "Movimiento 2.", "Movimiento 4.", arythea));
        create(new CartaAvanzada(7, "Marcha", Cristal.VERDE, "Movimiento 2.", "Movimiento 4.", arythea));
        create(new CartaAvanzada(8, "Resistencia", Cristal.AZUL, "Movimiento 2.", "Movimiento 4.", arythea));
        create(new CartaAvanzada(9, "Resistencia", Cristal.AZUL, "Movimiento 2.", "Movimiento 4.", arythea));
        create(new CartaAvanzada(10, "Tranquilidad", Cristal.VERDE, "Curación 1 o roba 1 carta.", "Curación 2 o roba 2 cartas.", arythea));
        create(new CartaAvanzada(11, "Promesa", Cristal.BLANCO, "Influencia 2.", "Influencia 4.", arythea));
        create(new CartaAvanzada(12, "Amenaza", Cristal.ROJO, "Influencia 2.", "Influencia 5. Reputación -1.", arythea));
        create(new CartaAvanzada(13, "Cristalización", Cristal.AZUL, "Cuando juegues esta carta, paga 1 maná de color básico. Gana 1 cristal de ese color para tu inventario.", "Gana 1 cristal de cualquier color para tu Inventario.", arythea));
        create(new CartaAvanzada(14, "Extracción de Maná", Cristal.BLANCO, "Puedes usar 1 dado de maná adicional de la Fuente en este turno. Si es negro, úsalo para producir maná de cualquier color.", "Toma 2 dados de maná de la Fuente y déjalos en el color que quieras excepto dorado. Gana 1 ficha de maná de cada color. No relances los dados cuando los devuelvas a la Fuente.", arythea));
        create(new CartaAvanzada(15, "Concentración", Cristal.VERDE, "Gana 1 ficha de maná azul, blanco o rojo.", "Cuando juegues esta carta, juega otra carta de Acción con ella. Gana el efecto avanzado de esa carta sin coste. Si el efecto te otorga Movimiento, Influencia, Bloqueo o cualquier tipo de Ataque, gana la cantidad indicada +2.", arythea));
        create(new CartaAvanzada(16, "Improvisación", Cristal.ROJO, "Descarta otra carta de tu mano para obtener Movimiento 3, Influencia 3, Ataque 3 o Bloqueo 3.", "Descarta otra carta de tu mano para obtener Movimiento 5, Influencia 5, Ataque 5 o Bloqueo 5.", arythea));

        // 16 cartas del Mazo de Gesta de Tovak (Acción Básica)
        create(new CartaAvanzada(17, "Rabia", Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", tovak));
        create(new CartaAvanzada(18, "Rabia", Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", tovak));
        create(new CartaAvanzada(19, "Dureza Fría", Cristal.AZUL, "Ataque 2 o Bloqueo de Hielo 3.", "Bloqueo de Hielo 5. Gana +1 Bloqueo de Hielo por cada aptitud o color del ataque de la ficha de enemigo bloqueada (a menos que tenga Inmunidad Arcana), o por cada maná utilizado por el oponente durante el ataque.", tovak));
        create(new CartaAvanzada(20, "Rapidez", Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", tovak));
        create(new CartaAvanzada(21, "Rapidez", Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", tovak));
        create(new CartaAvanzada(22, "Marcha", Cristal.VERDE, "Movimiento 2.", "Movimiento 4.", tovak));
        create(new CartaAvanzada(23, "Marcha", Cristal.VERDE, "Movimiento 2.", "Movimiento 4.", tovak));
        create(new CartaAvanzada(24, "Resistencia", Cristal.AZUL, "Movimiento 2.", "Movimiento 4.", tovak));
        create(new CartaAvanzada(25, "Resistencia", Cristal.AZUL, "Movimiento 2.", "Movimiento 4.", tovak));
        create(new CartaAvanzada(26, "Tranquilidad", Cristal.VERDE, "Curación 1 o roba 1 carta.", "Curación 2 o roba 2 cartas.", tovak));
        create(new CartaAvanzada(27, "Promesa", Cristal.BLANCO, "Influencia 2.", "Influencia 4.", tovak));
        create(new CartaAvanzada(28, "Amenaza", Cristal.ROJO, "Influencia 2.", "Influencia 5. Reputación -1.", tovak));
        create(new CartaAvanzada(29, "Cristalización", Cristal.AZUL, "Cuando juegues esta carta, paga 1 maná de color básico. Gana 1 cristal de ese color para tu inventario.", "Gana 1 cristal de cualquier color para tu Inventario.", tovak));
        create(new CartaAvanzada(30, "Atracción de Maná", Cristal.BLANCO, "Puedes usar 1 dado de maná adicional de la Fuente en este turno.", "Toma 1 dado de maná de la Fuente y escoge una cara de cualquier color excepto dorado. Gana 2 fichas de maná de ese color. No relances este dado cuando lo devuelvas a la Fuente.", tovak));
        create(new CartaAvanzada(31, "Concentración", Cristal.VERDE, "Gana 1 ficha de maná azul, blanco o rojo.", "Cuando juegues esta carta, juega otra carta de Acción con ella. Gana el efecto avanzado de esa carta sin coste. Si el efecto te otorga Movimiento, Influencia, Bloqueo o cualquier tipo de Ataque, gana la cantidad indicada +2.", tovak));
        create(new CartaAvanzada(32, "Instinto", Cristal.ROJO, "Movimiento 2, Influencia 2, Ataque 2 o Bloqueo 2.", "Movimiento 4, Influencia 4, Ataque 4 o Bloqueo 4.", tovak));

        // 16 cartas del Mazo de Gesta de Norowas (Acción Básica)
        create(new CartaAvanzada(33, "Rabia", Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", norowas));
        create(new CartaAvanzada(34, "Rabia", Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", norowas));
        create(new CartaAvanzada(35, "Determinación", Cristal.AZUL, "Ataque o Bloqueo 2.", "Bloqueo 5.", norowas));
        create(new CartaAvanzada(36, "Rapidez", Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", norowas));
        create(new CartaAvanzada(37, "Rapidez", Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", norowas));
        create(new CartaAvanzada(38, "Marcha", Cristal.VERDE, "Movimiento 2.", "Movimiento 4.", norowas));
        create(new CartaAvanzada(39, "Marcha", Cristal.VERDE, "Movimiento 2.", "Movimiento 4.", norowas));
        create(new CartaAvanzada(40, "Resistencia", Cristal.AZUL, "Movimiento 2.", "Movimiento 4.", norowas));
        create(new CartaAvanzada(41, "Resistencia", Cristal.AZUL, "Movimiento 2.", "Movimiento 4.", norowas));
        create(new CartaAvanzada(42, "Rejuvenecer", Cristal.VERDE, "Curación 1, roba 1 carta, gana 1 ficha de maná verde, o prepara 1 Unidad de nivel I o II.", "Curación 2, roba 2 cartas, gana 1 cristal de maná verde para tu inventario, o prepara 1 Unidad de nivel I, II o III.", norowas));
        create(new CartaAvanzada(43, "Actos Nobles", Cristal.BLANCO, "Influencia 2. Fama +1 si usas esta carta durante la interacción.", "Influencia 4. Fama +1 y Reputación +1 si usas esta carta durante la interacción.", norowas));
        create(new CartaAvanzada(44, "Amenaza", Cristal.ROJO, "Influencia 2.", "Influencia 5. Reputación -1.", norowas));
        create(new CartaAvanzada(45, "Cristalización", Cristal.AZUL, "Cuando juegues esta carta, paga 1 maná de color básico. Gana 1 cristal de ese color para tu inventario.", "Gana 1 cristal de cualquier color para tu Inventario.", norowas));
        create(new CartaAvanzada(46, "Atracción de Maná", Cristal.BLANCO, "Puedes usar 1 dado de maná adicional de la Fuente en este turno.", "Toma 1 dado de maná de la Fuente y escoge una cara de cualquier color excepto dorado. Gana 2 fichas de maná de ese color. No relances este dado cuando lo devuelvas a la Fuente.", norowas));
        create(new CartaAvanzada(47, "Concentración", Cristal.VERDE, "Gana 1 ficha de maná azul, blanco o rojo.", "Cuando juegues esta carta, juega otra carta de Acción con ella. Gana el efecto avanzado de esa carta sin coste. Si el efecto te otorga Movimiento, Influencia, Bloqueo o cualquier tipo de Ataque, gana la cantidad indicada +2.", norowas));
        create(new CartaAvanzada(48, "Improvisación", Cristal.ROJO, "Descarta otra carta de tu mano para obtener Movimiento 3, Influencia 3, Ataque 3 o Bloqueo 3.", "Descarta otra carta de tu mano para obtener Movimiento 5, Influencia 5, Ataque 5 o Bloqueo 5.", norowas));

        // 16 cartas del Mazo de Gesta de Goldyx (Acción Básica)
        create(new CartaAvanzada(49, "Rabia", Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", goldyx));
        create(new CartaAvanzada(50, "Rabia", Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", goldyx));
        create(new CartaAvanzada(51, "Determinación", Cristal.AZUL, "Ataque o Bloqueo 2.", "Bloqueo 5.", goldyx));
        create(new CartaAvanzada(52, "Rapidez", Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", goldyx));
        create(new CartaAvanzada(53, "Rapidez", Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", goldyx));
        create(new CartaAvanzada(54, "Marcha", Cristal.VERDE, "Movimiento 2.", "Movimiento 4.", goldyx));
        create(new CartaAvanzada(55, "Marcha", Cristal.VERDE, "Movimiento 2.", "Movimiento 4.", goldyx));
        create(new CartaAvanzada(56, "Resistencia", Cristal.AZUL, "Movimiento 2.", "Movimiento 4.", goldyx));
        create(new CartaAvanzada(57, "Resistencia", Cristal.AZUL, "Movimiento 2.", "Movimiento 4.", goldyx));
        create(new CartaAvanzada(58, "Tranquilidad", Cristal.VERDE, "Curación 1 o roba 1 carta.", "Curación 2 o roba 2 cartas.", goldyx));
        create(new CartaAvanzada(59, "Promesa", Cristal.BLANCO, "Influencia 2.", "Influencia 4.", goldyx));
        create(new CartaAvanzada(60, "Amenaza", Cristal.ROJO, "Influencia 2.", "Influencia 5. Reputación -1.", goldyx));
        create(new CartaAvanzada(61, "Alegría Cristalizada", Cristal.AZUL, "Paga 1 maná de cualquier color básico para ganar 1 cristal de ese color para tu inventario. Al final de tu turno, puedes descartar otra carta que no sea Herida para devolver esta carta a tu mano.", "Gana 1 cristal de cualquier color básico para tu inventario. Al final de tu turno, puedes descartar otra carta (incluso una Herida) para", goldyx));
        create(new CartaAvanzada(62, "Atracción de Maná", Cristal.BLANCO, "Puedes usar 1 dado de maná adicional de la Fuente en este turno.", "Toma 1 dado de maná de la Fuente y escoge una cara de cualquier color excepto dorado. Gana 2 fichas de maná de ese color. No relances este dado cuando lo devuelvas a la Fuente.", goldyx));
        create(new CartaAvanzada(63, "Enfoque Decisivo", Cristal.VERDE, "Gana 1 ficha de maná azul, blanco o rojo o bien 1 cristal verde para tu inventario.", "Cuando juegues esta carta, juega otra carta de Acción con ella. Gana el efecto avanzado de esa carta sin coste. Si el efecto te otorga Movimiento, Influencia, Bloqueo o cualquier tipo de Ataque, gana la cantidad indicada +3.", goldyx));
        create(new CartaAvanzada(64, "Improvisación", Cristal.ROJO, "Descarta otra carta de tu mano para obtener Movimiento 3, Influencia 3, Ataque 3 o Bloqueo 3.", "Descarta otra carta de tu mano para obtener Movimiento 5, Influencia 5, Ataque 5 o Bloqueo 5.", goldyx));

        // 16 cartas del Mazo de Gesta de Wolfhawk (Acción Básica)
        create(new CartaAvanzada(271, "Rabia", Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", wolfhawk));
        create(new CartaAvanzada(272, "Rabia", Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", wolfhawk));
        create(new CartaAvanzada(273, "Determinación", Cristal.AZUL, "Ataque o Bloqueo 2.", "Bloqueo 5.", wolfhawk));
        create(new CartaAvanzada(274, "Reflejos Instantáneos", Cristal.BLANCO, "Movimiento 2, Ataque a Distancia 1, o reduce el ataque de un enemigo en 1.", "Movimiento 4, Ataque a Distancia 3, o reduce el ataque de un enemigo en 2.", wolfhawk));
        create(new CartaAvanzada(275, "Rapidez", Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", wolfhawk));
        create(new CartaAvanzada(276, "Marcha", Cristal.VERDE, "Movimiento 2.", "Movimiento 4.", wolfhawk));
        create(new CartaAvanzada(277, "Marcha", Cristal.VERDE, "Movimiento 2.", "Movimiento 4.", wolfhawk));
        create(new CartaAvanzada(278, "Incansable", Cristal.AZUL, "Movimiento 2. Con la siguiente carta que otorgue Movimiento (Incluidas las cartas giradas) obtienes +1 Movimiento en este turno.", "Movimiento 4. Con cualquier otra carta que otorgue Movimiento (Incluidas las cartas giradas) obtienes +1 Movimiento en este turno.", wolfhawk));
        create(new CartaAvanzada(279, "Resistencia", Cristal.AZUL, "Movimiento 2.", "Movimiento 4.", wolfhawk));
        create(new CartaAvanzada(280, "Tranquilidad", Cristal.VERDE, "Curación 1 o roba 1 carta.", "Curación 2 o roba 2 cartas.", wolfhawk));
        create(new CartaAvanzada(281, "Promesa", Cristal.BLANCO, "Influencia 2.", "Influencia 4.", wolfhawk));
        create(new CartaAvanzada(282, "Amenaza", Cristal.ROJO, "Influencia 2.", "Influencia 5. Reputación -1.", wolfhawk));
        create(new CartaAvanzada(283, "Cristalización", Cristal.AZUL, "Cuando juegues esta carta, paga 1 maná de color básico. Gana 1 cristal de ese color para tu inventario.", "Gana 1 cristal de cualquier color para tu Inventario.", wolfhawk));
        create(new CartaAvanzada(284, "Atracción de Maná", Cristal.BLANCO, "Puedes usar 1 dado de maná adicional de la Fuente en este turno.", "Toma 1 dado de maná de la Fuente y escoge una cara de cualquier color excepto dorado. Gana 2 fichas de maná de ese color. No relances este dado cuando lo devuelvas a la Fuente.", wolfhawk));
        create(new CartaAvanzada(285, "Concentración", Cristal.VERDE, "Gana 1 ficha de maná azul, blanco o rojo.", "Cuando juegues esta carta, juega otra carta de Acción con ella. Gana el efecto avanzado de esa carta sin coste. Si el efecto te otorga Movimiento, Influencia, Bloqueo o cualquier tipo de Ataque, gana la cantidad indicada +2.", wolfhawk));
        create(new CartaAvanzada(286, "Improvisación", Cristal.ROJO, "Descarta otra carta de tu mano para obtener Movimiento 3, Influencia 3, Ataque 3 o Bloqueo 3.", "Descarta otra carta de tu mano para obtener Movimiento 5, Influencia 5, Ataque 5 o Bloqueo 5.", wolfhawk));

        // 16 cartas del Mazo de Gesta de Krang (Acción Básica)
        create(new CartaAvanzada(337, "Rabia", Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", krang));
        create(new CartaAvanzada(338, "Rabia", Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", krang));
        create(new CartaAvanzada(339, "Determinación", Cristal.AZUL, "Ataque o Bloqueo 2.", "Bloqueo 5.", krang));
        create(new CartaAvanzada(340, "Rapidez", Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", krang));
        create(new CartaAvanzada(341, "Rapidez", Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", krang));
        create(new CartaAvanzada(342, "Cosecha Salvaje", Cristal.VERDE, "Movimiento 2. Una vez en este turno, cuando te muevas un espacio, puedes descartar 1 carta para ganar 1 cristal del mismo color. Si descartas 1 Artefacto puedes escoger el color.", "Movimiento 4. Cada vez que te muevas un espacio en este turno, puedes descartar 1 carta para ganar 1 cristal del mismo color. Si descartas 1 Artefacto puedes escoger el color.", krang));
        create(new CartaAvanzada(343, "Marcha", Cristal.VERDE, "Movimiento 2.", "Movimiento 4.", krang));
        create(new CartaAvanzada(344, "Resistencia", Cristal.AZUL, "Movimiento 2.", "Movimiento 4.", krang));
        create(new CartaAvanzada(345, "Resistencia", Cristal.AZUL, "Movimiento 2.", "Movimiento 4.", krang));
        create(new CartaAvanzada(346, "Tranquilidad", Cristal.VERDE, "Curación 1 o roba 1 carta.", "Curación 2 o roba 2 cartas.", krang));
        create(new CartaAvanzada(347, "Promesa", Cristal.BLANCO, "Influencia 2.", "Influencia 4.", krang));
        create(new CartaAvanzada(348, "Extorsión Implacable", Cristal.ROJO, "Influencia 2. Puedes obtener un descuento de 2 en el coste de reclutamiento de 1 Unidad. Si reclutas esa Unidad en este turno, obtienes Reputación -1.", "Influencia 6. Reputación -1. Puedes preparar Unidades de nivel I y II que controles pagando 2 de Influencia por cada nivel de la Unidad.", krang));
        create(new CartaAvanzada(349, "Cristalización", Cristal.AZUL, "Cuando juegues esta carta, paga 1 maná de color básico. Gana 1 cristal de ese color para tu inventario.", "Gana 1 cristal de cualquier color para tu Inventario.", krang));
        create(new CartaAvanzada(350, "Atracción de Maná", Cristal.BLANCO, "Puedes usar 1 dado de maná adicional de la Fuente en este turno.", "Toma 1 dado de maná de la Fuente y escoge una cara de cualquier color excepto dorado. Gana 2 fichas de maná de ese color. No relances este dado cuando lo devuelvas a la Fuente.", krang));
        create(new CartaAvanzada(351, "Concentración", Cristal.VERDE, "Gana 1 ficha de maná azul, blanco o rojo.", "Cuando juegues esta carta, juega otra carta de Acción con ella. Gana el efecto avanzado de esa carta sin coste. Si el efecto te otorga Movimiento, Influencia, Bloqueo o cualquier tipo de Ataque, gana la cantidad indicada +2.", krang));
        create(new CartaAvanzada(352, "Improvisación", Cristal.ROJO, "Descarta otra carta de tu mano para obtener Movimiento 3, Influencia 3, Ataque 3 o Bloqueo 3.", "Descarta otra carta de tu mano para obtener Movimiento 5, Influencia 5, Ataque 5 o Bloqueo 5.", krang));

        // 16 cartas del Mazo de Gesta de Braevalar (Acción Básica)
        create(new CartaAvanzada(356, "Rabia", Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", braevalar));
        create(new CartaAvanzada(357, "Rabia", Cristal.ROJO, "Ataque o Bloqueo 2.", "Ataque 4.", braevalar));
        create(new CartaAvanzada(358, "Determinación", Cristal.AZUL, "Ataque o Bloqueo 2.", "Bloqueo 5.", braevalar));
        create(new CartaAvanzada(359, "Rapidez", Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", braevalar));
        create(new CartaAvanzada(360, "Rapidez", Cristal.BLANCO, "Movimiento 2.", "Ataque a Distancia 3.", braevalar));
        create(new CartaAvanzada(361, "Marcha", Cristal.VERDE, "Movimiento 2.", "Movimiento 4.", braevalar));
        create(new CartaAvanzada(362, "Unido con la Tierra", Cristal.VERDE, "Movimiento 2, Curación 1, o Bloqueo 2.", "Movimiento 4, Curación 2, o Bloqueo igual al coste de Movimiento sin modificar del espacio en el que te encuentras (Montañas 5, Lagos 2). Bloqueo de Fuego durante el Día y Bloqueo de Hielo durante la Noche.", braevalar));
        create(new CartaAvanzada(363, "Resistencia", Cristal.AZUL, "Movimiento 2.", "Movimiento 4.", braevalar));
        create(new CartaAvanzada(364, "Sendas del Druida", Cristal.AZUL, "Movimiento 2. El coste de Movimiento de un espacio se reduce en 1 en este turno, hasta un mínimo de 2.", "Movimiento 4. El coste de Movimiento de un tipo de terreno se reduce en 1 en este turno, hasta un mínimo de 2.", braevalar));
        create(new CartaAvanzada(365, "Tranquilidad", Cristal.VERDE, "Curación 1 o roba 1 carta.", "Curación 2 o roba 2 cartas.", braevalar));
        create(new CartaAvanzada(366, "Promesa", Cristal.BLANCO, "Influencia 2.", "Influencia 4.", braevalar));
        create(new CartaAvanzada(367, "Amenaza", Cristal.ROJO, "Influencia 2.", "Influencia 5. Reputación -1.", braevalar));
        create(new CartaAvanzada(368, "Cristalización", Cristal.AZUL, "Cuando juegues esta carta, paga 1 maná de color básico. Gana 1 cristal de ese color para tu inventario.", "Gana 1 cristal de cualquier color para tu Inventario.", braevalar));
        create(new CartaAvanzada(369, "Atracción de Maná", Cristal.BLANCO, "Puedes usar 1 dado de maná adicional de la Fuente en este turno.", "Toma 1 dado de maná de la Fuente y escoge una cara de cualquier color excepto dorado. Gana 2 fichas de maná de ese color. No relances este dado cuando lo devuelvas a la Fuente.", braevalar));
        create(new CartaAvanzada(370, "Concentración", Cristal.VERDE, "Gana 1 ficha de maná azul, blanco o rojo.", "Cuando juegues esta carta, juega otra carta de Acción con ella. Gana el efecto avanzado de esa carta sin coste. Si el efecto te otorga Movimiento, Influencia, Bloqueo o cualquier tipo de Ataque, gana la cantidad indicada +2.", braevalar));
        create(new CartaAvanzada(371, "Improvisación", Cristal.ROJO, "Descarta otra carta de tu mano para obtener Movimiento 3, Influencia 3, Ataque 3 o Bloqueo 3.", "Descarta otra carta de tu mano para obtener Movimiento 5, Influencia 5, Ataque 5 o Bloqueo 5.", braevalar));

        // 28 cartas de Acción Avanzada (juego base)
        create(new CartaAvanzada(65, "Rayo de Fuego", Cristal.ROJO, "Gana 1 cristal rojo para tu Inventario.", "Ataque a Distancia de Fuego 3.", null));
        create(new CartaAvanzada(66, "Rayo de Hielo", Cristal.AZUL, "Gana 1 cristal azul para tu Inventario.", "Ataque a Distancia de Hielo 3.", null));
        create(new CartaAvanzada(67, "Rayo Fulminante", Cristal.BLANCO, "Gana 1 cristal blanco para tu Inventario.", "Ataque a Distancia 4.", null));
        create(new CartaAvanzada(68, "Rayo Devastador", Cristal.VERDE, "Gana 1 cristal verde para tu Inventario.", "Ataque de Asedio 3.", null));
        create(new CartaAvanzada(69, "Furia Sangrienta", Cristal.ROJO, "Ataque 2. Puedes recibir 1 Herida para aumentar a Ataque 5.", "Ataque 4. Puedes recibir 1 Herida para aumentar a Ataque 9.", null));
        create(new CartaAvanzada(70, "Escudo de Hielo", Cristal.AZUL, "Bloqueo de Hielo 3.", "Bloqueo de Hielo 3. Reduce en 3 la Armadura de un enemigo bloqueado de esta manera. La Armadura no puede ser reducida por debajo de 1.", null));
        create(new CartaAvanzada(71, "Agilidad", Cristal.BLANCO, "Movimiento 2. Durante un combate en este turno, puedes gastar puntos de Movimiento para obtener Ataque 1: uno por cada punto.", "Movimiento 4. Durante un combate en este turno, puedes gastar puntos de Movimiento: 2 para Ataque a Distancia 1 y/o 1 para Ataque 1.", null));
        create(new CartaAvanzada(72, "Paseo Reparador", Cristal.VERDE, "Movimiento 2 y Curación 1. Durante el combate: solo Movimiento 2.", "Movimiento 4 y Curación 2. Durante el combate: solo Movimiento 4.", null));
        create(new CartaAvanzada(73, "Intimidación", Cristal.ROJO, "Influencia 4 o Ataque 3. Reputación -1.", "Influencia 8 o Ataque 7. Reputación -2.", null));
        create(new CartaAvanzada(74, "Puente Helado", Cristal.AZUL, "Movimiento 2. El coste de Movimiento de pantanos se reduce a 1 en este turno.", "Movimiento 4. Puedes moverte a través de lagos. El coste de Movimiento de lagos y pantanos se reduce a 1 en este turno.", null));
        create(new CartaAvanzada(75, "Canción del Viento", Cristal.BLANCO, "Movimiento 2. El coste de Movimiento de llanuras, desiertos y páramos se reduce en 1, hasta un mínimo de 0 en este turno.", "Movimiento 2. El Movimiento de llanuras, desiertos y páramos se reduce en 2, hasta un mínimo de 0 este turno. Puedes pagar un maná azul para moverte a través de lagos con un coste de Movimiento 0 este turno.", null));
        create(new CartaAvanzada(76, "Búsqueda del Camino", Cristal.VERDE, "Movimiento 2. El coste de Movimiento de todos los terrenos se reduce en 1, hasta un mínimo de 2 en este turno.", "Movimiento 4. El coste de Movimiento de todos los terrenos se reduce a 2 en este turno.", null));
        create(new CartaAvanzada(77, "Ritual de Sangre", Cristal.ROJO, "Recibe 1 Herida. Gana 1 cristal de maná rojo para tu inventario y 1 ficha de maná de cualquier color (incluidos los no básicos).", "Recibe 1 Herida. Gana 3 fichas de maná de cualquier color (Incluidos los no básicos). Puedes pagar 1 maná de cualquier color básico para ganar 1 cristal de ese color para tu inventario.", null));
        create(new CartaAvanzada(78, "Magia Pura", Cristal.AZUL, "Cuando juegues esta carta, paga 1 maná. Si pagas verde, Movimiento 4. Si pagas blanco, Influencia 4. Si pagas azul, Bloqueo 4. Si pagas rojo, Ataque 4.", "Cuando juegues esta carta, paga 1 maná. Si pagas verde, Movimiento 7. Si pagas blanco, Influencia 7. Si pagas azul, Bloqueo 7. Si pagas rojo, Ataque 7.", null));
        create(new CartaAvanzada(79, "Cuento Heroico", Cristal.BLANCO, "Influencia 3. Por cada Unidad que hayas reclutado en este turno, Reputación +1.", "Influencia 6. Por cada Unidad que hayas reclutado en este turno, Fama +1 y Reputación +1.", null));
        create(new CartaAvanzada(80, "Regeneración", Cristal.VERDE, "Curación 1. Prepara 1 Unidad de Nivel I o II que controles.", "Curación 2. Prepara 1 Unidad de Nivel I, II o III que controles.", null));
        create(new CartaAvanzada(81, "Hacia el Fragor", Cristal.ROJO, "Juega esta carta al principio del combate. Todas tus Unidades aumentan sus valores de Ataque y Bloqueo en +2 durante este combate. No puedes asignar daño a tus Unidades en este turno.", "Juega esta carta al principio del combate. Todas tus Unidades aumentan sus valores de Ataque y Bloqueo en +3 durante este combate. No puedes asignar daño a tus Unidades en este turno.", null));
        create(new CartaAvanzada(82, "Ritmo Constante", Cristal.AZUL, "Movimiento 2. Al final de tu turno, en lugar de colocar esta carta en tu pila de descarte, puedes colocarla en la parte inferior de tu mazo de Gesta, siempre y cuando no esté vacío.", "Movimiento 4. Al final de tu turno, en lugar de colocar esta carta en tu pila de descarte, puedes colocarla en la parte superior de tu mazo de Gesta.", null));
        create(new CartaAvanzada(83, "Diplomacia", Cristal.BLANCO, "Influencia 2. Puedes usar Influencia como Bloqueo en este turno.", "Influencia 4. Elige Hielo o Fuego. Puedes usar Influencia como Bloqueo del elemento elegido en este turno.", null));
        create(new CartaAvanzada(84, "Necesitado", Cristal.VERDE, "Influencia 3. Gana Influencia, +1 adicional por cada carta de Herida en tu mano o en Unidades que controles.", "Influencia 5. Gana Influencia, +2 adicional por cada carta de Herida en tu mano o en Unidades que controles.", null));
        create(new CartaAvanzada(85, "Descomposición", Cristal.ROJO, "Cuando juegues esta carta, retira 1 carta de Acción de tu mano. Gana para tu inventario 2 cristales del mismo color que la carta retirada.", "Cuando juegues esta carta, retira 1 carta de Acción de tu mano. Gana para tu inventario 1 cristal de cada color básico que no coincida con el color de la carta retirada.", null));
        create(new CartaAvanzada(86, "Dominio del Cristal", Cristal.AZUL, "Gana 1 cristal para tu Inventario del mismo color que un cristal que ya poseas.", "Al final del turno, cualquier cristal que hayas utilizado en este turno se devuelve a tu inventario.", null));
        create(new CartaAvanzada(87, "Tormenta de Maná", Cristal.BLANCO, "Elige de la Fuente 1 dado de maná de color básico. Gana 1 cristal de ese color para tu inventario. Relanza ese dado inmediatamente y devuélvelo a la Fuente.", "Relanza los dados de la Fuente. Puedes utilizar 3 dados extra, y puedes usar dados de maná negro o dorado como cualquier color básico, independientemente de la Ronda.", null));
        create(new CartaAvanzada(88, "Emboscada", Cristal.VERDE, "Movimiento 2. Añade +1 a tu primera carta de Ataque de cualquier tipo o +2 a tu primera carta de Bloqueo de cualquier tipo, lo primero que juegues en este turno.", "Movimiento 4. Añade +2 a tu primera carta de Ataque de cualquier tipo o +4 a tu primera carta de Bloqueo, lo primero que juegues en este turno.", null));
        create(new CartaAvanzada(89, "Máxima Efectividad", Cristal.ROJO, "Cuando juegues esta carta, retira otra carta de Acción de tu mano. Usa 3 veces el efecto básico de la carta retirada.", "Cuando juegues esta carta, retira otra carta de Acción de tu mano. Usa 2 veces el efecto avanzado de la carta retirada (sin coste).", null));
        create(new CartaAvanzada(90, "Talento Mágico", Cristal.AZUL, "Descarta 1 carta de cualquier color. Puedes jugar 1 carta de Hechizo de ese color de la oferta de Hechizos en este turno como si estuviera en tu mano. La carta permanece en la oferta de Hechizos.", "Cuando juegues esta carta, paga 1 maná de cualquier color. Gana 1 carta de Hechizo de ese color de la oferta de Hechizos y colócala en tu pila de descarte.", null));
        create(new CartaAvanzada(91, "Aprendizaje", Cristal.BLANCO, "Influencia 2. Una vez en este turno, puedes pagar Influencia 6 para obtener 1 carta de Acción Avanzada de la oferta de Acciones Avanzadas y colocarla en tu pila de descarte.", "Influencia 4. Una vez en este turno, puedes pagar Influencia 9 para obtener 1 carta de Acción Avanzada de la oferta de Acciones Avanzadas y colocarla en tu mano.", null));
        create(new CartaAvanzada(92, "Entrenamiento", Cristal.VERDE, "Retira 1 carta de Acción de tu mano para tomar 1 carta del mismo color de la oferta de Acciones Avanzadas y colocarla en tu pila de descarte.", "Retira 1 carta de Acción de tu mano para tomar 1 carta del mismo color de la oferta de Acciones Avanzadas y colocarla en tu mano.", null));

        // 16 cartas de Hechizo (juego base)
        create(new CartaAvanzada(93, "Bola de Fuego - Tormenta de Fuego", Cristal.ROJO, "Ataque a Distancia de Fuego 5.", "Recibe 1 Herida. Ataque de Asedio de Fuego 8.", null));
        create(new CartaAvanzada(94, "Tormenta de Nieve - Ventisca", Cristal.AZUL, "Ataque a Distancia de Hielo 5.", "Recibe 1 Herida. Ataque de Asedio de Hielo 8.", null));
        create(new CartaAvanzada(95, "Exposición - Exposición Masiva", Cristal.BLANCO, "El enemigo objetivo pierde todas las fortificaciones y resistencias durante este combate. Ataque a Distancia 2.", "Los enemigos pierden todas las fortificaciones, o pierden todas las resistencias durante este combate. Ataque a Distancia 3.", null));
        create(new CartaAvanzada(96, "Temblor - Terremoto", Cristal.VERDE, "El enemigo objetivo obtiene Armadura -3, o todos los enemigos obtienen Armadura -2. La Armadura no puede ser reducida por debajo de 1.", "El enemigo objetivo obtiene Armadura -3 (-6 si está fortificado), o todos los enemigos obtienen Armadura -2 (-4 si están fortificados). La Armadura no puede ser reducida por debajo de 1.", null));
        create(new CartaAvanzada(97, "Muro de Llamas - Ola de Llamas", Cristal.ROJO, "Ataque de Fuego 5, o Bloqueo de Fuego 7.", "Ataque de Fuego 5, o Bloqueo de Fuego 7. Este Ataque o Bloqueo incrementa en 2 por cada ficha de enemigo a la que te estás enfrentando.", null));
        create(new CartaAvanzada(98, "Rayo de Maná - Trueno de Maná", Cristal.AZUL, "Cuando juegues esta carta, paga 1 maná. Si es azul, Ataque de Hielo 8. Si es rojo, Ataque de Fuego Frío 7. Si es blanco, Ataque a Distancia de Hielo 6. Si es verde, Ataque de Asedio de Hielo 5.", "Cuando juegues esta carta, paga 1 maná. Si es azul, Ataque de Hielo 11. Si es rojo, Ataque de Fuego Frío 10. Si es blanco, Ataque a Distancia de Hielo 9. Si es verde, Ataque de Asedio de Hielo 8.", null));
        create(new CartaAvanzada(99, "Torbellino - Tornado", Cristal.BLANCO, "El enemigo objetivo no ataca durante este combate.", "Juega esta carta solo en la fase de Ataque del combate. Destruye al enemigo objetivo.", null));
        create(new CartaAvanzada(100, "Viaje Subterráneo - Ataque Subterráneo", Cristal.VERDE, "Muévete hasta 3 espacios revelados en el mapa. No puedes moverte a o a través de pantanos o lagos de este modo, y debes terminar tu movimiento en un espacio seguro. Este movimiento no provoca a los enemigos acechantes.", "Igual que el efecto básico, excepto que debes terminar tu movimiento en un lugar fortificado (o en un espacio ocupado por otro jugador). Esto termina tu movimiento e inicia un asalto (o un ataque a otro jugador). Ignora las fortificaciones del lugar. Si te retiras después del combate, vuelve a tu posición inicial.", null));
        create(new CartaAvanzada(101, "Escudo Ardiente - Escudo Explosivo", Cristal.ROJO, "Bloqueo de Fuego 4. Si usas esta carta en un Bloqueo exitoso , puedes usarla también en la fase de Ataque como Ataque de Fuego 4.", "Bloqueo de Fuego 4. Si usas esta carta en un Bloqueo exitoso, destruye al enemigo bloqueado.", null));
        create(new CartaAvanzada(102, "Escalofrío - Escalofrío Letal", Cristal.AZUL, "El enemigo objetivo no ataca durante este combate. Si tiene Resistencia al Fuego, la pierde para el resto del turno.", "El enemigo objetivo no ataca durante este combate y obtiene Armadura -4 (hasta un mínimo de 1) para el resto del turno.", null));
        create(new CartaAvanzada(103, "Alas de Viento - Alas de Noche", Cristal.BLANCO, "Cuando juegues esta carta, gasta 1-5 puntos de Movimiento y muévete a un espacio revelado por cada punto. Debes terminar tu movimiento en un espacio seguro. Este movimiento no provoca a los enemigos acechantes.", "El enemigo objetivo no ataca durante este combate. Puedes hacer objetivo a enemigos adicionales: paga 1 punto de Movimiento por el segundo enemigo, 2 puntos por el tercero, etc.", null));
        create(new CartaAvanzada(104, "Restaurar - Renacer", Cristal.VERDE, "Curación 3. Si estás en un bosque, Curación 5 en su lugar.", "Curación 3. Si estás en un bosque, Curación 5 en su lugar. Prepara Unidades hasta un valor de 3 niveles. Si estás en un Bosque, Prepara Unidades hasta un valor de 5 niveles en su lugar.", null));
        create(new CartaAvanzada(105, "Demoler - Desintegrar", Cristal.ROJO, "Ignora las fortificaciones de los lugares en este turno. Los enemigos obtienen Armadura -1 (hasta un mínimo de 1).", "Juega esta carta solamente durante la fase de Ataque del combate. Destruye al enemigo objetivo. Otros enemigos obtienen Armadura -1 (hasta un mínimo de 1).", null));
        create(new CartaAvanzada(106, "Salto en el Espacio - Salto en el Tiempo", Cristal.AZUL, "En este turno, puedes moverte a espacios y explorar nuevas losetas que estén a 2 espacios de distancia de ti como si estuvieran adyacentes. Ignora cualquier espacio por el que saltes de este modo. Este movimiento no provoca a los enemigos acechantes.", "Al final de tu turno, deja esta carta apartada para el resto de la Ronda. Coloca todas las demás cartas que hayas jugado en este turno (pero no las que hayas descartado o retirado) de nuevo en tu mano. Ignora la parte \"roba cartas nuevas\" del paso final de tu turno. Vuelve a realizar inmediatamente otro turno completo.", null));
        create(new CartaAvanzada(107, "Llamada a las Armas - Llamada a la Gloria", Cristal.BLANCO, "Puedes usar una aptitud de una Unidad en la oferta de Unidades en este turno, como si fuese una de las reclutadas por ti. No puedes asignar daño a esta Unidad.", "Recluta cualquier Unidad de la oferta de Unidades sin coste. (Si no tienes espacio para añadirla, debes disolver una Unidad primero.)", null));
        create(new CartaAvanzada(108, "Meditación - Trance", Cristal.VERDE, "Toma 2 cartas al azar de tu pila de descarte y colócalas en la parte inferior o superior de tu mazo de Gesta. Tu límite de mano aumenta en 2 la próxima vez que robes cartas.", "Igual que el efecto básico, excepto que eliges las cartas en vez de tomarlas al azar.", null));

        // 4 cartas de Acción Avanzada (Ultimate Edition)
        /*create(new CartaAvanzada(266, "Poder de los Cristales", Cristal.VERDE,  Cristal.AZUL, "Gana 1 cristal para tu Inventario de un color básico que aún no tengas.", "Movimiento 4, o Curación 2, o roba dos cartas. Por cada conjunto de 4 cristales de diferentes colores en tu Inventario: Movimiento 2, o Curación 1, o roba una carta.", null));
        create(new CartaAvanzada(267, "Descarga de Adrenalina", Cristal.ROJO, Cristal.VERDE, "Por cada una de las primeras 3 Heridas que llevas a la mano en este turno, roba 1 carta.", "Tras tomar la primera Herida a la mano en este turno, retírala y roba 1 carta. Por cada 1 de las 3 Heridas siguientes que tomes, roba 1 carta.", null));
        create(new CartaAvanzada(268, "Rayo Explosivo", Cristal.BLANCO, Cristal.ROJO, "Recibe 1 Herida. Gana 1 cristal blanco y rojo para tu Inventario.", "Ataque a Distancia 3. Por cada enemigo derrotado con este ataque, otro enemigo obtiene Armadura -1 (hasta un mínimo de 1).", null));
        create(new CartaAvanzada(269, "Mirada Penetrante", Cristal.AZUL, Cristal.BLANCO, "Influencia 3, o el ataque del enemigo objetivo pierde todas sus aptitudes (pero no su color).", "Influencia 5, o el enemigo objetivo no ataca en este turno.", null));*/

        // 12 cartas de Acción Avanzada (Legión Perdida)
        create(new CartaAvanzada(287, "Contraataque", Cristal.ROJO, "Ataque 2. Gana Ataque 2 adicional por cada enemigo bloqueado en este turno.", "Ataque 4. Gana Ataque 3 adicional por cada enemigo bloqueado en este turno.", null));
        create(new CartaAvanzada(288, "Ritual de Ataque", Cristal.ROJO, "Retira otra carta de acción. En función de su color, gana: Rojo: Ataque 5, Azul: Ataque de Hielo 3, Blanco: Ataque a Distancia 3, Verde: Ataque de Asedio 2.", "Retira otra carta de acción. En función de su color, gana: Rojo: Ataque de Fuego 6, Azul: Ataque de Fuego Frío 4, Blanco: Ataque a Distancia de Fuego 4, Verde: Ataque de Asedio de Fuego 3.", null));
        create(new CartaAvanzada(289, "Sangre Ancestral", Cristal.ROJO, "Recibe 1 Herida. Paga 1 maná de cualquier color. Gana 1 carta de ese color de la oferta de Acciones Avanzadas y colócala en tu mano.", "Recibe 1 Herida a tu mano o pila de descarte. Utiliza el efecto avanzado de cualquier carta de la oferta de Acciones Avanzadas sin pagar su coste. La carta permanece en la oferta.", null));
        create(new CartaAvanzada(290, "Golpe de Escudo", Cristal.AZUL, "Bloqueo 3. Cuenta el doble contra un ataque Veloz.", "Bloqueo 5. Cuenta el doble contra un ataque Veloz. El enemigo bloqueado obtiene Armadura -1 por cada punto de bloqueo sobrante (hasta un mínimo de 1).", null));
        create(new CartaAvanzada(291, "Portal Temporal", Cristal.AZUL, "Juega esta carta como tu acción en este turno. Puedes moverte a un espacio seguro adyacente revelado (sin provocar enemigos acechantes). Tanto si te mueves como si no, tu límite de mano aumenta en 1 la próxima vez que robes cartas.", "Lo mismo que el efecto básico, excepto que puedes o moverte hacia un espacio seguro 2 espacios en vez de uno, o aumentar tu límite de mano en 2 en vez de 1.", null));
        create(new CartaAvanzada(292, "Hechizo Forjado", Cristal.AZUL, "Gana 1 cristal para tu Inventario del mismo color de 1 de los Hechizos en la oferta de Hechizos.", "Gana 2 cristales para tu Inventario de los mismos colores que 2 Hechizos diferentes en la oferta de Hechizos.", null));
        create(new CartaAvanzada(293, "Cortesía", Cristal.BLANCO, "Ataque 3, o Ataque 2 y Reputación +1 por cada enemigo derrotado por este ataque.", "Ataque 6, o Ataque 4 y Reputación +1 y Fama +1 por cada enemigo derrotado por este ataque.", null));
        create(new CartaAvanzada(294, "Momento de Paz", Cristal.BLANCO, "Influencia 3. Puedes jugar esta carta como tu acción en este turno: si lo haces, puedes obtener Curación 1 por cada 2 de Influencia que hayas gastado.", "Influencia 6. Puedes jugar esta carta como tu acción en este turno: si lo haces, puedes obtener Curación 1 por cada 2 de Influencia que hayas gastado y/o preparar una Unidad pagando 2 de Influencia por cada nivel de la Unidad.", null));
        create(new CartaAvanzada(295, "Esquivar y Evitar", Cristal.BLANCO, "Reduce el ataque de un enemigo en 2. Gana Ataque 1 en la fase de Ataque si no añadiste Heridas a tu mano en las fases previas del combate.", "Reduce el ataque de un enemigo en 4 o 2 ataques de 1 o 2 enemigos en 2. Gana Ataque 2 en la fase de Ataque si no añadiste Heridas a tu mano en las fases previas del combate.", null));
        create(new CartaAvanzada(296, "Decisión Valiente", Cristal.VERDE, "Movimiento 2, Influencia 2, Ataque 2 o Bloqueo 2. Puedes descartar 1 Herida para aumentar el efecto en 1.", "Movimiento 3, Influencia 3, Ataque 3 o Bloqueo 3. Puedes descartar cualquier número de cartas, incluyendo 1 Herida, para incrementar el efecto en 2 por cada una.", null));
        create(new CartaAvanzada(297, "Fuerza de la Naturaleza", Cristal.VERDE, "La Unidad elegida gana Resistencia Física en este combate.", "Ataque de Asedio 3 o Bloqueo 6", null));
        create(new CartaAvanzada(298, "Sabiduría de la Montaña", Cristal.VERDE, "Movimiento 3. Si finalizas tu turno en la colinas, tu límite de Mano aumenta en 1 la próxima vez que robes cartas.", "Movimiento 5. Puedes entrar en Montañas con un coste de Movimiento 5 y se considerarán un espacio seguro para ti hasta el final de este turno. Si finalizas tu turno en montañas/colinas, tu límite de Mano aumenta en 2/1 la próxima vez que robes cartas.", null));

        // 4 cartas de Hechizo (Legión Perdida)
        create(new CartaAvanzada(299, "Ofrenda - Sacrificio", Cristal.ROJO, "Gana un cristal rojo para tu Inventario. Puedes descartar hasta 3 cartas que no sean Herida de tu mano. Por cada carta descartada gana un cristal del mismo color que la carta para tu Inventario (si descartas Artefactos, puedes escoger el color).", "Elige verde o blanco, luego elige rojo o azul. Gana Ataque de Asedio 4 (si escogiste verde) o Ataque a Distancia 6 (si escogiste blanco) por cada pareja de cristales de uno de estos dos colores en tu Inventario. Este Ataque es de Fuego (si escogiste rojo) o Hielo (si escogiste azul). Luego, convierte todas las parejas de cristales en maná.", null));
        create(new CartaAvanzada(300, "Forma Nebulosa - Velo Nebuloso", Cristal.AZUL, "Movimiento 4. El coste de Movimiento de todos los terrenos, incluidos los lagos, es 2. No puedes entrar en colinas y montañas durante el resto de este turno.", "Todas tus Unidades obtienen todas las resistencias. Tu héroe ignora la primera Herida de los enemigos en este turno. Ignora también cualquier efecto adicional de esa Herida.", null));
        create(new CartaAvanzada(301, "Encanto - Posesión", Cristal.BLANCO, "Influencia 4. Si usas esta carta durante la interacción puedes ganar un cristal de cualquier color para tu Inventario o bien obtener un descuento de 3 en el coste de una Unidad.", "El enemigo objetivo no ataca. En la fase de Ataque ganas el mismo Ataque que el del enemigo, incluidos sus elementos (si tuviera alguno), pero ignorando las aptitudes especiales. Con ese Ataque solo puedes hacer objetivo a otros enemigos.", null));
        create(new CartaAvanzada(302, "Cura - Enfermedad", Cristal.VERDE, "Curación 2. Roba una carta por cada Herida curada de tu mano en este turno, y prepara las Unidades curadas en este turno.", "Todos los enemigos bloqueados durante la fase de Bloqueo reducen su Armadura a 1.", null));


    }



}

package com.afr.afrmageknight.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.ListView;

import com.afr.afrmageknight.activities.InitialMenuActivity;
import com.afr.afrmageknight.model.CartaAccionAvanzada;
import com.afr.afrmageknight.model.CartaAccionAvanzadaEspecial;
import com.afr.afrmageknight.model.CartaTactica;

import java.util.ArrayList;
import java.util.List;

public class AdvancedActionCardsDialogFragment extends DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        /*String tituloDialogFragment = "Cartas de Acción Avanzada\nCarta inferior de la Oferta para el JV";
        List<CartaAccionAvanzada> cartasAccionAvanzada = InitialMenuActivity.gameServicesImpl.getAdvancedActionCards();
        List<CartaAccionAvanzadaEspecial> cartasAccionAvanzadaEspecial = InitialMenuActivity.gameServicesImpl.getSpecialAdvancedActionCards();
        final String[] accionAvanzadasArray;


        List<String> accionAvanzadas = new ArrayList<String>();
        for(CartaAccionAvanzada cartaAccionAvanzada: cartasAccionAvanzada){
            String informacionAccionAvanzada = cartaAccionAvanzada.getNumero() + " - " + cartaAccionAvanzada.getNombre() + " - " + cartaAccionAvanzada.getColor();
            informacionAccionAvanzada.add(accionAvanzadas);
        }
        accionAvanzadasArray = accionAvanzadas.toArray(new String[accionAvanzadas.size()]);

        List<String> accionAvanzadasEspeciales = new ArrayList<String>();
        for(CartaAccionAvanzadaEspecial cartaAccionAvanzadaEspecial: cartasAccionAvanzadaEspecial){
            String informacionAccionAvanzadaEspecial = cartaAccionAvanzadaEspecial.getNumero() + " - " + cartaAccionAvanzadaEspecial.getNombre() + " - " + cartaAccionAvanzadaEspecial.getColor() + " - " + cartaAccionAvanzadaEspecial.getColorSecundario();
            informacionAccionAvanzadaEspecial.add(accionAvanzadasEspeciales);
        }
        accionAvanzadasEspecialesArray = accionAvanzadasEspeciales.toArray(new String[accionAvanzadasEspeciales.size()]);*/


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        /*builder.setTitle(tituloDialogFragment)
                .setSingleChoiceItems(accionAvanzadasArray, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        ListView lw = ((AlertDialog)dialog).getListView();
                        Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());

                        String nombreCartaTactica = InitialMenuActivity.gameServicesImpl.getTacticCardNameFromString(checkedItem.toString());

                        //Método para actualizar la carta Táctica seleccionada por el Jugador/Jugadores a 'DESCARTADA'=1
                        InitialMenuActivity.gameServicesImpl.modifyTableGameTacticCardAvailabilityByName(nombreCartaTactica, true);

                        //Método para descartar al azar una carta Táctica después de la selección del Jugador en el modo SOLITARIO
                        discardGameTacticCardRandomlyAfterPlayerTacticCardSelectionInSolitaireGame();

                        //Método para actualizar la carta Táctica seleccionada al azar anteriormente por el Jugador Virtual a 'DESCARTADA'=0 (Carta disponible) en el modo COOPERATIVO
                        availableSameGameTacticCardAfterPlayersTacticCardSelectionInCooperative(cartaTacticaDescartadaPartidaCooperativo);

                        if(!InitialMenuActivity.gameServicesImpl.isFirstRound()){ //Rondas 2, 3, 4, 5, 6
                            //Barajar cartas del Jugador Virtual y actualizar todas las cartas a NO descartadas
                            shuffleDummyPlayerCardsAndUpdateToAvailable();
                        }

                        //Crear método para convertir RONDA_ESTADO_INICIO = 0
                        InitialMenuActivity.gameServicesImpl.modifyTableGameStatusRoundBeginning(false);

                        dialog.dismiss();
                    }
                });*/

        return builder.create();
    }

}
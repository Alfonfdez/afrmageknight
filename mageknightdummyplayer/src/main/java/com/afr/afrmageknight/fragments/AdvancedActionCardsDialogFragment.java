package com.afr.afrmageknight.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.ListView;

import com.afr.afrmageknight.activities.InitialMenuActivity;
import com.afr.afrmageknight.model.CartaAccionAvanzada;
import com.afr.afrmageknight.model.CartaAccionAvanzadaEspecial;
import com.afr.afrmageknight.model.CartaTactica;

import java.util.ArrayList;
import java.util.List;

public class AdvancedActionCardsDialogFragment extends DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //I - Declarar variables
        String tituloDialogFragment = "Cartas de Acción Avanzada\nCarta inferior de la Oferta para el JV";
        List<CartaAccionAvanzada> cartasAccionesAvanzadas = InitialMenuActivity.gameServicesImpl.getAdvancedActionCards();
        List<CartaAccionAvanzadaEspecial> cartasAccionesAvanzadasEspeciales = InitialMenuActivity.gameServicesImpl.getSpecialAdvancedActionCards();

        final String[] accionAvanzadasArrayFinal = getAdvancedActionCardArray(cartasAccionesAvanzadas, cartasAccionesAvanzadasEspeciales);

        //AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(tituloDialogFragment)
                .setSingleChoiceItems(accionAvanzadasArrayFinal, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        ListView lw = ((AlertDialog)dialog).getListView();
                        Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());

                        int numeroCarta = InitialMenuActivity.gameServicesImpl.getAdvancedActionCardNumberFromString(checkedItem.toString());

                        if(numeroCarta!=266 && numeroCarta!=267 && numeroCarta!=268 && numeroCarta!=269){
                            CartaAccionAvanzada cartaAccionAvanzada = InitialMenuActivity.gameServicesImpl.getAdvancedActionCard(numeroCarta);
                            //InitialMenuActivity.gameServicesImpl.insertTableGameNewAdvancedActionCard();
                            //int numeroCarta, String nombre, boolean esDescartada, String colorCristal, String colorSecundarioCristal, String descripcionBasica, String descripcionAvanzada, String heroe, int indice
                        } else {
                            CartaAccionAvanzadaEspecial cartaAccionAvanzadaEspecial = InitialMenuActivity.gameServicesImpl.getSpecialAdvancedActionCardByNumber(numeroCarta);
                        }

                        /*ListView lw = ((AlertDialog)dialog).getListView();
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
                        InitialMenuActivity.gameServicesImpl.modifyTableGameStatusRoundBeginning(false);*/

                        dialog.dismiss();
                    }
                });

        return builder.create();
    }

    //Métodos privados
    private String[] getAdvancedActionCardArray(List<CartaAccionAvanzada> cartasAccionesAvanzadas, List<CartaAccionAvanzadaEspecial> cartasAccionesAvanzadasEspeciales){
        String[] accionAvanzadasArray;
        String[] accionAvanzadasEspecialesArray;

        List<String> accionAvanzadas = new ArrayList<String>();
        for(CartaAccionAvanzada cartaAccionAvanzada: cartasAccionesAvanzadas){
            String informacionAccionAvanzada = cartaAccionAvanzada.getNumero() + " - " + cartaAccionAvanzada.getNombre() + " - " + cartaAccionAvanzada.getColor();
            accionAvanzadas.add(informacionAccionAvanzada);
        }
        accionAvanzadasArray = accionAvanzadas.toArray(new String[accionAvanzadas.size() + 4]);

        List<String> accionAvanzadasEspeciales = new ArrayList<String>();
        for(CartaAccionAvanzadaEspecial cartaAccionAvanzadaEspecial: cartasAccionesAvanzadasEspeciales){
            String informacionAccionAvanzadaEspecial = cartaAccionAvanzadaEspecial.getNumero() + " - " + cartaAccionAvanzadaEspecial.getNombre() + " - " + cartaAccionAvanzadaEspecial.getColor() + " - " + cartaAccionAvanzadaEspecial.getColorSecundario();
            accionAvanzadasEspeciales.add(informacionAccionAvanzadaEspecial);
        }
        accionAvanzadasEspecialesArray = accionAvanzadasEspeciales.toArray(new String[accionAvanzadasEspeciales.size()]);

        for(int i = 0; i < accionAvanzadasEspecialesArray.length; i++){
            if(i == 0){
                accionAvanzadasArray[accionAvanzadasArray.length - 4] = accionAvanzadasEspecialesArray[i];
            } else if(i == 1){
                accionAvanzadasArray[accionAvanzadasArray.length - 3] = accionAvanzadasEspecialesArray[i];
            } else if(i == 2){
                accionAvanzadasArray[accionAvanzadasArray.length - 2] = accionAvanzadasEspecialesArray[i];
            } else {
                accionAvanzadasArray[accionAvanzadasArray.length - 1] = accionAvanzadasEspecialesArray[i];
            }
        }

        return accionAvanzadasArray;
    }

}
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

import java.util.ArrayList;
import java.util.List;

public class AdvancedActionCardsDialogFragment extends DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //I - Declarar variables
        String tituloDialogFragment = "Cartas de Acción Avanzada\nCarta inferior Oferta para el JV";
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

                        int numeroCarta = InitialMenuActivity.gameServicesImpl.getActionCardNumberFromString(checkedItem.toString());

                        if(numeroCarta!=266 && numeroCarta!=267 && numeroCarta!=268 && numeroCarta!=269){
                            CartaAccionAvanzada cartaAccionAvanzada = InitialMenuActivity.gameServicesImpl.getAdvancedActionCardByCardNumber(numeroCarta);

                            //Método para insertar la carta de Acción Avanzada que se encuentra en la parte inferior de la Oferta al mazo del Jugador Virtual
                            InitialMenuActivity.gameServicesImpl.insertTableGameNewAdvancedActionCard(cartaAccionAvanzada.getNumero(), cartaAccionAvanzada.getNombre(), cartaAccionAvanzada.isDescartada(), cartaAccionAvanzada.getColor().toString(), null, cartaAccionAvanzada.getDescripcionBasica(), cartaAccionAvanzada.getDescripcionAvanzada(), null, InitialMenuActivity.gameServicesImpl.getGameTotalCardsDummyPlayer());

                            //Insertar la información de la carta de Acción Avanzada añadida al mazo del Jugador Virtual
                            String informacionPartida = InitialMenuActivity.gameServicesImpl.getGameInformationAdvancedActionCardAddedToDummyPlayer(cartaAccionAvanzada);
                            InitialMenuActivity.gameServicesImpl.insertTableGameRoundInformation(informacionPartida);
                        } else {
                            CartaAccionAvanzadaEspecial cartaAccionAvanzadaEspecial = InitialMenuActivity.gameServicesImpl.getSpecialAdvancedActionCardByCardNumber(numeroCarta);

                            //Método para insertar la carta de Acción Avanzada Especial que se encuentra en la parte inferior de la Oferta al mazo del Jugador Virtual
                            InitialMenuActivity.gameServicesImpl.insertTableGameNewAdvancedActionCard(cartaAccionAvanzadaEspecial.getNumero(), cartaAccionAvanzadaEspecial.getNombre(), cartaAccionAvanzadaEspecial.isDescartada(), cartaAccionAvanzadaEspecial.getColor().toString(), cartaAccionAvanzadaEspecial.getColorSecundario().toString(), cartaAccionAvanzadaEspecial.getDescripcionBasica(), cartaAccionAvanzadaEspecial.getDescripcionAvanzada(), null, InitialMenuActivity.gameServicesImpl.getGameTotalCardsDummyPlayer());

                            //Insertar la información de la carta de Acción Avanzada Especial añadida al mazo del Jugador Virtual
                            String informacionPartida = InitialMenuActivity.gameServicesImpl.getGameInformationSpecialAdvancedActionCardAddedToDummyPlayer(cartaAccionAvanzadaEspecial);
                            InitialMenuActivity.gameServicesImpl.insertTableGameRoundInformation(informacionPartida);
                        }

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
            String informacionAccionAvanzada = cartaAccionAvanzada.getNumero() + " - " + cartaAccionAvanzada.getNombre() + " (" + cartaAccionAvanzada.getColor() + ")";
            accionAvanzadas.add(informacionAccionAvanzada);
        }
        accionAvanzadasArray = accionAvanzadas.toArray(new String[accionAvanzadas.size() + 4]);

        List<String> accionAvanzadasEspeciales = new ArrayList<String>();
        for(CartaAccionAvanzadaEspecial cartaAccionAvanzadaEspecial: cartasAccionesAvanzadasEspeciales){
            String informacionAccionAvanzadaEspecial = cartaAccionAvanzadaEspecial.getNumero() + " - " + cartaAccionAvanzadaEspecial.getNombre() + " (" + cartaAccionAvanzadaEspecial.getColor() + ") (" + cartaAccionAvanzadaEspecial.getColorSecundario() + ")";
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
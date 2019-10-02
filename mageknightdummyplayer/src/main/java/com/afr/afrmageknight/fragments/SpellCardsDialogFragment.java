package com.afr.afrmageknight.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.ListView;

import com.afr.afrmageknight.activities.InitialMenuActivity;
import com.afr.afrmageknight.model.CartaAccionHechizo;

import java.util.ArrayList;
import java.util.List;

public class SpellCardsDialogFragment extends DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //I - Declarar variables
        String tituloDialogFragment = "Cartas de Hechizo\nCarta inferior Oferta y Cristal al JV";
        List<CartaAccionHechizo> cartasAccionesHechizos = InitialMenuActivity.gameServicesImpl.getSpellCards();

        final String[] hechizosArrayFinal = getSpellCardArray(cartasAccionesHechizos);


        //AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(tituloDialogFragment)
                .setSingleChoiceItems(hechizosArrayFinal, 0, new DialogInterface.OnClickListener() {
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

                        CartaAccionHechizo cartaAccionHechizo = InitialMenuActivity.gameServicesImpl.getSpellCardByCardNumber(numeroCarta);

                        //Método para insertar un cristal nuevo al Inventario del Jugador Virtual a partir de un Hechizo de la parte inferior de la Oferta de Hechizos
                        InitialMenuActivity.gameServicesImpl.insertTableGameNewCristal(cartaAccionHechizo.getColor().toString());

                        //Insertar la información del cristal añadido al Inventario del Jugador Virtual, a partir de la carta de Hechizo
                        String informacionPartida = InitialMenuActivity.gameServicesImpl.getGameInformationCristalAddedToDummyPlayer(cartaAccionHechizo);
                        InitialMenuActivity.gameServicesImpl.insertTableGameRoundInformation(informacionPartida);

                        //Método para actualizar la Ronda a la siguiente
                        String estadoRondaSiguiente = InitialMenuActivity.gameServicesImpl.getNextGameRoundFromCurrentRound(InitialMenuActivity.gameServicesImpl.getGameRound());
                        InitialMenuActivity.gameServicesImpl.modifyTableGameRound(estadoRondaSiguiente);

                        //Método para actualizar la columna 'RONDA_ESTADO_FINALIZADO'=0 (false) de la tabla 'PARTIDA_DATOS'
                        InitialMenuActivity.gameServicesImpl.modifyTableGameStatusRoundEnding(false);

                        //Método para actualizar la columna 'TURNO' de la tabla 'PARTIDA_DATOS' a un valor 0
                        InitialMenuActivity.gameServicesImpl.modifyTableGameStatusTurnNumber(0);

                        //Crear método para convertir 'RONDA_ESTADO_INICIO' = 1 (true)
                        InitialMenuActivity.gameServicesImpl.modifyTableGameStatusRoundBeginning(true);

                        dialog.dismiss();
                    }
                });

        return builder.create();
    }

    private String[] getSpellCardArray(List<CartaAccionHechizo> cartasAccionesHechizos){
        String[] hechizosArray;

        List<String> hechizos = new ArrayList<String>();
        for(CartaAccionHechizo cartaAccionHechizo: cartasAccionesHechizos){
            String informacionHechizo = cartaAccionHechizo.getNumero() + " - " + cartaAccionHechizo.getNombre() + " / " + cartaAccionHechizo.getNombreSecundario() + " (" + cartaAccionHechizo.getColor().toString() + ")";
            hechizos.add(informacionHechizo);
        }
        hechizosArray = hechizos.toArray(new String[hechizos.size()]);

        return hechizosArray;
    }


}

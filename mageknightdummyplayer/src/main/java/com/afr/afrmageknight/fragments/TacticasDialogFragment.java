package com.afr.afrmageknight.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.afr.afrmageknight.activities.InitialMenuActivity;
import com.afr.afrmageknight.model.CartaTactica;

import java.util.ArrayList;
import java.util.List;

public class TacticasDialogFragment extends DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        List<CartaTactica> cartasTacticasDisponibles;
        String titulo = "";

        if(InitialMenuActivity.gameServicesImpl.isDayRound()){
            cartasTacticasDisponibles = InitialMenuActivity.gameServicesImpl.getGameAvailableDayTacticsCards();
            titulo = "Tácticas de Día disponibles\n\nTáctica seleccionada a descartar";
        } else {
            cartasTacticasDisponibles = InitialMenuActivity.gameServicesImpl.getGameAvailableNightTacticsCards();
            titulo = "Tácticas de Noche disponibles\n\nTáctica seleccionada a descartar";
        }

        List<String> tacticas = new ArrayList<String>();
        for(CartaTactica cartaTactica: cartasTacticasDisponibles){
            String informacionTactica = cartaTactica.getNumeroOrden() + " - " + cartaTactica.getNombre();
            tacticas.add(informacionTactica);
        }
        String[] tacticasArray = tacticas.toArray(new String[tacticas.size()]);


        final ArrayList selectedItems = new ArrayList();  // Where we track the selected items

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Set the dialog title
        builder.setTitle(titulo)
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                /*.setMultiChoiceItems(tacticasArray, null,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (isChecked) {
                                    // If the user checked the item, add it to the selected items
                                    selectedItems.add(which);
                                } else if (selectedItems.contains(which)) {
                                    // Else, if the item is already in the array, remove it
                                    selectedItems.remove(Integer.valueOf(which));
                                }
                            }
                        })*/
                .setSingleChoiceItems(tacticasArray, 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                /*.setSingleChoiceItems(tacticasArray, null, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })*/
                // Set the action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK, so save the selectedItems results somewhere
                        // or return them to the component that opened the dialog

                    }
                });

        return builder.create();
    }
}

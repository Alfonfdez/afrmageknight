package com.afr.afrmageknight.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.afr.afrmageknight.activities.InitialMenuActivity;
import com.afr.afrmageknight.model.CartaTactica;

import java.util.List;

public class TacticsListDialogFragment extends DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String tituloDialogFragment = "Listado de Tácticas disponibles";
        String tacticasDisponibles = "";

        List<CartaTactica> cartasTacticasDiaDisponibles = InitialMenuActivity.gameServicesImpl.getGameAvailableDayTacticsCards();
        List<CartaTactica> cartasTacticasNocheDisponibles = InitialMenuActivity.gameServicesImpl.getGameAvailableNightTacticsCards();

        StringBuilder strTacticasDisponibles = new StringBuilder();
        strTacticasDisponibles.append("TÁCTICAS DE DÍA\n\n");

        for(CartaTactica cartaTactica : cartasTacticasDiaDisponibles){
            strTacticasDisponibles.append(cartaTactica.getNumeroOrden() + " - " + cartaTactica.getNombre()+"\n");
        }

        strTacticasDisponibles.append("\nTÁCTICAS DE NOCHE\n\n");

        for(CartaTactica cartaTactica : cartasTacticasNocheDisponibles){
            strTacticasDisponibles.append(cartaTactica.getNumeroOrden() + " - " + cartaTactica.getNombre()+"\n");
        }

        tacticasDisponibles = strTacticasDisponibles.toString();


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(tituloDialogFragment)
                .setMessage(tacticasDisponibles)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        return builder.create();

    }

}

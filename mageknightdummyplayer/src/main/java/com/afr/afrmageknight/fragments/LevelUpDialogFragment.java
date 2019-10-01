package com.afr.afrmageknight.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.ListView;

import com.afr.afrmageknight.activities.InitialMenuActivity;
import com.afr.afrmageknight.model.CartaTactica;
import com.afr.afrmageknight.model.FichaHabilidad;

import java.util.ArrayList;
import java.util.List;

public class LevelUpDialogFragment extends DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String tituloDialogFragment = "Fichas Habilidades JV disponibles";
        String fichasHabilidadJugadorVirtualDisponiblesEnSubidaNivel = "";
        String preguntaSubidaNivel = "";

        if(InitialMenuActivity.gameServicesImpl.isPlayerExperienceUpToTen()){ //Experiencia del Jugador de 10
            preguntaSubidaNivel = "\nYa tienes la máxima experiencia (Nivel "+InitialMenuActivity.gameServicesImpl.getGamePlayerExperience() + ")";
        } else{ //Experiencia del Jugador inferior a 10
            preguntaSubidaNivel = "\n¿Subes al Nivel "+InitialMenuActivity.gameServicesImpl.getGamePlayerExperiencePlusTwo()+"?";
        }

        if(InitialMenuActivity.gameServicesImpl.isPlayerExperienceLevelFourOrMore()){
            int experienciaJugador = InitialMenuActivity.gameServicesImpl.getGamePlayerExperience();
            List<FichaHabilidad> fichasHabilidadJugadorVirtualPorNivelJugador = InitialMenuActivity.gameServicesImpl.getGameSkillTokensDummyPlayerBasedOnPlayerExperience(experienciaJugador);

            StringBuilder strfichasHabilidadJugadorVirtualPorNivelJugador = new StringBuilder();
            int i = 1;

            for(FichaHabilidad fichaHabilidad : fichasHabilidadJugadorVirtualPorNivelJugador){
                strfichasHabilidadJugadorVirtualPorNivelJugador.append(i +") ");
                strfichasHabilidadJugadorVirtualPorNivelJugador.append(fichaHabilidad.getNombre() + " - " + fichaHabilidad.getHeroe().getNombre());
                strfichasHabilidadJugadorVirtualPorNivelJugador.append("\n");
                ++i;
            }

            fichasHabilidadJugadorVirtualDisponiblesEnSubidaNivel = strfichasHabilidadJugadorVirtualPorNivelJugador.toString();
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(tituloDialogFragment)
                .setMessage(fichasHabilidadJugadorVirtualDisponiblesEnSubidaNivel+preguntaSubidaNivel)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if(!InitialMenuActivity.gameServicesImpl.isPlayerExperienceUpToTen()){ // Experiencia del Jugador inferior a 10
                            int experienciaJugador = InitialMenuActivity.gameServicesImpl.getGamePlayerExperience();
                            int experienciaJugadorAumentada = experienciaJugador + 2;

                            InitialMenuActivity.gameServicesImpl.modifyTableGameStatusPlayerExperience(experienciaJugadorAumentada);
                        }

                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        return builder.create();
    }

}

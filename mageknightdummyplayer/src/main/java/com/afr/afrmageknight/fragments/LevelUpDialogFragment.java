package com.afr.afrmageknight.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.afr.afrmageknight.activities.InitialMenuActivity;
import com.afr.afrmageknight.model.FichaHabilidad;

import java.util.List;

public class LevelUpDialogFragment extends DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String tituloDialogFragment = "JV Fichas de Habilidades disponibles en la Oferta Común de Habilidades\n";
        String fichasHabilidadJugadorVirtualDisponiblesEnSubidaNivel = "";
        String preguntaSubidaNivel = "";

        if(InitialMenuActivity.gameServicesImpl.isPlayerExperienceUpToTen()){ //Experiencia del Jugador de 10
            preguntaSubidaNivel = "\n\nYa tienes la máxima experiencia (Nivel "+InitialMenuActivity.gameServicesImpl.getGamePlayerExperience() + ")";
        } else{ //Experiencia del Jugador inferior a 10
            preguntaSubidaNivel = "\n\n¿Quieres subir al Nivel "+InitialMenuActivity.gameServicesImpl.getGamePlayerExperiencePlusTwo()+"?";
        }

        if(InitialMenuActivity.gameServicesImpl.isPlayerExperienceLevelFourOrMore()){
            int experienciaJugador = InitialMenuActivity.gameServicesImpl.getGamePlayerExperience();
            List<FichaHabilidad> fichasHabilidadJugadorVirtualPorNivelJugador = InitialMenuActivity.gameServicesImpl.getGameSkillTokensDummyPlayerBasedOnPlayerExperience(experienciaJugador);

            StringBuilder strfichasHabilidadJugadorVirtualPorNivelJugador = new StringBuilder();
            int i = 1;

            strfichasHabilidadJugadorVirtualPorNivelJugador.append("Con tu subida de Nivel "+InitialMenuActivity.gameServicesImpl.getGamePlayerExperience() + ", puedes obtener:\n\n");

            for(FichaHabilidad fichaHabilidad : fichasHabilidadJugadorVirtualPorNivelJugador){
                strfichasHabilidadJugadorVirtualPorNivelJugador.append(i +") ");
                strfichasHabilidadJugadorVirtualPorNivelJugador.append(fichaHabilidad.getNombre() + " - " + fichaHabilidad.getHeroe().getNombre());
                strfichasHabilidadJugadorVirtualPorNivelJugador.append("\n");
                ++i;
            }

            fichasHabilidadJugadorVirtualDisponiblesEnSubidaNivel = strfichasHabilidadJugadorVirtualPorNivelJugador.toString();
        } else {
            fichasHabilidadJugadorVirtualDisponiblesEnSubidaNivel = "No podrás obtener una Ficha de Habilidad de la Oferta hasta que obtengas el Nivel 4\n\n";
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        if(!InitialMenuActivity.gameServicesImpl.isPlayerExperienceUpToTen()){ // Experiencia del Jugador inferior a 10
            builder.setTitle(tituloDialogFragment)
                    .setMessage(fichasHabilidadJugadorVirtualDisponiblesEnSubidaNivel+preguntaSubidaNivel)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            int experienciaJugador = InitialMenuActivity.gameServicesImpl.getGamePlayerExperience();
                            int experienciaJugadorAumentada = experienciaJugador + 2;

                            //Método para actualizar la columna 'EXPERIENCIA' de la tabla "PARTIDA_DATOS"
                            InitialMenuActivity.gameServicesImpl.modifyTableGameStatusPlayerExperience(experienciaJugadorAumentada);

                            // Insertar la información de la Ficha de Habilidad escogida aleatoriamente por el Jugador Virtual en el modo SOLITARIO
                            if(InitialMenuActivity.gameServicesImpl.isPlayerExperienceFromTwoToEight()){ // Experiencia del Jugador entre 2 y 8
                                FichaHabilidad fichaHabilidad = InitialMenuActivity.gameServicesImpl.getGameSkillTokenDummyPlayerByPlayerExperience(experienciaJugadorAumentada);
                                String informacionFichaHabilidadJugadorVirtual = InitialMenuActivity.gameServicesImpl.getGameInformationSkillTokenDummyPlayerSolitaireType(fichaHabilidad);

                                InitialMenuActivity.gameServicesImpl.insertTableGameSkillTokenInformation(informacionFichaHabilidadJugadorVirtual);
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
        } else{
            builder.setTitle(tituloDialogFragment)
                    .setMessage(fichasHabilidadJugadorVirtualDisponiblesEnSubidaNivel+preguntaSubidaNivel)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });

            return builder.create();
        }
    }
}

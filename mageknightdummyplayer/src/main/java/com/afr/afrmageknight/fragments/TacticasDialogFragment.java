package com.afr.afrmageknight.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.afr.afrmageknight.activities.GameActivity;
import com.afr.afrmageknight.activities.InitialMenuActivity;
import com.afr.afrmageknight.model.CartaTactica;

import java.util.ArrayList;
import java.util.List;

public class TacticasDialogFragment extends DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        List<CartaTactica> cartasTacticasDisponibles;
        final String[] tacticasArray;
        String titulo = "";

        if(InitialMenuActivity.gameServicesImpl.isDayRound()){  //Ronda de DÍA
            cartasTacticasDisponibles = InitialMenuActivity.gameServicesImpl.getGameAvailableDayTacticsCards();
            titulo = "Tácticas de Día disponibles\nTáctica seleccionada a descartar";
        } else {    //Ronda de NOCHE
            cartasTacticasDisponibles = InitialMenuActivity.gameServicesImpl.getGameAvailableNightTacticsCards();
            titulo = "Tácticas de Noche disponibles\nTáctica seleccionada a descartar";
        }

        List<String> tacticas = new ArrayList<String>();
        for(CartaTactica cartaTactica: cartasTacticasDisponibles){
            String informacionTactica = cartaTactica.getNumeroOrden() + " - " + cartaTactica.getNombre();
            tacticas.add(informacionTactica);
        }
        tacticasArray = tacticas.toArray(new String[tacticas.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(titulo)
                .setSingleChoiceItems(tacticasArray, -1, new DialogInterface.OnClickListener() {
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

                        //Método para actualizar la carta Táctica seleccionada por el Jugador a 'DESCARTADA'=1
                        InitialMenuActivity.gameServicesImpl.modifyTableGameTacticCardAvailabilityByName(nombreCartaTactica, true);

                        //Método para descartar al azar una carta Táctica después de la selección del Jugador en el modo SOLITARIO
                        discardGameTacticCardRandomlyAfterPlayerTacticCardSelectionInSolitaireGame();

                        if(!InitialMenuActivity.gameServicesImpl.isFirstRound()){ //Rondas 2, 3, 4, 5, 6
                            //Barajar cartas del Jugador Virtual y actualizar todas las cartas a NO descartadas
                            shuffleDummyPlayerCardsAndUpdateToAvailable();
                        }

                        //Crear método para convertir RONDA_ESTADO_INICIO = 0
                        InitialMenuActivity.gameServicesImpl.modifyTableGameStatusRoundBeginning(false);

                        dialog.dismiss();
                    }
                });

        return builder.create();
    }

    //Métodos privados
    private void shuffleDummyPlayerCardsAndUpdateToAvailable(){
        List<Integer> cartasJugadorVirtualPorNumero = InitialMenuActivity.gameServicesImpl.getGameCardsDummyPlayerByNumber();
        InitialMenuActivity.gameServicesImpl.getShuffledGameCardsDummyPlayerByNumber(cartasJugadorVirtualPorNumero);

        InitialMenuActivity.gameServicesImpl.modifyTableGameShuffledCardsDummyPlayer(cartasJugadorVirtualPorNumero, false);
    }

    private void discardGameTacticCardRandomlyAfterPlayerTacticCardSelectionInSolitaireGame(){
        if(InitialMenuActivity.gameServicesImpl.isGameTypeSolitaire()){
            if(InitialMenuActivity.gameServicesImpl.isDayRound()){  //Ronda de DÍA
                List<CartaTactica> cartasTacticasDisponiblesJuego = InitialMenuActivity.gameServicesImpl.getGameAvailableDayTacticsCards();
                CartaTactica cartaTacticaAleatoriaDisponible = InitialMenuActivity.gameServicesImpl.getGameAvailableRandomTacticCard(cartasTacticasDisponiblesJuego);

                //Método para actualizar la carta Táctica seleccionada al azar por el Jugador VIrtual a 'DESCARTADA'=1
                InitialMenuActivity.gameServicesImpl.modifyTableGameTacticCardAvailabilityByName(cartaTacticaAleatoriaDisponible.getNombre(), true);
            } else{ //Ronda de NOCHE
                List<CartaTactica> cartasTacticasDisponiblesJuego = InitialMenuActivity.gameServicesImpl.getGameAvailableNightTacticsCards();
                CartaTactica cartaTacticaAleatoriaDisponible = InitialMenuActivity.gameServicesImpl.getGameAvailableRandomTacticCard(cartasTacticasDisponiblesJuego);

                //Método para actualizar la carta Táctica seleccionada al azar por el Jugador VIrtual a 'DESCARTADA'=1
                InitialMenuActivity.gameServicesImpl.modifyTableGameTacticCardAvailabilityByName(cartaTacticaAleatoriaDisponible.getNombre(), true);
            }
        }
    }

    private void discardGameTacticCardRandomlyBeforePlayersTacticCardSelectionInCooperative(){

    }

    private void availableSameGameTacticCardAfterPlayersTacticCardSelectionInCooperative(){

    }
}

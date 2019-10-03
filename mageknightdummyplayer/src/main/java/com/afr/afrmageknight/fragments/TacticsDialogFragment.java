package com.afr.afrmageknight.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.ListView;

import com.afr.afrmageknight.activities.InitialMenuActivity;
import com.afr.afrmageknight.model.CartaTactica;

import java.util.ArrayList;
import java.util.List;

public class TacticsDialogFragment extends DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //I - Declarar variables
        List<CartaTactica> cartasTacticasDisponibles;
        String tituloDialogFragment = "";

        //Si la partida es en COOPERATIVO descartaremos 1 carta Táctica aleatoriamente, que volverá a estar disponible después de la selección de los Jugadores
        final CartaTactica cartaTacticaDescartadaPartidaCooperativo = discardGameTacticCardRandomlyBeforePlayersTacticCardSelectionInCooperative();

        //Obtener todas las cartas Tácticas disponibles de la Partida
        if(InitialMenuActivity.gameServicesImpl.isDayRound()){  //Ronda de DÍA
            cartasTacticasDisponibles = InitialMenuActivity.gameServicesImpl.getGameAvailableDayTacticsCards();
            tituloDialogFragment = "Tácticas de Día disponibles\nTáctica seleccionada a descartar";
        } else {    //Ronda de NOCHE
            cartasTacticasDisponibles = InitialMenuActivity.gameServicesImpl.getGameAvailableNightTacticsCards();
            tituloDialogFragment = "Tácticas de Noche disponibles\nTáctica seleccionada a descartar";
        }

        final String[] tacticasArrayFinal = getTacticCardArray(cartasTacticasDisponibles);


        //AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(tituloDialogFragment)
                .setSingleChoiceItems(tacticasArrayFinal, 0, new DialogInterface.OnClickListener() {
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
        if(InitialMenuActivity.gameServicesImpl.isGameTypeSolitaire()){ //SOLITARIO
            if(InitialMenuActivity.gameServicesImpl.isDayRound()){  //Ronda de DÍA
                List<CartaTactica> cartasTacticasDisponiblesJuego = InitialMenuActivity.gameServicesImpl.getGameAvailableDayTacticsCards();
                CartaTactica cartaTacticaAleatoriaDisponible = InitialMenuActivity.gameServicesImpl.getGameAvailableRandomTacticCard(cartasTacticasDisponiblesJuego);

                //Método para actualizar la carta Táctica seleccionada al azar por el Jugador VIrtual a 'DESCARTADA'=1
                InitialMenuActivity.gameServicesImpl.modifyTableGameTacticCardAvailabilityByName(cartaTacticaAleatoriaDisponible.getNombre(), true);

                //Insertar la información de la Ronda sobre la carta Táctica escogida aleatoriamente por el Jugador Virtual en el modo SOLITARIO
                String informacionPartida = InitialMenuActivity.gameServicesImpl.getGameInformationTacticCardDummyPlayerSolitaireType(cartaTacticaAleatoriaDisponible);
                InitialMenuActivity.gameServicesImpl.insertTableGameRoundInformation(informacionPartida);
            } else{ //Ronda de NOCHE
                List<CartaTactica> cartasTacticasDisponiblesJuego = InitialMenuActivity.gameServicesImpl.getGameAvailableNightTacticsCards();
                CartaTactica cartaTacticaAleatoriaDisponible = InitialMenuActivity.gameServicesImpl.getGameAvailableRandomTacticCard(cartasTacticasDisponiblesJuego);

                //Método para actualizar la carta Táctica seleccionada al azar por el Jugador VIrtual a 'DESCARTADA'=1
                InitialMenuActivity.gameServicesImpl.modifyTableGameTacticCardAvailabilityByName(cartaTacticaAleatoriaDisponible.getNombre(), true);

                //Insertar la información de la Ronda sobre la carta Táctica escogida aleatoriamente por el Jugador Virtual en el modo SOLITARIO
                String informacionPartida = InitialMenuActivity.gameServicesImpl.getGameInformationTacticCardDummyPlayerSolitaireType(cartaTacticaAleatoriaDisponible);
                InitialMenuActivity.gameServicesImpl.insertTableGameRoundInformation(informacionPartida);
            }
        }
    }

    private CartaTactica discardGameTacticCardRandomlyBeforePlayersTacticCardSelectionInCooperative(){
        if(!InitialMenuActivity.gameServicesImpl.isGameTypeSolitaire()){ //COOPERATIVO
            if(InitialMenuActivity.gameServicesImpl.isDayRound()){  //Ronda de DÍA
                List<CartaTactica> cartasTacticasDisponiblesJuego = InitialMenuActivity.gameServicesImpl.getGameAvailableDayTacticsCards();
                CartaTactica cartaTacticaAleatoriaDisponible = InitialMenuActivity.gameServicesImpl.getGameAvailableRandomTacticCard(cartasTacticasDisponiblesJuego);

                //Método para actualizar la carta Táctica seleccionada al azar por el Jugador VIrtual a 'DESCARTADA'=1
                InitialMenuActivity.gameServicesImpl.modifyTableGameTacticCardAvailabilityByName(cartaTacticaAleatoriaDisponible.getNombre(), true);

                //Insertar la información de la Ronda sobre la carta Táctica escogida aleatoriamente por el Jugador Virtual en el modo COOPERATIVO
                String informacionPartida = InitialMenuActivity.gameServicesImpl.getGameInformationTacticCardDummyPlayerCooperativeType(cartaTacticaAleatoriaDisponible);
                InitialMenuActivity.gameServicesImpl.insertTableGameRoundInformation(informacionPartida);

                return cartaTacticaAleatoriaDisponible;
            } else{ //Ronda de NOCHE
                List<CartaTactica> cartasTacticasDisponiblesJuego = InitialMenuActivity.gameServicesImpl.getGameAvailableNightTacticsCards();
                CartaTactica cartaTacticaAleatoriaDisponible = InitialMenuActivity.gameServicesImpl.getGameAvailableRandomTacticCard(cartasTacticasDisponiblesJuego);

                //Método para actualizar la carta Táctica seleccionada al azar por el Jugador VIrtual a 'DESCARTADA'=1
                InitialMenuActivity.gameServicesImpl.modifyTableGameTacticCardAvailabilityByName(cartaTacticaAleatoriaDisponible.getNombre(), true);

                //Insertar la información de la Ronda sobre la carta Táctica escogida aleatoriamente por el Jugador Virtual en el modo COOPERATIVO
                String informacionPartida = InitialMenuActivity.gameServicesImpl.getGameInformationTacticCardDummyPlayerCooperativeType(cartaTacticaAleatoriaDisponible);
                InitialMenuActivity.gameServicesImpl.insertTableGameRoundInformation(informacionPartida);

                return cartaTacticaAleatoriaDisponible;
            }
        }

        return null;
    }

    private void availableSameGameTacticCardAfterPlayersTacticCardSelectionInCooperative(CartaTactica cartaTacticaDescartadaPartidaCooperativo){
        if(!InitialMenuActivity.gameServicesImpl.isGameTypeSolitaire()){ //COOPERATIVO
            //Método para actualizar la carta Táctica seleccionada al azar anteriormente por el Jugador Virtual a 'DESCARTADA'=0 (Carta disponible)
            InitialMenuActivity.gameServicesImpl.modifyTableGameTacticCardAvailabilityByName(cartaTacticaDescartadaPartidaCooperativo.getNombre(), false);
        }
    }

    private String[] getTacticCardArray(List<CartaTactica> cartasTacticasDisponibles){
        String[] tacticasArray;

        List<String> tacticas = new ArrayList<String>();
        for(CartaTactica cartaTactica: cartasTacticasDisponibles){
            String informacionTactica = cartaTactica.getNumeroOrden() + " - " + cartaTactica.getNombre();
            tacticas.add(informacionTactica);
        }
        tacticasArray = tacticas.toArray(new String[tacticas.size()]);

        return tacticasArray;
    }
}

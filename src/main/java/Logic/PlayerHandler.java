package Logic;

import MonopolyJunior.*;

public class PlayerHandler {

    private final int START_MONEY = 31;
    private final int MAX_BOOTHS = 12;
    private final int MIN_BOOTHS = 10;

    private Player[] players;
    private Player currentPlayer;


    public PlayerHandler(int numPlayers){
        players = new Player[numPlayers];

        for (int player = 0; player < numPlayers; player++) {
            players[player] = new Player("Player " + (player + 1));
            players[player].setupStartBalance(START_MONEY);
            players[player].setBooths( (numPlayers > 2) ? MIN_BOOTHS : MAX_BOOTHS );
        }

        currentPlayer = players[0]; // Default change later

        System.out.println("Players Initialized");
    }

    public void changeTurn(){
        // Get index of current player
        int playerIndex = java.util.Arrays.asList(players).indexOf(currentPlayer);

        if (playerIndex >= (players.length-1)){
            currentPlayer = players[0];
        }else{
            currentPlayer = players[playerIndex +1];
        }

        System.out.println("Changed player from: " + players[playerIndex].getName() + " to: " +
                "" + players[ (playerIndex==players.length-1) ? 0 : playerIndex+1 ].getName());
    }

    public Player getCurrentPlayer(){
        System.out.println("Current Player: " + currentPlayer.getName());

        return currentPlayer;
    }

}


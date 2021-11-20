package Logic;

import Board.*;
import MonopolyJunior.*;
import Utilities.Debug;

import java.util.ArrayList;
import java.util.Scanner;

public class GameController {

    ActionHandler actionHandler;
    PositionHandler positionHandler;
    Board board = new Board();
    Deck deck = new Deck();//never used objekt
    Die die = new Die();

    private Player[] players;
    private Player currentPlayer;

    boolean gameOver = false;
    boolean extraTurn = false;

    int BOARD_SIZE = board.getAllSquares().length;

    public void setupGame(int amountOfPlayers){
        setupPlayers(amountOfPlayers);
        positionHandler = new PositionHandler(players, BOARD_SIZE);
        actionHandler = new ActionHandler(this, board, positionHandler);
    }

    public void playGame(){
        do {
            takeTurn();
        }while (!gameOver);
    }

    public void takeTurn(){

        // Print players turn and stats
        String name = currentPlayer.getName();
        int balance = currentPlayer.getBalance();
        int position = currentPlayer.getPosition();

        Debug.println("\n" + name + ":\t" + "Pos " + position + ", \t $" + balance + "\t" + "booths " + currentPlayer.getBoothsOnHand());

        // Get action from player
        Debug.print("Please press ENTER to roll");
        Debug.strInput();

        // Roll die, get value.
        die.roll();
        int spacesToMove = die.getFaceValue();
        Debug.println("You rolled: " + spacesToMove);

        // Move player position
        positionHandler.movePlayer(currentPlayer, spacesToMove);

        // Get new position
        int currentPosition = currentPlayer.getPosition();

        // Do action on that field
        actionHandler.doFieldAction(currentPlayer, currentPosition);

        // Lose check - Only for currentPlayer, since it's not possible for others to be a zero now.
        loseCheck();

        // Change turn
        if (!extraTurn) changeTurn();
        extraTurn = false;

    }

    private void loseCheck(){ // On currentplayer
        if (currentPlayer.getBalance() <= 0){
            // Found a loser
            findWinner();
            gameOver = true;
        }
    }
    // Maybe move into playerHandler???
    private void findWinner(){
        Player[] winners = new Player[players.length-1]; // need to handle a tie with one less player than playing.
        winners[0] = new Player(" "); // Just a startpoint to go out from
        winners[0].setBalance(-1);

        for (Player player : players){
            if (player.getBalance() > winners[0].getBalance()){ // This player has a higher balance
                // Remove all previous winners from the array.
                winners = new Player[players.length-1];
                winners[0] = player;
            }
            else if (player.getBalance() == winners[0].getBalance()){ // Tie case
                for (int i = 0; i < players.length-1; i++) {
                    if (winners[i] == null){ // Look for next place in array to place tied player
                        winners[i] = player;
                        break;
                    }
                }
            }
        }

        // Announce winner(s)
        Debug.println("\n We have a winner!!!!");
        for (int i = 0; i < winners.length; i++) {
            if (winners[i] != null){
                Debug.println(winners[i].getName() + ", with a balance of " + winners[i].getBalance());
            }
        }
    }


    // *** Player Handling *** //
    
    private void setupPlayers(int numPlayers){
        final int START_MONEY = 31;
        final int MAX_BOOTHS = 12;
        final int MIN_BOOTHS = 10;

        players = new Player[numPlayers];

        for (int player = 0; player < numPlayers; player++) {
            players[player] = new Player("Player " + (player + 1));
            players[player].setupStartBalance(START_MONEY);
            players[player].setBooths( (numPlayers > 2) ? MIN_BOOTHS : MAX_BOOTHS );
        }

        currentPlayer = players[0]; // Default change later

        Debug.println("~Players Initialized~");
    }
    
    private void changeTurn(){
        // Get index of current player
        int playerIndex = java.util.Arrays.asList(players).indexOf(currentPlayer);

        if (playerIndex >= (players.length-1)){
            currentPlayer = players[0];
        }else{
            currentPlayer = players[playerIndex +1];
        }

        Debug.println("~Changed player from: " + players[playerIndex].getName() + " to: " +
                "" + players[ (playerIndex==players.length-1) ? 0 : playerIndex+1 ].getName() + "~");
    }

    public void giveExtraTurn(){
        extraTurn = true;
    }
}
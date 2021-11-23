package Logic;

import Board.*;
import MonopolyJunior.*;
import Utilities.Debug;

import java.util.Scanner;

public class GameController {

    Scanner s = new Scanner(System.in);

    ActionHandler actionHandler;
    PositionHandler positionHandler;
    Board board = new Board();
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

    private void takeTurn(){

        // Print players turn and stats
        String name = currentPlayer.getName();
        int balance = currentPlayer.getBalance();
        int position = currentPlayer.getPosition();

        Debug.println("\n" + name + ":\t" + "Pos " + position + ", \t $" + balance + "\t" +
                "booths " + currentPlayer.getBoothsOnHand());

        // Get action from player
        Debug.print("Please press ENTER to roll");
        s.nextLine();

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
        if (gameOver){ return; }

        // Change turn
        if (!extraTurn)
            changeTurn();

        extraTurn = false;

    }

    private void loseCheck(){ // On currentplayer
        if (currentPlayer.getBalance() <= 0){
            // Found a loser
            findWinner();
            gameOver = true;
        }
    }

    private void findWinner(){
        int amountOfWinners = 0;
        int highestAmountOfMoney = 0;
        StringBuilder announce = new StringBuilder();
        //finds the hightest amount of cash in a single players hand, and how many have that amount.
        for (Player p : players){
            if (p.getBalance() > highestAmountOfMoney) {
                amountOfWinners = 1;
                highestAmountOfMoney = p.getBalance();
            }
            else if (p.getBalance() == highestAmountOfMoney){
                amountOfWinners++;
            }
        }
        //construct the winning message
        if (amountOfWinners > 1)
            announce.append("the game were tie between: ");
        else
            announce.append("the winner is: ");
        for (Player p : players){
            if (p.getBalance() == highestAmountOfMoney){
                announce.append(p.getName()).append(" ");
            }
        }
        //announce the winner
        System.out.println(announce);
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

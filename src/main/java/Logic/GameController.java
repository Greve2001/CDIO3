package Logic;

import Board.*;
import MonopolyJunior.*;

import java.util.Scanner;

public class GameController {

    SquareActionHandler squareActionHandler;
    PositionHandler positionHandler;
    Board board = new Board();
    Deck deck = new Deck();
    Die die = new Die();

    private Player[] players;
    private Player currentPlayer;

    boolean gameOver = false;

    private final int START_MONEY = 31;
    private final int MAX_BOOTHS = 12;
    private final int MIN_BOOTHS = 10;
    int BOARD_SIZE = board.getAllSquares().length;

    // Ekstra, remove later
    Scanner input = new Scanner(System.in);

    public void setupGame(){
        squareActionHandler = new SquareActionHandler(board);
        positionHandler = new PositionHandler(players, board.getAllSquares().length);

    }

    public void startGame(){
        do {
            takeTurn();
        }while (!gameOver);
    }

    public void takeTurn(){
        // Get action from player
        System.out.print("Please press enter");
        input.nextLine();

        // Roll die, get value.
        die.roll();
        int spacesToMove = die.getFaceValue();
        System.out.println("You rolled: " + spacesToMove);

        // Move player position
        positionHandler.movePlayer(currentPlayer, spacesToMove);

        // Get new position
        int currentPosition = currentPlayer.getPosition();

        // Do action on that field
        squareActionHandler.doFieldAction(currentPlayer, currentPosition);

        // Lose check - Only for currentPlayer, since it's not possible for others to be a zero now.
        loseCheck();

        // Change turn
        changeTurn();

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
        Player winner = players[0]; // Just to have a starting point

        for (Player player : players){
            if (winner.getBalance() > player.getBalance() && player != winner){
                winner = player;
            }
            else if (winner.getBalance() == player.getBalance() && player != winner){
                System.out.println("Its a tie");
            }
        }

        // Announce winner
        System.out.println("We have a winner!!!!");
        System.out.println(winner.getName() + " with a balance of " + winner.getBalance());
    }

    
    
    // *** Player Handling *** // 
    
    private void setupPlayers(int numPlayers){
        players = new Player[numPlayers];

        for (int player = 0; player < numPlayers; player++) {
            players[player] = new Player("Player " + (player + 1));
            players[player].setupStartBalance(START_MONEY);
            players[player].setBooths( (numPlayers > 2) ? MIN_BOOTHS : MAX_BOOTHS );
        }

        currentPlayer = players[0]; // Default change later

        System.out.println("Players Initialized");
    }
    
    private void changeTurn(){
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
}

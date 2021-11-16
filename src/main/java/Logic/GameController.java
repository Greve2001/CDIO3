package Logic;

import Board.*;
import MonopolyJunior.*;

import java.util.Scanner;


public class GameController {

    PlayerHandler playerHandler;
    SquareActionHandler squareActionHandler;
    PositionHandler positionHandler;
    Board board = new Board();
    Deck deck = new Deck();
    Die die = new Die();

    Player currentPlayer;
    boolean gameOver = false;
    int BOARD_SIZE = board.getAllSquares().length;

    // Ekstra, remove later
    Scanner input = new Scanner(System.in);

    public void setupGame(){
        playerHandler = new PlayerHandler(4); // Change default when using GUI
        squareActionHandler = new SquareActionHandler(board);
        positionHandler = new PositionHandler(playerHandler.getPlayers(), board.getAllSquares().length);

    }

    public void startGame(){
        do {
            takeTurn();
        }while (!gameOver);
    }

    public void takeTurn(){
        // Get all new relevant information
        currentPlayer = playerHandler.getCurrentPlayer();

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
        int currentPosition = playerHandler.getCurrentPlayer().getPosition();

        // Do action on that field
        squareActionHandler.doFieldAction(currentPlayer, currentPosition);

        // Lose check - Only for currentPlayer, since it's not possible for others to be a zero now.
        loseCheck();

        // Change turn
        playerHandler.changeTurn();

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
        Player[] players = playerHandler.getPlayers();

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
}

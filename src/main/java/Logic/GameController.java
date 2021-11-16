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
    int BOARD_SIZE = board.getAllSquares().length;

    // Ekstra, remove later
    Scanner input = new Scanner(System.in);

    public void setupGame(){
        playerHandler = new PlayerHandler(4); // Change default when using GUI
        squareActionHandler = new SquareActionHandler(board);
        positionHandler = new PositionHandler(playerHandler.getPlayers());

    }

    public void startGame(){
        do {
            takeTurn();
        }while (true);
    }

    public void takeTurn(){
        // Get all new relevant information
        currentPlayer = playerHandler.getCurrentPlayer();

        // Get action from player
        System.out.print("Action please");
        input.nextLine();

        // Roll die, get value.
        die.roll();
        int spacesToMove = die.getFaceValue();

        // Move player position
        positionHandler.movePlayer(currentPlayer, spacesToMove);
        // Get new position
        int currentPosition = playerHandler.getCurrentPlayer().getPosition();

        // Do action on that field
        squareActionHandler.doFieldAction(currentPlayer, currentPosition);

        // Win check

        // Change turn
        playerHandler.changeTurn();

    }
}

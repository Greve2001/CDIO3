package Logic;

import Board.*;
import MonopolyJunior.*;

import java.util.Scanner;


public class GameController {

    PlayerHandler playerHandler;
    Board board = new Board();
    Deck deck = new Deck();
    Die die = new Die();

    Player currentPlayer;
    int BOARD_SIZE = board.getAllSquares().length;

    // Ekstra, remove later
    Scanner input = new Scanner(System.in);

    public void setupGame(){
        playerHandler = new PlayerHandler(4); // Change default when using GUI

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
        int faceValue = die.getFaceValue();

        // Move player, do action on that field

        // Change turn
        playerHandler.changeTurn();

    }
}

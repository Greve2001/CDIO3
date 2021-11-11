import Board.Board;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import gui_main.GUI;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MonopolyJunior {
    private final int MAX_NR_OF_PLAYERS = 4;
    private final int START_MONEY = 31;
    private final int BOARD_SIZE = 32;
    private final int DECK_SIZE = 24;
    private final int MOVING_PAST_START = 2;

    private Player[] players;
    private boolean win_condition;
    private final Die die = new Die();
    private final Board board = new Board();
    private final Deck pile = new Deck(DECK_SIZE);
    private Player currentPlayer;
    private boolean hasWinner = false;
    private final Scanner input = new Scanner(System.in);
    private MJGui gui;

    public MonopolyJunior(int numOfPlayers, MJGui gui){
        this.gui = gui;
        players = new Player[numOfPlayers];

        for (int i = 0 ; i < numOfPlayers && i < MAX_NR_OF_PLAYERS ; i++){
            players[i] = new Player();
            gui.setPlayerNameAndColor(i+1, START_MONEY);
        }
        currentPlayer = players[0];
    }

    public void giveStartMoney() { //starts with 31 dollars
        for (Player player : this.players) {
            player.setPlayerAccount(START_MONEY);
        }
    }

    public void takeTurn() {
        //todo logic
        //roll die and update position
        die.roll();
        gui.showRoll(die);
        updatePosition();

        //need to create method to handle all the different fields that the player can land on.
        //what to do if landing on an amusement
        //what to do if landing on a rail road
        //what to do if landing on 'chance field'
        //what to do if landing on or passing start
        //what to do if landing on 'go to cafe'



        //need to finish with an update to the win condition
        //something like 'current money' < 'money they have to pay'

        //check if a player has no money
        if (currentPlayer.getAccount().getBalance() == 0)
            hasWinner = true;
    }

    public void changePlayer(){
        int playerIndex = java.util.Arrays.asList(players).indexOf(currentPlayer);
        if (playerIndex == players.length-1)
            currentPlayer = players[0];
        else
            currentPlayer = players[playerIndex + 1];

        gui.changePlayer(playerIndex);
    }

    public boolean gethasWinner(){
        return hasWinner;
    }

    public void updatePosition(){
        if (die.getFaceValue() + currentPlayer.getToken().getPosition() > BOARD_SIZE) {
            currentPlayer.getToken().setPosition(die.getFaceValue() + currentPlayer.getToken().getPosition() - BOARD_SIZE);
            currentPlayer.getAccount().setBalance(currentPlayer.getAccount().getBalance() + MOVING_PAST_START);
        }
        else
            currentPlayer.getToken().setPosition(die.getFaceValue() + currentPlayer.getToken().getPosition());
    }

    public void decideAndAnnounceWinner(){

    }
}
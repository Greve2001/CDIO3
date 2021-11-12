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
    private final Die die = new Die();
    private final Board board = new Board();
    private final Deck pile = new Deck(DECK_SIZE);
    private Player currentPlayer;
    private boolean hasWinner = false;
    private final Scanner input = new Scanner(System.in);
    private MJGui gui;

    public MonopolyJunior(int numOfPlayers){
        setupGame();
        playGame();
    }

    public void setupGame(){
        // Evt lav getAllPlayers method to ask for players
        initalizePlayers(4);
        giveStartMoney();
    }

    public void playGame(){
        do {
            takeTurn();
        } while (!hasWinner);
    }

    
    public void initalizePlayers(int numOfPlayers){
        players = new Player[numOfPlayers];
        for (int i = 0 ; i < numOfPlayers && i < MAX_NR_OF_PLAYERS ; i++){ // Maybe remove && state and make it try catch to inform players
            players[i] = new Player();
        }
        currentPlayer = players[0]; // Set starting player. // Upgrade feature
    }

    public void giveStartMoney() { //starts with 31 dollars
        for (Player player : this.players) {
            player.setupStartBalance(START_MONEY);
        }
    }

    public void takeTurn() {
        // Might ask for player action
        die.roll();
        updatePosition(die.getFaceValue());

        // getField(), do action
        String typeofField = ""; // Place holder
        switch (typeofField){
            case "Amusement" ->  {
                /* field.getBooth
                if (field.getBooth == null){
                    int boothPrice = field.getPrice;
                    pay(currentPlayer, boothPrice);
                    Board.addBooth(currentPlayer, currentPlayer.getPosition());
                }
                if field.getBooth != null{
                    pay(field.getBooth.ownedBy, field.getCost)
                    // In pay method, check paymentPossible()
                }
                */
            }
            default -> {

            }
        }


        //need to create method to handle all the different fields that the player can land on.
        //what to do if landing on an amusement
        //what to do if landing on a rail road
        //what to do if landing on 'chance field'
        //what to do if landing on or passing start
        //what to do if landing on 'go to cafe'



        //need to finish with an update to the win condition
        //something like 'current money' < 'money they have to pay'

        //check if a player has no money
        if (currentPlayer.getBalance() == 0)
            hasWinner = true;
    }

    public void changePlayer(){
        int playerIndex = java.util.Arrays.asList(players).indexOf(currentPlayer);
        if (playerIndex == players.length-1)
            currentPlayer = players[0];
        else
            currentPlayer = players[playerIndex + 1];
    }

    public boolean gethasWinner(){
        return hasWinner;
    }

    public void updatePosition(int moveSpaces){
        int prevPos = currentPlayer.getPosition();
        int endPos = prevPos + moveSpaces;
        if (endPos >= BOARD_SIZE)
            currentPlayer.setPosition(endPos - BOARD_SIZE);
        else
            currentPlayer.setPosition(endPos);
        hasPassStart(prevPos, endPos, false);
    }

    public void hasPassStart(int prevPos, int endPos, boolean goingToRestrooms){ //
        if (goingToRestrooms) return;

        if (endPos > BOARD_SIZE || prevPos == 0 || endPos < prevPos) { // Maybe get the zero form fieldtype GO position.
            // Give passing start money
            currentPlayer.updateBalance(MOVING_PAST_START);
        }
    }

    public void pay(Player from, int amount){
        from.updateBalance(amount);
    }

    public void pay(Player from, Player to, int amount){
        if (paymentPossible(from , amount))
            to.updateBalance(amount);
        else
            to.updateBalance(from.getBalance());
        from.updateBalance(amount);
    }

    public boolean paymentPossible(Player player, int amount){
        if (player.getBalance() > amount)
            return true;
        else {
            this.hasWinner = true;
            return false;
        }
    }

    public void decideAndAnnounceWinner(){

    }
}
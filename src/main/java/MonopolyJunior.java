import Board.Board;

import java.util.Scanner;

public class MonopolyJunior {
    private final int MAX_NR_OF_PLAYERS = 4;
    private final int START_MONEY = 31;
    private final int BOARD_SIZE = 40;
    private final int DECK_SIZE = 16;

    private Player[] players = new Player[MAX_NR_OF_PLAYERS];
    private boolean win_condition;
    private final Die die = new Die();
    private final Board board = new Board(BOARD_SIZE);
    private final Pile pile = new Pile(DECK_SIZE);
    private Player currentPlayer;
    private boolean hasWinner = false;
    private final Scanner input = new Scanner(System.in);


    public MonopolyJunior(int numOfPlayers){
        for (int i = 0 ; i < numOfPlayers ; i++){
            players[i] = new Player();
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
        updatePosition();

        //need to create method to handle all the different fields that the player can land on.
        //what to do if landing on an amusement
        //what to do if landing on a rail road
        //what to do if landing on 'chance field'
        //what to do if landing on or passing start
        //what to do if landing on 'go to cafe'



        //need to finish with an update to the win condition
        //something like 'current money' < 'money they have to pay'

    }

    public void changePlayer(){
        int playerIndex = java.util.Arrays.asList(players).indexOf(currentPlayer);
        if (playerIndex == players.length-1)
            currentPlayer = players[0];
        else
            currentPlayer = players[playerIndex + 1];
    }

    public boolean isWin_condition(){
        if (this.hasWinner)
            return true;
        else
            return false;
    }

    public void updatePosition(){
        if (die.getFaceValue() + currentPlayer.getToken().getPosition() > BOARD_SIZE) {
            currentPlayer.getToken().setPosition(die.getFaceValue() + currentPlayer.getToken().getPosition() - BOARD_SIZE);
            currentPlayer.getAccount().setBalance(currentPlayer.getAccount().getBalance() + 2);
        }
        else
            currentPlayer.getToken().setPosition(die.getFaceValue() + currentPlayer.getToken().getPosition());
    }

    public void updateBalance(int update){
        if (update > 0) {
            currentPlayer.getAccount().setBalance(currentPlayer.getAccount().getBalance() + update);
        }
        else{
            if (currentPlayer.getAccount().getBalance() < Math.abs(update)){
                currentPlayer.getAccount().setBalance(0);
                hasWinner = true;
            }
            else{
                currentPlayer.getAccount().setBalance(currentPlayer.getAccount().getBalance() - update);
            }

        }
    }

}


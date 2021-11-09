import Board.Board;

import java.util.Scanner;

public class MonopolyJunior {
    private final int MAX_NR_OF_PLAYERS = 4;
    private final int START_MONEY = 31;

    private final Player[] players = new Player[MAX_NR_OF_PLAYERS];
    private boolean win_condition;
    private final Die die = new Die();;
    private final Board board = new Board(40);
    private final Pile pile = new Pile(16);
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
        for (Player p : this.players) {
            p.setPlayerAccount(START_MONEY);
        }
    }

    public boolean isWin_condition(){

    }

}

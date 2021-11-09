import Board.Board;

import java.util.Scanner;

public class MonopolyJunior {
    private final Die die;
    private final Board board;
    private final Pile pile;
    private final Player[] players;
    private Player currentPlayer;
    private boolean hasWinner = false;
    private final Scanner input;


    public MonopolyJunior(int numOfPlayers){
        players = new Player[numOfPlayers];
        die = new Die();
        board = new Board(40);
        pile = new Pile(16);
        input = new Scanner(System.in);

        for (int i = 0 ; i < numOfPlayers ; i++){
            players[i] = new Player();
        }
        currentPlayer = players[0];
    }

    public void playGame() {
        do {
            takeTurn(currentPlayer);
        }while(!hasWinner);
    }

    private void takeTurn(Player currentPlayer) {

    }
}

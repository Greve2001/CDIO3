package Logic;

import Board.*;
import MonopolyJunior.*;
import Utilities.Debug;
import Utilities.Language;

public class GameController {

    private ActionHandler actionHandler;
    PositionHandler positionHandler;
    Board board = new Board();
    private Die die = new Die();

    private Player[] players;
    private Player currentPlayer;

    boolean gameOver = false;
    boolean extraTurn = false;

    final int START_MONEY = 31;
    final int MAX_BOOTHS = 12;
    final int MIN_BOOTHS = 10;

    int BOARD_SIZE = board.getAllSquares().length;

    public void setupGame(int amountOfPlayers){
        new GUIController2(board.getAllSquares());

        GUIController2.createPlayers(START_MONEY);
        String[] playerNames = GUIController2.getPlayers();
        setupPlayers(playerNames);

        positionHandler = new PositionHandler(players, BOARD_SIZE, ((Go) board.getSquare(1)).getAmount());
        actionHandler = new ActionHandler(this, board, positionHandler);

        // Intialises gui with balances
        for (Player p : players){
            GUIController2.setPlayerBalance(p, p.getBalance());
        }

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
        GUIController2.getPlayerAction(currentPlayer, ", " + Language.getText("pleaseRoll"));
        //s.nextLine(); //TODO SLET!

        // Roll die, get value.
        die.roll();
        int spacesToMove = die.getFaceValue();
        Debug.println("You rolled: " + spacesToMove);
        GUIController2.showDie(spacesToMove);

        // Move player position
        positionHandler.movePlayer(currentPlayer, spacesToMove);

        // Get new position
        int currentPosition = currentPlayer.getPosition();
        GUIController2.movePlayer(currentPlayer, currentPosition);

        // Do action on that field
        actionHandler.doFieldAction(currentPlayer, currentPosition);

        // GUI operations
        for (Player p : players){
            GUIController2.setPlayerBalance(p, p.getBalance());
        }

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
        Debug.println(announce.toString());
        GUIController2.showCenterMessage(announce.toString());
    }


    // *** Player Handling *** //
    public void setupPlayers(String[] playerNames){
        if (playerNames.length >= 2 && playerNames.length <= 4){
            players = new Player[playerNames.length];

            for (int player = 0; player < playerNames.length; player++) {
                players[player] = new Player(playerNames[player]);
                players[player].setupStartBalance(START_MONEY);
                players[player].setBooths( (playerNames.length > 2) ? MIN_BOOTHS : MAX_BOOTHS );
            }

            currentPlayer = players[0]; // Default change later

            Debug.println("~Players Initialized~");
        } else{
            players = new Player[]{};
            Debug.println("Incorrect amount of player, should be between 2-4");
        }

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

    public Player[] getPlayers(){ // For testing.
        return players;
    }
}

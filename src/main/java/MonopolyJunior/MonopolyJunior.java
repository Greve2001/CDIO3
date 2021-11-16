package MonopolyJunior;

import Board.*;

import java.util.Scanner;

public class MonopolyJunior {

    private Player[] players;
    private final Die die = new Die();
    private final Board board = new Board();
    private final Deck pile = new Deck();
    private Player currentPlayer;
    private boolean hasWinner = false;
    private final Scanner input = new Scanner(System.in);
    private MJGui gui;

    private final int MIN_NR_OF_PLAYERS = 2;
    private final int MAX_NR_OF_PLAYERS = 4;
    private final int START_MONEY = 31;
    private final int BOARD_SIZE = board.getAllSquares().length;
    private final int PENNYBAG_POSITION = board.getFistPosOfSquareByType("GetMoney");
    private final int RESTOROOM_POSITION = board.getFistPosOfSquareByType("Restrooms");
    private final int MOVING_PAST_START = ((Go) board.getSquare(1)).getAmount();

    public void setupGame(int numOfPlayers){
        if (numOfPlayers < MIN_NR_OF_PLAYERS || numOfPlayers > MAX_NR_OF_PLAYERS) {
            System.out.println("The number of players is not allowed. game will not be setup.");
            return;
        }
        // Evt lav getAllPlayers method to ask for players
        initializePlayers(numOfPlayers);
        // Give players items
        for (Player player : this.players) {
            player.setupStartBalance(START_MONEY); // $31
            player.setBooths( (numOfPlayers > 2) ? 10 : 12 );
        }

    }

    public void playGame(){
        do {
            takeTurn();
        } while (!hasWinner);
        decideAndAnnounceWinner();
    }

    
    public void initializePlayers(int numOfPlayers){
        players = new Player[numOfPlayers];
        for (int i = 0 ; i < numOfPlayers && i < MAX_NR_OF_PLAYERS ; i++){ // Maybe remove && state and make it try catch to inform players
            players[i] = new Player("Player " + (i+1));
        }
        currentPlayer = players[0]; // Set starting player. // Upgrade feature
    }


    public void takeTurn() {
        input.nextLine();//just as a stop between turns
        die.roll();
        updatePosition(die.getFaceValue());
        handleField(currentPlayer.getPosition());//handle all interaction with the field the player lands on

        //check if winCondition is meet
        if (currentPlayer.getBalance() == 0)
            hasWinner = true;
    }

    private void handleField(int position) {
        String fieldType = new String(board.getSquare(position).getClass().getSimpleName());
        // Get squares which is needed across cases:
        PennyBag pennyBag = (PennyBag)board.getSquare(PENNYBAG_POSITION);

        switch (fieldType){
            case "Amusement":
                Amusement amusement = (Amusement) (board.getSquare(position));
                if ((amusement.getBoothOwner() == null)) {
                    pay(amusement.getPrice());
                    if (currentPlayer.hasBooth()) {
                        board.addBooth(currentPlayer, position);
                        currentPlayer.useOneBooth();
                    }
                }
                else{
                    if(board.hasMonopoly(position)) {
                        pay(amusement.getBoothOwner(), amusement.getPrice() * 2);
                    }
                    else
                        pay(amusement.getBoothOwner(), amusement.getPrice());
                }
                break;
            case "Railroad":
                takeTurn();//equels to restart your turn
                break;
            case "Chance":
                handleChance();
                break;
            case "GoToRestrooms":
                GoToRestrooms goToRestrooms = (GoToRestrooms)board.getSquare(position);
                currentPlayer.setGoingToRestRoom(true);
                currentPlayer.setPosition(((GoToRestrooms)board.getSquare(position)).getDestination());
                currentPlayer.setPosition(goToRestrooms.getDestination());
                pay(3);
                pennyBag.addMoney(3);
                break;
            case "PennyBag":
                currentPlayer.updateBalance(((PennyBag)board.getSquare(position)).withDraw());
                break;
            case "Restrooms":
                //do nothing
                break;
            case "PayToSee":
                PayToSee payToSee = (PayToSee)board.getSquare(position);
                pay(payToSee.getAmount());
                pennyBag.addMoney(payToSee.getAmount());
                break;
            case "Go":
                //do nothing
                break;
            default:
                System.out.println("Switch case input unknown");
        }
    }

    private void handleChance() {
        pile.pullCard();
        if (pile.getCard().getDestination() > 0){
            if (pile.getCard().getDestination() == RESTOROOM_POSITION)
                currentPlayer.setGoingToRestRoom(true);
            updatePosition(pile.getCard().getDestination());
            handleField(currentPlayer.getPosition());
        }
        else{
            //todo logik
            String color = new String(pile.getCard().getColor().toString());
            if (!board.hasMonopoly(board.getSquarePosByColor(color)[0]) && currentPlayer.hasBooth()){
                System.out.print("pick either 1 or 2");//going to gui later
                board.addBooth(currentPlayer,board.getSquarePosByColor(color)[input.nextInt()-1]);  //need refractor, also because we can't use scanner with GUI
                currentPlayer.useOneBooth();
            }

        }
    }

    public void changePlayer(){
        int playerIndex = java.util.Arrays.asList(players).indexOf(currentPlayer);
        if (playerIndex >= players.length-1)
            currentPlayer = players[0];
        else
            currentPlayer = players[playerIndex + 1];
    }

    public void updatePosition(int moveSpaces){
        int prevPos = currentPlayer.getPosition();
        int endPos = prevPos + moveSpaces;
        if (endPos >= BOARD_SIZE)
            currentPlayer.setPosition(endPos - BOARD_SIZE);
        else
            currentPlayer.setPosition(endPos);
        hasPassStart(prevPos, endPos, currentPlayer.getGoingToRestRoom());
    }

    public void hasPassStart(int prevPos, int endPos, boolean goingToRestrooms){ //
        if (goingToRestrooms) return;

        if (endPos > BOARD_SIZE || prevPos == 1 || endPos < prevPos) { // Maybe get the zero form fieldtype GO position.
            // Give passing start money
            currentPlayer.updateBalance(MOVING_PAST_START);
        }
    }

    public void pay(int amount){
        if (currentPlayer.getBalance() <= 0) { System.out.println("Negative balance, payment not possible"); return; }
        if (paymentPossible(currentPlayer, amount))
            currentPlayer.updateBalance(-amount);
        else {
            currentPlayer.updateBalance(-currentPlayer.getBalance());
        }
    }

    public void pay(Player to, int amount){
        if (currentPlayer.getBalance() <= 0) { System.out.println("Negative balance, payment not possible"); return; }
        if (paymentPossible(currentPlayer, amount)){
            to.updateBalance(amount);
            currentPlayer.updateBalance(-amount);
        }else{
            to.updateBalance(currentPlayer.getBalance());
            currentPlayer.updateBalance(-currentPlayer.getBalance()); // Removes last remaning balance
        }



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
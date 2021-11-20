package Logic;

import Board.*;
import MonopolyJunior.*;

public class ActionHandler {

    Deck deck = new Deck();
    Bank bank = new Bank();

    private int PENNYBAG_POSITION;
    private int RESTOROOM_POSITION;//never used
    private int MOVING_PAST_START;//never used

    private final Board board;
    private Player currentPlayer;
    private final GameController gameController;
    private final PositionHandler positionHandler;

    public ActionHandler(GameController gameController, Board board, PositionHandler positionHandler){
        this.board = board;
        this.positionHandler = positionHandler;
        this.gameController = gameController;
        setVariables();
    }

    private void setVariables(){
        this.PENNYBAG_POSITION = board.getPennyBagPos();
        this.RESTOROOM_POSITION = 11;
        //this.RESTOROOM_POSITION = board.getFistPosOfSquareByType("Restrooms"); // Change methode when merged
        MOVING_PAST_START = ((Go) board.getSquare(1)).getAmount();
    }

    public void doFieldAction(Player currentPlayer, int position){
        this.currentPlayer = currentPlayer;

        String fieldType = board.getSquare(position).getClass().getSimpleName();

        // Get squares which is needed across cases:
        PennyBag pennyBag = (PennyBag)board.getSquare(PENNYBAG_POSITION);

        System.out.println("Landed on: " + fieldType + " at position: " + position);

        switch (fieldType){
            case "Amusement":
                Amusement amusement = (Amusement) board.getSquare(position);

                // Check if there is a booth already
                if (amusement.getBoothOwner() == null){ // There is not a booth
                    // Buy booth
                    bank.payToBank(currentPlayer, amusement.getPrice());
                    // Give booth
                    board.addBooth(currentPlayer, position);
                }
                else{ // There is a booth
                    // Pay to the one who owns the booth
                    if (board.hasMonopoly(position)){ // Has monopoly, x2
                        bank.payToPlayer(currentPlayer, amusement.getBoothOwner(), amusement.getPrice()*2);
                    }
                    else{ // Has not, normal price
                        bank.payToPlayer(currentPlayer, amusement.getBoothOwner(), amusement.getPrice());
                    }
                }
                break;

            case "Railroad":
                // Give extra turn.
                gameController.giveExtraTurn();
                break;

            case "Chance":
                // Take card
                ChanceCard card = deck.pullCard();
                doChanceCard(card);
                break;

            case "GoToRestrooms":
                GoToRestrooms goToRestrooms = (GoToRestrooms) board.getSquare(position);

                // Pay 3$
                bank.payToBank(currentPlayer, 3);

                // Change position
                positionHandler.setPlayerPosition(currentPlayer, goToRestrooms.getDestination(), false);
                break;

            case "Restrooms":
                // Do nothing as planned
                break;

            case "PennyBag":
                // Get all the money saved up
                currentPlayer.updateBalance(pennyBag.withDraw());
                break;

            case "PayToSee":
                PayToSee payToSee = (PayToSee) board.getSquare(position);
                // Add money to pennybag
                bank.payToBank(currentPlayer, payToSee.getAmount());
                pennyBag.addMoney(payToSee.getAmount());
                break;

            case "Go":
                // Does nothing
                break;

            default:
                System.out.println("Unknown fieldtype: " + fieldType);
        }
    }

    private void doChanceCard(ChanceCard card) {
        //String cardText = card.getChanceCardText();//unused for now

        // Determine if free ticketbooth card or move-somewhere card.
        try { // Destination card
            int dest = card.getDestination();
            int amount = card.getAmountToPay();

            //Only pay amount if is not zero
            if (amount > 0){ bank.payToBank(currentPlayer, amount); }

            //Update position after card destination
            positionHandler.setPlayerPosition(currentPlayer, dest, true);
            doFieldAction(currentPlayer, currentPlayer.getPosition());

        } catch (Exception e) { // If dest is null, then it is a free ticketbooth card
            String color = card.getColor();

            int[] squares = board.getSquarePosByColor(color);
            boolean monopoly = board.hasMonopoly(squares[0]);

            if (!monopoly && currentPlayer.hasBooth()) {// Use booth
                // Get fields
                Amusement square1 = (Amusement) board.getSquare(squares[0]);
                Amusement square2 = (Amusement) board.getSquare(squares[1]);

                if (square1.getBoothOwner() == null && square2.getBoothOwner() == null){
                    // Choose a square to place booth
                    // TODO use utility class to get a choice from players
                    currentPlayer.useOneBooth();
                    board.addBooth(currentPlayer, square1.getPosition()); // Deafult, remove with extended features
                }
                else if (square1.getBoothOwner() == null) { // Square1 is free
                    currentPlayer.useOneBooth();
                    board.addBooth(currentPlayer, square1.getPosition());
                }
                else if (square2.getBoothOwner() == null){ // Square 2
                    currentPlayer.useOneBooth();
                    board.addBooth(currentPlayer, square2.getPosition());
                }
                else{ // Both are taken with different colored booths
                    // TODO use utility class to get a choice from players

                    currentPlayer.useOneBooth();
                    board.addBooth(currentPlayer, square1.getPosition()); // Deafult, remove with extended features
                }
            }
            else if(monopoly){ // There is monopoly, draw new card
                ChanceCard newCard = deck.pullCard();
                doChanceCard(newCard);
            }
            else{ // Player has no more booths, can't get new card
                System.out.println("Can't draw new card, you have no more booths");
            }

            //
        }
    }

}

package Logic;

import Board.*;
import MonopolyJunior.*;
import Utilities.Debug;

public class ActionHandler {

    Deck deck = new Deck();
    Bank bank = new Bank();

    private int PENNYBAG_POSITION;

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
    }

    public void doFieldAction(Player currentPlayer, int position){
        this.currentPlayer = currentPlayer;

        String fieldType = board.getSquare(position).getClass().getSimpleName();

        // Get squares which is needed across cases:
        PennyBag pennyBag = (PennyBag)board.getSquare(PENNYBAG_POSITION);

        Debug.println("Landed on: " + fieldType + "-square at position: " + position);

        switch (fieldType){
            case "Amusement":
                Amusement amusement = (Amusement) board.getSquare(position);

                // Check if there is a booth already
                if (amusement.getBoothOwner() == null){ // There is not a booth
                    // Buy booth
                    bank.payToBank(currentPlayer, amusement.getPrice());
                    // add booth to board
                    board.addBooth(currentPlayer, position);
                    // Remove one booth
                    currentPlayer.useOneBooth();

                    Debug.println(currentPlayer.getName() + ", bought booth at " + amusement.getName());
                }
                else{ // There is a booth
                    Debug.println(amusement.getBoothOwner().getName() + ", has a booth on this square" );
                    // Pay to the one who owns the booth
                    if (board.hasMonopoly(position)){ // Has monopoly, x2
                        Debug.println("That player has monopoly you will pay x2.");
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
                Debug.println("You get an extra turn!");
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
                Debug.println("Unknown fieldtype: " + fieldType);
        }
    }

    public void doChanceCard(ChanceCard card) {
        //String cardText = card.getChanceCardText();//unused for now

        Debug.println("You pulled a chance card");

        // Determine if free ticketbooth card or move-somewhere card.
        try { // Destination card
            Debug.println("Chance card: " + card.getChanceCardText());

            int dest = card.getDestination();
            int amount = card.getAmountToPay();

            //Only pay amount if is not zero
            if (amount > 0){ bank.payToBank(currentPlayer, amount); }

            //Update position after card destination
            positionHandler.setPlayerPosition(currentPlayer, dest, true);
            doFieldAction(currentPlayer, currentPlayer.getPosition());

        } catch (Exception e) { // If dest is null, then it is a free ticketbooth card
            System.out.println("test out, kontrol point a");//temp for test
            String color = card.getColor();
            System.out.println(color); // for test purpose

            int[] squares = board.getSquarePosByColor(color);
            System.out.println(squares[0] + " and " + squares[2]); //for test purpose
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
                Debug.println("Can't draw new card, you have no more booths");
            }

            //
        }
    }

}

package Logic;

import Board.*;
import MonopolyJunior.*;
import Utilities.Debug;

public class ActionHandler {

    private Deck deck = new Deck();
    private Bank bank = new Bank();

    private int PENNYBAG_POSITION;

    private final Board board;
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
                    if (currentPlayer.hasBooth()) {
                        // add booth to board
                        board.addBooth(currentPlayer, position);
                        GUIController.showTicketBooth(currentPlayer.getName(), position, true);
                        // Remove one booth
                        currentPlayer.useOneBooth();
                    }

                    Debug.println(currentPlayer.getName() + ", bought booth at " + amusement.getName());
                }
                else{ // There is a booth
                    Debug.println(amusement.getBoothOwner().getName() + ", has a booth on this square" );
                    // Pay to the one who owns the booth
                    if (board.hasMonopoly(position) && amusement.getBoothOwner() != currentPlayer){
                        Debug.println("That player has monopoly you will pay x2.");
                        bank.payToPlayer(currentPlayer, amusement.getBoothOwner(), amusement.getPrice()*2);
                    }
                    else if (amusement.getBoothOwner() != currentPlayer)// If owned by anyone else then the player
                        bank.payToPlayer(currentPlayer, amusement.getBoothOwner(), amusement.getPrice());

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
                doChanceCard(card, currentPlayer);
                break;

            case "GoToRestrooms":
                GoToRestrooms goToRestrooms = (GoToRestrooms) board.getSquare(position);

                // Pay 3$ and adds them to pennybag
                bank.payToBank(currentPlayer, 3);
                pennyBag.addMoney(3);
                GUIController.setPennyBagValue(pennyBag.getPosition(), pennyBag.getAmountOfMoneyPlaced());

                // Change position
                positionHandler.setPlayerPosition(currentPlayer, goToRestrooms.getDestination(), false);
                break;

            case "Restrooms":
                // Do nothing as planned
                break;

            case "PennyBag":
                // Get all the money saved up
                currentPlayer.updateBalance(pennyBag.withDraw());
                GUIController.setPennyBagValue(pennyBag.getPosition(), pennyBag.getAmountOfMoneyPlaced());
                break;

            case "PayToSee":
                PayToSee payToSee = (PayToSee) board.getSquare(position);
                // Add money to pennybag
                bank.payToBank(currentPlayer, payToSee.getAmount());
                pennyBag.addMoney(payToSee.getAmount());
                GUIController.setPennyBagValue(pennyBag.getPosition(), pennyBag.getAmountOfMoneyPlaced());

                break;

            case "Go":
                // Does nothing
                break;

            default:
                Debug.println("Unknown fieldtype: " + fieldType);
        }
    }

    public void doChanceCard(ChanceCard card, Player currentPlayer) {
        //String cardText = card.getChanceCardText();//unused for now

        Debug.println("You pulled a chance card");

        GUIController.displayChanceCard(card);


        // Determine if free ticketbooth card or move-somewhere card.
        if (card.getDestination() != 0) {
            Debug.println("Chance card: " + card);

            int dest = card.getDestination();
            int amount = card.getAmountToPay();

            //Only pay amount if is not zero
            // Only one card. When going to the restrooms
            if (amount > 0) {
                bank.payToBank(currentPlayer, amount);
            }

            // Restroom card
            if (dest == 11){
                PennyBag pennyBag = (PennyBag)board.getSquare(PENNYBAG_POSITION);
                pennyBag.addMoney(amount);
                GUIController.setPennyBagValue(pennyBag.getPosition(), pennyBag.getAmountOfMoneyPlaced());

                positionHandler.setPlayerPosition(currentPlayer, dest, false);
                return;
            }

            //Update position after card destination
            positionHandler.setPlayerPosition(currentPlayer, dest, true);
            doFieldAction(currentPlayer, currentPlayer.getPosition());

        } else { // If dest is null, then it is a free ticketbooth card
            String color = card.getColor();

            int[] squares = board.getSquarePosByColor(color);
            boolean monopoly = board.hasMonopoly(squares[0]);

            if (!monopoly && currentPlayer.hasBooth()) {// Use booth
                // Get fields
                Amusement square1 = (Amusement) board.getSquare(squares[0]);
                Amusement square2 = (Amusement) board.getSquare(squares[1]);

                if (square1.getBoothOwner() == null && square2.getBoothOwner() == null) {
                    // Choose a square to place booth
                    // TODO use utility class to get a choice from players
                    currentPlayer.useOneBooth();
                    board.addBooth(currentPlayer, square1.getPosition()); // Default, remove with extended features
                    GUIController.showTicketBooth(currentPlayer.getName(), square1.getPosition(), true);
                }
                // If either the first one is free, or you own the second, buy the first
                else if (square1.getBoothOwner() == null || square2.getBoothOwner() == currentPlayer) {
                    // Check if the ones you take is owned by another, then remove exitsting
                    if (square1.getBoothOwner() != null){
                        square1.getBoothOwner().getOneBooth();
                        board.removeBooth(square1.getPosition());
                        GUIController.showTicketBooth(null, square1.getPosition(), false);

                    }

                    currentPlayer.useOneBooth();
                    board.addBooth(currentPlayer, square1.getPosition());
                    GUIController.showTicketBooth(currentPlayer.getName(), square1.getPosition(), true);

                }
                // If either the secoind one is free, or you own the first, buy the second
                else if (square2.getBoothOwner() == null || square1.getBoothOwner() == currentPlayer){
                    // Check if the ones you take is owned by another, then remove exitsting
                    if (square2.getBoothOwner() != null){
                        square2.getBoothOwner().getOneBooth();
                        board.removeBooth(square2.getPosition());
                        GUIController.showTicketBooth(null, square1.getPosition(), false);

                    }
                    currentPlayer.useOneBooth();
                    board.addBooth(currentPlayer, square2.getPosition()); // Deafult, remove with extended features
                    GUIController.showTicketBooth(currentPlayer.getName(), square2.getPosition(), true);

                } else { // Both are taken with different colored booths
                    // TODO use utility class to get a choice from players
                    // Quick solution:
                    currentPlayer.useOneBooth();
                    board.addBooth(currentPlayer, square1.getPosition()); // Default, remove with extended features
                    GUIController.showTicketBooth(currentPlayer.getName(), square2.getPosition(), true);

                }
            } else if (monopoly) { // There is monopoly, draw new card
                ChanceCard newCard = deck.pullCard();
                doChanceCard(newCard, currentPlayer);
            } else { // Player has no more booths, can't get new card
                Debug.println("Can't draw new card, you have no more booths");
            }
        }
    }
}

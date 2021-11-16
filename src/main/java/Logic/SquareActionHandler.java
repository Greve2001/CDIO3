package Logic;

import Board.*;
import MonopolyJunior.*;

public class SquareActionHandler {

    Board board;
    Bank bank = new Bank();

    private int PENNYBAG_POSITION;
    private int RESTOROOM_POSITION;
    private int MOVING_PAST_START;

    public SquareActionHandler(Board board){
        this.board = board;
        setVariables();
    }

    private void setVariables(){
        PENNYBAG_POSITION = board.getFistPosOfSquareByType("GetMoney");
        RESTOROOM_POSITION = board.getFistPosOfSquareByType("Restrooms");
        MOVING_PAST_START = ((Go) board.getSquare(1)).getAmount();
    }

    public void doFieldAction(Player currentPlayer, int position){
        String fieldType = new String(board.getSquare(position).getClass().getSimpleName());

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
                // !!!!!!! Figure out how to signal extra turn !!!!!!!
                break;

            case "Chance":
                // !!!!! Handle Chance !!!!!!!
                break;

            case "GoToRestrooms":
                GoToRestrooms goToRestrooms = (GoToRestrooms) board.getSquare(position);
                // !!!!! change position to restroom  !!!!!!
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

}

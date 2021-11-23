package MonopolyJunior;

import Board.*;
import Utilities.Language;
import gui_fields.*;
import gui_main.GUI;

public class GUIController2 {
    private static GUI gui;
    private static String[] playerNames;
    private static GUI_Player[] guiPlayers;
    private static int[] playerPositions;
    private static Square[] allSquares;
    private static GUI_Field[] squares;

    public GUIController2(Square[] allSquares){
        GUI_Field[] guiBoard = createBoard(allSquares);
        gui = new GUI(guiBoard);
    }

    public static GUI_Field[] createBoard(Square[] as) {
        allSquares = as;
        squares = new GUI_Field[allSquares.length];

        for (int i = 0; i < squares.length; i++) {
            switch (allSquares[i].getClass().getSimpleName()) {
                case "Go" :
                    squares[i] = new GUI_Start();
                    squares[i].setSubText(Language.getText("getMoneyFromSquare") + ((Go) allSquares[i]).getAmount() + "$");
                    break;
                case "Amusement" :
                    squares[i] = new GUI_Street();
                    squares[i].setSubText(((Amusement)allSquares[i]).getPrice() + "$");
                    ((GUI_Ownable) squares[i]).setRent(((Amusement)allSquares[i]).getPrice() + "$");
                    break;
                case "Chance" :
                    squares[i] = new GUI_Chance();
                    squares[i].setSubText("");
                    break;
                case "Railroad" :
                    squares[i] = new GUI_Street();
                    squares[i].setSubText(Language.getText("rollAgain"));
                    break;
                case "PayToSee" :
                    squares[i] = new GUI_Street();
                    squares[i].setSubText(Language.getText("payToLandOn") + ((PayToSee) allSquares[i]).getAmount() + "$");
                    break;
                case "GoToRestrooms" :
                    squares[i] = new GUI_Street();
                    squares[i].setSubText("");
                    break;
                case "PennyBag" :
                    squares[i] = new GUI_Refuge();
                    squares[i].setSubText(Language.getText("valueOfSquare") + ((PennyBag) allSquares[i]).getAmountOfMoneyPlaced() + "$");
                    break;
                case "Restrooms" :
                    squares[i] = new GUI_Jail();
                    squares[i].setSubText(allSquares[i].getName());
                    break;
            }
            squares[i].setTitle(allSquares[i].getName());
            squares[i].setDescription(allSquares[i].getName());
        }
        return squares;
    }

    public static void createPlayers(int startBalance) {
        int numberOfPlayers = Integer.parseInt(
                gui.getUserSelection(Language.getText("selectNumOfPlayers"), "2", "3", "4"));

        playerNames = new String[numberOfPlayers];
        guiPlayers = new GUI_Player[numberOfPlayers];
        playerPositions = new int[numberOfPlayers];

        for (int i = 0; i < numberOfPlayers; i++) {
            playerNames[i] = gui.getUserString(Language.getText("enterName"));

            guiPlayers[i] = new GUI_Player(playerNames[i], startBalance);
            gui.addPlayer(guiPlayers[i]);

            GUI_Field field = gui.getFields()[0];
            field.setCar(guiPlayers[i], true);

            playerPositions[i] = 0;
        }
    }

    public static void movePlayer(Player player, int position) {
        GUI_Player playerToMove = new GUI_Player("");
        GUI_Field to = gui.getFields()[position - 1];

        for (int i = 0; i < playerNames.length ; i++) {
            if(playerNames[i].equals(player.getName())) {
                playerToMove = guiPlayers[i];

                GUI_Field from = gui.getFields()[playerPositions[i]];

                playerPositions[i] = position - 1;
                from.setCar(playerToMove, false);
            }
        }
        to.setCar(playerToMove, true);
    }

    public static void displayChanceCard(String description) {
        gui.setChanceCard(description);
        gui.displayChanceCard();
    }

    public static void clearChanceCard() {
        gui.setChanceCard("");
    }

    public static void getPlayerAction(Player player, String str){
        gui.showMessage(player.getName() + str);
    }

    public static void showDie(int value){
        gui.setDie(value);
    }

    public static void showTicketBooth(String ownerName, int position, boolean show){
        GUI_Street amusement = ((GUI_Street) squares[position - 1]);

        if(show) {
            amusement.setHouses(1);
            amusement.setOwnerName(ownerName);
        } else {
            amusement.setHouses(0);
            amusement.setOwnerName(null);
        }

    }

    public static void setPennyBagValue(int position, int value) {
        squares[position - 1].setSubText(Language.getText("valueOfSquare") + value);
    }

    public static void setPlayerBalance(Player player, int value){
        for (int i = 0; i < playerNames.length; i++) {
            if (player.getName().equals(playerNames[i])){
                guiPlayers[i].setBalance(value);
            }
        }
    }

    public static String[] getPlayers() {
        return playerNames;
    }
}
package MonopolyJunior;

import Board.*;
import Utilities.Language;
import gui_fields.*;
import gui_main.GUI;

import java.awt.*;

public class GUIController {
    private static GUI gui;
    private static String[] playerNames;
    private static Color[] playerColors = {Color.RED, Color.YELLOW, Color.WHITE, Color.BLACK};
    private static GUI_Player[] guiPlayers;
    private static int[] playerPositions;
    private static Square[] allSquares;
    private static GUI_Field[] squares;

    public GUIController(Square[] allSquares){
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
                    squares[i].setBackGroundColor(convertColor((((Amusement) allSquares[i])).getColor()));
                    break;
                case "Chance" :
                    squares[i] = new GUI_Chance();
                    squares[i].setSubText("");
                    squares[i].setBackGroundColor(convertColor("orange"));
                    break;
                case "Railroad" :
                    squares[i] = new GUI_Street();
                    squares[i].setSubText(Language.getText("rollAgain"));
                    squares[i].setBackGroundColor(convertColor((((Railroad) allSquares[i])).getColor()));
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
            GUI_Car playerCar = new GUI_Car(playerColors[i], playerColors[i],
                    GUI_Car.Type.RACECAR, GUI_Car.Pattern.FILL);

            guiPlayers[i] = new GUI_Player(playerNames[i], startBalance, playerCar);
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

    public static void displayChanceCard(ChanceCard card) {
        if (card.getColor() != null){
            gui.displayChanceCard( Language.getText("chanceCard") + "\n" + card.toString() + "\n" +
                    card.getColor());
        }else{
            gui.displayChanceCard(Language.getText("chanceCard") + "\n" + card.toString());
        }

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

    public static void showCenterMessage(String str){
        gui.displayChanceCard(str);
    }

    private static Color convertColor(String color) {
        Color result = Color.white;

        switch (color.toLowerCase()) {
            case "red" :
                result = Color.red;
                break;
            case "green" :
                result = Color.green;
                break;
            case "blue" :
                result = new Color(45, 137, 239);
                break;
            case "yellow" :
                result = Color.yellow;
                break;
            case "purple" :
                result = new Color(255, 90, 255);
                break;
            case "turquoise" :
                result = new Color(0,255,239);
                break;
            case "magenta" :
                result = new Color(255,0,151);
                break;
            case "orange" :
                result = new Color(235,97,35);
                break;
        }

        return result;
    }
}
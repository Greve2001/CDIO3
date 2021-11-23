/*package MonopolyJunior;

import Board.*;
import gui_fields.*;
import gui_main.GUI;

public class GUIController2 {
    private static GUI gui;
    private static String[] playerNames;
    private static GUI_Player[] guiPlayers;
    private static int[] playerPositions;
    static Square[] allSquares;
    static GUI_Field[] squares;

    public static void main(String[] args) {
        gui = new GUI(createBoard());
        createPlayers(31); // add balance from gamecontroller
        movePlayer(new Player("Mikael"), 4);
        displayChanceCard("VIS MIG!");
        clearChanceCard();
    }

    public static GUI_Field[] createBoard() {
        Board board = new Board();
        allSquares =  board.getAllSquares();
        squares = new GUI_Field[allSquares.length];

        for (int i = 0; i < squares.length; i++) {
            switch (allSquares[i].getClass().getSimpleName()) {
                case "Go" :
                    squares[i] = new GUI_Start();
                    squares[i].setTitle(allSquares[i].getName());
                    break;
                case "Amusement" :
                    squares[i] = new GUI_Street();
                    squares[i].setTitle(allSquares[i].getName());
                    squares[i].setSubText(((Amusement)allSquares[i]).getPrice() + "");
                    ((GUI_Ownable) squares[i]).setRent(((Amusement)allSquares[i]).getPrice() + "");
                    break;
                case "Chance" :
                    squares[i] = new GUI_Chance();
                    squares[i].setTitle(allSquares[i].getName());
                    squares[i].setSubText("");
                    break;
                case "Railroad" :
                    squares[i] = new GUI_Street();
                    squares[i].setTitle(allSquares[i].getName());
                    break;
                case "PayToSee" :
                    squares[i] = new GUI_Street();
                    squares[i].setTitle(allSquares[i].getName());
                    squares[i].setSubText("Pay: " + ((PayToSee) allSquares[i]).getAmount());
                    break;
                case "GoToRestrooms" :
                    squares[i] = new GUI_Street();
                    squares[i].setTitle(allSquares[i].getName());
                    squares[i].setSubText("");
                    break;
                case "PennyBag" :
                    squares[i] = new GUI_Refuge();
                    squares[i].setTitle(allSquares[i].getName());
                    squares[i].setSubText("Value: " + ((PennyBag) allSquares[i]).getAmountOfMoneyPlaced());
                    break;
                case "Restrooms" :
                    squares[i] = new GUI_Jail();
                    squares[i].setTitle(allSquares[i].getName());
                    squares[i].setSubText(allSquares[i].getName());
                    break;
            }
        }

        return squares;
    }

    public static void createPlayers(int startBalance) {
        int numberOfPlayers = Integer.parseInt(
                gui.getUserSelection("Select number of players: ", "2", "3", "4"));

        playerNames = new String[numberOfPlayers];
        guiPlayers = new GUI_Player[numberOfPlayers];
        playerPositions = new int[numberOfPlayers];

        for (int i = 0; i < numberOfPlayers; i++) {
            playerNames[i] = gui.getUserString("Enter you name: ");

            guiPlayers[i] = new GUI_Player(playerNames[i], startBalance);
            gui.addPlayer(guiPlayers[i]);

            GUI_Field field = gui.getFields()[0];
            field.setCar(guiPlayers[i], true);

            playerPositions[i] = 0;
        }
    }

    public static void movePlayer(Player player, int position) {
        GUI_Player playerToMove = new GUI_Player("");
        GUI_Field to = gui.getFields()[position];

        for (int i = 0; i < playerNames.length ; i++) {
            if(playerNames[i].equals(player.getName())) {
                playerToMove = guiPlayers[i];

                GUI_Field from = gui.getFields()[playerPositions[i]];
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

    public static void askPlayerToThrowDie(Player player){
        gui.showMessage(player.getName() + ", throw dice:");
    }

    public static void showDie(int value){
        gui.setDie(value);
    }

    public static void showTicketBooth(int position, boolean show){
        for (int square = 0; square < allSquares.length; square++) {
            if (allSquares[square].getPosition() == position-1){
                GUI_Street street = (GUI_Street) squares[square];
                if (show)
                    street.setHouses(1);
                else
                    street.setHouses(0);

            }
        }
    }

    public static String[] getPlayers() {
        return playerNames;
    }
}
*/
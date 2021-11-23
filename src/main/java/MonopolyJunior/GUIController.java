package MonopolyJunior;

import Board.*;
import gui_fields.*;
import gui_main.GUI;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GUIController {
    //------------------Fields---------------------
    private final GUI gui;
    private Map<Player, GUI_Player> playerMap;
    private final Map<String, Color> colorMap;

    //------------------Constructor---------------------
    public GUIController(Square[] squares) {
        playerMap = new HashMap<>();
        colorMap = new HashMap<>();
        colorMap.put("red", Color.RED);
        colorMap.put("green", Color.GREEN);
        colorMap.put("blue", new Color(45, 137, 239));
        colorMap.put("yellow", Color.YELLOW);
        colorMap.put("purple", new Color(255, 90, 255));
        colorMap.put("turquoise",new Color(0,255,239));
        colorMap.put("magenta",new Color(255,0,151));
        colorMap.put("orange",new Color(235,97,35));


        GUI_Field[] fields = new GUI_Field[squares.length];
        for (int i = 0; i < squares.length; i++) {
            fields[i] = convertTo(squares[i]);
        }
        gui = new GUI(fields);
    }

    //------------------Methods---------------------


    //this method is responsible to convert our square to library's GUI_Field
    public GUI_Field convertTo(Square square) {
        GUI_Field field;

        if (square instanceof Amusement) {
            //here we used the GUI_Street to create our amusement-fields on the GUI and get square's info and set it to the Field.
            field = new GUI_Street();
            field.setTitle(square.getName());
            field.setSubText(String.valueOf(((Amusement) square).getPrice()));
            field.setDescription(String.valueOf(((Amusement) square).getBoothOwner()));
            Color color = colorMap.get(String.valueOf(((Amusement) square).getColor()).toLowerCase());
            field.setBackGroundColor(color);
        }
        else
        {
            //here we used the GUI_Start to create rest of the squares-fields on the GUI and get square's info and set it to the related-Field.
            field = new GUI_Start();
            field.setTitle("");
            field.setSubText(square.getName());
            if (square instanceof Go) {
                field.setDescription(String.valueOf(((Go) square).getAmount()));
            } else if (square instanceof PayToSee) {
                field.setDescription(String.valueOf(((PayToSee) square).getAmount()));
            } else if (square instanceof PennyBag) {
                field.setDescription(String.valueOf(((PennyBag) square).getAmountOfMoneyPlaced()));
            } else if (square instanceof GoToRestrooms) {
                field.setDescription(String.valueOf(((GoToRestrooms) square).getDestination()));
            } else if (square instanceof Railroad) {
                field.setForeGroundColor(Color.getColor(((Railroad) square).getColor()));
            }
        }
        return field;
    }


    //this methode is responsible for get the number of players and return it.
    public int selectNumbersOfPlayers() {
        return Integer.valueOf(gui.getUserSelection("Select numbers of players?", "2", "3", "4"));
    }

    //this methode take the input from GUI and create Player and GUI_Player and add them to playerMap.
    //then return the Player object to the system.
    public Player setPlayer(int turn, int balance) {
        String playerName = gui.getUserString("Player " + turn + ": Enter your name please.");
        String color = gui.getUserSelection("Select your color?", "green", "red", "yellow", "blue").toLowerCase();
        Color primaryColor = colorMap.get(color);
        GUI_Car car = new GUI_Car(primaryColor, primaryColor, GUI_Car.Type.CAR, GUI_Car.Pattern.FILL);
        Player player = new Player(playerName);
        player.setBalance(balance);
        GUI_Player gui_player = new GUI_Player(player.getName(), player.getBalance(), car);
        try {
            gui.addPlayer(gui_player);
            playerMap.put(player, gui_player);
            return player;
        } catch (Exception error) {
            return null;
        }
    }

    //here we get the die as input and show it's faceValue on the GUI.
    public int  showRoll(Die die) {
        int value= die.getFaceValue();
        try{
            gui.setDie(value);
            return value;
        }catch (Exception error){
            error.printStackTrace();
            return 0;
        }
    }

    //we get the Player object as input and find the related GUI_player in the playerMap and move the car.
    public boolean moveCar(Player player, int from, int to) {
        try {
            GUI_Player gui_player = playerMap.get(player);
            gui.getFields()[from].setCar(gui_player, false);
            gui.getFields()[to].setCar(gui_player, true);
            return true;
        } catch (Exception error) {
            error.printStackTrace();
            return false;
        }
    }

    //this method show the discription of chancecart on the GUI
    public boolean showChance(ChanceCard card) {
        try {
            gui.displayChanceCard(card.toString());
            return true;
        } catch (Exception error) {
            error.printStackTrace();
            return false;
        }
    }

    //this method requires amusement and color of owner as input.
    //we cast the amusement to the Ownable class to set the border with new color.
    public boolean setOwner(Amusement amusement, String color) {
        try {
            GUI_Ownable field = (GUI_Ownable) gui.getFields()[(amusement.getPosition() - 1)];
            field.setBorder(colorMap.get(color));
            return true;
        } catch (Exception error) {
            error.printStackTrace();
            return false;
        }
    }


    public boolean updatePennybag(Player player, PennyBag pennyBag) {
        try {
            gui.getFields()[pennyBag.getPosition() - 1].setSubText(String.valueOf(pennyBag.getAmountOfMoneyPlaced()));
            return true;
        } catch (Exception error) {
            error.printStackTrace();
            return false;
        }
    }


    public String ShowWinner(Player player) {
        String input;
        input = gui.getUserButtonPressed("winner: " + player.getName() + " with balance: " + player.getBalance(), "ok");
        return input;
    }

}


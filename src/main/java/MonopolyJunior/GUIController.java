package MonopolyJunior;

import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_fields.GUI_Ownable;
import gui_fields.GUI_Player;
import gui_main.GUI;

import java.awt.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class GUIController {
    private final GUI gui;
    private final Map<String, Color> colorMap;

    public GUIController(GUI_Field[] fields) {
        gui = new GUI(fields);
        colorMap = new HashMap<>();
        colorMap.put("red", Color.RED);
        colorMap.put("green", Color.GREEN);
        colorMap.put("blue", Color.BLUE);
        colorMap.put("yellow", Color.YELLOW);
    }

    public int selectNumbersOfPlayers(){
        return Integer.valueOf(gui.getUserSelection("Select numbers of players?", "2", "3", "4"));
    }

    public boolean setPlayer(int playerTurn, int balance) {
        String playerName = gui.getUserString("Player " + playerTurn + ": Enter your name please.");
        String color = gui.getUserSelection("Select your color?", "green", "red", "yellow", "blue").toLowerCase();
        Color primaryColor = colorMap.get(color);
        GUI_Car car = new GUI_Car(primaryColor, primaryColor, GUI_Car.Type.CAR, GUI_Car.Pattern.FILL);
        GUI_Player player = new GUI_Player(playerName, balance, car);
        if ( gui.addPlayer(player)== true) {
            return true;
        }

        return false;
    }

    public void showRoll(Die die) {
        gui.setDie(die.getFaceValue());
    }

    public boolean moveCar(GUI_Player player, int from, int to) {
        try {
            gui.getFields()[from].setCar(player, false);
            gui.getFields()[to].setCar(player, true);
            return true;
        }catch (Exception error){
            error.printStackTrace();
            return false;
        }
    }

    public boolean showChance(String message){
        try {
            gui.displayChanceCard(message);
            return true;
        }catch (Exception error){
            error.printStackTrace();
            return false;
        }
    }

    public boolean setOwner(GUI_Ownable field, String color) {
        try {
            String newPrice = gui.getUserString("Enter your price:");
            field.setSubText(newPrice);
            field.setBorder(Color.getColor(color.toLowerCase(Locale.ROOT)));
            return true;
        }catch (Exception error){
            error.printStackTrace();
            return false;
        }
    }

    public boolean addToPennyBag(String playerName, int pbIndex, int money) {
        try {
            gui.getUserButtonPressed(playerName + " you pay" + money, "pay");
            int  balance=Integer.valueOf(gui.getFields()[pbIndex].getSubText()) + money;
            gui.getFields()[pbIndex].setSubText(String.valueOf(balance));
            return true;
        }catch (Exception error){
            error.printStackTrace();
            return false;
        }
    }

    public boolean withdrawPennyBag(String playerName, int pbIndex, int money) {
        try {
            gui.getUserButtonPressed(playerName + " you recive" + money, "Receive");
            int  balance=Integer.valueOf(gui.getFields()[pbIndex].getSubText()) + money;
            gui.getFields()[pbIndex].setSubText(String.valueOf(balance));
            return true;
        }catch (Exception error){
            error.printStackTrace();
            return false;
        }
    }

    public String ShowWinner(GUI_Player player){
        String input;
       input= gui.getUserButtonPressed("winner: "+ player.getName() + " with balance: "+ player.getBalance(), "ok");
        return input;
    }

}


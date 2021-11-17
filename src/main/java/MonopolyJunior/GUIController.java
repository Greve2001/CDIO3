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

    public void setPlayer(int playerTurn, int balance) {
        String playerName = gui.getUserString("Player " + playerTurn + ": Enter your name please.");
        String color = gui.getUserSelection("Select your color?", "green", "red", "yellow", "blue").toLowerCase();
        Color primaryColor = colorMap.get(color);
        GUI_Car car = new GUI_Car(primaryColor, primaryColor, GUI_Car.Type.CAR, GUI_Car.Pattern.FILL);
        GUI_Player player = new GUI_Player(playerName, balance, car);
        gui.addPlayer(player);
    }

    public void showRoll(Die die) {
        gui.setDie(die.getFaceValue());
    }

    public void moveCar(GUI_Player player, int from, int to) {
        gui.getFields()[from].setCar(player, false);
        gui.getFields()[to].setCar(player, true);
    }

    public void showChance(String message){
        gui.displayChanceCard(message);
    }
    public void setOwner(GUI_Ownable field, String color) {
        String newPrice = gui.getUserString("Enter your price:");
        field.setSubText(newPrice);
        field.setBorder(Color.getColor(color.toLowerCase(Locale.ROOT)));
    }

    public void updatePennyBag(String playerName, int pbIndex, int money) {
        if(money> 0) {
            gui.getUserButtonPressed(playerName + " you recive" + money, "Receive");
        }else{
            gui.getUserButtonPressed(playerName + " you pay" + money, "pay");
        }

        int  balance=Integer.valueOf(gui.getFields()[pbIndex].getSubText()) + money;
        gui.getFields()[pbIndex].setSubText(String.valueOf(balance));
    }

    public void ShowWinner(GUI_Player player){
        gui.getUserButtonPressed("winner: "+ player.getName() + " with balance: "+ player.getBalance(), "ok");
    }

}


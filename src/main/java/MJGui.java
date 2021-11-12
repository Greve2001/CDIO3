import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import gui_main.GUI;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MJGui {
    private GUI gui;
    private Map<String, Color> colorMap;

    public MJGui(){
        gui = new GUI();
        colorMap = new HashMap<>();
        colorMap.put("rød", Color.RED);
        colorMap.put("grøn", Color.GREEN);
        colorMap.put("blå", Color.BLUE);
        colorMap.put("gul", Color.YELLOW);
    }

    /**
     * Set player name and color
     * @param playerNumber - the number of the player
     * @param startMoney - the amount to start with
     */
    public void setPlayerNameAndColor(int playerNumber, int startMoney){
        String playerName = gui.getUserString("Hvad hedder spiller "+playerNumber+"?");
        String color = gui.getUserString("Hvilken farve?").toLowerCase();
        Color primaryColor = colorMap.get(color);
        GUI_Car car = new GUI_Car(primaryColor, primaryColor, GUI_Car.Type.CAR, GUI_Car.Pattern.FILL);
        GUI_Player p = new GUI_Player(playerName, startMoney, car);
        gui.addPlayer(p);
    }

    public void buildGameFields(){


    }

    /**
     * Show that a die was rolled
     * @param die - the die that was rolled
     */
    public void showRoll(Die die) {
        gui.setDie(die.getFaceValue());
    }

    public void changePlayer(int playerIndex) {

    }
}

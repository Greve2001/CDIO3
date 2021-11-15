package MonopolyJunior;

import gui_fields.*;
import gui_main.GUI;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MJGui {
    private GUI gui;
    private Map<String, Color> colorMap;
    private GUI_Player[] players;
    private GUI_Player[] priorityList;
    int[] currentPosition;
    private Die die;

    public MJGui(){
        //gui= new GUI();
        gui = new GUI(createField());
        colorMap = new HashMap<>();
        colorMap.put("rød", Color.RED);
        colorMap.put("grøn", Color.GREEN);
        colorMap.put("blå", Color.BLUE);
        colorMap.put("gul", Color.YELLOW);
        die= new Die();
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

    /**
     * Show that a die was rolled
     * @param die - the die that was rolled
     */
    public void showRoll(Die die) {
        gui.setDie(die.getFaceValue());
    }

    public void changePlayer(int playerIndex) {

    }

    //-------------------adding new functionalities-------------------------------------

    /*setPlayers use the GUI class which asks the users :
        1-to select how many players participate the game
        2-to enter their name
        3-to select the color.
     */
    public void setPlayers(){
        String playerNumber = gui.getUserSelection("select the players number:", "2","3","4");
        players= new GUI_Player[Integer.valueOf(playerNumber)];
        String[] selectedColor= new String[4];
        for(int i=0;i < players.length ;i++){
            String playerName = gui.getUserString("Player "+ (i+1) +" please enter your name:");
            String color = gui.getUserSelection("Select your color?", "green","red","yellow","blue").toLowerCase();
            if(Arrays.asList(selectedColor).contains(color)== true){
                --i;
                gui.getUserButtonPressed( "color is already taken.\n "+ players[i].getName()+ " please try again!" ,"Ok" );
            }else {
                selectedColor[i] =  color;
                Color primaryColor = colorMap.get(color);
                GUI_Car car = new GUI_Car(primaryColor, primaryColor, GUI_Car.Type.CAR, GUI_Car.Pattern.FILL);
                GUI_Player p = new GUI_Player(playerName, 31, car);
                gui.addPlayer(p);
                players[i] = p;
            }
        }
    }

    //setPriority ask the players to rolle the die, and then sort the players from the highst to lowest facevalues,
    public void setPriority(){
        Integer[] orginal= new Integer[players.length];
        Integer[] sorted= new Integer[players.length];
        for (int i= 0; i<players.length; i++){
            gui.getUserButtonPressed(players[i].getName()+ "'s turn!" ,"Roll Die" );
            die.roll();
            int value= die.getFaceValue();
            gui.setDie(value);
            if(Arrays.asList(orginal).contains(value) == true){
                --i;
            }
            else{
                orginal[i]= value;
                sorted[i]= value;
            }
        }

        Arrays.sort(sorted, Collections.reverseOrder());
        priorityList= new GUI_Player[players.length];
        currentPosition= new int[priorityList.length];
        for (int i = 0; i < sorted.length; i++) {
            for (int j = 0; j < orginal.length; j++) {
                if (sorted[i] == orginal[j]) {
                    priorityList[i] = players[j];
                    currentPosition[i]= 0;
                }
            }
        }

        String priority="";
        for(int q=0 ; q<priorityList.length; q++){
            priority += ((q+1) + "-"+ priorityList[q].getName()+",");
            gui.getFields()[currentPosition[q]].setCar(priorityList[q],true);
        }
        gui.showMessage(priority);
    }

    //moveCar method set the car to the new position and remove it from the old position.
    public void moveCar(int from, int to, GUI_Player player){
        gui.getFields()[from].setCar(player, false);
        gui.getFields()[to].setCar(player, true);
    }

    /*setOwner set the name of the owner to the filed
      and ask the player to enter a price for the field
      and set a green border around the filed that border has an owner.
     */
    public void setOwner(GUI_Field field, GUI_Player player){
        GUI_Ownable ownable = (GUI_Ownable) field;

        ownable.setOwnerName(player.getName());
        String newPrice= gui.getUserString("Enter your price:");
        ownable.setSubText(newPrice);
        ownable.setBorder(Color.GREEN);
    }

    // getOwner return the player who owns the filed
    public GUI_Player getOwner(GUI_Field field){
        GUI_Player player= null;
        GUI_Street street = (GUI_Street) field;
        if(street.getOwnerName() !=null){
            for(int i=0; i< priorityList.length; i++){
                if(street.getOwnerName() == priorityList[i].getName()){
                    player = priorityList[i];
                }
            }
        }
        return player;
    }

    //handleLanding method handle the player when lands on a field.
    public void handleLanding(GUI_Player player, int turn, GUI_Field field){
        String type= field.getTitle();
        System.out.println("filedType= " + type);
        switch(type){
            case"Start":
                int balance= player.getBalance();
                balance +=200;
                player.setBalance(balance);
                break;
            case "Railroad": --turn;
                break;
            case "PennyBag":
                int cost= (Integer.valueOf(field.getSubText()));
                gui.getUserButtonPressed( player.getName()+ " you recive"+ cost ,"Receive" );
                balance= player.getBalance() + cost;
                player.setBalance(balance);
                balance= Integer.valueOf(field.getSubText()) - cost;
                gui.getFields()[16].setSubText(String.valueOf(balance));
                break;
            case "Fireworks":
                cost= (Integer.valueOf(field.getSubText()));
                gui.getUserButtonPressed( player.getName()+ " you should pay "+ cost ,"Pay" );
                balance= player.getBalance() - cost;
                player.setBalance(balance);
                balance= Integer.valueOf(gui.getFields()[16].getSubText()) + cost;
                gui.getFields()[16].setSubText(String.valueOf(balance));
                break;
            case "WaterShow":
                cost= (Integer.valueOf(field.getSubText()));
                gui.getUserButtonPressed( player.getName()+ " you should pay "+ cost +" to pennyBag" ,"Pay" );
                balance= player.getBalance() - cost;
                player.setBalance(balance);
                balance= Integer.valueOf(gui.getFields()[16].getSubText()) + cost;
                gui.getFields()[16].setSubText(String.valueOf(balance));
                break;
            case"?":
                break;
            case "Restroom":
                break;
            case "goToRestroom":
                break;
            default:
                GUI_Player owner = this.getOwner(field);
                if (owner != null) {
                    gui.getUserButtonPressed(player.getName() + " you should pay" + (Integer.valueOf(field.getSubText())) +"to "+ getOwner(field).getName() , "Pay");
                    balance = player.getBalance() - (Integer.valueOf(field.getSubText()));
                    player.setBalance(balance);
                    balance= owner.getBalance() + (Integer.valueOf(field.getSubText()));
                    owner.setBalance(balance);
                }else {
                    gui.getUserButtonPressed(player.getName() + " you should pay" + (Integer.valueOf(field.getSubText())), "Pay");
                    balance = player.getBalance() - (Integer.valueOf(field.getSubText()));
                    player.setBalance(balance);
                    boolean confirm  = gui.getUserLeftButtonPressed(
                            "do you wnat to be the owner of" +field.getTitle() ,
                            "Yes", "No"
                    );
                    if (confirm== true){
                        setOwner(field, player);
                    }
                }
                break;
        }

    }

    //getWiner method return the player with the highst balance
    public GUI_Player getWiner(){
        GUI_Player winner;
        for (int i = 0; i < priorityList.length; i++)
        {
            for (int j = i + 1; j < priorityList.length; j++)
            {
                if (priorityList[i].getBalance() > priorityList[j].getBalance())
                {
                    winner = priorityList[i];
                    priorityList[i] = priorityList[j];
                    priorityList[j] = winner;
                }
            }
        }

        winner= priorityList[priorityList.length - 1];

        return winner;
    }

    public void play(){
        this.setPlayers();
        this.setPriority();

        boolean run=true;
        while(run) {

            for(int turn= 0; turn < priorityList.length; turn++){
                gui.getUserButtonPressed(priorityList[turn].getName()+ "'s turn!" ,"Roll Die" );
                die.roll();
                int value= die.getFaceValue();
                gui.setDie(value);

                int newPosition = currentPosition[turn] + value;
                if(newPosition >= gui.getFields().length) {
                    newPosition = newPosition- gui.getFields().length;
                }

                this.moveCar(currentPosition[turn],newPosition,priorityList[turn]);

                currentPosition[turn] = newPosition;

                handleLanding(priorityList[turn], turn, gui.getFields()[newPosition]);

                if(priorityList[turn].getBalance()== 0){
                    gui.showMessage(getWiner().getName() +"won");
                }
            }
        }
    }

    /*we might need to create our own fields later. but for now I used the following field as substitution.
     GUI_Street  as Amusment field
     GUI_Shipment as Railrood field
     GUI_Refuge as Fireworks and watershow filed
     GUI_Jail as RestRoom and goToRestroom
     */
    public  GUI_Field[] createField(){
        GUI_Field[] fields = new GUI_Field[32];
        int i = 0;
        fields[i++] = new GUI_Start("Start", "200", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Chance("?", "Prøv lykken", "Ta' et chancekort.", new Color(204, 204, 204), Color.BLACK);
        fields[i++] = new GUI_Street("Ballon\nStand", "1", "Ballon Stand", "Leje:  20", new Color(128, 0, 225), Color.BLACK);
        fields[i++] = new GUI_Street("Cotton\nCandy", "1", "Cotton Candy", "Leje:  20", new Color(128, 0, 225), Color.BLACK);
        fields[i++] = new GUI_Chance("?", "Prøv lykken", "Ta' et chancekort.", new Color(204, 204, 204), Color.BLACK);
        fields[i++] = new GUI_Shipping("default", "Railroad", "Yellow Line", "Roll Again", "", Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Street("Poppet\nSHow", "2", "Poppet Show", " ", Color.CYAN, Color.BLACK);
        fields[i++] = new GUI_Street("Magic\nShow", "2", "Magic Show", " ", Color.CYAN, Color.BLACK);
        fields[i++] = new GUI_Refuge("default", "Fireworks", "2", "Watch Fireworks", Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Chance("?", "Prøv lykken", "Ta' et chancekort.", new Color(204, 204, 204), Color.BLACK);
        fields[i++] = new GUI_Jail("default", "Restroom", "Restroom", "In restroom", new Color(125, 125, 125), Color.BLACK);
        fields[i++] = new GUI_Street("Merry", "2", "Merrry Go Round", "", Color.PINK, Color.BLACK);
        fields[i++] = new GUI_Street("Paddle\nboats", "2", "Paddle Boats", "Leje:  20", Color.PINK, Color.BLACK);
        fields[i++] = new GUI_Shipping("default", "Railroad", "Green Line", "Roll Again.", "Leje:  75", Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Street("Water\nSlide", "3", "Water Slide", "Leje:  20", Color.ORANGE, Color.BLACK);
        fields[i++] = new GUI_Street("Miniature\nGolf", "3", "Miniature Golf", "Leje:  20",Color.ORANGE, Color.BLACK);
        fields[i++] = new GUI_Shipping("default", "PennyBag", "0", "Loose Change", "Leje:  75", Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Chance("?", "Prøv lykken", "Ta' et chancekort.", new Color(204, 204, 204), Color.BLACK);
        fields[i++] = new GUI_Street("Video\nArcade", "3", "Video Arcade", "Leje:  40", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street("Haunted\nHouse", "3", "Haunted House", "Leje:  45", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Chance("?", "Prøv lykken", "Ta' et chancekort.", new Color(204, 204, 204), Color.BLACK);
        fields[i++] = new GUI_Shipping("default", "Railroad", "Blue Line", "Roll Again", "Leje:  75", Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Street("Helicopter\nRide", "4", "Helicopter Ride", "Leje:  40", Color.YELLOW, Color.BLACK);
        fields[i++] = new GUI_Street("Ponny\nRide", "4", "Pony Ride", "Leje:  45", Color.YELLOW, Color.BLACK);
        fields[i++] = new GUI_Refuge("default", "WaterSHow", "2", "Watch waterShow", Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Chance("?", "Prøv lykken", "Ta' et chancekort.", new Color(204, 204, 204), Color.BLACK);
        fields[i++] = new GUI_Jail("default", "goToRestroom", "Go-Restroom", "Go to Restroom", new Color(125, 125, 125), Color.BLACK);
        fields[i++] = new GUI_Street("Bumpers\nCar", "4", "Bumpers Car", "Leje:  60", Color.GREEN, Color.BLACK);
        fields[i++] = new GUI_Street("Ferries\nWheel", "4", "Ferris Wheel", "Leje:  60", Color.GREEN, Color.BLACK);
        fields[i++] = new GUI_Shipping("default", "Railroad", "RedLine", "Roll Again", "Leje:  75", Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Street("Loop\nThe\nLoop", "5", "Loop the Loop", "Leje:  20", Color.BLUE, Color.BLACK);
        fields[i++] = new GUI_Street("Roller\nCaster", "5", "Roller Caster", "Leje:  20", Color.BLUE, Color.BLACK);

        return fields;
    }


    public static void main(String[] args){
        MJGui mp= new MJGui();
        mp.play();
    }

}

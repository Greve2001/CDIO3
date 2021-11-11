import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_main.GUI;

public class Main {
    public static void main(String[] args) {
        /*
        GUI gui = new GUI();
        var field = gui.getFields()[0];
        field.setTitle("Hubabuba");
        field.setSubText("Super-Goli");
        field.setDescription("Super spændende beskrivelse");

        GUI_Player player1 = new GUI_Player("Goli", 2000);
        field.setCar(player1, true);
        */

        /*
        MonopolyJunior monopoly = new MonopolyJunior(4);//need to be changeable with some user input later.
        monopoly.giveStartMoney();
        //monopoly.inputNames();//For name input later (might never go live)
        //monopoly.whoGoesFirst();//let all roll, and highest goes first
        do {
            monopoly.takeTurn();
            if (!monopoly.gethasWinner())
                monopoly.changePlayer();
        }while(!monopoly.gethasWinner());
        monopoly.decideAndAnnounceWinner();//to find and Announce the winner
        */

        /*GUI_Field[] fields = new GUI_Field[]{

        };*/

        MJGui gui = new MJGui();
//        gui.showMessage("Jubiiiiiii");
//        gui.setDie(3);

//        String chosenColor = gui.getUserButtonPressed("Choose a car color", "Red", "Green", "Blue", "Infrared");
//        System.out.println("User chose "+ chosenColor);

//        String chosenAnimal = gui.getUserSelection("Choose an animal", "Cat","Dog","Dolphin");
//        System.out.println("User chose "+ chosenAnimal);

//        String username = gui.getUserString("Hvad hedder spiller 1?");
//        System.out.println("Spiller 1 hedder "+username);

        MonopolyJunior game = new MonopolyJunior(4, gui);

    }
}

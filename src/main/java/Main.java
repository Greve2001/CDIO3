import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_main.GUI;

public class Main {
    public static void main(String[] args) {

        MonopolyJunior monopoly = new MonopolyJunior(4);//need to be changeable with some user input later.
        monopoly.setupGame(4);
        monopoly.playGame();
        // !!! GUI !!!
        /*
        MJGui gui = new MJGui();
        //gui_sandbox();
        MonopolyJunior game = new MonopolyJunior(4, gui);
        */
    }

    /**
     * Experiments with gui. Delete before project hand-in.
     */
    private static void gui_sandbox(){
        /*
        GUI gui = new GUI();
        var field = gui.getFields()[0];
        field.setTitle("Hubabuba");
        field.setSubText("Super-Goli");
        field.setDescription("Super spændende beskrivelse");

        GUI_Player player1 = new GUI_Player("Goli", 2000);
        field.setCar(player1, true);


        GUI gui = new GUI();
        gui.showMessage("Jubiiiiiii");
        gui.setDie(3);

        String chosenColor = gui.getUserButtonPressed("Choose a car color", "Red", "Green", "Blue", "Infrared");
        System.out.println("User chose "+ chosenColor);

        String chosenAnimal = gui.getUserSelection("Choose an animal", "Cat","Dog","Dolphin");
        System.out.println("User chose "+ chosenAnimal);

        String username = gui.getUserString("Hvad hedder spiller 1?");
        System.out.println("Spiller 1 hedder "+username);
        */
    }
}

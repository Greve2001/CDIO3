import MonopolyJunior.GUIController;
import gui_codebehind.GUI_FieldFactory;
import gui_fields.*;
import gui_main.GUI;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.awt.*;

import static org.junit.Assert.*;

public class GUIControllerTest {
    GUI_Field[] fields = GUI_FieldFactory.makeFields();
    GUIController controller= new GUIController(fields);

    GUI_Player[] players = {new GUI_Player("Goli", 100, new GUI_Car())};

    @Test
    public void selectNumbersOfPlayers(){
        int total= controller.selectNumbersOfPlayers();
        Assertions.assertEquals(2, total);
    }

    @Test
    public void setPlayer() {
        boolean added= controller.setPlayer(1, 100);
        Assertions.assertEquals(added , true);
    }

    @Test
    public void showRoll() {
    }

    @Test
    public void moveCar() {
        boolean moved = controller.moveCar(players[0], 0,10);
        Assertions.assertEquals(moved, true);
    }

    @Test
    public void showChance() {
        boolean displayed= controller.showChance("welcome");
        Assertions.assertEquals(true, displayed);
    }

    @Test
    public void setOwner() {
        boolean owned=controller.setOwner( (GUI_Ownable) fields[1] ,"red");
        Assertions.assertEquals(true,owned);
    }

    @Test
    public void addToPennyBag() {
        fields[5].setSubText("5");
        boolean isAdded=controller.addToPennyBag(players[0].getName(),5,100);
        Assertions.assertEquals(true,isAdded);
    }

    @Test
    public void withdrawPennyBag() {
        fields[5].setSubText("5");
        boolean isWithrawed=controller.withdrawPennyBag(players[0].getName(),5,100);
        Assertions.assertEquals(true,isWithrawed);
    }

    @Test
    public void showWinner() {
        String input =controller.ShowWinner(players[0]);
       Assertions.assertEquals("winner: "+ players[0].getName() + " with balance: "+ players[0].getBalance(), input);
    }
}
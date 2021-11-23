import Board.*;
import MonopolyJunior.*;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class GUIControllerTest {
    Square[] squares = new Board().getAllSquares();
    GUIController controller= new GUIController(squares);

    Player player = new Player("Goli");

    @Test
    public void selectNumbersOfPlayers(){
        int total= controller.selectNumbersOfPlayers();
        Assertions.assertEquals(2, total);
    }

    @Test
    public void setPlayer() {
        player.setBalance(100);
        String playerName= player.getName().toLowerCase();
        player = controller.setPlayer(1, 100);
        Assertions.assertEquals( playerName, player.getName().toLowerCase());

        controller.selectNumbersOfPlayers();

        boolean moved = controller.moveCar(player, 0,10);
        Assertions.assertEquals(moved, true);

        controller.selectNumbersOfPlayers();
    }

    @Test
    public void showRoll() {
        Die die=new Die();
        int x= die.getFaceValue();

        int value=controller.showRoll(die);
        Assertions.assertEquals(x,value);

        //controller.selectNumbersOfPlayers();
    }

    @Test
    public void moveCar() {
        boolean moved = controller.moveCar(player, 0,10);
        Assertions.assertEquals(moved, true);
    }

    @Test
    public void showChance() {
        ChanceCard card = new ChanceCard("hello",5);
        boolean displayed= controller.showChance(card);
        Assertions.assertEquals(true, displayed);
    }

    @Test
    public void setOwner() {
        Amusement amusement=new Amusement("Cotton Candy",4, "Purple", 1);
        Boolean owned=controller.setOwner(amusement,"red");

        Assertions.assertEquals(true, owned);

        //controller.selectNumbersOfPlayers();
    }

    @Test
    public void updatePennybag(){
        PennyBag pennyBag = new PennyBag("lene",17);
        pennyBag.addMoney(50);
        Boolean updated=controller.updatePennybag(player,pennyBag);
        Assertions.assertEquals(true,updated);

        //controller.selectNumbersOfPlayers();
    }

    @Test
    public void showWinner() {
        String input =controller.ShowWinner(player);
        Assertions.assertEquals("winner: "+ player.getName() + " with balance: "+ player.getBalance(), input);
    }
}
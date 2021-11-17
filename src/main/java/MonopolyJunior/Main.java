package MonopolyJunior;

public class Main {
    public static void main(String[] args) {

        MonopolyJunior monopoly = new MonopolyJunior();//need to be changeable with some user input later.
        monopoly.setupGame(4);
        monopoly.playGame();
    }

}

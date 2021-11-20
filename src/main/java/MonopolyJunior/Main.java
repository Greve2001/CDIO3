package MonopolyJunior;

public class Main {
    public static void main(String[] args) {

        MonopolyJunior_old monopoly = new MonopolyJunior_old();//need to be changeable with some user input later.
        monopoly.setupGame(4);
        monopoly.playGame();
    }

}

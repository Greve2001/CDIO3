package MonopolyJunior;

import Logic.GameController;

public class Main {
    public static void main(String[] args) {

        GameController monopoly = new GameController();//need to be changeable with some user input later.
        monopoly.setupGame(4);
        monopoly.playGame();
    }

}

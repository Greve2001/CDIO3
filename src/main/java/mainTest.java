import Logic.GameController;

public class mainTest {
    static GameController GC = new GameController();

    public static void main(String[] args) {
        GC.setupGame();
        GC.startGame();
    }
}

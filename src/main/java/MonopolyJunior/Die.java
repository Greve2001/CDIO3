package MonopolyJunior;

public class Die {
    private int faceValue;
    final int NROFSIDES = 6;

    public Die() {
        roll();
    }

    public void roll() {
        faceValue = (int) (Math.random() * NROFSIDES) + 1;
    }

    public int getFaceValue() {
        return faceValue;
    }
}

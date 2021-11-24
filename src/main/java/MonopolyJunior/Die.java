package MonopolyJunior;

public class Die {
    private int faceValue;

    public Die() {
        roll();
    }

    public void roll() {
        final int NROFSIDES = 6;
        faceValue = (int) (Math.random() * NROFSIDES) + 1;
    }

    public int getFaceValue() {
        return faceValue;
    }
}

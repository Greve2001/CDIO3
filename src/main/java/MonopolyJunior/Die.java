package MonopolyJunior;

public class Die {
    private int faceValue;
    private final int NR_OF_SIDES = 6;

    public Die() {
        roll();
    }

    public void roll() {
        faceValue = (int) (Math.random() * NR_OF_SIDES) + 1;
    }

    public int getFaceValue() {
        return faceValue;
    }
}

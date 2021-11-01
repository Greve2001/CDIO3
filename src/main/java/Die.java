
public class Die {
    private int faceValue;
    private int nrOfSides = 6;

    public Die() {
        roll();
    }

    public Die(int nrOfSides) {
        this.nrOfSides = nrOfSides;
        roll();
    }

    public void roll() {
        faceValue = (int) (Math.random() * this.nrOfSides) + 1;
    }

    public int getFaceValue() {
        return faceValue;
    }
}

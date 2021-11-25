package Board;

public class Go extends Square {
    private final int AMOUNT;

    public Go(String name, int position, int AMOUNT) {
        super(name, position);
        this.AMOUNT = AMOUNT;
    }

    public int getAmount() {
        return AMOUNT;
    }
}

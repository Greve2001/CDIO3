package Board;

public class Go extends Square{
    private final int amount;

    public Go(int amount, int pos) {
        super("GO!", pos);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}

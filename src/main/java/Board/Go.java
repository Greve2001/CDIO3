package Board;

public class Go extends Square{
    private final int amount;

    public Go(String name, int amount, int position) {
        super(name, position);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}

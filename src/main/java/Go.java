public class Go extends Square{
    private final int amount;

    public Go(int amount) {
        super("GO!", 1);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}

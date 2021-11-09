package Board;

public class PayToSee extends Square{
    private final int amount;

    public PayToSee(String name, int position, int amount) {
        super(name, position);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}

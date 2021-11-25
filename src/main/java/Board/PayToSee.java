package Board;

public class PayToSee extends Square {
    private final int AMOUNT;

    public PayToSee(String name, int position, int AMOUNT) {
        super(name, position);
        this.AMOUNT = AMOUNT;
    }

    public int getAmount() {
        return AMOUNT;
    }
}

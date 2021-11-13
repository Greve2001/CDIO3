package Board;

public class PennyBag extends Square{
    int AmountOfMoneyPlaced = 0;

    PennyBag(String name, int pos) {
        super(name, pos);
    }

    public void addMoney(int amount){
        if(amount <= 0) {
            System.out.println("Error: value must be negative");
        } else {
            AmountOfMoneyPlaced += amount;
        }
    }

    public int withDraw() {
        int value = AmountOfMoneyPlaced;
        AmountOfMoneyPlaced = 0;

        return value;
    }

    public int getAmountOfMoneyPlaced() {
        return AmountOfMoneyPlaced;
    }
}

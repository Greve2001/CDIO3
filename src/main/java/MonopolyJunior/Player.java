package MonopolyJunior;// A class for the player which contains the score and name of the player

public class Player {
    private String name;
    private int position = 1, balance, boothsOnHand;

    public void useOneBooth() {
        //uses one booth that the player has.
        this.boothsOnHand -= 1;

    }

    public void getOneBooth() {
        this.boothsOnHand += 1;
    }

    public Player(String name) {
        this.name = name;
    }

    public boolean hasBooth() {
        //Checks if the player has booths on hand.
        return boothsOnHand > 0;
    }

    public void updateBalance(int update) {
        //Updates the balance of a player.
        if (update > 0 || balance > Math.abs(update))
            this.balance += update;
        else
            this.balance = 0;
    }

    //Sets the start Balance of a player.
    public void setupStartBalance(int startMoney) {
        this.balance = startMoney;
    }

    public int getBoothsOnHand() {
        return boothsOnHand;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setBooths(int booths) {
        this.boothsOnHand = booths;
    }

    public int getPosition() {
        return this.position;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}

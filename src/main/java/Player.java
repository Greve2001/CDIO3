// A class for the player which contains the score and name of the player

public class Player {
    private int balance;
    private int position;
    private int boothsOnHand;
    private int playerNumber;
    private String name;
    private boolean hasExtraTurn = false;

    public Player() {
        this.name = this.playerNumber + "";
    }

    public void setName(String name){
        this.name = name;
    }

    public String toString() {
        return name;
    }

    public void setPosition(int position){
        this.position = position;
    }

    public int getPosition(){
        return this.position;
    }

    public void setupStartBalance(int startMoney){
        this.balance = startMoney;
    }

    public void updateBalance(int update){
        if (update > 0 || balance > Math.abs(update))
            this.balance += update;
        else
            this.balance = 0;
    }

    public int getBalance(){
        return balance;
    }
}



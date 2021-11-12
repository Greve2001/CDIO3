// A class for the player which contains the score and name of the player

import java.awt.*;

public class Player {
    private int playerNumber;
    private String name;
    private int position, balance, boothsOnHand;
    private Color color;


    public void useOneBooth () {
        this.boothsOnHand = this.boothsOnHand -1;
    }

    public Player() {
        this.name = this.playerNumber + "";
    }
    public boolean hasBooth(){
        if (boothsOnHand > 0)
            return true;
        else
            return false;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPosition (int position){
        this.position = position;
    }
    public void setBooths (int booths) {
        this.boothsOnHand = booths;
    }

    public int getPosition () {
        return this.position;
    }
    public void setupStartBalance (int startMoney){
        this.balance = startMoney;
    }
    public void updateBalance(int update){
        if (update > 0 || balance > Math.abs(update))
            this.balance += update;
        else
            this.balance = 0;
    }

    public int getBalance() {
        return balance;
    }
}

package MonopolyJunior;// A class for the player which contains the score and name of the player

import java.awt.*;

public class Player {
    private String name;
    private int position = 1, balance, boothsOnHand;
    private Color color;
    boolean goingToRestRoom;


    public void useOneBooth () {
        this.boothsOnHand = this.boothsOnHand -1;
    }

    public Player(String name) {
        this.name = name;
    }
    public boolean hasBooth(){
        if (boothsOnHand > 0)
            return true;
        else
            return false;
    }

    public void setGoingToRestRoom(boolean goingToRestRoom) {
        this.goingToRestRoom = goingToRestRoom;
    }
    public boolean getGoingToRestRoom () {
        return this.goingToRestRoom;
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

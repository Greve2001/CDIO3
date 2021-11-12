// A class for the player which contains the score and name of the player

import java.awt.*;

public class Player {
    private int playerNum;
    private String name;
    private int position, balance, boothsOnHand;
    private Color color;

    public void payMoney (int balance){
        this.balance -= balance;
    }
    public void addMoney (int balance){
        this.balance += balance;
    }
    public void UseOneBooth () {
        this.boothsOnHand = this.boothsOnHand -1;
    }

    public Player() {

    }

    public void setName(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
}


package Board;

import MonopolyJunior.Player;

public class Amusement extends Square{
    private final String color;
    private final int price;
    private Player boothOwner;

    public Amusement(String name, int position, String color, int price) {
        super(name, position);
        this.color = color;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }


    public String getColor() {
        return color;
    }

    public void setBoothOwner(Player boothOwner) {
        this.boothOwner = boothOwner;
    }

    public Player getBoothOwner() {
        return boothOwner;
    }
}

package Board;

import MonopolyJunior.Player;

public class Amusement extends Square {
    private final String color;
    private final int PRICE;
    private Player boothOwner = null;

    public Amusement(String name, int position, String color, int PRICE) {
        super(name, position);
        this.color = color;
        this.PRICE = PRICE;
    }

    public int getPrice() {
        return PRICE;
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

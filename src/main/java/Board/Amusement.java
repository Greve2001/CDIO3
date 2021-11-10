package Board;

import java.awt.*;

public class Amusement extends Square{
    private final Color color;
    private final int price;

    public Amusement(String name, int position, Color color, int price) {
        super(name, position);
        this.color = color;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }


    public Color getColor() {
        return color;
    }
}

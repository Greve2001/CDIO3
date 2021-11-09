package Board;

import java.awt.*;

public class Amusement extends Square{
    private final Color color;
    private final int price;
    private Stand ticketBooth;

    public Amusement(String name, int position, Color color, int price) {
        super(name, position);
        this.color = color;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setTicketBooth(Stand ticketBooth) {
        this.ticketBooth = ticketBooth;
    }

    public Stand getTicketBooth() {
        return ticketBooth;
    }

    public Color getColor() {
        return color;
    }
}

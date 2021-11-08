import java.awt.*;

public class Amusement extends Square{
    private Color color;
    private int price;
    private Stand ticketBooth;

    public Amusement(String name, int position, Color color, int price) {
        super(name, position);
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

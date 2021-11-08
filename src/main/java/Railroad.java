import java.awt.*;

public class Railroad extends Square {
    private Color color;

    public Railroad(int position, Color color) {
        super("Railroad", position);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}

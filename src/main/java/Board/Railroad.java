package Board;

import java.awt.*;

public class Railroad extends Square {
    private final String color;

    public Railroad(int position, String color) {
        super("Railroad", position);
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}

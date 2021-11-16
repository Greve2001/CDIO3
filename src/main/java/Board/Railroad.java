package Board;

public class Railroad extends Square {
    private final String color;

    public Railroad(String name, int position, String color) {
        super(name, position);
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}

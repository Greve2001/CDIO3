package Board;

public class Railroad extends Square {
    private final String COLOR;

    public Railroad(String name, int position, String COLOR) {
        super(name, position);
        this.COLOR = COLOR;
    }

    public String getColor() {
        return COLOR;
    }
}

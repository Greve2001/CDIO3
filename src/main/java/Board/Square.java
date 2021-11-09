package Board;

public class Square {
    private final String name;
    private final int position;

    public Square(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
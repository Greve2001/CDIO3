package Board;

public class Square {
    private final String NAME;
    private final int POSITION;

    public Square(String NAME, int POSITION) {
        this.NAME = NAME;
        this.POSITION = POSITION;
    }

    public String getName() {
        return NAME;
    }

    public int getPosition() {
        return POSITION;
    }}


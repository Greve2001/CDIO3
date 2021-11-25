package Board;

public class GoToRestrooms extends Square {
    private final int DESTINATION;

    public GoToRestrooms(String name, int position, int DESTINATION) {
        super(name, position);
        this.DESTINATION = DESTINATION;
    }

    public int getDestination() {
        return DESTINATION;
    }
}

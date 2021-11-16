package Board;

public class GoToRestrooms extends Square{
    private final int destination;

    public GoToRestrooms(String name, int position, int destination) {
        super(name, position);
        this.destination = destination;
    }

    public int getDestination() {
        return destination;
    }
}

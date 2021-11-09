package Board;

public class GoToRestrooms extends Square{
    private final int dest;

    public GoToRestrooms(int position, int dest) {
        super("Go to the Restrooms", position);
        this.dest = dest;
    }

    public int getDest() {
        return dest;
    }
}

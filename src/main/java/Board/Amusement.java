package Board;

public class Amusement extends Square{
    private final String color;
    private final int price;

    public Amusement(String name, int position, String color, int price) {
        super(name, position);
        this.color = color;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }


    public String getColor() {
        return color;
    }
}

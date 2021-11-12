import java.awt.*;

public class ChanceCard {

    private String chanceCardText;
    private Color color;
    private int amountToPay;
    private int destination;


    /**
     * Instantiates a ChanceCard object that contains a chanceCardText and a destination.
     * @param text represents the text on the chance card, that holds information on what the player must do.
     * @param destination represents a field on the board, that the players token must move to.
     */
    public ChanceCard(String text, int destination){
        this(text, destination, 0);
    }

    /**
     * Instantiates a ChanceCard object that contains a chanceCardText, a destination and an amount to pay.
     * @param text represents the text on the chance card, that holds information on what the player must do.
     * @param destination represents a field on the board, that the players token must move to.
     * @param amountToPay represents the amount of dollars that the player must pay.
     */
    public ChanceCard(String text, int destination, int amountToPay){
        this.chanceCardText = text;
        this.destination = destination;
        this.amountToPay = amountToPay;
    }

    /**
     * Instantiates a ChanceCard object that contains a chanceCardText and a color.
     * @param text represents the text on the chance card, that holds information on what the player must do.
     * @param color represents the color, corresponding to the different colors on the board.
     */
    public ChanceCard(String text, Color color){
        this.chanceCardText = text;
        this.color = color;
    }

    public String getChanceCardText() {
        return chanceCardText;
    }

    public Color getColor() {
        return color;
    }

    public int getAmountToPay() {
        return amountToPay;
    }

    public int getDestination() {
        return destination;
    }
}

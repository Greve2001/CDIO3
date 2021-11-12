import static java.awt.Color.*;

public class Deck {

    private ChanceCard[] chanceCardDeck;
    private int drawCardCount = 0;

    /**
     * Instantiates an array with 24 elements, to hold the chance cards for the game.
     */
    public Deck(){
        this.chanceCardDeck = new ChanceCard[24];

        chanceCardDeck[0] = new ChanceCard("Take a ride on the GREEN LINE RAILROAD and roll again", 14);
        chanceCardDeck[1] = new ChanceCard("Take a ride on the BLUE LINE RAILROAD and roll again", 22);
        chanceCardDeck[2] = new ChanceCard("Take a ride on the RED LINE RAILROAD and roll again", 30);
        chanceCardDeck[3] = new ChanceCard("GO to the WATER SHOW and pay 2 dollars", 25, 2);
        chanceCardDeck[4] = new ChanceCard("GO to the FIREWORKS and pay 2 dollars", 9, 2);
        chanceCardDeck[5] = new ChanceCard("GO to the MERRY-GO-ROUND", 12);
        chanceCardDeck[6] = new ChanceCard("GO to the WATER SLIDE", 15);
        chanceCardDeck[7] = new ChanceCard("GO to the BUMPER CARS", 28);
        chanceCardDeck[8] = new ChanceCard("GO to the LOOP THE LOOP", 31);
        chanceCardDeck[9] = new ChanceCard("GO to the ARCADE VIDEO", 19);
        chanceCardDeck[10] = new ChanceCard("Pay 3 dollars to take the tramway to the restrooms", 11, 3);
        chanceCardDeck[11] = new ChanceCard("GO to GO. Collect 2 dollars allowance as you pass", 1);
        chanceCardDeck[12] = new ChanceCard("Free ticket booth", magenta);
        chanceCardDeck[13] = new ChanceCard("Free ticket booth", blue);
        chanceCardDeck[14] = new ChanceCard("Free ticket booth", red);
        chanceCardDeck[15] = new ChanceCard("Free ticket booth", yellow);
        chanceCardDeck[16] = new ChanceCard("Free ticket booth", blue);
        chanceCardDeck[17] = new ChanceCard("Free ticket booth", white);
        chanceCardDeck[18] = new ChanceCard("Free ticket booth", orange);
        chanceCardDeck[19] = new ChanceCard("Free ticket booth", green);
        chanceCardDeck[20] = new ChanceCard("Free ticket booth", white);
        chanceCardDeck[21] = new ChanceCard("Free ticket booth", yellow);
        chanceCardDeck[22] = new ChanceCard("Free ticket booth", orange);
        chanceCardDeck[23] = new ChanceCard("Free ticket booth", pink); //FIXME: skal ændres til lilla eller mørkeblå?

        shuffleDeck();
    }
    public Deck(ChanceCard[] cards){
       if(cards == null){
           throw new IllegalArgumentException("You cannot initialize an empty array.");
       }
       this.chanceCardDeck = cards;
    }

    private void shuffleDeck(){
        //Implementation otw
    }

    /**
     * @return the ChanceCard that is currently at the top of the deck.
     */
    public ChanceCard pullCard(){
        try{
            return chanceCardDeck[drawCardCount++];

        } catch (ArrayIndexOutOfBoundsException ex){
            // If an ArrayOutOfBoundsException is thrown, the deck is empty and the drawCardCount is reset
            // to 0 making sure that the next card will be drawn from the top of the deck.
            drawCardCount = 0;
            return chanceCardDeck[drawCardCount++];

        }


    }

}

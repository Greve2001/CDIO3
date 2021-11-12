public class Deck {

    private ChanceCard[] chanceCardDeck;
    private int drawCardCount = 0;

    public Deck(){
        this.chanceCardDeck = new ChanceCard[3];

        chanceCardDeck[0] = new ChanceCard();
        chanceCardDeck[1] = new ChanceCard();
        chanceCardDeck[2] = new ChanceCard();

        shuffleDeck();
    }
    public Deck(int deckSize){
        this.chanceCardDeck = new ChanceCard[deckSize];
    }

    public Deck(ChanceCard[] cards){
       this.chanceCardDeck = cards;
    }


    private void shuffleDeck(){
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

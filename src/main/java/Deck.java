public class Deck {

    private ChanceCard[] chanceCardDeck;
    private int drawCardCount = 0;

    public Deck(int deckSize){
       this.chanceCardDeck = new ChanceCard[deckSize];
    }

    public Deck(){
        this.chanceCardDeck = new ChanceCard[3];

        chanceCardDeck[0] = new ChanceCard();
        chanceCardDeck[1] = new ChanceCard();
        chanceCardDeck[2] = new ChanceCard();

        shuffleDeck();
    }


    private void shuffleDeck(){
    }

    public ChanceCard pullCard(){
        // for now hardcoded
        return new ChanceCard();
    }

}

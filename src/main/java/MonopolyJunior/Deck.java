package MonopolyJunior;

import Utilities.Language;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {

    private ChanceCard[] chanceCardDeck;
    private int drawCardCount = 0;

    /**
     * Instantiates an array with 24 elements, to hold the chance cards for the game.
     */
    public Deck() {
        this.chanceCardDeck = new ChanceCard[24];

        chanceCardDeck[0] = new ChanceCard(Language.getText("chanceGreenlineRailroad"), 14);
        chanceCardDeck[1] = new ChanceCard(Language.getText("chanceBluelineRailroad"), 22);
        chanceCardDeck[2] = new ChanceCard(Language.getText("chanceRedlineRailroad"), 30);
        chanceCardDeck[3] = new ChanceCard(Language.getText("chanceGoToWaterShow"), 2);
        chanceCardDeck[4] = new ChanceCard(Language.getText("chanceGoToFireworks"), 9);
        chanceCardDeck[5] = new ChanceCard(Language.getText("chanceGoToMerryGoRound"), 12);
        chanceCardDeck[6] = new ChanceCard(Language.getText("chanceGoToWaterSlide"), 15);
        chanceCardDeck[7] = new ChanceCard(Language.getText("chanceGoToBumperCars"), 28);
        chanceCardDeck[8] = new ChanceCard(Language.getText("chanceGoToLoopTheLoop"), 31);
        chanceCardDeck[9] = new ChanceCard(Language.getText("chanceGoToArcadeVideo"), 19);
        chanceCardDeck[10] = new ChanceCard(Language.getText("chanceGoToTheRestrooms"), 11, 3);
        chanceCardDeck[11] = new ChanceCard(Language.getText("chanceGoToGo"), 1);
        chanceCardDeck[12] = new ChanceCard(Language.getText("chanceFreeTicketBooth"), "magenta");
        chanceCardDeck[13] = new ChanceCard(Language.getText("chanceFreeTicketBooth"), "blue");
        chanceCardDeck[14] = new ChanceCard(Language.getText("chanceFreeTicketBooth"), "red");
        chanceCardDeck[15] = new ChanceCard(Language.getText("chanceFreeTicketBooth"), "yellow");
        chanceCardDeck[16] = new ChanceCard(Language.getText("chanceFreeTicketBooth"), "blue");
        chanceCardDeck[17] = new ChanceCard(Language.getText("chanceFreeTicketBooth"), "turquoise");
        chanceCardDeck[18] = new ChanceCard(Language.getText("chanceFreeTicketBooth"), "orange");
        chanceCardDeck[19] = new ChanceCard(Language.getText("chanceFreeTicketBooth"), "green");
        chanceCardDeck[20] = new ChanceCard(Language.getText("chanceFreeTicketBooth"), "turquoise");
        chanceCardDeck[21] = new ChanceCard(Language.getText("chanceFreeTicketBooth"), "yellow");
        chanceCardDeck[22] = new ChanceCard(Language.getText("chanceFreeTicketBooth"), "orange");
        chanceCardDeck[23] = new ChanceCard(Language.getText("chanceFreeTicketBooth"), "purple");

        shuffleDeck();
    }

    /**
     * One-argument constructor, that initializes the deck with the input array.
     * Primarily used for unit-testing.
     *
     * @param cards
     */
    public Deck(ChanceCard[] cards) {
        if (cards == null) {
            throw new IllegalArgumentException("You cannot initialize an empty array.");
        }
        ChanceCard[] copy = Arrays.copyOf(cards, cards.length); //Makes sure that no changes is applied to original input-array
        this.chanceCardDeck = copy;
    }

    /**
     * Takes the chanceCard array and shuffles it.
     */
    public void shuffleDeck() {
        List<ChanceCard> list = Arrays.asList(chanceCardDeck);
        Collections.shuffle(list);
        chanceCardDeck = list.toArray(new ChanceCard[0]);
    }

    /**
     * @returns the ChanceCard that is currently at the top of the deck.
     */
    public ChanceCard pullCard() {
        try {
            return chanceCardDeck[drawCardCount++];

        } catch (ArrayIndexOutOfBoundsException ex) {
            // If an ArrayOutOfBoundsException is thrown, the deck is empty and the drawCardCount is reset
            // to 0 making sure that the next card will be drawn from the top of the deck.
            drawCardCount = 0;
            return chanceCardDeck[drawCardCount++];

        }

    }
}

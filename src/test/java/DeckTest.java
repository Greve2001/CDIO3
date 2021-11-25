import MonopolyJunior.ChanceCard;
import MonopolyJunior.Deck;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {

    @Test
    public void pullCardShouldReturnChanceCard() {

        Deck testDeck = new Deck(new ChanceCard[]{new ChanceCard("", 0)});

        ChanceCard result = testDeck.pullCard();

        Assertions.assertTrue(result instanceof ChanceCard);
    }

    @Test
    public void pullCardShouldReturnAChanceCardFromTheDeck() {

        ChanceCard testCard = new ChanceCard("", 0);

        Deck testDeck = new Deck(new ChanceCard[]{testCard});

        ChanceCard result = testDeck.pullCard();

        Assertions.assertEquals(testCard, result);

    }

    @Test
    public void pullCardShouldReturnChanceCardWithIndexCorrespondingToDrawCardCount() {
        ChanceCard testCard1 = new ChanceCard("", 0);
        ChanceCard testCard2 = new ChanceCard("", 0);
        ChanceCard testCard3 = new ChanceCard("", 0);

        Deck testDeck = new Deck(new ChanceCard[]{testCard1, testCard2, testCard3});

        ChanceCard result1 = testDeck.pullCard();
        ChanceCard result2 = testDeck.pullCard();
        ChanceCard result3 = testDeck.pullCard();

        Assertions.assertEquals(result1, testCard1);
        Assertions.assertEquals(result2, testCard2);
        Assertions.assertEquals(result3, testCard3);

    }

    @Test
    public void pullCardShouldReturnChanceCardIndex0WhenArrayIsOutOfBounds() {
        ChanceCard testCard0 = new ChanceCard("", 0);
        ChanceCard testCard1 = new ChanceCard("", 0);
        ChanceCard testCard2 = new ChanceCard("", 0);

        Deck testDeck = new Deck(new ChanceCard[]{testCard0, testCard1, testCard2});

        ChanceCard result0 = testDeck.pullCard();
        ChanceCard result1 = testDeck.pullCard();
        ChanceCard result2 = testDeck.pullCard();
        ChanceCard result3 = testDeck.pullCard();

        Assertions.assertEquals(result3, testCard0);
    }

    @Test
    public void shuffleDeckShouldReturnChanceCardsInNewOrder() {

        ChanceCard testCard1 = new ChanceCard("1", 0);
        ChanceCard testCard2 = new ChanceCard("2", 0);
        ChanceCard testCard3 = new ChanceCard("3", 0);
        ChanceCard testCard4 = new ChanceCard("4", 0);
        ChanceCard testCard5 = new ChanceCard("5", 0);

        ChanceCard[] deckBeforeShuffle = new ChanceCard[]{testCard1, testCard2, testCard3, testCard4, testCard5};

        Deck testDeck = new Deck(deckBeforeShuffle);

        testDeck.shuffleDeck();

        ChanceCard result1 = testDeck.pullCard();
        ChanceCard result2 = testDeck.pullCard();
        ChanceCard result3 = testDeck.pullCard();
        ChanceCard result4 = testDeck.pullCard();
        ChanceCard result5 = testDeck.pullCard();

        ChanceCard[] deckAfterShuffle = new ChanceCard[]{result1, result2, result3, result4, result5};

        assertThat(deckAfterShuffle).isNotNull();
        assertThat(deckAfterShuffle).isNotEqualTo(deckBeforeShuffle);
        assertThat(deckAfterShuffle).containsExactlyInAnyOrder(deckBeforeShuffle);

    }

    @Test
    public void changesMadeTheArrayInDeckClassShouldNotApplyToOriginalArray() {
        ChanceCard testCard0 = new ChanceCard("0", 0);
        ChanceCard testCard1 = new ChanceCard("1", 0);
        ChanceCard testCard2 = new ChanceCard("2", 0);


        ChanceCard[] originalArray = {testCard0, testCard1, testCard2};
        Deck testDeck = new Deck(originalArray);

        ChanceCard temp = originalArray[0];
        originalArray[0] = originalArray[1];
        originalArray[1] = temp;

        assertThat(testDeck.pullCard()).isEqualTo(testCard0);


    }
}

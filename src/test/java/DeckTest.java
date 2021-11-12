import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    public void pullCardShouldReturnChanceCard(){

        Deck testDeck = new Deck(new ChanceCard[]{new ChanceCard()});

        ChanceCard result = testDeck.pullCard();

        Assertions.assertTrue(result instanceof ChanceCard);
    }

    @Test
    public void pullCardShouldReturnAChanceCardFromTheDeck(){

        ChanceCard testCard = new ChanceCard();

        Deck testDeck = new Deck(new ChanceCard[]{testCard});

        ChanceCard result = testDeck.pullCard();

        Assertions.assertEquals(testCard, result);

    }

    @Test
    public void pullCardShouldReturnIndex0WhenArrayOutOfBoundsExceptionIsThrown(){

        ChanceCard testCard1 = new ChanceCard();
        ChanceCard testCard2 = new ChanceCard();
        ChanceCard testCard3 = new ChanceCard();

        Deck testDeck = new Deck(new ChanceCard[]{testCard1, testCard2, testCard3});

        ChanceCard result1 = testDeck.pullCard();
        ChanceCard result2 = testDeck.pullCard();
        ChanceCard result3 = testDeck.pullCard();
        ChanceCard result4 = testDeck.pullCard();

        Assertions.assertEquals(testCard1, result4);

    }

    @Test
    public void pullCardShouldReturnChanceCardWithIndexCorrespondingToDrawCardCount(){
        ChanceCard testCard1 = new ChanceCard();
        ChanceCard testCard2 = new ChanceCard();
        ChanceCard testCard3 = new ChanceCard();

        Deck testDeck = new Deck(new ChanceCard[]{testCard1, testCard2, testCard3});

        ChanceCard result1 = testDeck.pullCard();
        ChanceCard result2 = testDeck.pullCard();
        ChanceCard result3 = testDeck.pullCard();
        ChanceCard result4 = testDeck.pullCard();

        Assertions.assertEquals(result1, testCard1);
        Assertions.assertEquals(result2, testCard2);
        Assertions.assertEquals(result3, testCard3);
        Assertions.assertEquals(result4, testCard1);


    }
}

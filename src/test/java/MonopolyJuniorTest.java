import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonopolyJuniorTest {
    MonopolyJunior game;

    @BeforeEach
    void setup(){
        MonopolyJunior game = new MonopolyJunior(4);
        this.game = game;
    }

    @Test
    void testPaymentPossible(){
        int[] testAmount = {0, 20, 20, 10, -20}; // Cant use 33bit int
        int[] testBalance = {10, 10, -10, 20, 0};
        boolean[] expected = {true, false, false, true, false};

        for (int i = 0; i < testAmount.length; i++) {
            Player player = new Player();
            player.setupStartBalance(testBalance[i]);
            boolean result = this.game.paymentPossible(player, testAmount[i]);
            assertEquals(result, expected[i]);
        }


    }
}
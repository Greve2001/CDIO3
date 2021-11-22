import MonopolyJunior.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
        Player player = new Player("Name");

    @Test
    void HasBoothEqualsANegativeValueOrZero() {
        player.setBooths(-3);
        boolean actual = player.hasBooth();
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
        player.setBalance(0);
        Assertions.assertEquals(expected,actual);


    }
    @Test
    void PlusOrMinusValuesToUpdateBalance() {
        player.setBalance(200);
        //When input is a negative value.
        player.updateBalance(-15);
        int actual = 185;

        assertEquals(player.getBalance(),actual);

        player.setBalance(200);
        //When input is a Positiv value
        player.updateBalance(+15);
        actual = 215;

        assertEquals(player.getBalance(),actual);
    }

}
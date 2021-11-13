import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonopolyJuniorTest {
    MonopolyJunior game;
    Player player1;
    Player player2;

    @BeforeEach void setup(){
        game = new MonopolyJunior(4);
        player1 = new Player();
        player2 = new Player();
    }

    @Test
    void setupGame() {
    }

    @Test
    void playGame() {
    }

    @Test
    void initializePlayers() {
    }

    @Test
    void takeTurn() {
    }

    @Test
    void changePlayer() {
    }

    @Test
    void gethasWinner() {
    }

    @Test
    void updatePosition() {
    }

    @Test
    void hasPassStart() {
    }

    @Test
    void payFromTo() {
        //Valid payment
        player1.setupStartBalance(10);
        player2.setupStartBalance(10);
        game.pay(player1, player2, 5);

        int acutal1 = player1.getBalance();
        int acutal2 = player2.getBalance();
        int expected1 = 5;
        int expected2 = 15;
        assertEquals(expected1, acutal1);
        assertEquals(expected2, acutal2);


        //Pay as much as possible
        player1.setupStartBalance(10);
        player2.setupStartBalance(10);
        game.pay(player1, player2, 15);

        acutal1 = player1.getBalance();
        acutal2 = player2.getBalance();
        expected1 = 0;
        expected2 = 20;
        assertEquals(expected1, acutal1);
        assertEquals(expected2, acutal2);

        //Negative balance payment, should not be possible
        player1.setupStartBalance(-10);
        player2.setupStartBalance(10);
        game.pay(player1, player2, 15);

        acutal1 = player1.getBalance();
        acutal2 = player2.getBalance();
        expected1 = -10;
        expected2 = 10;
        assertEquals(expected1, acutal1);
        assertEquals(expected2, acutal2);
    }

    @Test
    void PayFrom() {
        // Valid base case
        player1.setupStartBalance(10);
        game.pay(player1, 5);
        int actual = player1.getBalance();
        int expected = 5;
        assertEquals(expected, actual);

        // Invalid payment
        player1.setupStartBalance(10);
        game.pay(player1, 15);
        actual = player1.getBalance();
        expected = 0;
        assertEquals(expected, actual);

        // No payment because of negative balance. (Should actually never reach negative balance, but who knows)
        player1.setupStartBalance(-10);
        game.pay(player1, 15);
        actual = player1.getBalance();
        expected = -10;
        assertEquals(expected, actual);
    }

    @Test
    void paymentPossible() {
    }

    @Test
    void decideAndAnnounceWinner() {
    }
}
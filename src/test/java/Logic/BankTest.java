package Logic;

import MonopolyJunior.GUIController2;
import MonopolyJunior.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    GameController g = new GameController();

    Bank bank;
    Player player1;
    Player player2;

    @BeforeEach
    void setup() {
        g.setupGame();
        bank = new Bank();
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
    }



    // *** Pay to Bank test *** //
    @Test
    void normalCaseValidPaymentToBank(){
        player1.setBalance(10);
        bank.payToBank(player1, 5);

        int actual = player1.getBalance();
        int expected = 5;

        assertEquals(expected, actual);
    }

    @Test
    void borderCaseValidPaymentToBank(){
        player1.setBalance(10);
        bank.payToBank(player1, 10);

        int actual = player1.getBalance();
        int expected = 0;

        assertEquals(expected, actual);
    }

    @Test
    void partialPaymentToBank(){ // Should only pay as much as in bank account
        player1.setBalance(10);
        bank.payToBank(player1, 15);

        int actual = player1.getBalance();
        int expected = 0;

        assertEquals(expected, actual);
    }

    @Test
    void invalidPaymentToBank(){
        player1.setBalance(10);
        bank.payToBank(player1, -15);

        int actual = player1.getBalance();
        int expected = 10;

        assertEquals(expected, actual);
    }



    // *** Pay to player *** //

    @Test
    void normalValidCasePaymentToPlayer(){
        player1.setBalance(10);
        player2.setBalance(10);

        bank.payToPlayer(player1, player2, 5);

        int actual1 = player1.getBalance();
        int actual2 = player2.getBalance();

        int expected1 = 5;
        int expected2 = 15;

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

    @Test
    void borderCasePaymentToPlayer(){
        player1.setBalance(10);
        player2.setBalance(10);

        bank.payToPlayer(player1, player2, 10);

        int actual1 = player1.getBalance();
        int actual2 = player2.getBalance();

        int expected1 = 0;
        int expected2 = 20;

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

    @Test
    void partialPaymentToPlayer(){ // Should not pay more than already in balance
        player1.setBalance(10);
        player2.setBalance(10);

        bank.payToPlayer(player1, player2, 15);

        int actual1 = player1.getBalance();
        int actual2 = player2.getBalance();

        int expected1 = 0;
        int expected2 = 20;

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

    @Test
    void invalidPaymentToPlayer(){
        player1.setBalance(10);
        player2.setBalance(10);

        bank.payToPlayer(player1, player2, -5);

        int actual1 = player1.getBalance();
        int actual2 = player2.getBalance();

        int expected1 = 10;
        int expected2 = 10;

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

}
import MonopolyJunior.MonopolyJunior_old;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import MonopolyJunior.*;

import static org.junit.jupiter.api.Assertions.*;

class MonopolyJuniorOldTest {
    MonopolyJunior_old game;
    Player player1;
    Player player2;
    Player[] curPlayers = {};
    Field players;
    Player cP;
    Field currentPlayer;

    @BeforeEach void setup() throws NoSuchFieldException {
        game = new MonopolyJunior_old();
        player1 = new Player("Player1");
        player2 = new Player("Player2");


    }

    @Test
    void AmountOfTicketBoothsGivenOnSetup() throws NoSuchFieldException, IllegalAccessException {
        // **** Test of num of booths with 2 players ****
        game.setupGame(2);
        getPlayers();

        for (Player p : curPlayers){
            // Get boothsOnHand variable, which is normally private
            Field numOfBooths = p.getClass().getDeclaredField("boothsOnHand");
            numOfBooths.setAccessible(true);
            int curBoothsOnHand = (int) numOfBooths.get(p);

            int actual = curBoothsOnHand;
            int expected = 12;
            assertEquals(expected, actual);
        }

        // **** Test of num of booths with more than 2 players, that is allowed by rules ****
        game.setupGame(4);
        getPlayers();

        for (Player p : curPlayers){
            // Get boothsOnHand variable, which is normally private
            Field numOfBooths = p.getClass().getDeclaredField("boothsOnHand");
            numOfBooths.setAccessible(true);
            int curBoothsOnHand = (int) numOfBooths.get(p);

            int actual = curBoothsOnHand;
            int expected = 10;
            assertEquals(expected, actual);
        }
    }

    @Test
    void OnlyAllowNumberOfPlayersAccordingToRules() throws NoSuchFieldException, IllegalAccessException {
        // **** Minimum of players ****
        game = new MonopolyJunior_old();
        game.setupGame(2);
        getPlayers();

        int actual = curPlayers.length;
        int expected = 2;
        assertEquals(expected, actual);



        // **** Max players ****
        game = new MonopolyJunior_old();
        game.setupGame(4);
        getPlayers();

        actual = curPlayers.length;
        expected = 4;
        assertEquals(expected, actual);



        // **** Under minimum ****
        game = new MonopolyJunior_old();
        game.setupGame(-2);
        getPlayers();

        assertNull(curPlayers);


        // **** Over max ****
        game = new MonopolyJunior_old();
        game.setupGame(10);
        getPlayers();

        assertNull(curPlayers);

        // **** Zero exception? ****
        game = new MonopolyJunior_old();
        game.setupGame(0);
        getPlayers();

        assertNull(curPlayers);

    }


    @Test
    void initializePlayers() {
    }

    @Test
    void playGame() {
    }

    @Test
    void takeTurn() {
    }

    @Test
    void testChangePlayer() throws NoSuchFieldException, IllegalAccessException {
        // **** Base case ****
        game.setupGame(4);
        getPlayers();

        game.changePlayer();

        getCurrentPlayer();
        Player actual = cP;
        Player expected = curPlayers[1];

        assertEquals(expected.getName(), actual.getName());

        // **** Limit case ****
        // Beacuse we have no method to set the variable we get from getCurrentPlayers
        // We will just do it manually:
        game.setupGame(4);
        getPlayers();

        game.changePlayer();
        game.changePlayer();
        game.changePlayer();
        game.changePlayer();

        getCurrentPlayer();
        actual = cP;
        expected = curPlayers[0];

        assertEquals(expected.getName(), actual.getName());
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
        //game.pay(player1, player2, 5);

        int acutal1 = player1.getBalance();
        int acutal2 = player2.getBalance();
        int expected1 = 5;
        int expected2 = 15;
        assertEquals(expected1, acutal1);
        assertEquals(expected2, acutal2);


        //Pay as much as possible
        player1.setupStartBalance(10);
        player2.setupStartBalance(10);
        //game.pay(player1, player2, 15);

        acutal1 = player1.getBalance();
        acutal2 = player2.getBalance();
        expected1 = 0;
        expected2 = 20;
        assertEquals(expected1, acutal1);
        assertEquals(expected2, acutal2);

        //Negative balance payment, should not be possible
        player1.setupStartBalance(-10);
        player2.setupStartBalance(10);
        //game.pay(player1, player2, 15);

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

    // utility methods
    void getPlayers() throws NoSuchFieldException, IllegalAccessException {
        // Get players, which normally are private.
        players = game.getClass().getDeclaredField("players");
        players.setAccessible(true);
        curPlayers = (Player[]) players.get(game);
    }

    void getCurrentPlayer() throws NoSuchFieldException, IllegalAccessException {
        // Get currentPlayer, which is private
        currentPlayer = game.getClass().getDeclaredField("currentPlayer");
        currentPlayer.setAccessible(true);
        cP = (Player) currentPlayer.get(game);
    }

}
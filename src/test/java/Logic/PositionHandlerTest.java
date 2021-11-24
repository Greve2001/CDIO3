package Logic;

import MonopolyJunior.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionHandlerTest {

    Player player;
    PositionHandler posHandler;

    @BeforeEach
    void setup(){
        posHandler = new PositionHandler(32, 2);
    }


    @Test
    void normalCaseSetPlayer(){
        posHandler.setPlayerPosition(player, 7, true);

        int actual = player.getPosition();
        int expected = 7;

        assertEquals(expected, actual);
    }


    // *** SetPlayer method *** //
    @Test
    void aroundToStartBonusBorderCaseSetPlayer(){
        posHandler.setPlayerPosition(player, 0, true);

        // Position
        int actualPos = player.getPosition();
        int expectedPos = 0;
        assertEquals(expectedPos, actualPos);

        // Start bonus applied
        int actualBalance = player.getBalance();
        int expectedBalance = 2;
        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    void aroundToStartNoBonusBorderCaseSetPlayer(){
        posHandler.setPlayerPosition(player, 0, false);

        // Position
        int actualPos = player.getPosition();
        int expectedPos = 0;
        assertEquals(expectedPos, actualPos);

        // Start bonus applied
        int actualBalance = player.getBalance();
        int expectedBalance = 0;
        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    void aroundToEndBonusBorderCaseSetPlayer(){
        posHandler.setPlayerPosition(player, 32, true);

        // Position
        int actualPos = player.getPosition();
        int expectedPos = 32;
        assertEquals(expectedPos, actualPos);

        // Start bonus applied
        int actualBalance = player.getBalance();
        int expectedBalance = 0;
        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    void aroundToEndNoBonusBorderCaseSetPlayer(){
        posHandler.setPlayerPosition(player, 32, false);

        // Position
        int actualPos = player.getPosition();
        int expectedPos = 32;
        assertEquals(expectedPos, actualPos);

        // Start bonus applied
        int actualBalance = player.getBalance();
        int expectedBalance = 0;
        assertEquals(expectedBalance, actualBalance);
    }



    // *** MovePlayer method *** //
    @Test
    void normalCaseMovePlayer(){
        posHandler.movePlayer(player, 5);

        // Position
        int actualPos = player.getPosition();
        int expectedPos = 6; // 1+5
        assertEquals(expectedPos, actualPos);
    }

    @Test
    void sequentialCasePlayerMoves(){
        posHandler.movePlayer(player, 5);
        int actualPos = player.getPosition();
        int expectedPos = 6; // 1+5
        assertEquals(expectedPos, actualPos);

        posHandler.movePlayer(player, 3);
        actualPos = player.getPosition();
        expectedPos = 9; // 1+5+3
        assertEquals(expectedPos, actualPos);

        posHandler.movePlayer(player, 6);
        actualPos = player.getPosition();
        expectedPos = 15; // 1+5
        assertEquals(expectedPos, actualPos);
    }

    @Test
    void moveAcrossStartCase(){
        posHandler.setPlayerPosition(player, 30, false);

        posHandler.movePlayer(player, 5);

        // Position
        int actualPos = player.getPosition();
        int expectedPos = 3; // 30+5 -32
        assertEquals(expectedPos, actualPos);

        // Balance
        int actualBalance = player.getBalance();
        int expectedBalance = 2;
        assertEquals(expectedBalance, actualBalance);
    }

    @Test // Hvad forventer vi, kan man bevæge sig bagud????
    void moveNegativeSpacesCase(){
        posHandler.movePlayer(player, -5);

        // Position
        int actualPos = player.getPosition();
        int expectedPos = 28; // 33 or 1 -> 28
        assertEquals(expectedPos, actualPos);
    }
}
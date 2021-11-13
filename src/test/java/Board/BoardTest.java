package Board;

import MonopolyJunior.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void GetSubClass() {
        String result = board.getSquare(1).getClass().getSimpleName();
        String expected = "Go";

        assertEquals(expected, result);
    }

    @Test
    void allArrayFieldInit() throws NoSuchFieldException, IllegalAccessException {
        Field allSquares = board.getClass().getDeclaredField("allSquares");
        allSquares.setAccessible(true);
        Square[] arr =(Square[]) (allSquares.get(board));

        int index = 1;
        for (Square square : arr) {
            if(square == null)
                System.out.println("Index: " + index + " " + square);
            index++;

            assertNotNull(square);
        }
    }

    @Test
    void hasNotMonopoly() {
        Player player = new Player("Spiller1");
        Amusement amusement = (Amusement) board.getSquare(3);
        amusement.setBoothOwner(player);

        assertFalse(board.hasMonopoly(3));
    }

    @Test
    void hasMonopoly() {
        Player player = new Player("Spiller1");
        Amusement amusement = (Amusement) board.getSquare(3);
        amusement.setBoothOwner(player);

        amusement = (Amusement) board.getSquare(4);
        amusement.setBoothOwner(player);

        assertTrue(board.hasMonopoly(3));
    }

    @Test
    void readCSV() {
    }
}
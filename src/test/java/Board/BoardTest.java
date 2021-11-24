package Board;

import MonopolyJunior.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void ensureDifferentTypesOfSquares(){
        String prevSquareType = board.getSquare(1).getClass().getSimpleName();

        for (Square square : board.getAllSquares()){
            String newSquareType = square.getClass().getSimpleName();
            if (prevSquareType != newSquareType){
                assertTrue(true);
            }
        }
    }

    @Test
    void allArrayFieldInit() {
        Square[] arr = board.getAllSquares();

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
    void getSquarePosByColor() {
        int[] actual = board.getSquarePosByColor("Purple");
        int[] expected = {3, 4};

        assertArrayEquals(expected, actual);
    }
}
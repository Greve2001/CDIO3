package Board;

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
    void allArrayFieldInit() throws NoSuchFieldException {
        Field allSquares = board.getClass().getDeclaredField("allSquares");
        allSquares.setAccessible(true);

        Square[] arr = board.allSquares;

        int index = 1;

        for (Square square : arr) {
            if(square == null)
                System.out.println("Index: " + index + " " + square);
            index++;

            assertNotNull(square);
        }
    }

    @Test
    void readCSV() {
    }
}
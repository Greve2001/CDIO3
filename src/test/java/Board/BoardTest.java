package Board;

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
    void readCSV() {
    }
}
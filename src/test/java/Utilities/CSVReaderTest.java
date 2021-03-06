package Utilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CSVReaderTest {
    private CSVReader reader;

    @BeforeEach
    void setUp() {
        reader = new CSVReader("en_board.csv", ",");
    }

    @Test
    void getColumnNames() {
        String[] expected = {"Position", "Type",
                "Name", "AmountGiven", "Price", "Color",
                "AmountToPay", "Dest"};

        String[] actual = reader.getColumnNames();

        assertTrue(Arrays.equals(expected, actual));
    }

    @Test
    void getFILE_AS_LIST_OF_HASHMAPS() {
    }
}
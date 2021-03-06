package Utilities;

import Utilities.OverloadList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OverloadListTest {

    @Test
    void addInt() {
        OverloadList list = new OverloadList("int");

        for (int i = 0; i < 22; i++) {
            list.add(1);
        }

        assertEquals(22, list.size());
    }

    @Test
    void getInt() {
        OverloadList list = new OverloadList("int");

        for (int i = 0; i < 22; i++) {
            list.add(1);
        }

        assertEquals(1, list.getInt(1));
    }

    @Test
    void getIntList() {
        OverloadList list = new OverloadList("int");

        for (int i = 0; i < 22; i++) {
            list.add(1);
        }

        int[] expected = {
                1, 1, 1, 1, 1,
                1, 1, 1, 1, 1,
                1, 1, 1, 1, 1,
                1, 1, 1, 1, 1,
                1, 1,
        };

        assertArrayEquals(list.getListOfInts(), expected);
    }

    @Test
    void stringArrOutPutTest() {
        OverloadList list = new OverloadList("String[]");

        String[] myArr = {"Name", "Type"};
        list.add(myArr);

        String[] actual = list.getStringArr(0);

        assertArrayEquals(myArr, actual);
    }
}
package Board;

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

        assertEquals(1, list.get(1,0));
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
                1, 1, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
        };

        assertArrayEquals(list.getList(0), expected);
    }

    @Test
    void generalTesting() {
        OverloadList list = new OverloadList("String[]");

        String[] myArr = {"Name", "Type"};

        list.add(myArr);

        System.out.println("test");

        String[] myListArr = list.get(0, new String[1]);


        System.out.println(myListArr[0]);
    }
}
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

        System.out.println(list.get(0,1));

        assertEquals(1, list.get(1,0));
        assertEquals(22, list.size());
    }

    @Test
    void getInt() {
    }

    @Test
    void getIntList() {
    }

    @Test
    void addString() {
    }

    @Test
    void getString() {
    }

    @Test
    void getStringList() {
    }

    @Test
    void size() {
    }
}
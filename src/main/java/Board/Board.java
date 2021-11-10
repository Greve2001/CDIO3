package Board;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class Board {

    HashMap<String, String> row = new HashMap<String, String>();

    public Board (int boardSize){

    }


    public void readCSV(String file) {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStreamReader reader = new InputStreamReader(Objects.requireNonNull(classLoader.getResourceAsStream(file)));

        Scanner scan = new Scanner(reader);
        scan.useDelimiter(",");

        while(scan.hasNext()) {
            System.out.print(scan.next() + " ");
        }

        scan.close();

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

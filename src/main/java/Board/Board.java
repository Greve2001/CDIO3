package Board;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Board {

    List<HashMap<String, String>> mapList = new ArrayList<>();

    public Board (){
        readCSV("board.csv");

        for (int i = 0; i < mapList.toArray().length; i++) {
            switch (mapList.get(i).get("type")) {

            }
        }

    }

    public void readCSV(String file) {
        ClassLoader classLoader = Board.class.getClassLoader();
        InputStreamReader reader = new InputStreamReader(Objects.requireNonNull(classLoader.getResourceAsStream(file)));

        Scanner scan = new Scanner(reader);
        Scanner lineReader;

        int index = 0;
        int line = 0;

        while(scan.hasNextLine()) {
            lineReader = new Scanner(scan.nextLine());
            lineReader.useDelimiter(",");

            mapList.add(new HashMap<String, String>());

            while(lineReader.hasNext()) {
                switch (index) {
                    case 0 : mapList.get(line).put("pos", lineReader.next());         break;
                    case 1 : mapList.get(line).put("type", lineReader.next());        break;
                    case 2 : mapList.get(line).put("name", lineReader.next());        break;
                    case 3 : mapList.get(line).put("amountGiven", lineReader.next()); break;
                    case 4 : mapList.get(line).put("price", lineReader.next());       break;
                    case 5 : mapList.get(line).put("color", lineReader.next());       break;
                    case 6 : mapList.get(line).put("amountToPay", lineReader.next()); break;
                    case 7 : mapList.get(line).put("dest", lineReader.next());        break;
                }

                index++;
            }

            index = 0;
            line++;
        }

        scan.close();

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

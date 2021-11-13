package Board;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Board {

    List<HashMap<String, String>> mapList = new ArrayList<>();
    Square[] allSquares;

    public Board (){
        readCSV("board.csv");

        allSquares = new Square[mapList.size() - 1];

        // initialises the objects based on the hashmap
        for (int i = 0; i < mapList.toArray().length; i++) {
            HashMap<String, String> currentSquare = mapList.get(i);

            switch (currentSquare.get("type")) {
                case "GO!" :
                    allSquares[Integer.parseInt(currentSquare.get("pos")) - 1] =
                            new Go(Integer.parseInt(currentSquare.get("amountGiven")),
                                    Integer.parseInt(currentSquare.get("pos")));
                    break;
                case "Amusement" :
                    allSquares[Integer.parseInt(currentSquare.get("pos")) - 1] =
                        new Amusement(currentSquare.get("name"),
                                Integer.parseInt(currentSquare.get("pos")),
                                currentSquare.get("color"),
                                Integer.parseInt(currentSquare.get("price")));
                    break;
                case "Chance" :
                    allSquares[Integer.parseInt(currentSquare.get("pos")) - 1] =
                        new Chance(Integer.parseInt(currentSquare.get("pos")));
                    break;
                case "Railroad" :
                    allSquares[Integer.parseInt(currentSquare.get("pos")) - 1] =
                        new Railroad(Integer.parseInt(currentSquare.get("pos")),
                                currentSquare.get("color"));
                    break;
                case "PayToSee" :
                    allSquares[Integer.parseInt(currentSquare.get("pos")) - 1] =
                            new PayToSee(currentSquare.get("name"),
                                    Integer.parseInt(currentSquare.get("pos")),
                                    Integer.parseInt(currentSquare.get("pos")));
                    break;
                case "GoTo" :
                    allSquares[Integer.parseInt(currentSquare.get("pos")) - 1] =
                            new GoToRestrooms(Integer.parseInt(currentSquare.get("pos")),
                                    Integer.parseInt(currentSquare.get("dest")));
                    break;
                case "GetMoney" :
                    allSquares[Integer.parseInt(currentSquare.get("pos")) - 1] =
                            new PennyBag(currentSquare.get("name"),
                                    Integer.parseInt(currentSquare.get("pos")));
                    break;
                case "Restrooms" :
                    allSquares[Integer.parseInt(currentSquare.get("pos")) - 1] =
                            new Restrooms(Integer.parseInt(currentSquare.get("pos")));
                    break;
             }
        }

    }

    // Reads a CSV file an stores the result as a list of hashmaps
    public void readCSV(String file) {
        // Gets the class loader and reads the file from the ressources folder
        ClassLoader classLoader = Board.class.getClassLoader();
        InputStreamReader reader = new InputStreamReader(Objects.requireNonNull(classLoader.getResourceAsStream(file)));

        // Creates a scanner that reads the inputstream
        Scanner scan = new Scanner(reader);
        Scanner lineReader;

        int index = 0;
        int line = 0;

        // Scans one line at a time and then breaks up the line using the delimiter ","
        while(scan.hasNextLine()) {
            lineReader = new Scanner(scan.nextLine());
            lineReader.useDelimiter(",");

            mapList.add(new HashMap<String, String>());

            // puts the values to the hashmap
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

    public Square getSquare(int pos) {
        return allSquares[pos - 1];
    }
}

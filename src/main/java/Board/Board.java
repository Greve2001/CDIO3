package Board;

import MonopolyJunior.Player;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Board {

    private final int OFFSET = 1;

    private List<HashMap<String, String>> mapList = new ArrayList<>();
    private Square[] allSquares;

    public Board (){
        readCSV("board.csv");

        allSquares = new Square[mapList.size() - OFFSET];

        // initialises the objects in an array based on the hashmap
        for (int i = 0; i < mapList.toArray().length; i++) {
            HashMap<String, String> currentSquare = mapList.get(i);

            switch (currentSquare.get("type")) {
                case "GO!" :
                    allSquares[Integer.parseInt(currentSquare.get("pos")) - OFFSET] =
                            new Go(Integer.parseInt(currentSquare.get("amountGiven")),
                                    Integer.parseInt(currentSquare.get("pos")));
                    break;
                case "Amusement" :
                    allSquares[Integer.parseInt(currentSquare.get("pos")) - OFFSET] =
                        new Amusement(currentSquare.get("name"),
                                Integer.parseInt(currentSquare.get("pos")),
                                currentSquare.get("color"),
                                Integer.parseInt(currentSquare.get("price")));
                    break;
                case "Chance" :
                    allSquares[Integer.parseInt(currentSquare.get("pos")) - OFFSET] =
                        new Chance(Integer.parseInt(currentSquare.get("pos")));
                    break;
                case "Railroad" :
                    allSquares[Integer.parseInt(currentSquare.get("pos")) - OFFSET] =
                        new Railroad(Integer.parseInt(currentSquare.get("pos")),
                                currentSquare.get("color"));
                    break;
                case "PayToSee" :
                    allSquares[Integer.parseInt(currentSquare.get("pos")) - OFFSET] =
                            new PayToSee(currentSquare.get("name"),
                                    Integer.parseInt(currentSquare.get("pos")),
                                    Integer.parseInt(currentSquare.get("pos")));
                    break;
                case "GoTo" :
                    allSquares[Integer.parseInt(currentSquare.get("pos")) - OFFSET] =
                            new GoToRestrooms(Integer.parseInt(currentSquare.get("pos")),
                                    Integer.parseInt(currentSquare.get("dest")));
                    break;
                case "GetMoney" :
                    allSquares[Integer.parseInt(currentSquare.get("pos")) - OFFSET] =
                            new PennyBag(currentSquare.get("name"),
                                    Integer.parseInt(currentSquare.get("pos")));
                    break;
                case "Restrooms" :
                    allSquares[Integer.parseInt(currentSquare.get("pos")) - OFFSET] =
                            new Restrooms(Integer.parseInt(currentSquare.get("pos")));
                    break;
             }
        }

    }

    // Reads a CSV file and stores the result as a list of hashmaps
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
                    case 0 :
                        mapList.get(line).put("pos", lineReader.next());
                    break;
                    case 1 :
                        mapList.get(line).put("type", lineReader.next());
                        break;
                    case 2 :
                        mapList.get(line).put("name", lineReader.next());
                    break;
                    case 3 :
                        mapList.get(line).put("amountGiven", lineReader.next());
                    break;
                    case 4 :
                        mapList.get(line).put("price", lineReader.next());
                    break;
                    case 5 :
                        mapList.get(line).put("color", lineReader.next());
                    break;
                    case 6 :
                        mapList.get(line).put("amountToPay", lineReader.next());
                    break;
                    case 7 :
                        mapList.get(line).put("dest", lineReader.next());
                    break;
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

    public void addBooth(Player player, int position) {
        Amusement amusement = (Amusement) getSquare(position);
        amusement.setBoothOwner(player);
    }

    public void removeBooth(int position) {
        Amusement amusement = (Amusement) getSquare(position);
        amusement.setBoothOwner(null);
    }

    public boolean hasMonopoly(int position) {
        Amusement amusement = (Amusement) getSquare(position);
        boolean result = true;

        for (int i = 0; i < allSquares.length; i++) {
            if(allSquares[i].getClass().getSimpleName().equals("Amusement")) {
                if(i + OFFSET != position && ((Amusement) allSquares[i]).getColor().equals(amusement.getColor()) ) {
                    if (amusement.getBoothOwner() == null ||
                                    ((Amusement) allSquares[i]).getBoothOwner() == null) {
                        result = false;
                    }
                    else if(!(((Amusement) allSquares[i]).getBoothOwner().equals(amusement.getBoothOwner()))) {

                        result = false;
                    }
                }
            }
        }

        return result;
    }

    public Integer[] getSquarePosByColor(String color) {
        List<Integer> listOfPositions = new ArrayList<>();

        for (int i = 0; i < allSquares.length; i++) {
            if (((Amusement) allSquares[i]).getColor().equals(color)) {
                listOfPositions.add((allSquares[i].getPosition()));
            }
        }

        return (Integer[]) listOfPositions.toArray();
    }

    public int getFistPosOfSquareByType(String type) {
        int result = -1;
        for (int i = 0; i < allSquares.length; i++) {
            if (allSquares[i].getClass().getSimpleName().equals(type))
                result = allSquares[i].getPosition();
        }
        return result;
    }

    public Square[] getAllSquares() {
        return allSquares;
    }

    public Square getSquare(int position) {
        return allSquares[position - OFFSET];
    }
}

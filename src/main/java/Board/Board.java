package Board;

import MonopolyJunior.Player;

import java.util.*;

public class Board {
    private final int OFFSET = 1;
    private final Square[] allSquares;

    public Board (){
        // Loads the information from the board.csv in ressources.
        CSVReader reader = new CSVReader("board.csv", ",");
        String[] columnNames = reader.getColumnNames();
        List<HashMap<String, String>> listOfAllSquareAndProps = reader.getFILE_AS_LIST_OF_HASHMAPS();

        allSquares = new Square[listOfAllSquareAndProps.size()];

        // initialises the objects in an array based on the hashmap
        for (int i = 0; i < listOfAllSquareAndProps.toArray().length; i++) {
            HashMap<String, String> currentSquare = listOfAllSquareAndProps.get(i);

            // Column names is expected to be in the following order in the columnNames:
            // 0 Position; 1 Type; 2 Name; 3 AmountGiven; 4 Price; 5 Color; 6 AmountToPay; 7 Dest
            switch (currentSquare.get(columnNames[1])) {
                case "GO!" :
                    allSquares[Integer.parseInt(currentSquare.get(columnNames[0])) - OFFSET] =
                            new Go(currentSquare.get(columnNames[2]),
                                    Integer.parseInt(currentSquare.get(columnNames[0])),
                                    Integer.parseInt(currentSquare.get(columnNames[3])));
                    break;
                case "Amusement" :
                    allSquares[Integer.parseInt(currentSquare.get(columnNames[0])) - OFFSET] =
                        new Amusement(currentSquare.get(columnNames[2]),
                                Integer.parseInt(currentSquare.get(columnNames[0])),
                                currentSquare.get(columnNames[5]),
                                Integer.parseInt(currentSquare.get(columnNames[4])));
                    break;
                case "Chance" :
                    allSquares[Integer.parseInt(currentSquare.get(columnNames[0])) - OFFSET] =
                        new Chance(currentSquare.get(columnNames[2]),
                                Integer.parseInt(currentSquare.get(columnNames[0])));
                    break;
                case "Railroad" :
                    allSquares[Integer.parseInt(currentSquare.get(columnNames[0])) - OFFSET] =
                        new Railroad(currentSquare.get(columnNames[3]),
                                Integer.parseInt(currentSquare.get(columnNames[0])),
                                currentSquare.get(columnNames[5]));
                    break;
                case "PayToSee" :
                    allSquares[Integer.parseInt(currentSquare.get(columnNames[0])) - OFFSET] =
                            new PayToSee(currentSquare.get(columnNames[2]),
                                    Integer.parseInt(currentSquare.get(columnNames[0])),
                                    Integer.parseInt(currentSquare.get(columnNames[6])));
                    break;
                case "GoTo" :
                    allSquares[Integer.parseInt(currentSquare.get(columnNames[0])) - OFFSET] =
                            new GoToRestrooms(currentSquare.get(columnNames[2]),
                                    Integer.parseInt(currentSquare.get(columnNames[0])),
                                    Integer.parseInt(currentSquare.get(columnNames[7])));
                    break;
                case "GetMoney" :
                    allSquares[Integer.parseInt(currentSquare.get(columnNames[0])) - OFFSET] =
                            new PennyBag(currentSquare.get(columnNames[2]),
                                    Integer.parseInt(currentSquare.get(columnNames[0])));
                    break;
                case "Restrooms" :
                    allSquares[Integer.parseInt(currentSquare.get(columnNames[0])) - OFFSET] =
                            new Restrooms(currentSquare.get(columnNames[2]),
                                    Integer.parseInt(currentSquare.get(columnNames[0])));
                    break;
             }
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

    // Check squares of same color on the entire board for if they have the same owner
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

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
            switch (currentSquare.get(columnNames[1])) {                                            // type
                case "GO!" :
                    allSquares[Integer.parseInt(currentSquare.get(columnNames[0])) - OFFSET] =
                            new Go(currentSquare.get(columnNames[2]),                               // name
                                    Integer.parseInt(currentSquare.get(columnNames[0])),            // position
                                    Integer.parseInt(currentSquare.get(columnNames[3])));           // amountGiven
                    break;
                case "Amusement" :
                    allSquares[Integer.parseInt(currentSquare.get(columnNames[0])) - OFFSET] =
                        new Amusement(currentSquare.get(columnNames[2]),                            // name
                                Integer.parseInt(currentSquare.get(columnNames[0])),                // position
                                currentSquare.get(columnNames[5]),                                  // color
                                Integer.parseInt(currentSquare.get(columnNames[4])));               // price
                    break;
                case "Chance" :
                    allSquares[Integer.parseInt(currentSquare.get(columnNames[0])) - OFFSET] =
                        new Chance(currentSquare.get(columnNames[2]),                               // name
                                Integer.parseInt(currentSquare.get(columnNames[0])));               // position
                    break;
                case "Railroad" :
                    allSquares[Integer.parseInt(currentSquare.get(columnNames[0])) - OFFSET] =
                        new Railroad(currentSquare.get(columnNames[2]),                             // name
                                Integer.parseInt(currentSquare.get(columnNames[0])),                // position
                                currentSquare.get(columnNames[5]));                                 // color
                    break;
                case "PayToSee" :
                    allSquares[Integer.parseInt(currentSquare.get(columnNames[0])) - OFFSET] =
                            new PayToSee(currentSquare.get(columnNames[2]),                         // name
                                    Integer.parseInt(currentSquare.get(columnNames[0])),            // position
                                    Integer.parseInt(currentSquare.get(columnNames[6])));           // amountToPay
                    break;
                case "GoTo" :
                    allSquares[Integer.parseInt(currentSquare.get(columnNames[0])) - OFFSET] =
                            new GoToRestrooms(currentSquare.get(columnNames[2]),                    // name
                                    Integer.parseInt(currentSquare.get(columnNames[0])),            // position
                                    Integer.parseInt(currentSquare.get(columnNames[7])));           // destination
                    break;
                case "GetMoney" :
                    allSquares[Integer.parseInt(currentSquare.get(columnNames[0])) - OFFSET] =
                            new PennyBag(currentSquare.get(columnNames[2]),                         // name
                                    Integer.parseInt(currentSquare.get(columnNames[0])));           // position
                    break;
                case "Restrooms" :
                    allSquares[Integer.parseInt(currentSquare.get(columnNames[0])) - OFFSET] =
                            new Restrooms(currentSquare.get(columnNames[2]),                        // name
                                    Integer.parseInt(currentSquare.get(columnNames[0])));           // position
                    break;
             }
        }

    }

    public void addBooth(Player player, int position) {
        if(getSquare(position) instanceof Amusement amusement) {
            amusement.setBoothOwner(player);
        } else {
            System.out.println("This Square is not an Amusement... Ignoring addBooth");
        }
    }

    public void removeBooth(int position) {
        if(getSquare(position) instanceof Amusement amusement) {
            amusement.setBoothOwner(null);
        } else {
            System.out.println("This Square is not an Amusement... Ignoring removeBooth");
        }
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

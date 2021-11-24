package Board;

import MonopolyJunior.Player;
import Utilities.CSVReader;
import Utilities.OverloadList;

public class Board {
    private final int OFFSET = 1;
    private final Square[] allSquares;
    private int pennyBagPosition;

    public Board() {
        // Loads the information from the en_board.csv in ressources.
        CSVReader reader;
        try {
            reader = new CSVReader(System.getProperty("user.language") + "_board.csv", ",");
        } catch (Exception e) {
            reader = new CSVReader("en_board.csv", ",");
        }

        OverloadList listOfAllSquareAndProps = reader.getFILE_AS_LIST_OF_ARR();

        allSquares = new Square[listOfAllSquareAndProps.size()];

        // initialises the objects in an array based on the hashmap
        for (int i = 0; i < listOfAllSquareAndProps.size(); i++) {
            String[] currentSquare = listOfAllSquareAndProps.getStringArr(i);

            // Column names is expected to be in the following order in the columnNames:
            int position = 0, type = 1, name = 2, amountGiven = 3, price = 4, color = 5, amountToPay = 6, dest = 7;
            
            switch (currentSquare[type]) {
                case "GO!":
                    allSquares[Integer.parseInt(currentSquare[position]) - OFFSET] =
                            new Go(currentSquare[name],
                                    Integer.parseInt(currentSquare[position]),
                                    Integer.parseInt(currentSquare[amountGiven]));
                    break;
                case "Amusement":
                    allSquares[Integer.parseInt(currentSquare[position]) - OFFSET] =
                            new Amusement(currentSquare[name],
                                    Integer.parseInt(currentSquare[position]),
                                    currentSquare[color],
                                    Integer.parseInt(currentSquare[price]));
                    break;
                case "Chance":
                    allSquares[Integer.parseInt(currentSquare[position]) - OFFSET] =
                            new Chance(currentSquare[name],
                                    Integer.parseInt(currentSquare[position]));
                    break;
                case "Railroad":
                    allSquares[Integer.parseInt(currentSquare[position]) - OFFSET] =
                            new Railroad(currentSquare[name],
                                    Integer.parseInt(currentSquare[position]),
                                    currentSquare[color]);
                    break;
                case "PayToSee":
                    allSquares[Integer.parseInt(currentSquare[position]) - OFFSET] =
                            new PayToSee(currentSquare[name],
                                    Integer.parseInt(currentSquare[position]),
                                    Integer.parseInt(currentSquare[amountToPay]));
                    break;
                case "GoTo":
                    allSquares[Integer.parseInt(currentSquare[position]) - OFFSET] =
                            new GoToRestrooms(currentSquare[name],
                                    Integer.parseInt(currentSquare[position]),
                                    Integer.parseInt(currentSquare[dest]));
                    break;
                case "GetMoney":
                    allSquares[Integer.parseInt(currentSquare[position]) - OFFSET] =
                            new PennyBag(currentSquare[name],
                                    Integer.parseInt(currentSquare[position]));
                    pennyBagPosition = Integer.parseInt(currentSquare[position]);
                    break;
                case "Restrooms":
                    allSquares[Integer.parseInt(currentSquare[position]) - OFFSET] =
                            new Restrooms(currentSquare[name],
                                    Integer.parseInt(currentSquare[position]));
                    break;
            }
        }

    }

    public void addBooth(Player player, int position) {
        if (getSquare(position) instanceof Amusement amusement) {
            amusement.setBoothOwner(player);
        } else {
            System.out.println("This Square is not an Amusement... Ignoring addBooth");
        }
    }

    public void removeBooth(int position) {
        if (getSquare(position) instanceof Amusement amusement) {
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
            if (allSquares[i].getClass().getSimpleName().equals("Amusement")) {
                if (i + OFFSET != position && ((Amusement) allSquares[i]).getColor().equalsIgnoreCase(amusement.getColor())) {
                    if (amusement.getBoothOwner() == null ||
                            ((Amusement) allSquares[i]).getBoothOwner() == null) {
                        result = false;
                    } else if (!(((Amusement) allSquares[i]).getBoothOwner().equals(amusement.getBoothOwner()))) {

                        result = false;
                    }
                }
            }
        }

        return result;
    }

    // Return int[] so the game logic and chance card knows where to place booths
    public int[] getSquarePosByColor(String color) {
        OverloadList listOfPositions = new OverloadList("int");

        // Checks the entire array in case there is implemented more than two Amusements of the same color
        for (Square square : allSquares) {
            if(square instanceof Amusement) {
                if (((Amusement) square).getColor().equalsIgnoreCase(color)) {
                    listOfPositions.add((square.getPosition()));
                }
            }
        }

        return listOfPositions.getListOfInts();
    }

    public int getPennyBagPos() {
        return pennyBagPosition;
    }

    public Square[] getAllSquares() {
        return allSquares;
    }

    public Square getSquare(int position) {
        return allSquares[position - OFFSET];
    }

    public String toString() {
        String result = "";
        for (Square s : allSquares) {
            result = result +  s.getPosition() + " " + s.getName() + "\n";
        }

        return result;
    }
}

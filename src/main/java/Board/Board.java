package Board;

import MonopolyJunior.Player;
import Utilities.CSVReader;
import Utilities.OverloadList;

public class Board {
    private final int OFFSET = 1;
    private final Square[] ALL_SQUARES;
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

        ALL_SQUARES = new Square[listOfAllSquareAndProps.size()];

        // initialises the objects in an array based on the hashmap
        for (int i = 0; i < listOfAllSquareAndProps.size(); i++) {
            String[] currentSquare = listOfAllSquareAndProps.getStringArr(i);

            // Column names is expected to be in the following order in the columnNames:
            int position = 0, type = 1, name = 2, amountGiven = 3, price = 4, color = 5, amountToPay = 6, dest = 7;

            switch (currentSquare[type]) {
                case "GO!":
                    ALL_SQUARES[Integer.parseInt(currentSquare[position]) - OFFSET] =
                            new Go(currentSquare[name],
                                    Integer.parseInt(currentSquare[position]),
                                    Integer.parseInt(currentSquare[amountGiven]));
                    break;
                case "Amusement":
                    ALL_SQUARES[Integer.parseInt(currentSquare[position]) - OFFSET] =
                            new Amusement(currentSquare[name],
                                    Integer.parseInt(currentSquare[position]),
                                    currentSquare[color],
                                    Integer.parseInt(currentSquare[price]));
                    break;
                case "Chance":
                    ALL_SQUARES[Integer.parseInt(currentSquare[position]) - OFFSET] =
                            new Chance(currentSquare[name],
                                    Integer.parseInt(currentSquare[position]));
                    break;
                case "Railroad":
                    ALL_SQUARES[Integer.parseInt(currentSquare[position]) - OFFSET] =
                            new Railroad(currentSquare[name],
                                    Integer.parseInt(currentSquare[position]),
                                    currentSquare[color]);
                    break;
                case "PayToSee":
                    ALL_SQUARES[Integer.parseInt(currentSquare[position]) - OFFSET] =
                            new PayToSee(currentSquare[name],
                                    Integer.parseInt(currentSquare[position]),
                                    Integer.parseInt(currentSquare[amountToPay]));
                    break;
                case "GoTo":
                    ALL_SQUARES[Integer.parseInt(currentSquare[position]) - OFFSET] =
                            new GoToRestrooms(currentSquare[name],
                                    Integer.parseInt(currentSquare[position]),
                                    Integer.parseInt(currentSquare[dest]));
                    break;
                case "GetMoney":
                    ALL_SQUARES[Integer.parseInt(currentSquare[position]) - OFFSET] =
                            new PennyBag(currentSquare[name],
                                    Integer.parseInt(currentSquare[position]));
                    pennyBagPosition = Integer.parseInt(currentSquare[position]);
                    break;
                case "Restrooms":
                    ALL_SQUARES[Integer.parseInt(currentSquare[position]) - OFFSET] =
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

        for (int i = 0; i < ALL_SQUARES.length; i++) {
            if (ALL_SQUARES[i].getClass().getSimpleName().equals("Amusement")) {
                if (i + OFFSET != position && ((Amusement) ALL_SQUARES[i]).getColor().equalsIgnoreCase(amusement.getColor())) {
                    if (amusement.getBoothOwner() == null ||
                            ((Amusement) ALL_SQUARES[i]).getBoothOwner() == null) {
                        result = false;
                    } else if (!(((Amusement) ALL_SQUARES[i]).getBoothOwner().equals(amusement.getBoothOwner()))) {

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
        for (Square square : ALL_SQUARES) {
            if (square instanceof Amusement) {
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
        return ALL_SQUARES;
    }

    public Square getSquare(int position) {
        return ALL_SQUARES[position - OFFSET];
    }

    public String toString() {
        String result = "";
        for (Square s : ALL_SQUARES) {
            result = result + s.getPosition() + " " + s.getName() + "\n";
        }
        return result;
    }
}

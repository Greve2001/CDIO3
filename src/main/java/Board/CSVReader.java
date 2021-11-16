package Board;

import java.io.InputStreamReader;
import java.util.*;

public class CSVReader {
    private String[] columnNames;
    private Scanner fileScanner;
    private final String DELIMITER;
    private final List<HashMap<String, String>> FILE_AS_LIST_OF_HASHMAPS = new ArrayList<>();

    public CSVReader(String file, String delimiter) {
        this.DELIMITER = delimiter;

        // Sets the file as a scanner object
        fileAsScanner(file);

        // Set the columnNames based on the first line.
        setColumnNames();

        // reads the entire file to a list of hashmaps
        readFileToMap();
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public List<HashMap<String, String>> getFILE_AS_LIST_OF_HASHMAPS() {
        return FILE_AS_LIST_OF_HASHMAPS;
    }

    private void fileAsScanner(String file) {
        this.FILE = file;

        // Gets the class loader and reads the file from the ressources folder
        ClassLoader classLoader = Board.class.getClassLoader();
        InputStreamReader reader = new InputStreamReader(Objects.requireNonNull(classLoader.getResourceAsStream(file)));

        fileScanner = new Scanner(reader);
        // System.out.println(fileScanner.nextLine());
    }

    private void setColumnNames(){
        this.columnNames = fileScanner.nextLine().split(",");
    }

    private void readFileToMap(){
        while (fileScanner.hasNextLine()) {
            FILE_AS_LIST_OF_HASHMAPS.add(readLineToHashMap());
        }

        // Closes the scanner.
        fileScanner.close();
    }

    private HashMap<String, String> readLineToHashMap() {
        HashMap<String, String> lineAsMap = new HashMap<>();
        Scanner currentLine = new Scanner(fileScanner.nextLine());
        currentLine.useDelimiter(DELIMITER);
        int column = 0;

        while(currentLine.hasNext()) {
            lineAsMap.put(columnNames[column++] ,currentLine.next());
        }
        return lineAsMap;
    }
}

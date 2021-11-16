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

        // Takes only the first line of the CSV file and sets it as column names
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

    // Opens the file as a scanner object for further use in the class
    private void fileAsScanner(String file) {
        // Gets the class loader and reads the file from the ressources folder
        ClassLoader classLoader = CSVReader.class.getClassLoader();
        InputStreamReader reader = new InputStreamReader(Objects.requireNonNull(classLoader.getResourceAsStream(file)));

        fileScanner = new Scanner(reader);
    }

    // Takes the first line and makes it the column names
    private void setColumnNames(){
        this.columnNames = fileScanner.nextLine().split(DELIMITER);
    }

    // Reads the entire file except the first line to a list of HashMaps and closes the scanner object
    private void readFileToMap(){
        while (fileScanner.hasNextLine()) {
            FILE_AS_LIST_OF_HASHMAPS.add(readLineToHashMap());
        }

        // Closes the scanner.
        fileScanner.close();
    }


    // Reads one line at a time and makes it a HashMap
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

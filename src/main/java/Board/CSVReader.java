package Board;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class CSVReader {
    private String[] columnNames;
    private Scanner fileScanner;
    private final String DELIMITER;

    public CSVReader(String file, String delimiter) {
        this.DELIMITER = delimiter;

        // Sets the file as a scanner object
        fileAsScanner(file);

        // Set the columnNames based on the first line.
        setColumnNames();
    }

    public HashMap<String, String> readLineToHashMap() {
        HashMap<String, String> lineAsMap = new HashMap<>();
        Scanner currentLine = new Scanner(fileScanner.nextLine());
        currentLine.useDelimiter(DELIMITER);
        int column = 0;

        while(currentLine.hasNext()) {
            lineAsMap.put(columnNames[column++] ,currentLine.next());
        }
        return lineAsMap;
    }

    public boolean hasNextLine() {
        return fileScanner.hasNextLine();
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    private void fileAsScanner(String file) {
        // Gets the class loader and reads the file from the ressources folder
        ClassLoader classLoader = Board.class.getClassLoader();
        InputStreamReader reader = new InputStreamReader(Objects.requireNonNull(classLoader.getResourceAsStream(file)));

        fileScanner = new Scanner(reader);
        // System.out.println(fileScanner.nextLine());
    }

    private void setColumnNames(){
        this.columnNames = fileScanner.nextLine().split(",");
    }
}

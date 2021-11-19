package Board;

public class OverloadList {
    private int size = 0;
    private int[] arrOfInts;
    private String[] ArrOfStrings;
    private String[][] ArrOfStringArrs;

    public OverloadList(String type) {
        switch (type) {
            case "int" :
                arrOfInts = new int[20];
                break;
            case "String" :
                ArrOfStrings = new String[20];
                break;
            case "String[]" :
                ArrOfStringArrs = new String[20][1];
                break;
            default:
                System.out.println("Type not defined in list");
        }
    }

    public void add(int integer) {
        if (size >= arrOfInts.length) {
            int[] increasedSizeArr = new int[arrOfInts.length * 2];

            int i = 0;
            for (int value : arrOfInts) {

                increasedSizeArr[i++] = value;
                arrOfInts = increasedSizeArr;
            }
        }

        arrOfInts[size] = integer;
        size++;
    }

    public int getInt(int index) {
        return arrOfInts[index];
    }

    public int[] getListOfInts() {
        return arrOfInts;
    }

    public void add(String string) {
        if (size >= ArrOfStrings.length) {
            String[] increasedSizeArr = new String[ArrOfStrings.length * 2];

            int i = 0;
            for (String value : ArrOfStrings) {

                increasedSizeArr[i++] = value;
                ArrOfStrings = increasedSizeArr;
            }
        }

        ArrOfStrings[size] = string;
        size++;
    }

    public String getString(int index) {
        return ArrOfStrings[index];
    }

    public String[] getListOfStrings() {
        return ArrOfStrings;
    }

    public void add(String[] string) {
        if (size >= ArrOfStringArrs.length) {
            String[][] increasedSizeArr = new String[ArrOfStringArrs.length * 2][1];

            int i = 0;
            for (String[] value : ArrOfStringArrs) {

                increasedSizeArr[i++] = value;
                ArrOfStringArrs = increasedSizeArr;
            }
        }

        ArrOfStringArrs[size] = string;
        size++;
    }

    public String[] getStringArr(int index) {
        return ArrOfStringArrs[index];
    }

    public String[][] getListOfStringArr() {
        return ArrOfStringArrs;
    }

    public int size() {
        return size;
    }
}
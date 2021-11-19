package Board;

public class OverloadList {
    private int size = 0;
    private int[] arrOfInts;
    private String[] ArrOfStrings;

    public OverloadList(String type) {
        switch (type) {
            case "int" :
                arrOfInts = new int[20];
                break;
            case "String" :
                ArrOfStrings = new String[20];
                break;
            default:
                System.out.println("Type not defined in list");
        }
    }

    public void add(int integer) {
        if (size > arrOfInts.length) {
            int[] increasedSizeArr = new int[arrOfInts.length * 2];

            int i = 0;
            for (int value : arrOfInts) {

                arrOfInts = increasedSizeArr;
                increasedSizeArr[i++] = value;
            }

            arrOfInts[size] = integer;
            size++;
        }
    }

    public int get(int index, int typeOf) {
        return arrOfInts[index];
    }

    public int[] getList(int typeOf) {
        return arrOfInts;
    }

    public void add(String string) {
        if (size > ArrOfStrings.length) {
            String[] increasedSizeArr = new String[ArrOfStrings.length * 2];

            int i = 0;
            for (String value : ArrOfStrings) {

                ArrOfStrings = increasedSizeArr;
                increasedSizeArr[i++] = value;
            }

            ArrOfStrings[size] = string;
            size++;
        }
    }

    public String get(int index, String typeOf) {
        return ArrOfStrings[index];
    }

    public String[] getList(String typeOf) {
        return ArrOfStrings;
    }

    public int size() {
        return size;
    }
}
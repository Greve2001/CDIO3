package Utilities;

import java.util.Arrays;

public class OverloadList {
    private int size = 0;
    private int[] arrOfInts;
    private String[] ArrOfStrings;
    private String[][] ArrOfStringArs;
    private final String TYPE;

    public OverloadList(String type) {
        this.TYPE = type;

        switch (type) {
            case "int" :
                arrOfInts = new int[20];
                break;
            case "String" :
                ArrOfStrings = new String[20];
                break;
            case "String[]" :
                ArrOfStringArs = new String[20][1];
                break;
            default:
                System.out.println("Type not defined in list");
        }
    }

    public void add(int integer) {
        if (TYPE.equals("int")) {
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
    }

    public int getInt(int index) {
        return arrOfInts[index];
    }

    public int[] getListOfInts() {
        return Arrays.copyOf(arrOfInts, size);
    }

    public void add(String string) {
        if (TYPE.equals("String")) {
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
    }

    public String getString(int index) {
        return ArrOfStrings[index];
    }

    public String[] getListOfStrings() {
        return Arrays.copyOf(ArrOfStrings, size);
    }

    public void add(String[] string) {
        if (TYPE.equals("String[]")) {
            if (size >= ArrOfStringArs.length) {
                String[][] increasedSizeArr = new String[ArrOfStringArs.length * 2][1];

                int i = 0;
                for (String[] value : ArrOfStringArs) {

                    increasedSizeArr[i++] = value;
                    ArrOfStringArs = increasedSizeArr;
                }
            }

            ArrOfStringArs[size] = string;
            size++;
        }
    }

    public String[] getStringArr(int index) {
        return ArrOfStringArs[index];
    }

    public String[][] getListOfStringArr() {
        return Arrays.copyOf(ArrOfStringArs, size);
    }

    public int size() {
        return size;
    }
}
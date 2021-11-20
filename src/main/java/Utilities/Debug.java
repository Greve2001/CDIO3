package Utilities;

import java.util.Scanner;

public class Debug {

    static Scanner input = new Scanner(System.in);

    private static final Debug instance = new Debug();

    private Debug(){}

    public static Debug getInstance(){
        return instance;
    }

    static boolean showPrints = true;

    // Handles prints, will not print when boolean is false.
    // Should only be used for debugging
    public static void println(String str){
        if (showPrints){
            System.out.println(str);
        }
    }

    public static void print(String str){
        if (showPrints){
            System.out.print(str);
        }
    }

    public static void strInput(){
        if (showPrints){
            input.nextLine();
        }
    }
}

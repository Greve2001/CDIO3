package Utilities;

import java.util.Scanner;

public class Debug {

    static boolean enableDebugging = false;
    static Scanner input;
    
    private static Debug instance;

    private Debug(){
        if (enableDebugging){
            input = new Scanner(System.in);
        }
    }

    public static Debug getInstance(){
        if (instance == null){
            instance = new Debug();
        }
        return instance;
    }


    // Handles prints, will not print when boolean is false.
    // Should only be used for debugging
    public static void println(String str){
        if (enableDebugging){
            System.out.println(str);
        }
    }
    public static void print(String str){
        if (enableDebugging){
            System.out.print(str);
        }
    }

    public static void strInput(){
        if (enableDebugging){
            input.nextLine();
        }
    }
}

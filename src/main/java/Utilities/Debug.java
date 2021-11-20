package Utilities;

public class Debug {

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
}
